package com.example.firstapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfile(val name: String, val age: Int) : Parcelable
