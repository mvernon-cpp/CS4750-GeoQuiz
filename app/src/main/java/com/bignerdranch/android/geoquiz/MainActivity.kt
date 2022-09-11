package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.Gravity
//import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

//Challenge: Graded Quiz
import kotlin.math.roundToInt


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
//    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_pollen, true),
        Question(R.string.question_foodchain, false),
        Question(R.string.question_treeAge, true),
        Question(R.string.question_stems, false),
        Question(R.string.question_photosynthesis, false)
    )
    private var currentIndex = 0

    //Challenge: Graded Quiz
    private var totalCLicks: Int = 0
    private var correctAnswers: Int = 0
    private var percentage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
//        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            checkAnswer(true)
            nextQuestion()
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
            nextQuestion()
        }

//        nextButton.setOnClickListener {
//            currentIndex = (currentIndex + 1) % questionBank.size
//            updateQuestion()
//        }

        updateQuestion()

    }

    //Challenge: Graded Quiz
    private fun nextQuestion(){
//        currentIndex++
        currentIndex = (currentIndex + 1) % questionBank.size
//        val messageResId = correctAnswers / totalCLicks
//        if( currentIndex >= questionBank.size)
//            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
//                .show()

        updateQuestion()
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

        //Challenge: Graded Quiz
        if (userAnswer == correctAnswer) {
            correctAnswers++
            totalCLicks++
        }
        else
        {
            totalCLicks++
        }

        percentage = ((correctAnswers.toDouble() / totalCLicks) * 100).roundToInt()

        val t = Toast.makeText (this, "Overall Grade: $percentage% ",Toast.LENGTH_SHORT)
        t.setGravity(Gravity.TOP , 0, 200)
        t.show()
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }


}
