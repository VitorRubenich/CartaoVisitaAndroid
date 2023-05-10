package com.vitorrubenich.cards.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.vitorrubenich.cards.App
import com.vitorrubenich.cards.R
import com.vitorrubenich.cards.data.entities.BusinessCard
import com.vitorrubenich.cards.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }

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
            val businessCard = BusinessCard(
            nome = binding.tilNome.editText?.text.toString(),
            empresa = binding.tilEmpresa.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                fundoPersonalizado = binding.tilCor.editText?.text.toString()
                )
            mainViewModel.insert(businessCard)
            Snackbar.make(binding.root, R.string.label_success,Snackbar.LENGTH_LONG).show()
            finish()
        }
    }
}