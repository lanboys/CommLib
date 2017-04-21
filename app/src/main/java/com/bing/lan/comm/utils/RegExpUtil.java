package com.bing.lan.comm.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {

    public static boolean checkReg(String text) {
        Pattern pattern = Pattern.compile("^/w+$*");
        Matcher m = pattern.matcher(text);
        return m.matches();
    }

    public static boolean checkUserName(String password) {
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z@]+$"); // [0-9a-zA-Z@]*
        Matcher m = pattern.matcher(password);
        return m.matches();
    }

    public static boolean checkIdCard(String sfzhm) {
        Pattern patternSfzhm1 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
        Pattern patternSfzhm2 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");

        Matcher matcherSfzhm1 = patternSfzhm1.matcher(sfzhm);
        Matcher matcherSfzhm2 = patternSfzhm2.matcher(sfzhm);

        if (sfzhm.equals("")) {
            //showToast("身份证号码不能为空！");
            return false;
        } else if (!(sfzhm.equals("")) && !matcherSfzhm1.find() && !matcherSfzhm2.find()) {
           // showToast("身份证号码格式不正确，请重新输入！");
            return false;
        }
        return true;
    }

    private void check( String dzyx, String sjhm, String dlzh, String xm, String dlmm) {
        Pattern patternSjhm = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcherSjhm = patternSjhm.matcher(sjhm);
        Pattern patternDzyx = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcherDzyx = patternDzyx.matcher(dzyx);

        //if (xm.equals("")) {
        //    showToast("姓名不能为空！");
        //} else if (dlzh.equals("")) {
        //    showToast("登录账号不能为空！");
        //} else if (dlmm.equals("")) {
        //    showToast("登录密码不能为空！");
        //}
        //
        //else if (!(sjhm.equals("")) && !(matcherSjhm.find())) {
        //    showToast("手机号码格式不正确，请重新输入！");
        //} else if (!(dzyx.equals("")) && !((matcherDzyx.find()))) {
        //    showToast("电子邮箱格式不正确，请重新输入！");
        //}
    }

    public static boolean checkChineseName(String name) {

        //name.matches()

        //String regx = "(([\u4E00-\u9FA5]{2,7})|([a-zA-Z]{3,10}))";
        //System.out.println(Pattern.matches(regx, "我我我我我我我"));
        //System.out.println(Pattern.matches(regx, "aBcDefghij"));

        if (!name.matches("[\u4e00-\u9fa5]{2,7}")) {
            System.out.println("只能输入2到7个汉字");
            return false;
        }else return true;
    }

    public static boolean checkPassword(String password) {
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z_]{8,20}$"); // [0-9a-zA-Z_]*
        //        Pattern.compile("^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$");
        Matcher m = pattern.matcher(password);
        return m.matches();
    }

    public static boolean checkNickName(String nikeName) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_\u4e00-\u9fa5]+$");
        Matcher m = pattern.matcher(nikeName);
        return m.matches();
    }

    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern
                .compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher m = pattern.matcher(email);
        return m.matches();
    }

    public static boolean checkQQ(String qq) {
        String regx = "^[1-9][0-9]{4,14}$";
        boolean result = qq.matches(regx);
        return result;
    }

    //   ^( 1  (   ([35][0-9])  |  ([4][57])  |  [8][0-9]   ))       \d{8}$
    public static boolean checkPhoneNum(String phone) {
        Pattern pattern = Pattern.compile("^(1(([35][0-9])|([4][57])|[8][0-9]))\\d{8}$"); //全面
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean checkIdentityNum(String identityNum) {
        Pattern pattern = Pattern.compile("^(\\d{14}\\w)|\\d{17}\\w$");
        Matcher matcher = pattern.matcher(identityNum);
        return matcher.matches();
    }

    public static boolean checkPayPassword(@NonNull String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[0-9]{8,16}$");
        Matcher m = pattern.matcher(password);
        return m.matches();
    }
}
