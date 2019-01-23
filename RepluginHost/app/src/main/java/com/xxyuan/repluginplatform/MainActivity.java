package com.xxyuan.repluginplatform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xxyuan.repluginplatform.activity.DownActivity;
import com.xxyuan.repluginplatform.activity.GoPluginActivity;
import com.xxyuan.repluginplatform.activity.InstallPluginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_go_plugin)
    Button btGoPlugin;
    @BindView(R.id.bt_plugin_install)
    Button btPluginInstall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_go_plugin,R.id.bt_plugin_install,R.id.bt_plugin_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_go_plugin:
                startActivity(new Intent(this, GoPluginActivity.class));
                break;
            case R.id.bt_plugin_install:
                startActivity(new Intent(this, InstallPluginActivity.class));
                break;
            case R.id.bt_plugin_down:
                startActivity(new Intent(this, DownActivity.class));
                break;
        }
    }

}
