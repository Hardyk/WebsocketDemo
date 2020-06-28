package com.hkv.websocketdemo.models;


import androidx.annotation.NonNull;

import com.hkv.websocketdemo.DemoApp;

import org.json.JSONException;
import org.json.JSONObject;


/**
Classes which extend from this Stub Class will not be obfuscated by proguard
 */
public class BaseModel {


    public static <T extends BaseModel>T fromJson(String json, Class<T>typeOf) {
        return DemoApp.getGson().fromJson(json, typeOf);
    }

    @NonNull
    @Override
    public String toString() {
        return DemoApp.getGson().toJson(this);
    }

    public JSONObject toJsonObject() {
        try {
            return new JSONObject(toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
