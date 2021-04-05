package com.example.tictactoe

import com.example.tictactoe.models.Position

class Game {
   private var currentPlayer = 1
    var player1Points = 0
    var player2Points = 0

    val currentPlayerMark : String
    get() {
        return if (currentPlayer == 1) "X" else "O"
    }

    private var state = arrayOf(
        intArrayOf(0,0,0),
        intArrayOf(0,0,0),
        intArrayOf(0,0,0)
    )

    fun makeMove(position: Position) : WinningLine?{
        state[position.row][position.column] = currentPlayer
        var winningLine = checkWinner()

        if (winningLine == null){
            currentPlayer = 3 -currentPlayer
        }else{
            when(currentPlayer){
                1 -> player1Points++
                2 -> player2Points++
            }
        }
        return winningLine
    }

    private fun checkWinner(): WinningLine? {
       //Row_1
        if (state[0][0] == currentPlayer && state[0][1]==currentPlayer && state[0][2]==currentPlayer){
            return WinningLine.ROW_0
        }

        //Row_2
        if (state[1][0] == currentPlayer && state[1][1]==currentPlayer && state[1][2]==currentPlayer){
            return WinningLine.ROW_1
        }

        //Row_3
        if (state[2][0] == currentPlayer && state[2][1]==currentPlayer && state[2][2]==currentPlayer){
            return WinningLine.ROW_0
        }

        //Col_1
        if (state[0][0] == currentPlayer && state[1][0]==currentPlayer && state[2][0]==currentPlayer){
            return WinningLine.COLUMN_0
        }

        //Col_2
        if (state[0][1] == currentPlayer && state[1][1]==currentPlayer && state[2][1]==currentPlayer){
            return WinningLine.COLUMN_1
        }

        //Col_3
        if (state[0][2] == currentPlayer && state[1][2]==currentPlayer && state[2][2]==currentPlayer){
            return WinningLine.COLUMN_2
        }

        //Diagonal_Left
        if (state[0][0]== currentPlayer && state[1][1]==currentPlayer && state[2][2]==currentPlayer){
            return WinningLine.DIAGONAL_LEFT
        }

        //Diagonal_Right
        if (state[0][2]== currentPlayer && state[1][1]==currentPlayer && state[2][0]==currentPlayer){
            return WinningLine.DIAGONAL_RIGHT
        }
        return null
    }

    fun reset(){
        state = arrayOf(
            intArrayOf(0,0,0),
            intArrayOf(0,0,0),
            intArrayOf(0,0,0)
        )
        currentPlayer = 1
    }

    private fun hasGameEnded():Boolean{
        state.forEach { row ->
            val hasWon = row.all { player -> player == currentPlayer }
            if (hasWon)
                return true
        }

        for (i in state.indices){
            val hasWon = state[i].all { player -> player == currentPlayer }
            if(hasWon)
                return true
        }

        for (i in state.indices){
            if (state[i][i] != currentPlayer)
                return false
        }

        for (i in state.size until 0){
            if(state[i][i] != currentPlayer)
                return false
        }
        return true
    }

}