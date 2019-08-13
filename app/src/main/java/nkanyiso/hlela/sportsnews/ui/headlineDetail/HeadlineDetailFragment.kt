package nkanyiso.hlela.sportsnews.ui.headlineDetail


import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_headline_detail.view.*
import nkanyiso.hlela.sportsnews.R
import nkanyiso.hlela.sportsnews.data.ArticleModel


class HeadlineDetailFragment : androidx.fragment.app.Fragment() {

    lateinit var viewModel: HeadlineDetailViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var articleUrl = arguments?.getString("articleUrl")
        val view = inflater.inflate(R.layout.fragment_headline_detail, container, false)
        viewModel = ViewModelProviders.of(this).get(HeadlineDetailViewmodel::class.java)
        viewModel.fetchArticle(articleUrl)

        viewModel.articleLiveData.observe(this, object : Observer<ArticleModel> {
            override fun onChanged(t: ArticleModel?) {
                if (t != null) {

                    view.article_group.visibility = View.VISIBLE
                    view.dt_headline.text = t.Headline
                    view.dt_date.text = t.DateCreated
                    view.dt_article_body.text = Html.fromHtml(t.Body)
                    val imageView = view.dt_article_image
                    imageView.contentDescription = t.LargeImageAlt
                    val currentUrl = t.ImageUrlLocal + t.LargeImageName

                    Glide.with(view)
                        .load(currentUrl)
                        .into(imageView)
                    view.dt_no_article.visibility = View.GONE
                } else {
                    view.article_group.visibility = View.GONE
                    if (view.dt_no_article.visibility == View.GONE) {
                        view.dt_no_article.visibility = View.VISIBLE
                    }
                }

            }
        })
        return view
    }


}
