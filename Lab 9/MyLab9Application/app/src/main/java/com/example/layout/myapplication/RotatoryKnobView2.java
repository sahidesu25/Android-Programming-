package com.example.layout.myapplication;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public final class RotatoryKnobView2 extends View  {
    private static final String TAG = ExerciseRotaryKnobView.class.getSimpleName();

    private float currentValue=300;
    Bitmap backgroundBitmap;
    Paint backgroundPaint, handPaint,  handScrewPaint;
    Path handPath;
    private String mTitle;
    private int mLogoID;
    private int color;
    private String hexcolor;
    int flag=0;

    private OnRotateListener listener = null;


    public RotatoryKnobView2(Context context) {
        super(context);
        init(null);
    }



    public RotatoryKnobView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public RotatoryKnobView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        // This layer setting is very important, or some drawings may not show up
        // However, this will have performance penalty.
        //setLayerType(View.LAYER_TYPE_SOFTWARE, null);
         initDrawingTools();
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs){
        if (attrs != null) {
            String packageName = "http://schemas.android.com/apk/res-auto";
            mTitle = attrs.getAttributeValue(packageName, "title_");
            if (mTitle == null) mTitle = "";
            mLogoID = attrs.getAttributeResourceValue(packageName, "image", R.drawable.su3);
            Resources res = getResources();
            int mycolor=res.getColor(R.color.Red);

if(this.getId()==R.id.rotaryKnobView)
            this.color=attrs.getAttributeResourceValue(packageName, "color_", Color.GREEN);
            if(this.getId()==R.id.rotaryKnobView2)
                this.color=mycolor;
            if(this.getId()==R.id.rotaryKnobView3)
                this.color=attrs.getAttributeResourceValue(packageName, "color_", Color.BLUE);

        }
    }

    private void initDrawingTools(){
        backgroundPaint = new Paint();
        backgroundPaint.setFilterBitmap(true);

        handPaint = new Paint();
        handPaint.setAntiAlias(true);
        handPaint.setColor(0xff392f2c);
        handPaint.setShadowLayer(0.01f, -0.005f, -0.005f, 0x7f000000);
        handPaint.setStyle(Paint.Style.FILL);

        handPath = new Path();
        handPath.moveTo(0.5f, 0.5f + 0.2f);
        handPath.lineTo(0.5f - 0.010f, 0.5f + 0.2f - 0.01f);
        handPath.lineTo(0.5f - 0.002f, 0.5f - 0.28f);
        handPath.lineTo(0.5f + 0.002f, 0.5f - 0.28f);
        handPath.lineTo(0.5f + 0.010f, 0.5f + 0.2f - 0.01f);
        handPath.lineTo(0.5f, 0.5f + 0.2f);
        handPath.addCircle(0.5f, 0.5f, 0.025f, Path.Direction.CW);

        handScrewPaint = new Paint();
        handScrewPaint.setAntiAlias(true);
        handScrewPaint.setColor(0xff493f3c);
        handScrewPaint.setStyle(Paint.Style.FILL);
    }

    public interface OnRotateListener {
        public void onRotate(int arg);
    }

    public void setOnRotateListener( OnRotateListener l )
    {
        listener = l;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int chosenWidth = chooseDimension(widthMode, widthSize);
        int chosenHeight = chooseDimension(heightMode, heightSize);

        int chosenDimension = Math.min(chosenWidth, chosenHeight);

        setMeasuredDimension(chosenDimension, chosenDimension);
    }

    private int chooseDimension(int mode, int size) {
        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.EXACTLY) {
            return size;
        } else { // (mode == MeasureSpec.UNSPECIFIED)
            return getPreferredSize();
        }
    }

    // in case there is no size specified
    private int getPreferredSize() {
        return 300;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        if (backgroundBitmap != null) {
          // Matrix matrix = new Matrix();
           //matrix.setRotate(currentValue,width/2 ,height/2 );
            canvas.drawBitmap(backgroundBitmap,0,0, backgroundPaint);
        }
        //drawShadedCircle(canvas,currentValue);
        float scale = getWidth();
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(scale, scale);
        drawShadedCircle(canvas, currentValue);
        drawMarker(canvas, currentValue);
        drawScale(canvas,currentValue);
        canvas.restore();
    }

        //drawingExperiment(canvas);



    // Test the drawing APIs when the hardware acceleration is on/off
    void drawingExperiment(Canvas canvas){
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(getWidth(), getWidth());

        Paint paint = new Paint();
        paint.setColor(0x9f004d0f);
        paint.setTextSize(0.05f);
        paint.setLinearText(true);

        canvas.drawPath(handPath, handPaint);
        canvas.drawCircle(0.5f, 0.5f, 0.05f, handScrewPaint);
        canvas.drawText("Hello", 0.3f, 0.5f, paint);
        canvas.drawRect(0.3f, 0.3f, 0.4f, 0.4f, handPaint);

        canvas.restore();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        regenerateBackground();
    }

    private void regenerateBackground() {
        float width = (float) getWidth();
        float height = (float) getHeight();

        backgroundBitmap = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
        Canvas backgroundCanvas = new Canvas(backgroundBitmap);

        float scale = (float) getWidth();

        backgroundCanvas.save(Canvas.MATRIX_SAVE_FLAG);
        backgroundCanvas.scale(scale, scale);


        drawTitle(backgroundCanvas);
        drawLogoBitmap(backgroundCanvas);
       drawScale(backgroundCanvas, currentValue);
        drawMarker(backgroundCanvas,255);

        backgroundCanvas.restore();
    }


    void drawShadedCircle(Canvas canvas, float angle){

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.rotate(angle, 0.5f, 0.5f); int width = canvas.getWidth();
        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        int[] Colors_inner={Color.rgb(0xb0,0xb5,0xb0),
        Color.rgb(0xe0,0xe5,0xe0),Color.rgb(0xb0,0xb5,0xb0),
        Color.rgb(0x40,0x41,0x40),Color.rgb(0xb0,0xb5,0xb0),
                Color.rgb(0xe0,0xe5,0xe0),Color.rgb(0xb0,0xb5,0xb0),
        Color.rgb(0x40,0x41,0x40),Color.rgb(0xb0,0xb5,0xb0)};
        float[] positions_inner={0.0f,0.125f,0.25f,0.37f,0.5f,0.625f,0.75f,0.875f,1.0f};
        paint.setShader(new SweepGradient(0.5f, 0.5f, Colors_inner, positions_inner));
        canvas.drawCircle(0.5f, 0.5f, 0.31f, paint);
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(0.019f);
        paint2.setColor(Color.GRAY);

        canvas.drawCircle(0.5f, 0.5f, 0.31f, paint2);
        canvas.restore();
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

        canvas.drawTextOnPath(mTitle, titlePath, 0.0f, 0.0f, titlePaint);
    }



    void drawLogoBitmap(Canvas canvas){
        Paint paint = new Paint();

        Bitmap logo = BitmapFactory.decodeResource(getContext().getResources(), mLogoID);
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.translate(0.60f, 0.35f);
        RectF rect = new RectF(0.0f, 0.0f, 0.12f, 0.12f);
        canvas.drawBitmap(logo, null, rect, paint);
        canvas.restore();
    }
    void drawMarker(Canvas canvas, float angle)
    {

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        int color_=Color.BLUE;
        paint.setColor(color);
        paint.setStrokeWidth(0.02f);
        paint.setAntiAlias(true);
        paint.setTextSize(0.025f);
        paint.setTypeface(Typeface.SANS_SERIF);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setLinearText(true);
        float y1 = 0.20f;
        float y2 = y1 + 0.045f;
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        //canvas.rotate(230, 0.5f, 0.5f);
        canvas.rotate(angle, 0.5f, 0.5f);

        canvas.drawLine(0.5f, y1, 0.5f, y2, paint);


        canvas.restore();
    }

    void drawScale (Canvas canvas, float angle){
        int degreesPerNick = 15;

        Paint paint = new Paint();
        Paint offpaint=new Paint();

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(0.02f);
        paint.setAntiAlias(true);
        paint.setTextSize(0.025f);
        paint.setTypeface(Typeface.SANS_SERIF);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setLinearText(true);

        offpaint.setStyle(Paint.Style.STROKE);
        offpaint.setColor( Color.rgb(0xa0,0xa5,0xa0));
        offpaint.setStrokeWidth(0.02f);
        offpaint.setAntiAlias(true);
        offpaint.setTextSize(0.025f);
        offpaint.setTypeface(Typeface.SANS_SERIF);
        offpaint.setTextAlign(Paint.Align.CENTER);
        offpaint.setLinearText(true);

        //canvas.drawCircle(0.7f, 0.7f, 0.35f, paint);

        int total = 300/degreesPerNick;

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
       // canvas.rotate(-90,0.5f,0.5f);
        for (int i = 0; i < total; ++i) {
            float y1 = 0.08f;
            float y2 = y1 + 0.040f;
            int degree = i*degreesPerNick;
            String value="Min";
            if(degree==270)
            {
                Paint paint2=new Paint();
                paint2.setStyle(Paint.Style.STROKE);
                paint2.setColor(Color.BLACK);
                paint2.setStrokeWidth(0.003f);
                paint2.setAntiAlias(true);
                paint2.setTextSize(0.05f);
                paint2.setTypeface(Typeface.SANS_SERIF);
                paint2.setTextAlign(Paint.Align.CENTER);
                paint2.setLinearText(true);
                canvas.drawText(value, 0.6f, y1, paint2);


            }

            //if (degree>=135&&degree<225) {

                // canvas.drawText(value,0.5f,y2+0.025f,paint);
                // int value = i*degreesPerNick;
                // String valueString = Integer.toString(value);
                //canvas.drawText(valueString, 0.5f, y2 + 0.025f, paint);
                // String value="Min";
                //canvas.drawText(value,0.5f,y2+0.025f,paint);
           // }
           // else
            {

                 if((i!=0 )&&( i!=1) &&(i!=2) &&(i!=3))
                {
                  if(angle<degree)
                  {
                      canvas.drawLine(0.5f, y1, 0.5f, y2, paint);

                  }
                  else
                  {
                      canvas.drawLine(0.5f, y1, 0.5f, y2, offpaint);

                  }

                }

            }
            canvas.rotate(degreesPerNick, 0.5f, 0.5f);

        }
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
       Matrix matrix = new Matrix();
       matrix.setRotate(180,getWidth()/2 ,getHeight() / 2);
        canvas.drawBitmap(backgroundBitmap, matrix, backgroundPaint);
        invalidate();


        canvas.restore();
    }




    public void setAngle(float angle) {
        currentValue=angle;
        invalidate();

    }

    private float theta_old=0f;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float theta = getTheta(x,y);

        switch(event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                theta_old = theta;
                break;
            case MotionEvent.ACTION_MOVE:
                float delta_theta = theta - theta_old;
                theta_old = theta;
                int direction = (delta_theta > 0) ? 1 : -1;
                currentValue += 3*direction;

                if (currentValue < 0) currentValue = currentValue + 360;
                currentValue -= ((int)(currentValue /360)) * 360;
                invalidate();
                if(listener!=null)
                {
                    listener.onRotate((int)currentValue);
                }


                break;
        }
        return true;
    }

    private float getTheta(float x, float y)
    {
        float sx = x - (getWidth() / 2.0f);
        float sy = y - (getHeight() / 2.0f);

        float length = (float)Math.sqrt( sx*sx + sy*sy);
        float nx = sx / length;
        float ny = sy / length;
        float theta = (float)Math.atan2( ny, nx );

        final float rad2deg = (float)(180.0/Math.PI);
        float thetaDeg = theta*rad2deg;

        return (thetaDeg < 0) ? thetaDeg + 360.0f : thetaDeg;
    }
}
