package com.app.covidtracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Session(
    val address: String,
    val address_l: String,
    val available_capacity: Int,
    val available_capacity_dose1: Int,
    val available_capacity_dose2: Int,
    val block_name: String,
    val block_name_l: String,
    val center_id: Int,
    val date: String,
    val district_name: String,
    val district_name_l: String,
    val fee: String,
    val fee_type: String,
    val from: String,
    val lat: Double,
    val long: Double,
    val min_age_limit: Int,
    val name: String,
    val name_l: String,
    val pincode: String,
    val session_id: String,
    val slots: List<String>,
    val state_name: String,
    val state_name_l: String,
    val to: String,
    val vaccine: String,
    val walkin_ind: String
) : Parcelable