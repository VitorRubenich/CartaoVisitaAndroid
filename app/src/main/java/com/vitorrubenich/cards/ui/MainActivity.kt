package com.vitorrubenich.cards.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.vitorrubenich.cards.App
import com.vitorrubenich.cards.R
import com.vitorrubenich.cards.databinding.ActivityMainBinding
import com.vitorrubenich.cards.util.Image

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
        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
        binding.bottomAppBar.setOnMenuItemClickListener{
            Snackbar.make(binding.root,"$it", Snackbar.LENGTH_LONG).show()
            true
        }
        binding.bottomAppBar.setNavigationOnClickListener{menuItem ->
            Log.e("LISTENER", "insertListeners: ${menuItem.id}")
            when (menuItem.id) {
                R.id.door -> {
                    Snackbar.make(binding.root, resources.getString(R.string.door), Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.rotation -> {
                    Snackbar.make(binding.root, resources.getString(R.string.rotation), Snackbar.LENGTH_SHORT).show()

                    true
                }
                R.id.dashboard -> {
                    Snackbar.make(binding.root, resources.getString(R.string.dashboard), Snackbar.LENGTH_SHORT).show()

                    true
                }
                else -> false
            }
        }
    }
    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this) {
            businessCards -> adapter.submitList(businessCards)
        }
    }
}