package com.example.myekasari.model.dummy

import android.media.Rating

class HomeModel (title:String, src:String, rating:Float) {
    var title = " "
    var src = " "
    var rating = 0f

    init {
        this.title = title
        this.src = src
        this.rating = rating
    }
}