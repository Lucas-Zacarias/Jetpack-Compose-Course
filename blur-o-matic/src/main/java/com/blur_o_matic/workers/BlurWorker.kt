package com.blur_o_matic.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.blur_o_matic.DELAY_TIME_MILLIS
import com.blur_o_matic.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val TAG = "BlurWorker"

class BlurWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {
        makeStatusNotification(
            applicationContext.resources.getString(R.string.blurring_image),
            applicationContext
        )

        return withContext(Dispatchers.IO) {
            // This is an utility function added to emulate slower work.
            delay(DELAY_TIME_MILLIS)

            return@withContext try {
                val picture = BitmapFactory.decodeResource(
                    applicationContext.resources,
                    R.drawable.android_cupcake
                )
                val output = blurBitmap(bitmap = picture, blurLevel = 1)
                // Write bitmap to a temp file
                val outputUri = writeBitmapToFile(applicationContext, output)

                makeStatusNotification(
                    "Output is $outputUri",
                    applicationContext
                )

                Result.success()
            } catch(throwable: Throwable) {
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_applying_blur),
                    throwable
                )
                Result.failure()
            }
        }
    }

}