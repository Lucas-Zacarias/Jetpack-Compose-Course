package com.waterme.data

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.waterme.model.Plant
import com.waterme.worker.WaterReminderWorker
import com.waterme.worker.WaterReminderWorker.Companion.nameKey
import java.util.concurrent.TimeUnit

class WorkManagerWaterRepository(context: Context) : WaterRepository {
    private val workManager = WorkManager.getInstance(context)

    override val plants: List<Plant>
        get() = DataSource.plants

    override fun scheduleReminder(duration: Long, unit: TimeUnit, plantName: String) {
        val workName = "$plantName $duration $unit"
        val waterReminderWorker = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInputData(workDataOf(nameKey to plantName))
            .build()

        workManager.enqueueUniqueWork(
            workName,
            ExistingWorkPolicy.REPLACE,
            waterReminderWorker
        )
    }
}