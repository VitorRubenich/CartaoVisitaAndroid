package com.vitorrubenich.cards

import android.app.Application
import com.vitorrubenich.cards.data.AppDatabase
import com.vitorrubenich.cards.data.repository.BusinessCardRepository

class App : Application() {
    val database by lazy {AppDatabase.getDatabase(this)}
    val repository by lazy { BusinessCardRepository(database.businessDAO())}
}