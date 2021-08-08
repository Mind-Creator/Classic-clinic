package ru.ivanzotov.classicclinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private static final String TAG = "AboutActivity";

    private final static String mDeveloperId = "8066779784953105746";

    private TextView mVersion;
    private Button mRateThisApp;
    private Button mVisitMeAtGooglePlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mVersion = (TextView) findViewById(R.id.version);
        try {
            mVersion.setText(getVersion());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mVisitMeAtGooglePlay = (Button) findViewById(R.id.visit_me_at_google_play_button);
        mVisitMeAtGooglePlay.setOnClickListener((View view) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setData(Uri.parse("market://dev?id=" + mDeveloperId));
            intent.setData(Uri.parse("https://play.google.com/store/apps/dev?id=" + mDeveloperId));
            intent.setPackage("com.android.vending");

            startActivity(intent);
        });

        mRateThisApp = (Button) findViewById(R.id.rate_this_app_button);
        mRateThisApp.setOnClickListener((View view) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // 	https://play.google.com/store/apps/details?id=<package_name>
            intent.setData(Uri.parse("market://details?id=" + getPackageName()));
            intent.setPackage("com.android.vending");

            startActivity(intent);
        });

        mVisitMeAtGooglePlay.setAlpha(0.0f);
        mVisitMeAtGooglePlay.setTranslationY(-16.0f); //. .toPixel(this)
        mVisitMeAtGooglePlay.animate()
                .setStartDelay(1000)
                .setDuration(1500)
                .setInterpolator(new FastOutSlowInInterpolator())
                .alpha(1.0f)
                .translationY(0.0f)
                .start();

        mRateThisApp.setAlpha(0.0f);
        mRateThisApp.setTranslationY(-16.0f); //. .toPixel(this)
        mRateThisApp.animate()
                .setStartDelay(2500)
                .setDuration(1500)
                .setInterpolator(new FastOutSlowInInterpolator())
                .alpha(1.0f)
                .translationY(0.0f)
                .start();
    }


    private String getVersion() throws PackageManager.NameNotFoundException {

        PackageManager manager = this.getPackageManager();
        PackageInfo info = manager.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);

        return String.format("%s | %s", info.packageName, info.versionName);
    }
}