package com.hkv.websocketdemo.ui.main;

import androidx.lifecycle.ViewModel;

import com.hkv.websocketdemo.ui.common.SocketLiveData;

public class MainViewModel extends ViewModel {

    private SocketLiveData socketLiveData;

    public MainViewModel() {
        socketLiveData = SocketLiveData.get();
    }

    public SocketLiveData getSocketLiveData() {
        return socketLiveData;
    }
}
