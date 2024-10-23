package com.example.livingcostapp.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.livingcostapp.data.model.Converters
import com.example.livingcostapp.data.model.TransactionLiveCost
import com.example.livingcostapp.data.model.TransactionDao


@Database(entities = [TransactionLiveCost::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {

        @Volatile
        private var INSTANCE: TransactionDatabase? = null

        fun getDatabase(context: Context): TransactionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TransactionDatabase::class.java,
                    "transaction_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}