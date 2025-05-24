package com.example.rmai2425_projects_astromap

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.KvizPitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton
    private lateinit var submitButton: Button
    private lateinit var scoreTextView: TextView
    private lateinit var nextButton: Button
    private lateinit var quitButton: ImageButton

    private var currentQuestionIndex = 0
    private var score = 0
    private var questions: List<KvizPitanje> = listOf()
    private var answeredQuestions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionTextView = findViewById(R.id.question_text)
        radioGroup = findViewById(R.id.options_radiogroup)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)
        submitButton = findViewById(R.id.submit_button)
        scoreTextView = findViewById(R.id.score_text)
        nextButton = findViewById(R.id.next_button)
        quitButton = findViewById(R.id.quit_button)

        val category = intent.getStringExtra("category") ?: "Planeti"

        title = category

        lifecycleScope.launch {
            loadQuestions(category)
        }

        submitButton.setOnClickListener {
            checkAnswer()
            submitButton.visibility = View.GONE
            nextButton.visibility = View.VISIBLE
        }

        nextButton.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                displayQuestion()
                nextButton.visibility = View.GONE
                submitButton.visibility = View.VISIBLE
                radioGroup.clearCheck()
                enableOptions(true)
            } else {
                val resultMessage = "Kviz završen! Vaš rezultat: $score od ${questions.size}"
                Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show()

                Handler(Looper.getMainLooper()).postDelayed({
                    finish()
                }, 2000)
            }
        }

        quitButton.setOnClickListener {
            finish()
        }
    }

    private suspend fun loadQuestions(category: String) {
        withContext(Dispatchers.IO) {
            val dao = DatabaseProvider.getDatabase(this@QuizActivity).entitiesDao()
            questions = dao.getKvizPitanjaByKategorija(category)
        }

        withContext(Dispatchers.Main) {
            if (questions.isNotEmpty()) {
                if (questions.size > 10) {
                    questions = questions.take(10)
                }
                displayQuestion()
            } else {
                Toast.makeText(this@QuizActivity, "Nema dostupnih pitanja za ovu kategoriju", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun displayQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.pitanje

        val options = mutableListOf(
            currentQuestion.tocniOdgovor,
            currentQuestion.netocniOdgovor1,
            currentQuestion.netocniOdgovor2,
            currentQuestion.netocniOdgovor3
        ).shuffled()

        option1.text = options[0]
        option2.text = options[1]
        option3.text = options[2]
        option4.text = options[3]

        scoreTextView.text = "Rezultat: $score/$answeredQuestions"
    }

    private fun checkAnswer() {
        val selectedOptionId = radioGroup.checkedRadioButtonId
        if (selectedOptionId != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedOptionId)
            val answer = selectedRadioButton.text.toString()
            val correctAnswer = questions[currentQuestionIndex].tocniOdgovor

            if (answer == correctAnswer) {
                score++
                Toast.makeText(this, "Točno!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Netočno! Točan odgovor je: $correctAnswer", Toast.LENGTH_SHORT).show()
            }

            answeredQuestions++
            scoreTextView.text = "Rezultat: $score/$answeredQuestions"

            submitButton.visibility = View.GONE
            nextButton.visibility = View.VISIBLE

            enableOptions(false)
        } else {
            Toast.makeText(this, "Molimo odaberite odgovor", Toast.LENGTH_SHORT).show()
        }
    }

    private fun enableOptions(enable: Boolean) {
        option1.isEnabled = enable
        option2.isEnabled = enable
        option3.isEnabled = enable
        option4.isEnabled = enable
    }
}