package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;

public class Blob implements Comparable<Blob> {
    private final ByteString bytes;

    private Blob(ByteString byteString) {
        this.bytes = byteString;
    }

    public static Blob fromBytes(byte[] bArr) {
        Preconditions.checkNotNull(bArr, "Provided bytes array must not be null.");
        return new Blob(ByteString.copyFrom(bArr));
    }

    public static Blob fromByteString(ByteString byteString) {
        Preconditions.checkNotNull(byteString, "Provided ByteString must not be null.");
        return new Blob(byteString);
    }

    public byte[] toBytes() {
        return this.bytes.toByteArray();
    }

    public String toString() {
        return "Blob { bytes=" + Util.toDebugString(this.bytes) + " }";
    }

    public ByteString toByteString() {
        return this.bytes;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Blob) && this.bytes.equals(((Blob) obj).bytes);
    }

    public int hashCode() {
        return this.bytes.hashCode();
    }

    public int compareTo(Blob blob) {
        return Util.compareByteStrings(this.bytes, blob.bytes);
    }
}
