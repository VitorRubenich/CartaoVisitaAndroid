package com.vitorrubenich.cards.data.repository

import com.vitorrubenich.cards.data.DAO.BusinessCardDAO
import com.vitorrubenich.cards.data.entities.BusinessCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch

class BusinessCardRepository(private val dao: BusinessCardDAO) {

    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(businessCard)
        }
    }
    fun getAll() = dao.getAll()
}