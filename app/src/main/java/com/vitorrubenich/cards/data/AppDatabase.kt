package com.vitorrubenich.cards.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vitorrubenich.cards.App
import com.vitorrubenich.cards.data.DAO.BusinessCardDAO
import com.vitorrubenich.cards.data.entities.BusinessCard

@Database(entities = [BusinessCard::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun businessDAO() : BusinessCardDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "businesscard_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}