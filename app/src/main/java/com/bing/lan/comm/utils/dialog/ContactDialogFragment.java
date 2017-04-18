package com.bing.lan.comm.utils.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.bing.lan.bing.ui.join.JoinUsActivity;
import com.bing.lan.bing.ui.joinagent.JoinAgentActivity;
import com.bing.lan.bing.ui.joindealer.JoinDealerActivity;
import com.bing.lan.comm.R;

/**
 * Author: yxhuang
 * Date: 2016/11/21
 * Email: yxhuang@gmail.com
 */

public class ContactDialogFragment extends DialogFragment {

    public static final String DIALOG_TITLE = "dialog_title";
    public static final String DIALOG_CONTENT = "dialog_content";

    private String mDialogTitle;
    private SimpleCallbackListener mCallbackListener;
    private String mText = "最近听闻某人准备开一个课，关于写代码的，也是现在比较火的“全栈工程师”。你说开课就开课吧，没什么大不了，但是这个课牛逼了——收费六万，两个月速成，0基础不需要学习计算机相关的知识。\n" +
            "\n" +
            "最后这句“不需要计算机背景相关的知识”可不是我说的，是该授课者自己在介绍、推广自己的课程的时候说的。他还认为，在真正的编程、写项目的过程中，程序员的“思维”是最重要的，而且一般只能靠项目给磨出来，是学校学不到的。\n" +
            "\n" +
            "你说光看介绍能不能判断出个效果？作为一个程序员，半个“全栈工程师”（逗逗大家玩儿，也就是写过React的Web开发、SSH、推荐系统的，技术一般的代码狗），我的看法是：60k的最大作用可能就是给了你拼命学习的动力，其中，8k给教育（如果确实有效的话），52k应该做个金奖颁给你自己。\n" +
            "\n" +
            "作者：罗文益\n" +
            "链接：http://zhuanlan.zhihu.com/p/23836857\n" +
            "来源：知乎\n" +
            "著作权归作者所有，转载请联系作者获得授权。".toString();

    public static ContactDialogFragment newInstance(@NonNull String dialogTitle, @NonNull String content) {
        ContactDialogFragment fragment = new ContactDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_TITLE, dialogTitle);
        bundle.putString(DIALOG_CONTENT, content);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mDialogTitle = bundle.getString(DIALOG_TITLE);
        }
    }

    @Override
    public void onStart() {
        // 对话框宽度 总宽度0.75
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.80), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        super.onStart();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 去掉标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 透明背景
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 点击外部不能消失
        getDialog().setCanceledOnTouchOutside(true);

        View inflate = inflater.inflate(R.layout.alert_join, null);
        inflate.findViewById(R.id.btn_join_agent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((JoinUsActivity) getActivity()).startActivity(JoinAgentActivity.class, false, false);
            }
        });
        inflate.findViewById(R.id.btn_join_dealer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((JoinUsActivity) getActivity()).startActivity(JoinDealerActivity.class, false, false);
            }
        });
        //
        // TextView tv_dialog_title = (TextView) view.findViewById(R.id.tv_dialog_title);
        // TextView tv_dialog_content  = (TextView) view.findViewById(R.id.tv_dialog_content);
        // Button btn_agree = (Button) view.findViewById(R.id.btn_agree);
        //
        // tv_dialog_title.setText(mDialogTitle);
        // tv_dialog_content.setText(mText);
        //
        //
        // btn_agree.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
        //         if (mCallbackListener != null){
        //             dismiss();
        //             mCallbackListener.onCallbackListener(true);
        //         }
        //     }
        // });

        return inflate;
    }

    public void setCallbackListener(SimpleCallbackListener listener) {
        mCallbackListener = listener;
    }
}
