package com.example.klearndemo.module.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.klearndemo.R;
import com.example.klearndemo.adapter.ItemAdapter;
import com.example.klearndemo.base.BaseActivity;
import com.example.klearndemo.entity.Item;
import com.example.klearndemo.view.KItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private KItemDecoration itemDecoration;
    private List<Item> mDatas;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initViewAndDatas(Bundle savedInstanceState) {
        recyclerView = findViewById(R.id.recyclerview);
        itemDecoration = new KItemDecoration(this,true);
        //layoutManager = new LinearLayoutManager(this);
        //((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
        layoutManager = new GridLayoutManager(this,2);
        mDatas = new ArrayList();
        String coverUrl = "https://p.ssl.qhimg.com/dmfd/130_100_100/t01a00196864aeb9ba7.jpg?size=640x360";
        for(int i = 0; i <= 100 ; i++)
            mDatas.add(new Item("item "+i,coverUrl));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ItemAdapter(mDatas));
        recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview;
    }
}
