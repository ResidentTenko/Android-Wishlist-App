package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// class takes a list of Wishlist objects
// extends the RecyclerView.Adapter class & property
class WishlistAdapter (private val myWishList: List<Wishlist>)
    : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    // itemView is a variable of type View
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        // create member variables for each view that will be used
        val nameTextView:TextView
        val priceTextView:TextView
        val urlTextView: TextView

        // We also create a constructor that accepts the entire item in the row
        // and does the view lookups to find each sub-view
        // connect the variables above to the textViews
        init {
            nameTextView = itemView.findViewById(R.id.nameTv)
            priceTextView = itemView.findViewById(R.id.priceTv)
            urlTextView = itemView.findViewById(R.id.urlTv)
        }
    }

    // create the view holder and specify the layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // inflate the custom layout
        // connect it to the wishlist_item xml
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(contactView)
    }

    // Populate the data into the wishlist_item xml model through the holder
    // takes two values when called - ViewHolder and Object position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // get the data model based on position
        val wishlist = myWishList.get(position)
        // set the holder text values to match the values of the
        holder.nameTextView.text = wishlist.itemName
        holder.priceTextView.text = wishlist.itemPrice
        holder.urlTextView.text = wishlist.itemUrl
    }

    override fun getItemCount(): Int {
        return myWishList.size
    }


}
