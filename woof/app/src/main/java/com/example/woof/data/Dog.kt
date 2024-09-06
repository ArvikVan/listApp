package com.example.woof.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.woof.R

data class Dog(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)
