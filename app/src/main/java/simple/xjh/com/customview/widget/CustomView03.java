package simple.xjh.com.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import simple.xjh.com.customview.R;

/**
 * 自定义圆环交替
 * http://blog.csdn.net/lmj623565791/article/details/24500107
 * Created by Administrator on 2016/9/2 0002.
 */
public class CustomView03 extends View{

    private int circleWidth;//圆环的宽度
    private int speed; //圆环的滚动速度
    private  int[] colors = new int[]{Color.RED,Color.BLACK,Color.BLUE,Color.GRAY,Color.GREEN}; //圆环交换的颜色
    private int colorIndex = 0; //当前颜色位置；
    private int oldColorIndex = colors.length-1;

    private Paint mPaint;
    private int mProgress = 0;

    public CustomView03(Context context) {
        this(context,null);
    }

    public CustomView03(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 设置圆环交换的颜色
     * @param colors
     */
    public void setColors(int... colors) {
        this.colors = colors;
    }

    public CustomView03(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context,attrs,defStyleAttr);
    }

    private void initAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView03, defStyleAttr, 0);
        int indexCount = typedArray.getIndexCount();
        for (int i=0;i<indexCount;i++)
        {
            int index = typedArray.getIndex(i);
            switch (index)
            {
                case R.styleable.CustomView03_circleWidth:
                    circleWidth = typedArray.getDimensionPixelSize(index, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomView03_speed:
                    speed = typedArray.getInt(index,20);
                    break;
            }

        }
        typedArray.recycle();
        mPaint = new Paint();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start();
            }
        },1000);

    }



    private void start() {
        new Thread()
        {
            @Override
            public void run() {
                super.run();
              while (true)
              {
                  if (mProgress == 360)
                  {
                      oldColorIndex = colorIndex;
                      colorIndex++;
                      colorIndex =  colorIndex%colors.length;
                      mProgress = 0;

                  }
                  postInvalidate();
                  try
                  {
                      Thread.sleep(speed);
                  } catch (InterruptedException e)
                  {
                      e.printStackTrace();
                  }
                  mProgress++;
              }


            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = centre - circleWidth / 2;// 半径
        mPaint.setStrokeWidth(circleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限
        mPaint.setColor(colors[oldColorIndex]); // 设置圆环的颜色
        canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
        mPaint.setColor(colors[colorIndex]); // 设置圆环的颜色
        canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
    }
}
