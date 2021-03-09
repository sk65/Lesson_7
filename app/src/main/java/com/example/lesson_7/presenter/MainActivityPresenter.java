package com.example.lesson_7.presenter;

import android.view.View;

import com.example.lesson_7.contract.MainActivityContract;
import com.example.lesson_7.model.Lists;
import com.example.lesson_7.model.MainActivityModel;
import com.example.lesson_7.model.ThreadCallBack;
import com.example.lesson_7.view.MainActivity;

public class MainActivityPresenter implements MainActivityContract.Presenter, ThreadCallBack {
    private MainActivityContract.View view;
    private MainActivityContract.Model model;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
        initPresenter();
    }

    private void initPresenter() {
        model = new MainActivityModel();
        view.initView();
    }

    @Override
    public void onClick(View view) {
        model.requestData(this);
    }

    @Override
    public void setTime(long time, Lists list) {
        switch (list) {
            case COPY_ON_WRITE_ARRAY_LIST_SER:
                view.setTextViewCopSer(String.valueOf(time));
                break;
            case COPY_ON_WRITE_ARRAY_LIST_REM:
                view.setTextViewCopRem(String.valueOf(time));
                break;
            case COPY_ON_WRITE_ARRAY_LIST_ADD:
                view.setTextViewCopAdd(String.valueOf(time));
                break;
            case ARRAY_LIST_ADD:
                view.setTextViewArAdd(String.valueOf(time));
                break;
            case ARRAY_LIST_REM:
                view.setTextViewArRem(String.valueOf(time));
                break;
            case ARRAY_LIST_SER:
                view.setTextViewArSer(String.valueOf(time));
                break;
            case LINKED_LIST_REM:
                view.setTextViewLinRem(String.valueOf(time));
                break;
            case LINKED_LIST_SER:
                view.setTextViewLinSer(String.valueOf(time));
                break;
            case LINKED_LIST_ADD:
                view.setTextViewLinAdd(String.valueOf(time));
                break;
        }
    }
}
