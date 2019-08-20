package com.example.klearndemo.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.klearndemo.R;
import com.example.klearndemo.utils.StatusbarUtils;


import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BaseContract.BasePresnter> extends AppCompatActivity implements BaseContract.BaseView {

    private List<Activity> mActivities = new ArrayList<>();
    private T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        configStatusBar();
        createPresenter();
        initViewAndDatas(savedInstanceState);
        fetchData();
    }

    protected void configStatusBar() {
        StatusbarUtils.setColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
    }

    protected abstract void createPresenter();

    protected void fetchData() {
    }

    protected abstract void initViewAndDatas(Bundle savedInstanceState);

    protected abstract int getLayoutId();

    protected void addActivity(Activity activity){
        if(mActivities == null)
            mActivities = new ArrayList<>();
        mActivities.add(activity);
    }

    protected void removeActivity(Activity activity){
        if(mActivities != null)
            mActivities.remove(activity);
    }


    @Override
    public void showState(int state) {
        switch (state){
            case BaseConfig.BaseState.STATE_LOADING:
                break;
            case BaseConfig.BaseState.STATE_API_ERR:
                break;
            case BaseConfig.BaseState.STATE_RESPONSE_DATA_ERR:
                break;
            case BaseConfig.BaseState.STATE_SERVER_HTTP_ERR:
                break;
            case BaseConfig.BaseState.STATE_SERVER_TIMEOUT_ERR:
                break;
            case BaseConfig.BaseState.STATE_UNKNOWN_ERR:
                break;
        }
    }
}
