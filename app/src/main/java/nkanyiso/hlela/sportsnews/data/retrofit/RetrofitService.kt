package nkanyiso.hlela.sportsnews.data.retrofit

import nkanyiso.hlela.sportsnews.data.ArticleModel
import nkanyiso.hlela.sportsnews.data.database.entity.HeadlineEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface RetrofitService {

    @GET("news?format=json")
    fun getCurrencies(): Call<MutableList<HeadlineEntity>>
    @GET()
    fun getArticle(@Url articleUrl: String?): Call<ArticleModel>
}
