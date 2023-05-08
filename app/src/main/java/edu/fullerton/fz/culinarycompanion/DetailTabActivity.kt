package edu.fullerton.fz.culinarycompanion

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.squareup.picasso.Picasso
import edu.fullerton.fz.culinarycompanion.api.FavoritesExecutor

private const val LOG_TAG = "DetailTabActivity"
private fun extractYoutubeVideoId(ytLink: String): String? {
    val pattern = "(?<=v=|youtu.be/)[a-zA-Z0-9_-]{11}".toRegex()
    val matchResult = pattern.find(ytLink)
    return matchResult?.value
}
class DetailTabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tab)

        val idMeal = intent.getIntExtra("idMeal", 0)

        // Find views
        val mealThumbImageView: ImageView = findViewById(R.id.mealImageView)
        val mealNameTextView: TextView = findViewById(R.id.mealTextView)
        val instructionsTextView: TextView = findViewById(R.id.instructionsTextView)
        val ingredientsListTextView: TextView = findViewById(R.id.ingredientsListTextVIew)
        val measuresListTextView: TextView = findViewById(R.id.measuresTextView)
        val faveButton: Button = findViewById(R.id.favoriteButton)

        val youtubePlayerView: YouTubePlayerView = findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(youtubePlayerView)
        youtubePlayerView.enableAutomaticInitialization = false

        val detailTabViewModel = ViewModelProvider(this)[DetailTabViewModel::class.java]
        faveButton.setOnClickListener{
            FavoritesExecutor().postFave(idMeal.toString())
        }
        detailTabViewModel.setMeal(idMeal)
        detailTabViewModel.mealLiveData.observe(this) {meal->
            if (meal != null) {
                Log.d(LOG_TAG, "Meal Name: ${meal.strMeal}")
                // Populate views with meal data
                Picasso.get().load(meal.strMealThumb).into(mealThumbImageView)
                mealNameTextView.text = meal.strMeal

                var youtubeLink: String? = ""
                if (meal.strYoutube != null) {
                    Log.i(LOG_TAG, "Youtube video loading ${meal.strYoutube}")
                    youtubeLink = extractYoutubeVideoId(meal.strYoutube!!)
                    Log.i(LOG_TAG, "Youtube video ID extracted $youtubeLink")
                    if (youtubeLink != null) {
                        youtubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
                            override fun onReady(player: YouTubePlayer) {
                                player.cueVideo(youtubeLink, 0f)
                            }

                            override fun onError(
                                youTubePlayer: YouTubePlayer,
                                error: PlayerConstants.PlayerError
                            ) {
                                super.onError(youTubePlayer, error)
                                Log.e(LOG_TAG, "ERROR LOADING VIDEO ${meal.strYoutube} : $error")
                            }
                        })
                    } else {
                        youtubePlayerView.visibility = View.GONE
                    }
                } else {
                    youtubePlayerView.visibility = View.GONE
                }

                instructionsTextView.text = meal.strInstructions!!.trimEnd()
                val ingredients = listOf(
                    meal.strIngredient1,
                    meal.strIngredient2,
                    meal.strIngredient3,
                    meal.strIngredient4,
                    meal.strIngredient5,
                    meal.strIngredient6,
                    meal.strIngredient7,
                    meal.strIngredient8,
                    meal.strIngredient9,
                    meal.strIngredient10,
                    meal.strIngredient11,
                    meal.strIngredient12,
                    meal.strIngredient13,
                    meal.strIngredient14,
                    meal.strIngredient15,
                    meal.strIngredient16,
                    meal.strIngredient17,
                    meal.strIngredient18,
                    meal.strIngredient19,
                    meal.strIngredient20,
                )
                for (ingredient in ingredients) {
                    if (ingredient != null) {
                        if (ingredient.trim() != "") {
                            Log.d(LOG_TAG, "Ingredient string: $ingredient")
                            ingredientsListTextView.append(ingredient.trim() + "\n")
                        }
                    }
                }
                ingredientsListTextView.text = ingredientsListTextView.text.trimEnd()
                val measures = listOf(
                    meal.strMeasure1,
                    meal.strMeasure2,
                    meal.strMeasure3,
                    meal.strMeasure4,
                    meal.strMeasure5,
                    meal.strMeasure6,
                    meal.strMeasure7,
                    meal.strMeasure8,
                    meal.strMeasure9,
                    meal.strMeasure10,
                    meal.strMeasure11,
                    meal.strMeasure12,
                    meal.strMeasure13,
                    meal.strMeasure14,
                    meal.strMeasure15,
                    meal.strMeasure16,
                    meal.strMeasure17,
                    meal.strMeasure18,
                    meal.strMeasure19,
                    meal.strMeasure20,
                )
                for (measure in measures) {
                    if (measure != null) {
                        if (measure.trim() != "") {
                            Log.d(LOG_TAG, "Measure string: $measure")
                            measuresListTextView.append(measure.trim() + "\n")
                        }
                    }
                }
                measuresListTextView.text = measuresListTextView.text.trimEnd()
            }
        }
    }
}