package com.example.swifty_component.api

import com.example.swifty_component.models.User
import com.example.swifty_component.models.UserWithId
import com.example.swifty_component.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface Api42 {

    @Headers("Accept: application/json")
    @POST("oauth/token")
    @FormUrlEncoded
    suspend fun getToken(
        @Field("grant_type")
        grantType: String = "authorization_code",
        @Field("client_id")
        clientId: String = Constants.UID,
        @Field("client_secret")
        clientSecret: String = Constants.SECRET,
        @Field("code")
        code: String,
        @Field("redirect_uri")
        redirectUrl: String = Constants.REDIRECT_URL
    ) : Response<AccessToken>

    @GET("v2/users")
    suspend fun getLimitedUserInfoByLogin(
        @Header("Authorization")
        tokenHeader: String?,
        @Query("filter[login]")
        login: String
    ) : Response<Array<UserWithId>>

    @GET("v2/users/{Id}")
    suspend fun getCompleteUserInfoById(
        @Header("Authorization")
        tokenHeader: String?,
        @Path("Id")
        id: String,
    ) : Response<User>
}