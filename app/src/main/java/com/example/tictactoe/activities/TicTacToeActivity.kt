package com.example.tictactoe.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.tictactoe.Game
import com.example.tictactoe.R
import com.example.tictactoe.WinningLine
import com.example.tictactoe.WinningLine.*
import com.example.tictactoe.models.Position
import kotlinx.android.synthetic.main.activity_tic_tac_toe.*

class TicTacToeActivity : AppCompatActivity() {

    private lateinit var gameManager: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        gameManager = Game()

        attachListeners()

        updatePoints()

    }

    private fun updatePoints() {
        player1Points_tv.text = "Player X Points: ${gameManager.player1Points}"
        player2Points_tv.text = "Player O Points: ${gameManager.player2Points}"
    }

    private fun attachListeners(){

        btn1.setOnClickListener {
            onBtnClicked(btn1,Position(0,0))
        }

        btn2.setOnClickListener {
            onBtnClicked(btn2, Position(0,1))
        }

        btn3.setOnClickListener {
            onBtnClicked(btn3,Position(0,2))
        }

        btn4.setOnClickListener {
            onBtnClicked(btn4, Position(1,0))
        }

        btn5.setOnClickListener {
            onBtnClicked(btn5,Position(1,1))
        }

        btn6.setOnClickListener {
            onBtnClicked(btn6, Position(1,2))
        }

        btn7.setOnClickListener {
            onBtnClicked(btn7 ,Position(2,0))
        }

        btn8.setOnClickListener {
            onBtnClicked(btn8 , Position(2,1))
        }

        btn9.setOnClickListener {
            onBtnClicked(btn9,Position(2,2))
        }

        newGameButton.setOnClickListener {
            newGameButton.visibility = View.GONE
            gameManager.reset()
            resetCells()
        }
    }

    private fun resetCells() {

        btn1.text = ""
        btn2.text = ""
        btn3.text = ""
        btn4.text = ""
        btn5.text = ""
        btn6.text = ""
        btn7.text = ""
        btn8.text = ""
        btn9.text = ""
        btn1.background = null
        btn2.background = null
        btn3.background = null
        btn4.background = null
        btn5.background = null
        btn6.background = null
        btn7.background = null
        btn8.background = null
        btn9.background = null
        btn1.isEnabled = true
        btn2.isEnabled = true
        btn3.isEnabled = true
        btn4.isEnabled = true
        btn5.isEnabled = true
        btn6.isEnabled = true
        btn7.isEnabled = true
        btn8.isEnabled = true
        btn9.isEnabled = true

    }

    private fun onBtnClicked(view: Button, position: Position) {

        if (view.text.isEmpty()){
            view.text = gameManager.currentPlayerMark
            if (gameManager.currentPlayerMark == "X")
                view.setBackgroundColor(Color.YELLOW)
            else
                view.setBackgroundColor(Color.CYAN)

            val winningLine = gameManager.makeMove(position)

            if (winningLine != null){
                updatePoints()
                disableBoxes()
                newGameButton.visibility = View.VISIBLE
                showWinner(winningLine)
            }
        }
    }

    private fun showWinner(winningLine: WinningLine) {

        val (winningBtn , background) = when (winningLine){
            ROW_0 -> Pair(listOf(btn1 , btn2 , btn3), R.drawable.horizontal_line)
            ROW_1 -> Pair(listOf(btn4 , btn5 , btn6), R.drawable.horizontal_line)
            ROW_2 -> Pair(listOf(btn7 , btn8 , btn9), R.drawable.horizontal_line)
            COLUMN_0 -> Pair(listOf(btn1 , btn4 , btn7), R.drawable.vertical_line)
            COLUMN_1 -> Pair(listOf(btn2 , btn5 , btn8), R.drawable.vertical_line)
            COLUMN_2 -> Pair(listOf(btn3 , btn6 , btn9), R.drawable.vertical_line)
            DIAGONAL_LEFT -> Pair(listOf(btn1 , btn5 , btn9), R.drawable.left_diagonal_line)
            DIAGONAL_RIGHT -> Pair(listOf(btn3 , btn5 , btn7), R.drawable.right_diagonal_line)
        }
        winningBtn.forEach { box ->
            box.background = ContextCompat.getDrawable(TicTacToeActivity@this , background)
        }
    }

    private fun disableBoxes() {
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        btn4.isEnabled = false
        btn5.isEnabled = false
        btn6.isEnabled = false
        btn7.isEnabled = false
        btn8.isEnabled = false
        btn9.isEnabled = false
    }
}