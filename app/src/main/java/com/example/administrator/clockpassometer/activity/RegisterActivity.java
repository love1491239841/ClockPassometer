package com.example.administrator.clockpassometer.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.clockpassometer.R;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText rePhone,reuser,reuseric,renumber,repassword,retpassword,reda1,reda2;
    private RadioGroup rergroup;
    private RadioButton radioButton;
    private Button register;
    private Spinner resp1,resp2;
    private String sex=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialization();
    }
    private void initialization(){
        rePhone=(TextInputEditText)findViewById(R.id.rephone);
        reuser=(TextInputEditText)findViewById(R.id.reuser);
        reuseric=(TextInputEditText)findViewById(R.id.reuseric);
        renumber=(TextInputEditText)findViewById(R.id.renumber);
        repassword=(TextInputEditText)findViewById(R.id.repassword);
        retpassword=(TextInputEditText)findViewById(R.id.retpassword);
        reda1=(TextInputEditText)findViewById(R.id.reda1);
        reda2=(TextInputEditText)findViewById(R.id.reda2);
        rergroup=(RadioGroup)findViewById(R.id.rergroup);
        rergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                sex = radioButton.getText().toString();
            }
        });
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new Click());

    }
    private boolean checkForm(){
        final String phone = rePhone.getText().toString();
        final String user = reuser.getText().toString();
        final String seric = reuseric.getText().toString();
        final String number = renumber.getText().toString();
        final String sexs = sex;
        final String password = repassword.getText().toString();
        final String tpassword = retpassword.getText().toString();
        boolean isPass =true;
        if (phone.isEmpty() || phone.length()!=11){
            rePhone.setError("手机号码错误");
            isPass=false;
        }else {
            rePhone.setError(null);
        }
        if(user.isEmpty()){
            reuser.setError("请输入姓名");
            isPass=false;
        }else {
            reuser.setError(null);
        }
        if (seric.isEmpty() || seric.length()!=18){
            reuseric.setError("身份证号码错误");
            isPass=false;
        }else {
            reuseric.setError(null);
        }
        if (number.isEmpty() || number.length()!=9){
            renumber.setError("学号格式错误");
            isPass=false;
        }else {
            renumber.setError(null);
        }
        if (sexs==null){
            Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
            isPass=false;
        }
        if (password.isEmpty() || password.length()<=6){
            repassword.setError("密码长度不能小于六位");
            isPass=false;
        }else {
            repassword.setError(null);
        }
        if (tpassword.isEmpty() || tpassword.length()<=6 || !tpassword.equals(password)){
            retpassword.setError("密码验证错误");
            isPass=false;
        }
        return isPass;
    }

    private class Click implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.register:
                    if (checkForm()){
                        Toast.makeText(RegisterActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegisterActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

}
