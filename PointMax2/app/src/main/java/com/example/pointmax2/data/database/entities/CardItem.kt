package com.example.pointmax2.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardItem(
    @PrimaryKey
    var cardName: String,
    var general: Double = 1.0,
    var groceries: Double = general,
    var restaurants: Double = general,
    var gas: Double = general,
    var airlines: Double = general,
    var travel: Double = general
)