package com.timposu.pembayaran.pembayaranserver.service;

import com.timposu.pembayaran.pembayaranserver.dao.ProdukDao;
import com.timposu.pembayaran.pembayaranserver.entity.AntrianFcm;
import com.timposu.pembayaran.pembayaranserver.entity.Produk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ucup on 8/18/17.
 */

@Service
public class PembayaranService {
    @Autowired private ProdukDao produkDao;
    @Autowired private FcmService fcmService;

    public void simpan(Produk p) {
        produkDao.save(p);

        Map<String, Object> data = new HashMap<>();
        data.put("action" , "update");
        data.put("content", "produk");

        fcmService.kirimFcmMessage("/topics/produk", data);
    }

    public Page<Produk> semuaProduk(Pageable page) {
        return produkDao.findAll(page);
    }

    public Long countAllProduk() {
        return produkDao.count();
    }
}
