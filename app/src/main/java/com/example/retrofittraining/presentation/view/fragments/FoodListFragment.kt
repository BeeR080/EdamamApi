package com.example.retrofittraining.presentation.view.fragments

import android.annotation.SuppressLint
import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittraining.R
import com.example.retrofittraining.databinding.FragmentFoodListBinding
import com.example.retrofittraining.viewmodel.FoodViewModel
import com.example.retrofittraining.presentation.view.adapter.FoodAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class FoodListFragment : Fragment(){

    private val foodViewModel: FoodViewModel by viewModel()
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

    private fun getFoodReciepe(foodName:String?) = with(binding!!) {
        foodViewModel.getFoodReciep(foodName!!)

        foodViewModel.isLoading.observe(viewLifecycleOwner){isLoading->
        when(isLoading){
            true->this.progressBar.visibility = View.VISIBLE
            false->this.progressBar.visibility = View.GONE
    }

}

        foodViewModel.errorMsg.observe(viewLifecycleOwner){errorMsg->
            if(!errorMsg.isNullOrEmpty()){
                Toast.makeText(requireContext(), errorMsg,Toast.LENGTH_SHORT).show()
            }

        }




        foodViewModel.foodList.observe(viewLifecycleOwner){foodReciepes->
        if(!foodReciepes.isNullOrEmpty()){
            this.foodlistErrorlist.visibility = View.GONE
            adapter.setData(foodReciepes)

        }else{
            this.foodlistErrorlist.visibility = View.VISIBLE
            adapter.setData(foodReciepes)
        }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupMenu() {



        //add suggestions list dropdown
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.suggest_tv)
        val suggestAdapter = SimpleCursorAdapter(
            activity,
            R.layout.suggest_item_list,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )

        (requireActivity() as MenuHost).addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

                menuInflater.inflate(R.menu.top_app_bar_menu, menu)
               val search = menu.findItem(R.id.search)
                val searchView = search.actionView as SearchView
                searchView.suggestionsAdapter = suggestAdapter


                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(foodName: String?): Boolean {
                        getFoodReciepe(foodName)
                        return true
                    }

                    override fun onQueryTextChange(foodName: String): Boolean {
                       foodViewModel.getSuggetList(foodName)

                        viewLifecycleOwner.lifecycleScope.launch{

                            repeatOnLifecycle(Lifecycle.State.STARTED){

                            foodViewModel.suggestList.collect{suggestList->

                                val cursor = MatrixCursor(arrayOf(
                                    BaseColumns._ID,
                                    SearchManager.SUGGEST_COLUMN_TEXT_1
                                ))
                                foodName.let {

                                    suggestList.forEachIndexed { index, suggestion ->

                                        if(suggestion.contains(foodName,true))
                                            cursor.addRow(arrayOf(index,suggestion))
                                    }
                                }
                                suggestAdapter.changeCursor(cursor)
                            }
                        }
                        }

                        return true
                    }

                })

                searchView.setOnSuggestionListener(object :SearchView.OnSuggestionListener{

                    override fun onSuggestionSelect(position: Int): Boolean {

                        return false
                    }

                    @SuppressLint("Range")
                    override fun onSuggestionClick(position: Int): Boolean {
                        val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor
                        val selection = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                        searchView.setQuery(selection,false)
                        return true
                    }

                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

        })
    }



}





