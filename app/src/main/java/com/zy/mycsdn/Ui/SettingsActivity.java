package com.zy.mycsdn.Ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.zy.mycsdn.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Switch webSwitch = (Switch) findViewById(R.id.switch_useWeb);
        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        webSwitch.setChecked(sharedPreferences.getBoolean("useWeb", false));
        webSwitch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.switch_useWeb:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("useWeb", !sharedPreferences.getBoolean("useWeb", false));
                if (!editor.commit()) {
                    Toast.makeText(SettingsActivity.this, "设置失败，请重试", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
