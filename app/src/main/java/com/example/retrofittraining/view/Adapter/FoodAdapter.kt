package com.example.retrofittraining.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittraining.R
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

        holder.itemView.food_name.text = currentitem.food.label.toString()
        holder.itemView.food_enerckcal.setText("ENERC_KCAL: " + currentitem.food.nutrients.ENERC_KCAL)
        holder.itemView.food_chocdf.setText("CHOCDF: " + currentitem.food.nutrients.CHOCDF)
        holder.itemView.food_fat.setText("FAT: " + currentitem.food.nutrients.FAT)
        holder.itemView.food_fibtg.setText("FIBTG: " + currentitem.food.nutrients.FIBTG)
        holder.itemView.food_procnt.setText("PROCNT: " + currentitem.food.nutrients.PROCNT)

        Picasso.get()
            .load(currentitem.food.image.toUri())
            .into(holder.itemView.food_image)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun setData(foods: List<Hint>){
        this.foodList = foods
        notifyDataSetChanged()
    }

}