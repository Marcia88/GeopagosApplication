package com.example.android.geopagosapplication.ui.paymentmethod.view

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.geopagosapplication.R
import com.example.android.geopagosapplication.model.PaymentMethod
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import kotlinx.android.synthetic.main.payment_method_item_layout.view.*


class PaymentMethodAdapter(
    private val paymentMethods: MutableList<PaymentMethod>,
    private val callback: OnPaymentMethodSelected
) : RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder>() {

    class PaymentMethodViewHolder(itemView: View, val callback: OnPaymentMethodSelected) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(paymentMethod: PaymentMethod) {
            itemView.textViewName.text = paymentMethod.name
            itemView.textViewType.text = paymentMethod.paymentTypeId
            setImage(paymentMethod.thumbnail ?: THUMBNAIL_PLACE_HOLDER, itemView.simpleDraweeView)
            itemView.setOnClickListener { callback.onClick(paymentMethod) }
        }

        companion object {
            const val THUMBNAIL_PLACE_HOLDER =
                "https://upload.wikimedia.org/wikipedia/commons/6/6a/Credit-card-1369111.svg"
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PaymentMethodViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.payment_method_item_layout, parent,
                false
            ),
            callback
        )

    override fun getItemCount(): Int = paymentMethods.size

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) =
        holder.bind(paymentMethods[position])

    fun addPaymentMethods(list: List<PaymentMethod>) {
        paymentMethods.addAll(list)
    }

    interface OnPaymentMethodSelected {
        fun onClick(paymentMethod: PaymentMethod)
    }
}