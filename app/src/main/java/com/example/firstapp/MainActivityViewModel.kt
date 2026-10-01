package com.example.firstapp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


class MainActivityViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // SavedStateHandle can automatically bundle 'UserProfile' because it is Parcelable
    var userProfile: UserProfile?
        get() = savedStateHandle["user_profile"]
        set(value) { savedStateHandle["user_profile"] = value }

}