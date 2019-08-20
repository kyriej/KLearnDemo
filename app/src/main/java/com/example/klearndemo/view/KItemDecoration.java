package com.example.klearndemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.klearndemo.R;
import com.example.klearndemo.utils.DensityUtils;

public class KItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = KItemDecoration.class.getSimpleName();
    private Paint mPaint;
    private Context mContext;
    private int mDividerHeight;
    private boolean isDrawVertical = false;
    private boolean isDrawHorizontal = false;
    private boolean isDrawGrid = false;

    public KItemDecoration(Context context,boolean isGrid) {
        mContext = context;
        mPaint = new Paint();
        isDrawGrid = isGrid;
        //mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setColor(mContext.getResources().getColor(R.color.colorPrimary));
        mDividerHeight = DensityUtils.dip2px(mContext,15);

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

//        if(isDrawGrid){
//            drawGrid(c,parent);
//            return;
//        }

        if(isDrawHorizontal)
            drawHorizontal(c,parent);
        else if(isDrawVertical)
            drawVertical(c,parent);

    }

    private void drawGrid(Canvas c, RecyclerView parent){
        int childCount = parent.getChildCount();
//        if(int i = 0; i < )
    }

    private void drawHorizontal( Canvas c, RecyclerView parent){

        int childCount = parent.getChildCount();
        for(int i = 0 ; i < childCount - 1; i++){

            View childItemView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)childItemView.getLayoutParams();

            int top = parent.getPaddingTop() + layoutParams.topMargin ;
            int bottom = parent.getHeight() - parent.getPaddingBottom() - layoutParams.bottomMargin;
            int left = childItemView.getRight();
            int right = left + mDividerHeight;

            c.drawRect(left,top,right,bottom,mPaint);
        }

    }

    private void drawVertical( Canvas c, @NonNull RecyclerView parent) {
        //recyclerview 可见子itemview 数目
        int childCount = parent.getChildCount();
        for(int i = 0; i < childCount - 1; i++){
            // childCount - 1 最后一条目不绘制
            View childItemView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)childItemView.getLayoutParams();

            int left = parent.getPaddingLeft() + layoutParams.leftMargin; // diver 相对于 parent (即父recyclerview) 坐标
            int right = parent.getWidth() - parent.getPaddingRight() - layoutParams.rightMargin;
            // 分割线 diver 的 top坐标
            int top = childItemView.getBottom() ;
            int bottom = top + mDividerHeight;
            // 每个childItemView 都要绘制分割线，无非就是绘制矩形
            c.drawRect(left,top,right,bottom,mPaint);
        }

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(parent.getChildAdapterPosition(view) + 1 == parent.getAdapter().getItemCount()) {
            return;
        }
        if(parent.getLayoutManager() instanceof LinearLayoutManager && !isDrawGrid){
            Log.e(TAG,"-------------------------------Lin3333333333333333");
            if(((LinearLayoutManager)parent.getLayoutManager()).getOrientation() == RecyclerView.VERTICAL){
                //竖直方向列表 ， 底部设置padding
                isDrawVertical = true;
                outRect.set(0,0,0,mDividerHeight);
            }else{
                //水平方向列表 ， 右边设置padding
                isDrawHorizontal = true;
                outRect.set(0,0,mDividerHeight,0);
            }

        }else if(parent.getLayoutManager() instanceof GridLayoutManager){
            Log.e(TAG,"--------------------------------------ttttGRID");
            isDrawGrid = true;
            int spanCount = ((GridLayoutManager)parent.getLayoutManager()).getSpanCount();
            int totalCount = parent.getAdapter().getItemCount();
            int pos = parent.getChildAdapterPosition(view);

            int row = totalCount / spanCount;
            int column = spanCount;

            //int extra = totalCount % spanCount;
            if((pos + 1) % spanCount == 0 ){
                // 最右边一列
               if (totalCount - (pos + 1) <= spanCount){
                   // 满足则说明后面的itemview是最后一列，则不绘制分割线,啥都不做
               }else{
                   outRect.set(0,0,0,mDividerHeight);
               }
            }else{
                if(totalCount - (pos + 1) <= spanCount)
                    outRect.set(0,0,mDividerHeight,0);
                else
                    outRect.set(0,0,mDividerHeight,mDividerHeight);
            }

        }else{
            Log.e(TAG,"-------------------------------Linxxxxxxxxxxxxxxxxxxx");
            isDrawVertical = true;
            outRect.set(0,0,0,mDividerHeight);
        }

    }
}
