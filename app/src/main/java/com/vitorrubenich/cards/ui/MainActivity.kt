package com.vitorrubenich.cards.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vitorrubenich.cards.App
import com.vitorrubenich.cards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.listCards.adapter = adapter
        getAllBusinessCard()
        insertListeners()
    }


    fun insertListeners(){
        binding.fabAdicionarCartaoVisita.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java )
            startActivity(intent)
        }
    }
    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this) {
            businessCards -> adapter.submitList(businessCards)
        }
    }
}