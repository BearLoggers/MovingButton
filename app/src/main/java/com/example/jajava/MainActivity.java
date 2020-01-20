package com.example.jajava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout myLayout = null;
    private ImageView luckyID = null;

    private float px = -1;
    private float py = -1;
    private float X;
    private float Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLayout = (RelativeLayout) findViewById(R.id.myLayout);
        luckyID = (ImageView) findViewById(R.id.luckyID);

        X = luckyID.getX();
        Y = luckyID.getY();

        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    x = x-250;
                    y = y-150;

                    float dX = x - px;
                    float dY = y - py;

                    if (px != -1 && py != -1) {
                        X += dX;
                        Y += dY;

                        Log.d("STATE", String.format("DX, DY: %f %f", dX, dY));
                        Log.d("STATE2", String.format("X, Y: %f %f", X, Y));
                        Log.d("STATE3", String.format("PX, PY: %f %f", px, py));
                        luckyID.setX(X);
                        luckyID.setY(Y);
                    }
                    px = x;
                    py = y;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d("DEAD", "deded");
                    px = -1;
                    py = -1;
                }
                return true;
            }
        });

    }
}
