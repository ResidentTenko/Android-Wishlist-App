package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// class takes a list of type Wishlist that holds Wishlist objects
// extends the RecyclerView.Adapter class & property
class WishlistAdapter(private val myWishList: List<Wishlist>)
    // recycler view adapter uses the ViewHolder class of the wishlist adapter
    : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    /***** Creating OnItemClickListener *****/
    // Define the listener interface
    interface OnItemClickListener {
        // can take a null view (item doesn't exist) without throwing an error
        fun onItemClick (itemView: View?, position: Int)
    }

    // Define listener member variable
    // listener is of the type interface defined above
    private lateinit var listener: OnItemClickListener

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        // takes a listener object when called and sets this.listener to it
        this.listener = listener
    }

    // Provide a direct reference to each of the views within a data model
    // Used to cache the views within the item layout for fast access
    // extends the RecyclerView.ViewHolder class
    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        // notice that this class has an argument variable called itemView which is of type view
        // a view can be anything TextView, ImageView, Button, ImageButton, etc
        // create member variables for each view that will be used
        // all member variables are of type TextView since our row model uses only TextViews
        // a class may use objects/data of type int, strings, etc.
        // This class makes use of TextView variables
        val nameTextView:TextView
        val priceTextView:TextView
        val urlTextView: TextView

        // The class constructor called 'init' takes the member variables and connects
        // them to the textViews of our wishlist_item_row models
        // it does so by using the view variable 'itemView' it took in through the argument
        init {
            nameTextView = itemView.findViewById(R.id.nameTv)
            priceTextView = itemView.findViewById(R.id.priceTv)
            urlTextView = itemView.findViewById(R.id.urlTv)

            // Setup the click listener
            itemView.setOnClickListener{
                // Triggers click upwards to the adapter on click
                val position = adapterPosition
                // if the position isn't empty or absent
                if(position != RecyclerView.NO_POSITION)
                {
                    // call on item click and pass the item and position to on item click
                    listener.onItemClick(itemView,position)
                }
            }
        }
    }

    // Specify the look of the ViewHolder as it comes into the screen; It is the look of each row
    // notice that the look of the ViewHolder is based on the look created in the row xml
    // so all of that design we did is what the user will see
    // parent is of type ViewGroup and viewType is of type int
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a variable and set it to the context property of ViewGroup objects
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // inflate the custom layout
        // connect it to the wishlist_item_row xml
        val contactView = inflater.inflate(R.layout.wishlist_item_row, parent, false)
        return ViewHolder(contactView)
    }

    // On bind view holder takes the views that have been recycled off screen
    // and uses them to show the new models currently scrolling into the user's screen
    // basically we are assigning values to each of the views as they come back into screen
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // get the position of the current data model
        val wishlist = myWishList.get(position)
        // set the holder text values to match the values of the data model from the class
        holder.nameTextView.text = wishlist.itemName
        holder.priceTextView.text = wishlist.itemPrice
        holder.urlTextView.text = wishlist.itemUrl
    }

    // get the current number of items in the list
    override fun getItemCount(): Int {
        return myWishList.size
    }
}

