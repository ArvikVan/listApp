package com.example.listapp.ui.theme.data

import com.example.listapp.R
import com.example.listapp.ui.theme.models.PicModel

class DataSource {
    fun getListOfPic(): List<PicModel>{
        return listOf(
            PicModel(R.string.pic1, R.drawable.image1),
            PicModel(R.string.pic2, R.drawable.image2),
            PicModel(R.string.pic3, R.drawable.image3),
            PicModel(R.string.pic4, R.drawable.image4),
            PicModel(R.string.pic5, R.drawable.image5),
            PicModel(R.string.pic6, R.drawable.image6),
            PicModel(R.string.pic7, R.drawable.image7),
            PicModel(R.string.pic8, R.drawable.image8),
            PicModel(R.string.pic9, R.drawable.image9),
            PicModel(R.string.pic10, R.drawable.image10)
        )
    }
}