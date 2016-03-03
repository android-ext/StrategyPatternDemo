package com.zxxk.json.strategypatterndemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zxxk.json.strategypatterndemo.R;
import com.zxxk.json.strategypatterndemo.factory.CashContext;

/**
 * 策略模式： 定义了算法家族，分别封装起来，让它们之间可以互相替换，次模式让算法的变化，不会影响到使用算法的客户
 *
 * @description: 策略模式测试
 * @Created Time: 2016/3/2 8:30
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    /** 单价 */
    private EditText mPriceET;
    /** 数量 */
    private EditText mCountET;
    /** 计算方式选择控件 */
    private Spinner mSpinner;
    /** 总价 */
    private TextView mTotalTV;
    /** 计算方式 */
    private static final String[] mTypes = new String[]{"正常收费", "打8折", "满300返100"};
    /** 选择的计算方式下标 */
    private int mSelectedIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        initViews();
    }

    /**
     * @description: 初始化布局控件
     * @Created Time: 2016/3/2 8:30
     * @param
     * @return
     */
    private void initViews() {

        mPriceET = initViewById(R.id.main_price_et);
        mCountET = initViewById(R.id.main_count_et);
        mSpinner = initViewById(R.id.main_calculate_type);
        mTotalTV = initViewById(R.id.main_total_tv);

        initSpinner();

        registerViewListener();
    }

    /**
     * @description: 初始化打折计算方式
     * @Created Time: 2016/3/2 8:46
     * @param
     * @return
     */
    private void initSpinner() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinenr_item_view, mTypes);

        mSpinner.setAdapter(adapter);
    }

    /**
     * @description: 控件注册监听器
     * @Created Time: 2016/3/2 8:42
     * @param
     * @return
     */
    private void registerViewListener() {

        initViewById(R.id.main_submit_btn).setOnClickListener(this);
        initViewById(R.id.main_reset_btn).setOnClickListener(this);
        mSpinner.setOnItemSelectedListener(this);
    }


    private <T extends View> T initViewById(int resId) {

        return (T) findViewById(resId);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_submit_btn:

                calculateTotalCash();
                break;

            case R.id.main_reset_btn:
                resetViewsValue();
                break;
        }
    }

    /**
     * @description: 计算总价
     * @Created Time: 2016/3/2 8:58
     * @param
     * @return
     */
    private void calculateTotalCash() {

        String priceStr = mPriceET.getText().toString().trim();
        if (validateView(priceStr)) {
            showToast("请输入合法的单价");
            return;
        }
        String countStr = mCountET.getText().toString().trim();
        if (validateView(countStr)) {
            showToast("请输入合法的总数");
            return;
        }

        CashContext cashContext = new CashContext(mTypes[mSelectedIndex]);
        double totalStr = cashContext.getResult(Integer.parseInt(priceStr) * Integer.parseInt(countStr));

        mTotalTV.setText(totalStr + " 元");
    }

    /**
     * @description 输入值有效性判断
     * @Created Time 2016/3/2 9:07
     * @param
     * @return
     */
    private boolean validateView(String value) {

        boolean isValid = true;
        if (TextUtils.isEmpty(value))
            return isValid;

        try {
            if (Integer.parseInt(value) > 0) {
                isValid = false;
            }
        } catch (Exception e) {

        }
        return isValid;
    }

    /**
     * @description: 重置控件的值
     * @Created Time: 2016/3/2 8:44
     * @param
     * @return
     */
    private void resetViewsValue() {

        mPriceET.setText("");
        mCountET.setText("");
        mTotalTV.setText("");
        mSpinner.setSelection(0);
    }

    //////////////Spinner监听回调方法//////////////
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        mSpinner.setSelection(position);
        mSelectedIndex = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

}
