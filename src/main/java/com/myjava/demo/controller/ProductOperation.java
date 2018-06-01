package com.myjava.demo.controller;

import com.myjava.demo.entity.*;
import com.myjava.demo.repo.ProductRepo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.*;

//import static com.myjava.demo.repo.ProductRepo.getProMar;
import static com.myjava.demo.repo.ProductRepo.getProSum;

@Controller
public class ProductOperation {
    //增加商品
    @RequestMapping(value = "/addPro", method = RequestMethod.POST)
    @ResponseBody
    public Object addPro( @RequestParam("name") String name ,@RequestParam("type") String type ){
        String aa;
        Product pro;
        long id=new Date().getTime();
        Date date = new Date();
        if("Fresh".equals(type)){
            pro = new Fresh(id,name,date,type);
            aa = pro.stockIn();
        }else if("Fruit".equals(type)){
            pro = new Fruit(id,name,date,type);
            aa = pro.stockIn();
        }else if("Greens".equals(type)){
            pro = new Greens(id,name,date,type);
            aa = pro.stockIn();
        }else{
            aa = "无此商品类型，请重新输入：";
        }
        JSONObject json = new JSONObject();
        try{
            json.put("addPro", aa);

        }catch(JSONException e){
            e.printStackTrace();
        }
        return json.toString();
        //return aa;
    }

    //查询商品
    @RequestMapping(value = "/queryPro", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object queryPro(@RequestParam("type") String type){

        JSONObject json = new JSONObject();
        if("Fresh".equals(type)){
            try{
                json.put("Fre_SUM", ProductRepo.getFreSum().size());
                json.put("Fre_MAR", ProductRepo.getFreMar().size());
                json.put("Fre_SAL", ProductRepo.getFreSal().size()*30);
            }catch(JSONException e){
                e.printStackTrace();
            }
        }else if("Fruit".equals(type)){
            try{
                json.put("Fru_SUM", ProductRepo.getFruSum().size());
                json.put("Fru_MAR", ProductRepo.getFruMar().size());
                json.put("Fru_SAL", ProductRepo.getFruSal().size()*20);
            }catch(JSONException e){
                e.printStackTrace();
            }
            //cc= "Fruit商品总量："+ProductRepo.getFruSum().size()+";"+"Fruit商品余量："+ProductRepo.getFruMar().size()+";"+"Fruit销售额："+ProductRepo.getFruSal().size()*20;
        }else if("Greens".equals(type)){
            try{
                json.put("Gre_SUM", ProductRepo.getGreSum().size());
                json.put("Gre_MAR", ProductRepo.getGreMar().size());
                json.put("Gre_SAl", ProductRepo.getGreSal().size()*10);
            }catch(JSONException e){
                e.printStackTrace();
            }
            //cc = "Greens商品总量："+ProductRepo.getGreSum().size()+";"+"Greens商品余量："+ProductRepo.getGreMar().size()+";"+"Greens销售额："+ProductRepo.getGreSal().size()*10;
        }else{
            try{
                json.put("error", "您输入的指令有误，请重新输入");

            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return json.toString();
    }
    //排序商品
    @RequestMapping(value = "/querySort", method = RequestMethod.GET)
    @ResponseBody
    public Object querySort(@RequestParam("attr") String attr) {

        String dd = "";
        List<Product> list = new ArrayList<Product>();
        JSONObject json = new JSONObject();  //创建Json对象
        if ("id".equals(attr)) {
            Collections.sort(getProSum(), new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            if (o1.getId() > o2.getId()) {
                                return 1;
                            } else if (o1.getId() == o2.getId()){
                                return 0;
                            } else {
                                return -1;
                            }
                        }
                    }
            );
            for (Product pro:getProSum()) {
                list.add(pro);
            }
            //dd = list.toString();
        }else if ("name".equals(attr)) {
           // dd = "";
            Collections.sort(getProSum(), (Product o1, Product o2) -> o1.getName().compareTo(o2.getName()));
            //System.out.println("/////////////排序之后///////////////");
            for (Product pro:getProSum()) {
                list.add(pro);
            }
            //dd = list.toString();
        } else if ("createDate".equals(attr)) {
            //dd = "";
            Collections.sort(getProSum(), (Product o1, Product o2) -> o1.getCreateDate().compareTo(o2.getCreateDate()));
            //System.out.println("/////////////排序之后///////////////");
            for (Product pro:getProSum()) {
                list.add(pro);
            }
            //dd = list.toString();
        } else {


            try {
                json.put("error", "输入有误，请重新输入");
                dd = json.toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //list.add("error");
        }
        return list.size()!=0?list : json.toString();
    }

//    @RequestMapping(value = "/salProduct", method = RequestMethod.POST)
//    @ResponseBody
//    public String salPro(@RequestParam("name") String name){
//        String bb = "";
//        for(int i=0;i<proMar.size();i++){
//            if(proMar.get(i).getName().equals(name)){
//                proSal.add(proMar.get(i));
//                switch (proMar.get(i).getType()){
//                    case "fresh":
//                        freSal.add((Fresh)proMar.get(i));
//                        for(int j=0;j<freMar.size();j++){
//                            if(freMar.get(j).getName().equals(name)){
//                                freMar.remove(j);
//                            }
//                        }
//                        bb = "售出fresh商品成功！";
//                        //productManage();
//                        break;
//                    case "fruit":
//                        fruSal.add((Fruit)proMar.get(i));
//                        for(int j=0;j<fruMar.size();j++){
//                            if(fruMar.get(j).getName().equals(name)){
//                                fruMar.remove(j);
//                            }
//                        }
//                        bb = "售出fruit商品成功！";
//                        //productManage();
//                        break;
//                    case "greens":
//                        greSal.add((Greens) proMar.get(i));
//                        for(int j=0;j<greMar.size();j++){
//                            if(greMar.get(j).getName().equals(name)){
//                                greMar.remove(j);
//                            }
//                        }
//                        bb = "售出greens商品成功！";
//                        //productManage();
//                        break;
//                }
//                for(int k=0;k<proMar.size();k++){
//                    if(proMar.get(k).getName().equals(name)){
//                        proMar.remove(k);
//                    }
//                }
//            }
//            else if(i==proMar.size()-1){
//                bb="你要售出的商品不在册，无法售出，请重新输入：";
//            }
//        }
//        return bb;
//    }
}
