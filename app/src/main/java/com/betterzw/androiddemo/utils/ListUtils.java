package com.betterzw.androiddemo.utils;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by zhengwu on 3/14/18.
 */

public class ListUtils {

    public void hashmapList(){
        HashMap<Integer, String> tmp = new HashMap<Integer, String>();
        tmp.put(1, "rock");
        tmp.put(2, "Joan");

        Iterator<Integer> iterator = tmp.keySet().iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(tmp.get(obj));
        }
    }


}
