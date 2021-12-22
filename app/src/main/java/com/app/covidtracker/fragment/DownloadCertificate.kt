package com.app.covidtracker.fragment

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.covidtracker.databinding.FragmentDownloadCertificateBinding
import com.app.covidtracker.network.Status
import com.app.covidtracker.util.SharedPrefs
import com.app.covidtracker.util.saveToFile
import com.app.covidtracker.util.showDialog
import com.app.covidtracker.util.toast
import com.app.covidtracker.viewModels.OtherViewModel

class DownloadCertificate:BaseFragment() {

    private lateinit var binding: FragmentDownloadCertificateBinding
    private val viewModel:OtherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDownloadCertificateBinding.inflate(layoutInflater)
        binding.btnDownload.setOnClickListener {
            if (binding.etBanerficiaryId.text.toString().isEmpty()){
                "Enter Baneficiaryid".showDialog(requireContext(),null)
            }
            else{
                viewModel.downloadCertificate(binding.etBanerficiaryId.text.toString(),SharedPrefs.getString("token"))
            }
        }
        bindObserver()
        return binding.root
    }
    private fun bindObserver() {
        viewModel.downloadResponse.observe(viewLifecycleOwner,{
            when(it.status){
                Status.LOADING->{
                    showProgress()
                }
                Status.SUCCESS->{
                    hideProgress()
                    val dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                   it.data?.byteStream()?.saveToFile(dir,"vaccine_certificate.pdf")
                }
                Status.ERROR->{
                    hideProgress()
                    toast(it.message.toString())
                }
            }
        })
    }

}