package com.bing.lan.comm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.comm.R;

/**
 * Author: yxhuang
 * Date: 2016/11/15
 * Email: yxhuang@gmail.com
 */

/**
 * 自定义 EditText， 主要用于登录输入用户名，密码等
 */
public class EditTextInputView extends LinearLayout {

    private EditText et_edittext_input;
    private ImageView iv_edittext_input;
    private TextView tv_edittext_input;

    private Context mContext;

    public EditTextInputView(Context context) {
        super(context);
        initView(context);
    }

    public EditTextInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EditTextInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        inflate(mContext, R.layout.view_edittext_input, this);

        et_edittext_input = (EditText) findViewById(R.id.et_edittext_input);
        iv_edittext_input = (ImageView) findViewById(R.id.iv_edittext_input);
        tv_edittext_input = (TextView) findViewById(R.id.tv_edittext_input);
    }

    public ImageView getImageView() {
        return iv_edittext_input;
    }

    public EditText getEditTextView() {
        return et_edittext_input;
    }

    public TextView getRightTextView() {
        return tv_edittext_input;
    }
}
