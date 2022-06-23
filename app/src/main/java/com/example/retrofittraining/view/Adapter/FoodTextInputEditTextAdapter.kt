package com.example.retrofittraining.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittraining.R
import com.example.retrofittraining.data.Food
import com.example.retrofittraining.data.Hint
import kotlinx.android.synthetic.main.foodtextinput_list.view.*

class FoodTextInputEditTextAdapter:RecyclerView.Adapter
<FoodTextInputEditTextAdapter.FoodTextInputViewHolder>() {



    var foodList = emptyList<Hint>()
    var foodTestString = ""




    class FoodTextInputViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            FoodTextInputViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.foodtextinput_list,
                parent,
                false)
        return FoodTextInputViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodTextInputViewHolder, position: Int) {
        val currentitem = foodList[position]
        holder.itemView.tv_foodtexinput.text = currentitem.food.label.toString()



    }
    override fun getItemCount(): Int {
        return foodList.size
    }


    fun setData(foods: List<Hint>){
        this.foodList = foods
        /*var foodStringList = ArrayList<String>()
         foodStringList.add(foods)
        foodTestString = foodStringList.toString()
        foodList.add(foodTestString)*/
        notifyDataSetChanged()

    }


}