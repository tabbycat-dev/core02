package com.example.a31p_foodparcel;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ImageDisplayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_image);
        initialiseUI();
    }
    private void initialiseUI() {
        Bundle extras = getIntent().getExtras();

        //unpack Bundle
        Drawable image = getResources().getDrawable(extras.getInt("IMAGE"));
        String name = getResources().getString(extras.getInt("NAME"));
        String date = getResources().getString(extras.getInt("DATE"));

        //display image
        ImageView ivImage = findViewById(R.id.imageView);
        ivImage.setImageDrawable(image);

        //display name
        EditText etName = findViewById(R.id.etName);
        etName.setText(name);

        //display Date
        EditText etDate = findViewById(R.id.etDate);
        etDate.setText(date);
    }
    @Override
    public void onBackPressed() {
        // TODO #3 handle returning the form details when back pressed
        // TODO #3a create intent and task
        Intent i = new Intent();
        EditText name = findViewById(R.id.etName);
        Food food = new Food(name.getText().toString());
        // TODO #3b create list for task to be attached to parcelable
        //need a list even for one item
        ArrayList<Food> foods = new ArrayList<Food>();
        foods.add(food);
        // TODO #3c add list to intent, set result
        i.putParcelableArrayListExtra("FOOD_DATA", foods);
        setResult(RESULT_OK, i);
        // TODO #3d return
        super.onBackPressed();//do not forget
    }
}
