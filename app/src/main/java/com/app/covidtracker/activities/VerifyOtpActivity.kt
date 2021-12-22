package com.app.covidtracker.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.app.covidtracker.util.encode256
import com.app.covidtracker.util.showDialog
import com.app.covidtracker.util.toast
import com.app.covidtracker.databinding.ActivityVerifyOtpBinding
import com.app.covidtracker.model.VerifyRequest
import com.app.covidtracker.network.Status
import com.app.covidtracker.util.SharedPrefs
import com.app.covidtracker.viewModels.AuthViewModel

class VerifyOtpActivity: BaseActivity() {
    private lateinit var binding: ActivityVerifyOtpBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        bindObserver()
    }

    private fun bindObserver() {
        viewModel.verifyOtp.observe(this,{
            when(it.status){
                Status.LOADING->{
                    showProgress()
                }
                Status.SUCCESS->{
                    hideProgress()
                    SharedPrefs.setBoolean("logedin",true)
                    SharedPrefs.setString("token","Bearer ${it.data?.token}")
                    val intent= Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Status.ERROR->{
                    hideProgress()
                    toast(it.message.toString())
                }
            }
        })
    }

    private fun setListeners() {
        binding.btnVerify.setOnClickListener {
            if (binding.otpView.text.toString().length<6){
                "Enter Valid otp".showDialog(this,null)
            }else{
                viewModel.verifyOtp(
                    VerifyRequest(intent?.getStringExtra("tid")!!,binding.otpView.text.toString().encode256())
                )
            }
        }
    }
}