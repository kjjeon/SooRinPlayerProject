package com.alticast.soorynplayer.core.exoplayer.source;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSpec;

import java.io.IOException;

public class FileTsStreamer implements TsStreamer {

    public static class FileDataSource extends TsDataSource {

        @Override
        public long open(DataSpec dataSpec) throws IOException {
            return 0;
        }

        @Override
        public int read(byte[] buffer, int offset, int readLength) throws IOException {
            return 0;
        }

        @Override
        public Uri getUri() {
            return null;
        }

        @Override
        public void close() throws IOException {

        }
    }

    @Override
    public boolean startStream() {
        return false;
    }

    @Override
    public void stopStream() {

    }

}
