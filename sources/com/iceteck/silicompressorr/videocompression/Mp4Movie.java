package com.iceteck.silicompressorr.videocompression;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.googlecode.mp4parser.util.Matrix;
import java.io.File;
import java.util.ArrayList;

public class Mp4Movie {
    private File cacheFile;
    private int height;
    private Matrix matrix = Matrix.ROTATE_0;
    private ArrayList<Track> tracks = new ArrayList<>();
    private int width;

    public Matrix getMatrix() {
        return this.matrix;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setCacheFile(File file) {
        this.cacheFile = file;
    }

    public void setRotation(int i) {
        if (i == 0) {
            this.matrix = Matrix.ROTATE_0;
        } else if (i == 90) {
            this.matrix = Matrix.ROTATE_90;
        } else if (i == 180) {
            this.matrix = Matrix.ROTATE_180;
        } else if (i == 270) {
            this.matrix = Matrix.ROTATE_270;
        }
    }

    public void setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public ArrayList<Track> getTracks() {
        return this.tracks;
    }

    public File getCacheFile() {
        return this.cacheFile;
    }

    public void addSample(int i, long j, MediaCodec.BufferInfo bufferInfo) throws Exception {
        if (i >= 0 && i < this.tracks.size()) {
            this.tracks.get(i).addSample(j, bufferInfo);
        }
    }

    public int addTrack(MediaFormat mediaFormat, boolean z) throws Exception {
        this.tracks.add(new Track(this.tracks.size(), mediaFormat, z));
        return this.tracks.size() - 1;
    }
}
