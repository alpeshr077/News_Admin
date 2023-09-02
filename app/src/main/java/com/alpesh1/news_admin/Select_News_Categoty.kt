package com.alpesh1.news_admin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Select_News_Categoty : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_news_categoty)


        var btnIndia = findViewById<TextView>(R.id.newsIndia)
        var btnBusiness = findViewById<TextView>(R.id.NewsBusiness)

        btnIndia.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        btnBusiness.setOnClickListener {

            val intent = Intent(this,Business_Category::class.java)
            startActivity(intent)
            finish()

        }

    }
}