package simple.xjh.com.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import simple.xjh.com.customview.R;


/**
 * 自定义view 02
 * http://blog.csdn.net/lmj623565791/article/details/24300125
 * 博客中为 图片在上文字在下，
 * 这里修改为图片在下文字在上。
 * Created by Administrator on 2016/8/26 0026.
 */
public class CustomView02 extends View{
    private static final int IMAGE_SCALE_FITXY = 0;
    private static final int IMAGE_SCALE_CENTER = 1;

    private String mText;
    private int textColor;
    private int imageScaleType;
    private Bitmap mImage;


    private Paint mPaint;
    private Rect mBound;

    int width=0,height=0 ;
    /**
     * 控制整体布局
     */
    private Rect rect;
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
        rect = new Rect();
        mPaint.getTextBounds(mText,0,mText.length(),mBound);
    }

//    MeasureSpec的specMode,一共三种类型：
//    EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
//    AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
//    UNSPECIFIED：表示子布局想要多大就多大，很少使用
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



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
        mPaint.setColor(Color.BLUE);
        //画出边框
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        //设置绘画区域
        rect.left = getPaddingLeft();
        rect.right = width - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = height - getPaddingBottom();

        //设置字体颜色
        mPaint.setColor(textColor);

        mPaint.setStyle(Paint.Style.FILL);

        /**
         * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
         */
        if (mBound.width() > width)
        {

            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mText, paint, (float) width - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();

            //在画板的
            canvas.drawText(msg, getPaddingLeft(), height - getPaddingBottom(), mPaint);

        } else
        {
            //正常情况，将字体居中
            canvas.drawText(mText, width / 2 - mBound.width() * 1.0f / 2, mBound.height(), mPaint);
        }


        //去掉使用掉的块
        rect.top += mBound.height();

        if (imageScaleType == IMAGE_SCALE_FITXY)
        {
            canvas.drawBitmap(mImage, null, rect, mPaint);
        } else
        {
            //计算居中的矩形范围
            rect.left = width / 2 - mImage.getWidth() / 2;
            rect.right = width / 2 + mImage.getWidth() / 2;

            //view的高度减去 字体的高度
           int newHeight =  height-mBound.height();
            //去除字体高度之后 图片居中之后的 top padding 高度
            newHeight = newHeight/2 - mImage.getHeight()/2;
            //字体高度+图片居中之后的top padding 高度 就是 top 的值
            newHeight+=mBound.height();

            rect.top = newHeight;

            //top的值加上图片的高度就是底部的值
            rect.bottom = newHeight+mImage.getHeight();

            canvas.drawBitmap(mImage, null, rect, mPaint);
        }



    }
}
