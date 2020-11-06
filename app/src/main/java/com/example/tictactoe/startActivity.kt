package com.example.tictactoe

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*

const val KEY_1 ="first"

const val KEY_2 ="Sec"
class startActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btn_start.setOnClickListener {

            if(et_name1.text.toString().isEmpty()){
                Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show()
            }else if (et_name2.text.toString().isEmpty()) {
                Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show()
            } else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(KEY_1,et_name1.text.toString())
                intent.putExtra(KEY_2,et_name2.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}


