package com.xxyuan.repluginplatform.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
import java.util.List;

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
//                String apkPath = "external" + File.separator + "replugin.apk";
//                String apkPath = "file:///android_asset/external/" + "replugin.apk";
                String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "down" + File.separator + "replugin01.apk";
                InstallPluginUtils.startRePlugin(this, "replugin01", apkPath);
                break;
            case R.id.bt_start_plugin:
                List<PluginInfo> pluginInfoList = RePlugin.getPluginInfoList();
                if (RePlugin.isPluginInstalled("replugin01")) {
                    openActivity(this,new Intent(),"replugin01","com.xxyuan.replugin.MainActivity");
                } else {
                    Toast.makeText(InstallPluginActivity.this, "请安装插件", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 打开插件的Activity 可带参数传递
     *
     * @param context
     * @param intent
     * @param pluginName
     * @param activityName
     */
    public void openActivity(Context context, Intent intent, String pluginName, String activityName) {
        intent.setComponent(new ComponentName(pluginName, activityName));
        RePlugin.startActivity(context, intent);
    }

    /**
     * 打开插件的Activity 带回调
     *
     * @param activity
     * @param intent
     * @param pluginName
     * @param activityName
     * @param requestCode
     */
    public void openActivityForResult(Activity activity, Intent intent, String pluginName, String activityName, int requestCode) {
        intent.setComponent(new ComponentName(pluginName, activityName));
        RePlugin.startActivityForResult(activity, intent, requestCode, null);
    }
}
