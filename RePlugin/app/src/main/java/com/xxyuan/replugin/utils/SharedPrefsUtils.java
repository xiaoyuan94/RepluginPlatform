package com.xxyuan.replugin.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import java.lang.ref.WeakReference;

/**
 * author : Ben
 * e-mail : yueb@bjhzwq.com
 * time   : 2018/11/16
 * desc   :   SharedPreferences 读写
 * version: 1.0
 */
public class SharedPrefsUtils {
    // 默认保存 文件名xml
    private static final String DEFAULT_NAME = "sp_replugin";
    // 默认保存 Mode
    private static final int DEFAULT_MODE = Context.MODE_PRIVATE;

    private static volatile SharedPrefsUtils instance;
    private final WeakReference<Context> mReference;

    private SharedPrefsUtils(Context context) {
        mReference = new WeakReference<>(context);
    }

    public static void init(Context context) {
        if (instance == null) {
            synchronized (SharedPrefsUtils.class) {
                if (instance == null) {
                    instance = new SharedPrefsUtils(context);
                }
            }
        }
    }

    private static Context getContext() {
        Context context = instance != null ? instance.mReference.get() : null;
        if (context == null) {
            throw new NullPointerException("SharedPrefsUtils Context is not initialized");
        }
        return context;
    }

    /**
     * 清空
     */
    public static void clear() {
        getContext().getSharedPreferences(DEFAULT_NAME, DEFAULT_MODE).edit().clear().apply();
    }

    public static void setString(String key, String value) {
        getContext().getSharedPreferences(DEFAULT_NAME, DEFAULT_MODE).edit()
                .putString(base64Encode(key), base64Encode(value)).apply();
    }

    public static String getString(String key, String defValue) {
        String str = getContext().getSharedPreferences(DEFAULT_NAME, DEFAULT_MODE)
                .getString(base64Encode(key), defValue);
        return TextUtils.equals(str, defValue) ? defValue : base64Decode(str);
    }

    public static void setInt(String key, int value) {
        setString(key, String.valueOf(value));
    }

    public static int getInt(String key, int defValue) {
        try {
            return Integer.parseInt(getString(key, String.valueOf(defValue)));
        } catch (NumberFormatException ignored) {
        }
        return defValue;
    }

    public static void setBool(String key, boolean value) {
        setString(key, String.valueOf(value));
    }

    public static boolean getBool(String key, boolean defValue) {
        return Boolean.parseBoolean(getString(key, String.valueOf(defValue)));
    }

    public static void setDouble(String key, double value) {
        setString(key, String.valueOf(value));
    }

    public static double getDouble(String key, double defValue) {
        try {
            return Double.parseDouble(getString(key, String.valueOf(defValue)));
        } catch (NumberFormatException ignored) {
        }
        return defValue;
    }

    public static void setFloat(String key, float value) {
        setString(key, String.valueOf(value));
    }

    public static float getFloat(String key, float defValue) {
        try {
            return Float.parseFloat(getString(key, String.valueOf(defValue)));
        } catch (NumberFormatException ignored) {
        }
        return defValue;
    }

    public static void setLong(String key, long value) {
        setString(key, String.valueOf(value));
    }

    public static long getLong(String key, long defValue) {
        try {
            return Long.parseLong(getString(key, String.valueOf(defValue)));
        } catch (NumberFormatException ignored) {
        }
        return defValue;
    }


    /**
     * Base64加密
     *
     * @param input
     * @return
     */
    private static String base64Encode(String input) {
        try {
            byte[] byteArray = Base64.encode(input.getBytes(), Base64.DEFAULT);
            return byteArray2HexString(byteArray);
        } catch (Exception ignored) {
        }
        return input;
    }

    /**
     * Base64解密
     *
     * @param hexStr
     * @return
     */
    private static String base64Decode(String hexStr) {
        try {
            byte[] byteArray = Base64.decode(hexString2ByteArray(hexStr), Base64.DEFAULT);
            return new String(byteArray);
        } catch (Exception ignored) {
        }
        return hexStr;
    }

    /**
     * byte数组转16进制字符串
     *
     * @param bytes
     * @return
     */
    private static String byteArray2HexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        if (bytes != null) {
            for (byte b : bytes) {
                String hexStr = Integer.toHexString(b & 0xFF);
                if (hexStr.length() == 1) {
                    builder.append("0");
                }
                builder.append(hexStr.toUpperCase());
            }
        }
        return builder.toString();
    }

    /**
     * 16进制字符串转byte数组
     *
     * @param hexStr
     * @return
     */
    private static byte[] hexString2ByteArray(String hexStr) {
        int byteLen = hexStr.length() / 2;
        byte[] bytes = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            String num = hexStr.substring(i * 2, i * 2 + 2);
            try {
                bytes[i] = Integer.valueOf(num, 16).byteValue();
            } catch (NumberFormatException ignored) {
            }
        }
        return bytes;
    }
}
