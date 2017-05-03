package com.coffeeshop.validator;

import java.util.HashMap;

/**
 * Created by Amirhossein on 5/3/2017.
 */
public class ValidatorMessage {

    private static ValidatorMessage validatorMessage;
    private HashMap<Integer,String> hashMap;

    private ValidatorMessage(){
        hashMap=new HashMap<Integer, String>();
        loadMessages();
    }

    public static ValidatorMessage getInstance(){
        if(validatorMessage==null){
            validatorMessage=new ValidatorMessage();
        }
        return validatorMessage;
    }

    public void loadMessages(){
        hashMap.put(1,"Username Or Password is Wrong!");
    }

    public HashMap<Integer, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<Integer, String> hashMap) {
        this.hashMap = hashMap;
    }
}
