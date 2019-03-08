/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alticast.soorinplayer.source;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.alticast.soorinplayer.utils.AltLog;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public final class RawDataSource implements DataSource {
    private static final String TAG = "RawDataSource";
    /**
     * Thrown when an {@link IOException} is encountered reading from a raw resource.
     */
    public static class RawResourceDataSourceException extends IOException {
        public RawResourceDataSourceException(String message) {
            super(message);
        }

        public RawResourceDataSourceException(IOException e) {
            super(e);
        }
    }

    /**
     * Builds a {@link Uri} for the specified raw resource identifier.
     *
     * @param rawResourceId A raw resource identifier (i.e. a constant defined in {@code R.raw}).
     * @return The corresponding {@link Uri}.
     */
    public static Uri buildRawResourceUri(int rawResourceId) {
        return Uri.parse(RAW_RESOURCE_SCHEME + ":///" + rawResourceId);
    }

    /** The scheme part of a raw resource URI. */
    public static final String RAW_RESOURCE_SCHEME = "rawresource";

    private final Resources resources;
    private final TransferListener<? super RawDataSource> listener;

    private Uri uri;
    private AssetFileDescriptor assetFileDescriptor;
    private InputStream inputStream;
    private long bytesRemaining;
    private boolean opened;

    /**
     * @param context A context.
     */
    public RawDataSource(Context context) {
        this(context, null);
    }

    /**
     * @param context A context.
     * @param listener An optional listener.
     */
    public RawDataSource(Context context,
                         TransferListener<? super RawDataSource> listener) {
        this.resources = context.getResources();
        this.listener = listener;
    }

    @Override
    public long open(DataSpec dataSpec) throws RawResourceDataSourceException {
        Log.d(TAG, "dataSpec = " + dataSpec.toString());
        try {
            uri = dataSpec.uri;
            if (!TextUtils.equals(RAW_RESOURCE_SCHEME, uri.getScheme())) {
                throw new RawResourceDataSourceException("URI must use scheme " + RAW_RESOURCE_SCHEME);
            }

            int resourceId;
            try {
                resourceId = Integer.parseInt(uri.getLastPathSegment());
            } catch (NumberFormatException e) {
                throw new RawResourceDataSourceException("Resource identifier must be an integer.");
            }

            assetFileDescriptor = resources.openRawResourceFd(resourceId);
            inputStream = new FileInputStream(assetFileDescriptor.getFileDescriptor());
            inputStream.skip(assetFileDescriptor.getStartOffset());
            long skipped = inputStream.skip(dataSpec.position);
            if (skipped < dataSpec.position) {
                // We expect the skip to be satisfied in full. If it isn't then we're probably trying to
                // skip beyond the end of the data.
                throw new EOFException();
            }
            if (dataSpec.length != C.LENGTH_UNSET) {
                bytesRemaining = dataSpec.length;
            } else {
                long assetFileDescriptorLength = assetFileDescriptor.getLength();
                // If the length is UNKNOWN_LENGTH then the asset extends to the end of the file.
                bytesRemaining = assetFileDescriptorLength == AssetFileDescriptor.UNKNOWN_LENGTH
                        ? C.LENGTH_UNSET : (assetFileDescriptorLength - dataSpec.position);
            }
        } catch (IOException e) {
            throw new RawResourceDataSourceException(e);
        }

        opened = true;
        if (listener != null) {
            listener.onTransferStart(this, dataSpec);
        }

        return bytesRemaining;
    }

    @Override
    public int read(byte[] buffer, int offset, int readLength) throws RawResourceDataSourceException {
//        AltLog.d(TAG, "offset = " +  offset + "readLength = " +  readLength);
        if (readLength == 0) {
            return 0;
        } else if (bytesRemaining == 0) {
            return C.RESULT_END_OF_INPUT;
        }

        int bytesRead;
        try {
            int bytesToRead = bytesRemaining == C.LENGTH_UNSET ? readLength
                    : (int) Math.min(bytesRemaining, readLength);
            bytesRead = inputStream.read(buffer, offset, bytesToRead);
        } catch (IOException e) {
            throw new RawResourceDataSourceException(e);
        }

        if (bytesRead == -1) {
            if (bytesRemaining != C.LENGTH_UNSET) {
                // End of stream reached having not read sufficient data.
                throw new RawResourceDataSourceException(new EOFException());
            }
            return C.RESULT_END_OF_INPUT;
        }
        if (bytesRemaining != C.LENGTH_UNSET) {
            bytesRemaining -= bytesRead;
        }
        if (listener != null) {
            listener.onBytesTransferred(this, bytesRead);
        }
        return bytesRead;
    }

    @Override
    public Uri getUri() {
        return uri;
    }

    @Override
    public void close() throws RawResourceDataSourceException {
        AltLog.d(TAG, "close");
        uri = null;
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new RawResourceDataSourceException(e);
        } finally {
            inputStream = null;
            try {
                if (assetFileDescriptor != null) {
                    assetFileDescriptor.close();
                }
            } catch (IOException e) {
                throw new RawResourceDataSourceException(e);
            } finally {
                assetFileDescriptor = null;
                if (opened) {
                    opened = false;
                    if (listener != null) {
                        listener.onTransferEnd(this);
                    }
                }
            }
        }
    }

}
