package com.example.retrofittraining.view.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittraining.Utils.inputChek
import com.example.retrofittraining.databinding.FragmentFoodListBinding
import com.example.retrofittraining.model.FoodViewModel
import com.example.retrofittraining.view.Adapter.FoodAdapter
import com.example.retrofittraining.view.Adapter.FoodClickListener
import com.example.retrofittraining.view.Adapter.FoodTextInputEditTextAdapter
import kotlinx.android.synthetic.main.fragment_food_list.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class FoodListFragment : Fragment(), FoodClickListener {
lateinit var foodViewModel: FoodViewModel
    var binding: FragmentFoodListBinding? = null

    val adapterTextInput = FoodTextInputEditTextAdapter(this)
    val adapter = FoodAdapter()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodListBinding.inflate(inflater, container, false)
        this.binding = binding

        //Adapter for food
    val recyclerView = binding.recyclerFood
    recyclerView.adapter = adapter
    recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Adapter for foodTextinputist
        val recyclerViewTextInput = binding.recyclerwatchlist
        recyclerViewTextInput.adapter = adapterTextInput
        recyclerViewTextInput.layoutManager = LinearLayoutManager(requireContext())

        binding.tvFood.addTextChangedListener(SimpleTextWatcher)

        //ViewModel
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)

binding.textInputLayout.setEndIconOnClickListener {
    lifecycleScope.launch {

        binding.progressBar.visibility = View.VISIBLE
        var tvfoodedittext = binding.tvFood.text.toString()
        if (inputChek(tvfoodedittext)) {
            var food = foodViewModel.getFoodReciep("$tvfoodedittext")
            Log.d("FOOD", "$food")
            adapter.setData(food)
            binding.recyclerFood.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE

            if (food.size > 0) {
                binding.recyclerFood.visibility = View.VISIBLE
                binding.listsearch.visibility = View.GONE
                binding.errorlist.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }else
                binding.errorlist.visibility = View.VISIBLE
            Log.d("FOOD","$food")
            adapter.setData(food)
        }else{
            binding.errorlist.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.recyclerFood.visibility = View.GONE

        }


    }

        }

        return binding.root

}
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

   // TextWatcher
    private val SimpleTextWatcher = object :  TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            binding!!.listsearch.visibility = View.VISIBLE
        }

        override fun afterTextChanged(s: Editable?) {
            lifecycleScope.launch {
              flow {
                try {
                    var text = s.toString()
                    delay(100)
                    if(adapterTextInput.foodList.size<1)
                        delay(300)
                        binding!!.progressBar.visibility = View.VISIBLE
                    var food = foodViewModel.getFoodReciep("$text")
                    emit(adapterTextInput.setData(food))
                    binding!!.progressBar.visibility = View.GONE
                    Log.d("textwatcher", "$text")
                    if(s!!.length < 1)
                        binding!!.listsearch.visibility = View.GONE



                    }


                catch (e:Exception){
                }


        }.collect()
            }



    }
    }

    // Recycler onItemClick
    override fun onFoodClickListener(food: String) {
        lifecycleScope.launch {
            val tvfoodedittext = binding!!.tvFood.text.toString()
            if (inputChek(tvfoodedittext)) {
                binding!!.tvFood.setText(food)
                var food = foodViewModel.getFoodReciep("$food")
                adapter.setData(food.take(1))
                binding!!.recyclerFood.visibility = View.VISIBLE
                binding!!.progressBar.visibility = View.GONE
                binding!!.listsearch.visibility = View.GONE

                if (food.size > 0) {
                    binding!!.recyclerFood.visibility = View.VISIBLE
                    binding!!.progressBar.visibility = View.GONE


                }else
                    binding!!.errorlist.visibility = View.VISIBLE
                Log.d("FOOD","$food")
                adapter.setData(food.take(1))
            }else{
                binding!!.errorlist.visibility = View.VISIBLE
                binding!!.progressBar.visibility = View.GONE
                binding!!.recyclerFood.visibility = View.GONE


            }

}
    }
}





