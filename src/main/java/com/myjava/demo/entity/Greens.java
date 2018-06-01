package com.myjava.demo.entity;

import java.util.Date;

import static com.myjava.demo.repo.ProductRepo.*;

public class Greens extends Product {
    public Greens(long id, String name, Date date, String type){
        super(id,name,date,type);
    }
    public String proOut(){
        setGreSal((Greens)this);
        delGreMar((Greens)this);
        return "Greens商品出库";
    }
    public String proIn(){
        setGreSum((Greens) this);
        setGreMar((Greens) this);
        return "Greens商品入库";
    }
}
