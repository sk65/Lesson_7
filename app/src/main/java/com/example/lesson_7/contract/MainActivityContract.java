package com.example.lesson_7.contract;


public interface MainActivityContract {
    interface View {
        void initView();

        void setTextViewArAdd(String time);

        void setTextViewArRem(String time);

        void setTextViewArSer(String time);

        void setTextViewLinAdd(String time);

        void setTextViewLinRem(String time);

        void setTextViewLinSer(String time);

        void setTextViewCopAdd(String time);

        void setTextViewCopRem(String time);

        void setTextViewCopSer(String time);

    }

    interface Model {
        void requestData(MainActivityContract.Presenter presenter);
    }

    interface Presenter {
        void onClick(android.view.View view);
    }
}
