package Clases
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Gif : Parcelable {
    var title: String ?= null
    var images: Map<String,GiphyImage>? = null

    inner class GiphyImage{
        var url:String?= null
    }
}
