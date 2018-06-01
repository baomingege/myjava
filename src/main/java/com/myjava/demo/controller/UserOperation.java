package com.myjava.demo.controller;

import com.myjava.demo.entity.User;
import com.myjava.demo.repo.ProductRepo;
import com.myjava.demo.repo.UserRepo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Controller
public class UserOperation {
    //Scanner sc = new Scanner(System.in);
    //public static ArrayList<User> users = new ArrayList<User>();
    //增加人员
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public Object  addUser( @RequestParam("name") String name, @RequestParam("address") String address, @RequestParam("telephone") String telephone){
        JSONObject json = new JSONObject();
        User user = new User();
        long id = new Date().getTime();
        user.setId(id);
        user.setName(name);
        user.setAddress(address);
        user.setTelephone(telephone);
        UserRepo.setUsers(user);
        try{
            json.put("addUser", "success");

        }catch(JSONException e){
            e.printStackTrace();
        }
        return json.toString();
        //TestEMD.operation();
    }
    //删除人员
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    @ResponseBody
    public Object  deleteUser(@RequestParam("name") String name){
        String df = "";
        if(UserRepo.getUsers().size() > 0){
            for(int i = 0; i<UserRepo.getUsers().size(); i++){
                if(name.equals(UserRepo.getUsers().get(i).getName())){
                    UserRepo.getUsers().remove(i);
                    df = "删除成功！";
                }else if(i==(UserRepo.getUsers().size()-1)){
                    df = "查无此人，请重新输入：";
                }
            }
        }else{
            df = "查无此人，请重新输入：";
        }
        JSONObject json = new JSONObject();
        try{
            json.put("deleteUser", df);

        }catch(JSONException e){
            e.printStackTrace();
        }
        return json.toString();
        //return df;
    }
    //更新人员
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public Object  updateUser(@RequestParam("name") String name,@RequestParam("address") String address,@RequestParam("telephone") String telephone){
        String df = "";
         df = " "+UserRepo.getUsers().size();
        JSONObject json = new JSONObject();
        if(UserRepo.getUsers().size()>0){
            //df ="11";
            for(int i = 0; i<UserRepo.getUsers().size(); i++){
                //df ="22";
                if(name.equals(UserRepo.getUsers().get(i).getName())){
                    UserRepo.getUsers().get(i).setAddress(address);
                    UserRepo.getUsers().get(i).setTelephone(telephone);
                    df = "更新人员信息成功！";
                    try{
                        json.put("updateUser", df);

                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                    return json.toString();
                }else if(i==(UserRepo.getUsers().size()-1)){
                    df = "查无此人，请重新输入：";
                }
            }
        }else{
            df = "查无此人，请重新输入：";
        }

        try{
            json.put("updateUser", df);

        }catch(JSONException e){
            e.printStackTrace();
        }
        return json.toString();
        //return " ";
    }
    //查询人员
    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    @ResponseBody
    public Object  queryUser(@RequestParam("name") String name){
        String dd = "";
        List<User> list = new ArrayList<User>();
        if(UserRepo.getUsers().size()>0){
            for(int i = 0; i<UserRepo.getUsers().size(); i++){
                if(name.equals(UserRepo.getUsers().get(i).getName())){
                    list.add(UserRepo.getUsers().get(i));
                    break;
                }else if(i==(UserRepo.getUsers().size()-1)){
                    dd = "查无此人，请重新输入：";
                }
            }
        }else{
            dd = "查无此人，请重新输入：";
        }
        JSONObject json = new JSONObject();
        try{
            json.put("queryUser", dd);

        }catch(JSONException e){
            e.printStackTrace();
        }
        return list.size() !=0?list:json.toString();
        //return dd;
    }
    //查询所有人
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    @ResponseBody
    public Object  queryAll(){
        List<User> list = new ArrayList<User>();
        for(int j = 0; j<UserRepo.getUsers().size(); j++){
           list.add(UserRepo.getUsers().get(j));
        }
        return list;
//        JSONObject json = new JSONObject();
//        try{
//            json.put("queryUser", list);
//
//        }catch(JSONException e){
//            e.printStackTrace();
//        }
//        return json.toString();
    }

}
