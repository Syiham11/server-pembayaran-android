package com.timposu.pembayaran.pembayaranserver.dao;

import com.timposu.pembayaran.pembayaranserver.entity.AntrianFcm;
import com.timposu.pembayaran.pembayaranserver.entity.StatusAntrian;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by ucup on 8/18/17.
 */

public interface AntrianFcmDao extends PagingAndSortingRepository<AntrianFcm, String> {
    public Page<AntrianFcm> findByStatusOrderByWaktuKirimAsc(StatusAntrian baru, Pageable p);
}
