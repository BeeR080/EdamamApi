package com.example.retrofittraining.view.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittraining.R
import com.example.retrofittraining.Utils.inputChek
import com.example.retrofittraining.databinding.FragmentFoodListBinding
import com.example.retrofittraining.model.FoodViewModel
import com.example.retrofittraining.view.Adapter.FoodAdapter
import kotlinx.android.synthetic.main.fragment_food_list.view.*
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

                binding.progressBar.visibility = View.VISIBLE
                var tvfoodedittext = binding.tvFood.text.toString()

                if (inputChek(tvfoodedittext)) {
                    var food = foodViewModel.getFoodReciep("$tvfoodedittext")
                    Log.d("FOOD","$food")
                    adapter.setData(food)
                    binding.progressBar.visibility = View.GONE

                    if (food.size == 0) {
                        binding.errorlist.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }else
                        binding.errorlist.visibility = View.GONE
                    Log.d("FOOD","$food")
                    adapter.setData(food)
                }

            }









        }

        return binding.root

}
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}

private abstract class SimpleTextWatcher: TextWatcher {
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}
