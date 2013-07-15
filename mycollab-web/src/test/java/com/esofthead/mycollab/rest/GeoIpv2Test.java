package com.esofthead.mycollab.rest;

import java.io.File;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.Country;

public class GeoIpv2Test {
	public static void main(String[] args) {
		try {
			InetAddress[] addresses = InetAddress
					.getAllByName("2405:4800:503:0000::1");

			com.maxmind.geoip2.DatabaseReader reader = new DatabaseReader(
					new File("GeoLite2-City.mmdb"));

			for (InetAddress address : addresses) {
				System.out.println(address);
				Country city = reader.country(address);
				System.out.println(city.getCountry().getName());
			}
			reader.close();
		} catch (Exception e) {
		}
	}
}
