package org.codejudge.application.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.codejudge.application.R
import org.codejudge.application.repository.data.Results

class MovieItemAdapter(private var dataSet: MutableList<Results>, private var mContext: Context) :
    RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {
    private var onNoteListener: OnMovieClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieImage: ImageView = view.findViewById(R.id.ivMovieImage)
        val movieName: TextView = view.findViewById(R.id.tvMovieName)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_movie_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.movieName.text = dataSet[position].display_title
        Glide.with(mContext)
            .load(dataSet[position].multimedia.src)
            .apply(
                RequestOptions().override(
                    dataSet[position].multimedia.width,
                    dataSet[position].multimedia.height
                )
            )
            .into(viewHolder.movieImage)
        viewHolder.itemView.setOnClickListener {
            onNoteListener?.onMovieClick(position)
        }
    }
    override fun getItemCount() = dataSet.size

    fun setDataSource(items: MutableList<Results>) {
        this.dataSet = items;
        notifyDataSetChanged()
    }

    fun setClickListener(onNoteListener: OnMovieClickListener) {
        this.onNoteListener = onNoteListener;
    }
}
interface OnMovieClickListener {
    fun onMovieClick(position: Int)
}
