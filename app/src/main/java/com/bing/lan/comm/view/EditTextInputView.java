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

public class EditTextInputView extends LinearLayout {

    private EditText mEdContent;
    private ImageView mIvImage;
    private TextView mTextView;

    public EditTextInputView(Context context) {
        super(context);
        initView(context, null);
    }

    public EditTextInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public EditTextInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        inflate(context, R.layout.view_edittext_input, this);

        mEdContent = (EditText) findViewById(R.id.et_content);
        mIvImage = (ImageView) findViewById(R.id.iv_image);
        mTextView = (TextView) findViewById(R.id.tv_content);

        if (attrs != null) {

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.EditTextInputView);

            String hint = a.getString(R.styleable.EditTextInputView_edit_hint1);
            String text = a.getString(R.styleable.EditTextInputView_text_string);
            Drawable drawable = a.getDrawable(R.styleable.EditTextInputView_image1);
            boolean enable = a.getBoolean(R.styleable.EditTextInputView_edit_enable1, true);
            int inputType = a.getInt(R.styleable.EditTextInputView_edit_inputType1, -1);

            int visible = a.getInt(R.styleable.EditTextInputView_text_visibility, -1);

            mEdContent.setEnabled(enable);

            if (visible != -1) {
                switch (visible) {
                    case View.GONE:
                        mTextView.setVisibility(GONE);
                        break;
                    case View.INVISIBLE:
                        mTextView.setVisibility(INVISIBLE);
                        break;
                    case View.VISIBLE:
                        mTextView.setVisibility(VISIBLE);
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

            if (text != null) {
                mTextView.setHint(text);
            }

            a.recycle();
        }
    }

    public ImageView getImageView() {
        return mIvImage;
    }

    public EditText getEditTextView() {
        return mEdContent;
    }

    public TextView getRightTextView() {
        return mTextView;
    }

    public void setTextViewVisibility(int visibility) {

        if (mTextView != null) {
            mTextView.setVisibility(visibility);
        }
    }

    public void setEditEnabled(boolean enabled) {
        mEdContent.setEnabled(false);
    }

    public void setTextViewString(@NonNull String s) {

        if (mTextView != null) {
            mTextView.setText(s);
        }
    }

    public void setEditContent(@NonNull String s) {

        if (mEdContent != null) {
            mEdContent.setText(s);
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

    public CharSequence getTextViewString() {
        return mTextView.getText();
    }

    Validator mValidator;

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

    public interface Validator {

        boolean validate(int id, String str);
    }
}
