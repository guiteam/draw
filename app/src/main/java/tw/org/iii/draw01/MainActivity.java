package tw.org.iii.draw01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private  MyView drawView ;
    private ImageView img;
    private File sdroot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = (MyView)findViewById(R.id.myView);
        sdroot = Environment.getExternalStorageDirectory();
        img = (ImageView)findViewById(R.id.img);

    }

    public  void takePic(View v){
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri outputFile = Uri.fromFile(new File(sdroot, "brad.jpg"));
        it.putExtra(MediaStore.EXTRA_OUTPUT, outputFile);

        startActivityForResult(it,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            //afterTakePic1(data);
            afterTakePic2();
        }else if (requestCode == RESULT_CANCELED){

        }
    }
    private void afterTakePic2(){
        Bitmap bmp = BitmapFactory.decodeFile(sdroot.getAbsolutePath() + "/brad.jpg");
        img.setImageBitmap(bmp);

    }


    public void  gotoDocument (View v){
       goPage();
    }
    private void goPage(){
        Intent it = new Intent(this,DocumentMessage.class);
        startActivity(it);
        finish();

    }




    public void clear(View v){
        drawView.clear();
        img.setImageResource(0);
        Log.d("brad","clear");
    }
    public void undo(View v){
        drawView.undo();
        Log.d("brad","undo");
    }
    public void redo(View v){
        drawView.redo();
        Log.d("brad","redo");
    }
}
