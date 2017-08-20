package com.timposu.pembayaran.pembayaranserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PembayaranServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PembayaranServerApplication.class, args);
	}
}
