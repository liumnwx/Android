package com.example.aeon.calculate;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.aeon.calculate.R;

public class MainActivity extends AppCompatActivity {

    //定义控件变量
    private Button bt_0, bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9,
            bt_plus, bt_sub, bt_equal, bt_mul, bt_div, bt_zf, bt_per, bt_dot, bt_ac; //按钮
    private EditText et_result; //输入框
    private final String TAG = "MyCal"; //日志输出TAG


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiemian);
        //绑定控件变量, 即实例化
        bt_0 = (Button) findViewById(R.id.bt_0);
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        bt_6 = (Button) findViewById(R.id.bt_6);
        bt_7 = (Button) findViewById(R.id.bt_7);
        bt_8 = (Button) findViewById(R.id.bt_8);
        bt_9 = (Button) findViewById(R.id.bt_9);
        bt_plus = (Button) findViewById(R.id.bt_plus);
        bt_sub = (Button) findViewById(R.id.bt_sub);
        bt_ac = (Button) findViewById(R.id.bt_ac);
        bt_div = (Button) findViewById(R.id.bt_div);
        bt_mul = (Button) findViewById(R.id.bt_mul);
        bt_zf = (Button) findViewById(R.id.bt_zf);
        bt_equal = (Button) findViewById(R.id.bt_equal);
        bt_per = (Button) findViewById(R.id.bt_per);
        bt_dot = (Button) findViewById(R.id.bt_dot);

        et_result = (EditText) findViewById(R.id.et_result);

        MyOnClickLis myOnClickLis = new MyOnClickLis();

        bt_0.setOnClickListener(myOnClickLis);
        bt_1.setOnClickListener(myOnClickLis);
        bt_2.setOnClickListener(myOnClickLis);
        bt_3.setOnClickListener(myOnClickLis);
        bt_4.setOnClickListener(myOnClickLis);
        bt_5.setOnClickListener(myOnClickLis);
        bt_6.setOnClickListener(myOnClickLis);
        bt_7.setOnClickListener(myOnClickLis);
        bt_8.setOnClickListener(myOnClickLis);
        bt_9.setOnClickListener(myOnClickLis);
        bt_plus.setOnClickListener(myOnClickLis);
        bt_sub.setOnClickListener(myOnClickLis);
        bt_mul.setOnClickListener(myOnClickLis);
        bt_div.setOnClickListener(myOnClickLis);
        bt_equal.setOnClickListener(myOnClickLis);
        bt_ac.setOnClickListener(myOnClickLis);
        bt_dot.setOnClickListener(myOnClickLis);
        bt_zf.setOnClickListener(myOnClickLis);
        bt_per.setOnClickListener(myOnClickLis);


    }

    //定义事件处理器, 计算器上的按钮触摸点击事件统一由这个类处理
    private class MyOnClickLis implements View.OnClickListener{
        private String strOp1 = "0";
        private String strOp2 = "";
        private String operation = "";
        private String strResult = "0"; //输入框展示的内容
        private int lastInputType = 0; //0代表数字, 1代表运算符

        private void numInput(int num){
            Log.i(TAG, "当前点击的按钮是数字" + num);
            if(strResult.equals("0")) {
                strResult = "" + num; //结果为"0", 代表尚未输入操作数, 结果变为该操作数
            }
            else if(lastInputType == 0){
                strResult = strResult + num; //上一个输入的是数字时, 字符串进行拼接
            }
            else if(lastInputType == 1){
                strResult = "" + num; //上一个输入的是运算符时, 结果变为该操作数
            }
            et_result.setText(strResult);
            lastInputType = 0;
        }

        private void dotInput(){
            if (strResult.indexOf('.')==-1){
                strResult = strResult+'.';
                et_result.setText(strResult);
            }
        }

        private void zfInput(){
            strResult = String.valueOf(0-Double.parseDouble(strResult));
            et_result.setText(strResult);
        }

        private void operationInput(String opt){
            if(lastInputType==1){
                operation=opt;
                lastInputType=1;
                return;
            }

            if(operation.isEmpty()){
                operation=opt;
                strOp1=strResult;
            }
            else if(!strOp1.isEmpty()){
                strOp2=strResult;
                if(operation.equals("+")){
                    double op1 = Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(op1+op2);
                    et_result.setText(strResult);
                    strOp1=strResult;
                    strOp2="";
                    operation=opt;
                }
                if(operation.equals("-")){
                    double op1 = Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(op1-op2);
                    et_result.setText(strResult);
                    strOp1=strResult;
                    strOp2="";
                    operation=opt;
                }
                if(operation.equals("×")){
                    double op1 = Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(op1*op2);
                    et_result.setText(strResult);
                    strOp1=strResult;
                    strOp2="";
                    operation=opt;
                }
                if(operation.equals("÷")){
                    double op1 = Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(op1/op2);
                    et_result.setText(strResult);
                    strOp1=strResult;
                    strOp2="";
                    operation=opt;
                }
            }
            lastInputType=1;
        }

        private void  perInput(){
            strResult = String.valueOf(Double.parseDouble(strResult)/100);
            et_result.setText(strResult);
        }

        private void ACInput(){
            strResult="0";
            lastInputType = 0;
            operation="";
            strOp1=strOp2="";
            et_result.setText(strResult);
        }
        private void equalInput(){
            if(operation.isEmpty()){
                return;
            }
            if(lastInputType==1&&!strOp1.isEmpty()){
                if(operation.equals("+")){
                    double op1 = Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strResult);
                    strResult = String.valueOf(op1+op2);
                    et_result.setText(strResult);
                }
            }
            else if(!strOp1.isEmpty()&&lastInputType==0){
                strOp2 = strResult;
                if(operation.equals("+")){
                    double op1=Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(op1+op2);
                    et_result.setText(strResult);
                    strOp1="";
                    strOp2="";
                    operation="";
                }
            }
            if(lastInputType==1&&!strOp1.isEmpty()){
                if(operation.equals("-")){
                    double op1 = Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strResult);
                    strResult = String.valueOf(op2-op1);
                    et_result.setText(strResult);
                }
            }
            else if(!strOp1.isEmpty()&&lastInputType==0){
                strOp2 = strResult;
                if(operation.equals("-")){
                    double op1=Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(op1-op2);
                    et_result.setText(strResult);
                    strOp1="";
                    strOp2="";
                    operation="";
                }
            }
            if(lastInputType==1&&!strOp1.isEmpty()){
                if(operation.equals("×")){
                    double op1 = Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strResult);
                    strResult = String.valueOf(op1*op2);
                    et_result.setText(strResult);
                }
            }
            else if(!strOp1.isEmpty()&&lastInputType==0){
                strOp2 = strResult;
                if(operation.equals("×")){
                    double op1=Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(op1*op2);
                    et_result.setText(strResult);
                    strOp1="";
                    strOp2="";
                    operation="";
                }
            }
            if(lastInputType==1&&!strOp1.isEmpty()){
                if(operation.equals("÷")){
                    double op1 = Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strResult);
                    strResult = String.valueOf(op2/op1);
                    et_result.setText(strResult);
                }
            }
            else if(!strOp1.isEmpty()&&lastInputType==0){
                strOp2 = strResult;
                if(operation.equals("÷")){
                    double op1=Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strOp2);
                    strResult = String.valueOf(op1/op2);
                    et_result.setText(strResult);
                    strOp1="";
                    strOp2="";
                    operation="";
                }
            }
        }

        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.bt_0:
                    numInput(0);
                    break;
                case R.id.bt_1:
                    numInput(1);
                    break;
                case R.id.bt_2:
                    numInput(2);
                    break;
                case R.id.bt_3:
                    numInput(3);
                    break;
                case R.id.bt_4:
                    numInput(4);
                    break;
                case R.id.bt_5:
                    numInput(5);
                    break;
                case R.id.bt_6:
                    numInput(6);
                    break;
                case R.id.bt_7:
                    numInput(7);
                    break;
                case R.id.bt_8:
                    numInput(8);
                    break;
                case R.id.bt_9:
                    numInput(9);
                    break;
                case R.id.bt_plus:
                    operationInput("+");
                    break;
                case R.id.bt_sub:
                    operationInput("-");
                    break;
                case R.id.bt_mul:
                    operationInput("×");
                    break;
                case R.id.bt_div:
                    operationInput("÷");
                    break;
                case R.id.bt_per:
                    perInput();
                    break;
                case R.id.bt_zf:
                    zfInput();
                    break;
                case R.id.bt_ac:
                    ACInput();
                    break;
                case R.id.bt_equal:
                    equalInput();
                    break;
                case R.id.bt_dot:
                    dotInput();
                    break;
            }
        }
    }
}
