package nkanyiso.hlela.sportsnews.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import nkanyiso.hlela.sportsnews.data.database.entity.HEADLINE_TABLE_NAME
import nkanyiso.hlela.sportsnews.data.database.entity.HeadlineEntity

@Dao
interface HeadlineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg headlineEntity: HeadlineEntity)


    @Query("select * from $HEADLINE_TABLE_NAME")
    fun getAll(): Flowable<MutableList<HeadlineEntity>>


}