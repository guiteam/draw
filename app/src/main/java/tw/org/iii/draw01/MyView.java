package tw.org.iii.draw01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by gui on 2016/9/26.
 */
public class MyView extends View{
    private LinkedList<LinkedList<HashMap<String,Float>>>  lines,recycle ;
    private GestureDetector gd ;
    private  LinkedList<HashMap> line ;
    private  HashMap<String,Float> point;
    private boolean isClear;

                                    //AttributeSet???
    public MyView(Context context,AttributeSet attrs) {
        super(context,attrs);
        lines = new LinkedList<>();
        gd= new GestureDetector(context,new MyGDListener());
        point = new HashMap<>();
        recycle = new LinkedList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p =  new Paint();
        if(isClear){
            canvas.drawColor(Color.WHITE);
            isClear = false;
        }
        p.setColor(Color.YELLOW);
        p.setStrokeWidth(10);
        for (LinkedList<HashMap<String,Float>> line:lines) {
            for (int i = 1; i < line.size(); i++) {
               canvas.drawLine(line.get(i - 1).get("x"), line.get(i - 1).get("y"),
                  line.get(i).get("x"), line.get(i).get("y"), p);
                    }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex= event.getX(),ey=event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            doTouchDown(ex, ey);
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
           doMove(ex,ey);
             }
        return true;
    }

    private  void addPoint(float x , float y) {
        HashMap<String,Float> point =
                new HashMap<>();
        point.put("x", x); point.put("y", y);
        lines.getLast().add(point);
        invalidate();
    }
   private void  doTouchDown(float x , float y){
       LinkedList<HashMap<String,Float>> line =
         new LinkedList<>();
        lines.add(line);
        addPoint(x,y);


   }
    private void  doMove(float x , float y){
        addPoint(x,y);


    }


    void clear(){
        lines.clear();
        isClear = true;
        invalidate();

        }


   void undo() {
        if (lines.size() > 0) {
            recycle.add(lines.removeLast());
            invalidate();
        }
    }
    void redo() {
        if (recycle.size() > 0) {
            lines.add(recycle.removeLast());
            invalidate();
        }
    }



    private class MyGDListener extends GestureDetector.SimpleOnGestureListener{


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("brad","onFling");
            return super.onFling(e1, e2, velocityX, velocityY);
    }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("brad","omDown");

            return super.onDown(e);
        }

    }
}
