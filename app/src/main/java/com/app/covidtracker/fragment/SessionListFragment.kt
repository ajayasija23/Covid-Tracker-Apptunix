package com.app.covidtracker.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.covidtracker.adapter.SessionAdapter
import com.app.covidtracker.databinding.FragmentSessionsBinding
import com.app.covidtracker.model.Session
import com.app.covidtracker.model.VaccinationSessions

class SessionListFragment:BaseFragment() {
    private lateinit var binding:FragmentSessionsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSessionsBinding.inflate(layoutInflater)
        binding.rvSessions.adapter=SessionAdapter(requireContext(),
            arguments?.getParcelable<VaccinationSessions>("parcel")!!.sessions
        )
        return binding.root
    }
}