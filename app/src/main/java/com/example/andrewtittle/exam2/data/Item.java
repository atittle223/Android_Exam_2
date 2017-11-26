package com.example.andrewtittle.exam2.data;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by andrewtittle on 11/25/17.
 */

public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}
