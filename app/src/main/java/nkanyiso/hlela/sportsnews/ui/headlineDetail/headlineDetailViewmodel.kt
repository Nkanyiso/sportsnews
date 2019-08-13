package nkanyiso.hlela.sportsnews.ui.headlineDetail

import androidx.lifecycle.MutableLiveData
import nkanyiso.hlela.sportsnews.Utils
import nkanyiso.hlela.sportsnews.data.ArticleModel
import nkanyiso.hlela.sportsnews.data.repo.HeadlineDetailRepo
import nkanyiso.hlela.sportsnews.ui.BaseViewModel
import java.text.SimpleDateFormat

open class HeadlineDetailViewmodel : BaseViewModel() {
    var repository: HeadlineDetailRepo = HeadlineDetailRepo();
    val articleLiveData: MutableLiveData<ArticleModel> by lazy {
        MutableLiveData<ArticleModel>()
    }

    fun fetchArticle(articleUrl: String?) {
        repository.getArticle(articleUrl) {
            if (it != null) {
                println("Found article")

            } else {
                println("No article found")
            }
            updateCurrencyLiveData(it)
        }

    }

    fun updateCurrencyLiveData(articleModel: ArticleModel?) {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        articleModel?.DateCreated = Utils.convertMilliSeondsToStringDate(articleModel?.DateCreated, formatter)
        articleLiveData.postValue(articleModel)

    }
}