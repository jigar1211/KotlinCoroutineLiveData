package eventown.com.eventown.view.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import eventown.com.eventown.R
import eventown.com.eventown.model.ApiResponseModel

class EventPhotoAdapter(
    val activity: Activity, val apiResponseModel:
    MutableList<ApiResponseModel>
) : RecyclerView.Adapter<EventPhotoAdapter.EventPhotoViewHolder>() {
    inner class EventPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivEventPhoto: ImageView = itemView.findViewById(R.id.ivEventPhoto)
        fun bind(item: ApiResponseModel) {

            Picasso.with(activity).load(item.image).into(ivEventPhoto)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EventPhotoAdapter.EventPhotoViewHolder {
        val v = LayoutInflater.from(p0.context)
            .inflate(R.layout.row_event_list, p0, false)

        return EventPhotoViewHolder(v)

    }

    override fun getItemCount(): Int {
        return apiResponseModel.size
    }

    override fun onBindViewHolder(holder: EventPhotoAdapter.EventPhotoViewHolder, position: Int) {

        val item = apiResponseModel[position]
        holder.bind(item)


    }
}