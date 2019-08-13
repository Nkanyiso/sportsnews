package nkanyiso.hlela.sportsnews.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nkanyiso.hlela.sportsnews.data.database.dao.HeadlineDao
import nkanyiso.hlela.sportsnews.data.database.entity.HeadlineEntity




const val DATABASE_VERSION = 1
const val DATABASE_NAME = "news_db"

@Database(
    entities = [
        HeadlineEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun provideHeadline(): HeadlineDao

    companion object {

        @Volatile
        private var instance: NewsDatabase? = null

        //    @Inject
        fun getInstance(context: Context): NewsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): NewsDatabase {
            return Room.databaseBuilder(context, NewsDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }
}