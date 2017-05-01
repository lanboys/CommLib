package com.bing.lan.comm.utils.picker;

import android.app.Activity;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;

public class PickerUtil {

    PickerItemSelectListener mListener;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private OptionsPickerView pvOptions;

    public PickerUtil(PickerItemSelectListener listener) {
        mListener = listener;
    }

    public void setListener(PickerItemSelectListener listener) {
        mListener = listener;
    }

    public void selectType(Activity activity) {
        if (pvOptions == null) {
            initOptionPicker(activity);
        }

        if (pvOptions != null) {
            pvOptions.show();
        }
    }

    private void initOptionPicker(Activity activity) {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */
        initOptionData();
        pvOptions = new OptionsPickerView.Builder(activity, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(options2)
                        + options3Items.get(options1).get(options2).get(options3);
                // btn_Options.setText(tx);

                if (mListener != null) {
                    mListener.onItemSelect(
                            options1Items.get(options1).getPickerViewText(),
                            options2Items.get(options1).get(options2),

                            options3Items.get(options1).get(options2).get(options3),
                            v);
                }
            }
        })
                .setTitleText("主营类型")
                .setContentTextSize(20)//设置滚轮文字大小
                //.setDividerColor(Color.GREEN)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                //.setBgColor(Color.BLACK)
                //.setTitleBgColor(Color.DKGRAY)
                //.setTitleColor(Color.LTGRAY)
                .setCancelColor(0xff0195ff)
                .setSubmitColor(0xff0195ff)
                //.setTextColorCenter(Color.LTGRAY)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                // .setLabels("省", "市", "区")
                .build();

        //pvOptions.setSelectOptions(1,1);

        /*pvOptions.setPicker(options1Items);//一级选择器*/
        //pvOptions.setPicker(options1Items, options2Items);//二级选择器
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器

        // 不联动的多级选项
        /*pvOptions.setNPicker(options1Items, options2Items,options3Items);//三级选择器*/

    }

    private void initOptionData() {

        /**
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        //选项1
        options1Items.add(new ProvinceBean(0, "女装", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(1, "男装", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(2, "其他", "描述部分", "其他数据"));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("蓝领");
        options2Items_01.add("少女");
        //options2Items_01.add("东莞");
        //options2Items_01.add("珠海");
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("白领");
        //options2Items_02.add("岳阳");
        //options2Items_02.add("株洲");
        //options2Items_02.add("衡阳");
        ArrayList<String> options2Items_03 = new ArrayList<>();
        options2Items_03.add("礼品鲜花");
        options2Items_03.add("餐饮外卖");
        // options2Items_03.add("丽人健身");
        // options2Items_03.add("休闲娱乐");

        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);

        /*--------数据源添加完毕---------*/

        ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();

        ArrayList<String> options3Items_01_01 = new ArrayList<>();
        options3Items_01_01.add("工人");
        options3Items_01_01.add("大学生");

        ArrayList<String> options3Items_01_02 = new ArrayList<>();
        options3Items_01_02.add("女工人");
        options3Items_01_02.add("大女学生");

        options3Items_01.add(options3Items_01_01);
        options3Items_01.add(options3Items_01_02);

        ArrayList<ArrayList<String>> options3Items_02 = new ArrayList<ArrayList<String>>();


        ArrayList<String> options3Items_02_01 = new ArrayList<>();
        options3Items_02_01.add("女工人111");
        options3Items_02_01.add("大女学生11");

        options3Items_02.add(options3Items_02_01);

        ArrayList<ArrayList<String>> options3Items_03 = new ArrayList<ArrayList<String>>();

        ArrayList<String> options3Items_03_01 = new ArrayList<>();
        options3Items_03_01.add("工人");
        options3Items_03_01.add("大学生");

        ArrayList<String> options3Items_03_02 = new ArrayList<>();
        options3Items_03_02.add("女工人");
        options3Items_03_02.add("大女学生");

        options3Items_03.add(options3Items_03_01);
        options3Items_03.add(options3Items_03_02);

        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);
        options3Items.add(options3Items_03);



    }

    //private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    //private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    //private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    public interface PickerItemSelectListener {

        void onItemSelect(String options1, String options2, String options3, View v);
    }
}
