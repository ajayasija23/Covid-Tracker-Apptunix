package com.app.covidtracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VaccinationSessions(
    val sessions: List<Session>
) : Parcelable