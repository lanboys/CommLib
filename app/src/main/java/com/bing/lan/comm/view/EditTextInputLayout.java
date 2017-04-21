package com.bing.lan.comm.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.config.AppConfig;
import com.bing.lan.comm.utils.LogUtil;

public class EditTextInputLayout extends LinearLayout {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    Validator mValidator;
    private EditText mEdContent;
    private TextView mTvTitle;
    private ImageView mIvImage;
    private View mLineContainer;

    public EditText getEditText() {
        return mEdContent;
    }

    private boolean mIsEditEnable;
    /**
     * 开启校验总开关
     */
    private boolean isOpenValidate = AppConfig.VALIDATE;

    public EditTextInputLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public EditTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        //  mContext = context;
        inflate(context, R.layout.view_input_layout, this);

        mEdContent = (EditText) findViewById(R.id.et_content);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvImage = (ImageView) findViewById(R.id.iv_image);
        mLineContainer = findViewById(R.id.fl_line_container);

        //mEdContent.setClickable(false);

        if (attrs != null) {

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EditTextInputLayout);

            String hint = a.getString(R.styleable.EditTextInputLayout_edit_hint);
            String title = a.getString(R.styleable.EditTextInputLayout_title);
            String editDigits = a.getString(R.styleable.EditTextInputLayout_edit_digits);
            Drawable drawable = a.getDrawable(R.styleable.EditTextInputLayout_image);
            mIsEditEnable = a.getBoolean(R.styleable.EditTextInputLayout_edit_enable, true);
            boolean showLine = a.getBoolean(R.styleable.EditTextInputLayout_show_line, true);
            int inputType = a.getInt(R.styleable.EditTextInputLayout_edit_inputType, -1);
            int imageVisible = a.getInt(R.styleable.EditTextInputLayout_image_visibility, -1);
            int titleVisible = a.getInt(R.styleable.EditTextInputLayout_title_visibility, -1);
            int editMaxLength = a.getInt(R.styleable.EditTextInputLayout_edit_maxLength, -1);

            setEditMaxLength(editMaxLength);

            setEditDigits(editDigits);

            mEdContent.setEnabled(mIsEditEnable);

            mLineContainer.setVisibility(showLine ? VISIBLE : GONE);

            if (imageVisible != -1) {
                switch (imageVisible) {
                    case View.GONE:
                        mIvImage.setVisibility(GONE);
                        break;
                    case View.INVISIBLE:
                        mIvImage.setVisibility(INVISIBLE);
                        break;
                    case View.VISIBLE:
                        mIvImage.setVisibility(VISIBLE);
                        break;
                }
            }
            if (titleVisible != -1) {
                switch (titleVisible) {
                    case View.GONE:
                        mTvTitle.setVisibility(GONE);
                        break;
                    case View.INVISIBLE:
                        mTvTitle.setVisibility(INVISIBLE);
                        break;
                    case View.VISIBLE:
                        mTvTitle.setVisibility(VISIBLE);
                        break;
                }
            }

            if (drawable != null) {
                mIvImage.setImageDrawable(drawable);
            }

            if (inputType != -1) {
                mEdContent.setInputType(inputType);
            }

            if (hint != null) {
                mEdContent.setHint(hint);
            }

            if (title != null) {
                mTvTitle.setText(title);
            }

            a.recycle();
        }
    }

    // 只有输入类型是 字符串 时才能起作用
    public void setEditDigits(String editDigits) {
        if (editDigits != null) {
            mEdContent.setKeyListener(DigitsKeyListener.getInstance(editDigits));
        }
    }

    public void setEditMaxLength(int editMaxLength) {

        if (editMaxLength >= 0) {
            mEdContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(editMaxLength)});
        }
    }

    public void setLineVisibility(int visibility) {
        if (mLineContainer != null) {

            mLineContainer.setVisibility(visibility);
        }
    }

    public void setImageVisibility(int visibility) {

        if (mIvImage != null) {
            mIvImage.setVisibility(visibility);
        }
    }

    public void setImageResource(@DrawableRes int resId) {

        if (mIvImage != null) {
            mIvImage.setImageResource(resId);
        }
    }

    public void setTitleVisibility(int visibility) {

        if (mTvTitle != null) {
            mTvTitle.setVisibility(visibility);
        }
    }

    public void setEditEnabled(boolean enabled) {
        if (mEdContent != null) {
            mEdContent.setEnabled(false);
        }
    }

    public void setEditTextGravity(int gravity) {
        if (mEdContent != null) {
            mEdContent.setGravity(gravity);
        }
    }

    public void setTextViewDrawableLeft(@DrawableRes int resId) {

        if (mTvTitle != null) {
            Drawable drawable = getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            mTvTitle.setCompoundDrawables(drawable, null, null, null);
        }
    }

    public void setTextViewDrawablePadding(int dp) {

        if (mTvTitle != null) {
            float density = getResources().getDisplayMetrics().density;
            int px = (int) (dp * density + .5f);
            mTvTitle.setCompoundDrawablePadding(px);
        }
    }

    public void setTextViewTitle(@NonNull String s) {

        if (mTvTitle != null) {
            mTvTitle.setText(s);
        }
    }

    public void setEditHint(@NonNull String s) {
        if (mEdContent != null) {
            mEdContent.setHint(s);
        }
    }

    public String getEditContent() {

        return mEdContent.getText().toString().trim();
    }

    public void setEditContent(@NonNull String s) {

        if (mEdContent != null) {
            mEdContent.setText(s);
        }
    }

    public void setEditShowPassword(boolean show) {
        if (mEdContent != null) {
            if (show) {
                //如果选中，显示密码
                mEdContent.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                //否则隐藏密码
                mEdContent.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    public void setEditInputType(int type) {

        if (mEdContent != null) {
            mEdContent.setInputType(type);
        }

        //选择状态 显示明文--设置为可见的密码
        mEdContent.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
        mEdContent.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    public CharSequence getTitle() {
        return mTvTitle.getText();
    }

    public void setValidator(Validator validator) {
        mValidator = validator;
    }

    /**
     * 如果 编辑框 设置为 不可点击 就拦截事件，交给父类处理
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //  return mIsEditEnable || super.onInterceptTouchEvent(ev);

        if (!mIsEditEnable) {
            return true;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    public boolean validate() {

        if (isOpenValidate) {

            if (mValidator != null) {
                return mValidator.validate(getId(), getEditContent());
            } else {
                throw new RuntimeException("请先设置校验器");
            }
        } else {
            return true;
        }
    }

    public void setImageOnClickListener(@Nullable OnClickListener l) {
        if (mIvImage != null) {
            mIvImage.setOnClickListener(l);
        }
    }

    public interface Validator {

        boolean validate(int id, String str);
    }
}
