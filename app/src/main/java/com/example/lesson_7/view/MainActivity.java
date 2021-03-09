package com.example.lesson_7.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson_7.R;
import com.example.lesson_7.contract.MainActivityContract;
import com.example.lesson_7.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    private TextView textViewArAdd;
    private TextView textViewArRem;
    private TextView textViewArSer;
    private TextView textViewLinAdd;
    private TextView textViewLinRem;
    private TextView textViewLinSer;
    private TextView textViewCopAdd;
    private TextView textViewCopRem;
    private TextView textViewCopSer;


    private Button button;
    private MainActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
    }

    @Override
    public void initView() {
        textViewArAdd = findViewById(R.id.textView_activityMain_arAdd);
        textViewArRem = findViewById(R.id.textViev_activityMain_arRem);
        textViewArSer = findViewById(R.id.textView_activityMain_arSer);

        textViewLinAdd = findViewById(R.id.textView_activityMain_linAdd);
        textViewLinRem = findViewById(R.id.textView_activityMain_linRem);
        textViewLinSer = findViewById(R.id.textView_activityMain_linSer);

        textViewCopAdd = findViewById(R.id.textView_activityMain_copAdd);
        textViewCopRem = findViewById(R.id.textView_activityMain_copRem);
        textViewCopSer = findViewById(R.id.textView_activityMain_copSer);

        button = findViewById(R.id.button_mainActivity);
        button.setOnClickListener((v) -> presenter.onClick(v));
    }

    @Override
    public void setTextViewArAdd(String time) {
        runOnUiThread(() -> textViewArAdd.setText(time));
    }

    @Override
    public void setTextViewArRem(String time) {
        runOnUiThread(() -> textViewArRem.setText(time));
    }

    @Override
    public void setTextViewArSer(String time) {
        runOnUiThread(() -> textViewArSer.setText(time));
    }

    @Override
    public void setTextViewLinAdd(String time) {
        runOnUiThread(() -> textViewLinAdd.setText(time));
    }

    @Override
    public void setTextViewLinRem(String time) {
        runOnUiThread(() -> textViewLinRem.setText(time));
    }

    @Override
    public void setTextViewLinSer(String time) {
        runOnUiThread(() -> textViewLinSer.setText(time));
    }

    @Override
    public void setTextViewCopAdd(String time) {
        runOnUiThread(() -> textViewCopAdd.setText(time));
    }

    @Override
    public void setTextViewCopRem(String time) {
        runOnUiThread(() -> textViewCopRem.setText(time));
    }

    @Override
    public void setTextViewCopSer(String time) {
        runOnUiThread(() -> textViewCopSer.setText(time));
    }


}