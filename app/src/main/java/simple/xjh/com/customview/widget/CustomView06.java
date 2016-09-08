package simple.xjh.com.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;
import com.orhanobut.logger.Logger;

/**
 * 利用LinearGradient Shader 和Matrix 实现一个冬天的文字闪动效果
 * Created by Administrator on 2016/9/8 0008.
 */
public class CustomView06 extends TextView {

    private int mViewWidth;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix  mMatrix;


    private int mTranslate;

    public CustomView06(Context context) {
        this(context,null);
    }

    public CustomView06(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView06(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth ==0 )
        {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth>0)
            {
                //关键 :  这里是获取！！！
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,new int[]{Color.BLACK,Color.WHITE,Color.BLACK},null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mMatrix!=null)
        {
            mTranslate+=10;
            if (mTranslate>mViewWidth)
            {
                mTranslate=-mViewWidth/2;
            }

            mMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mMatrix);
            postInvalidateDelayed(80);
            Logger.d("onDraw"+mTranslate);
        }

    }
}
