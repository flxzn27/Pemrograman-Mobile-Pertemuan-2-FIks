package com.example.ifunsoedmobile

import BookAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ifunsoedmobile.viewmodel.MainViewModel

class DaftarBukuActivity : AppCompatActivity() {

    // Menggunakan View Binding untuk mengakses view secara aman.
    private lateinit var binding: ActivityDaftarBukuBinding

    // Menginisialisasi ViewModel menggunakan delegasi KTX 'by viewModels()'.
    private val viewModel: MainViewModel by viewModels()

    // Membuat instance dari BookAdapter yang sebelumnya dibuat.
    private val adapter = BookAdapter(books = emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout dan set content view menggunakan View Binding.
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur RecyclerView.
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Mengamati perubahan data 'books' (LiveData) dari ViewModel.
        // Setiap kali data berubah, UI akan diperbarui.
        viewModel.books.observe(owner = this) {
            adapter.setData(it)
        }

        // Memanggil fungsi di ViewModel untuk mengambil data buku.
        viewModel.fetchBooks(query = "kotlin programming")
    }
}