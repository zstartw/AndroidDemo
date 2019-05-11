package com.betterzw.pickcolor;

import android.os.Bundle;

import com.stripe.android.Stripe;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Stripe(this, "pk_test_D4OIOA7CpFJzKVtHFABmYdBm00Ss9ewM29");


    }
}
