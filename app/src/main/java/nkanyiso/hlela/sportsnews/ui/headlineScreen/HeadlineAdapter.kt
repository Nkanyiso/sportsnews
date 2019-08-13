package nkanyiso.hlela.sportsnews.ui.headlineScreen


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.headline_item.view.*
import nkanyiso.hlela.sportsnews.R
import nkanyiso.hlela.sportsnews.data.database.entity.HeadlineEntity
import com.bumptech.glide.Glide
import nkanyiso.hlela.sportsnews.Utils.convertMilliSeondsToStringDate
import java.text.SimpleDateFormat


class HeadlineAdapter(private var items: MutableList<HeadlineEntity>,private var itemClick: ItemClick) :
    androidx.recyclerview.widget.RecyclerView.Adapter<HeadlineAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.headline_item, parent, false)

        return ViewHolder(view = view)
    }

    fun refreshItemsData(newItems: MutableList<HeadlineEntity>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {

        val dataItem = items[position]

        viewholder.view.headline.text = dataItem.Headline
        viewholder.view.site_name.text = dataItem.SiteName
        val formatter = SimpleDateFormat("yyyy-dd-MM")
        viewholder.view.date_created.text = convertMilliSeondsToStringDate(dataItem.DateCreated,formatter)
        viewholder.view.blurb.text = dataItem.Blurb


        viewholder.view.setOnClickListener {
            itemClick.setClick(dataItem)
        }
        val imageView =  viewholder.view.media_image

        val currentUrl = dataItem.ImageUrlRemote + dataItem.SmallImageName //"http://images.supersport.com/2019/4/Phil-Foden-190404-Gsm.jpg"

        Glide.with(viewholder.view)
            .load(currentUrl)
           //override(imageWidthPixels, imageHeightPixels)
            .into(imageView)

//        if (dataItem.monitored) viewholder.view.image_view_button.setImageResource(R.drawable.ic_menu_delete) else {
//            viewholder.view.image_view_button.setImageResource(R.drawable.ic_input_add)
//        }


    }

    interface ItemClick {
        fun setClick(headlineEntity: HeadlineEntity)

    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return items[position].ID.toLong()
    }

    inner class ViewHolder(val view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
}