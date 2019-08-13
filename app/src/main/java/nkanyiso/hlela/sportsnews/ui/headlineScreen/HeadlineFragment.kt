package nkanyiso.hlela.sportsnews.ui.headlineScreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.headline_fragment.view.*
import nkanyiso.hlela.sportsnews.R
import nkanyiso.hlela.sportsnews.data.database.entity.HeadlineEntity


class HeadlineFragment : androidx.fragment.app.Fragment(), HeadlineAdapter.ItemClick {
    override fun setClick(headlineEntity: HeadlineEntity) {
        view?.let {
            var articleUrl =
                headlineEntity.SiteName + "/" + headlineEntity.UrlName + "/news/" + headlineEntity.UrlFriendlyDate + "/" + headlineEntity.UrlFriendlyHeadline + "?format=json"
            val bundle = bundleOf("articleUrl" to articleUrl)
            Navigation.findNavController(it).navigate(R.id.action_headlineFragment_to_headlineDetailFragment2, bundle)
        }
    }


    lateinit var viewModel: HeadlineViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.headline_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(HeadlineViewmodel::class.java)
        viewModel.fetchHeadlines()

        val headllineAdapter = HeadlineAdapter(mutableListOf(), this)

        view.recycler_headlines.apply {
            adapter = headllineAdapter
            layoutManager = LinearLayoutManager(requireContext()) as RecyclerView.LayoutManager?

        }

        viewModel.headlineLiveData.observe(this, object : Observer<MutableList<HeadlineEntity>> {
            override fun onChanged(t: MutableList<HeadlineEntity>?) {
                if (t != null) {

                    if (t.size == 0) {//show  no news
                        view.non_news.visibility = View.VISIBLE
                        view.recycler_headlines.visibility = View.GONE
                    } else {
                        view.non_news.visibility = View.GONE
                        if (view.recycler_headlines.visibility == View.GONE) {
                            view.recycler_headlines.visibility = View.VISIBLE
                        }
                        headllineAdapter.refreshItemsData(t)
                    }
                }
            }
        })
        return view
    }


}
