package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var board :Array<Array<Button>>
    var PLAYER = true;
    var TURN_COUNT =0;
    var boardStatus = Array(3){ IntArray(3) }
    var name1 :String? = null
    var name2 :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main)

          name1 = intent.getStringExtra(KEY_1)
          name2 = intent.getStringExtra(KEY_2)

        displayTv.text = "$name1 Turn"

        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9),
        )

        for(i:Array<Button>in board){
            for(button:Button in i){
                button.setOnClickListener (this)
            }
        }
           intializeBoardStatus()

        resetBtn.setOnClickListener {
            displayTv.text ="$name1 Turn"
            TURN_COUNT=0;
            PLAYER=true
            intializeBoardStatus()
        }
    }

    private fun intializeBoardStatus(){
        for(i:Int in 0..2){
            for(j:Int in 0..2){
                boardStatus[i][j] =-1;

            }

        }

        for(i:Array<Button> in board){
            for(button:Button in i){
                button.isEnabled=true
                button.text =""
            }

        }
    }

    override fun onClick(view: View) {
      when(view.id) {
          R.id.button1 ->{
            updateValue(row =0,col =0,player = PLAYER)
          }
          R.id.button2 ->{
              updateValue(row =0,col =1,player = PLAYER)

          }
          R.id.button3 ->{
              updateValue(row =0,col =2,player = PLAYER)
          }
          R.id.button4 ->{
              updateValue(row =1,col =0,player = PLAYER)
      }
          R.id.button5 ->{
              updateValue(row =1,col =1,player = PLAYER)
      }
          R.id.button6 ->{
              updateValue(row =1,col =2,player = PLAYER)
      }
          R.id.button7 ->{
              updateValue(row =2,col =0,player = PLAYER)
      }
          R.id.button8 ->{
              updateValue(row =2,col =1,player = PLAYER)
          }
          R.id.button9 ->{
              updateValue(row =2,col =2,player = PLAYER)
          }

        }
        TURN_COUNT++
        PLAYER =!PLAYER
        if(PLAYER){
            updatedispaly("$name1 Turn")
        }else{
            updatedispaly("$name2 Turn")
        }
        if(TURN_COUNT == 9){
            updatedispaly("Game Draw")
        }
        checkWiner()
    }

    private fun disableButton(){
        for(i:Array<Button>in board){
            for(button:Button in i){
                button.isEnabled=false
            }
        }

    }

    private fun checkWiner(){
        //Horizontal Rows

        for(i:Int in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if(boardStatus[i][0] == 1){
                    updatedispaly("$name1 Winner")

                    break
                }else if(boardStatus[i][0] == 0){
                    updatedispaly("$name2 Winner")
                    break
                }
            }
        }

        // Vertical Columns


        for(i:Int in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if(boardStatus[0][i] == 1){
                    updatedispaly("$name1 Winner")

                    break
                }else if(boardStatus[0][i] == 0){
                    updatedispaly("$name2 Winner")
                    break
                }
            }
        }

        // First diagnol
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[2][2] == boardStatus[0][0]){
            if(boardStatus[0][0] == 1){
                updatedispaly("$name1 Winner")
            }else if(boardStatus[0][0] == 0){
                updatedispaly("$name2 Winner")
            }
        }

        // Second diagnol
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[2][0] == boardStatus[0][2]){
            if(boardStatus[0][2] == 1){
                updatedispaly("$name1 Winner")
            }else if(boardStatus[0][2] == 0){
                updatedispaly("$name2 Winner")
            }
        }
    }
    @SuppressLint("ResourceAsColor")
    private fun updateValue(row:Int, col:Int, player:Boolean){
        val text:String = if(player) "X" else "0"
        val value:Int = if(player) 1 else 0

        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardStatus[row][col] = value
    }

    private fun updatedispaly(text:String){
        displayTv.text=text
        if(text.contains("Winner")){
            disableButton()
        }else{

        }
    }

}