package com.mylivn.data.models.response


data class ComicsResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comics>
)

data class Comics(val id:Int, val title:String, val description :String,val thumbnail:Thumbnail )