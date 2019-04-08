package com.xxyuan.repluginplatform.activity;

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
                if (RePlugin.isPluginInstalled("replugin01")) {
                    RePlugin.startActivity(GoPluginActivity.this,
                            RePlugin.createIntent("replugin01", "com.xxyuan.replugin.MainActivity"));
                } else {
                    Toast.makeText(GoPluginActivity.this, "请安装插件", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
