package nkanyiso.hlela.sportsnews

import nkanyiso.hlela.sportsnews.data.database.NewsDatabase
import nkanyiso.hlela.sportsnews.data.database.NewsDatabase.Companion.getInstance


import android.app.Application
import com.facebook.stetho.Stetho


class MyApplication : Application() {
    public lateinit var appInstance: MyApplication

    companion object {
        var db: NewsDatabase? = null
        fun getDatabase(): NewsDatabase? {
            return db
        }

    }
    override fun onCreate() {
        super.onCreate()
        appInstance = this;
        Stetho.initializeWithDefaults(this)
        db= getInstance(applicationContext)
    }

}