package com.myjava.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {
    private User user;
    private Product pro;
    private double money;
    private Date date;
    private long orderNum;
    public String toStr(){
        JSONObject json = new JSONObject();  //创建Json对象
        try {
            json.put("用户信息", this.getUser().toString());
            json.put("商品信息", this.getPro().toString());
            json.put("订单金额", this.getMoney());
            json.put("订单创建时间", this.getDate());
            json.put("订单编号", this.getOrderNum());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
