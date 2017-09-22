package com.timposu.pembayaran.pembayaranserver.controller;

import com.timposu.pembayaran.pembayaranserver.entity.Produk;
import com.timposu.pembayaran.pembayaranserver.service.PembayaranService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by ucup on 11/09/17.
 */

@RestController
@RequestMapping("/api/produk")
public class ProdukController {

    @Autowired
    private PembayaranService pembayaranService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Produk p) {
        pembayaranService.simpan(p);
    }

    @GetMapping("/")
    public Page<Produk> semuaProduk() {
        Long countAll = pembayaranService.countAllProduk();
        PageRequest pr = new PageRequest(0, countAll.intValue());

        return pembayaranService.semuaProduk(pr);
    }
}
