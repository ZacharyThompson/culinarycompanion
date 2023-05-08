package edu.fullerton.fz.culinarycompanion.db

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import java.util.*
import java.util.concurrent.Executors

private const val TAG = "MyDatabaseRepository"
private const val DATABASE_NAME = "my-cool-database"

class myDatabaseRepository constructor(private val fragment: Fragment)
{
    private val database: MyDatabase = Room.databaseBuilder(
        fragment.requireContext().applicationContext,
        MyDatabase::class.java,
        DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    //	Data Access Object
    private val myDao = database.myDao()

    //	Executor makes it easier to run stuff in a background thread
    private val executor = Executors.newSingleThreadExecutor()

    //	Keep track of person IDs
    private var currentFavoriteIndex: Int = 0
    var currentID: MutableLiveData<String?> = MutableLiveData<String?>()
    val faveIDs: LiveData<List<String>> = this.fetchFaveIDs()
    var currentObj: LiveData<String?> = MutableLiveData<String?>()

    fun fetchFaveIDs(): LiveData<List<String>> = myDao.fetchFavoriteIDs()

    fun removeFave(id: String)
    {
        this.executor.execute {
            this.myDao.removeFavorite(id)
        }
    }

    fun addFavorite(fave: Favorite)
    {
        this.executor.execute {
            this.myDao.addFavorite(fave)
        }
    }
    fun previousFave(): Unit
    {
        this.adjustFavoriteIndex(-1)
    }
    fun nextFave(): Unit
    {
        this.adjustFavoriteIndex(1)
    }
    private fun adjustFavoriteIndex(adjustment: Int)
    {
        this.currentFavoriteIndex += adjustment
    }
    fun updateCurrentFavorite()
    {
        Log.v(TAG, "updateCurrentFavorite()")

        this.currentID.value?.let { pid ->
            this.currentID = this.myDao.fetchFavorite(pid) as MutableLiveData<String?>
            this.currentID.observe(this.fragment.viewLifecycleOwner) { fave ->
                Log.v(TAG, "Loaded fave: $fave")
            }
        }
    }
}