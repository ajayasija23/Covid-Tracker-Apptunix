package com.app.covidtracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.app.covidtracker.R
import com.app.covidtracker.databinding.FragmentDashboardBinding

class DashboardFragment:BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDashboardBinding.inflate(layoutInflater)
        setupListeners()
        return binding.root
    }

    private fun setupListeners() {
        binding.downloadCertificate.setOnClickListener(this)
        binding.cardVaccinationSession.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0){
            binding.downloadCertificate->{
                findNavController().navigate(R.id.action_dashboardFragment_to_downloadCertificate2)
            }
            binding.cardVaccinationSession->{
                findNavController().navigate(R.id.action_dashboardFragment_to_vaccinationSession)
            }
        }
    }
}