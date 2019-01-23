package com.xxyuan.repluginplatform.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        mList = new ArrayList<>();
        RepluginInfoBean apk1 = new RepluginInfoBean();
        apk1.setRePluginName("微信");
        apk1.setRePluginIconUrl("http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/00814b5dad9b54cc804466369c8cb18f23e23823f");
        apk1.setRePluginUrl("http://116.117.158.129/f2.market.xiaomi.com/download/AppStore/04275951df2d94fee0a8210a3b51ae624cc34483a/com.tencent.mm.apk");
        apk1.setPriority(100);
        mList.add(apk1);

        RepluginInfoBean apk2 = new RepluginInfoBean();
        apk2.setRePluginName("新浪微博");
        apk2.setRePluginIconUrl("http://file.market.xiaomi.com/thumbnail/PNG/l114/AppStore/01db44d7f809430661da4fff4d42e703007430f38");
        apk2.setRePluginUrl("http://60.28.125.129/f1.market.xiaomi.com/download/AppStore/0ff41344f280f40c83a1bbf7f14279fb6542ebd2a/com.sina.weibo.apk");
        apk2.setPriority(100);
        mList.add(apk2);

        PluginAdapter pluginAdapter = new PluginAdapter(mList);
        rvPlugin.setLayoutManager(new GridLayoutManager(this, 4));
        rvPlugin.setAdapter(pluginAdapter);
        rvPlugin.setNestedScrollingEnabled(false);
    }

}
