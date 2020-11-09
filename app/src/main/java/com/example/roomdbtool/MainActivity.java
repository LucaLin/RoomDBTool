package com.example.roomdbtool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtNum;
    TextView txvResult;
    PersonDB personDB;
    List<Person> personList;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personDB = PersonDB.getInstance(this);

        edtName = findViewById(R.id.edtName);
        edtNum = findViewById(R.id.edtPhone);
        txvResult = findViewById(R.id.txvResult);

    }

    //新增資料到db
    public void insertData(View v) {
        if (!(TextUtils.isEmpty(edtName.getText().toString())) &&
                !(TextUtils.isEmpty(edtNum.getText().toString()))) {
            String name = edtName.getText().toString();
            String phoneNum = edtNum.getText().toString();

            final Person p = new Person(name, phoneNum);
            //用async進行耗時操作
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    personDB.personDao().insert(p);
                }
            });

            Toast.makeText(this, "新增資料成功!!!", Toast.LENGTH_SHORT).show();
            edtNum.setText("");
            edtName.setText("");
        }
    }

    //取出db資料並且顯示在畫面上
    public void queryData(View v) {
        result = "";
        //用async進行耗時操作
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                personList = personDB.personDao().getPersonList();
                if (personList != null) {

                    for (Person p : personList) {
                        result += String.format("第%d位名字：%s, 電話：%s%n", p.getId(), p.getName(), p.getPhoneNum());
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txvResult.setText(result);
                        }
                    });
                }

            }
        });


    }
}
