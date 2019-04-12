package com.xxyuan.repluginplatform.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.xxyuan.repluginplatform.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoPluginActivity extends AppCompatActivity {

    @BindView(R.id.bt_plugin)
    Button btPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_plugin);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_plugin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_plugin:
                if (RePlugin.isPluginInstalled("MixPlugin")) {
                    Intent intent = new Intent();
                    intent.putExtra("web_url","http://192.168.22.91");
                    openActivity(this,intent,"MixPlugin","com.sgcc.pda.mix.h5.activity.MixWebViewActivity");
//                    RePlugin.startActivity(GoPluginActivity.this,
//                            RePlugin.createIntent("MixPlugin", "com.sgcc.pda.mix.h5.activity.MixWebViewActivity"));

                } else {
                    Toast.makeText(GoPluginActivity.this, "You must install replugin first!", Toast.LENGTH_SHORT).show();
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
