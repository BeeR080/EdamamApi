package com.example.retrofittraining.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittraining.R
import com.example.retrofittraining.Utils.DoubleRoudTo
import com.example.retrofittraining.data.Hint
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.foods_list.view.*

class FoodAdapter:RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {


    var foodList = emptyList<Hint>()


    class FoodViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.foods_list,
                parent,
                false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentitem = foodList[position]

        var getimage =  Picasso.get()
            .load(currentitem.food.image?.toUri())
            .placeholder(R.mipmap.ic_edamam)
            .into(holder.itemView.food_image)

        holder.itemView.food_name.text = currentitem.food.label.toString()
        holder.itemView.food_enerckcal.setText(DoubleRoudTo(currentitem.food.nutrients.ENERC_KCAL)+"kcal")
        holder.itemView.food_fat.setText(DoubleRoudTo(currentitem.food.nutrients.FAT)+"g")
        holder.itemView.food_procnt.setText(DoubleRoudTo(currentitem.food.nutrients.PROCNT)+"g")


    }
    override fun getItemCount(): Int {
        return foodList.size
    }

    fun setData(foods: List<Hint>){
        this.foodList = foods
        notifyDataSetChanged()
    }

}