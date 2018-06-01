package com.myjava.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private String address;
    private String telephone;

    public String toStr(){
        JSONObject json = new JSONObject();  //创建Json对象
        try {
            json.put("userID", this.getId());
            json.put("userName", this.getName());
            json.put("userAddress", this.getAddress());
            json.put("userTelephone", this.getTelephone());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
