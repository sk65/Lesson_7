package com.example.lesson_7.contract;

import com.example.lesson_7.model.Lists;

public interface MainActivityContract {
    interface View {
        void initView();

        void setTime(String time, Lists enumList);
    }

    interface Model {
        void requestData(MainActivityContract.Presenter presenter);
    }

    interface Presenter {
        void initWorkTimeLists();
    }
}
