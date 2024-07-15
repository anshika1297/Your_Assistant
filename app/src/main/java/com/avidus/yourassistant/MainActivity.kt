package com.avidus.yourassistant

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.avidus.yourassistant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonImage.setOnClickListener {
            startActivity(Intent(this,ImageActivity::class.java))
        }

        binding.buttonChat.setOnClickListener {
            startActivity(Intent(this,ChatActivity::class.java))
        }
           }
}