package com.iceteck.silicompressorr.videocompression;

import android.content.Context;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MediaController {
    private static final int DEFAULT_VIDEO_BITRATE = 450000;
    private static final int DEFAULT_VIDEO_HEIGHT = 360;
    private static final int DEFAULT_VIDEO_WIDTH = 640;
    private static volatile MediaController Instance = null;
    public static final String MIME_TYPE = "video/avc";
    private static final int PROCESSOR_TYPE_INTEL = 2;
    private static final int PROCESSOR_TYPE_MTK = 3;
    private static final int PROCESSOR_TYPE_OTHER = 0;
    private static final int PROCESSOR_TYPE_QCOM = 1;
    private static final int PROCESSOR_TYPE_SEC = 4;
    private static final int PROCESSOR_TYPE_TI = 5;
    public static File cachedFile;
    public String path;
    private boolean videoConvertFirstWrite = true;

    public static native int convertVideoFrame(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, int i3, int i4, int i5);

    private static boolean isRecognizedFormat(int i) {
        if (i == 39 || i == 2130706688) {
            return true;
        }
        switch (i) {
            case 19:
            case 20:
            case 21:
                return true;
            default:
                return false;
        }
    }

    public static MediaController getInstance() {
        MediaController mediaController = Instance;
        if (mediaController == null) {
            synchronized (MediaController.class) {
                mediaController = Instance;
                if (mediaController == null) {
                    mediaController = new MediaController();
                    Instance = mediaController;
                }
            }
        }
        return mediaController;
    }

    public static int selectColorFormat(MediaCodecInfo mediaCodecInfo, String str) {
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
        int i = 0;
        for (int i2 : capabilitiesForType.colorFormats) {
            if (isRecognizedFormat(i2)) {
                if (!mediaCodecInfo.getName().equals("OMX.SEC.AVC.Encoder") || i2 != 19) {
                    return i2;
                }
                i = i2;
            }
        }
        return i;
    }

    private void didWriteData(boolean z, boolean z2) {
        if (this.videoConvertFirstWrite) {
            this.videoConvertFirstWrite = false;
        }
    }

    public static class VideoConvertRunnable implements Runnable {
        private File destDirectory;
        private String videoPath;

        private VideoConvertRunnable(String str, File file) {
            this.videoPath = str;
            this.destDirectory = file;
        }

        public static void runConversion(final String str, final File file) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread thread = new Thread(new VideoConvertRunnable(str, file), "VideoConvertRunnable");
                        thread.start();
                        thread.join();
                    } catch (Exception e) {
                        Log.e("tmessages", e.getMessage());
                    }
                }
            }).start();
        }

        public void run() {
            MediaController.getInstance().convertVideo(this.videoPath, this.destDirectory);
        }
    }

    public static MediaCodecInfo selectCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        MediaCodecInfo mediaCodecInfo = null;
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String equalsIgnoreCase : codecInfoAt.getSupportedTypes()) {
                    if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                        if (!codecInfoAt.getName().equals("OMX.SEC.avc.enc") || codecInfoAt.getName().equals("OMX.SEC.AVC.Encoder")) {
                            return codecInfoAt;
                        }
                        mediaCodecInfo = codecInfoAt;
                    }
                }
                continue;
            }
        }
        return mediaCodecInfo;
    }

    public void scheduleVideoConvert(String str, File file) {
        startVideoConvertFromQueue(str, file);
    }

    private void startVideoConvertFromQueue(String str, File file) {
        VideoConvertRunnable.runConversion(str, file);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007d, code lost:
        if (r10 == -1) goto L_0x007f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0085 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long readAndWriteTrack(android.media.MediaExtractor r20, com.iceteck.silicompressorr.videocompression.MP4Builder r21, android.media.MediaCodec.BufferInfo r22, long r23, long r25, java.io.File r27, boolean r28) throws java.lang.Exception {
        /*
            r19 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r23
            r5 = r19
            r6 = r28
            int r7 = r5.selectTrack(r0, r6)
            r8 = -1
            if (r7 < 0) goto L_0x008c
            r0.selectTrack(r7)
            android.media.MediaFormat r10 = r0.getTrackFormat(r7)
            int r11 = r1.addTrack(r10, r6)
            java.lang.String r12 = "max-input-size"
            int r10 = r10.getInteger(r12)
            r12 = 0
            r14 = 0
            int r15 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r15 <= 0) goto L_0x0030
            r0.seekTo(r3, r14)
            goto L_0x0033
        L_0x0030:
            r0.seekTo(r12, r14)
        L_0x0033:
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocateDirect(r10)
            r16 = r8
            r4 = 0
        L_0x003a:
            if (r4 != 0) goto L_0x0088
            int r10 = r20.getSampleTrackIndex()
            r18 = 1
            if (r10 != r7) goto L_0x007c
            int r10 = r0.readSampleData(r3, r14)
            r2.size = r10
            int r10 = r2.size
            if (r10 >= 0) goto L_0x0051
            r2.size = r14
            goto L_0x007f
        L_0x0051:
            long r12 = r20.getSampleTime()
            r2.presentationTimeUs = r12
            if (r15 <= 0) goto L_0x0061
            int r10 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x0061
            long r12 = r2.presentationTimeUs
            r16 = r12
        L_0x0061:
            r12 = 0
            int r10 = (r25 > r12 ? 1 : (r25 == r12 ? 0 : -1))
            if (r10 < 0) goto L_0x006d
            long r12 = r2.presentationTimeUs
            int r10 = (r12 > r25 ? 1 : (r12 == r25 ? 0 : -1))
            if (r10 >= 0) goto L_0x007f
        L_0x006d:
            r2.offset = r14
            int r10 = r20.getSampleFlags()
            r2.flags = r10
            r1.writeSampleData(r11, r3, r2, r6)
            r20.advance()
            goto L_0x0081
        L_0x007c:
            r12 = -1
            if (r10 != r12) goto L_0x0081
        L_0x007f:
            r10 = 1
            goto L_0x0082
        L_0x0081:
            r10 = 0
        L_0x0082:
            if (r10 == 0) goto L_0x0085
            r4 = 1
        L_0x0085:
            r12 = 0
            goto L_0x003a
        L_0x0088:
            r0.unselectTrack(r7)
            return r16
        L_0x008c:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iceteck.silicompressorr.videocompression.MediaController.readAndWriteTrack(android.media.MediaExtractor, com.iceteck.silicompressorr.videocompression.MP4Builder, android.media.MediaCodec$BufferInfo, long, long, java.io.File, boolean):long");
    }

    private int selectTrack(MediaExtractor mediaExtractor, boolean z) {
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            String string = mediaExtractor.getTrackFormat(i).getString("mime");
            if (z) {
                if (string.startsWith("audio/")) {
                    return i;
                }
            } else if (string.startsWith("video/")) {
                return i;
            }
        }
        return -5;
    }

    public boolean convertVideo(String str, File file) {
        return convertVideo(str, file, 0, 0, 0);
    }

    public boolean convertVideo(Context context, Uri uri, File file) {
        return convertVideo(context, uri, file, 0, 0, 0);
    }

    public boolean convertVideo(String str, File file, int i, int i2, int i3) {
        return convertVideo((Context) null, (Uri) null, str, file, i, i2, i3);
    }

    public boolean convertVideo(Context context, Uri uri, File file, int i, int i2, int i3) {
        return convertVideo(context, uri, (String) null, file, i, i2, i3);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v47, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v48, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v52, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v80, resolved type: java.nio.ByteBuffer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v81, resolved type: java.nio.ByteBuffer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v84, resolved type: java.nio.ByteBuffer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v85, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v86, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v87, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v88, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v89, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r14v55 */
    /* JADX WARNING: type inference failed for: r14v56 */
    /* JADX WARNING: type inference failed for: r14v57 */
    /* JADX WARNING: type inference failed for: r14v58 */
    /* JADX WARNING: type inference failed for: r14v59 */
    /* JADX WARNING: type inference failed for: r14v60 */
    /* JADX WARNING: type inference failed for: r14v61 */
    /* JADX WARNING: type inference failed for: r14v63 */
    /* JADX WARNING: type inference failed for: r14v65 */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0243, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0244, code lost:
        r2 = r38;
        r4 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x024d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x024e, code lost:
        r33 = r2;
        r34 = r3;
        r13 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x02f0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02f1, code lost:
        r4 = r1;
        r33 = r2;
        r34 = r3;
        r13 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0332, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x03d6, code lost:
        r29 = r15;
        r14 = r30;
        r15 = r31;
        r27 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x0457, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x0458, code lost:
        r14 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x0494, code lost:
        r4 = java.nio.ByteBuffer.allocate(r13);
        r14 = java.nio.ByteBuffer.allocate(r5.size - r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x049f, code lost:
        r33 = r2;
        r34 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:?, code lost:
        r4.put(r15, 0, r13).position(0);
        r14.put(r15, r13, r5.size - r13).position(0);
        r13 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x04e0, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x04e2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x055a, code lost:
        if (r5.size != 0) goto L_0x055c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x0577, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:?, code lost:
        android.util.Log.e(r13, r0.getMessage());
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x0671, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x0682, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x0683, code lost:
        r33 = r2;
        r34 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:384:0x069e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:385:0x069f, code lost:
        r33 = r2;
        r34 = r3;
        r13 = r25;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x06a7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:387:0x06a8, code lost:
        r34 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:389:0x06ad, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:412:0x06f5, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:477:0x0857, code lost:
        r4.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:481:?, code lost:
        r6.finishMovie(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:482:0x0861, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:483:0x0862, code lost:
        android.util.Log.e(r13, r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0112, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0113, code lost:
        r4 = r3;
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0119, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x011a, code lost:
        r4 = r3;
        r22 = r14;
        r23 = r15;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0243 A[Catch:{ Exception -> 0x024d, all -> 0x0243 }, ExcHandler: all (th java.lang.Throwable), PHI: r3 r25 
      PHI: (r3v70 android.media.MediaExtractor) = (r3v26 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor) binds: [B:255:0x0441, B:242:0x042a, B:243:?, B:227:0x03fe, B:179:0x0314, B:168:0x02ea, B:169:?, B:157:0x02c7, B:158:?, B:159:0x02d0, B:160:?, B:148:0x02ae, B:149:?, B:119:0x0231] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r25v18 java.lang.String) = (r25v6 java.lang.String), (r25v6 java.lang.String), (r25v6 java.lang.String), (r25v6 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v19 java.lang.String) binds: [B:255:0x0441, B:242:0x042a, B:243:?, B:227:0x03fe, B:179:0x0314, B:168:0x02ea, B:169:?, B:157:0x02c7, B:158:?, B:159:0x02d0, B:160:?, B:148:0x02ae, B:149:?, B:119:0x0231] A[DONT_GENERATE, DONT_INLINE], Splitter:B:119:0x0231] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x024d A[Catch:{ Exception -> 0x024d, all -> 0x0243 }, ExcHandler: Exception (e java.lang.Exception), PHI: r25 
      PHI: (r25v16 java.lang.String) = (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v19 java.lang.String) binds: [B:139:0x0276, B:150:0x02b8, B:148:0x02ae, B:149:?, B:119:0x0231] A[DONT_GENERATE, DONT_INLINE], Splitter:B:139:0x0276] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0291 A[Catch:{ Exception -> 0x024d, all -> 0x06a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0292 A[Catch:{ Exception -> 0x024d, all -> 0x06a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02c7 A[SYNTHETIC, Splitter:B:157:0x02c7] */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02d4  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x02ea A[SYNTHETIC, Splitter:B:168:0x02ea] */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x02fa A[SYNTHETIC, Splitter:B:172:0x02fa] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0314 A[SYNTHETIC, Splitter:B:179:0x0314] */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x0332 A[Catch:{ Exception -> 0x0332, all -> 0x0243 }, ExcHandler: Exception (e java.lang.Exception), PHI: r2 r3 r25 
      PHI: (r2v35 int) = (r2v16 int), (r2v16 int), (r2v16 int), (r2v16 int), (r2v16 int), (r2v6 int) binds: [B:259:0x0449, B:260:?, B:242:0x042a, B:243:?, B:227:0x03fe, B:179:0x0314] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r3v69 android.media.MediaExtractor) = (r3v26 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor) binds: [B:259:0x0449, B:260:?, B:242:0x042a, B:243:?, B:227:0x03fe, B:179:0x0314] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r25v14 java.lang.String) = (r25v6 java.lang.String), (r25v6 java.lang.String), (r25v6 java.lang.String), (r25v6 java.lang.String), (r25v6 java.lang.String), (r25v0 java.lang.String) binds: [B:259:0x0449, B:260:?, B:242:0x042a, B:243:?, B:227:0x03fe, B:179:0x0314] A[DONT_GENERATE, DONT_INLINE], Splitter:B:179:0x0314] */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x033c A[Catch:{ Exception -> 0x0332, all -> 0x0243 }] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x0347 A[Catch:{ Exception -> 0x0332, all -> 0x0243 }] */
    /* JADX WARNING: Removed duplicated region for block: B:304:0x0502 A[Catch:{ Exception -> 0x0630, all -> 0x062d }] */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x0504 A[Catch:{ Exception -> 0x0630, all -> 0x062d }] */
    /* JADX WARNING: Removed duplicated region for block: B:308:0x050e A[Catch:{ Exception -> 0x0630, all -> 0x062d }] */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x0519 A[Catch:{ Exception -> 0x0630, all -> 0x062d }] */
    /* JADX WARNING: Removed duplicated region for block: B:338:0x0572 A[SYNTHETIC, Splitter:B:338:0x0572] */
    /* JADX WARNING: Removed duplicated region for block: B:355:0x05e0 A[Catch:{ Exception -> 0x0673, all -> 0x0671 }] */
    /* JADX WARNING: Removed duplicated region for block: B:372:0x0671 A[ExcHandler: all (th java.lang.Throwable), PHI: r13 r34 
      PHI: (r13v32 java.lang.String) = (r13v34 java.lang.String), (r13v34 java.lang.String), (r13v34 java.lang.String), (r13v36 java.lang.String), (r13v83 java.lang.String) binds: [B:338:0x0572, B:343:0x0579, B:339:?, B:321:0x0549, B:301:0x04fc] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r34v18 android.media.MediaExtractor) = (r34v19 android.media.MediaExtractor), (r34v19 android.media.MediaExtractor), (r34v19 android.media.MediaExtractor), (r34v19 android.media.MediaExtractor), (r34v22 android.media.MediaExtractor) binds: [B:338:0x0572, B:343:0x0579, B:339:?, B:321:0x0549, B:301:0x04fc] A[DONT_GENERATE, DONT_INLINE], Splitter:B:321:0x0549] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:386:0x06a7 A[ExcHandler: all (th java.lang.Throwable), PHI: r3 r25 
      PHI: (r3v22 android.media.MediaExtractor) = (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v26 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor) binds: [B:139:0x0276, B:150:0x02b8, B:153:0x02be, B:154:?, B:162:0x02d5, B:164:0x02e2, B:165:?, B:175:0x0300, B:176:?, B:219:0x03e8, B:238:0x0424, B:239:?, B:244:0x042d, B:172:0x02fa, B:173:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r25v2 java.lang.String) = (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String), (r25v6 java.lang.String), (r25v6 java.lang.String), (r25v6 java.lang.String), (r25v6 java.lang.String), (r25v0 java.lang.String), (r25v0 java.lang.String) binds: [B:139:0x0276, B:150:0x02b8, B:153:0x02be, B:154:?, B:162:0x02d5, B:164:0x02e2, B:165:?, B:175:0x0300, B:176:?, B:219:0x03e8, B:238:0x0424, B:239:?, B:244:0x042d, B:172:0x02fa, B:173:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:139:0x0276] */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x06ad A[ExcHandler: Exception (e java.lang.Exception), PHI: r23 
      PHI: (r23v12 java.io.File) = (r23v13 java.io.File), (r23v13 java.io.File), (r23v13 java.io.File), (r23v18 java.io.File) binds: [B:108:0x01f6, B:109:?, B:111:0x0212, B:74:0x015b] A[DONT_GENERATE, DONT_INLINE], Splitter:B:74:0x015b] */
    /* JADX WARNING: Removed duplicated region for block: B:404:0x06db A[Catch:{ Exception -> 0x06f7, all -> 0x06f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:406:0x06e0 A[Catch:{ Exception -> 0x06f7, all -> 0x06f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:408:0x06e5 A[Catch:{ Exception -> 0x06f7, all -> 0x06f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:410:0x06ed A[Catch:{ Exception -> 0x06f7, all -> 0x06f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:412:0x06f5 A[ExcHandler: all (th java.lang.Throwable), PHI: r3 r13 
      PHI: (r3v15 android.media.MediaExtractor) = (r3v16 android.media.MediaExtractor), (r3v9 android.media.MediaExtractor) binds: [B:401:0x06d6, B:111:0x0212] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v8 java.lang.String) = (r13v9 java.lang.String), (r13v0 java.lang.String) binds: [B:401:0x06d6, B:111:0x0212] A[DONT_GENERATE, DONT_INLINE], Splitter:B:111:0x0212] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:423:0x0710  */
    /* JADX WARNING: Removed duplicated region for block: B:430:0x0732  */
    /* JADX WARNING: Removed duplicated region for block: B:433:0x0739  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e3 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:464:0x07d6  */
    /* JADX WARNING: Removed duplicated region for block: B:466:0x07db  */
    /* JADX WARNING: Removed duplicated region for block: B:474:0x083a  */
    /* JADX WARNING: Removed duplicated region for block: B:477:0x0857  */
    /* JADX WARNING: Removed duplicated region for block: B:479:0x085c  */
    /* JADX WARNING: Removed duplicated region for block: B:508:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0112 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:51:0x010a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean convertVideo(android.content.Context r39, android.net.Uri r40, java.lang.String r41, java.io.File r42, int r43, int r44, int r45) {
        /*
            r38 = this;
            r11 = r38
            r0 = r39
            r1 = r40
            r2 = r41
            java.lang.String r12 = "time = "
            java.lang.String r13 = "tmessages"
            android.media.MediaMetadataRetriever r3 = new android.media.MediaMetadataRetriever
            r3.<init>()
            if (r2 == 0) goto L_0x0019
            r11.path = r2
            r3.setDataSource(r2)
            goto L_0x0020
        L_0x0019:
            if (r0 == 0) goto L_0x088a
            if (r1 == 0) goto L_0x088a
            r3.setDataSource(r0, r1)
        L_0x0020:
            r4 = 19
            java.lang.String r4 = r3.extractMetadata(r4)
            r5 = 18
            java.lang.String r6 = r3.extractMetadata(r5)
            r7 = 24
            java.lang.String r3 = r3.extractMetadata(r7)
            if (r43 <= 0) goto L_0x0037
            r7 = r43
            goto L_0x0039
        L_0x0037:
            r7 = 640(0x280, float:8.97E-43)
        L_0x0039:
            if (r44 <= 0) goto L_0x003e
            r8 = r44
            goto L_0x0040
        L_0x003e:
            r8 = 360(0x168, float:5.04E-43)
        L_0x0040:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            int r3 = r3.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            int r4 = r4.intValue()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            int r6 = r6.intValue()
            if (r45 <= 0) goto L_0x005d
            r9 = r45
            goto L_0x0060
        L_0x005d:
            r9 = 450000(0x6ddd0, float:6.30584E-40)
        L_0x0060:
            java.io.File r15 = new java.io.File
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r14 = "VIDEO_"
            r10.append(r14)
            java.text.SimpleDateFormat r14 = new java.text.SimpleDateFormat
            java.util.Locale r5 = java.util.Locale.US
            r16 = r12
            java.lang.String r12 = "yyyyMMdd_HHmmss"
            r14.<init>(r12, r5)
            java.util.Date r5 = new java.util.Date
            r5.<init>()
            java.lang.String r5 = r14.format(r5)
            r10.append(r5)
            java.lang.String r5 = ".mp4"
            r10.append(r5)
            java.lang.String r5 = r10.toString()
            r10 = r42
            r15.<init>(r10, r5)
            int r5 = android.os.Build.VERSION.SDK_INT
            r10 = 180(0xb4, float:2.52E-43)
            r14 = 90
            r12 = 18
            if (r5 >= r12) goto L_0x00a4
            if (r8 <= r7) goto L_0x00a4
            if (r7 == r4) goto L_0x00a4
            if (r8 == r6) goto L_0x00a4
            r3 = 90
            goto L_0x00ad
        L_0x00a4:
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 20
            if (r4 <= r5) goto L_0x00c1
            if (r3 != r14) goto L_0x00b5
            r3 = 0
        L_0x00ad:
            r10 = 270(0x10e, float:3.78E-43)
        L_0x00af:
            r37 = r8
            r8 = r7
            r7 = r37
            goto L_0x00c2
        L_0x00b5:
            if (r3 != r10) goto L_0x00b9
            r3 = 0
            goto L_0x00c2
        L_0x00b9:
            r4 = 270(0x10e, float:3.78E-43)
            if (r3 != r4) goto L_0x00c1
            r3 = 0
            r10 = 90
            goto L_0x00af
        L_0x00c1:
            r10 = 0
        L_0x00c2:
            r4 = 0
            r12 = 1
            if (r2 == 0) goto L_0x00da
            java.io.File r5 = new java.io.File
            java.lang.String r6 = r11.path
            r5.<init>(r6)
            boolean r6 = r5.canRead()
            if (r6 != 0) goto L_0x00d8
            r11.didWriteData(r12, r12)
        L_0x00d6:
            r1 = 0
            return r1
        L_0x00d8:
            r14 = r5
            goto L_0x00db
        L_0x00da:
            r14 = r4
        L_0x00db:
            r11.videoConvertFirstWrite = r12
            long r17 = java.lang.System.currentTimeMillis()
            if (r7 == 0) goto L_0x0883
            if (r8 == 0) goto L_0x0883
            android.media.MediaCodec$BufferInfo r5 = new android.media.MediaCodec$BufferInfo     // Catch:{ Exception -> 0x07c4, all -> 0x07ba }
            r5.<init>()     // Catch:{ Exception -> 0x07c4, all -> 0x07ba }
            com.iceteck.silicompressorr.videocompression.Mp4Movie r6 = new com.iceteck.silicompressorr.videocompression.Mp4Movie     // Catch:{ Exception -> 0x07c4, all -> 0x07ba }
            r6.<init>()     // Catch:{ Exception -> 0x07c4, all -> 0x07ba }
            r6.setCacheFile(r15)     // Catch:{ Exception -> 0x07c4, all -> 0x07ba }
            r6.setRotation(r3)     // Catch:{ Exception -> 0x07c4, all -> 0x07ba }
            r6.setSize(r7, r8)     // Catch:{ Exception -> 0x07c4, all -> 0x07ba }
            com.iceteck.silicompressorr.videocompression.MP4Builder r3 = new com.iceteck.silicompressorr.videocompression.MP4Builder     // Catch:{ Exception -> 0x07c4, all -> 0x07ba }
            r3.<init>()     // Catch:{ Exception -> 0x07c4, all -> 0x07ba }
            com.iceteck.silicompressorr.videocompression.MP4Builder r6 = r3.createMovie(r6)     // Catch:{ Exception -> 0x07c4, all -> 0x07ba }
            android.media.MediaExtractor r3 = new android.media.MediaExtractor     // Catch:{ Exception -> 0x07b0, all -> 0x07aa }
            r3.<init>()     // Catch:{ Exception -> 0x07b0, all -> 0x07aa }
            if (r2 == 0) goto L_0x0123
            if (r14 == 0) goto L_0x0123
            java.lang.String r0 = r14.toString()     // Catch:{ Exception -> 0x0119, all -> 0x0112 }
            r3.setDataSource(r0)     // Catch:{ Exception -> 0x0119, all -> 0x0112 }
            goto L_0x012a
        L_0x0112:
            r0 = move-exception
            r4 = r3
            r2 = r11
        L_0x0115:
            r1 = r16
            goto L_0x07c1
        L_0x0119:
            r0 = move-exception
            r4 = r3
            r22 = r14
            r23 = r15
        L_0x011f:
            r1 = r16
            goto L_0x07cd
        L_0x0123:
            if (r0 == 0) goto L_0x0779
            if (r1 == 0) goto L_0x0779
            r3.setDataSource(r0, r1, r4)     // Catch:{ Exception -> 0x076e, all -> 0x0763 }
        L_0x012a:
            r1 = 0
            int r2 = r11.selectTrack(r3, r1)     // Catch:{ Exception -> 0x076e, all -> 0x0763 }
            if (r2 < 0) goto L_0x0707
            java.lang.String r0 = android.os.Build.MANUFACTURER     // Catch:{ Exception -> 0x06bc, all -> 0x06b0 }
            java.lang.String r0 = r0.toLowerCase()     // Catch:{ Exception -> 0x06bc, all -> 0x06b0 }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x06bc, all -> 0x06b0 }
            java.lang.String r4 = "lge"
            r20 = 2
            r21 = 4
            java.lang.String r12 = "video/avc"
            r22 = r14
            r14 = 18
            if (r1 >= r14) goto L_0x01ed
            android.media.MediaCodecInfo r1 = selectCodec(r12)     // Catch:{ Exception -> 0x01e4, all -> 0x0112 }
            int r14 = selectColorFormat(r1, r12)     // Catch:{ Exception -> 0x01e4, all -> 0x0112 }
            if (r14 == 0) goto L_0x01da
            r41 = r14
            java.lang.String r14 = r1.getName()     // Catch:{ Exception -> 0x01e4, all -> 0x0112 }
            r23 = r15
            java.lang.String r15 = "OMX.qcom."
            boolean r15 = r14.contains(r15)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            if (r15 == 0) goto L_0x017b
            int r14 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            r15 = 16
            if (r14 != r15) goto L_0x0178
            boolean r14 = r0.equals(r4)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            if (r14 != 0) goto L_0x0175
            java.lang.String r14 = "nokia"
            boolean r14 = r0.equals(r14)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            if (r14 == 0) goto L_0x0178
        L_0x0175:
            r14 = 1
        L_0x0176:
            r15 = 1
            goto L_0x01a5
        L_0x0178:
            r14 = 1
        L_0x0179:
            r15 = 0
            goto L_0x01a5
        L_0x017b:
            java.lang.String r15 = "OMX.Intel."
            boolean r15 = r14.contains(r15)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            if (r15 == 0) goto L_0x0185
            r14 = 2
            goto L_0x0179
        L_0x0185:
            java.lang.String r15 = "OMX.MTK.VIDEO.ENCODER.AVC"
            boolean r15 = r14.equals(r15)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            if (r15 == 0) goto L_0x018f
            r14 = 3
            goto L_0x0179
        L_0x018f:
            java.lang.String r15 = "OMX.SEC.AVC.Encoder"
            boolean r15 = r14.equals(r15)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            if (r15 == 0) goto L_0x0199
            r14 = 4
            goto L_0x0176
        L_0x0199:
            java.lang.String r15 = "OMX.TI.DUCATI1.VIDEO.H264E"
            boolean r14 = r14.equals(r15)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            if (r14 == 0) goto L_0x01a3
            r14 = 5
            goto L_0x0179
        L_0x01a3:
            r14 = 0
            goto L_0x0179
        L_0x01a5:
            r42 = r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            r14.<init>()     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            r43 = r15
            java.lang.String r15 = "codec = "
            r14.append(r15)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            java.lang.String r1 = r1.getName()     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            r14.append(r1)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            java.lang.String r1 = " manufacturer = "
            r14.append(r1)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            r14.append(r0)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            java.lang.String r1 = "device = "
            r14.append(r1)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            java.lang.String r1 = android.os.Build.MODEL     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            r14.append(r1)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            java.lang.String r1 = r14.toString()     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            android.util.Log.e(r13, r1)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            r14 = r41
            r1 = r42
            r15 = r43
            goto L_0x01f4
        L_0x01da:
            r23 = r15
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            java.lang.String r1 = "no supported color format"
            r0.<init>(r1)     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
            throw r0     // Catch:{ Exception -> 0x06ad, all -> 0x0112 }
        L_0x01e4:
            r0 = move-exception
            r23 = r15
        L_0x01e7:
            r33 = r2
            r34 = r3
            goto L_0x06c5
        L_0x01ed:
            r23 = r15
            r14 = 2130708361(0x7f000789, float:1.701803E38)
            r1 = 0
            r15 = 0
        L_0x01f4:
            r24 = r15
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x06ad, all -> 0x06b0 }
            r15.<init>()     // Catch:{ Exception -> 0x06ad, all -> 0x06b0 }
            java.lang.String r11 = "colorFormat = "
            r15.append(r11)     // Catch:{ Exception -> 0x06ad, all -> 0x06b0 }
            r15.append(r14)     // Catch:{ Exception -> 0x06ad, all -> 0x06b0 }
            java.lang.String r11 = r15.toString()     // Catch:{ Exception -> 0x06ad, all -> 0x06b0 }
            android.util.Log.e(r13, r11)     // Catch:{ Exception -> 0x06ad, all -> 0x06b0 }
            int r11 = r7 * r8
            int r15 = r11 * 3
            int r15 = r15 / 2
            if (r1 != 0) goto L_0x022c
            int r0 = r8 % 16
            if (r0 == 0) goto L_0x0229
            int r0 = r8 % 16
            r1 = 16
            int r0 = 16 - r0
            int r0 = r0 + r8
            int r0 = r0 - r8
            int r0 = r0 * r7
            int r1 = r0 * 5
            int r1 = r1 / 4
            int r15 = r15 + r1
            r1 = r0
            r25 = r13
            goto L_0x0276
        L_0x0229:
            r25 = r13
            goto L_0x0275
        L_0x022c:
            r25 = r13
            r13 = 1
            if (r1 != r13) goto L_0x0256
            java.lang.String r0 = r0.toLowerCase()     // Catch:{ Exception -> 0x024d, all -> 0x0243 }
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x024d, all -> 0x0243 }
            if (r0 != 0) goto L_0x0275
            int r0 = r11 + 2047
            r0 = r0 & -2048(0xfffffffffffff800, float:NaN)
            int r0 = r0 - r11
            int r15 = r15 + r0
        L_0x0241:
            r1 = r0
            goto L_0x0276
        L_0x0243:
            r0 = move-exception
            r2 = r38
            r4 = r3
        L_0x0247:
            r1 = r16
            r13 = r25
            goto L_0x07c1
        L_0x024d:
            r0 = move-exception
            r33 = r2
            r34 = r3
            r13 = r25
            goto L_0x06c5
        L_0x0256:
            r4 = 5
            if (r1 != r4) goto L_0x025a
            goto L_0x0275
        L_0x025a:
            r4 = 3
            if (r1 != r4) goto L_0x0275
            java.lang.String r1 = "baidu"
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x024d, all -> 0x0243 }
            if (r0 == 0) goto L_0x0275
            int r0 = r8 % 16
            r1 = 16
            int r0 = 16 - r0
            int r0 = r0 + r8
            int r0 = r0 - r8
            int r0 = r0 * r7
            int r1 = r0 * 5
            int r1 = r1 / 4
            int r15 = r15 + r1
            goto L_0x0241
        L_0x0275:
            r1 = 0
        L_0x0276:
            r3.selectTrack(r2)     // Catch:{ Exception -> 0x024d, all -> 0x06a7 }
            r4 = r1
            r0 = 0
            r11 = 0
            r3.seekTo(r0, r11)     // Catch:{ Exception -> 0x024d, all -> 0x06a7 }
            android.media.MediaFormat r0 = r3.getTrackFormat(r2)     // Catch:{ Exception -> 0x024d, all -> 0x06a7 }
            android.media.MediaFormat r1 = android.media.MediaFormat.createVideoFormat(r12, r7, r8)     // Catch:{ Exception -> 0x024d, all -> 0x06a7 }
            java.lang.String r11 = "color-format"
            r1.setInteger(r11, r14)     // Catch:{ Exception -> 0x024d, all -> 0x06a7 }
            java.lang.String r11 = "bitrate"
            if (r9 == 0) goto L_0x0292
            goto L_0x0295
        L_0x0292:
            r9 = 921600(0xe1000, float:1.291437E-39)
        L_0x0295:
            r1.setInteger(r11, r9)     // Catch:{ Exception -> 0x024d, all -> 0x06a7 }
            java.lang.String r9 = "frame-rate"
            r11 = 25
            r1.setInteger(r9, r11)     // Catch:{ Exception -> 0x024d, all -> 0x06a7 }
            java.lang.String r9 = "i-frame-interval"
            r11 = 10
            r1.setInteger(r9, r11)     // Catch:{ Exception -> 0x024d, all -> 0x06a7 }
            int r9 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x024d, all -> 0x06a7 }
            r11 = 18
            if (r9 >= r11) goto L_0x02b8
            java.lang.String r9 = "stride"
            int r11 = r7 + 32
            r1.setInteger(r9, r11)     // Catch:{ Exception -> 0x024d, all -> 0x0243 }
            java.lang.String r9 = "slice-height"
            r1.setInteger(r9, r8)     // Catch:{ Exception -> 0x024d, all -> 0x0243 }
        L_0x02b8:
            android.media.MediaCodec r9 = android.media.MediaCodec.createEncoderByType(r12)     // Catch:{ Exception -> 0x024d, all -> 0x06a7 }
            r11 = 0
            r13 = 1
            r9.configure(r1, r11, r11, r13)     // Catch:{ Exception -> 0x069e, all -> 0x06a7 }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x069e, all -> 0x06a7 }
            r11 = 18
            if (r1 < r11) goto L_0x02d4
            com.iceteck.silicompressorr.videocompression.InputSurface r11 = new com.iceteck.silicompressorr.videocompression.InputSurface     // Catch:{ Exception -> 0x069e, all -> 0x0243 }
            android.view.Surface r1 = r9.createInputSurface()     // Catch:{ Exception -> 0x069e, all -> 0x0243 }
            r11.<init>(r1)     // Catch:{ Exception -> 0x069e, all -> 0x0243 }
            r11.makeCurrent()     // Catch:{ Exception -> 0x0694, all -> 0x0243 }
            goto L_0x02d5
        L_0x02d4:
            r11 = 0
        L_0x02d5:
            r9.start()     // Catch:{ Exception -> 0x0694, all -> 0x06a7 }
            java.lang.String r1 = "mime"
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Exception -> 0x0694, all -> 0x06a7 }
            android.media.MediaCodec r1 = android.media.MediaCodec.createDecoderByType(r1)     // Catch:{ Exception -> 0x0694, all -> 0x06a7 }
            int r13 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x068b, all -> 0x06a7 }
            r26 = r15
            r15 = 18
            if (r13 < r15) goto L_0x02fa
            com.iceteck.silicompressorr.videocompression.OutputSurface r10 = new com.iceteck.silicompressorr.videocompression.OutputSurface     // Catch:{ Exception -> 0x02f0, all -> 0x0243 }
            r10.<init>()     // Catch:{ Exception -> 0x02f0, all -> 0x0243 }
            goto L_0x0300
        L_0x02f0:
            r0 = move-exception
            r4 = r1
            r33 = r2
            r34 = r3
            r13 = r25
            goto L_0x069c
        L_0x02fa:
            com.iceteck.silicompressorr.videocompression.OutputSurface r13 = new com.iceteck.silicompressorr.videocompression.OutputSurface     // Catch:{ Exception -> 0x068b, all -> 0x06a7 }
            r13.<init>(r7, r8, r10)     // Catch:{ Exception -> 0x068b, all -> 0x06a7 }
            r10 = r13
        L_0x0300:
            android.view.Surface r13 = r10.getSurface()     // Catch:{ Exception -> 0x0682, all -> 0x06a7 }
            r19 = r4
            r4 = 0
            r15 = 0
            r1.configure(r0, r13, r15, r4)     // Catch:{ Exception -> 0x0682, all -> 0x06a7 }
            r1.start()     // Catch:{ Exception -> 0x0682, all -> 0x06a7 }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0682, all -> 0x06a7 }
            r13 = 21
            if (r0 >= r13) goto L_0x033c
            java.nio.ByteBuffer[] r0 = r1.getInputBuffers()     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            java.nio.ByteBuffer[] r27 = r9.getOutputBuffers()     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            int r15 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            r4 = 18
            if (r15 >= r4) goto L_0x032e
            java.nio.ByteBuffer[] r4 = r9.getInputBuffers()     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            r15 = r4
            r28 = 0
            r29 = -5
            r4 = r0
            r0 = 0
            goto L_0x0345
        L_0x032e:
            r4 = r0
            r0 = 0
            r15 = 0
            goto L_0x0341
        L_0x0332:
            r0 = move-exception
        L_0x0333:
            r4 = r1
            r33 = r2
            r34 = r3
        L_0x0338:
            r13 = r25
            goto L_0x06c9
        L_0x033c:
            r0 = 0
            r4 = 0
            r15 = 0
            r27 = 0
        L_0x0341:
            r28 = 0
            r29 = -5
        L_0x0345:
            if (r0 != 0) goto L_0x0675
            r30 = r14
            if (r28 != 0) goto L_0x03cb
            int r13 = r3.getSampleTrackIndex()     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            if (r13 != r2) goto L_0x03a1
            r31 = r15
            r14 = 2500(0x9c4, double:1.235E-320)
            int r13 = r1.dequeueInputBuffer(r14)     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            if (r13 < 0) goto L_0x03a8
            int r14 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            r15 = 21
            if (r14 >= r15) goto L_0x0364
            r14 = r4[r13]     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            goto L_0x0368
        L_0x0364:
            java.nio.ByteBuffer r14 = r1.getInputBuffer(r13)     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
        L_0x0368:
            r15 = 0
            int r14 = r3.readSampleData(r14, r15)     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            if (r14 >= 0) goto L_0x0387
            r14 = 0
            r15 = 0
            r32 = 0
            r28 = 4
            r39 = r1
            r40 = r13
            r41 = r14
            r42 = r15
            r43 = r32
            r45 = r28
            r39.queueInputBuffer(r40, r41, r42, r43, r45)     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            r28 = 1
            goto L_0x03a8
        L_0x0387:
            r15 = 0
            long r32 = r3.getSampleTime()     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            r34 = 0
            r39 = r1
            r40 = r13
            r41 = r15
            r42 = r14
            r43 = r32
            r45 = r34
            r39.queueInputBuffer(r40, r41, r42, r43, r45)     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            r3.advance()     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            goto L_0x03a8
        L_0x03a1:
            r31 = r15
            r14 = -1
            if (r13 != r14) goto L_0x03a8
            r13 = 1
            goto L_0x03a9
        L_0x03a8:
            r13 = 0
        L_0x03a9:
            if (r13 == 0) goto L_0x03cd
            r13 = 2500(0x9c4, double:1.235E-320)
            int r15 = r1.dequeueInputBuffer(r13)     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            if (r15 < 0) goto L_0x03cd
            r13 = 0
            r14 = 0
            r32 = 0
            r28 = 4
            r39 = r1
            r40 = r15
            r41 = r13
            r42 = r14
            r43 = r32
            r45 = r28
            r39.queueInputBuffer(r40, r41, r42, r43, r45)     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            r28 = 1
            goto L_0x03cd
        L_0x03cb:
            r31 = r15
        L_0x03cd:
            r15 = r29
            r13 = 1
            r14 = 1
        L_0x03d1:
            if (r13 != 0) goto L_0x03e0
            if (r14 == 0) goto L_0x03d6
            goto L_0x03e0
        L_0x03d6:
            r29 = r15
            r14 = r30
            r15 = r31
            r13 = 21
            goto L_0x0345
        L_0x03e0:
            r40 = r0
            r29 = r13
            r39 = r14
            r13 = 2500(0x9c4, double:1.235E-320)
            int r0 = r9.dequeueOutputBuffer(r5, r13)     // Catch:{ Exception -> 0x0682, all -> 0x06a7 }
            r13 = -1
            if (r0 != r13) goto L_0x03fb
            r33 = r2
            r34 = r3
            r32 = r4
            r3 = -1
            r14 = 0
        L_0x03f7:
            r2 = r40
            goto L_0x050c
        L_0x03fb:
            r13 = -3
            if (r0 != r13) goto L_0x0412
            int r13 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            r14 = 21
            if (r13 >= r14) goto L_0x0408
            java.nio.ByteBuffer[] r27 = r9.getOutputBuffers()     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
        L_0x0408:
            r14 = r39
            r33 = r2
            r34 = r3
            r32 = r4
            r3 = -1
            goto L_0x03f7
        L_0x0412:
            r13 = -2
            if (r0 != r13) goto L_0x0422
            android.media.MediaFormat r13 = r9.getOutputFormat()     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            r14 = -5
            if (r15 != r14) goto L_0x0408
            r14 = 0
            int r15 = r6.addTrack(r13, r14)     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            goto L_0x0408
        L_0x0422:
            if (r0 < 0) goto L_0x0654
            int r13 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0682, all -> 0x06a7 }
            r14 = 21
            if (r13 >= r14) goto L_0x042d
            r13 = r27[r0]     // Catch:{ Exception -> 0x0332, all -> 0x0243 }
            goto L_0x0431
        L_0x042d:
            java.nio.ByteBuffer r13 = r9.getOutputBuffer(r0)     // Catch:{ Exception -> 0x0682, all -> 0x06a7 }
        L_0x0431:
            if (r13 == 0) goto L_0x0632
            int r14 = r5.size     // Catch:{ Exception -> 0x0682, all -> 0x06a7 }
            r32 = r4
            r4 = 1
            if (r14 <= r4) goto L_0x04f8
            int r4 = r5.flags     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            r4 = r4 & 2
            if (r4 != 0) goto L_0x045c
            r4 = 0
            boolean r13 = r6.writeSampleData(r15, r13, r5, r4)     // Catch:{ Exception -> 0x0457, all -> 0x0243 }
            if (r13 == 0) goto L_0x0453
            r14 = r38
            r14.didWriteData(r4, r4)     // Catch:{ Exception -> 0x0332, all -> 0x044e }
            goto L_0x04f8
        L_0x044e:
            r0 = move-exception
            r4 = r3
            r2 = r14
            goto L_0x0247
        L_0x0453:
            r14 = r38
            goto L_0x04f8
        L_0x0457:
            r0 = move-exception
            r14 = r38
            goto L_0x0333
        L_0x045c:
            r4 = -5
            r14 = r38
            if (r15 != r4) goto L_0x04f8
            int r15 = r5.size     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            byte[] r15 = new byte[r15]     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            int r4 = r5.offset     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            int r14 = r5.size     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            int r4 = r4 + r14
            r13.limit(r4)     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            int r4 = r5.offset     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            r13.position(r4)     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            r13.get(r15)     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            int r4 = r5.size     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            r13 = 1
            int r4 = r4 - r13
        L_0x0479:
            if (r4 < 0) goto L_0x04c2
            r14 = 3
            if (r4 <= r14) goto L_0x04c2
            byte r14 = r15[r4]     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            if (r14 != r13) goto L_0x04b6
            int r13 = r4 + -1
            byte r13 = r15[r13]     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            if (r13 != 0) goto L_0x04b6
            int r13 = r4 + -2
            byte r13 = r15[r13]     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            if (r13 != 0) goto L_0x04b6
            int r13 = r4 + -3
            byte r14 = r15[r13]     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            if (r14 != 0) goto L_0x04b6
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r13)     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            int r14 = r5.size     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            int r14 = r14 - r13
            java.nio.ByteBuffer r14 = java.nio.ByteBuffer.allocate(r14)     // Catch:{ Exception -> 0x04f0, all -> 0x04e4 }
            r33 = r2
            r34 = r3
            r2 = 0
            java.nio.ByteBuffer r3 = r4.put(r15, r2, r13)     // Catch:{ Exception -> 0x04e2, all -> 0x04e0 }
            r3.position(r2)     // Catch:{ Exception -> 0x04e2, all -> 0x04e0 }
            int r3 = r5.size     // Catch:{ Exception -> 0x04e2, all -> 0x04e0 }
            int r3 = r3 - r13
            java.nio.ByteBuffer r3 = r14.put(r15, r13, r3)     // Catch:{ Exception -> 0x04e2, all -> 0x04e0 }
            r3.position(r2)     // Catch:{ Exception -> 0x04e2, all -> 0x04e0 }
            goto L_0x04c8
        L_0x04b6:
            r33 = r2
            r34 = r3
            int r4 = r4 + -1
            r2 = r33
            r3 = r34
            r13 = 1
            goto L_0x0479
        L_0x04c2:
            r33 = r2
            r34 = r3
            r4 = 0
            r14 = 0
        L_0x04c8:
            android.media.MediaFormat r2 = android.media.MediaFormat.createVideoFormat(r12, r7, r8)     // Catch:{ Exception -> 0x04e2, all -> 0x04e0 }
            if (r4 == 0) goto L_0x04da
            if (r14 == 0) goto L_0x04da
            java.lang.String r3 = "csd-0"
            r2.setByteBuffer(r3, r4)     // Catch:{ Exception -> 0x04e2, all -> 0x04e0 }
            java.lang.String r3 = "csd-1"
            r2.setByteBuffer(r3, r14)     // Catch:{ Exception -> 0x04e2, all -> 0x04e0 }
        L_0x04da:
            r3 = 0
            int r15 = r6.addTrack(r2, r3)     // Catch:{ Exception -> 0x04e2, all -> 0x04e0 }
            goto L_0x04fc
        L_0x04e0:
            r0 = move-exception
            goto L_0x04e7
        L_0x04e2:
            r0 = move-exception
            goto L_0x04f5
        L_0x04e4:
            r0 = move-exception
            r34 = r3
        L_0x04e7:
            r2 = r38
            r3 = r0
            r1 = r16
            r13 = r25
            goto L_0x06b8
        L_0x04f0:
            r0 = move-exception
            r33 = r2
            r34 = r3
        L_0x04f5:
            r4 = r1
            goto L_0x0338
        L_0x04f8:
            r33 = r2
            r34 = r3
        L_0x04fc:
            int r2 = r5.flags     // Catch:{ Exception -> 0x0630, all -> 0x062d }
            r2 = r2 & 4
            if (r2 == 0) goto L_0x0504
            r2 = 1
            goto L_0x0505
        L_0x0504:
            r2 = 0
        L_0x0505:
            r3 = 0
            r9.releaseOutputBuffer(r0, r3)     // Catch:{ Exception -> 0x0630, all -> 0x062d }
            r14 = r39
            r3 = -1
        L_0x050c:
            if (r0 == r3) goto L_0x0519
            r0 = r2
        L_0x050f:
            r13 = r29
            r4 = r32
            r2 = r33
            r3 = r34
            goto L_0x03d1
        L_0x0519:
            r3 = 2500(0x9c4, double:1.235E-320)
            int r0 = r1.dequeueOutputBuffer(r5, r3)     // Catch:{ Exception -> 0x0630, all -> 0x062d }
            r3 = -1
            if (r0 != r3) goto L_0x0528
            r13 = r25
        L_0x0524:
            r29 = 0
            goto L_0x0611
        L_0x0528:
            r4 = -3
            if (r0 != r4) goto L_0x052f
            r13 = r25
            goto L_0x0611
        L_0x052f:
            r4 = -2
            if (r0 != r4) goto L_0x054e
            android.media.MediaFormat r0 = r1.getOutputFormat()     // Catch:{ Exception -> 0x0630, all -> 0x062d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0630, all -> 0x062d }
            r4.<init>()     // Catch:{ Exception -> 0x0630, all -> 0x062d }
            java.lang.String r13 = "newFormat = "
            r4.append(r13)     // Catch:{ Exception -> 0x0630, all -> 0x062d }
            r4.append(r0)     // Catch:{ Exception -> 0x0630, all -> 0x062d }
            java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x0630, all -> 0x062d }
            r13 = r25
            android.util.Log.e(r13, r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            goto L_0x0611
        L_0x054e:
            r13 = r25
            if (r0 < 0) goto L_0x0616
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r3 = 18
            if (r4 < r3) goto L_0x0560
            int r3 = r5.size     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            if (r3 == 0) goto L_0x055e
        L_0x055c:
            r3 = 1
            goto L_0x056d
        L_0x055e:
            r3 = 0
            goto L_0x056d
        L_0x0560:
            int r3 = r5.size     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            if (r3 != 0) goto L_0x055c
            long r3 = r5.presentationTimeUs     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r35 = 0
            int r25 = (r3 > r35 ? 1 : (r3 == r35 ? 0 : -1))
            if (r25 == 0) goto L_0x055e
            goto L_0x055c
        L_0x056d:
            r1.releaseOutputBuffer(r0, r3)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            if (r3 == 0) goto L_0x05da
            r10.awaitNewImage()     // Catch:{ Exception -> 0x0577, all -> 0x0671 }
            r0 = 0
            goto L_0x0581
        L_0x0577:
            r0 = move-exception
            r3 = r0
            java.lang.String r0 = r3.getMessage()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            android.util.Log.e(r13, r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r0 = 1
        L_0x0581:
            if (r0 != 0) goto L_0x05da
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r3 = 18
            if (r0 < r3) goto L_0x059a
            r3 = 0
            r10.drawImage(r3)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            long r3 = r5.presentationTimeUs     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r35 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 * r35
            r11.setPresentationTime(r3)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r11.swapBuffers()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            goto L_0x05da
        L_0x059a:
            r3 = 2500(0x9c4, double:1.235E-320)
            int r0 = r9.dequeueInputBuffer(r3)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            if (r0 < 0) goto L_0x05d5
            r3 = 1
            r10.drawImage(r3)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.nio.ByteBuffer r3 = r10.getFrame()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r4 = r31[r0]     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r4.clear()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r39 = r3
            r40 = r4
            r41 = r30
            r42 = r7
            r43 = r8
            r44 = r19
            r45 = r24
            convertVideoFrame(r39, r40, r41, r42, r43, r44, r45)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            long r3 = r5.presentationTimeUs     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r25 = 0
            r39 = r9
            r40 = r0
            r0 = 0
            r41 = r0
            r42 = r26
            r43 = r3
            r45 = r25
            r39.queueInputBuffer(r40, r41, r42, r43, r45)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            goto L_0x05da
        L_0x05d5:
            java.lang.String r0 = "input buffer not available"
            android.util.Log.e(r13, r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
        L_0x05da:
            int r0 = r5.flags     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r0 = r0 & 4
            if (r0 == 0) goto L_0x0611
            java.lang.String r0 = "decoder stream end"
            android.util.Log.e(r13, r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r3 = 18
            if (r0 < r3) goto L_0x05f0
            r9.signalEndOfInputStream()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            goto L_0x0524
        L_0x05f0:
            r3 = 2500(0x9c4, double:1.235E-320)
            int r0 = r9.dequeueInputBuffer(r3)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            if (r0 < 0) goto L_0x0524
            r25 = 0
            r29 = 1
            long r3 = r5.presentationTimeUs     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r35 = 4
            r39 = r9
            r40 = r0
            r41 = r25
            r42 = r29
            r43 = r3
            r45 = r35
            r39.queueInputBuffer(r40, r41, r42, r43, r45)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            goto L_0x0524
        L_0x0611:
            r0 = r2
            r25 = r13
            goto L_0x050f
        L_0x0616:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r3.<init>()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.lang.String r4 = "unexpected result from decoder.dequeueOutputBuffer: "
            r3.append(r4)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r3.append(r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            throw r2     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
        L_0x062d:
            r0 = move-exception
            goto L_0x06aa
        L_0x0630:
            r0 = move-exception
            goto L_0x0687
        L_0x0632:
            r33 = r2
            r34 = r3
            r13 = r25
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r3.<init>()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.lang.String r4 = "encoderOutputBuffer "
            r3.append(r4)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r3.append(r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.lang.String r0 = " was null"
            r3.append(r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            throw r2     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
        L_0x0654:
            r33 = r2
            r34 = r3
            r13 = r25
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r3.<init>()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.lang.String r4 = "unexpected result from encoder.dequeueOutputBuffer: "
            r3.append(r4)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r3.append(r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
            throw r2     // Catch:{ Exception -> 0x0673, all -> 0x0671 }
        L_0x0671:
            r0 = move-exception
            goto L_0x06b3
        L_0x0673:
            r0 = move-exception
            goto L_0x0689
        L_0x0675:
            r33 = r2
            r34 = r3
            r13 = r25
            r2 = r33
            r3 = r34
            r0 = 0
            goto L_0x06d6
        L_0x0682:
            r0 = move-exception
            r33 = r2
            r34 = r3
        L_0x0687:
            r13 = r25
        L_0x0689:
            r4 = r1
            goto L_0x06c9
        L_0x068b:
            r0 = move-exception
            r33 = r2
            r34 = r3
            r13 = r25
            r4 = r1
            goto L_0x069c
        L_0x0694:
            r0 = move-exception
            r33 = r2
            r34 = r3
            r13 = r25
            r4 = 0
        L_0x069c:
            r10 = 0
            goto L_0x06c9
        L_0x069e:
            r0 = move-exception
            r33 = r2
            r34 = r3
            r13 = r25
            r4 = 0
            goto L_0x06c7
        L_0x06a7:
            r0 = move-exception
            r34 = r3
        L_0x06aa:
            r13 = r25
            goto L_0x06b3
        L_0x06ad:
            r0 = move-exception
            goto L_0x01e7
        L_0x06b0:
            r0 = move-exception
            r34 = r3
        L_0x06b3:
            r2 = r38
            r3 = r0
            r1 = r16
        L_0x06b8:
            r4 = r34
            goto L_0x0855
        L_0x06bc:
            r0 = move-exception
            r33 = r2
            r34 = r3
            r22 = r14
            r23 = r15
        L_0x06c5:
            r4 = 0
            r9 = 0
        L_0x06c7:
            r10 = 0
            r11 = 0
        L_0x06c9:
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x0701, all -> 0x06f9 }
            android.util.Log.e(r13, r0)     // Catch:{ Exception -> 0x0701, all -> 0x06f9 }
            r1 = r4
            r2 = r33
            r3 = r34
            r0 = 1
        L_0x06d6:
            r3.unselectTrack(r2)     // Catch:{ Exception -> 0x06f7, all -> 0x06f5 }
            if (r10 == 0) goto L_0x06de
            r10.release()     // Catch:{ Exception -> 0x06f7, all -> 0x06f5 }
        L_0x06de:
            if (r11 == 0) goto L_0x06e3
            r11.release()     // Catch:{ Exception -> 0x06f7, all -> 0x06f5 }
        L_0x06e3:
            if (r1 == 0) goto L_0x06eb
            r1.stop()     // Catch:{ Exception -> 0x06f7, all -> 0x06f5 }
            r1.release()     // Catch:{ Exception -> 0x06f7, all -> 0x06f5 }
        L_0x06eb:
            if (r9 == 0) goto L_0x06f3
            r9.stop()     // Catch:{ Exception -> 0x06f7, all -> 0x06f5 }
            r9.release()     // Catch:{ Exception -> 0x06f7, all -> 0x06f5 }
        L_0x06f3:
            r11 = r0
            goto L_0x070c
        L_0x06f5:
            r0 = move-exception
            goto L_0x06fc
        L_0x06f7:
            r0 = move-exception
            goto L_0x0704
        L_0x06f9:
            r0 = move-exception
            r3 = r34
        L_0x06fc:
            r2 = r38
            r4 = r3
            goto L_0x0115
        L_0x0701:
            r0 = move-exception
            r3 = r34
        L_0x0704:
            r4 = r3
            goto L_0x011f
        L_0x0707:
            r22 = r14
            r23 = r15
            r11 = 0
        L_0x070c:
            r7 = -1
            if (r11 != 0) goto L_0x0732
            r10 = 1
            r14 = -1
            r1 = r38
            r2 = r3
            r12 = r3
            r3 = r6
            r4 = r5
            r9 = r6
            r5 = r7
            r7 = r14
            r14 = r9
            r9 = r23
            r1.readAndWriteTrack(r2, r3, r4, r5, r7, r9, r10)     // Catch:{ Exception -> 0x072d, all -> 0x0723 }
            goto L_0x0734
        L_0x0723:
            r0 = move-exception
            r2 = r38
            r3 = r0
            r4 = r12
            r6 = r14
            r1 = r16
            goto L_0x0855
        L_0x072d:
            r0 = move-exception
            r4 = r12
            r6 = r14
            goto L_0x011f
        L_0x0732:
            r12 = r3
            r14 = r6
        L_0x0734:
            r12.release()
            if (r14 == 0) goto L_0x0747
            r1 = 0
            r14.finishMovie(r1)     // Catch:{ Exception -> 0x073e }
            goto L_0x0747
        L_0x073e:
            r0 = move-exception
            r1 = r0
            java.lang.String r0 = r1.getMessage()
            android.util.Log.e(r13, r0)
        L_0x0747:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = r16
            r0.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            long r1 = r1 - r17
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r13, r0)
            goto L_0x0802
        L_0x0763:
            r0 = move-exception
            r12 = r3
            r14 = r6
            r1 = r16
            r2 = r38
            r3 = r0
            r4 = r12
            goto L_0x0855
        L_0x076e:
            r0 = move-exception
            r12 = r3
            r22 = r14
            r23 = r15
            r1 = r16
            r14 = r6
            r4 = r12
            goto L_0x07cd
        L_0x0779:
            r12 = r3
            r14 = r6
            r1 = r16
            r12.release()
            if (r14 == 0) goto L_0x0790
            r2 = 0
            r14.finishMovie(r2)     // Catch:{ Exception -> 0x0787 }
            goto L_0x0790
        L_0x0787:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r2.getMessage()
            android.util.Log.e(r13, r0)
        L_0x0790:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            long r1 = r1 - r17
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r13, r0)
            goto L_0x00d6
        L_0x07aa:
            r0 = move-exception
            r14 = r6
            r1 = r16
            r4 = 0
            goto L_0x07bf
        L_0x07b0:
            r0 = move-exception
            r22 = r14
            r23 = r15
            r1 = r16
            r14 = r6
            r4 = 0
            goto L_0x07cd
        L_0x07ba:
            r0 = move-exception
            r1 = r16
            r4 = 0
            r6 = 0
        L_0x07bf:
            r2 = r38
        L_0x07c1:
            r3 = r0
            goto L_0x0855
        L_0x07c4:
            r0 = move-exception
            r22 = r14
            r23 = r15
            r1 = r16
            r4 = 0
            r6 = 0
        L_0x07cd:
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0852 }
            android.util.Log.e(r13, r0)     // Catch:{ all -> 0x0852 }
            if (r4 == 0) goto L_0x07d9
            r4.release()
        L_0x07d9:
            if (r6 == 0) goto L_0x07e9
            r2 = 0
            r6.finishMovie(r2)     // Catch:{ Exception -> 0x07e0 }
            goto L_0x07e9
        L_0x07e0:
            r0 = move-exception
            r2 = r0
            java.lang.String r0 = r2.getMessage()
            android.util.Log.e(r13, r0)
        L_0x07e9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            long r1 = r1 - r17
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r13, r0)
            r11 = 1
        L_0x0802:
            r2 = r38
            r1 = 1
            r2.didWriteData(r1, r11)
            cachedFile = r23
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r2.path
            r0.append(r1)
            java.lang.String r1 = ""
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r3 = "ViratPath"
            android.util.Log.e(r3, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = r23.getPath()
            r0.append(r4)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r3, r0)
            if (r22 == 0) goto L_0x0850
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = r22.getPath()
            r0.append(r4)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r3, r0)
        L_0x0850:
            r1 = 1
            return r1
        L_0x0852:
            r0 = move-exception
            goto L_0x07bf
        L_0x0855:
            if (r4 == 0) goto L_0x085a
            r4.release()
        L_0x085a:
            if (r6 == 0) goto L_0x086a
            r4 = 0
            r6.finishMovie(r4)     // Catch:{ Exception -> 0x0861 }
            goto L_0x086a
        L_0x0861:
            r0 = move-exception
            r4 = r0
            java.lang.String r0 = r4.getMessage()
            android.util.Log.e(r13, r0)
        L_0x086a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r17
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r13, r0)
            throw r3
        L_0x0883:
            r2 = r11
            r1 = 1
            r2.didWriteData(r1, r1)
            goto L_0x00d6
        L_0x088a:
            r2 = r11
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iceteck.silicompressorr.videocompression.MediaController.convertVideo(android.content.Context, android.net.Uri, java.lang.String, java.io.File, int, int, int):boolean");
    }

    public static void copyFile(File file, File file2) throws IOException {
        FileChannel channel = new FileInputStream(file).getChannel();
        FileChannel channel2 = new FileOutputStream(file2).getChannel();
        try {
            channel.transferTo(1, channel.size(), channel2);
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (channel2 != null) {
                channel2.close();
            }
        }
    }
}
