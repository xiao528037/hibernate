package com.xiao;

import lombok.Data;

@Data
public class GoodsEntity {
    public GoodsEntity() {
    }
    public GoodsEntity(String name, Double price) {
        this.name = name;
        this.price = price;
    }
    private int sid;
    private String name;
    private Double price;

}
