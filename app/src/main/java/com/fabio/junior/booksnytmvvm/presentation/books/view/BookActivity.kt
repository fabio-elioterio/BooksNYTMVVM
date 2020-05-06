package com.fabio.junior.booksnytmvvm.presentation.books.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fabio.junior.booksnytmvvm.BuildConfig
import com.fabio.junior.booksnytmvvm.R
import com.fabio.junior.booksnytmvvm.presentation.base.BaseActivity
import com.fabio.junior.booksnytmvvm.presentation.books.adapter.BookAdapter
import com.fabio.junior.booksnytmvvm.presentation.books.viewmodel.BookViewModel
import com.fabio.junior.booksnytmvvm.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.toolbar.*

class BookActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        setupToolbar(toolbarBook, R.string.name_toolbar_book_activity, false)

        //ViewModelProviders.of, what means?
        val viewModel: BookViewModel = ViewModelProviders.of(this).get(
            BookViewModel::class.java)

        viewModel.bookLiveData.observe(this, Observer {
         it?.let {books ->
             with(recyclerViewBook) {
                 layoutManager = LinearLayoutManager(this@BookActivity, RecyclerView.VERTICAL, false)
                 setHasFixedSize(true)
                 adapter = BookAdapter(books) {book ->
                     val intent = Intent(this@BookActivity, DetailActivity::class.java)
                     intent.putExtra("STRING_AUTHOR", book.author)
                     intent.putExtra("STRING_DESCRIPTION", book.description)
                     startActivity(intent)
                 }
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

        viewModel.error.observe(this, Observer {
            if(it) {
                Toast.makeText(this@BookActivity, "Falha no carregamento. Tente mais tarde.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getBook(BuildConfig.API_KEY,
            LIST_TYPE
        )
    }

    companion object {
        private const val LIST_TYPE = "hardcover-fiction"
    }
}
