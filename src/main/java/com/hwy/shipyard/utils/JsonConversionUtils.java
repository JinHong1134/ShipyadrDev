package com.hwy.shipyard.utils;

import com.google.gson.Gson;

public class JsonConversionUtils {

    public static String objectToJsonStr(Object object) {
        Gson gson = new Gson();
        String jsonObject = gson.toJson(object);
        return jsonObject;
    }

    public static Object jsonStrToObject(String json) {
        Gson gson = new Gson();
        Object object = gson.fromJson(json, Object.class);
        return object;
    }
}
