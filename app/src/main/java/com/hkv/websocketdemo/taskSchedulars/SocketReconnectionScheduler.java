package com.hkv.websocketdemo.taskSchedulars;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.hkv.websocketdemo.DemoApp;
import com.hkv.websocketdemo.ui.common.SocketLiveData;

import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class SocketReconnectionScheduler extends Worker {

    public SocketReconnectionScheduler(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        SocketLiveData socketLiveData = SocketLiveData.get();
        if (!DemoApp.isInForeground()) return Result.failure();
        if (!socketLiveData.isDisconnected() || !socketLiveData.hasObservers())
            return Result.success();
        Timber.d(  "Doing work");
        socketLiveData.connect();
        return Result.success();
    }

    public static void schedule() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED).build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SocketReconnectionScheduler.class)
                .setConstraints(constraints).setInitialDelay(10, TimeUnit.SECONDS).setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .addTag(SocketReconnectionScheduler.class.getSimpleName()).build();
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);
    }

    public static void cancel() {
        WorkManager.getInstance().cancelAllWorkByTag(SocketReconnectionScheduler.class.getSimpleName());
    }
}
