package com.example.bluromatic


    import androidx.work.CoroutineWorker
    import androidx.work.WorkerParameters
    import android.content.Context
    import com.example.bluromatic.workers.makeStatusNotification

private const val TAG = "BlurWorker"
    class BlurWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
        override suspend fun doWork(): Result {
            makeStatusNotification(
                applicationContext.resources.getString(R.string.blurring_image),
                applicationContext
            )

            return try {
                Result.success()
            } catch (throwable: Throwable) {
                Result.failure()
            }
            TODO("Not yet implemented")
        }
    }
