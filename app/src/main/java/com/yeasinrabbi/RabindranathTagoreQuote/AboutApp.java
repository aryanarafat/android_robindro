package com.yeasinrabbi.RabindranathTagoreQuote;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class AboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);


        ImageView imageView = findViewById(R.id.sample_back_button);

        imageView.setOnClickListener(v -> finish());

        TextView about_version = findViewById(R.id.about_version);

        about_version.setText(getString(R.string.version));



    }
}