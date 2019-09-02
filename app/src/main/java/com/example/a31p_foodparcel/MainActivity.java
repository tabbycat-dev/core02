package com.example.a31p_foodparcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
        showDetails(R.drawable.bo_kho,R.string.name_01, R.string.date);
    }
    public void clickFood02(View v){
        showDetails(R.drawable.broken_rice,R.string.name_02, R.string.date);
    }
    public void clickFood03(View v){
        showDetails(R.drawable.sashimi,R.string.name_02, R.string.date);
    }
    public void clickFood04(View v){
        showDetails(R.drawable.thai_rice,R.string.name_04, R.string.date);
    }
    //passing date to Image Display Activity using Bundle
    private void showDetails(int image, int name, int date) {
        Bundle dataBundle = new Bundle();
        dataBundle.putInt("IMAGE",image);
        dataBundle.putInt("NAME",name);
        dataBundle.putInt("DATE",date);
        Intent i = new Intent(this, ImageDisplayActivity.class);

        i.putExtras(dataBundle);
        startActivity(i);
    }
    //TODO #1 set up intent to get a result
    public void buttonHandler(View v) {

    }
    // TODO #4 get result back and process/update UI
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO #4a check for result code
        // TODO #4b if result ok then get data and update UI
        // TODO #4c add elses/Logs for result not okay

    }
}
