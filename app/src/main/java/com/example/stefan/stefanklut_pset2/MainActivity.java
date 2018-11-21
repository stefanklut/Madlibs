package com.example.stefan.stefanklut_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startMadlib(View view) {
        // Open new activity where the story can be selected
        Intent intent = new Intent(MainActivity.this, StorySelectorActivity.class);
        startActivity(intent);
    }
}
