package com.example.a31p_foodparcel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.a31p_foodparcel.model.Food
import java.util.*

class ImageDisplayActivity : AppCompatActivity() {
    private lateinit var food: Food
    private lateinit var foodList: ArrayList<Food?>
    private lateinit var name: String
    private lateinit var date: String
    private lateinit var cuisine: String
    private var rating: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_image)
        initialiseUI()
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }
    }

    private fun initialiseUI() {
        if (intent.hasExtra("FOOD_01")) {
            getParcelFromFirstActivity("FOOD_01")
        } else if (intent.hasExtra("FOOD_02")) {
            getParcelFromFirstActivity("FOOD_02")
        } else if (intent.hasExtra("FOOD_03")) {
            getParcelFromFirstActivity("FOOD_03")
        } else if (intent.hasExtra("FOOD_04")) {
            getParcelFromFirstActivity("FOOD_04")
        }
    }

    /* Get Intent Parcel from First Activity and call updateUI method
     * @param: parcelName is name of Intent
     */
    private fun getParcelFromFirstActivity(parcelName: String?) {
        foodList = intent.getParcelableArrayListExtra(parcelName)
        foodList[0]?.let {
            food = it
            Log.i("INTENT", "Details: results Food object: ${it}")
            updateUI()
        }
    }

    private fun updateUI() {
        val image: Int? = food.image
        val ivImage = findViewById<ImageView?>(R.id.imageView)
        val etName = findViewById<EditText?>(R.id.etName)
        val etDate = findViewById<EditText?>(R.id.etDate)
        val etCusine = findViewById<EditText?>(R.id.etCuisine)
        val ratingBar = findViewById<RatingBar?>(R.id.ratingBar)
        image?.let {
            val imageRes = resources.getDrawable(it)
            ivImage.setImageDrawable(imageRes)
        }
        food.name?.let {
            etName.setText(it)
        }
        food.date?.let {
            etDate.setText(it)
        }
        food.cusine?.let {
            etCusine.setText(it)
        }
        food.rating?.let {
            ratingBar.rating = it
        }
    }

    override fun onBackPressed() {
        super.onBackPressed() //do not forget
    }

    /* Onclick method of SAVE BUTTON to
     * Validate input: NAME/ Cusine/ Date are required;
     * IF valid, Save and call createIntent to send back
     * Back press
     */
    fun onSubmit(v: View?) {
        var isValidName = false
        var isValidCusine = false
        var isValidDate = false
        var msg = ""
        name = findViewById<EditText?>(R.id.etName).text.toString()
        cuisine = findViewById<EditText?>(R.id.etCuisine).text.toString()
        date = findViewById<EditText?>(R.id.etDate).text.toString()
        rating = findViewById<RatingBar?>(R.id.ratingBar).rating
        //validate FORM
        if (name.isEmpty()) {
            findViewById<EditText?>(R.id.etName).error = "Name is required"
        } else {
            isValidName = true
        }
        if (date.isEmpty()) {
            findViewById<EditText?>(R.id.etDate).error = "Date is required"
        } else {
            isValidDate = true
        }
        if (cuisine.isEmpty()) {
            findViewById<EditText?>(R.id.etCuisine).error = "Type of Cusine is required."
        } else isValidCusine = true
        if (isValidCusine && isValidName && isValidDate) {
            msg = "Saved"
            //call setup Intent and send back
            HandlingResult()
        } else {
            msg = "Fail to save"
        }
        Toast.makeText(this.applicationContext, String.format("%s", msg),
                Toast.LENGTH_SHORT).show()
    }

    /* Make Parcelable Intent to send back and send back the first activity
         * @param: INTENT
         * Back press
         */
    private fun HandlingResult() {
        // #3 handle returning the form details when Saved
        //  #3a create intent and task
        createFoodObject()
        //  #3b create list for food to be attached to parcelable
        //need a list even for one item
        val foods = ArrayList<Food?>()
        foods.add(food)
        Log.i("INTENT", "Details: HandlingResult: ${foods[0]}")
        //  #3c add list to intent, set result
        val i = Intent().apply {
            putParcelableArrayListExtra("FOOD_DATA", foods)
            setResult(Activity.RESULT_OK, this)
        }
        //  #3d return
        onBackPressed()
    }

    //Food parcel is created
    private fun createFoodObject() {
        food.name = name
        food.date = date
        food.cusine = cuisine
        food.rating = rating
        Log.i("INTENT", "Details: createFoodObject ${food}")
    }
}