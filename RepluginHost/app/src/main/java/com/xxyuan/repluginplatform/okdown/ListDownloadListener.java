package com.xxyuan.repluginplatform.okdown;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;
import com.xxyuan.repluginplatform.adapter.PluginAdapter;
import com.xxyuan.repluginplatform.utils.InstallPluginUtils;

import java.io.File;

/**
 * author:xuxiaoyuan
 * date:2019/1/23
 */
public class ListDownloadListener extends DownloadListener {

    private PluginAdapter.ViewHolder holder;
    private Context mContext;

    public ListDownloadListener(Object tag, PluginAdapter.ViewHolder holder,
                                Context mContext) {
        super(tag);
        this.holder = holder;
        this.mContext = mContext;
    }

    @Override
    public void onStart(Progress progress) {
    }

    @Override
    public void onProgress(Progress progress) {
        if (tag == holder.getTag()) {
            Log.d(toString(), progress.fraction + "");
            holder.mSpv_progress.setPercent((int) (progress.fraction * 100));
        }
    }

    @Override
    public void onError(Progress progress) {
        Throwable throwable = progress.exception;
        if (throwable != null) throwable.printStackTrace();
    }

    @Override
    public void onFinish(File file, Progress progress) {
        Log.d(toString(), "onFinish: "+file.getAbsolutePath());
        holder.mSpv_progress.setVisibility(View.GONE);
        //跳转对应的插件
        String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "down" + File.separator + "replugin.apk";
        InstallPluginUtils.startRePlugin(mContext, "replugin", apkPath);
    }

    @Override
    public void onRemove(Progress progress) {

    }
}
