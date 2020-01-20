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

    private float sinX = 0;

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
                    float dX = x - px;
                    float dY = y - py;

                    sinX += 0.01;
                    luckyID.setScaleX((float)(Math.abs((2 * Math.sin(sinX))) + 2));
                    luckyID.setScaleY((float)(Math.abs((2 * Math.sin(sinX))) + 2));

                    if (px != -1 && py != -1) {
                        X += dX;
                        Y += dY;

                        float screen_width = myLayout.getMeasuredWidth();
                        float screen_height = myLayout.getMeasuredHeight();
                        float img_width = luckyID.getWidth();
                        float img_height = luckyID.getHeight();
                        float scale_x = luckyID.getScaleX();
                        float scale_y = luckyID.getScaleY();

                        float ax = (img_width  * (scale_x - 1)) / 2;
                        float ay = (img_height * (scale_y - 1)) / 2;

                        Log.d("TUPOI AYX", String.format("IW: %f IH: %f", img_width, img_height));

                        /*float Xstrih = X - ax;
                        float Ystrih = Y - ay;*/

                        if (X - ax > 0) {
                            X = ax;
                            Log.d("Ae1", String.format("AX: %f", ax));
                        }
                        if (X + img_width + ax < screen_width) {
                            X = screen_width - img_width - ax;
                            Log.d("Ae3", String.format("SW: %f; IW: %f", screen_width, img_width));
                        }

                        if (Y - ay > 0) {
                            Y = ay;
                            Log.d("Ae2", String.format("AY: %f", ay));
                        }
                        if (Y + img_height + ay < screen_height) {
                            Y = screen_height - img_height - ay;
                            Log.d("Ae4", String.format("SH: %f, IH: %f", screen_height, img_height));
                        }

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
