package com.app.covidtracker.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.app.covidtracker.util.toast
import com.app.covidtracker.databinding.ActivityPhoneBinding
import com.app.covidtracker.model.GenerateOtpRequest
import com.app.covidtracker.network.Status
import com.app.covidtracker.util.SharedPrefs
import com.app.covidtracker.viewModels.AuthViewModel

class PhoneActivity : BaseActivity() {
    private lateinit var binding: ActivityPhoneBinding
    private val viewModel:AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPhoneBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreate: ${SharedPrefs.getString("token")}")
        if(SharedPrefs.getBoolean("logedin")){
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(binding.root)
        setListeners()
        bindObserver()
    }

    private fun bindObserver() {
        viewModel.generateOtp.observe(this,{
            when(it.status){
                Status.LOADING->{
                    showProgress()
                }
                Status.SUCCESS->{
                    hideProgress()
                    val intent= Intent(this, VerifyOtpActivity::class.java)
                    intent.putExtra("tid",it.data!!.txnId)
                    startActivity(intent)
                }
                Status.ERROR->{
                    hideProgress()
                   toast(it.message.toString())
                }
            }
        })

    }

    private fun setListeners() {
        binding.btnNext.setOnClickListener {
            if (binding.etPhone.text.toString().length<10){
                binding.etPhone.error="Enter valid mobile number"
            }else{

                viewModel.generateOtp(GenerateOtpRequest(binding.etPhone.text.toString()))
            }
        }
    }
}