package kotlin.rtoinformation.vehicalinfo.Splash;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;


import com.pesonal.adsdk.AppManage;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlin.rtoinformation.vehicalinfo.Activities.MainActivity;
import kotlin.rtoinformation.vehicalinfo.BuildConfig;
import kotlin.rtoinformation.vehicalinfo.R;


public class Spalsh_StartActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQ_CODE_PERMISSION = 111;
    private Uri urishare;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashstart);

        if (Build.VERSION.SDK_INT >= 23) {
            checkMultiplePermissions();
        }

        AppManage.getInstance(Spalsh_StartActivity.this).loadInterstitialAd(this, AppManage.ADMOB_I[0], AppManage.FACEBOOK_I[0]);

        FrameLayout fl_native=findViewById(R.id.fl_native);
        AppManage.getInstance(Spalsh_StartActivity.this).showNative(fl_native, AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0]);
    }

    private void checkMultiplePermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissionsNeeded = new ArrayList();
            List<String> permissionsList = new ArrayList();
            if (!addPermission(permissionsList, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                permissionsNeeded.add("Write Storage");
            }
            if (!addPermission(permissionsList, "android.permission.READ_EXTERNAL_STORAGE")) {
                permissionsNeeded.add("Read Storage");
            }
            if (permissionsList.size() > 0) {
                requestPermissions((String[]) permissionsList.toArray(new String[permissionsList.size()]), REQ_CODE_PERMISSION);
                return;
            }
        }
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            return shouldShowRequestPermissionRationale(permission);
        }
        return true;
    }

    @SuppressLint("WrongConstant")
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != 101) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        if (requestCode == REQ_CODE_PERMISSION) {
            Map<String, Integer> perms = new HashMap();
            perms.put("android.permission.WRITE_EXTERNAL_STORAGE", Integer.valueOf(0));
            perms.put("android.permission.READ_EXTERNAL_STORAGE", Integer.valueOf(0));
            for (int i = 0; i < permissions.length; i++) {
                perms.put(permissions[i], Integer.valueOf(grantResults[i]));
            }
            if (perms.get("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                perms.get("android.permission.WRITE_EXTERNAL_STORAGE");
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start1:
                AppManage.getInstance(Spalsh_StartActivity.this).showInterstitialAd(Spalsh_StartActivity.this, () -> {
                    startActivityForResult(new Intent(Spalsh_StartActivity.this, MainActivity.class), 101);
                }, "", AppManage.app_mainClickCntSwAd);
                break;
            case R.id.btn_rate:
                Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
                try {
                    startActivity(myAppLinkToMarket);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Spalsh_StartActivity.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btn_share:
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_foreground);
                File f = new File(getExternalCacheDir() + "/image.png");
                try {
                    FileOutputStream outStream = new FileOutputStream(f);
                    bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    urishare = FileProvider.getUriForFile(Spalsh_StartActivity.this, BuildConfig.APPLICATION_ID +
                            ".provider", f);
                } else {
                    urishare = Uri.fromFile(f);
                }
                shareIntent.putExtra(Intent.EXTRA_STREAM, urishare);
                startActivity(Intent.createChooser(shareIntent, "Share Image using"));
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1010) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        startActivityForResult(new Intent(Spalsh_StartActivity.this, Exit_Activity.class), 1010);
    }

}