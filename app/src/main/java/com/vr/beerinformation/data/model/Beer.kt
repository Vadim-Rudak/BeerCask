package com.vr.beerinformation.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Beer() {
    constructor(id : Int,name: String,first_brewed: String,description:String,image_url:String) : this() {
        this.id=id
        this.name=name
        this.first_brewed=first_brewed
        this.description=description
        this.image_url=image_url
    }
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("first_brewed")
    @Expose
    var first_brewed: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("image_url")
    @Expose
    var image_url: String? = null

    override fun toString(): String {
        return "Beer(id=$id, name=$name, first_brewed=$first_brewed, description=$description, image_url=$image_url)"
    }
}