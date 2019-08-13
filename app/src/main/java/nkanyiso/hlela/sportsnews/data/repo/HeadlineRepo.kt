package nkanyiso.hlela.sportsnews.data.repo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nkanyiso.hlela.sportsnews.MyApplication
import nkanyiso.hlela.sportsnews.data.database.entity.HeadlineEntity
import nkanyiso.hlela.sportsnews.data.retrofit.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HeadlineRepo {

    val db = MyApplication.getDatabase()


    fun updateHeadlinesLiveData(callback: (MutableList<HeadlineEntity>) -> Unit): Disposable? {
        return db?.provideHeadline()?.getAll()?.subscribeOn(Schedulers.io())
            ?.subscribeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { result ->
                    callback(result)
                },

                Throwable::printStackTrace
            )

    }

    fun getAllHeadlines(callback: (Boolean) -> Unit) {
        val service = RetrofitFactory.makeRetrofitService()
        val call = service.getCurrencies()
        // return callback(false);
        call.enqueue(object : Callback<MutableList<HeadlineEntity>> {


            override fun onResponse(
                call: Call<MutableList<HeadlineEntity>>,
                response: Response<MutableList<HeadlineEntity>>
            ) {
                if (response.code() == 200) {
                    val mHeadlineList = mutableListOf<HeadlineEntity>()

                    response.body()?.onEach {
                        val mHeadline = it;

                        mHeadlineList.add(mHeadline)


                    }

                    val newArr = mHeadlineList.toTypedArray()
                    db?.provideHeadline()?.insertAll(*newArr)
                    return callback(true);
                }
                return callback(false);

            }

            override fun onFailure(call: Call<MutableList<HeadlineEntity>>, t: Throwable) {
                return callback(false);
            }
        })
    }
}