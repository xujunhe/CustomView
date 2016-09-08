package simple.xjh.com.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import simple.xjh.com.customview.R;

/**
 * 简易的TopBar
 * Created by Administrator on 2016/9/8 0008.
 */
public class CustomView07 extends RelativeLayout{

    private String mTitle;
    private int mTitleColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    private int mLeftColor;
    private Drawable mRightBackgroud;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private String mRightText;
    private int mRightColor;

    private Button mLeftBtn,mRightBtn;
    private TextView mTitleText;

    private  OnClickListener onClickListener;

    private RelativeLayout.LayoutParams mLeftBtnlayoutParams,mRightBtnlayoutParams,mTextViewlayoutParams;


    public CustomView07(Context context) {
        this(context,null);
    }

    public CustomView07(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView07(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs,defStyleAttr);
        initView(context);
    }

    //初始化子控件
    private void initView(Context context) {
        mLeftBtn = new Button(context);
        mRightBtn = new Button(context);
        mTitleText = new TextView(context);

        //设置属性值
        mTitleText.setText(mTitle);
        mTitleText.setTextColor(mTitleColor);
        mTitleText.setGravity(Gravity.CENTER);

        mLeftBtn.setText(mLeftText);
        mLeftBtn.setTextColor(mLeftColor);
        setBackground(mLeftBtn,mLeftBackground);
        mRightBtn.setText(mRightText);
        mRightBtn.setTextColor(mRightColor);
        setBackground(mRightBtn,mRightBackgroud);

        //左边按钮
        mLeftBtnlayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mLeftBtnlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
        addView(mLeftBtn,mLeftBtnlayoutParams);

        //右边按钮
        mRightBtnlayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mRightBtnlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        addView(mRightBtn,mRightBtnlayoutParams);

        //标题
        mTextViewlayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mTextViewlayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleText,mTextViewlayoutParams);
        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null)
                    onClickListener.onLeftClick();
            }
        });
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null)
                    onClickListener.onRightClick();
            }
        });
    }

    private void setBackground(Button btn,Drawable drawable) {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.JELLY_BEAN)
        {
            btn.setBackgroundDrawable(drawable);
        }else
        {
            btn.setBackground(drawable);
        }
    }

    //初始化自定义属性
    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView07, defStyleAttr, 0);
        mTitle = typedArray.getString(R.styleable.CustomView07_mTitle);
        mTitleColor =  typedArray.getColor(R.styleable.CustomView07_mTitleColor,Color.BLACK);
        mLeftBackground = typedArray.getDrawable(R.styleable.CustomView07_mLeftBackground);
        mLeftText = typedArray.getString(R.styleable.CustomView07_mLeftText);
        mLeftColor = typedArray.getColor(R.styleable.CustomView07_mLeftTextColor,Color.BLACK);
        mRightBackgroud = typedArray.getDrawable(R.styleable.CustomView07_mRightBackgroud);
        mRightText = typedArray.getString(R.styleable.CustomView07_mRightText);
        mRightColor = typedArray.getColor(R.styleable.CustomView07_mRightTextColor,Color.BLACK);
        //回收资源
        typedArray.recycle();
    }



    public  interface  OnClickListener{
        void onLeftClick();
        void onRightClick();

    }


}
