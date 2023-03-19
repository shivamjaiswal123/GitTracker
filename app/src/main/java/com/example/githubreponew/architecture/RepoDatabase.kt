package com.example.githubreponew.architecture

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubreponew.model.Repository

@Database(entities = [Repository::class], version = 1 , exportSchema = false)
abstract class RepoDatabase: RoomDatabase() {
    abstract fun repoDao(): RepoDao
    companion object{
        private var INSTANCE: RepoDatabase? = null
        fun getInstance(context: Context): RepoDatabase? {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(RepoDatabase::class.java){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RepoDatabase::class.java,
                    "repo_database"
                ).build()
                INSTANCE = instance
                instance.close()
                return INSTANCE
            }
        }
    }
}