package com.example.lesson_7.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson_7.R;
import com.example.lesson_7.contract.MainActivityContract;
import com.example.lesson_7.model.Lists;
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

    private MainActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
    }

    @Override
    public void initView() {
        setSupportActionBar(findViewById(R.id.toolbar_mainActivity));

        textViewArAdd = findViewById(R.id.textView_activityMain_arAdd);
        textViewArRem = findViewById(R.id.textView_activityMain_arRem);
        textViewArSer = findViewById(R.id.textView_activityMain_arSer);

        textViewLinAdd = findViewById(R.id.textView_activityMain_linAdd);
        textViewLinRem = findViewById(R.id.textView_activityMain_linRem);
        textViewLinSer = findViewById(R.id.textView_activityMain_linSer);

        textViewCopAdd = findViewById(R.id.textView_activityMain_copAdd);
        textViewCopRem = findViewById(R.id.textView_activityMain_copRem);
        textViewCopSer = findViewById(R.id.textView_activityMain_copSer);

    }

    @Override
    public void setTime(String time, Lists enumList) {
        switch (enumList) {
            case LINKED_LIST_ADD:
                runOnUiThread(() -> textViewLinAdd.setText(time));
                break;
            case LINKED_LIST_SER:
                runOnUiThread(() -> textViewLinSer.setText(time));
                break;
            case LINKED_LIST_REM:
                runOnUiThread(() -> textViewLinRem.setText(time));
                break;
            case ARRAY_LIST_REM:
                runOnUiThread(() -> textViewArRem.setText(time));
                break;
            case ARRAY_LIST_SER:
                runOnUiThread(() -> textViewArSer.setText(time));
                break;
            case ARRAY_LIST_ADD:
                runOnUiThread(() -> textViewArAdd.setText(time));
                break;
            case COPY_ON_WRITE_ARRAY_LIST_ADD:
                runOnUiThread(() -> textViewCopAdd.setText(time));
                break;
            case COPY_ON_WRITE_ARRAY_LIST_REM:
                runOnUiThread(() -> textViewCopRem.setText(time));
                break;
            case COPY_ON_WRITE_ARRAY_LIST_SER:
                runOnUiThread(() -> textViewCopSer.setText(time));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.call) {
            presenter.initWorkTimeLists();
        }
        return super.onOptionsItemSelected(item);
    }


}