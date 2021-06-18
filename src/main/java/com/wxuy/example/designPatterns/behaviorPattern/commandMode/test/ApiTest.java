package com.wxuy.example.designPatterns.behaviorPattern.commandMode.test;

import com.wxuy.example.designPatterns.behaviorPattern.commandMode.XiaoEr;
import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cook.impl.GuangDongCook;
import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cook.impl.JiangSuCook;
import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cook.impl.ShanDongCook;
import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cook.impl.SiChuanCook;
import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cuisine.ICuisine;
import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cuisine.impl.GuangDoneCuisine;
import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cuisine.impl.JiangSuCuisine;
import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cuisine.impl.ShanDongCuisine;
import com.wxuy.example.designPatterns.behaviorPattern.commandMode.cuisine.impl.SiChuanCuisine;

import java.util.HashMap;
import java.util.Map;

public class ApiTest {
    public static void main(String[] args) {

        // 菜系 + 厨师；广东（粤菜）、江苏（苏菜）、山东（鲁菜）、四川（川菜）
        ICuisine guangDoneCuisine = new GuangDoneCuisine(new GuangDongCook());
        JiangSuCuisine jiangSuCuisine = new JiangSuCuisine(new JiangSuCook());
        ShanDongCuisine shanDongCuisine = new ShanDongCuisine(new ShanDongCook());
        SiChuanCuisine siChuanCuisine = new SiChuanCuisine(new SiChuanCook());

        // 点单
        XiaoEr xiaoEr = new XiaoEr();
        xiaoEr.order(guangDoneCuisine);
        xiaoEr.order(jiangSuCuisine);
        xiaoEr.order(shanDongCuisine);
        xiaoEr.order(siChuanCuisine);

        // 下单
        xiaoEr.placeOrder();

    }

/*    @Test
    public void test_(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("Mary","1243ew");
        map.put("Anna","fds32");
        map.put("Bom","dfre23");
        map.put("Jim","bbnyt");
        map.put("Kary","ppli");
        System.out.println(map.keySet());

        for (String k:map.keySet()){
            System.out.println(k.hashCode());
        }

        Map<String,String> map2 = new HashMap<String, String>();
        map2.put("A","1243ew");
        map2.put("B","fds32");
        map2.put("C","dfre23");
        map2.put("D","bbnyt");
        map2.put("E","ppli");



        System.out.println(map2.keySet());
        for (String k:map2.keySet()){
            System.out.println(k.hashCode());
        }
    }*/

}
