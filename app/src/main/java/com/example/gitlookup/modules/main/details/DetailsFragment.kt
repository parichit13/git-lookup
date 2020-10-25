package com.example.gitlookup.modules.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitlookup.R
import com.example.gitlookup.models.GithubUser
import com.example.gitlookup.modules.main.MainViewModel
import com.example.gitlookup.utils.applyImage
import com.example.gitlookup.utils.applyText
import com.example.gitlookup.utils.hide
import com.example.gitlookup.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_scrolling.view.*
import kotlinx.android.synthetic.main.fragment_info.view.*

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private val viewModel: DetailsViewModel by viewModels()
    lateinit var repoListAdapter: RepoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        repoListAdapter = RepoListAdapter()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initScreen()
        initObservers()

    }

    private fun initScreen() {
        view?.apply {

            toolbar?.apply {
                setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
                setNavigationOnClickListener {
                    activity?.onBackPressed()
                }
            }

            publicRepoList?.apply {
                adapter = repoListAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            }

        }
    }

    private fun initObservers() {
        view?.apply {
            sharedViewModel.liveUser.observe(viewLifecycleOwner, Observer {
                it?.let {
                    showUserInfo(it)
                }
            })

            viewModel.livePublicRepos.observe(viewLifecycleOwner, Observer {
                it?.let {
                    repoListAdapter.submitList(it)
                }
            })

            viewModel.showLoading.observe(viewLifecycleOwner, Observer {
                progressIndicator?.show(it == true)
            })
        }
    }

    private fun showUserInfo(user: GithubUser) {
        view?.apply {

            toolbar?.title = user.name ?: user.login
            img_avatar?.applyImage(
                user.avatarUrl,
                placeholder = R.drawable.avatar,
                circleCrop = true
            )
            name?.applyText(user.name)
            username?.applyText("@${user.login}")
            email?.applyText(user.email)
            profileBio?.applyText(user.bio)
            followerCount?.applyText(user.followers?.toString())
            followingCount?.applyText(user.following?.toString())
            publicRepoCount?.applyText(user.publicRepos?.toString())

            user.publicRepos?.let {
                if (it > 0) {
                    viewModel.fetchPublicRepos(user.login)
                } else {
                    publicRepoList?.hide()
                    emptyRepoMsg?.show()
                }
            }

        }
    }
}