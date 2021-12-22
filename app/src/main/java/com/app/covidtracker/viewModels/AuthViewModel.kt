package com.app.covidtracker.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.covidtracker.model.GenerateOtp
import com.app.covidtracker.model.GenerateOtpRequest
import com.app.covidtracker.model.VerifyOtp
import com.app.covidtracker.model.VerifyRequest
import com.app.covidtracker.network.Repository
import com.app.covidtracker.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class AuthViewModel:ViewModel() {
    private val repository=Repository()

    val generateOtp= MutableLiveData<Resource<GenerateOtp>>()
    val verifyOtp= MutableLiveData<Resource<VerifyOtp>>()
    val downloadResponse= MutableLiveData<Resource<ResponseBody>>()

    fun generateOtp(phone: GenerateOtpRequest){
        generateOtp.value=Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.generateOtp(phone)
                withContext(Dispatchers.Main)
                {
                    Log.d("response","$response")
                    if (response != null) {
                        when(response.isSuccessful){
                            true->{
                                generateOtp.value=Resource.success(response.body(),response.message())
                            }
                            false->{
                                generateOtp.value=Resource.error(null,response.message())
                            }
                        }
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main)
                {
                    generateOtp.value = Resource.error(data = null, message = t.localizedMessage)
                }
            }
        }
    }

    fun verifyOtp(request: VerifyRequest){
        verifyOtp.value=Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.verifyOtp(request)
                withContext(Dispatchers.Main)
                {
                    Log.d("response","$response")
                    if (response != null) {
                        when(response.isSuccessful){
                            true->{
                                verifyOtp.value=Resource.success(response.body(),response.message())
                            }
                            false->{
                                verifyOtp.value=Resource.error(null,response.message())
                            }
                        }
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main)
                {
                    verifyOtp.value = Resource.error(data = null, message = t.localizedMessage)
                }
            }
        }
    }

}