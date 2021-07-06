package com.blc.domaincrawler;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Domain implements Serializable {
	
	String domain;
	String create_date;
	String update_date;
	String country;
	boolean isDead;
	String A;
	String NS;
	String CNAME;
	String MX;
	String TXT;
	
}
