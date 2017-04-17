package com.bing.lan.comm.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.comm.R;

public class EditTextInputLayout extends LinearLayout {

    private EditText mEdContent;
    private TextView mTvTitle;

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

        if (attrs != null) {

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EditTextInputLayout);

            String hint = a.getString(R.styleable.EditTextInputLayout_edit_hint);
            String title = a.getString(R.styleable.EditTextInputLayout_title);

            if (hint != null) {
                mEdContent.setHint(hint);
            }

            if (title != null) {
                mTvTitle.setText(title);
            }

            a.recycle();
        }
    }

    public void setTitle(@NonNull String s) {

        if (mTvTitle != null) {
            mTvTitle.setText(s);
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
            return mValidator.validate( getId());
        } else {
            throw new RuntimeException("请先设置校验器");
        }
    }

    public interface Validator {

        boolean validate(int id);
    }
}
