package com.example.andrewtittle.exam2.data;

import org.json.JSONObject;

/**
 * Created by andrewtittle on 11/25/17.
 */

public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");

    }
}
