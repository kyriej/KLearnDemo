package com.example.klearndemo.base;

import com.example.klearndemo.net.helper.RetrofitHelper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

public class BaseRxPresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresnter<V> {

    protected CompositeDisposable compositeDisposable;
    protected V mBaseView;
    protected Retrofit mRetrofit = RetrofitHelper.getInstance().getRetrofit();

    protected void addDisposable(Disposable disposable){
        if(compositeDisposable == null)
            compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }

    protected void undispose(){
        if(compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }


    @Override
    public void attachView(V view) {
        this.mBaseView = view;
    }

    @Override
    public void detachView() {
        if(mBaseView != null)
            mBaseView = null;
        undispose();
    }
}
