package com.example.retrofittraining.presentation.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittraining.R
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.databinding.FoodtextinputListBinding


class FoodTextInputEditTextAdapter(private val foodClickListener: FoodClickListener):RecyclerView.Adapter
<FoodTextInputEditTextAdapter.FoodTextInputViewHolder>() {

    var foodList = emptyList<Hint>()

    class FoodTextInputViewHolder(binding: FoodtextinputListBinding) :
        RecyclerView.ViewHolder(binding.root){

val textInput = binding.tvFoodtexinput

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            FoodTextInputViewHolder {
        val view = FoodtextinputListBinding
            .inflate(
            LayoutInflater
                .from(parent.context) ,
                    parent,
            false
        )

        return FoodTextInputViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodTextInputViewHolder, position: Int) {
        val currentitem = foodList[position]
         holder.textInput.text = currentitem.food.label
holder.itemView.setOnClickListener{
    foodClickListener.onFoodClickListener(currentitem.food.label)
}



    }
    override fun getItemCount(): Int {
        return foodList.size
    }


    fun setData(foods: List<Hint>){
        this.foodList = foods
        notifyDataSetChanged()

    }




}
interface FoodClickListener {
    fun onFoodClickListener(food:String)


}
