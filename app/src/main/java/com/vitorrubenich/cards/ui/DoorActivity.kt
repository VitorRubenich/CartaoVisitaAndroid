package com.vitorrubenich.cards.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vitorrubenich.cards.R
import com.vitorrubenich.cards.databinding.ActivityDoorBinding

class DoorActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDoorBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    fun insertListeners(){
        binding.btnFechar.setOnClickListener{
            finish()
        }
    }

}