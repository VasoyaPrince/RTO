package testkotlin.rtoinformation.vehicalinfo.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pesonal.adsdk.AppManage;

import testkotlin.rtoinformation.vehicalinfo.R;
import testkotlin.rtoinformation.vehicalinfo.RTOExamDatabase.RTO_Variable;

import java.util.Collections;

public class ExamPreparationOptionsActivity extends AppCompatActivity {
    public static Boolean sign_or_not = false;
    LinearLayout driving_questions;
    SharedPreferences prefs;
    RadioGroup radioLangauge;
    RadioButton radioenglish;
    RadioButton radiogujarati;
    RadioButton radiohindi;
    LinearLayout signsymbol_questions;
    TextView startexam;
    private ImageView back_btn;

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_exam_preparation_options);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.setStatusBarColor(getResources().getColor(R.color.white));

        LinearLayout adMobView = findViewById(R.id.adMobView);
        AppManage.getInstance(ExamPreparationOptionsActivity.this).showBanner(adMobView, AppManage.ADMOB_B[0], AppManage.FACEBOOK_B[0]);

        back_btn = (ImageView) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(view -> onBackPressed());

        radioLangauge = (RadioGroup) findViewById(R.id.radioLangauge);
        radioenglish = (RadioButton) findViewById(R.id.radioenglish);
        radiohindi = (RadioButton) findViewById(R.id.radiohindi);
        radiogujarati = (RadioButton) findViewById(R.id.radiogujarati);
        startexam = (TextView) findViewById(R.id.startexam);
        prefs = getSharedPreferences("settings", 0);

        driving_questions = (LinearLayout) findViewById(R.id.driving_questions);
        driving_questions.setOnClickListener(view -> AppManage.getInstance(ExamPreparationOptionsActivity.this).showInterstitialAd(ExamPreparationOptionsActivity.this, () -> {
            sign_or_not = false;
            operationalization();
        }, "", AppManage.app_mainClickCntSwAd));

        signsymbol_questions = (LinearLayout) findViewById(R.id.signsymbol_questions);
        signsymbol_questions.setOnClickListener(view -> AppManage.getInstance(ExamPreparationOptionsActivity.this).showInterstitialAd(ExamPreparationOptionsActivity.this, () -> {
            sign_or_not = true;
            operationalization();
        }, "", AppManage.app_mainClickCntSwAd));

        startexam.setOnClickListener(view -> AppManage.getInstance(ExamPreparationOptionsActivity.this).showInterstitialAd(ExamPreparationOptionsActivity.this, () -> {
            int checkedRadioButtonId = radioLangauge.getCheckedRadioButtonId();
            if (checkedRadioButtonId == radioenglish.getId()) {
                SharedPreferences.Editor edit = prefs.edit();
                edit.putInt("langId", 1);
                edit.apply();
                Intent intent = new Intent(ExamPreparationOptionsActivity.this, RTOExamActivity.class);
                RTO_Variable.getInstance().counter = 30;
                Collections.shuffle(RTO_Variable.getInstance().nlist);
                RTO_Variable.getInstance().right_score = 0;
                RTO_Variable.getInstance().wrong_score = 0;
                RTO_Variable.getInstance().qid = 0;
                startActivity(intent);
            } else if (checkedRadioButtonId == radiohindi.getId()) {
                SharedPreferences.Editor edit2 = prefs.edit();
                edit2.putInt("langId", 2);
                edit2.apply();
                Intent intent2 = new Intent(ExamPreparationOptionsActivity.this, RTOExamActivity.class);
                RTO_Variable.getInstance().counter = 30;
                Collections.shuffle(RTO_Variable.getInstance().nlist);
                RTO_Variable.getInstance().right_score = 0;
                RTO_Variable.getInstance().wrong_score = 0;
                RTO_Variable.getInstance().qid = 0;
                startActivity(intent2);
            } else if (checkedRadioButtonId == radiogujarati.getId()) {
                SharedPreferences.Editor edit3 = prefs.edit();
                edit3.putInt("langId", 3);
                edit3.apply();
                Intent intent3 = new Intent(ExamPreparationOptionsActivity.this, RTOExamActivity.class);
                RTO_Variable.getInstance().counter = 30;
                Collections.shuffle(RTO_Variable.getInstance().nlist);
                RTO_Variable.getInstance().right_score = 0;
                RTO_Variable.getInstance().wrong_score = 0;
                RTO_Variable.getInstance().qid = 0;
                startActivity(intent3);
            } else {
                Toast.makeText(ExamPreparationOptionsActivity.this, "Select Language", Toast.LENGTH_SHORT).show();
            }
        }, "", AppManage.app_mainClickCntSwAd));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void operationalization() {
        int checkedRadioButtonId = this.radioLangauge.getCheckedRadioButtonId();
        if (checkedRadioButtonId == this.radioenglish.getId()) {
            SharedPreferences.Editor edit = this.prefs.edit();
            edit.putInt("langId", 1);
            edit.apply();
            startActivity(new Intent(this, RTOExamPreparationActivity.class));
        } else if (checkedRadioButtonId == this.radiohindi.getId()) {
            SharedPreferences.Editor edit2 = this.prefs.edit();
            edit2.putInt("langId", 2);
            edit2.apply();
            startActivity(new Intent(this, RTOExamPreparationActivity.class));
        } else if (checkedRadioButtonId == this.radiogujarati.getId()) {
            SharedPreferences.Editor edit3 = this.prefs.edit();
            edit3.putInt("langId", 3);
            edit3.apply();
            startActivity(new Intent(this, RTOExamPreparationActivity.class));
        } else {
            Toast.makeText(this, "Select Language", Toast.LENGTH_SHORT).show();
        }
    }
}