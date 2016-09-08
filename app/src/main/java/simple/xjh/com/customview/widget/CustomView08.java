package simple.xjh.com.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import simple.xjh.com.customview.R;

/**
 * 参考Android群英传 3.6.31  图3.11 展示 51页
 * Created by Administrator on 2016/9/8 0008.
 */
public class CustomView08 extends View{

    private int mCircleColor;//圆的颜色
    private String mText;

    private Paint mPaint;
    private Rect mTextRect;
    private RectF rectF;

    private  Paint mTextPaint;

    public CustomView08(Context context) {
        this(context,null);
    }

    public CustomView08(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView08(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs,defStyleAttr);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView08, defStyleAttr, 0);
        mCircleColor = typedArray.getColor(R.styleable.CustomView08_circleColor, Color.BLUE);
        mText = typedArray.getString(R.styleable.CustomView08_mText);
        typedArray.recycle();

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(28);
        mTextRect = new Rect();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (mText==null)mText="";

        mTextPaint.getTextBounds(mText,0,mText.length(),mTextRect);

        mPaint.setColor(mCircleColor);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int mCircle =   getWidth()/2;
        float mRadius = mCircle/2;

        //画出內圆
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mCircleColor);
        mPaint.setStrokeWidth(1);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,mRadius,mPaint);

        //画出外圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(20);
        rectF = new RectF(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10);
        canvas.drawArc(rectF,135,270,false,mPaint);

        //画出字
        canvas.drawText(mText,getWidth()/2-mTextRect.width()/2,getHeight()/2+mTextRect.height()/2,mTextPaint);

    }
}
