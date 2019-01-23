package com.xxyuan.repluginplatform.okdown;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;
import com.xxyuan.repluginplatform.adapter.PluginAdapter;

import java.io.File;

/**
 * author:xuxiaoyuan
 * date:2019/1/23
 */
public class ListDownloadListener extends DownloadListener {

    private PluginAdapter.ViewHolder holder;

    public ListDownloadListener(Object tag, PluginAdapter.ViewHolder holder) {
        super(tag);
        this.holder = holder;
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
    }

    @Override
    public void onRemove(Progress progress) {

    }
}
