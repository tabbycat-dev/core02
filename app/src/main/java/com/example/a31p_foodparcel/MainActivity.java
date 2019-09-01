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
        setText(R.id.tvDate02);
        setText(R.id.tvDate03);
        setText(R.id.tvDate04);
        setText(R.id.tvDate01);


    }
    private void setText(int res){
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
    private void showDetails(int image, int name, int date) {
        Bundle dataBundle = new Bundle();
        dataBundle.putInt("IMAGE",image);
        dataBundle.putInt("NAME",name);
        dataBundle.putInt("DATE",date);
        Intent i = new Intent(this, ImageDisplayActivity.class);

        i.putExtras(dataBundle);
        startActivity(i);
    }
}
