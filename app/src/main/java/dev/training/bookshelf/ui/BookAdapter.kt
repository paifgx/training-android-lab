package dev.training.bookshelf.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.training.bookshelf.databinding.ItemBookBinding
import dev.training.bookshelf.model.Book
import dev.training.bookshelf.util.formatAuthors

class BookAdapter : ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookViewHolder(
        private val binding: ItemBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.textViewTitle.text = book.title
            binding.textViewAuthors.text = book.authors.formatAuthors()
            binding.textViewYear.text = book.publishedDate ?: ""

            if (book.thumbnailUrl != null) {
                binding.imageViewCover.load(book.thumbnailUrl) {
                    crossfade(true)
                }
            }
        }
    }
}

private object BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
        oldItem == newItem
}
