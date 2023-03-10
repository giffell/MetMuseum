package com.test.metmuseum.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import com.test.common.network.ActivityScope
import com.test.metmuseum.MainActivity
import com.test.metmuseum.R
import com.test.navigation.BaseNavigator
import com.test.object_details_screen.ObjectDetailsRouter
import com.test.search_screen.SearchFragmentDirections
import com.test.search_screen.SearchRouter
import javax.inject.Inject

@ActivityScope
class Router @Inject constructor() : BaseNavigator(), SearchRouter, ObjectDetailsRouter {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (owner is MainActivity) {
            navController = owner.findNavController(R.id.nav_host_fragment)
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        navController = null
    }

    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun openObjectDetailsFragment(objectId: Int) {
        val direction = SearchFragmentDirections.actionSearchFragmentToObjectDetailsFragment(objectId)
        navController?.navigate(direction)
    }
}