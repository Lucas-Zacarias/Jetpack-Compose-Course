package com.blur_o_matic.data

import android.content.Context
import android.net.Uri
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.blur_o_matic.IMAGE_MANIPULATION_WORK_NAME
import com.blur_o_matic.KEY_BLUR_LEVEL
import com.blur_o_matic.KEY_IMAGE_URI
import com.blur_o_matic.TAG_OUTPUT
import com.blur_o_matic.getImageUri
import com.blur_o_matic.workers.BlurWorker
import com.blur_o_matic.workers.CleanupWorker
import com.blur_o_matic.workers.SaveImageToFileWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class WorkManagerBluromaticRepository(context: Context) : BluromaticRepository {
    private var imageUri: Uri = context.getImageUri()
    private val workManager = WorkManager.getInstance(context)

    override val outputWorkInfo: Flow<WorkInfo> =
        workManager.getWorkInfosByTagFlow(TAG_OUTPUT).mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }

    /**
     * Create the WorkRequests to apply the blur and save the resulting image
     * @param blurLevel The amount to blur the image
     */
    override fun applyBlur(blurLevel: Int) {
        // var continuation = workManager.beginWith(OneTimeWorkRequest.from(CleanupWorker::class.java))

        var continuation = workManager
            .beginUniqueWork(
                IMAGE_MANIPULATION_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(CleanupWorker::class.java)
            )

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()


        // Create WorkRequest to blur the image
        val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()
            .setInputData(createInputDataForWorkRequest(blurLevel = blurLevel, imageUri = imageUri))

        blurBuilder.setConstraints(constraints)


        continuation = continuation.then(blurBuilder.build())

        val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
            .addTag(TAG_OUTPUT)
            .build()

        continuation = continuation.then(save)

        // Actually start the work
        continuation.enqueue()

//        // Start the work
//        workManager.enqueue(blurBuilder.build())
    }

    /**
     * Cancel any ongoing WorkRequests
     * */
    override fun cancelWork() {
        workManager.cancelUniqueWork(IMAGE_MANIPULATION_WORK_NAME)
    }

    /**
     * Creates the input data bundle which includes the blur level to
     * update the amount of blur to be applied and the Uri to operate on
     * @return Data which contains the Image Uri as a String and blur level as an Integer
     */
    private fun createInputDataForWorkRequest(blurLevel: Int, imageUri: Uri): Data {
        val builder = Data.Builder()
        builder.putString(KEY_IMAGE_URI, imageUri.toString()).putInt(KEY_BLUR_LEVEL, blurLevel)
        return builder.build()
    }
}