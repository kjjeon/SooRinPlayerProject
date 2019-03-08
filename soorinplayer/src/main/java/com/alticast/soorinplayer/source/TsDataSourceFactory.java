package com.alticast.soorinplayer.source;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.Factory;

public final class TsDataSourceFactory implements Factory {

    public static final int RAW_FILE_TYPE = 0;
    public static final int FILE_STREAM_TYPE = 1;

    private final Context context;
    private int streamType = FILE_STREAM_TYPE;

    public TsDataSourceFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    public TsDataSourceFactory(Context context,int type) {
        this.context = context.getApplicationContext();
        this.streamType = type;
    }

    @Override
    public DataSource createDataSource() {
        switch (streamType) {
            case RAW_FILE_TYPE:
                return new RawDataSource(this.context);

            case FILE_STREAM_TYPE:
            default:
                return new FileTsStreamer.FileDataSource();
        }
    }
}
