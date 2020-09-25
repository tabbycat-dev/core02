package com.example.a31p_foodparcel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a31p_foodparcel.model.Food
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {
    private var food01: Food? = null
    private var food02: Food? = null
    private var food03: Food? = null
    private var food04: Food? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialiseUI()
        onRestoreInstanceState(savedInstanceState)
    }

    private fun initialiseUI() {
        setDate(R.id.tvDate02) //Set date
        setDate(R.id.tvDate03)
        setDate(R.id.tvDate04)
        setDate(R.id.tvDate01)
        val tvName01 = findViewById<TextView?>(R.id.tvName01)
        val tvDate01 = findViewById<TextView?>(R.id.tvDate01)
        val tvName02 = findViewById<TextView?>(R.id.tvName02)
        val tvDate02 = findViewById<TextView?>(R.id.tvDate02)
        val tvName03 = findViewById<TextView?>(R.id.tvName03)
        val tvDate03 = findViewById<TextView?>(R.id.tvDate03)
        val tvName04 = findViewById<TextView?>(R.id.tvName04)
        val tvDate04 = findViewById<TextView?>(R.id.tvDate04)
        val exampleAuthor = resources.getString(R.string.author) //example@gmail.com
        food01 = Food(R.drawable.bo_kho, tvName01.text.toString(), tvDate01.text.toString(), resources.getString(R.string.author))
        food02 = Food(R.drawable.broken_rice, tvName02.text.toString(), tvDate02.text.toString(), exampleAuthor)
        food03 = Food(R.drawable.sashimi, tvName03.text.toString(), tvDate03.text.toString(), exampleAuthor)
        food04 = Food(R.drawable.thai_mango, tvName04.text.toString(), tvDate04.text.toString(), exampleAuthor)
    }

    //java built-in date to generate date
    private fun setDate(res: Int) {
        val date = findViewById<TextView?>(res)
        val dateTime = LocalDate.now().toString()
        date.text = dateTime
    }

    //onClick method when lick on food image
    fun clickFood01(v: View?) {
        //TODO #1 set up intent to get a result
        setUpIntent(food01, "FOOD_01", 1)
    }

    fun clickFood02(v: View?) {
        setUpIntent(food02, "FOOD_02", 2)
    }

    fun clickFood03(v: View?) {
        setUpIntent(food03, "FOOD_03", 3)
    }

    fun clickFood04(v: View?) {
        setUpIntent(food04, "FOOD_04", 4)
    }

    /* Prepare food Parcel and put it into Intent to send to Second Activity
     * @param: requestCode of parcel, parcel Name, food object to send
     */
    private fun setUpIntent(foodObject: Food?, parcelName: String?, requestedCode: Int) {
        //TODO #1a set up intent to get a result, receiver: second activity
        val i = Intent(applicationContext, ImageDisplayActivity::class.java)
        //TODO #1b set up food parcel with name, date and image
        val foodList = ArrayList<Food?>()
        foodList.add(foodObject)
        i.putParcelableArrayListExtra(parcelName, foodList)
        startActivityForResult(i, requestedCode)
    }

    /* Get result back and process/update UI
     * @param: requestCode of parcel, resultCode, intent
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        // TODO #4 get result back and process/update UI
        // TODO #4a check for result code
        if (requestCode == 1 || requestCode == 2 || requestCode == 3 || requestCode == 4) {
            // TODO #4b if result ok then get data and update UI
            if (resultCode == Activity.RESULT_OK) {
                // TODO #4c add elses/Logs for result not okay
                if (intent == null) {
                    Log.i("INTENT", "Intent is empty")
                } else {
                    val foods: ArrayList<Food?> = intent.getParcelableArrayListExtra("FOOD_DATA")
                    val food = foods[0] //get first index
                    //TODO UPDATE UI AND OBJECT
                    updateFood(requestCode, food)
                }
            } else Log.i("INTENT", "Result not okay")
        } else {
            //Log.i("INTENT","Code does not match");
        }
    }

    /* updateFood is to update UI and Food object (food01, food02,..) in this class.
    * @param: foodNumber (1 is to update food01 object and UI)
    * @param: food object
    */
    private fun updateFood(foodNumber: Int, food: Food?) {
        val name = food.getName()
        val date = food.getDate()
        if (foodNumber == 1) {
            val tvName01 = findViewById<TextView?>(R.id.tvName01)
            val tvDate01 = findViewById<TextView?>(R.id.tvDate01)
            tvName01.text = name
            tvDate01.text = date
            food01.updateFood(food.getName(), food.getLocationURL(), food.getKeyword(),
                    food.getDate(), food.getShare(), food.getAuthorEmail(), food.getRating())
        }
        if (foodNumber == 2) {
            val tvName02 = findViewById<TextView?>(R.id.tvName02)
            val tvDate02 = findViewById<TextView?>(R.id.tvDate02)
            tvName02.text = name
            tvDate02.text = date
            food02.updateFood(food.getName(), food.getLocationURL(), food.getKeyword(),
                    food.getDate(), food.getShare(), food.getAuthorEmail(), food.getRating())
        }
        if (foodNumber == 3) {
            val tvName03 = findViewById<TextView?>(R.id.tvName03)
            val tvDate03 = findViewById<TextView?>(R.id.tvDate03)
            tvName03.text = name
            tvDate03.text = date
            food03.updateFood(food.getName(), food.getLocationURL(), food.getKeyword(),
                    food.getDate(), food.getShare(), food.getAuthorEmail(), food.getRating())
        }
        if (foodNumber == 4) {
            val tvName04 = findViewById<TextView?>(R.id.tvName04)
            val tvDate04 = findViewById<TextView?>(R.id.tvDate04)
            tvName04.text = name
            tvDate04.text = date
            food04.updateFood(food.getName(), food.getLocationURL(), food.getKeyword(),
                    food.getDate(), food.getShare(), food.getAuthorEmail(), food.getRating())
        }
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        val name01 = findViewById<TextView?>(R.id.tvName01)
        val date01 = findViewById<TextView?>(R.id.tvDate01)
        val name02 = findViewById<TextView?>(R.id.tvName02)
        val date02 = findViewById<TextView?>(R.id.tvDate02)
        val name03 = findViewById<TextView?>(R.id.tvName03)
        val date03 = findViewById<TextView?>(R.id.tvDate03)
        val name04 = findViewById<TextView?>(R.id.tvName04)
        val date04 = findViewById<TextView?>(R.id.tvDate04)
        outState.putString("NAME01", name01.text.toString())
        outState.putString("NAME02", name02.text.toString())
        outState.putString("NAME03", name03.text.toString())
        outState.putString("NAME04", name04.text.toString())
        outState.putString("DATE01", date01.text.toString())
        outState.putString("DATE02", date02.text.toString())
        outState.putString("DATE03", date03.text.toString())
        outState.putString("DATE04", date04.text.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState == null) {
            return
        }
        val name01 = findViewById<TextView?>(R.id.tvName01)
        val date01 = findViewById<TextView?>(R.id.tvDate01)
        val name02 = findViewById<TextView?>(R.id.tvName02)
        val date02 = findViewById<TextView?>(R.id.tvDate02)
        val name03 = findViewById<TextView?>(R.id.tvName03)
        val date03 = findViewById<TextView?>(R.id.tvDate03)
        val name04 = findViewById<TextView?>(R.id.tvName04)
        val date04 = findViewById<TextView?>(R.id.tvDate04)
        name01.text = savedInstanceState.getString("NAME01")
        name02.text = savedInstanceState.getString("NAME02")
        name03.text = savedInstanceState.getString("NAME03")
        name04.text = savedInstanceState.getString("NAME04")
        date01.text = savedInstanceState.getString("DATE01")
        date02.text = savedInstanceState.getString("DATE02")
        date03.text = savedInstanceState.getString("DATE03")
        date04.text = savedInstanceState.getString("DATE04")
        super.onRestoreInstanceState(savedInstanceState)
    }
}