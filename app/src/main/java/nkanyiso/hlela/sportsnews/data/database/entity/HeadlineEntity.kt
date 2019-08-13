package nkanyiso.hlela.sportsnews.data.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.net.IDN

const val HEADLINE_TABLE_NAME = "headlines"

@Entity(tableName = HEADLINE_TABLE_NAME,indices = [Index("ID",unique = true)])
class HeadlineEntity(
    @PrimaryKey
    val ID: Int,

    val Headline: String?,
    val Blurb: String?,
    val SmallImageName: String?,
    val SmallImageAlt: String?,
    val LargeImageName: String?,
    val LargeImageAlt: String?,
    val ExtraImageName: String?,
    val ExtraImageAlt: String?,
    val ImageUrlLocal: String?,
    val ImageUrlRemote: String?,
    var DateCreated: String?,
    val Category: String?,
    val CategoryDisplayName: String?,
    val CategoryId: Int?,
    val SiteId: Int?,
    val SiteName: String?,
    val Author: String?,
    val Credit: String?,
    val CreditImageUrl: String?,
    val CreditUrl: String?,
    val UrlName: String?,
    val LiveChat: Boolean?,
    val WebOnly: Boolean?,
    val UrlFriendlyHeadline: String?,
    val UrlFriendlyDate: String?,
    val IsMainStory: Boolean?,
    val UpdatedDate: String?,
    val Keywords: String?,
    val Active: Boolean?,
    val ValidFrom: String?,
    val ValidTo: String?,
    val relatedArticles: String?
)
