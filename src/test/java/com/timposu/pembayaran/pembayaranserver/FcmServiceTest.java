package com.timposu.pembayaran.pembayaranserver;

import com.timposu.pembayaran.pembayaranserver.service.FcmService;
import com.timposu.pembayaran.pembayaranserver.service.PembayaranService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FcmServiceTest {

    @Autowired private FcmService fcm;

    @Test
    public void testKirimFcm() throws Exception{
        //FcmService fcm = new FcmService();
        Map<String, Object> data = new HashMap<>();
        data.put("action","Pesan dari Server");
        fcm.kirimFcmMessage("fVphlR9PTew:APA91bF1Hc1Hok5Lasb-bDcXiTQTI6AWPgkLtOBs8rIdzzuHbu0Q1d2dWY2FYXHZhIikEG5FjjN__4-o43MGYFqp4nmyNylkY2pAgr-8AFZfZwRzg-g7wQ9ozEknNWG1Zm-1q-bbmGBk",
                data);

	Thread.sleep(10000);
    }
}
