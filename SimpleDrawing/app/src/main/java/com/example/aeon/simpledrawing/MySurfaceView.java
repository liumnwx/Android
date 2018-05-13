package com.example.aeon.simpledrawing;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.aeon.simpledrawing.R;

/**
 * Created by Lenovo on 2018/5/7.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private boolean mIsDrawing;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private Canvas cacheCanvas;
    private Bitmap cache_bitmap;

    public MySurfaceView(Context context, AttributeSet attrs,int defStyle) {
        super(context,attrs,defStyle);
        initView();
    }
    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView();
    }
    public MySurfaceView(Context context) {
        super(context);
        initView();
    }

    private void initView(){
        mHolder=getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        mPath = new Path();
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int x=(int)event.getX();
        int y=(int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void run() {
        long start= System.currentTimeMillis();
        while (mIsDrawing){
            draw();
        }
        long end = System.currentTimeMillis();
        if(end-start<100){
            try {
                Thread.sleep(100-(end-start));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        mIsDrawing=true;
        initCacheBitmapAndDrawBackground(true);
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        mIsDrawing=false;
    }


    private void initCacheBitmapAndDrawBackground(boolean isFirst) {
        int view_width=getWidth();
        int view_height=getHeight();

        Bitmap bg_bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.banana);
        int bgBitmapWidth=bg_bitmap.getWidth();
        int bgBitmapHeight=bg_bitmap.getHeight();
        if(isFirst){
            cache_bitmap=Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
            cacheCanvas=new Canvas();
            cacheCanvas.setBitmap(cache_bitmap);
        }

        Matrix matrix=new Matrix();
        float scale=1.0f;
        int left = 0,top=0;
        if(bgBitmapWidth<=view_width&&bgBitmapHeight<=view_height){
            left=(view_width-bgBitmapWidth)/2;
            top=(view_height-bgBitmapHeight)/2;
        }else{
            double viewRatio=view_height/(double)view_width;
            double bitmapRatio=bgBitmapHeight/(double)bgBitmapWidth;
            if(bitmapRatio>viewRatio){
                top=0;
                left=(int)((view_width-view_height/(double)bitmapRatio)/2);
                scale=view_width/(float)bgBitmapWidth;
            }else{
                left=0;
                top=(int)((view_height-bitmapRatio*view_width)/2);
                scale=view_width/(float)bgBitmapWidth;
            }
        }
        matrix.postScale(scale,scale);
        matrix.postTranslate(left,top);

        cacheCanvas.drawColor(Color.WHITE);
        cacheCanvas.drawBitmap(bg_bitmap,matrix,null);
    }

    private void draw(){
        try {
            mCanvas=mHolder.lockCanvas();
            cacheCanvas.drawPath(mPath,mPaint);
            mCanvas.drawBitmap(cache_bitmap,0,0,new Paint());
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(mCanvas!=null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    public void clean(){
        initView();
        initCacheBitmapAndDrawBackground(false);
    }

    public void setYellowPaint(){
        mPath.reset();
        mPaint.setColor(Color.YELLOW);
    }

    public void setBlackPaint(){
        mPath.reset();
        mPaint.setColor(Color.BLACK);
    }

}


