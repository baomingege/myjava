package com.myjava.demo.entity;

import java.util.Date;

import static com.myjava.demo.repo.ProductRepo.*;

public class Fruit extends Product {
    public Fruit(long id, String name, Date date, String type){
        super(id,name,date,type);
    }
    public String proOut(){
        setFruSal((Fruit)this);
        delFruMar((Fruit)this);
        return "Fruit商品出库";
    }
    public String proIn(){
        setFruSum((Fruit) this);
        setFruMar((Fruit) this);
        return "Fruit商品入库";
    }
}
