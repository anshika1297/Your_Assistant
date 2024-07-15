package com.avidus.yourassistant

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.avidus.yourassistant.databinding.ActivityMainBinding
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eTPrompt = binding.editText
        val sendButton = binding.sendButton
        val textViewResult = binding.textViewResult
        val resultLayout = binding.resultLayout
        val resultText = binding.resultText
        val progressBar = binding.progressBar

        sendButton.setOnClickListener {
            val prompt = eTPrompt.text.toString()
            val generativeModel = GenerativeModel(
                modelName = "gemini-1.5-flash",
                apiKey = "AIzaSyABdLfRiPa6S3Vgu_QtRuw9NUVfbIso9XI"
            )

            progressBar.visibility = ProgressBar.VISIBLE
            resultLayout.visibility = LinearLayout.GONE
            textViewResult.visibility = TextView.GONE

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        generativeModel.generateContent(prompt)
                    }
                    resultText.text = response.text
                    resultLayout.visibility = LinearLayout.VISIBLE
                } catch (e: Exception) {
                    textViewResult.text = "Error: ${e.message}"
                    textViewResult.visibility = TextView.VISIBLE
                } finally {
                    progressBar.visibility = ProgressBar.GONE
                }
            }
        }
    }
}
