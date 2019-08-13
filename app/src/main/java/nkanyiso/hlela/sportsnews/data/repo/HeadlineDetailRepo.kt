package nkanyiso.hlela.sportsnews.data.repo

import nkanyiso.hlela.sportsnews.data.ArticleModel
import nkanyiso.hlela.sportsnews.data.retrofit.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HeadlineDetailRepo {


    fun getArticle(articleUrl:String?,callback: (ArticleModel?) -> Unit) {
        val service = RetrofitFactory.makeRetrofitService()
        val call = service.getArticle(articleUrl)
        // return callback(false);
        call.enqueue(object : Callback<ArticleModel> {


            override fun onResponse(call: Call<ArticleModel>, response: Response<ArticleModel>) {
                if (response.code() == 200) {
               //     val mArticleModel = ArticleModel()

                 //   mArticleModel= response.body()?

                    return callback(response.body());
                }
                return callback(null);

            }
            override fun onFailure(call: Call<ArticleModel>, t: Throwable) {
                return callback(null);
            }
        })
    }
}