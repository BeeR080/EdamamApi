package com.example.retrofittraining.presentation.view.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittraining.R
import com.example.retrofittraining.data.Hint
import com.example.retrofittraining.databinding.FragmentFoodListBinding
import com.example.retrofittraining.viewmodel.FoodViewModel
import com.example.retrofittraining.presentation.view.Adapter.FoodAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class FoodListFragment : Fragment(){
    private val foodViewModel: FoodViewModel by viewModel<FoodViewModel>()

    var binding: FragmentFoodListBinding? = null

    private val adapter = FoodAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFoodListBinding.inflate(inflater, container, false)
        this.binding = binding
        initAdapter()
        setupMenu()


        return binding.root

    }


    //Adapter for food
    private fun initAdapter() = with(binding!!) {
        val recyclerView = foodlistRecyclerFood
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getFoodReciepe(foodName:String?) {

        foodViewModel.getFoodReciep(foodName!!)

        foodViewModel.foodList.observe(viewLifecycleOwner){foodReciepes->

            adapter.setData(foodReciepes)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupMenu() {

        (requireActivity() as MenuHost).addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.top_app_bar_menu, menu)
               val search = menu.findItem(R.id.search)
                val searchView = search.actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(foodName: String?): Boolean {
                        getFoodReciepe(foodName)
                        return true
                    }

                    override fun onQueryTextChange(foodName: String?): Boolean {
                        return false
                    }

                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        })
    }



}





