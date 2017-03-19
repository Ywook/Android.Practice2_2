package com.examples.hw2_2;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    TextView tV, tV1, tV2, tV3, tV4, tV5;
    EditText eT1, eT2, eT3;
    Button btn1, btn2;
    Switch aSwitch;

    LinearLayout llayout, llayout2;
    FrameLayout flayout;
    GridLayout glayout;

    DatePicker datePicker;

    TimePicker timePicker;

    Chronometer chronometer;

    int PAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("레스토랑 예약시스템");

        init();
    }

    void init(){
        tV = (TextView) findViewById(R.id.textView);
        tV1 = (TextView) findViewById(R.id.textView1);
        tV2 = (TextView) findViewById(R.id.textView2);
        tV3 = (TextView) findViewById(R.id.textView3);
        tV4 = (TextView) findViewById(R.id.textView4);
        tV5 = (TextView) findViewById(R.id.textView5);

        eT1 = (EditText) findViewById(R.id.editText);
        eT2 = (EditText) findViewById(R.id.editText2);
        eT3 = (EditText) findViewById(R.id.editText3);

        aSwitch = (Switch) findViewById(R.id.switch1);

        llayout = (LinearLayout) findViewById(R.id.linearlayout1);
        llayout2 = (LinearLayout) findViewById(R.id.linearlayout2);

        flayout = (FrameLayout) findViewById(R.id.framelayout);

        glayout = (GridLayout) findViewById(R.id.gridlayout);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    switchT();
                }else{
                    switchF();
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPAGE(--PAGE);
                btn2.setEnabled(true);
                if(PAGE <= 1) {
                    btn1.setEnabled(false);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPAGE(++PAGE);
                btn1.setEnabled(true);
                if(PAGE >= 4){
                    btn2.setEnabled(false);
                }
            }
        });

    }

    void switchT(){
        llayout.setVisibility(View.VISIBLE);
        flayout.setVisibility(View.VISIBLE);

        tV.setVisibility(View.VISIBLE);

        chronometer.setVisibility(View.VISIBLE);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        setPAGE(PAGE);

        btn1.setEnabled(false);
        btn2.setEnabled(true);
    }

    void switchF(){
        PAGE = 1;

        llayout.setVisibility(View.GONE);
        flayout.setVisibility(View.GONE);

        tV.setVisibility(View.GONE);

        chronometer.setVisibility(View.GONE);

        eT1.setText("");
        eT2.setText("");
        eT3.setText("");
    }

    void setPAGE(int p){
        switch (p){
            case 1:
                datePicker.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.GONE);
                llayout2.setVisibility(View.GONE);
                glayout.setVisibility(View.GONE);
                break;
            case 2:
                datePicker.setVisibility(View.GONE);
                timePicker.setVisibility(View.VISIBLE);
                llayout2.setVisibility(View.GONE);
                glayout.setVisibility(View.GONE);
                break;
            case 3:
                datePicker.setVisibility(View.GONE);
                timePicker.setVisibility(View.GONE);
                llayout2.setVisibility(View.VISIBLE);
                glayout.setVisibility(View.GONE);
                break;
            case 4:
                String year = Integer.toString(datePicker.getYear());
                String month = Integer.toString(datePicker.getMonth() + 1);
                String day = Integer.toString(datePicker.getDayOfMonth());

                String hour = Integer.toString(timePicker.getCurrentHour()) ;
                String minute = Integer.toString(timePicker.getCurrentMinute());

                datePicker.setVisibility(View.GONE);
                timePicker.setVisibility(View.GONE);
                llayout2.setVisibility(View.GONE);
                glayout.setVisibility(View.VISIBLE);

                tV1.setText(year + "년 " + month + "월 " + day + "일");
                tV2.setText(hour + "시 " + minute +"분");
                tV3.setText(eT1.getText().toString()+"명");
                tV4.setText(eT2.getText().toString()+"명");
                tV5.setText(eT3.getText().toString()+"명");
                break;
        }
    }
}
