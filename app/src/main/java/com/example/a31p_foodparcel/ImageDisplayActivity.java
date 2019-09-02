package com.example.a31p_foodparcel;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ImageDisplayActivity extends AppCompatActivity {
    private Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_image);
        initialiseUI();
        //onRestoreInstanceState(savedInstanceState);
    }
    private void initialiseUI() {
        Bundle extras = getIntent().getExtras();

        //unpack Bundle
        Drawable image = getResources().getDrawable(extras.getInt("IMAGE"));
        String name = extras.getString("NAME");
        String date = extras.getString("DATE");

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
    private void HandlingResult(){
        // TODO #3 handle returning the form details when Saved
        // TODO #3a create intent and task
        Intent i = new Intent();
        EditText name = findViewById(R.id.etName);
        Food food = createFoodObject();
        // TODO #3b create list for task to be attached to parcelable
        //need a list even for one item
        ArrayList<Food> foods = new ArrayList<Food>();
        foods.add(food);
        // TODO #3c add list to intent, set result
        i.putParcelableArrayListExtra("FOOD_DATA", foods);
        setResult(RESULT_OK, i);
        // TODO #3d return
        onBackPressed();
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();//do not forget
    }
    //TODO validate FORM
    public void onSubmit(View v){
        boolean isValidName=false;
        boolean isValidAuthor=false;
        String msg = "";
        String regEx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        EditText name = findViewById(R.id.etName);
        EditText location = findViewById(R.id.etLocation);
        EditText author = findViewById(R.id.etAuthor);

        if (name.getText().toString().isEmpty()){
            name.setError("Name is required");
        }else{
            isValidName = true;
        }
        if (author.getText().toString().isEmpty()){
            author.setError("Author's email is required");
        }else if (!author.getText().toString().matches(regEx)) {
            author.setError("Email format is required");
        }else isValidAuthor = true;

        if(isValidAuthor && isValidName){
            msg="Saved";
            HandlingResult();
        }else{
            msg ="Fail to save";
        }
        Toast.makeText(this.getApplicationContext(),
                String.format("%s", msg),
                Toast.LENGTH_SHORT).show();

    }
    private Food createFoodObject(){
        EditText name = findViewById(R.id.etName);
        EditText location = findViewById(R.id.etLocation);
        EditText keyword = findViewById(R.id.etKeyword);
        EditText date = findViewById(R.id.etDate);
        EditText author = findViewById(R.id.etAuthor);

        ToggleButton share = findViewById(R.id.toggleShare);
        RatingBar rating = findViewById(R.id.ratingBar);

        Food food =new Food(name.getText().toString(),
                location.getText().toString(),
                keyword.getText().toString(),
                date.getText().toString(),
                share.isChecked(),
                author.getText().toString(),
                rating.getRating());
        return food;

    }


    @NonNull
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        EditText name = findViewById(R.id.etName);
        EditText location = findViewById(R.id.etLocation);
        EditText keyword = findViewById(R.id.etKeyword);
        EditText date = findViewById(R.id.etDate);
        EditText author = findViewById(R.id.etAuthor);

        ToggleButton share = findViewById(R.id.toggleShare);
        RatingBar rating = findViewById(R.id.ratingBar);

        outState.putString("NAME", name.getText().toString());
        outState.putString("DATE", date.getText().toString());
        outState.putString("LOCATION",location.getText().toString());
        outState.putString("KEYWORD", keyword.getText().toString());
        outState.putString("AUTHOR", author.getText().toString());
        outState.putBoolean("SHARE",share.isChecked());
        outState.putFloat("RATING", rating.getRating());
        super.onSaveInstanceState(outState);
    }
    @NonNull
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        EditText name = findViewById(R.id.etName);
        EditText location = findViewById(R.id.etLocation);
        EditText keyword = findViewById(R.id.etKeyword);
        EditText date = findViewById(R.id.etDate);
        EditText author = findViewById(R.id.etAuthor);

        ToggleButton share = findViewById(R.id.toggleShare);
        RatingBar rating = findViewById(R.id.ratingBar);

        String nameStr = savedInstanceState.getString("NAME");
        String dateStr  = savedInstanceState.getString("DATE");
        String locationStr  = savedInstanceState.getString("LOCATION");
        String keywordStr = savedInstanceState.getString("KEYWORD");
        String authorStr = savedInstanceState.getString("AUTHOR");
        boolean shareTog = savedInstanceState.getBoolean("SHARE");
        Float ratingFl = savedInstanceState.getFloat("RATING");
        name.setText(nameStr);
        date.setText(dateStr);
        location.setText(locationStr);
        keyword.setText(keywordStr);
        author.setText(authorStr);
        share.setChecked(shareTog);
        rating.setRating(ratingFl);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
