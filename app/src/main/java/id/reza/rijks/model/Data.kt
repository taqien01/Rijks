package id.reza.rijks.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Data: Serializable {

    @SerializedName("elapsedMilliseconds")
    val elapsedMilliseconds : Int = 0

    @SerializedName("count")
    val count : Int = 0

    @SerializedName("countFacets")
    val countFacets : CountFacets? = null

    @SerializedName("artObjects")
    val artObjects : List<ArtObjects>? = null

}

class ArtObjects: Serializable{
    @SerializedName("links")
    val links : Links? = null

    @SerializedName("id")
    val id : String = ""

    @SerializedName("objectNumber")
    val objectNumber : String = ""

    @SerializedName("title")
    val title : String = ""

    @SerializedName("hasImage")
    val hasImage : Boolean = true

    @SerializedName("principalOrFirstMaker")
    val principalOrFirstMaker : String = ""

    @SerializedName("longTitle")
    val longTitle : String = ""

    @SerializedName("showImage")
    val showImage : Boolean = true

    @SerializedName("permitDownload")
    val permitDownload : Boolean = true

    @SerializedName("webImage")
    val webImage : DataImage? = null

    @SerializedName("headerImage")
    val headerImage : DataImage? = null

    @SerializedName("productionPlaces")
    val productionPlaces : Array<String>? = null
}

class Links: Serializable{
    @SerializedName("self")
    val self : String = ""

    @SerializedName("web")
    val web : String = ""
}

class DataImage: Serializable{
    @SerializedName("guid")
    val guid : String = ""

    @SerializedName("offsetPercentageX")
    val offsetPercentageX : Int = 0

    @SerializedName("offsetPercentageY")
    val headeoffsetPercentageYrImage : Int = 0

    @SerializedName("width")
    val width : Int = 0

    @SerializedName("height")
    val height : Int = 0

    @SerializedName("url")
    val headerIurlmage : String = ""

}

class CountFacets: Serializable{
    @SerializedName("hasimage")
    val hasimage : Int = 0

    @SerializedName("ondisplay")
    val ondisplay : Int = 0
}