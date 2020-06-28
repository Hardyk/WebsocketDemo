package com.hkv.websocketdemo.ui.main;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hkv.websocketdemo.R;
import com.hkv.websocketdemo.databinding.FragmentMainBinding;
import com.hkv.websocketdemo.models.pojo.SocketEventModel;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private MessagesAdapter messageAdapter;
    private LinearLayoutManager linearLayoutManager;

    private MainViewModel mViewModel;
    private FragmentMainBinding mBinding;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mBinding.recyclerView.setLayoutManager(linearLayoutManager = new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(messageAdapter = new MessagesAdapter(getContext()));
        linearLayoutManager.setStackFromEnd(true);
        mViewModel.getSocketLiveData().observe(this, socketEventModelObserver);
        mViewModel.getSocketLiveData().connect();
        mBinding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String text = mBinding.contentText.getText().toString().trim();
        if (TextUtils.isEmpty(text)) return;
        mBinding.contentText.setText("");
        mViewModel.getSocketLiveData().sendEvent(
                new SocketEventModel(SocketEventModel.EVENT_MESSAGE, text)
                        .setType(SocketEventModel.TYPE_OUTGOING)
        );
    }

    private Observer<SocketEventModel> socketEventModelObserver = new Observer<SocketEventModel>() {
        @Override
        public void onChanged(SocketEventModel socketEventModel) {
            Timber.d( "New socket event: %s", socketEventModel.toString());
            int index = messageAdapter.addToList(socketEventModel);
            linearLayoutManager.scrollToPosition(index);
        }
    };

}
