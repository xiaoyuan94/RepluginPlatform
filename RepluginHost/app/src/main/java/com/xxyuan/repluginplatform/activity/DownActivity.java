package com.xxyuan.repluginplatform.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okserver.OkDownload;
import com.xxyuan.repluginplatform.R;
import com.xxyuan.repluginplatform.adapter.PluginAdapter;
import com.xxyuan.repluginplatform.bean.RepluginInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownActivity extends AppCompatActivity {


    @BindView(R.id.rv_plugin)
    RecyclerView rvPlugin;

    private List<RepluginInfoBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //初始化下载控件
        OkDownload.getInstance().setFolder(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/down/");
        OkDownload.getInstance().getThreadPool().setCorePoolSize(3);

        mList = new ArrayList<>();
        RepluginInfoBean apk1 = new RepluginInfoBean();
        apk1.setRePluginName("replugin");
        apk1.setRePluginIconUrl("http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/00814b5dad9b54cc804466369c8cb18f23e23823f");
        apk1.setRePluginUrl("http://192.168.22.91/down/replugin.apk");
        apk1.setPriority(100);
        mList.add(apk1);


        PluginAdapter pluginAdapter = new PluginAdapter(mList,DownActivity.this);
        rvPlugin.setLayoutManager(new GridLayoutManager(this, 4));
        rvPlugin.setAdapter(pluginAdapter);
        rvPlugin.setNestedScrollingEnabled(false);
    }

}
