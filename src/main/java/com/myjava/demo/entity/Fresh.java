package com.myjava.demo.entity;

import java.util.Date;

import static com.myjava.demo.repo.ProductRepo.*;

public class Fresh extends Product {
    public Fresh(long id,String name,Date date,String type){
        super(id,name,date,type);
    }
    public String proOut(){
        setFreSal((Fresh)this);
        delFreMar((Fresh)this);
        return "Fresh商品出库";
    }
    public String proIn(){
        setFreSum((Fresh) this);
        setFreMar((Fresh) this);
        return "Fresh商品入库";
    }
}
