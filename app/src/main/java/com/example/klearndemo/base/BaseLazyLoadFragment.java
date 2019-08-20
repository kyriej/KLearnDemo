package com.example.klearndemo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseLazyLoadFragment<T extends BaseContract.BasePresnter> extends Fragment implements BaseContract.BaseView{

    protected boolean isViewPrepared = false;
    protected boolean isViewVisible = false;
    protected T mPresenter;
    private Unbinder unBinder;
    private Activity mActivity;
    private Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        this.mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        unBinder = ButterKnife.bind(this,view);
        return view;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        createPresenter();
        initViewAndDatas(savedInstanceState);
        lazyLoad();
    }

    protected abstract void createPresenter();

    protected abstract void initViewAndDatas(Bundle bundle);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint())
            doOnViewVisibleLogic();
        else
            doOnViewInvisibleLogic();
    }

    private void doOnViewInvisibleLogic() {
        isViewVisible = false;
    }

    private void doOnViewVisibleLogic() {
        isViewVisible = true;
        lazyLoad();
    }

    private void lazyLoad() {
        if(isViewVisible && isViewPrepared){
            lazyLoadData();
        }else{

        }
    }

    protected abstract void lazyLoadData();

    protected Activity getFragmentActivity(){
        if(mActivity == null)
            mActivity = getActivity();
        return mActivity;
    }



}
