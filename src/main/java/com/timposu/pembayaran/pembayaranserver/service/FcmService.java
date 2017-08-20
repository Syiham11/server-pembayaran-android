 package com.timposu.pembayaran.pembayaranserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timposu.pembayaran.pembayaranserver.dao.AntrianFcmDao;
import com.timposu.pembayaran.pembayaranserver.entity.AntrianFcm;
import com.timposu.pembayaran.pembayaranserver.entity.StatusAntrian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FcmService {

    private static final String API_KEY = "AIzaSyBTAEcWLwDdymqSgz9ErO8WCs8UBYPT0eI";
    private static final String URL_FCM_SERVER = "https://fcm.googleapis.com/fcm/send";

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AntrianFcmDao antrianFcmDao;

    private final Logger LOGGER = LoggerFactory.getLogger(FcmService.class);

    public FcmService() {
        restTemplate.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                                ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().add("Authorization", "key=" + API_KEY);

                return execution.execute(request, body);
            }
        }));
    }

    public void kirimFcmMessage(String tujuan, Map<String, Object> data) {
        try {
            AntrianFcm a = new AntrianFcm();
            a.setTujuan("/topics/produk");
            a.setData(objectMapper.writeValueAsString(data));
            antrianFcmDao.save(a);
        } catch (JsonProcessingException e) {
            LOGGER.warn("FCM Gagal ke antrian : {}" + e.getMessage());
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void prosesAntrianFcm(){
        PageRequest pr = new PageRequest(0, 1);

        Page<AntrianFcm> antrianTeratas = antrianFcmDao
                .findByStatusOrderByWaktuKirimAsc(StatusAntrian.BARU, pr);

        LOGGER.debug("FCM : proses memproses {} antrian dari {}",
                antrianTeratas.getNumberOfElements(),
                antrianTeratas.getTotalElements());

        if (antrianTeratas.getNumberOfElements() < 1) {
            return;
        }

        AntrianFcm a = antrianTeratas.getContent().get(0);
        prosesPengiriman(a);
        antrianFcmDao.save(a);
    }

    private void prosesPengiriman(AntrianFcm a) {
        try {
            Map<String, Object> fcmRequest = new HashMap<>();
            fcmRequest.put("to", a.getTujuan());
            fcmRequest.put("data", objectMapper.readValue(a.getData(), Map.class));
            Map<String, Object> hasil = restTemplate.postForObject(URL_FCM_SERVER, fcmRequest, Map.class);
            LOGGER.debug("FCM SUKSES: [{}]", hasil.get("success"));
            a.setStatus(StatusAntrian.TERKIRIM);
            a.setWaktuKirim(new Date());
        }
        catch (Exception e) {
            LOGGER.warn("FCM Gagal : {}" + e.getMessage());
            a.setStatus(StatusAntrian.GAGAL_KIRIM);
            a.setWaktuKirim(new Date());
            a.setKeterangan(e.getMessage());
        }

    }
}
