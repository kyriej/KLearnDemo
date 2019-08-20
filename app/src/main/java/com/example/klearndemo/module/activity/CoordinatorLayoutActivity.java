package com.example.klearndemo.module.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.klearndemo.R;
import com.example.klearndemo.base.BaseActivity;
import com.google.android.material.snackbar.Snackbar;

public class CoordinatorLayoutActivity extends BaseActivity {

    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initViewAndDatas(Bundle savedInstanceState) {
        relativeLayout = findViewById(R.id.relative_layout);
        linearLayout = findViewById(R.id.linear_layout);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_test;
    }

    public void onXClick(View view){
        Snackbar.make(linearLayout,"23333333",Snackbar.LENGTH_SHORT).show();
    }
}
