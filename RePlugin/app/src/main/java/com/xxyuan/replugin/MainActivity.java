package com.xxyuan.replugin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
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
    @BindView(R.id.tv_get_mixplugin)
    TextView tvGetMixplugin;

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
            PluginInfo pluginInfo = RePlugin.getPluginInfo("com.xxyuan.replugin");
            tvPluginName.setText("relugin界面：V" + pluginInfo.getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tv_save_sp, R.id.tv_get_sp,R.id.tv_get_mixplugin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save_sp:
                SharedPrefsUtils.setString("ceshi", "ceshi");
                break;
            case R.id.tv_get_sp:
                String str = SharedPrefsUtils.getString("ceshi", "");
                Log.d(getClass().getSimpleName(), str);
                break;
            case R.id.tv_get_mixplugin:
                if (RePlugin.isPluginInstalled("MixPlugin")) {
                    Intent intent = new Intent();
                    intent.putExtra("web_url","http://192.168.22.91");
                    openActivity(this,intent,"MixPlugin","com.sgcc.pda.mix.h5.activity.MixWebViewActivity");

                } else {
                    Toast.makeText(MainActivity.this, "You must install replugin first!", Toast.LENGTH_SHORT).show();
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
}
