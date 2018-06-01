package com.myjava.demo.entity;

import com.myjava.demo.repo.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import static com.myjava.demo.repo.ProductRepo.delProMar;
import static com.myjava.demo.repo.ProductRepo.setProSal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    //private  Long ifdfg;
    private  Long id;
    private  String name;
    private  Date createDate;
    private  String type;
    public String stockOut(){

        setProSal(this);
        delProMar(this);
        return this.proOut();
    }
    public String proOut(){
        return "商品出库";
    }
    public String proIn(){
        return "商品入库";
    }

    public String toStr(){
        JSONObject json = new JSONObject();  //创建Json对象

        try {
            json.put("proID", this.getId());
            json.put("proName", this.getName());
            json.put("proCreateDate", this.getCreateDate());
            json.put("proType", this.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
        //return this.getId()+this.getType()+this.getCreateDate() +this.getName();
    }
    public String stockIn(){
        ProductRepo.setProSum(this);
        ProductRepo.setProMar(this);
        return this.proIn();
    }
    public int compareTo(long id){
        int result = 0;
        if(this.getId() > id){
            result = 1;
        }else if(this.getId() == id){
            result = 0;
        }else{
            result = -1;
        }
        return result;
    }
}
