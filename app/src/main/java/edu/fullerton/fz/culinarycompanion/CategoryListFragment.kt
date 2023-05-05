package edu.fullerton.fz.culinarycompanion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import edu.fullerton.fz.culinarycompanion.api.Category

private const val LOG_TAG = "CategoryListFragment"

val DUMMY_CATEGORY_LIST = listOf<List<Category>>(
    listOf(Category("hello", "Hello", "https://www.themealdb.com/images/category/chicken.png", "bye"), Category("hi", "Hi", "https://www.themealdb.com/images/category/beef.png", "Bye")),
    listOf(Category("hello", "Hello", "https://www.themealdb.com/images/category/chicken.png", "bye"), Category("hi", "Hi", "https://www.themealdb.com/images/category/beef.png", "Bye")),
    listOf(Category("hello", "Hello", "https://www.themealdb.com/images/category/chicken.png", "bye"), Category("hi", "Hi", "https://www.themealdb.com/images/category/beef.png", "Bye")),
    listOf(Category("hello", "Hello", "https://www.themealdb.com/images/category/chicken.png", "bye"), Category("hi", "Hi", "https://www.themealdb.com/images/category/beef.png", "Bye")),
    listOf(Category("hello", "Hello", "https://www.themealdb.com/images/category/chicken.png", "bye"), Category("hi", "Hi", "https://www.themealdb.com/images/category/beef.png", "Bye")),
    listOf(Category("hello", "Hello", "https://www.themealdb.com/images/category/chicken.png", "bye"), Category("hi", "Hi", "https://www.themealdb.com/images/category/beef.png", "Bye")),
    listOf(Category("hello", "Hello", "https://www.themealdb.com/images/category/chicken.png", "bye"), Category("hi", "Hi", "https://www.themealdb.com/images/category/beef.png", "Bye")),
)

val EMPTY_CATEGORY_LIST = listOf<List<Category>>()
class CategoryListFragment : Fragment(){
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryViewModel: CategoryViewModel

    private var adapter: CategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category_list, container, false)
        this.categoryRecyclerView = view.findViewById(R.id.category_recycler_view) as RecyclerView
        this.categoryRecyclerView.layoutManager = LinearLayoutManager(context)

        val categories = categoryViewModel.getCategories()
//        val categories = DUMMY_CATEGORY_LIST
        if (categories != null) {
            Log.d(LOG_TAG, "Received List of Categories. Size = ${categories.size}")
            adapter = CategoryAdapter(categories)
        }
        else {
            Log.d(LOG_TAG, "List of Categories is null")
            adapter = CategoryAdapter(EMPTY_CATEGORY_LIST)
        }
        this.categoryRecyclerView.adapter = adapter

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.categoryViewModel = ViewModelProvider(this.requireActivity())[CategoryViewModel::class.java]
    }

    private inner class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var category1: Category
        private lateinit var category2: Category

        private val category1ImageView: ImageView = this.itemView.findViewById(R.id.category1_image_view)
        private val category2ImageView: ImageView = this.itemView.findViewById(R.id.category2_image_view)
        private val category1NameText: TextView = this.itemView.findViewById(R.id.category1_name_text_view)
        private val category2NameText: TextView = this.itemView.findViewById(R.id.category2_name_text_view)

        fun bind(category1: Category, category2: Category) {
            this.category1 = category1
            this.category2 = category2
            this.category1NameText.text = this.category1.strCategory
            this.category2NameText.text = this.category2.strCategory
            Log.d(LOG_TAG, "Category1 Name loaded: ${category1.strCategory}")
            Log.d(LOG_TAG, "Category2 Name loaded: ${category2.strCategory}")
            Picasso.get().load(category1.strCategoryThumb).into(category1ImageView)
            Picasso.get().load(category2.strCategoryThumb).into(category2ImageView)
            Log.d(LOG_TAG, "Category1 Thumbnail loaded: ${category1.strCategoryThumb}")
            Log.d(LOG_TAG, "Category2 Thumbnail loaded: ${category2.strCategoryThumb}")
        }
    }
    private inner class CategoryAdapter (var categories: List<List<Category>>)
        : RecyclerView.Adapter<CategoryHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
            val view = layoutInflater.inflate(R.layout.list_item_category, parent, false)
            return CategoryHolder(view)
        }

        override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
            val category1 = this.categories[position][0]
            val category2 = this.categories[position][1]
            holder.bind(category1, category2)
        }

        override fun getItemCount() = categories.size
    }

    companion object {
        fun newInstance(): CategoryListFragment {
            return CategoryListFragment()
        }
    }
}
