package com.example.a31p_foodparcel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.a31p_foodparcel.model.Food
import java.util.*

class ImageDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_image)
        initialiseUI()
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }
    }

    /* Get Intent Parcel from First Activity and call updateUI method
     * @param: parcelName is name of Intent
     */
    private fun getParcelFromFirstActivity(parcelName: String?) {
        val foodList: ArrayList<Food?> = intent.getParcelableArrayListExtra(parcelName)
        val food = foodList[0]
        updateUI(food?.getImage(), food.getName(), food.getLocationURL(), food.getKeyword(),
                food.getDate(), food.getShare(), food.getAuthorEmail(), food.getRating())
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

    /* Update UI, fill in form.
     * @param: image, name,.. of a food object
     */
    private fun updateUI(image: Int, name: String?, locationURL: String?,
                         keyword: String?, date: String?, share: Boolean,
                         authorEmail: String?, rating: Float?) {
        val ivImage = findViewById<ImageView?>(R.id.imageView)
        val etName = findViewById<EditText?>(R.id.etName)
        val etLocation = findViewById<EditText?>(R.id.etLocation)
        val etKeyword = findViewById<EditText?>(R.id.etKeyword)
        val etDate = findViewById<EditText?>(R.id.etDate)
        val btnShare = findViewById<ToggleButton?>(R.id.toggleShare)
        val etAuthor = findViewById<EditText?>(R.id.etAuthor)
        val ratingBar = findViewById<RatingBar?>(R.id.ratingBar)
        val imageRes = resources.getDrawable(image)
        ivImage.setImageDrawable(imageRes)
        etName.setText(name)
        etLocation.setText(locationURL)
        etKeyword.setText(keyword)
        etDate.setText(date)
        btnShare.isChecked = share
        etAuthor.setText(authorEmail)
        ratingBar.setRating(rating)
    }

    /* Make Parcelable Intent to send back and send back the first activity
     * @param: INTENT
     * Back press
     */
    private fun HandlingResult() {
        // TODO #3 handle returning the form details when Saved
        // TODO #3a create intent and task
        val i = Intent()
        val food = createFoodObject()
        // TODO #3b create list for food to be attached to parcelable
        //need a list even for one item
        val foods = ArrayList<Food?>()
        foods.add(food)
        // TODO #3c add list to intent, set result
        i.putParcelableArrayListExtra("FOOD_DATA", foods)
        setResult(Activity.RESULT_OK, i)
        // TODO #3d return
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed() //do not forget
    }

    /* Onclick method of SAVE BUTTON to
     * Validate input: NAME and Author email are required; EMAIL must be the right format
     * IF valid, Save and call createIntent to send back
     * Back press
     */
    fun onSubmit(v: View?) {
        var isValidName = false
        var isValidAuthor = false
        var msg = ""
        val regExEmail = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
        val name = findViewById<EditText?>(R.id.etName)
        val location = findViewById<EditText?>(R.id.etLocation)
        val author = findViewById<EditText?>(R.id.etAuthor)
        //TODO validate FORM
        if (name.text.toString().isEmpty()) {
            name.error = "Name is required"
        } else {
            isValidName = true
        }
        if (author.text.toString().isEmpty()) {
            author.error = "Author's email is required."
        } else if (!author.text.toString().matches(regExEmail)) {
            author.error = "Email format is required."
        } else isValidAuthor = true
        if (isValidAuthor && isValidName) {
            msg = "Saved"
            //TODO call setup Intent and send back
            HandlingResult()
        } else {
            msg = "Fail to save"
        }
        Toast.makeText(this.applicationContext, String.format("%s", msg),
                Toast.LENGTH_SHORT).show()
    }

    //Food parcel is created
    private fun createFoodObject(): Food? {
        val name = findViewById<EditText?>(R.id.etName)
        val location = findViewById<EditText?>(R.id.etLocation)
        val keyword = findViewById<EditText?>(R.id.etKeyword)
        val date = findViewById<EditText?>(R.id.etDate)
        val author = findViewById<EditText?>(R.id.etAuthor)
        val share = findViewById<ToggleButton?>(R.id.toggleShare)
        val rating = findViewById<RatingBar?>(R.id.ratingBar)
        return Food(name.text.toString(),
                location.text.toString(),
                keyword.text.toString(),
                date.text.toString(),
                share.isChecked,
                author.text.toString(),
                rating.rating)
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        val name = findViewById<EditText?>(R.id.etName)
        val location = findViewById<EditText?>(R.id.etLocation)
        val keyword = findViewById<EditText?>(R.id.etKeyword)
        val date = findViewById<EditText?>(R.id.etDate)
        val author = findViewById<EditText?>(R.id.etAuthor)
        val share = findViewById<ToggleButton?>(R.id.toggleShare)
        val rating = findViewById<RatingBar?>(R.id.ratingBar)
        outState.putString("NAME", name.text.toString())
        outState.putString("LOCATION", location.text.toString())
        outState.putString("KEYWORD", keyword.text.toString())
        outState.putString("DATE", date.text.toString())
        outState.putBoolean("SHARE", share.isChecked)
        outState.putFloat("RATING", rating.rating)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState == null) {
            return
        }
        val name = findViewById<EditText?>(R.id.etName)
        val location = findViewById<EditText?>(R.id.etLocation)
        val keyword = findViewById<EditText?>(R.id.etKeyword)
        val date = findViewById<EditText?>(R.id.etDate)
        val author = findViewById<EditText?>(R.id.etAuthor)
        val share = findViewById<ToggleButton?>(R.id.toggleShare)
        val rating = findViewById<RatingBar?>(R.id.ratingBar)
        val nameStr = savedInstanceState.getString("NAME")
        val dateStr = savedInstanceState.getString("DATE")
        val locationStr = savedInstanceState.getString("LOCATION")
        val keywordStr = savedInstanceState.getString("KEYWORD")
        val authorStr = savedInstanceState.getString("AUTHOR")
        val shareTog = savedInstanceState.getBoolean("SHARE")
        val ratingFl = savedInstanceState.getFloat("RATING")
        name.setText(nameStr)
        date.setText(dateStr)
        location.setText(locationStr)
        keyword.setText(keywordStr)
        author.setText(authorStr)
        share.isChecked = shareTog
        rating.rating = ratingFl
        super.onRestoreInstanceState(savedInstanceState)
    }
}