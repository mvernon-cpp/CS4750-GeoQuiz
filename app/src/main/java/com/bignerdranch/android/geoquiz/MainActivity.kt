package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
//import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
//    private lateinit var nextButton: Button
//    private lateinit var prevButton: Button

    //    Challenge: From Button to ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton

    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_pollen, true),
        Question(R.string.question_foodchain, false),
        Question(R.string.question_treeAge, true),
        Question(R.string.question_stems, false),
        Question(R.string.question_photosynthesis, false)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        //Challenge: Add a Previous Button
        prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            //when on first question wrap back to last question
            if (currentIndex < 0)
                currentIndex = questionBank.size-1
            updateQuestion()
        }

        //Challenge: Add a Listener to the TextView
        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()

    }
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }
}
