package com.vitorrubenich.cards.ui


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
        insertListeners()
        getAllBusinessCard()

    }


    fun insertListeners(){
        binding.fabAdicionarCartaoVisita.setOnClickListener {
            adicionarNovoCartao()
        }
        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
        binding.bottomAppBar.setOnMenuItemClickListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.door -> {
                    doorActivity()
                    true
                }
                R.id.rotation -> {
                    // Do Rotation Things

                    true
                }
                R.id.dashboard -> {
                    // Do Dashboard things
                    true
                }
                else -> false
            }
            true
        }
        binding.bottomAppBar.setNavigationOnClickListener{menuItem ->
            Log.e("LISTENER", "insertListeners: ${menuItem.id}")
            AlertDialog.Builder(binding.root.context)
                .setMessage("Ir para configurações?")
                .setPositiveButton("Sim") { dialog, which -> adicionarNovoCartao() }
                .setNegativeButton("Não") { dialog, which -> dialog.dismiss() }
                .create().show()

        }
    }
    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this) {
            businessCards -> adapter.submitList(businessCards)
        }
    }
    fun adicionarNovoCartao(){
        val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java )
        startActivity(intent)
    }
    fun doorActivity(){
        val intent = Intent(this@MainActivity, DoorActivity::class.java)
        startActivity(intent)
    }
}