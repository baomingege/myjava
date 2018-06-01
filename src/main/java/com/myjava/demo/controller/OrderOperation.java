package com.myjava.demo.controller;

import com.myjava.demo.entity.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static com.myjava.demo.repo.FormRepo.getForms;
import static com.myjava.demo.repo.FormRepo.setForms;
import static com.myjava.demo.repo.ProductRepo.*;
import static com.myjava.demo.repo.UserRepo.getUsers;

@Controller
public class OrderOperation {

    //增加订单
    @RequestMapping(value = "/addForm", method = RequestMethod.POST)
    @ResponseBody
    public String  addForm(@RequestParam("useName") String useName,@RequestParam("proName") String proName) {
        String aa = "";
        //String dd="";
        User user = new User();
        Product pro=new Product();
        JSONObject json = new JSONObject();  //创建Json对象

        if (getUsers().size() > 0) {
           // type = "if内";
            for (int i = 0; i < getUsers().size(); i++) {
                if (getUsers().get(i).getName().equals(useName)) {
                    user = getUsers().get(i);
                    break;
                } else if (i == getUsers().size() - 1) {
                    try {
                        json.put("addForm", "error您输入的名字不在数据库中，请重新输入:");
                        aa = json.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return aa;
                }
            }
        }else{
            try {
                json.put("addForm", "error您输入的商品不在数据库中，请重新输入:");
                aa = json.toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return aa;
        }
        if(getProMar().size()>0){
            for(int j=0;j<getProMar().size();j++){
                if(getProMar().get(j).getName().equals(proName)){
                    pro = getProMar().get(j);
                    break;
                }else if(j == getProMar().size()-1){
                    try {
                        json.put("addForm", "error您输入的名字不在数据库中，请重新输入:");
                        aa = json.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return aa;
                }
            }
        }else{
            try {
                json.put("addForm", "error您输入的名字不在数据库中，请重新输入:");
                aa = json.toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return aa;
        }

        double money=0;

        if("Fresh".equals(pro.getType())){
            money = 30;
        }else if("Fruit".equals(pro.getType())){
            money = 20;
        }else if("Greens".equals(pro.getType())){
            money = 10;
        }
        Date date = new Date();
        long orderNum = date.getTime();
        OrderForm form = new OrderForm();
        form.setUser(user);
        form.setPro(pro);
        form.setDate(date);
        form.setMoney(money);
        form.setOrderNum(orderNum);
        setForms(form);
        aa = "success增加订单成功;";
        aa += pro.stockOut();
        try {
            json.put("addForm", aa);
            aa = json.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    //排序订单
    @RequestMapping(value = "/formSort", method = RequestMethod.GET)
    @ResponseBody
    public Object querySort(@RequestParam("attr") String attr){
        String ss="";
        List<OrderForm> list = new ArrayList<OrderForm>();
        if("money".equals(attr)){
            Collections.sort(getForms(), (OrderForm o1, OrderForm o2) -> o1.getDate().compareTo(o2.getDate()));
            //System.out.println("/////////////排序之后///////////////");
            for(OrderForm form:getForms()){
                list.add(form);
            }
            //ss = list.toString();
//            new Comparator<OrderForm>(){
//                @Override
//                public int compare(OrderForm o1, OrderForm o2) {
//                    if(o1.getMoney()>o2.getMoney()){
//                        return 1;
//                    }else if(o1.getMoney()==o2.getMoney()){
//                        return 0;
//                    }else{
//                        return -1;
//                    }
//                }
//            }
        }else if("date".equals(attr)){
            Collections.sort(getForms(), (OrderForm o1, OrderForm o2) -> o1.getDate().compareTo(o2.getDate()));
            for(OrderForm form:getForms()){
                list.add(form);
            }
            //ss = list.toString();
        }else{
            ss = "error您输入的指令有误，请重新输入：";
        }
        JSONObject json = new JSONObject();  //创建Json对象
        try {
            json.put("error", ss);
            //ss = json.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list.size() !=0?list:json.toString();
    }
    //过滤订单
    @RequestMapping(value = "/queryFilter", method = RequestMethod.GET)
    @ResponseBody
    public Object queryFilter(@RequestParam("term") String term,@RequestParam("attr") String attr){
        String ff="";
        ArrayList<OrderForm> filter = new ArrayList<OrderForm>();
        if("user".equals(term)){

            if(getForms().size()>0){
                for(int i=0;i<getForms().size();i++){
                    if(getForms().get(i).getUser().getName().equals(attr)){
                        filter.add(getForms().get(i));
                    }else if(i==(getForms().size()-1)&&filter.size()==0){
                        ff="error没有匹配的订单，请重新查询：";
                    }
                }
                //ff = filter1.toString();
                //System.out.println("查询filter成功");
//                for(int j=0;j<filter1.size();j++){
//                    ff += filter1.get(j).toStr();
//                }
            }else{
                ff = "error目前没有订单，请执行其他操作！";
            }
        }else if("product".equals(term)){
            //ArrayList<OrderForm> filter2 = new ArrayList<OrderForm>();
            if(getForms().size()>0){
                for(int i=0;i<getForms().size();i++){
                    // System.out.println("gg");
                    if(getForms().get(i).getPro().getName().equals(attr)){
                        filter.add(getForms().get(i));
                        //System.out.println(filter2.size());
                    }else if(i==(getForms().size()-1)&&filter.size()==0){
                        ff="error没有匹配的订单，请重新查询：";
                    }
                }
//                for(int j=0;j<filter2.size();j++){
//                    ff += filter2.get(j).toString()+"\n";
//                }
            }else{
                ff ="error目前没有订单，请执行其他操作！";
            }
        }else{
            ff = "error查询条件不支持";
        }
        JSONObject json = new JSONObject();  //创建Json对象
        try {
            json.put("error", ff);
            //ss = json.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return filter.size() != 0 ?filter:json.toString();
    }
}
