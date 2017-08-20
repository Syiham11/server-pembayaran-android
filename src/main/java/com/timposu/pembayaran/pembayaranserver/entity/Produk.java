package com.timposu.pembayaran.pembayaranserver.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by ucup on 8/15/17.
 */

@Entity
@Table(name = "m_produk")
public class Produk extends BaseEntity {

    @NotNull @NotEmpty
    @Column(nullable = false, unique = true)
    private String kode;

    @NotNull @NotEmpty
    @Column(nullable = false, unique = true)
    private String nama;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}