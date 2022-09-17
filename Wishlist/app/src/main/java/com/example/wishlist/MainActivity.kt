package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //create a list to store the list of wishlists
    // let's make this a MutableList instead such as
    // Huzzah!! a list of wishlist objects
    var aWishlist = mutableListOf<Wishlist>()
    //var secondWishlist = Wishlist(" ", " ", " ")

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

        submitButton.setOnClickListener {
            // grab values from Edit Text fields and store in a created wishlist object

            var secondWishlist = Wishlist(
                editName.text.toString(),
                editPrice.text.toString(),
                editUrl.text.toString())
            /*secondWishlist.itemName = editName.text.toString()
            secondWishlist.itemPrice = editPrice.text.toString()
            secondWishlist.itemUrl = editUrl.text.toString()
            */

            editName.text.clear()
            editPrice.text.clear()
            editUrl.text.clear()

            // add the created wishlist object to the end of the list
            aWishlist.add(secondWishlist)
            // Notify the adapter there's a new wishlist so the RecyclerView layout is updated
            adapter.notifyDataSetChanged()

            Log.d("Hello", "The wish list size: ${aWishlist.size}")
        }
    }
}