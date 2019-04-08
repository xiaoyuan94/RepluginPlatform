package com.xxyuan.replugin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xxyuan.replugin.utils.SharedPrefsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_save_sp)
    TextView tvSaveSp;
    @BindView(R.id.tv_get_sp)
    TextView tvGetSp;
    @BindView(R.id.tv_plugin_name)
    TextView tvPluginName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String goPlugin = intent.getStringExtra("goPlugin");
        Log.d(getClass().getSimpleName(), "传递过来的值：" + goPlugin);
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            tvPluginName.setText("relugin界面：V" + info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tv_save_sp, R.id.tv_get_sp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save_sp:
                SharedPrefsUtils.setString("ceshi", "ceshi");
                break;
            case R.id.tv_get_sp:
                String str = SharedPrefsUtils.getString("ceshi", "");
                Log.d(getClass().getSimpleName(), str);
                break;
        }
    }
}
