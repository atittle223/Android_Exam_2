package com.example.andrewtittle.exam2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrewtittle.exam2.data.Channel;
import com.example.andrewtittle.exam2.data.Item;
import com.example.andrewtittle.exam2.service.WeatherServiceCallback;
import com.example.andrewtittle.exam2.service.YahooWeatherService;

/**
 * Created by andrewtittle on 11/25/17.
 */

public class SecondScreen extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImage2;
    private TextView temperatureTextView2;
    private TextView conditionTextView2;
    private TextView locationTextView2;

    private YahooWeatherService service;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen_layout);

        Button btNavFirstScreen = (Button)findViewById(R.id.btnGoToFirstScreen);

        btNavFirstScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        weatherIconImage2 = (ImageView)findViewById(R.id.weatherIconImageView2);
        temperatureTextView2 = (TextView)findViewById(R.id.temperatureTextView2);
        conditionTextView2 = (TextView)findViewById(R.id.conditionTextView2);
        locationTextView2 = (TextView)findViewById(R.id.locationTextView2);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Miami, FL");


    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
        weatherIconImage2.setImageDrawable(weatherIconDrawable);

        temperatureTextView2.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
        conditionTextView2.setText(item.getCondition().getDescription());
        locationTextView2.setText(service.getLocation());

    }

    @Override
    public void serviceFailuer(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

    }
}
