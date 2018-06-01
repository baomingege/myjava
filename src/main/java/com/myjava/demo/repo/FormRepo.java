package com.myjava.demo.repo;

import com.myjava.demo.entity.OrderForm;

import java.util.ArrayList;

public class FormRepo {
    private static ArrayList<OrderForm> forms= new ArrayList<OrderForm>();
    public static void setForms(OrderForm form){
        forms.add(form);
    }
    public static ArrayList<OrderForm> getForms(){

        return forms;
    }
}
