package com.xxyuan.repluginplatform.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.db.DownloadManager;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadTask;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.xxyuan.repluginplatform.R;
import com.xxyuan.repluginplatform.activity.InstallPluginActivity;
import com.xxyuan.repluginplatform.bean.RepluginInfoBean;
import com.xxyuan.repluginplatform.okdown.ListDownloadListener;
import com.xxyuan.repluginplatform.view.SectorProgressView;

import java.io.File;
import java.util.List;

/**
 * author:xuxiaoyuan
 * date:2019/1/21
 */
public class PluginAdapter extends RecyclerView.Adapter<PluginAdapter.ViewHolder>  {
    private List<RepluginInfoBean> list;
    private Context mContext ;

    public PluginAdapter(List<RepluginInfoBean> list, Context mContext) {
        this.list = list;
        this.mContext=mContext;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plugin, parent, false);
        return new PluginAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        RepluginInfoBean repluginInfoBean = list.get(position);
        initListener(viewHolder,repluginInfoBean);
    }

    private void initListener(final ViewHolder viewHolder,
                              final  RepluginInfoBean apk) {
        viewHolder.mRl_plugin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PluginInfo pluginInfo = RePlugin.getPluginInfo("com.xxyuan.replugin");
                if (RePlugin.isPluginInstalled("com.xxyuan.replugin")
                    &&pluginInfo.getVersion()>3) {
                    Intent intent = new Intent();
                    intent.putExtra("goPlugin","goPlugin");
                    openActivity(mContext,intent,"com.xxyuan.replugin","com.xxyuan.replugin.MainActivity");
                } else {
                    Toast.makeText(mContext, "请安装插件", Toast.LENGTH_SHORT).show();
                    //这里只是演示，表示请求可以传参，怎么传都行，和okgo使用方法一样
                    GetRequest<File> request = OkGo.<File>get(apk.getRePluginUrl());
                    //这里第一个参数是tag，代表下载任务的唯一标识，传任意字符串都行，需要保证唯一,我这里用url作为了tag
                    String tag = apk.getRePluginUrl();
                    viewHolder.setTag(tag);
                    OkDownload.request(tag, request)//
                            .priority(apk.priority)//
                            .extra1(apk)//
                            .save()//
                            .register(new ListDownloadListener(tag,viewHolder,mContext))
                            .start();
                    notifyDataSetChanged();
                }
            }
        });
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
     * 插件的ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public SectorProgressView mSpv_progress;
        private RelativeLayout mRl_plugin;
        private String tag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSpv_progress = itemView.findViewById(R.id.spv_progress);
            mRl_plugin = itemView.findViewById(R.id.rl_plugin);
        }
        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }
    }
}
