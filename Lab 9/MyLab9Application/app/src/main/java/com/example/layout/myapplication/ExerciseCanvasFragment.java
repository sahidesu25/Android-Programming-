package com.example.layout.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by kevin on 7/6/2014.
 */
public class ExerciseCanvasFragment extends Fragment {

    private Canvas canvas;
    private Bitmap backgroundBitmap;

    public static ExerciseCanvasFragment newInstance(int sectionNumber) {
        ExerciseCanvasFragment fragment = new ExerciseCanvasFragment();
        return fragment;
    }

    public ExerciseCanvasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initialization();
    }

    private void initialization(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_canvas, container, false);

        final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageview);

        float width = (float) imageView.getWidth();
        float height = (float) imageView.getHeight();

        backgroundBitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        final Canvas backgroundCanvas = new Canvas(backgroundBitmap);
        int scale = backgroundCanvas.getWidth();
        backgroundCanvas.scale(scale, scale);

        imageView.setImageBitmap(backgroundBitmap);

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final int id = view.getId();
                Intent intent;

                switch (id){
                    case R.id.button1:
                        drawShadedCircle(backgroundCanvas);
                        imageView.invalidate();
                        break;
                    case R.id.button2:
                        drawTitle(backgroundCanvas);
                        imageView.invalidate();
                        break;
                    case R.id.button3:
                        drawScale(backgroundCanvas);
                        imageView.invalidate();
                        break;
                    case R.id.button4:
                        drawLogoBitmap(backgroundCanvas);
                        imageView.invalidate();
                        break;
                    case R.id.button5:
                        drawHand(backgroundCanvas, 315.0f);
                        imageView.invalidate();
                        break;
                    default:
                        break;
                }

            }
        };

        ((Button) rootView.findViewById(R.id.button1)).setOnClickListener(onClickListener);
        ((Button) rootView.findViewById(R.id.button2)).setOnClickListener(onClickListener);
        ((Button) rootView.findViewById(R.id.button3)).setOnClickListener(onClickListener);
        ((Button) rootView.findViewById(R.id.button4)).setOnClickListener(onClickListener);
        ((Button) rootView.findViewById(R.id.button5)).setOnClickListener(onClickListener);
        return rootView;
    }

    void drawShadedCircle(Canvas canvas){
        int width = canvas.getWidth();

        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);

        //Exercise: add a shader to the paint
        paint.setShader(new LinearGradient(0.40f,0.0f,0.6f,1.0f,
                Color.rgb(0xf0,0xf4,0xf0),
                Color.rgb(0x30,0x31,0x30),
        Shader.TileMode.CLAMP));

        canvas.drawCircle(0.5f, 0.5f, 0.35f, paint);

    }


    void drawTitle(Canvas canvas){
        int width = canvas.getWidth();

        Paint titlePaint = new Paint();
        titlePaint.setColor(Color.rgb(0xff, 0x66, 0x00));
        titlePaint.setAntiAlias(true);
        titlePaint.setTypeface(Typeface.DEFAULT_BOLD);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTextSize(0.05f);
        titlePaint.setTextScaleX(0.8f);

        Path titlePath = new Path();
        titlePath.addArc(new RectF(0.3f, 0.3f, 0.70f, 0.70f), -180.0f, -180.0f);

        canvas.drawTextOnPath("Syracuse University", titlePath, 0.0f,0.0f, titlePaint);
    }


    void drawLogoBitmap(Canvas canvas){
        Paint paint = new Paint();

        Bitmap logo = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.su3);
        canvas.save(Canvas.MATRIX_SAVE_FLAG);


        canvas.translate(0.60f, 0.35f);
        RectF rect = new RectF(0.0f, 0.0f, 0.15f, 0.15f);
        canvas.drawBitmap(logo, null, rect, paint);

        canvas.restore();
    }

    void drawScale (Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0x9f004d0f);
        paint.setStrokeWidth(0.003f);
        paint.setAntiAlias(true);
        paint.setTextSize(0.025f);
        paint.setTypeface(Typeface.SANS_SERIF);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setLinearText(true);

        int degreesPerNick = 3;
        canvas.drawCircle(0.5f, 0.5f, 0.35f, paint);

        int total = 360/degreesPerNick;
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        for (int i = 0; i < total; ++i) {
            float y1 = 0.15f;
            float y2 = y1 + 0.020f;

            canvas.drawLine(0.5f, y1, 0.5f, y2, paint);

            if (i % 5 == 0) {
                int value = i*degreesPerNick;
                String valueString = Integer.toString(value);
                canvas.drawText(valueString, 0.5f, y2 + 0.025f, paint);
            }
            canvas.rotate(degreesPerNick,0.5f,0.5f);


        }

        canvas.restore();
    }

    void drawHand (Canvas canvas, float angle){
        Paint handPaint = new Paint();
        handPaint.setAntiAlias(true);
        handPaint.setColor(0xff392f2c);
        handPaint.setShadowLayer(0.01f, -0.005f, -0.005f, 0x7f000000);
        handPaint.setStyle(Paint.Style.FILL);

        Path handPath = new Path();
        handPath.moveTo(0.5f, 0.5f + 0.2f);
        handPath.lineTo(0.5f - 0.010f, 0.5f + 0.2f - 0.007f);
        handPath.lineTo(0.5f - 0.002f, 0.5f - 0.28f);
        handPath.lineTo(0.5f + 0.002f, 0.5f - 0.28f);
        handPath.lineTo(0.5f + 0.010f, 0.5f + 0.2f - 0.007f);
        handPath.lineTo(0.5f, 0.5f + 0.2f);
        handPath.addCircle(0.5f, 0.5f, 0.025f, Path.Direction.CW);

        Paint handScrewPaint = new Paint();
        handScrewPaint.setAntiAlias(true);
        handScrewPaint.setColor(0xff493f3c);
        handScrewPaint.setStyle(Paint.Style.FILL);

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        // Exercise: the hand should be in the correct angle.
        //canvas.rotate(***, ***, ***);
        canvas.drawPath(handPath, handPaint);
        canvas.drawCircle(0.5f, 0.5f, 0.01f, handScrewPaint);
        canvas.restore();
    }

}