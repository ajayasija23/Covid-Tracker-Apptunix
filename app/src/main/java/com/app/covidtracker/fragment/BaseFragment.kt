package com.app.covidtracker.fragment

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import com.app.covidtracker.databinding.FragmentDashboardBinding
import com.app.covidtracker.databinding.LayoutProgressbarBinding

open class BaseFragment:Fragment() {
    var dialog: Dialog? = null
    private var binding: LayoutProgressbarBinding? = null

    fun showProgress() {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = LayoutProgressbarBinding.inflate(
            layoutInflater
        )
        val view: View = binding!!.root
        dialog!!.window!!.setBackgroundDrawableResource(R.color.transparent)
        dialog!!.setContentView(view)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    fun hideProgress() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
            dialog = null
        }
    }

    private fun dismiss(builder: AlertDialog.Builder) {
        builder.create().dismiss()
    }
}