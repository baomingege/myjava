package com.myjava.demo.repo;

import com.myjava.demo.entity.Fresh;
import com.myjava.demo.entity.Fruit;
import com.myjava.demo.entity.Greens;
import com.myjava.demo.entity.Product;

import java.util.ArrayList;

public class ProductRepo {
    private static ArrayList<Fresh> freSum = new ArrayList<Fresh>();
    private static ArrayList<Fresh> freMar = new ArrayList<Fresh>();
    private static ArrayList<Fresh> freSal = new ArrayList<Fresh>();
    private static ArrayList<Fruit> fruSum = new ArrayList<Fruit>();
    private static ArrayList<Fruit> fruMar = new ArrayList<Fruit>();
    private static ArrayList<Fruit> fruSal = new ArrayList<Fruit>();
    private static ArrayList<Greens> greSum = new ArrayList<Greens>();
    private static ArrayList<Greens> greMar = new ArrayList<Greens>();
    private static ArrayList<Greens> greSal = new ArrayList<Greens>();
    private static ArrayList<Product> proSum = new ArrayList<Product>();
    private static ArrayList<Product> proMar = new ArrayList<Product>();
    private static ArrayList<Product> proSal = new ArrayList<Product>();

    public static ArrayList<Fresh> getFreSum() {
        return freSum;
    }

    public static void setFreSum(Fresh fresh) {
        freSum.add(fresh);
    }

    public static ArrayList<Fresh> getFreMar () {
        return freMar;
    }

    public static void setFreMar (Fresh fresh){
        freMar.add(fresh);
    }

    public static void delFreMar(Fresh pro){
        getFreMar().remove(pro);
    }

    public static ArrayList<Fresh> getFreSal () {
        return freSal;
    }

    public static void setFreSal (Fresh fresh) {
        freSal.add(fresh);
    }

    public static ArrayList<Fruit> getFruSum () {
        return fruSum;
    }

    public static void setFruSum (Fruit fruit) {
        fruSum.add(fruit);
    }

    public static ArrayList<Fruit> getFruMar () {
        return fruMar;
    }

    public static void setFruMar (Fruit fruit) {
        fruMar.add(fruit);
    }

    public static void delFruMar(Fruit pro){
        getFruMar().remove(pro);
    }

    public static ArrayList<Fruit> getFruSal () {
        return fruSal;
    }

    public static void setFruSal (Fruit fruit) {
        fruSal.add(fruit);
    }

    public static ArrayList<Greens> getGreSum () {
        return greSum;
    }

    public static void setGreSum (Greens greens) {
        greSum.add(greens);
    }

    public static ArrayList<Greens> getGreMar () {
        return greMar;
    }

    public static void setGreMar (Greens greens) {
        greMar.add(greens);
    }

    public static void delGreMar(Greens pro){
        getGreMar().remove(pro);
    }

    public static ArrayList<Greens> getGreSal () {
        return greSal;
    }

    public static void setGreSal (Greens greens) {
        greSal.add(greens);
    }

    public static ArrayList<Product> getProSum () {
        return proSum;
    }

    public static void setProSum (Product product) {
        proSum.add(product);
    }

    public static ArrayList<Product> getProMar () {
        return proMar;
    }

    public static void setProMar (Product product) {
        proMar.add(product);
    }

    public static void delProMar(Product pro){
        getProMar().remove(pro);
    }

    public static ArrayList<Product> getProSal () {
        return proSal;
    }

    public static void setProSal (Product product) {
        proSal.add(product);
    }

}
