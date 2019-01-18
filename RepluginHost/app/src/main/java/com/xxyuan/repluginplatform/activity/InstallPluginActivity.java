package com.xxyuan.repluginplatform.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.qihoo360.replugin.utils.FileUtils;
import com.xxyuan.repluginplatform.R;
import com.xxyuan.repluginplatform.utils.InstallPluginUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InstallPluginActivity extends AppCompatActivity {

    @BindView(R.id.bt_install_plugin)
    Button btInstallPlugin;
    @BindView(R.id.bt_start_plugin)
    Button btStartPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_plugin);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_install_plugin, R.id.bt_start_plugin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_install_plugin:
                String apkPath = "external" + File.separator + "replugin.apk";
                InstallPluginUtils.startRePlugin(this,"replugin",apkPath);
                break;
            case R.id.bt_start_plugin:
                RePlugin.startActivity(this,
                        RePlugin.createIntent("replugin", "com.xxyuan.replugin.MainActivity"));
                break;
        }
    }
}
