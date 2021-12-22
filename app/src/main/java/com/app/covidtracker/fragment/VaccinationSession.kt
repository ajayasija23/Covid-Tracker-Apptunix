package com.app.covidtracker.fragment

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import com.app.covidtracker.databinding.FragmentVaccinationSessionBinding
import com.app.covidtracker.model.districts.District
import com.app.covidtracker.model.states.State
import com.app.covidtracker.network.Status
import com.app.covidtracker.util.*
import com.app.covidtracker.viewModels.OtherViewModel
import java.util.*

class VaccinationSession:BaseFragment() {
    private var states: List<State>?=null
    private var districts: List<District>?=null
    private lateinit var binding:FragmentVaccinationSessionBinding
    private  var findByPin=true
    private val viewModel: OtherViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentVaccinationSessionBinding.inflate(layoutInflater)
        setupListeners()
        bindObserver()
        return binding.root
    }

    private fun setupListeners() {
        binding.tvFindByDistrict.setOnClickListener {
            findByPin=!findByPin
            if (findByPin){
                binding.etPin.visibility=View.VISIBLE
                binding.tvFindByDistrict.text="Find by district"
                binding.spinnerState.visibility=View.GONE
                binding.spinnerDistrict.visibility=View.GONE
            }else{
                binding.etPin.visibility=View.GONE
                binding.tvFindByDistrict.text="Find by pincode"
                binding.spinnerState.visibility=View.VISIBLE
                binding.spinnerDistrict.visibility=View.VISIBLE
                viewModel.getStates(SharedPrefs.getString("token"))            }
        }
        binding.btnSubmit.setOnClickListener {
            if (findByPin){
                if (binding.etPin.text.toString().length<6){
                    "Enter valid pin".showDialog(requireContext(),null)
                }else{
                    viewModel.findSessionByPin(
                        binding.etPin.text.toString(),
                        Date().format("dd-MM-yyyy"),
                        SharedPrefs.getString("token")
                    )
                }
            }
            else{
                if (binding.spinnerState.selectedItemPosition==0){
                    "Select State".showDialog(requireContext(),null)
                }
                else if (binding.spinnerDistrict.selectedItemPosition==0){
                    "Select District".showDialog(requireContext(),null)
                }
                else{
                    viewModel.findSessionByDistrict(
                        districts!![binding.spinnerDistrict.selectedItemPosition-1].district_id.toString(),
                        Date().format("dd-MM-yyyy"),
                        SharedPrefs.getString("token")
                    )
                }
            }
        }
        binding.spinnerState.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2!=0){
                    viewModel.getDistricts(states!![p2-1].state_id.toString(),SharedPrefs.getString("token"))
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }


    private fun bindObserver() {
        viewModel.sessionResponse.observe(viewLifecycleOwner,{
            when(it.status){
                Status.LOADING->{
                    showProgress()
                }
                Status.SUCCESS->{
                    hideProgress()
                    "Success".showDialog(requireContext(),null)
                }
                Status.ERROR->{
                    hideProgress()
                    toast(it.message.toString())
                }
            }
        })
        viewModel.statesResponse.observe(viewLifecycleOwner,{
            when(it.status){
                Status.LOADING->{
                    showProgress()
                }
                Status.SUCCESS->{
                    hideProgress()
                    states=it.data!!.states
                    binding.spinnerState.adapter=requireContext().createAdapter("Select State",
                        states!!.map { it.state_name }.toMutableList())
                }
                Status.ERROR->{
                    hideProgress()
                    toast(it.message.toString())
                }
            }
        })
        viewModel.districtsResponse.observe(viewLifecycleOwner,{
            when(it.status){
                Status.LOADING->{
                    showProgress()
                }
                Status.SUCCESS->{
                    hideProgress()
                    districts=it.data!!.districts
                    binding.spinnerDistrict.adapter=requireContext().createAdapter("Select District",
                        districts!!.map { it.district_name }.toMutableList())
                }
                Status.ERROR->{
                    hideProgress()
                    toast(it.message.toString())
                }
            }
        })
    }

}