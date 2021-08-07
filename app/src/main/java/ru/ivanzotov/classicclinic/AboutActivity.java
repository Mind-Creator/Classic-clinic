package ru.ivanzotov.classicclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView mVersion;

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
    }


    private String getVersion() throws PackageManager.NameNotFoundException {

        PackageManager manager = this.getPackageManager();
        PackageInfo info = manager.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);

        return String.format("%s | %s", info.packageName, info.versionName);
    }
}