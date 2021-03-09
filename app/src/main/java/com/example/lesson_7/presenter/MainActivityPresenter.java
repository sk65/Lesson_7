package com.example.lesson_7.presenter;

import com.example.lesson_7.contract.MainActivityContract;
import com.example.lesson_7.model.Lists;
import com.example.lesson_7.model.MainActivityModel;
import com.example.lesson_7.model.ThreadCallBack;

public class MainActivityPresenter implements MainActivityContract.Presenter, ThreadCallBack {
    private final MainActivityContract.View view;
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
    public void initWorkTimeLists() {
        model.requestData(this);
    }

    @Override
    public void setTime(long time, Lists list) {
        view.setTime(String.valueOf(time), list);
    }
}
