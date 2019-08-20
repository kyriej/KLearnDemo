package com.example.klearndemo.base;

import android.view.View;

public interface BaseContract {

    interface BaseView{
        void showState(int state);
    }

    interface BasePresnter<V extends BaseView>{
       void attachView(V view);
       void detachView();
    }

}
