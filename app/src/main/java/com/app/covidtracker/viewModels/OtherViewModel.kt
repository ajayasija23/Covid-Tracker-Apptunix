package com.app.covidtracker.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.covidtracker.model.GenerateOtp
import com.app.covidtracker.model.VaccinationSessions
import com.app.covidtracker.model.VerifyOtp
import com.app.covidtracker.model.districts.Districts
import com.app.covidtracker.model.states.StatesResponse
import com.app.covidtracker.network.Repository
import com.app.covidtracker.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class OtherViewModel: ViewModel() {
    private val repository= Repository()
    val downloadResponse= MutableLiveData<Resource<ResponseBody>>()
    val sessionResponse= MutableLiveData<Resource<VaccinationSessions>>()
    val statesResponse= MutableLiveData<Resource<StatesResponse>>()
    val districtsResponse= MutableLiveData<Resource<Districts>>()
    fun downloadCertificate(id: String,token:String){
        downloadResponse.value= Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.downloadCertificate(id, token)
                withContext(Dispatchers.Main)
                {
                    Log.d("response","$response")
                    if (response != null) {
                        when(response.isSuccessful){
                            true->{
                                downloadResponse.value= Resource.success(response.body(),response.message())
                            }
                            false->{
                                downloadResponse.value= Resource.error(null,response.message())
                            }
                        }
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main)
                {
                    downloadResponse.value = Resource.error(data = null, message = t.localizedMessage)
                }
            }
        }
    }

    fun findSessionByPin(id: String,date:String,token:String){
        sessionResponse.value= Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.findSessionByPin(id, date, token)
                withContext(Dispatchers.Main)
                {
                    Log.d("response","$response")
                    if (response != null) {
                        when(response.isSuccessful){
                            true->{
                                sessionResponse.value= Resource.success(response.body(),response.message())
                            }
                            false->{
                                sessionResponse.value= Resource.error(null,response.message())
                            }
                        }
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main)
                {
                    sessionResponse.value = Resource.error(data = null, message = t.localizedMessage)
                }
            }
        }
    }

    fun findSessionByDistrict(id: String,date:String,token:String){
        sessionResponse.value= Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.findSessionByDistrict(id, date, token)
                withContext(Dispatchers.Main)
                {
                    Log.d("response","$response")
                    if (response != null) {
                        when(response.isSuccessful){
                            true->{
                                sessionResponse.value= Resource.success(response.body(),response.message())
                            }
                            false->{
                                sessionResponse.value= Resource.error(null,response.message())
                            }
                        }
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main)
                {
                    sessionResponse.value = Resource.error(data = null, message = t.localizedMessage)
                }
            }
        }
    }

    fun getStates(token:String){
        statesResponse.value= Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getStates(token)
                withContext(Dispatchers.Main)
                {
                    Log.d("response","$response")
                    if (response != null) {
                        when(response.isSuccessful){
                            true->{
                                statesResponse.value= Resource.success(response.body(),response.message())
                            }
                            false->{
                                statesResponse.value= Resource.error(null,response.message())
                            }
                        }
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main)
                {
                    statesResponse.value = Resource.error(data = null, message = t.localizedMessage)
                }
            }
        }
    }

    fun getDistricts(id: String,token:String){
        districtsResponse.value= Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getDistricts(id,token)
                withContext(Dispatchers.Main)
                {
                    Log.d("response","$response")
                    if (response != null) {
                        when(response.isSuccessful){
                            true->{
                                districtsResponse.value= Resource.success(response.body(),response.message())
                            }
                            false->{
                                districtsResponse.value= Resource.error(null,response.message())
                            }
                        }
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main)
                {
                    districtsResponse.value = Resource.error(data = null, message = t.localizedMessage)
                }
            }
        }
    }

}