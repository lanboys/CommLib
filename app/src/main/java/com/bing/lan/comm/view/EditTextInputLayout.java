package com.bing.lan.comm.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.utils.LogUtil;

public class EditTextInputLayout extends LinearLayout {

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    Validator mValidator;
    private EditText mEdContent;
    private TextView mTvTitle;
    private ImageView mIvImage;
    private View mLineContainer;

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

        if (attrs != null) {

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EditTextInputLayout);

            String hint = a.getString(R.styleable.EditTextInputLayout_edit_hint);
            String title = a.getString(R.styleable.EditTextInputLayout_title);
            Drawable drawable = a.getDrawable(R.styleable.EditTextInputLayout_image);
            boolean enable = a.getBoolean(R.styleable.EditTextInputLayout_edit_enable, true);
            boolean showLine = a.getBoolean(R.styleable.EditTextInputLayout_show_line, true);
            int inputType = a.getInt(R.styleable.EditTextInputLayout_edit_inputType, -1);
            int imageVisible = a.getInt(R.styleable.EditTextInputLayout_image_visibility, -1);
            int titleVisible = a.getInt(R.styleable.EditTextInputLayout_title_visibility, -1);

            mEdContent.setEnabled(enable);

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
        mEdContent.setEnabled(false);
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

    public CharSequence getTitle() {
        return mTvTitle.getText();
    }

    public void setValidator(Validator validator) {
        mValidator = validator;
    }

    public boolean validate() {

        if (mValidator != null) {
            return mValidator.validate(getId(), getEditContent());
        } else {
            throw new RuntimeException("请先设置校验器");
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
