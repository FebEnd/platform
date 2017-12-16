package com.platform.parent.mybatis.bean.msgpayload;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tqyao.
 */
public class Ext {
    private Map<String, String> map;
    private String extId;

    public String getMap() {
        String result = null;
        for (String key : map.keySet()) {
            result += key + ":" + map.get(key) + ",";
        }
        return result;
    }

    /*public static void main(String[] args) {
        String result = "a:b,a:c,a:d,";
        String[] tokens = result.split(",");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
            String key = tokens[i].substring(0,tokens[i].indexOf(":"));
            String value = tokens[i].substring(tokens[i].lastIndexOf(":")+1,tokens[i].length());
            System.out.println("key: " + key + "\tvalue: " + value);
        }
    }*/

    public void setMap(String map) {
        this.map = new HashMap<String, String>();
        String[] sets = map.split(",");
        for (int i = 0; i < sets.length; i++) {
            String key = sets[i].substring(0,sets[i].indexOf(":"));
            String value = sets[i].substring(sets[i].lastIndexOf(":")+1,sets[i].length());
            this.map.put(key,value);
        }
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }
}
