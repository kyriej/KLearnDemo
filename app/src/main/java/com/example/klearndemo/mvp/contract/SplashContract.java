package com.example.klearndemo.mvp.contract;

import com.example.klearndemo.base.BaseContract;

public interface SplashContract {

    interface IPresenter<V extends IView> extends BaseContract.BasePresnter<V>{

        void showSplashData();
    }

    interface IView extends BaseContract.BaseView{

        void fetchSplashData();
    }

}
