package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.zhoukao0709.R;

public class GuaGuaLe  extends View{
private    Path path;
    private   Paint paint;
    //临时画布
    private   Canvas Mcanvas;
    //临时图片
    private   Bitmap bitmap;
    private int mx;
    private int my;
    //手动擦掉的
    private Bitmap outbitmap;
    public GuaGuaLe(Context context) {
   this(context,null);

    }

    public GuaGuaLe(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }
        public  GuaGuaLe(Context context,@Nullable AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        init();
        }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获得初始宽高
        int hight = getMeasuredHeight();
        int width = getMeasuredWidth();
        //初始化
        bitmap=Bitmap.createBitmap(width,hight,Bitmap.Config.ARGB_8888);
        Mcanvas=new Canvas(bitmap);
        setPaint();
        Mcanvas.drawColor(Color.parseColor("#c0c0c0"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            paint.setStyle(Paint.Style.STROKE);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    canvas.drawBitmap(outbitmap,0,0,null);
    canvas.drawBitmap(bitmap,0,0,null);
        Mcanvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction();
     int    x= (int) event.getX();
      int  y= (int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mx=x;
                my=y;
                path.moveTo(mx,my);
            break;
            case  MotionEvent.ACTION_MOVE:
                int dx=Math.abs(x-mx);
                 int dy=Math.abs(y-my);
                 if(dx>3||dy>3)
                 {
                     path.lineTo(x,y);
                 }
                 mx=x;
                 my=y;
                break;
        }
    invalidate();

      return  true;
    }

    private void  setPaint(){
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(60);
    }

    private void init() {
        paint=new Paint();
        path=new Path();
        outbitmap= BitmapFactory.decodeResource(getResources(), R.drawable.psb);
    }
}
