package com.fabio.junior.booksnytmvvm.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fabio.junior.booksnytmvvm.BuildConfig
import com.fabio.junior.booksnytmvvm.R
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        //ViewModelProviders.of, what means?
        val viewModel: BookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        viewModel.bookLiveData.observe(this, Observer {
         it?.let {books ->
             with(recyclerViewBook) {
                 layoutManager = LinearLayoutManager(this@BookActivity, RecyclerView.VERTICAL, false)
                 setHasFixedSize(true)
                 adapter = BookAdapter(books)
             }
         }
        })

        viewModel.isLoading.observe(this, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        viewModel.getBook(BuildConfig.API_KEY, LIST_TYPE)
    }

    companion object {
        private const val LIST_TYPE = "hardcover-fiction"
    }
}
