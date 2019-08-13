package nkanyiso.hlela.sportsnews.ui.headlineScreen

import androidx.lifecycle.MutableLiveData
import nkanyiso.hlela.sportsnews.data.database.entity.HeadlineEntity
import nkanyiso.hlela.sportsnews.data.repo.HeadlineRepo
import nkanyiso.hlela.sportsnews.ui.BaseViewModel

open class HeadlineViewmodel : BaseViewModel() {

    var repository: HeadlineRepo = HeadlineRepo();
    val headlineLiveData: MutableLiveData<MutableList<HeadlineEntity>> by lazy {
        MutableLiveData<MutableList<HeadlineEntity>>()
    }

    fun fetchHeadlines() {
        repository.getAllHeadlines {
            if (it) {
                println("Found headlines")

            } else {
                println("No headlines found")
            }
            updateCurrencyLiveData()
        }

    }

    fun updateCurrencyLiveData() {

        repository.updateHeadlinesLiveData {
            headlineLiveData.postValue(it)
        }
    }

}