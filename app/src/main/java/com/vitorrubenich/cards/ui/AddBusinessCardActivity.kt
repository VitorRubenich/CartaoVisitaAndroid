package com.vitorrubenich.cards.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.vitorrubenich.cards.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners(){
        binding.btnFechar.setOnClickListener{
            finish()
        }
        binding.fabConfirmar.setOnClickListener {
            Snackbar.make(binding.root, "Confirmado", Snackbar.LENGTH_SHORT).show()
        }
    }
}