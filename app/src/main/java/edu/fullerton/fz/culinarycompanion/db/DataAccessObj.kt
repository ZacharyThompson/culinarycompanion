package edu.fullerton.fz.culinarycompanion.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface DataAccessObj {
    @Query("SELECT 'id' FROM FAVORITES ORDER BY 'id' DESC")
    fun fetchFavoriteIDs(): LiveData<List<String>>

    @Insert
    fun addFavorite(fave: Favorite)

    @Query("DELETE FROM FAVORITES WHERE id=(:id)")
    fun removeFavorite(id: String)

    @Query("SELECT * FROM FAVORITES WHERE id=(:id)")
    fun fetchFavorite(id: String): LiveData<String?>
}