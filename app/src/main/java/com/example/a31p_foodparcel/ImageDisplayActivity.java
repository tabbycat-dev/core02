package com.example.a31p_foodparcel;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageDisplayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_image);
        initialiseUI();
    }
    private void initialiseUI() {
        Bundle extras = getIntent().getExtras();

        Drawable image = getResources().getDrawable(extras.getInt("IMAGE"));
        String name = getResources().getString(extras.getInt("NAME"));
        String date = getResources().getString(extras.getInt("DATE"));

        ImageView ivImage = findViewById(R.id.imageView);
        ivImage.setImageDrawable(image);

    }
}
