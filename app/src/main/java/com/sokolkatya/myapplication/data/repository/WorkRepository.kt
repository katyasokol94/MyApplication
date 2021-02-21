package com.sokolkatya.myapplication.data.repository

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import com.sokolkatya.myapplication.data.work_manager.LoadDataWork
import java.util.concurrent.TimeUnit

class WorkRepository {

    private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

    val loadDataRequest = PeriodicWorkRequest
            .Builder(LoadDataWork::class.java, 8, TimeUnit.HOURS)
            .setConstraints(constraints)
            .addTag("PeriodicWorkRequest UpdateWork")
            .build()
}