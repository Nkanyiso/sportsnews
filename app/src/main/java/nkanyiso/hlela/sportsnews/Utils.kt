package nkanyiso.hlela.sportsnews

import java.util.*
import java.text.SimpleDateFormat

object Utils {

    fun convertMilliSeondsToStringDate(ts_milli: String?, formatter: SimpleDateFormat):String{
        var ts=ts_milli?.substringAfter("(")
        ts=ts?.substringBefore(")")
        ts=ts?.substringBefore("+")
        var   ts_unformatted = ts?.toLong()?.let { it1 -> Date(it1) }
        var ts_formatted=formatter.format(ts_unformatted)

        return ts_formatted;
    }
}