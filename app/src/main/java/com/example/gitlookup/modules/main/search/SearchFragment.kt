package com.example.gitlookup.modules.main.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.gitlookup.R
import com.example.gitlookup.modules.main.MainViewModel
import com.example.gitlookup.utils.show
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.view.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initScreen()
        initObservers()

    }

    private fun initScreen() {
        view?.apply {
            searchBtn?.setOnClickListener {
                searchUser()
            }
        }
    }

    private fun initObservers() {

        view?.apply {
            sharedViewModel.showMainProgress.observe(viewLifecycleOwner, Observer {
                loader?.show(it == true)
            })

            sharedViewModel.errorEvent.observe(viewLifecycleOwner, Observer {
                Timber.d("Error occurred")
                Toast.makeText(context, "Some error occurred", Toast.LENGTH_SHORT).show()
            })

            sharedViewModel.successEvent.observe(viewLifecycleOwner, Observer {
                if(it == true)
                    findNavController().navigate(R.id.action_show_info)
            })
        }
    }

    private fun searchUser() {
        view?.apply {

            val searchString = searchInput?.text.toString()
            if(searchString.isNotEmpty()) {
                sharedViewModel.searchUser(searchString)
            }

        }
    }
}