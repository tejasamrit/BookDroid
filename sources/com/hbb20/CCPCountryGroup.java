package com.hbb20;

import android.content.Context;
import android.util.SparseArray;
import com.hbb20.CountryCodePicker;
import java.util.HashMap;
import java.util.Map;

public class CCPCountryGroup {
    private static SparseArray<CCPCountryGroup> countryGroups;
    int areaCodeLength;
    String defaultNameCode;
    private HashMap<String, String> nameCodeToAreaCodesMap;

    private CCPCountryGroup(String str, int i, HashMap<String, String> hashMap) {
        this.defaultNameCode = str;
        this.areaCodeLength = i;
        this.nameCodeToAreaCodesMap = hashMap;
    }

    private static void initializeGroups() {
        countryGroups = new SparseArray<>();
        addGroupForPhoneCode1();
        addGroupForPhoneCode44();
        addGroupForPhoneCode358();
    }

    private static void addGroupForPhoneCode358() {
        HashMap hashMap = new HashMap();
        hashMap.put("ax", "18");
        countryGroups.put(358, new CCPCountryGroup("fi", 2, hashMap));
    }

    private static void addGroupForPhoneCode44() {
        HashMap hashMap = new HashMap();
        hashMap.put("gg", "1481");
        hashMap.put("im", "1624");
        hashMap.put("je", "1534");
        countryGroups.put(44, new CCPCountryGroup("gb", 4, hashMap));
    }

    private static void addGroupForPhoneCode1() {
        HashMap hashMap = new HashMap();
        hashMap.put("ag", "268");
        hashMap.put("ai", "264");
        hashMap.put("as", "684");
        hashMap.put("bb", "246");
        hashMap.put("bm", "441");
        hashMap.put("bs", "242");
        hashMap.put("ca", "204/226/236/249/250/289/306/343/365/403/416/418/431/437/438/450/506/514/519/579/581/587/600/601/604/613/639/647/705/709/769/778/780/782/807/819/825/867/873/902/905/");
        hashMap.put("dm", "767");
        hashMap.put("do", "809/829/849");
        hashMap.put("gd", "473");
        hashMap.put("gu", "671");
        hashMap.put("jm", "876");
        hashMap.put("kn", "869");
        hashMap.put("ky", "345");
        hashMap.put("lc", "758");
        hashMap.put("mp", "670");
        hashMap.put("ms", "664");
        hashMap.put("pr", "787");
        hashMap.put("sx", "721");
        hashMap.put("tc", "649");
        hashMap.put("tt", "868");
        hashMap.put("vc", "784");
        hashMap.put("vg", "284");
        hashMap.put("vi", "340");
        countryGroups.put(1, new CCPCountryGroup("us", 3, hashMap));
    }

    public static CCPCountryGroup getCountryGroupForPhoneCode(int i) {
        if (countryGroups == null) {
            initializeGroups();
        }
        return countryGroups.get(i);
    }

    public CCPCountry getCountryForAreaCode(Context context, CountryCodePicker.Language language, String str) {
        String str2 = this.defaultNameCode;
        for (Map.Entry next : this.nameCodeToAreaCodesMap.entrySet()) {
            if (((String) next.getValue()).contains(str)) {
                str2 = (String) next.getKey();
            }
        }
        return CCPCountry.getCountryForNameCodeFromLibraryMasterList(context, language, str2);
    }
}
