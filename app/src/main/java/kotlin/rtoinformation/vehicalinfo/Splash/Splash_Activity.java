package kotlin.rtoinformation.vehicalinfo.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import kotlin.rtoinformation.vehicalinfo.R;

public class Splash_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(this::nextIntent, 2000);
    }

    public void nextIntent() {
        Intent i = new Intent(Splash_Activity.this, Spalsh_StartActivity.class);
        i.putExtra("fromSplash", true);
        startActivity(i);
        finish();
    }
}