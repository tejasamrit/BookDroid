package p012io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: io.grpc.internal.JsonParser */
public final class JsonParser {
    private static final Logger logger = Logger.getLogger(JsonParser.class.getName());

    private JsonParser() {
    }

    public static Object parse(String str) throws IOException {
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        try {
            return parseRecursive(jsonReader);
        } finally {
            try {
                jsonReader.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to close", e);
            }
        }
    }

    /* renamed from: io.grpc.internal.JsonParser$1 */
    static /* synthetic */ class C24531 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.gson.stream.JsonToken[] r0 = com.google.gson.stream.JsonToken.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$gson$stream$JsonToken = r0
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.BEGIN_ARRAY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.BEGIN_OBJECT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.STRING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NUMBER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NULL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p012io.grpc.internal.JsonParser.C24531.<clinit>():void");
        }
    }

    private static Object parseRecursive(JsonReader jsonReader) throws IOException {
        Preconditions.checkState(jsonReader.hasNext(), "unexpected end of JSON");
        switch (C24531.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()]) {
            case 1:
                return parseJsonArray(jsonReader);
            case 2:
                return parseJsonObject(jsonReader);
            case 3:
                return jsonReader.nextString();
            case 4:
                return Double.valueOf(jsonReader.nextDouble());
            case 5:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 6:
                return parseJsonNull(jsonReader);
            default:
                throw new IllegalStateException("Bad token: " + jsonReader.getPath());
        }
    }

    private static Map<String, ?> parseJsonObject(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (jsonReader.hasNext()) {
            linkedHashMap.put(jsonReader.nextName(), parseRecursive(jsonReader));
        }
        boolean z = jsonReader.peek() == JsonToken.END_OBJECT;
        Preconditions.checkState(z, "Bad token: " + jsonReader.getPath());
        jsonReader.endObject();
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static List<?> parseJsonArray(JsonReader jsonReader) throws IOException {
        jsonReader.beginArray();
        ArrayList arrayList = new ArrayList();
        while (jsonReader.hasNext()) {
            arrayList.add(parseRecursive(jsonReader));
        }
        boolean z = jsonReader.peek() == JsonToken.END_ARRAY;
        Preconditions.checkState(z, "Bad token: " + jsonReader.getPath());
        jsonReader.endArray();
        return Collections.unmodifiableList(arrayList);
    }

    private static Void parseJsonNull(JsonReader jsonReader) throws IOException {
        jsonReader.nextNull();
        return null;
    }
}
