package com.example.retrofittraining.presentation.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofittraining.R
import com.example.retrofittraining.utils.DoubleRoudTo
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.databinding.FoodsListBinding
import com.squareup.picasso.Picasso


class FoodAdapter:RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {


    var foodList = emptyList<Hint>()


    class FoodViewHolder(binding: FoodsListBinding) :RecyclerView.ViewHolder(binding.root){
        val foodName = binding.foodName
        val foodEnerckcal = binding.foodEnerckcal
        val foodFat = binding.foodFat
        val foodProcnt = binding.foodProcnt
        val foodImage = binding.foodImage
        val foodRecipe = binding.foodRecipe
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = FoodsListBinding
            .inflate(
                LayoutInflater
                    .from(
                        parent.context),

                parent,
                false)
        return FoodViewHolder(binding)
    }



    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentitem = foodList[position]

       Picasso.get()
            .load(currentitem.food.image?.toUri())
            .placeholder(R.mipmap.ic_edamam)
            .into(holder.foodImage)

        holder.foodName.text = currentitem.food.label
        holder.foodEnerckcal.text = DoubleRoudTo(currentitem.food.nutrients.ENERC_KCAL)+"kcal"
        holder.foodFat.text = (DoubleRoudTo(currentitem.food.nutrients.FAT)+"g")
        holder.foodProcnt.text = DoubleRoudTo(currentitem.food.nutrients.PROCNT)+"g"
        if(currentitem.food.foodContentsLabel !=null) {
            holder.foodRecipe.text = "Recipe: " + currentitem.food.foodContentsLabel

        }else{
            holder.foodRecipe.text = "Recipe: No recipe for this food :("
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