package com.example.vesp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

public class ClickedItemActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Gallery");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_item);

        imageView = findViewById(R.id.imageViewClick);

        Intent intent = getIntent();

        if(intent.getExtras() != null){
            int selectedImage = intent.getIntExtra("image",0);
            imageView.setImageResource(selectedImage);
        }
    }
}