package com.shaadi.android.assessment_challenge.app.model.globalData.userListting


import com.google.gson.annotations.SerializedName

data class Timezone(@SerializedName("offset")
                    val offset: String = "",
                    @SerializedName("description")
                    val description: String = "")


data class ResultsItem(@SerializedName("nat")
                       val nat: String = "",
                       @SerializedName("gender")
                       val gender: String = "",
                       @SerializedName("phone")
                       val phone: String = "",
                       @SerializedName("dob")
                       val dob: Dob,
                       @SerializedName("name")
                       val name: Name,
                       @SerializedName("location")
                       val location: Location,
                       @SerializedName("id")
                       val id: Id,
                       @SerializedName("cell")
                       val cell: String = "",
                       @SerializedName("email")
                       val email: String = "",
                       @SerializedName("login")
                       val login: Login,
                       @SerializedName("picture")
                       val picture: Picture)


data class Coordinates(@SerializedName("latitude")
                       val latitude: String = "",
                       @SerializedName("longitude")
                       val longitude: String = "")


data class Login(@SerializedName("sha1")
                 val sha1: String = "",
                 @SerializedName("password")
                 val password: String = "",
                 @SerializedName("salt")
                 val salt: String = "",
                 @SerializedName("sha256")
                 val sha256: String = "",
                 @SerializedName("uuid")
                 val uuid: String = "",
                 @SerializedName("username")
                 val username: String = "",
                 @SerializedName("md5")
                 val md: String = "")


data class Info(@SerializedName("seed")
                val seed: String = "",
                @SerializedName("page")
                val page: Int = 0,
                @SerializedName("results")
                val results: Int = 0,
                @SerializedName("version")
                val version: String = "")


data class Name(@SerializedName("last")
                val last: String = "",
                @SerializedName("title")
                val title: String = "",
                @SerializedName("first")
                val first: String = "")


data class Dob(@SerializedName("date")
               val date: String = "",
               @SerializedName("age")
               val age: Int = 0)


data class Picture(@SerializedName("thumbnail")
                   val thumbnail: String = "",
                   @SerializedName("large")
                   val large: String = "",
                   @SerializedName("medium")
                   val medium: String = "")


data class Street(@SerializedName("number")
                  val number: Int = 0,
                  @SerializedName("name")
                  val name: String = "")


data class UserListingResponse(@SerializedName("results")
                               val results: List<ResultsItem>?,
                               @SerializedName("info")
                               val info: Info)


data class Id(@SerializedName("name")
              val name: String = "",
              @SerializedName("value")
              val value: String = "")



data class Location(@SerializedName("country")
                    val country: String = "",
                    @SerializedName("city")
                    val city: String = "",
                    @SerializedName("street")
                    val street: Street,
                    @SerializedName("timezone")
                    val timezone: Timezone,
                    @SerializedName("postcode")
                    val postcode: String = "",
                    @SerializedName("coordinates")
                    val coordinates: Coordinates,
                    @SerializedName("state")
                    val state: String = "")


