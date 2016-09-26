package tw.org.iii.draw01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {
    private View img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        img =(ImageView)findViewById(R.id.welcome_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMain();
            }
        });

    }
    private void gotoMain(){
        Intent it = new Intent(this,MainActivity.class);
        startActivity(it);
        finish();

    }

}
