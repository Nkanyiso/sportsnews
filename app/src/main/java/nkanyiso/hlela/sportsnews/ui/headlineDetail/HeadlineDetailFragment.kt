package nkanyiso.hlela.sportsnews.ui.headlineDetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_headline_detail.view.*
import kotlinx.android.synthetic.main.headline_fragment.view.*
import nkanyiso.hlela.sportsnews.R
import nkanyiso.hlela.sportsnews.data.ArticleModel
import nkanyiso.hlela.sportsnews.data.database.entity.HeadlineEntity


class HeadlineDetailFragment : androidx.fragment.app.Fragment() {

    lateinit var viewModel: HeadlineDetailViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     //   requireActivity().actionBar.title="Headline Details"
      //  this.activity!!.title = getString(R.string.lbl_artictle)
       var articleUrl = arguments?.getString("articleUrl")
        val view =  inflater.inflate(R.layout.fragment_headline_detail, container, false)
        viewModel = ViewModelProviders.of(this).get(HeadlineDetailViewmodel::class.java)
        viewModel.fetchArticle(articleUrl)

        viewModel.articleLiveData.observe(this, object : Observer<ArticleModel> {
            override fun onChanged(t: ArticleModel?) {
                if (t != null) {

//                        view.articleContent.visibility = View.VISIBLE
                        view.lbl_headline.text=t.Headline
                        view.lbl_date.text=t.DateCreated
                        view.noArticle.visibility = View.GONE
                    } else {
//                        view.articleContent.visibility = View.GONE
                        if (view.noArticle.visibility == View.GONE) {
                            view.noArticle.visibility = View.VISIBLE
                        }
                    }

            }
        })
        return view
    }


}
