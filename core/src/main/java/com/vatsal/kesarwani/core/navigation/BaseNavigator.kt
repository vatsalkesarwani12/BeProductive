package com.vatsal.kesarwani.core.navigation

import androidx.navigation.NavController

abstract class BaseNavigator {

    private var navController: NavController? = null

    fun bind(navController: NavController) {
        this.navController = navController
    }

    fun unbind() {
        navController = null
    }
}