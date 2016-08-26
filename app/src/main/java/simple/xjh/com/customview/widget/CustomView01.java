package simple.xjh.com.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.orhanobut.logger.Logger;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import simple.xjh.com.customview.R;


/**
 *
 * 实现一个 随机数文本，点击 view  生成随机数展示
 * http://blog.csdn.net/lmj623565791/article/details/24252901
 *
 * Created by Administrator on 2016/8/26 0026.
 */
public class CustomView01 extends View implements View.OnClickListener{

    //文本
    private String mText;
    //文本
    private int textColor;

    //画笔
    private Paint mPaint;

    //控制文本的显示区域
    private Rect mBound;

    public CustomView01(Context context) {
        this(context,null);
    }

    public CustomView01(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView01(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    //初始化自定义属性
    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView01, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i=0;i<indexCount;i++)
        {
            int index = typedArray.getIndex(i);
            switch (index)
            {

                case R.styleable.CustomView01_mText:
                    mText = typedArray.getString(index);
                    break;
                case R.styleable.CustomView01_textColor:
                    textColor = typedArray.getColor(index,Color.BLACK);
                    break;
            }
        }


        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPaint.setTextSize(80);
        mBound = new Rect();

        if(TextUtils.isEmpty(mText))
        {
            mText = randomText();
        }
        //在这之前，必须把Paint 设置完成，否则 画出来 会有偏差
        mPaint.getTextBounds(mText,0 , mText.length(), mBound);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Logger.d("onMeasure");
        int width = 0;
        int height = 0;

        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        switch (specMode)
        {
            case MeasureSpec.EXACTLY:// 明确指定了
                width = getPaddingLeft() + getPaddingRight() + specSize;
                break;
            case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
                width = getPaddingLeft() + getPaddingRight() + mBound.width();
                break;
        }

        /**
         * 设置高度
         */
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode)
        {
            case MeasureSpec.EXACTLY:// 明确指定了
                height = getPaddingTop() + getPaddingBottom() + specSize;
                break;
            case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
                height = getPaddingTop() + getPaddingBottom() + mBound.height();
                break;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Logger.d("onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Logger.d("onDraw");
        super.onDraw(canvas);
       mPaint.setColor(Color.BLACK);
        //画出文本显示区域
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        mPaint.setColor(textColor);
        //设置居中显示
        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }

    @Override
    public void onClick(View v) {
        setText(randomText());
    }

    //生成4位数随机文本
    private String randomText()
    {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4)
        {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set)
        {
            sb.append("" + i);
        }

        return sb.toString();
    }

    public void setText(String text)
    {
        mText = text;
        postInvalidate();
    }


    public String getText()
    {
        return mText;
    }

}
