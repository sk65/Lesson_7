package com.example.lesson_7.model;

import com.example.lesson_7.contract.MainActivityContract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MainActivityModel implements MainActivityContract.Model {

    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private final int NUMBERS_OF_CORES = Runtime.getRuntime().availableProcessors();
    private final long KEEP_ALIVE_TIME = 1;
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

    private void fillLists(MainActivityContract.Presenter presenter) {
        ThreadPoolExecutor executorServices = new ThreadPoolExecutor(
                NUMBERS_OF_CORES,
                NUMBERS_OF_CORES,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                workQueue
        );
        executorServices.execute(new ListInitializer(Lists.ARRAY_LIST_ADD, presenter));
        executorServices.execute(new ListInitializer(Lists.ARRAY_LIST_REM, presenter));
        executorServices.execute(new ListInitializer(Lists.ARRAY_LIST_SER, presenter));

        executorServices.execute(new ListInitializer(Lists.COPY_ON_WRITE_ARRAY_LIST_ADD, presenter));
        executorServices.execute(new ListInitializer(Lists.COPY_ON_WRITE_ARRAY_LIST_REM, presenter));
        executorServices.execute(new ListInitializer(Lists.COPY_ON_WRITE_ARRAY_LIST_SER, presenter));

        executorServices.execute(new ListInitializer(Lists.LINKED_LIST_ADD, presenter));
        executorServices.execute(new ListInitializer(Lists.LINKED_LIST_REM, presenter));
        executorServices.execute(new ListInitializer(Lists.LINKED_LIST_SER, presenter));

        executorServices.shutdown();
    }

    @Override
    public void requestData(MainActivityContract.Presenter presenter) {
        fillLists(presenter);
    }

    public static class ListInitializer extends Thread {
        private List<Integer> list;
        private final Lists enumList;
        private final ThreadCallBack threadCallBack;
        private long latency;

        public ListInitializer(Lists enumList, MainActivityContract.Presenter presenter) {
            this.enumList = enumList;
            threadCallBack = (ThreadCallBack) presenter;
            initLists();
        }

        private void initLists() {
            switch (enumList) {
                case ARRAY_LIST_ADD:
                case ARRAY_LIST_REM:
                case ARRAY_LIST_SER:
                    list = new ArrayList<>();
                    break;
                case LINKED_LIST_ADD:
                case LINKED_LIST_SER:
                case LINKED_LIST_REM:
                    list = new LinkedList<>();
                    break;
                case COPY_ON_WRITE_ARRAY_LIST_ADD:
                case COPY_ON_WRITE_ARRAY_LIST_REM:
                case COPY_ON_WRITE_ARRAY_LIST_SER:
                    list = new CopyOnWriteArrayList<>();
                    break;
            }
        }

        @Override
        public void run() {
            fillList();
            measureTime();
            threadCallBack.setTime(latency, enumList);
        }

        private void fillList() {
            for (int i = 0; i < 100_000; i++) {
                list.add(i);
            }
        }
        private void measureTime() {
            long start;
            long finish;
            switch (enumList) {
                case LINKED_LIST_ADD:
                case COPY_ON_WRITE_ARRAY_LIST_ADD:
                case ARRAY_LIST_ADD:
                    start = System.currentTimeMillis();
                    list.add(50_00);
                    finish = System.currentTimeMillis();
                    latency = finish - start;
                    break;
                case LINKED_LIST_REM:
                case COPY_ON_WRITE_ARRAY_LIST_REM:
                case ARRAY_LIST_REM:
                    start = System.currentTimeMillis();
                    list.remove(50_00);
                    finish = System.currentTimeMillis();
                    latency = finish - start;
                    break;
                case LINKED_LIST_SER:
                case ARRAY_LIST_SER:
                case COPY_ON_WRITE_ARRAY_LIST_SER:
                    start = System.currentTimeMillis();
                    list.contains(50_000);
                    finish = System.currentTimeMillis();
                    latency = finish - start;
                    break;
            }
        }
    }
}
