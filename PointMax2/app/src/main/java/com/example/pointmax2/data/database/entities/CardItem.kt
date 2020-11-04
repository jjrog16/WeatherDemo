package com.example.pointmax2.data.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class CardItem(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        var cardName: String,
        var general: Double = 1.0,
        var groceries: Double = general,
        var restaurants: Double = general,
        var gas: Double = general,
        var airlines: Double = general,
        var travel: Double = general
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (other == null || other.javaClass != this.javaClass) {
            return false
        }
        val otherCard = other as CardItem
        return (otherCard.cardName == this.cardName
                && otherCard.general == this.general
                && otherCard.groceries == this.groceries
                && otherCard.restaurants == this.restaurants
                && otherCard.gas == this.gas
                && otherCard.airlines == this.airlines
                && otherCard.travel == this.travel
        )
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + cardName.hashCode()
        result = 31 * result + general.hashCode()
        result = 31 * result + groceries.hashCode()
        result = 31 * result + restaurants.hashCode()
        result = 31 * result + gas.hashCode()
        result = 31 * result + airlines.hashCode()
        result = 31 * result + travel.hashCode()
        return result
    }

}