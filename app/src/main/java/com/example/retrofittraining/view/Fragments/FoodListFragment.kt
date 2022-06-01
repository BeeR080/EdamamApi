package com.example.retrofittraining.view.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittraining.databinding.FragmentFoodListBinding
import com.example.retrofittraining.model.FoodViewModel
import com.example.retrofittraining.view.Adapter.FoodAdapter
import kotlinx.coroutines.launch


class FoodListFragment : Fragment() {
lateinit var foodViewModel: FoodViewModel
    var binding: FragmentFoodListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodListBinding.inflate(inflater, container, false)
        this.binding = binding

        //Adapter
    val adapter = FoodAdapter()
    val recyclerView = binding.recyclerFood
    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //ViewModel
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)


        binding.find.setOnClickListener {
                lifecycleScope.launch {
                    var food = foodViewModel.getFoodReciep("${binding.tvFood.text}")
                    Log.d("FOOD","$food")
                    adapter.setData(food)
                    if (food.size == 0) {
                        binding.errorlist.visibility = View.VISIBLE
                    }else
                        binding.errorlist.visibility = View.GONE

                }



        }

        return binding.root

}
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}