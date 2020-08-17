package com.wxuy.example.entity;

import lombok.Data;

@Data
public class City {
    private int id;
    private int province_id;
    private String city_name;
    private String description;
}
