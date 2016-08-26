package simple.xjh.com.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import simple.xjh.com.customview.R;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class CustomView02 extends View{

    private String mText;
    private int textColor;
    private int imageScaleType;
    private Bitmap mImage;


    private Paint mPaint;
    private Rect mBound;

    public CustomView02(Context context) {
        this(context,null);
    }

    public CustomView02(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView02(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView02, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();

        for (int i=0;i<indexCount;i++)
        {
            int index = typedArray.getIndex(i);
            switch (index)
            {
                case R.styleable.CustomView02_mText:
                    mText = typedArray.getString(index);
                    break;
                case R.styleable.CustomView02_textColor:
                    textColor = typedArray.getColor(index, Color.BLACK);
                    break;
                case R.styleable.CustomView02_imageScaleType:
                    imageScaleType = typedArray.getInt(index,0);
                    break;
                case R.styleable.CustomView02_image:
                    mImage = BitmapFactory.decodeResource(getResources(),typedArray.getResourceId(index,0));
                    break;
            }

        }

        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(80);
        mBound = new Rect();
        mPaint.getTextBounds(mText,0,mText.length(),mBound);
    }

//    MeasureSpec的specMode,一共三种类型：
//    EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
//    AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
//    UNSPECIFIED：表示子布局想要多大就多大，很少使用
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width=0,height=0 ;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            // Parent has told us how big to be. So be it.
            width = widthSize;
        }else
        {
            int imgWidth =  mImage.getWidth()+getPaddingLeft()+getPaddingRight();
            int textWidth = mBound.width()+getPaddingLeft()+getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST)// wrap_content
            {
                int desire = Math.max(imgWidth, textWidth);
                width = Math.min(desire, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY)
        {
            height = heightSize;
        }else
        {
            int desire = getPaddingTop() + getPaddingBottom() + mImage.getHeight() + mBound.height();
            if (widthMode == MeasureSpec.AT_MOST)
            {
                height = Math.min(desire, widthSize);
            }
        }

        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 边框
         */
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        canvas.drawBitmap(mImage, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2,mPaint);


        mPaint.setColor(textColor);
        //设置居中显示
        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);




    }
}
