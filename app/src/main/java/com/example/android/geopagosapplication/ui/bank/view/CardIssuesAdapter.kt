package com.example.android.geopagosapplication.ui.bank.view

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.geopagosapplication.R
import com.example.android.geopagosapplication.model.CardIssue
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder

class CardIssuesAdapter(
    private val values: MutableList<CardIssue>,
    private val callBack: OnCardIssueSelected
) : RecyclerView.Adapter<CardIssuesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_bank, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.text = item.name
        item.secure_thumbnail?.let {
            setImage(it, holder.simpleDraweeView)
        }
        holder.itemView.setOnClickListener { callBack.onClick(item) }
    }

    private fun setImage(url: String, simpleDraweeView: SimpleDraweeView) {
        Log.d("URL", url)
        val uri: Uri = Uri.parse(url)

        val request = ImageRequestBuilder
            .newBuilderWithSource(uri)
            .setResizeOptions(ResizeOptions(60, 30))
            .setLocalThumbnailPreviewsEnabled(true).build()

        simpleDraweeView.controller = Fresco.newDraweeControllerBuilder()
            .setOldController(simpleDraweeView.controller)
            .setImageRequest(request)
            .setAutoPlayAnimations(true)
            .build()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.textViewName)
        val simpleDraweeView: SimpleDraweeView = view.findViewById(R.id.simpleDraweeView)
    }

    fun addCardIssues(list: List<CardIssue>) {
        values.addAll(list)
    }

    interface OnCardIssueSelected {
        fun onClick(cardIssue: CardIssue)
    }
}