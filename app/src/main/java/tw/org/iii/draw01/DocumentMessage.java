package tw.org.iii.draw01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gui on 2016/9/26.
 */
public class DocumentMessage extends AppCompatActivity {
    private ImageView img1,img2,img3,img4,img5;
    private TextView tv ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv=(TextView)findViewById(R.id.tv);

    }


}
