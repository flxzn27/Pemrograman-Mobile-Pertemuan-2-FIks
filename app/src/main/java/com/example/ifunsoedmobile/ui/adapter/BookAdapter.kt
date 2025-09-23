import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ifunsoedmobile.data.model.BookDoc

class BookAdapter(private var books: List<BookDoc>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    /**
     * ViewHolder bertanggung jawab untuk memegang referensi ke view
     * untuk satu item dalam daftar.
     */
    inner class BookViewHolder(val binding: ListBukuBinding) :
        RecyclerView.ViewHolder(binding.root)

    /**
     * Dipanggil ketika RecyclerView perlu membuat ViewHolder baru.
     * Fungsi ini "mengembang" (inflate) layout XML untuk satu item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ListBukuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    /**
     * Mengembalikan jumlah total item dalam dataset (list buku).
     */
    override fun getItemCount(): Int = books.size

    /**
     * Dipanggil oleh RecyclerView untuk menampilkan data pada posisi tertentu.
     * Fungsi ini mengambil data buku dari list dan menampilkannya di view
     * yang ada di dalam ViewHolder.
     */
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.binding.tvTitle.text = book.title ?: "No Title"
        holder.binding.tvAuthor.text = book.authorName?.joinToString(separator = ", ") ?: "Unknown Author"
        holder.binding.tvYear.text = book.firstPublishYear?.toString() ?: "-"
    }

    /**
     * Fungsi kustom untuk memperbarui data di dalam adapter
     * dan memberi tahu RecyclerView untuk menggambar ulang daftarnya.
     */
    fun setData(newBooks: List<BookDoc>) {
        books = newBooks
        notifyDataSetChanged()
    }
}