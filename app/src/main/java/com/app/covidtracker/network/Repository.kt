package com.app.covidtracker.network

import com.app.covidtracker.model.*
import com.app.covidtracker.model.districts.Districts
import com.app.covidtracker.model.states.StatesResponse
import okhttp3.ResponseBody
import retrofit2.Response

class Repository {
    val baseUrl="https://cdn-api.co-vin.in/"
    val api=ApiClient.getClient(baseUrl)?.create(ApiInterface::class.java)

    suspend fun generateOtp(mobile: GenerateOtpRequest) : Response<GenerateOtp>? {
        return api!!.generateOtp(mobile)
    }
    suspend fun verifyOtp(request: VerifyRequest) : Response<VerifyOtp>? {
        return api!!.confirmOTP(request)
    }
    suspend fun downloadCertificate(id: String,token:String) : Response<ResponseBody>? {
        return api!!.downloadCertificate(id, token)
    }
    suspend fun findSessionByPin(id: String,date:String,token:String) : Response<VaccinationSessions>? {
        return api!!.findSessionByPin(id, date, token)
    }
    suspend fun findSessionByDistrict(id: String,date:String,token:String) : Response<VaccinationSessions>? {
        return api!!.findSessionByDistrict(id, date, token)
    }
    suspend fun getStates(token:String) : Response<StatesResponse>? {
        return api!!.getStates(token)
    }

    suspend fun getDistricts(id:String,token:String) : Response<Districts>? {
        return api!!.getDistricts(id, token)
    }


}