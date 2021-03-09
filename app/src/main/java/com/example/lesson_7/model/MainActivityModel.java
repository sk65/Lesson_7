package com.example.lesson_7.model;

import com.example.lesson_7.contract.MainActivityContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.lesson_7.model.Lists.ARRAY_LIST_ADD;
import static com.example.lesson_7.model.Lists.ARRAY_LIST_REM;
import static com.example.lesson_7.model.Lists.ARRAY_LIST_SER;
import static com.example.lesson_7.model.Lists.COPY_ON_WRITE_ARRAY_LIST_ADD;
import static com.example.lesson_7.model.Lists.COPY_ON_WRITE_ARRAY_LIST_REM;
import static com.example.lesson_7.model.Lists.COPY_ON_WRITE_ARRAY_LIST_SER;
import static com.example.lesson_7.model.Lists.LINKED_LIST_ADD;
import static com.example.lesson_7.model.Lists.LINKED_LIST_REM;
import static com.example.lesson_7.model.Lists.LINKED_LIST_SER;

public class MainActivityModel implements MainActivityContract.Model {

    private void fillLists(MainActivityContract.Presenter presenter) {
        ThreadCallBack threadCallBack = (ThreadCallBack) presenter;
        ExecutorService executorServices = Executors.newFixedThreadPool(9);

        executorServices.execute(() -> {
            List<Byte> list = new ArrayList<>(Collections.nCopies(1000000, (byte) 0));
            threadCallBack.setTime(measureTime(ARRAY_LIST_ADD, list), ARRAY_LIST_ADD);
        });
        executorServices.execute(() -> {
            List<Byte> list = new ArrayList<>(Collections.nCopies(1000000, (byte) 0));
            threadCallBack.setTime(measureTime(ARRAY_LIST_REM, list), ARRAY_LIST_REM);
        });
        executorServices.execute(() -> {
            List<Byte> list = new ArrayList<>(Collections.nCopies(1000000, (byte) 0));
            list.add(list.get(list.size() / 2), (byte) 100);
            threadCallBack.setTime(measureTime(ARRAY_LIST_SER, list), ARRAY_LIST_SER);
        });
        executorServices.execute(() -> {
            List<Byte> list = new CopyOnWriteArrayList<>(Collections.nCopies(1000000, (byte) 0));
            threadCallBack.setTime(measureTime(COPY_ON_WRITE_ARRAY_LIST_ADD, list), COPY_ON_WRITE_ARRAY_LIST_ADD);
        });
        executorServices.execute(() -> {
            List<Byte> list = new CopyOnWriteArrayList<>(Collections.nCopies(1000000, (byte) 0));
            threadCallBack.setTime(measureTime(COPY_ON_WRITE_ARRAY_LIST_REM, list), COPY_ON_WRITE_ARRAY_LIST_REM);
        });
        executorServices.execute(() -> {
            List<Byte> list = new CopyOnWriteArrayList<>(Collections.nCopies(1000000, (byte) 0));
            list.add(list.get(list.size() / 2), (byte) 100);
            threadCallBack.setTime(measureTime(COPY_ON_WRITE_ARRAY_LIST_SER, list), COPY_ON_WRITE_ARRAY_LIST_SER);
        });
        executorServices.execute(() -> {
            List<Byte> list = new LinkedList<>(Collections.nCopies(1000000, (byte) 0));
            threadCallBack.setTime(measureTime(LINKED_LIST_ADD, list), LINKED_LIST_ADD);
        });
        executorServices.execute(() -> {
            List<Byte> list = new LinkedList<>(Collections.nCopies(1000000, (byte) 0));
            threadCallBack.setTime(measureTime(LINKED_LIST_REM, list), LINKED_LIST_REM);
        });
        executorServices.execute(() -> {
            List<Byte> list = new LinkedList<>(Collections.nCopies(1000000, (byte) 0));
            list.add(list.get(list.size() / 2), (byte) 100);
            threadCallBack.setTime(measureTime(LINKED_LIST_SER, list), LINKED_LIST_SER);
        });

        executorServices.shutdown();
    }

    @Override
    public void requestData(MainActivityContract.Presenter presenter) {
        fillLists(presenter);
    }

    private long measureTime(Lists enumList, List<Byte> list) {
        long start;
        long finish;
        switch (enumList) {
            case LINKED_LIST_ADD:
            case COPY_ON_WRITE_ARRAY_LIST_ADD:
            case ARRAY_LIST_ADD:
                start = System.currentTimeMillis();
                list.add((byte) 100);
                finish = System.currentTimeMillis();
                return finish - start;
            case LINKED_LIST_REM:
            case COPY_ON_WRITE_ARRAY_LIST_REM:
            case ARRAY_LIST_REM:
                start = System.currentTimeMillis();
                list.remove(list.size() / 2);
                finish = System.currentTimeMillis();
                return finish - start;
            case LINKED_LIST_SER:
            case ARRAY_LIST_SER:
            case COPY_ON_WRITE_ARRAY_LIST_SER:
                start = System.currentTimeMillis();
                list.contains((byte) 100);
                finish = System.currentTimeMillis();
                return finish - start;
        }
        return -1;
    }

}
