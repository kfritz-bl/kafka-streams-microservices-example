package com.blc.domaincrawler;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Domain {

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
