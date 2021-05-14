package app.android.ceylonelectricityboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    Animation topAnim,BottomAnim;
    ImageView Image;
    TextView text1,text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        BottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        Image = findViewById(R.id.imageView);
        text1 = findViewById(R.id.textView);
        text2 = findViewById(R.id.textView2);


        Image.setAnimation(topAnim);
        text1.setAnimation(BottomAnim);
        text2.setAnimation(BottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Dashboard.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);






    }
}