package com.example.ifunsoedmobile

import android.os.Bundle
import android.telecom.Call
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ifunsoedmobile.databinding.ActivityDaftarBukuBinding
import com.example.ifunsoedmobile.ui.adapter.BookAdapter
import com.example.ifunsoedmobile.viewmodel.MainViewModel
import androidx.activity.viewModels
import com.example.ifunsoedmobile.data.model.BookDoc
import com.example.ifunsoedmobile.ui.adapter.OnBookClickListener
import com.example.ifunsoedmobile.ui.fragment.BookDetailFragment

class DaftarBukuActivity : AppCompatActivity(), OnBookClickListener {

    private lateinit var binding: ActivityDaftarBukuBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = BookAdapter(emptyList(), this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.books.observe(this) { books ->
            adapter.setData(books)
        }

        viewModel.fetchBooks(query = "kotlin programming")
    }

    override fun onBookClick(book: BookDoc) {
        book.let { b->
            BookDetailFragment(
                title = b.title ?: "No Title",
                author = b.authorName?.joinToString(", ") ?: "Unknown Author",
                year = b.firstPublishYear?.toString() ?: "-",
                coverId = b.coverId ?: 0
            ).show(supportFragmentManager,  BookDetailFragment::class.java.simpleName)
        }
    }
}