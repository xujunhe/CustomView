package simple.xjh.com.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 重写系统TextView控件自定义View
 * Created by xujunhe on 2016/9/7.
 */
public class CustomView05 extends TextView{

    private Paint mPaint;

    public CustomView05(Context context) {
        this(context,null);
    }

    public CustomView05(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView05(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        canvas.drawRect(0,0,measuredWidth,measuredHeight,mPaint);
        mPaint.setColor(Color.RED);

        canvas.drawRect(10,10,measuredWidth-10,measuredHeight-10,mPaint);
        canvas.save();
        canvas.translate(10,10);
        super.onDraw(canvas);
        canvas.restore();

    }
}
