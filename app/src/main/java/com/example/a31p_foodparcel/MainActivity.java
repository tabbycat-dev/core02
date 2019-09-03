package com.example.a31p_foodparcel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Food food01, food02,food03, food04 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
        onRestoreInstanceState(savedInstanceState);
    }
    private void initialiseUI(){
        setDate(R.id.tvDate02);//Set date
        setDate(R.id.tvDate03);
        setDate(R.id.tvDate04);
        setDate(R.id.tvDate01);

        TextView tvName01 = findViewById(R.id.tvName01);
        TextView tvDate01 = findViewById(R.id.tvDate01);
        TextView tvName02 = findViewById(R.id.tvName02);
        TextView tvDate02 = findViewById(R.id.tvDate02);

        TextView tvName03 = findViewById(R.id.tvName03);
        TextView tvDate03 = findViewById(R.id.tvDate03);
        TextView tvName04 = findViewById(R.id.tvName04);
        TextView tvDate04 = findViewById(R.id.tvDate04);
        String exampleAuthor = getResources().getString(R.string.author);//example@gmail.com
        food01 = new Food(R.drawable.bo_kho,tvName01.getText().toString(), tvDate01.getText().toString(), getResources().getString(R.string.author) );
        food02 = new Food(R.drawable.broken_rice,tvName02.getText().toString(), tvDate02.getText().toString(), exampleAuthor);
        food03 = new Food(R.drawable.sashimi,tvName03.getText().toString(), tvDate03.getText().toString(), exampleAuthor);
        food04 = new Food(R.drawable.thai_mango,tvName04.getText().toString(), tvDate04.getText().toString(), exampleAuthor);
    }

    //java built-in date to generate date
    private void setDate(int res){
        TextView date = findViewById(res);
        String dateTime = java.time.LocalDate.now().toString();
        date.setText(dateTime);
    }
    //onClick method when lick on food image
    public void clickFood01(View v){
        //TODO #1 set up intent to get a result
        setUpIntent(food01, "FOOD_01",1 );
    }
    public void clickFood02(View v){
        setUpIntent(food02, "FOOD_02",2 );
    }
    public void clickFood03(View v){
        setUpIntent(food03, "FOOD_03",3 );
    }
    public void clickFood04(View v){
        setUpIntent(food04, "FOOD_04",4 );
    }

    /* Prepare food Parcel and put it into Intent to send to Second Activity
     * @param: requestCode of parcel, parcel Name, food object to send
     */
    private void setUpIntent(Food foodObject, String parcelName, int requestedCode) {
        //TODO #1a set up intent to get a result, receiver: second activity
        Intent i = new Intent (getApplicationContext(), ImageDisplayActivity.class);
        //TODO #1b set up food parcel with name, date and image
        ArrayList<Food> foodList = new ArrayList<Food>();
        foodList.add(foodObject);
        i.putParcelableArrayListExtra(parcelName,foodList);
        startActivityForResult(i, requestedCode);
    }

    /* Get result back and process/update UI
     * @param: requestCode of parcel, resultCode, intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO #4 get result back and process/update UI
        // TODO #4a check for result code
        if((requestCode ==1)||(requestCode ==2)||(requestCode ==3)||(requestCode ==4)){
            // TODO #4b if result ok then get data and update UI
            if(resultCode ==RESULT_OK){
                // TODO #4c add elses/Logs for result not okay
                if(intent ==null){
                    Log.i("INTENT","Intent is empty");
                }else{
                    ArrayList<Food> foods = intent.getParcelableArrayListExtra("FOOD_DATA");
                    Food food = foods.get(0);//get first index
                    //TODO UPDATE UI AND OBJECT
                    updateFood(requestCode, food);
                }
            }else Log.i("INTENT","Result not okay");
        }else {
            //Log.i("INTENT","Code does not match");
        }
    }
    /* updateFood is to update UI and Food object (food01, food02,..) in this class.
    * @param: foodNumber (1 is to update food01 object and UI)
    * @param: food object
    */
    private void updateFood(int foodNumber, Food food){
        String name = food.getName();
        String date = food.getDate();

        if (foodNumber==1){
            TextView tvName01 = findViewById(R.id.tvName01);
            TextView tvDate01 = findViewById(R.id.tvDate01);
            tvName01.setText(name);
            tvDate01.setText(date);
            food01.updateFood(food.getName(),food.getLocationURL(),food.getKeyword(),
                    food.getDate(),food.getShare(),food.getAuthorEmail(),food.getRating());
        }
        if (foodNumber==2) {
            TextView tvName02 = findViewById(R.id.tvName02);
            TextView tvDate02 = findViewById(R.id.tvDate02);
            tvName02.setText(name);
            tvDate02.setText(date);
            food02.updateFood(food.getName(),food.getLocationURL(),food.getKeyword(),
                    food.getDate(),food.getShare(),food.getAuthorEmail(),food.getRating());
        }
        if (foodNumber==3) {
            TextView tvName03 = findViewById(R.id.tvName03);
            TextView tvDate03 = findViewById(R.id.tvDate03);
            tvName03.setText(name);
            tvDate03.setText(date);
            food03.updateFood(food.getName(),food.getLocationURL(),food.getKeyword(),
                    food.getDate(),food.getShare(),food.getAuthorEmail(),food.getRating());
        }
        if (foodNumber==4) {
            TextView tvName04 = findViewById(R.id.tvName04);
            TextView tvDate04 = findViewById(R.id.tvDate04);
            tvName04.setText(name);
            tvDate04.setText(date);
            food04.updateFood(food.getName(),food.getLocationURL(),food.getKeyword(),
                    food.getDate(),food.getShare(),food.getAuthorEmail(),food.getRating());
        }
    }
    @NonNull
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        TextView name01 = findViewById(R.id.tvName01);
        TextView date01 = findViewById(R.id.tvDate01);
        TextView name02 = findViewById(R.id.tvName02);
        TextView date02 = findViewById(R.id.tvDate02);

        TextView name03 = findViewById(R.id.tvName03);
        TextView date03 = findViewById(R.id.tvDate03);
        TextView name04 = findViewById(R.id.tvName04);
        TextView date04 = findViewById(R.id.tvDate04);

        outState.putString("NAME01", name01.getText().toString());
        outState.putString("NAME02", name02.getText().toString());
        outState.putString("NAME03", name03.getText().toString());
        outState.putString("NAME04", name04.getText().toString());

        outState.putString("DATE01", date01.getText().toString());
        outState.putString("DATE02", date02.getText().toString());
        outState.putString("DATE03", date03.getText().toString());
        outState.putString("DATE04", date04.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if (savedInstanceState == null){ return;}
        TextView name01 = findViewById(R.id.tvName01);
        TextView date01 = findViewById(R.id.tvDate01);
        TextView name02 = findViewById(R.id.tvName02);
        TextView date02 = findViewById(R.id.tvDate02);

        TextView name03 = findViewById(R.id.tvName03);
        TextView date03 = findViewById(R.id.tvDate03);
        TextView name04 = findViewById(R.id.tvName04);
        TextView date04 = findViewById(R.id.tvDate04);
        name01.setText(savedInstanceState.getString("NAME01"));
        name02.setText(savedInstanceState.getString("NAME02"));
        name03.setText(savedInstanceState.getString("NAME03"));
        name04.setText(savedInstanceState.getString("NAME04"));
        date01.setText(savedInstanceState.getString("DATE01"));
        date02.setText(savedInstanceState.getString("DATE02"));
        date03.setText(savedInstanceState.getString("DATE03"));
        date04.setText(savedInstanceState.getString("DATE04"));
        super.onRestoreInstanceState(savedInstanceState);
    }
}

