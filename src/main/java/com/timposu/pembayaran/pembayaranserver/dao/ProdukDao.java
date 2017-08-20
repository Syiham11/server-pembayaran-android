package com.timposu.pembayaran.pembayaranserver.dao;

import com.timposu.pembayaran.pembayaranserver.entity.Produk;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ucup on 8/18/17.
 */

public interface ProdukDao extends PagingAndSortingRepository<Produk, String> {
}
