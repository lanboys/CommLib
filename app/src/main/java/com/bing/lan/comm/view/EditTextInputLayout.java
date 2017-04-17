package com.bing.lan.comm.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.comm.R;
import com.bing.lan.comm.utils.LogUtil;

public class EditTextInputLayout extends LinearLayout {

    private EditText mEdContent;
    private TextView mTvTitle;
    private ImageView mIvImage;

    public EditTextInputLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public EditTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

    private void initView(Context context, AttributeSet attrs) {

        //  mContext = context;
        inflate(context, R.layout.view_input_layout, this);

        mEdContent = (EditText) findViewById(R.id.et_content);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvImage = (ImageView) findViewById(R.id.iv_image);

        if (attrs != null) {

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EditTextInputLayout);

            String hint = a.getString(R.styleable.EditTextInputLayout_edit_hint);
            String title = a.getString(R.styleable.EditTextInputLayout_title);
            Drawable drawable = a.getDrawable(R.styleable.EditTextInputLayout_image);
            boolean enable = a.getBoolean(R.styleable.EditTextInputLayout_edit_enable, true);
            int inputType = a.getInt(R.styleable.EditTextInputLayout_edit_inputType, -1);
            int visible = a.getInt(R.styleable.EditTextInputLayout_image_visibility, -1);

            mEdContent.setEnabled(enable);

            if (visible != -1) {
                switch (visible) {
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

    public void setEditEnabled(boolean enabled) {
        mEdContent.setEnabled(false);
    }

    public void setTitle(@NonNull String s) {

        if (mTvTitle != null) {
            mTvTitle.setText(s);
        }
    }

    public void setEditContent(@NonNull String s) {

        if (mEdContent != null) {
            mEdContent.setText(s);
        }
    }

    public void setHint(@NonNull String s) {
        if (mEdContent != null) {
            mEdContent.setHint(s);
        }
    }

    public String getContent() {

        return mEdContent.getText().toString().trim();
    }

    public CharSequence getTitle() {
        return mTvTitle.getText();
    }

    Validator mValidator;

    public void setValidator(Validator validator) {
        mValidator = validator;
    }

    public boolean validate() {

        if (mValidator != null) {
            return mValidator.validate(getId());
        } else {
            throw new RuntimeException("请先设置校验器");
        }
    }

    public interface Validator {

        boolean validate(int id);
    }
}
