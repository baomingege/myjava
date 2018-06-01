package com.myjava.demo.repo;

import com.myjava.demo.entity.User;

import java.util.ArrayList;


public class UserRepo {
    private static ArrayList<User> users = new ArrayList<User>();
    public static void setUsers(User user){
        users.add(user);
    }
    public static ArrayList<User> getUsers(){

        return users;
    }
//    public static void deleteUsers(User user){
//        users.remove(user);
//    }
}
