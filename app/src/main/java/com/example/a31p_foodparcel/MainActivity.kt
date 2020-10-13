package com.example.a31p_foodparcel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a31p_foodparcel.model.Food
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var food04: Food
    private lateinit var food03: Food
    private lateinit var food02: Food
    private lateinit var food01: Food

    private lateinit var tvName01: TextView
    private lateinit var tvName02: TextView
    private lateinit var tvName03: TextView
    private lateinit var tvName04: TextView

    private lateinit var tvRating01: TextView
    private lateinit var tvRating02: TextView
    private lateinit var tvRating03: TextView
    private lateinit var tvRating04: TextView


    private lateinit var imageView01: ImageView;
    lateinit var imageView02: ImageView;
    lateinit var imageView03: ImageView;
    lateinit var imageView04: ImageView;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseUI()
    }

    private fun initialiseUI() {
        tvName01 = findViewById(R.id.tvName01)
        tvRating01 = findViewById(R.id.tvRating01)
        tvName02 = findViewById(R.id.tvName02)
        tvRating02 = findViewById(R.id.tvRating02)
        tvName03 = findViewById(R.id.tvName03)
        tvRating03 = findViewById(R.id.tvRating03)
        tvName04 = findViewById(R.id.tvName04)
        tvRating04 = findViewById(R.id.tvRating04)
        val date = "20/09/2020"
        food01 = Food(R.drawable.bo_kho, tvName01.text.toString(), date, "Asian", 5f)
        setRating(tvRating01, food01.rating)
        food02 = Food(R.drawable.broken_rice, tvName02.text.toString(), date, "Asian", 5f)
        setRating(tvRating02, food02.rating)

        food03 = Food(R.drawable.sashimi, tvName03.text.toString(), date, "Asian", 5f)
        setRating(tvRating03, food03.rating)

        food04 = Food(R.drawable.thai_mango, tvName04.text.toString(), date, "Asian", 5f)
        setRating(tvRating04, food04.rating)

        imageView01 = findViewById(R.id.image_01)
        imageView02 = findViewById(R.id.image_02)
        imageView03 = findViewById(R.id.image_03)
        imageView04 = findViewById(R.id.image_04)

        setupImageView()
        setupListeners() //set up listener for imageview
    }

    fun setupImageView(){
        setImageViewSrc(imageView01, R.drawable.bo_kho)
        setImageViewSrc(imageView02, R.drawable.broken_rice)
        setImageViewSrc(imageView03, R.drawable.sashimi)
        setImageViewSrc(imageView04, R.drawable.thai_mango)
    }

    private fun setImageViewSrc(iamgeView : ImageView , imageSrc : Int? ){
        imageSrc?.let {
            val imageRes = resources.getDrawable(it)
            iamgeView.setImageDrawable(imageRes)
        }
    }

    fun setupListeners(){
        val clickListener = View.OnClickListener {view ->
            when (view.getId()) {
                R.id.image_01 ->  setUpIntent(food01, "FOOD_01", 1)
                R.id.image_02 ->  setUpIntent(food02, "FOOD_02", 2)
                R.id.image_03 ->  setUpIntent(food03, "FOOD_03", 3)
                R.id.image_04 ->  setUpIntent(food04, "FOOD_04", 4)
            }
        }
        imageView01.setOnClickListener(clickListener)
        imageView02.setOnClickListener(clickListener)
        imageView03.setOnClickListener(clickListener)
        imageView04.setOnClickListener(clickListener)
    }
//
//    //onClick method when click on food image
//    fun clickFood01(v: View?) {
//        //#1 set up intent to get a result
//        setUpIntent(food01, "FOOD_01", 1)
//    }
//
//    fun clickFood02(v: View?) {
//        setUpIntent(food02, "FOOD_02", 2)
//    }
//
//    fun clickFood03(v: View?) {
//        setUpIntent(food03, "FOOD_03", 3)
//    }
//
//    fun clickFood04(v: View?) {
//        setUpIntent(food04, "FOOD_04", 4)
//    }
    /* Prepare food Parcel and put it into Intent to send to Second Activity
     * @param: requestCode of parcel, parcel Name, food object to send
     */

    private fun setRating(textView: TextView, rating: Float) {
        textView.setText(rating.toDouble().toString())
    }
    private fun setUpIntent(foodObject: Food?, parcelName: String?, requestedCode: Int) {
        //#1a set up intent to get a result, receiver: second activity
        val i = Intent(applicationContext, ImageDisplayActivity::class.java)
        //#1b set up food parcel with name, date and image
        val foodList = ArrayList<Food?>()
        foodList.add(foodObject)
        i.putParcelableArrayListExtra(parcelName, foodList)
        startActivityForResult(i, requestedCode)
    }

    /* Get result back and process/update UI
     * @param: requestCode of parcel, resultCode, intent
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        // #4 get result back and process/update UI
        // #4a check for result code
        if (requestCode == 1 || requestCode == 2 || requestCode == 3 || requestCode == 4) {
            // #4b if result ok then get data and update UI
            if (resultCode == Activity.RESULT_OK) {
                //  #4c add elses/Logs for result not okay
                if (intent == null) {
                    Log.i("INTENT", "Intent is empty")
                } else {
                    Log.i("INTENT", "Intent NOT empty")
                    val foods: ArrayList<Food?> = intent.getParcelableArrayListExtra("FOOD_DATA")
                    Log.i("INTENT", "Main: results array list: ${foods.size}")
                    foods[0]?.let {
                        Log.i("INTENT", "Main: results Food object: ${it}")
                        //UPDATE UI AND OBJECT
                        updateFood(requestCode, it)
                    }
                }
            } else Log.i("INTENT", "Result not okay")
        } else {
            //Log.i("INTENT","Code does not match");
        }
    }

    private fun updateFoodObject(food: Food, updatedFood: Food) {
        food.apply {
            name = updatedFood.name
            date = updatedFood.date
            cusine = updatedFood.cusine
            rating = updatedFood.rating
        }
    }

    /* updateFood is to update UI and Food object (food01, food02,..) in this class.
    * @param: foodNumber (1 is to update food01 object and UI)
    * @param: food object
    */
    private fun updateFood(foodNumber: Int, food: Food?) {
        food?.let {
            if (foodNumber == 1) {
                updateFoodObject(food01, it)

                tvName01.text = it.name
                setRating(tvRating01, it.rating)
            }
            if (foodNumber == 2) {
                updateFoodObject(food02, it)

                tvName02.text = it.name
                setRating(tvRating02, it.rating)
            }
            if (foodNumber == 3) {
                updateFoodObject(food03, it)

                tvName03.text = it.name
                setRating(tvRating03, it.rating)
            }
            if (foodNumber == 4) {
                updateFoodObject(food03, it)

                tvName04.text = it.name
                setRating(tvRating04, it.rating)
            }
        }
    }
}