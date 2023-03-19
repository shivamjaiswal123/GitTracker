package com.example.githubreponew.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Repository(
    val name: String,
    val description: String,
    val html_url: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
