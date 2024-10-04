package com.example.vittoassignment.presentation.home

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vittoassignment.R
import com.example.vittoassignment.util.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    val TAG = "HomeFragment"

    lateinit var newsAdapter: RecyclerViewAdapter
    lateinit var recyclerView:RecyclerView
    lateinit var navController: NavController
    lateinit var tagsRecyclerView: RecyclerView
    lateinit var tagAdapter: TagsRecyclerViewAdapter

    val tagList = listOf(Category.Technology,Category.Health,Category.Sports,Category.Entertainment)

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view  = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.home_recyclerview)
        tagsRecyclerView = view.findViewById(R.id.home_tags__recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController =  view.findNavController()

    }
    @SuppressLint("RepeatOnLifecycleWrongUsage")
    override fun onResume() {
        super.onResume()




        newsAdapter = RecyclerViewAdapter(context = requireActivity(), onFavouriteCLick = viewModel::onEvent)
        tagAdapter = TagsRecyclerViewAdapter(tagList,viewModel::onEvent)


        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.newsList.collectLatest { list->

                    if (list != null) {
                        newsAdapter.updateList(list)
                    }



                }
            }

        }

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = newsAdapter

        tagsRecyclerView.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        tagsRecyclerView.adapter = tagAdapter



    }

}