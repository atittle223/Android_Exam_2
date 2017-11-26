package com.example.andrewtittle.exam2.service;

import com.example.andrewtittle.exam2.data.Channel;

/**
 * Created by andrewtittle on 11/25/17.
 */

public interface WeatherServiceCallback {

    void serviceSuccess(Channel channel);

    void serviceFailuer(Exception exception);
}
