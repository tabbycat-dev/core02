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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_image);
        initialiseUI();
        onRestoreInstanceState(savedInstanceState);
    }
    /* Get Intent Parcel from First Activity and call updateUI method
     * @param: parcelName is name of Intent
     */
    private void getParcelFromFirstActivity(String parcelName){
        ArrayList<Food> foodList = getIntent().getParcelableArrayListExtra(parcelName);
        Food food = foodList.get(0);
        updateUI(food.getImage(),food.getName(),food.getLocationURL(),food.getKeyword(),
                food.getDate(),food.getShare(),food.getAuthorEmail(),food.getRating());
    }

    private void initialiseUI() {
        if (getIntent().hasExtra("FOOD_01")) {
            getParcelFromFirstActivity("FOOD_01");
        }
        else if (getIntent().hasExtra("FOOD_02")) {
            getParcelFromFirstActivity("FOOD_02");
        }
        else if (getIntent().hasExtra("FOOD_03")) {
            getParcelFromFirstActivity("FOOD_03");
        }
        else if (getIntent().hasExtra("FOOD_04")) {
            getParcelFromFirstActivity("FOOD_04");
        }
    }
    /* Update UI, fill in form.
     * @param: image, name,.. of a food object
     */
    private void updateUI(int image, String name, String locationURL,
                                     String keyword, String date, boolean share,
                                     String authorEmail, Float rating ){
        ImageView ivImage = findViewById(R.id.imageView);
        EditText etName = findViewById(R.id.etName);
        EditText etLocation = findViewById(R.id.etLocation);
        EditText etKeyword = findViewById(R.id.etKeyword);
        EditText etDate = findViewById(R.id.etDate);
        ToggleButton btnShare = findViewById(R.id.toggleShare);
        EditText etAuthor = findViewById(R.id.etAuthor);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        Drawable imageRes = getResources().getDrawable(image);
        ivImage.setImageDrawable(imageRes);
        etName.setText(name);
        etLocation.setText(locationURL);
        etKeyword.setText(keyword);
        etDate.setText(date);
        btnShare.setChecked(share);
        etAuthor.setText(authorEmail);
        ratingBar.setRating(rating);
    }

    /* Make Parcelable Intent to send back and send back the first activity
     * @param: INTENT
     * Back press
     */
    private void HandlingResult(){
        // TODO #3 handle returning the form details when Saved
        // TODO #3a create intent and task
        Intent i = new Intent();
        Food food = createFoodObject();
        // TODO #3b create list for food to be attached to parcelable
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

    /* Onclick method of SAVE BUTTON to
     * Validate input: NAME and Author email are required; EMAIL must be the right format
     * IF valid, Save and call createIntent to send back
     * Back press
     */
    public void onSubmit(View v){
        boolean isValidName=false;
        boolean isValidAuthor=false;
        String msg = "";
        String regExEmail = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        EditText name = findViewById(R.id.etName);
        EditText location = findViewById(R.id.etLocation);
        EditText author = findViewById(R.id.etAuthor);
        //TODO validate FORM
        if (name.getText().toString().isEmpty()){
            name.setError("Name is required");
        }else{
            isValidName = true;
        }
        if (author.getText().toString().isEmpty()){
            author.setError("Author's email is required.");
        }else if (!author.getText().toString().matches(regExEmail)) {
            author.setError("Email format is required.");
        }else isValidAuthor = true;

        if(isValidAuthor && isValidName){
            msg="Saved";
            //TODO call setup Intent and send back
            HandlingResult();
        }else{
            msg ="Fail to save";
        }
        Toast.makeText(this.getApplicationContext(),
                String.format("%s", msg),
                Toast.LENGTH_SHORT).show();

    }
    //Food parcel is created
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
        outState.putString("LOCATION", location.getText().toString());
        outState.putString("KEYWORD", keyword.getText().toString());
        outState.putString("DATE", date.getText().toString());

        outState.putBoolean("SHARE",share.isChecked());
        outState.putFloat("RATING",rating.getRating());

        super.onSaveInstanceState(outState);

    }
    @NonNull
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState == null){ return;}
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
