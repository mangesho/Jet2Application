package com.jet2.assignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jet2.assignment.R

class ArticlesActivity : AppCompatActivity() {

    /**
     * called when activity starts. this is where all view initialization happen.
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)
    }
}