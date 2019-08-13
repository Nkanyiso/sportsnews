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
import java.text.SimpleDateFormat
import java.util.*


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


            override fun onResponse(call: Call<MutableList<HeadlineEntity>>, response: Response<MutableList<HeadlineEntity>>) {
                if (response.code() == 200) {
                    val mHeadlineList = mutableListOf<HeadlineEntity>()

                    response.body()?.onEach {
                        //   val mCurrency = HeadlineEntity(name = entry.key, description = entry.value)
                        val mHeadline= it;
                       var DateCreated= mHeadline.DateCreated;


                        DateCreated=DateCreated?.substringAfter("(")
                        DateCreated=DateCreated?.substringBefore(")")
                       var  DateCreatedMilli=DateCreated?.substringBefore("+")

                        val formatter = SimpleDateFormat("yyyy-dd-MM")

                        // Get date and time information in milliseconds
                        val now = System.currentTimeMillis()

                        // Create a calendar object that will convert the date and time value
                        // in milliseconds to date. We use the setTimeInMillis() method of the
                        // Calendar object.
                        val timestamp = DateCreatedMilli?.toLong()?.let { it1 -> Date(it1) }
                        val calendar = Calendar.getInstance()
                        calendar.timeInMillis = now

                        println(now.toString() + " = " + formatter.format(calendar.time))
                    //    mHeadline.DateCreated=formatter.format(timestamp)
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