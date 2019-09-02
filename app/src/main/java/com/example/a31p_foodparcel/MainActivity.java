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
    private Food food01 = new Food("Bo Kho");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }
    private void initialiseUI(){
        setDate(R.id.tvDate02);//Set date
        setDate(R.id.tvDate03);
        setDate(R.id.tvDate04);
        setDate(R.id.tvDate01);

    }
    //java built-in date to generate date
    private void setDate(int res){
        TextView date = findViewById(res);
        String dateTime = java.time.LocalDate.now().toString();
        date.setText(dateTime);
    }

    public void clickFood01(View v){
        //showDetails(R.drawable.bo_kho,R.string.name_01, R.string.date);
            Log.i("INTENT","Intent is empty");
            //TODO #1 set up intent to get a result
            //Intent i = new Intent (getApplicationContext(), ImageDisplayActivity.class);
            //startActivityForResult(i, 0);
            TextView name = findViewById(R.id.tvName01);
            TextView date = findViewById(R.id.tvDate01);
            showDetails(R.drawable.bo_kho,name.getText().toString(), date.getText().toString(),0);

        }


    public void clickFood02(View v){
        //showDetails(R.drawable.broken_rice,R.string.name_02, R.string.date);
    }
    public void clickFood03(View v){
        //showDetails(R.drawable.sashimi,R.string.name_02, R.string.date);
    }
    public void clickFood04(View v){
        //showDetails(R.drawable.thai_rice,R.string.name_04, R.string.date);
    }
    //passing date to Image Display Activity using Bundle, Intent
    private void showDetails(int image, String name, String date, int requestedCode) {
        Bundle dataBundle = new Bundle();
        dataBundle.putInt("IMAGE",image);
        dataBundle.putString("NAME",name);
        dataBundle.putString("DATE",date);
        //Intent i = new Intent(this, ImageDisplayActivity.class);
        //startActivity(i);
        //TODO #1 set up intent to get a result
        Intent i = new Intent (getApplicationContext(), ImageDisplayActivity.class);
        i.putExtras(dataBundle);
        startActivityForResult(i, requestedCode);
    }
    // TODO #4 get result back and process/update UI
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO #4a check for result code
        if(requestCode ==0){
            // TODO #4b if result ok then get data and update UI
            if(resultCode ==RESULT_OK){
                // TODO #4c add elses/Logs for result not okay
                if(intent ==null){
                    Log.i("INTENT","Intent is empty");
                }else{
                    ArrayList<Food> foods = intent.getParcelableArrayListExtra("FOOD_DATA");
                    food01 = foods.get(0);//get first index
                    Log.i("INTENT", food01.toString());

                    //TODO UPDATE RESULT TO LAYOUT
                    TextView tvName01 = findViewById(R.id.tvName01);
                    tvName01.setText(food01.getName());

                }

            }else Log.i("INTENT","Result not okay");
        }else Log.i("INTENT","Code does not match");

    }

}

