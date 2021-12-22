package com.app.covidtracker.network

import com.app.covidtracker.model.*
import com.app.covidtracker.model.districts.Districts
import com.app.covidtracker.model.states.StatesResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {


    @POST("api/v2/auth/public/generateOTP")
    suspend fun generateOtp(@Body mobile: GenerateOtpRequest):Response<GenerateOtp>

    @POST("api/v2/auth/public/confirmOTP")
    suspend fun confirmOTP(@Body mobile: VerifyRequest):Response<VerifyOtp>

    @GET("api/v2/registration/certificate/public/download")
    suspend fun downloadCertificate(
        @Query("beneficiary_reference_id") id: String,
        @Header("Authorization") token:String
    ):Response<ResponseBody>

    @GET("api/v2/appointment/sessions/public/findByPin")
    suspend fun findSessionByPin(
        @Query("pincode") id: String,
        @Query("date") date: String,
        @Header("Authorization") token:String
    ):Response<VaccinationSessions>

    @GET("api/v2/appointment/sessions/public/findByDistrict")
    suspend fun findSessionByDistrict(
        @Query("district_id") id: String,
        @Query("date") date: String,
        @Header("Authorization") token:String
    ):Response<VaccinationSessions>

    @GET("api/v2/admin/location/states")
    suspend fun getStates(
        @Header("Authorization") token:String
    ):Response<StatesResponse>

    @GET("api/v2/admin/location/districts/{state_id}")
    suspend fun getDistricts(
        @Path("state_id") id: String,
        @Header("Authorization") token:String
    ):Response<Districts>
}