package com.alticast.soorynplayer.utils;

import android.util.Log;

public final class AltLog
{
    public static void v(String parTag, String parMessage)
    {
        Log.v(parTag,getCurrentLineInfo() + parMessage);
    }

    public static void d(String parTag, String parMessage)
    {
        Log.d(parTag,getCurrentLineInfo()+ parMessage);
    }

    public static void i(String parTag, String parMessage)
    {
        Log.i(parTag,getCurrentLineInfo()+ parMessage);
    }

    public static void w(String parTag, String parMessage)
    {
        Log.w(parTag,getCurrentLineInfo()+ parMessage);
    }

    public static void e(String parTag, String parMessage)
    {
        Log.e(parTag,getCurrentLineInfo()+ parMessage);
    }

    private static String getCurrentLineInfo()
    {
        StackTraceElement trace = Thread.currentThread().getStackTrace()[4];

        String strFileName = trace.getFileName();
        String strMethodName = trace.getMethodName();
        int nLineNumber = trace.getLineNumber();

        return "[F:" + strFileName + "][T:" +getThreadName() + "][M:" + strMethodName + "][L:" + nLineNumber + "] : ";
    }

    private static String getThreadName() {
        String threadName = Thread.currentThread().getName();
        if (threadName.length() > 30) {
            threadName = threadName.substring(0, 30) + "...";
        }
        return threadName;
    }
}
