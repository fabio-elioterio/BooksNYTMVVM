package com.fabio.junior.booksnytmvvm.presentation.books.repository

import com.fabio.junior.booksnytmvvm.data.ApiService
import com.fabio.junior.booksnytmvvm.data.response.BookResponse
import retrofit2.Call

class BookRepository {

    fun getBooks(apiKey: String, listName: String): Call<BookResponse> {
        return ApiService.service.getBooks(apiKey, listName)
    }
}