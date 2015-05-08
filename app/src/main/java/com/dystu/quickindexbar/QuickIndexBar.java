package com.dystu.quickindexbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/5/6.
 */
public class QuickIndexBar extends View {

    private Paint paint;

    private TextView header;


    private float height;

    private ListView mListView;

    public void setListView(ListView mListView) {
        this.mListView = mListView;
    }

    private String[] indexArr;

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        indexArr = new String[]{"搜", "#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.DKGRAY);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(20);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float center = getWidth() / 2;

        height = getHeight() / indexArr.length;

        for (int i = indexArr.length - 1; i > -1; i--) {
            canvas.drawText(indexArr[i], center, height * (i + 1), paint);
        }
    }

    private int sectionForPoint(float y) {
        int index = (int) (y / height);
        if (index < 0) {
            index = 0;
        }
        if (index > indexArr.length - 1) {
            index = indexArr.length - 1;
        }
        return index;
    }

    private void setHeaderTextAndScroll(MotionEvent event){
        if (mListView == null){
            return;
        }

        String headString= indexArr[sectionForPoint(event.getY())];
        header.setText(headString);
        PeopleAdapter adapter = (PeopleAdapter) mListView.getAdapter();
        String[] adapterSections = (String[]) adapter.getSections();
        for (int i = adapterSections.length -1;i>-1;i--){
            if (adapterSections[i].equals(headString)){
                mListView.setSelection(adapter.getPositionForSection(i));
                break;
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (header == null){
                    header = (TextView)((View)getParent()).findViewById(R.id.floating_header);
                }
                setHeaderTextAndScroll(event);
                header.setVisibility(View.VISIBLE);
                setBackgroundResource(R.drawable.sidebar_background_pressed);
                return  true;
            case MotionEvent.ACTION_MOVE:
                setHeaderTextAndScroll(event);
                return  true;
            case MotionEvent.ACTION_UP:
                header.setVisibility(View.INVISIBLE);
                setBackgroundColor(Color.TRANSPARENT);
                return  true;
            case MotionEvent.ACTION_CANCEL:
                header.setVisibility(View.INVISIBLE);
                setBackgroundColor(Color.TRANSPARENT);
                return  true;


        }
        return super.onTouchEvent(event);
    }
}
