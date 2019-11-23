package com.example.myexperiment.calculater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myexperiment.R;

public class MainActivity extends AppCompatActivity{

    private Button jia1,jian1,cheng1,chu2,chu3,but1,but2,but3,but4,but5,but6,but7,but8,but9,but0,tuiwei,qufan,ac,dengyu;
    private String add,mult,sub,div,dd;
    private EditText etc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        relate();
    }

    protected void relate() {
        tuiwei=(Button)findViewById(R.id.tuiwei);
        qufan=(Button)findViewById(R.id.qufan);
        ac=(Button)findViewById(R.id.ac);
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText("0");
            }
        });
        etc= (EditText)findViewById(R.id.etc);
        etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Text=etc.getText().toString();
            }
        });
        jia1 = (Button) findViewById(R.id.jia);
        jia1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add=etc.getText().toString();
                etc.setText(etc.getText()+"+");
            }
        });
        jian1 = (Button) findViewById(R.id.jian);
        jian1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub = etc.getText().toString();
                etc.setText(etc.getText() + "-");
            }
        });
        cheng1 = (Button) findViewById(R.id.cheng);
        cheng1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mult=etc.getText().toString();
                etc.setText(etc.getText()+" * ");
            }
        });
        chu2 = (Button) findViewById(R.id.chu);
        chu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                div=etc.getText().toString();
                etc.setText(etc.getText()+"/");
            }
        });
        chu3 = (Button) findViewById(R.id.chu1);
        chu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                div=etc.getText().toString();
                etc.setText(etc.getText()+"/");
            }
        });
        but1 = (Button) findViewById(R.id.but_1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText(etc.getText()+"1");
            }
        });
        but0 = (Button) findViewById(R.id.but_0);
        but0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText(etc.getText()+"0");
            }
        });
        but2 = (Button) findViewById(R.id.but_2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText(etc.getText()+"2");
            }

        });
        but3 = (Button) findViewById(R.id.but_3);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText(etc.getText()+"3");
            }
        });
        but4 = (Button) findViewById(R.id.but_4);
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText(etc.getText()+"4");
            }
        });
        but5 = (Button) findViewById(R.id.but_5);
        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText(etc.getText()+"5");
            }
        });
        but6 = (Button) findViewById(R.id.but_6);
        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText(etc.getText()+"6");
            }
        });
        but7 = (Button) findViewById(R.id.but_7);
        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText(etc.getText()+"7");
            }
        });
        but8 = (Button) findViewById(R.id.but_8);
        but8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText(etc.getText()+"8");
            }
        });
        but9 = (Button) findViewById(R.id.but_9);
        but9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etc.setText(etc.getText()+"9");
            }
        });
        dengyu=(Button)findViewById(R.id.dengyu);
        dengyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dd=etc.getText().toString();
                if ( dd.contains("-"))
                    dd = dd.split("-")[1];
                else if ( dd.contains("/"))
                    dd = dd.split("/")[1];
                else if ( dd.contains("+")) {
                    for ( int i = 0 ; i < dd.length() ; i++ ) {
                        if ( dd.charAt(i) == '+' ) {
                            dd = dd.substring(i+1);
                            break;
                        }
                    }
                }
                else if ( dd.contains("*")) {
                    for ( int i = 0 ; i < dd.length() ; i++ ) {
                        if ( dd.charAt(i) == '*' ) {
                            dd = dd.substring(i+1);
                            break;
                        }
                    }
                }
                etc.setText(results()+"");
            }
        });
    }

    private int results() {
        String resultss = etc.getText().toString();
        resultss.replace(" ", "");
        if ( add != null )
            add.replace(" ", "");
        if ( sub != null )
            sub.replace(" ", "");
        if ( mult != null )
            mult.replace(" ", "");
        if ( div != null )
            div.replace(" ", "");
        if ( dd != null )
            dd.replace(" ", "");
        int t = 0;
        if (resultss.equals("")) {
            return 0;
        }
        if (resultss.indexOf("+") != -1) {
            t = Integer.parseInt(add) + Integer.parseInt(dd);
        }
        if (resultss.indexOf("-") != -1) {
            t = Integer.parseInt(sub) - Integer.parseInt(dd);
        }
        if (resultss.indexOf("*") != -1) {
            t = Integer.parseInt(mult) * Integer.parseInt(dd);

        }
        if (resultss.indexOf("/") != -1) {
            t = Integer.parseInt(div) / Integer.parseInt(dd);
        }
        return t;
    }
}

