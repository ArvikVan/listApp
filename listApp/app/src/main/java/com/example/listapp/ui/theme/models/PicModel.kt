package com.example.listapp.ui.theme.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PicModel(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
