package com.example.wishlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //create an empty Mutable list to store the wishlists
    // let's make this a MutableList instead such as
    // Huzzah!! a list of wishlist objects
    var aWishlist = mutableListOf<Wishlist>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /********************** RECYCLER VIEW CONNECTION ********************/
        // Lookup/get the RecyclerView in activity layout
        val wishlistRV = findViewById<RecyclerView>(R.id.wishRv)
        // Create adapter passing in the list of emails
        val adapter = WishlistAdapter(aWishlist)
        // Attach the adapter to the RecyclerView to populate items
        // basically pass your (class) adapter to the adapter property of RecyclerView
        wishlistRV.adapter = adapter
        // Set layout manager to position the items
        // LinearLayoutManger is one of the default linear managers that come with Android Studio
        wishlistRV.layoutManager = LinearLayoutManager(this)

        /***************** END RECYCLER VIEW CONNECTION ********************/

        /********************** VARIABLE DECLARATIONS ********************/
        // create variables to get EditText values
        val editName = findViewById<EditText>(R.id.nameField)
        val editUrl = findViewById<EditText>(R.id.urlField)
        val editPrice = findViewById<EditText>(R.id.priceField)

        // create submit button variable
        val submitButton = findViewById<Button>(R.id.submitBtn)
        /****************** END VARIABLE DECLARATIONS ********************/

        /************************* HELPER FUNCTIONS *************************/
        // hide keyboard on enter
        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }
        /************************ END HELPER FUNCTIONS ********************/

        /*
        create a new wishlist and add it to a list
        update the recycler view as the list grows and is fed to the holder
         */
        submitButton.setOnClickListener {
            // hide the keyboard on submit
            it.hideKeyboard()

            // grab values from Edit Text fields and store in a created wishlist object
            val secondWishlist = Wishlist(
                editName.text.toString(),
                editPrice.text.toString(),
                editUrl.text.toString())

            // clear the values in the text field on enter
            editName.text.clear()
            editPrice.text.clear()
            editUrl.text.clear()

            // add the created wishlist object to the end of the list
            aWishlist.add(secondWishlist)
            // Notify the adapter there's a new wishlist so the RecyclerView layout is updated
            adapter.notifyDataSetChanged()
        }

    }
}