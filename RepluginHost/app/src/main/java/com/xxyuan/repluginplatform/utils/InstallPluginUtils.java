package com.xxyuan.repluginplatform.utils;

import android.content.Context;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.qihoo360.replugin.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * author:xuxiaoyuan
 * date:2019/1/18
 */
public class InstallPluginUtils {

    public static void startRePlugin(Context context, String pluginName, String apkPath) {
        //安装插件过程
        PluginInfo pluginInfo = RePlugin.getPluginInfo(pluginName);
        //插件文件，只有存在就进行安装或者更新
        File file = new File(apkPath);
        //判断是否已经安装插件
        if (pluginInfo == null) {
            //插件未安装的情况
            if (!file.exists()) {
                Toast.makeText(context, "插件安装失败，插件文件不存在", Toast.LENGTH_SHORT).show();
            } else {
                //安装插件
                PluginInfo pluginInfo1 = RePlugin.install(apkPath);
                if (pluginInfo1 == null) {
                    Toast.makeText(context, "插件安装失败，安装出错", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "插件安装成功", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            //插件已安装,是否需要升级，判断条件是file是否为空
            if (file.exists()) {
                PluginInfo pluginInfo1 = RePlugin.install(file.getAbsolutePath());
                if (pluginInfo1 == null) {
                    Toast.makeText(context, "插件升级失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "插件升级成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "插件已安装", Toast.LENGTH_SHORT).show();
                RePlugin.preload(pluginInfo);
            }
        }
    }


    /**
     * 模拟安装或升级（覆盖安装）外置插件
     * 注意：为方便演示，外置插件临时放置到Host的assets/external目录下，具体说明见README</p>
     */
    private void simulateInstallExternalPlugin(Context context, String apkName) {
        String demo3Apk = apkName;
        String demo3apkPath = "external" + File.separator + demo3Apk;

        // 文件是否已经存在？直接删除重来
        String pluginFilePath = context.getFilesDir().getAbsolutePath() + File.separator + demo3Apk;
        File pluginFile = new File(pluginFilePath);
        if (pluginFile.exists()) {
            FileUtils.deleteQuietly(pluginFile);
        }

        // 开始复制
        copyAssetsFileToAppFiles(context, demo3apkPath, demo3Apk);
        PluginInfo info = null;
        if (pluginFile.exists()) {
            info = RePlugin.install(pluginFilePath);
        }

        if (info != null) {
//            RePlugin.startActivity(this, RePlugin.createIntent(info.getName(), "com.qihoo360.replugin.sample.demo3.MainActivity"));
            Toast.makeText(context, "install external plugin success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "install external plugin failed", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 从assets目录中复制某文件内容
     *
     * @param assetFileName assets目录下的Apk源文件路径
     * @param newFileName   复制到/data/data/package_name/files/目录下文件名
     */
    private void copyAssetsFileToAppFiles(Context context, String assetFileName, String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = context.getAssets().open(assetFileName);
            fos = context.openFileOutput(newFileName, Context.MODE_PRIVATE);
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
