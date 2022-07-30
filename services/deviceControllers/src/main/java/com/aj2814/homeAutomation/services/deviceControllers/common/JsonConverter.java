package com.aj2814.homeAutomation.services.deviceControllers.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;

public class JsonConverter {
    public static String convertToString(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
    public static JsonObject convertToJson(String str){
        Gson gson = new Gson();
        return gson.fromJson(str, JsonObject.class);
    }
    public static Map convertToMap(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, Map.class);
    }
}