package com.hbb20;

import android.content.Context;
import android.util.Log;
import com.hbb20.CountryCodePicker;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CCPCountry implements Comparable<CCPCountry> {
    private static String ANGUILLA_AREA_CODES = "264";
    private static String ANTIGUA_AND_BARBUDA_AREA_CODES = "268";
    private static String BAHAMAS_AREA_CODES = "242";
    private static String BARBADOS_AREA_CODES = "246";
    private static String BERMUDA_AREA_CODES = "441";
    private static String BRITISH_VIRGIN_ISLANDS_AREA_CODES = "284";
    private static String CANADA_AREA_CODES = "204/226/236/249/250/289/306/343/365/403/416/418/431/437/438/450/506/514/519/579/581/587/600/604/613/639/647/705/709/769/778/780/782/807/819/825/867/873/902/905/";
    private static String CAYMAN_ISLANDS_AREA_CODES = "345";
    static int DEFAULT_FLAG_RES = -99;
    private static String DOMINICAN_REPUBLIC_AREA_CODES = "809/829/849";
    private static String DOMINICA_AREA_CODES = "767";
    private static String GRENADA_AREA_CODES = "473";
    private static String ISLE_OF_MAN = "1624";
    private static String JAMAICA_AREA_CODES = "876";
    private static String MONTSERRAT_AREA_CODES = "664";
    private static String PUERTO_RICO_AREA_CODES = "787";
    private static String SAINT_KITTS_AND_NEVIS_AREA_CODES = "869";
    private static String SAINT_LUCIA_AREA_CODES = "758";
    private static String SAINT_VINCENT_AND_THE_GRENADINES_AREA_CODES = "784";
    private static String SINT_MAARTEN_AREA_CODES = "721";
    static String TAG = "Class Country";
    private static String TRINIDAD_AND_TOBAGO_AREA_CODES = "868";
    private static String TURKS_AND_CAICOS_ISLANDS_AREA_CODES = "649";
    private static String US_VIRGIN_ISLANDS_AREA_CODES = "340";
    static String dialogTitle;
    static CountryCodePicker.Language loadedLibraryMasterListLanguage;
    static List<CCPCountry> loadedLibraryMaterList;
    static String noResultFoundAckMessage;
    static String searchHintMessage;
    String englishName;
    int flagResID = DEFAULT_FLAG_RES;
    String name;
    String nameCode;
    String phoneCode;

    public CCPCountry() {
    }

    public CCPCountry(String str, String str2, String str3, int i) {
        this.nameCode = str.toUpperCase(Locale.ROOT);
        this.phoneCode = str2;
        this.name = str3;
        this.flagResID = i;
    }

    static CountryCodePicker.Language getLoadedLibraryMasterListLanguage() {
        return loadedLibraryMasterListLanguage;
    }

    static void setLoadedLibraryMasterListLanguage(CountryCodePicker.Language language) {
        loadedLibraryMasterListLanguage = language;
    }

    public static List<CCPCountry> getLoadedLibraryMaterList() {
        return loadedLibraryMaterList;
    }

    static void setLoadedLibraryMaterList(List<CCPCountry> list) {
        loadedLibraryMaterList = list;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x010a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void loadDataFromXML(android.content.Context r8, com.hbb20.CountryCodePicker.Language r9) {
        /*
            java.lang.String r0 = ""
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            org.xmlpull.v1.XmlPullParserFactory r2 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            org.xmlpull.v1.XmlPullParser r2 = r2.newPullParser()     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            android.content.res.Resources r3 = r8.getResources()     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            android.content.res.Resources r4 = r8.getResources()     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            r5.<init>()     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            java.lang.String r6 = "ccp_"
            r5.append(r6)     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            java.lang.String r6 = r9.toString()     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            java.util.Locale r7 = java.util.Locale.ROOT     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            java.lang.String r6 = r6.toLowerCase(r7)     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            r5.append(r6)     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            java.lang.String r5 = r5.toString()     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            java.lang.String r6 = "raw"
            java.lang.String r8 = r8.getPackageName()     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            int r8 = r4.getIdentifier(r5, r6, r8)     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            java.io.InputStream r8 = r3.openRawResource(r8)     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            java.lang.String r3 = "UTF-8"
            r2.setInput(r8, r3)     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            int r8 = r2.getEventType()     // Catch:{ XmlPullParserException -> 0x00d9, IOException -> 0x00d2, Exception -> 0x00cb }
            r3 = r0
            r4 = r3
        L_0x004b:
            r5 = 1
            if (r8 == r5) goto L_0x00c0
            java.lang.String r5 = r2.getName()     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r6 = 3
            if (r8 == r6) goto L_0x0056
            goto L_0x00bb
        L_0x0056:
            java.lang.String r8 = "country"
            boolean r8 = r5.equals(r8)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r6 = 0
            if (r8 == 0) goto L_0x0090
            com.hbb20.CCPCountry r8 = new com.hbb20.CCPCountry     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            java.lang.String r5 = "name_code"
            java.lang.String r5 = r2.getAttributeValue(r6, r5)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            java.lang.String r5 = r5.toUpperCase()     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r8.setNameCode(r5)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            java.lang.String r5 = "phone_code"
            java.lang.String r5 = r2.getAttributeValue(r6, r5)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r8.setPhoneCode(r5)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            java.lang.String r5 = "english_name"
            java.lang.String r5 = r2.getAttributeValue(r6, r5)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r8.setEnglishName(r5)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            java.lang.String r5 = "name"
            java.lang.String r5 = r2.getAttributeValue(r6, r5)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r8.setName(r5)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r1.add(r8)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            goto L_0x00bb
        L_0x0090:
            java.lang.String r8 = "ccp_dialog_title"
            boolean r8 = r5.equals(r8)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            java.lang.String r7 = "translation"
            if (r8 == 0) goto L_0x00a0
            java.lang.String r8 = r2.getAttributeValue(r6, r7)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r0 = r8
            goto L_0x00bb
        L_0x00a0:
            java.lang.String r8 = "ccp_dialog_search_hint_message"
            boolean r8 = r5.equals(r8)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            if (r8 == 0) goto L_0x00ae
            java.lang.String r8 = r2.getAttributeValue(r6, r7)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r3 = r8
            goto L_0x00bb
        L_0x00ae:
            java.lang.String r8 = "ccp_dialog_no_result_ack_message"
            boolean r8 = r5.equals(r8)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            if (r8 == 0) goto L_0x00bb
            java.lang.String r8 = r2.getAttributeValue(r6, r7)     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            r4 = r8
        L_0x00bb:
            int r8 = r2.next()     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            goto L_0x004b
        L_0x00c0:
            loadedLibraryMasterListLanguage = r9     // Catch:{ XmlPullParserException -> 0x00c7, IOException -> 0x00c5, Exception -> 0x00c3 }
            goto L_0x00df
        L_0x00c3:
            r8 = move-exception
            goto L_0x00ce
        L_0x00c5:
            r8 = move-exception
            goto L_0x00d5
        L_0x00c7:
            r8 = move-exception
            goto L_0x00dc
        L_0x00c9:
            r8 = move-exception
            goto L_0x0114
        L_0x00cb:
            r8 = move-exception
            r3 = r0
            r4 = r3
        L_0x00ce:
            r8.printStackTrace()     // Catch:{ all -> 0x00c9 }
            goto L_0x00df
        L_0x00d2:
            r8 = move-exception
            r3 = r0
            r4 = r3
        L_0x00d5:
            r8.printStackTrace()     // Catch:{ all -> 0x00c9 }
            goto L_0x00df
        L_0x00d9:
            r8 = move-exception
            r3 = r0
            r4 = r3
        L_0x00dc:
            r8.printStackTrace()     // Catch:{ all -> 0x00c9 }
        L_0x00df:
            int r8 = r1.size()
            if (r8 != 0) goto L_0x00ed
            com.hbb20.CountryCodePicker$Language r8 = com.hbb20.CountryCodePicker.Language.ENGLISH
            loadedLibraryMasterListLanguage = r8
            java.util.List r1 = getLibraryMasterCountriesEnglish()
        L_0x00ed:
            int r8 = r0.length()
            if (r8 <= 0) goto L_0x00f4
            goto L_0x00f6
        L_0x00f4:
            java.lang.String r0 = "Select a country"
        L_0x00f6:
            dialogTitle = r0
            int r8 = r3.length()
            if (r8 <= 0) goto L_0x00ff
            goto L_0x0101
        L_0x00ff:
            java.lang.String r3 = "Search..."
        L_0x0101:
            searchHintMessage = r3
            int r8 = r4.length()
            if (r8 <= 0) goto L_0x010a
            goto L_0x010c
        L_0x010a:
            java.lang.String r4 = "Results not found"
        L_0x010c:
            noResultFoundAckMessage = r4
            loadedLibraryMaterList = r1
            java.util.Collections.sort(r1)
            return
        L_0x0114:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hbb20.CCPCountry.loadDataFromXML(android.content.Context, com.hbb20.CountryCodePicker$Language):void");
    }

    public static String getDialogTitle(Context context, CountryCodePicker.Language language) {
        String str;
        CountryCodePicker.Language language2 = loadedLibraryMasterListLanguage;
        if (language2 == null || language2 != language || (str = dialogTitle) == null || str.length() == 0) {
            loadDataFromXML(context, language);
        }
        return dialogTitle;
    }

    public static String getSearchHintMessage(Context context, CountryCodePicker.Language language) {
        String str;
        CountryCodePicker.Language language2 = loadedLibraryMasterListLanguage;
        if (language2 == null || language2 != language || (str = searchHintMessage) == null || str.length() == 0) {
            loadDataFromXML(context, language);
        }
        return searchHintMessage;
    }

    public static String getNoResultFoundAckMessage(Context context, CountryCodePicker.Language language) {
        String str;
        CountryCodePicker.Language language2 = loadedLibraryMasterListLanguage;
        if (language2 == null || language2 != language || (str = noResultFoundAckMessage) == null || str.length() == 0) {
            loadDataFromXML(context, language);
        }
        return noResultFoundAckMessage;
    }

    public static void setDialogTitle(String str) {
        dialogTitle = str;
    }

    public static void setSearchHintMessage(String str) {
        searchHintMessage = str;
    }

    public static void setNoResultFoundAckMessage(String str) {
        noResultFoundAckMessage = str;
    }

    public static CCPCountry getCountryForCode(Context context, CountryCodePicker.Language language, List<CCPCountry> list, String str) {
        if (list != null && !list.isEmpty()) {
            for (CCPCountry next : list) {
                if (next.getPhoneCode().equals(str)) {
                    return next;
                }
            }
        }
        for (CCPCountry next2 : getLibraryMasterCountryList(context, language)) {
            if (next2.getPhoneCode().equals(str)) {
                return next2;
            }
        }
        return null;
    }

    static CCPCountry getCountryForCodeFromEnglishList(String str) {
        for (CCPCountry next : getLibraryMasterCountriesEnglish()) {
            if (next.getPhoneCode().equals(str)) {
                return next;
            }
        }
        return null;
    }

    static List<CCPCountry> getCustomMasterCountryList(Context context, CountryCodePicker countryCodePicker) {
        countryCodePicker.refreshCustomMasterList();
        if (countryCodePicker.customMasterCountriesList == null || countryCodePicker.customMasterCountriesList.size() <= 0) {
            return getLibraryMasterCountryList(context, countryCodePicker.getLanguageToApply());
        }
        return countryCodePicker.getCustomMasterCountriesList();
    }

    static CCPCountry getCountryForNameCodeFromCustomMasterList(Context context, List<CCPCountry> list, CountryCodePicker.Language language, String str) {
        if (list == null || list.size() == 0) {
            return getCountryForNameCodeFromLibraryMasterList(context, language, str);
        }
        for (CCPCountry next : list) {
            if (next.getNameCode().equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    public static CCPCountry getCountryForNameCodeFromLibraryMasterList(Context context, CountryCodePicker.Language language, String str) {
        for (CCPCountry next : getLibraryMasterCountryList(context, language)) {
            if (next.getNameCode().equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    static CCPCountry getCountryForNameCodeFromEnglishList(String str) {
        for (CCPCountry next : getLibraryMasterCountriesEnglish()) {
            if (next.getNameCode().equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    static CCPCountry getCountryForCode(Context context, CountryCodePicker.Language language, List<CCPCountry> list, int i) {
        return getCountryForCode(context, language, list, i + "");
    }

    static CCPCountry getCountryForNumber(Context context, CountryCodePicker.Language language, List<CCPCountry> list, String str) {
        CCPCountryGroup cCPCountryGroup;
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() != 0) {
            int i = 0;
            if (trim.charAt(0) == '+') {
                i = 1;
            }
            for (int i2 = i; i2 <= trim.length(); i2++) {
                String substring = trim.substring(i, i2);
                try {
                    cCPCountryGroup = CCPCountryGroup.getCountryGroupForPhoneCode(Integer.parseInt(substring));
                } catch (Exception unused) {
                    cCPCountryGroup = null;
                }
                if (cCPCountryGroup != null) {
                    int length = i + substring.length();
                    if (trim.length() >= cCPCountryGroup.areaCodeLength + length) {
                        return cCPCountryGroup.getCountryForAreaCode(context, language, trim.substring(length, cCPCountryGroup.areaCodeLength + length));
                    }
                    return getCountryForNameCodeFromLibraryMasterList(context, language, cCPCountryGroup.defaultNameCode);
                }
                CCPCountry countryForCode = getCountryForCode(context, language, list, substring);
                if (countryForCode != null) {
                    return countryForCode;
                }
            }
        }
        return null;
    }

    public static CCPCountry getCountryForNumber(Context context, CountryCodePicker.Language language, String str) {
        return getCountryForNumber(context, language, (List<CCPCountry>) null, str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v37, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v39, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v41, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v43, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v44, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v45, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v46, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v47, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v48, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v49, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v52, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v53, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v55, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v56, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v57, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v59, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v60, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v61, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v62, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v63, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v67, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v68, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v70, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v71, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v72, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v73, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v74, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v75, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v76, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v77, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v78, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v79, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v80, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v81, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v82, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v83, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v84, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v85, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v86, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v87, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v88, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v89, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v90, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v91, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v92, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v93, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v94, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v95, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v96, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v97, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v98, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v99, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v100, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v101, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v102, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v103, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v104, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v105, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v106, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v107, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v108, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v109, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v110, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v111, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v112, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v113, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v114, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v115, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v116, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v117, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v118, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v119, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v120, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v121, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v122, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v123, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v124, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v125, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v126, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v127, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v128, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v129, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v130, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v131, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v132, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v133, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v134, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v135, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v136, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v137, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v138, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v139, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v140, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v141, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v142, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v143, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v144, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v145, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v146, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v147, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v148, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v149, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v150, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v151, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v152, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v153, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v154, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v155, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v156, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v157, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v158, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v159, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v160, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v161, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v162, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v163, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v164, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v165, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v166, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v167, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v168, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v169, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v170, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v171, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v172, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v173, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v174, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v175, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v176, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v177, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v178, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v179, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v180, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v181, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v182, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v183, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v184, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v185, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v186, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v187, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v188, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v189, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v190, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v191, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v192, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v193, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v194, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v195, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v196, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v197, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v198, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v199, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v200, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v201, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v202, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v203, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v204, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v205, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v206, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v207, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v208, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v209, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v210, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v211, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v212, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v213, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v214, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v215, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v216, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v217, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v218, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v219, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v220, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v221, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v222, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v223, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v224, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v225, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v226, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v227, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v228, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v229, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v230, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v231, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v232, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v233, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v234, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v235, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v236, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v237, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v238, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v239, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v240, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v241, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v242, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v243, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int getFlagMasterResID(com.hbb20.CCPCountry r2) {
        /*
            java.lang.String r2 = r2.getNameCode()
            java.lang.String r2 = r2.toLowerCase()
            r2.hashCode()
            int r0 = r2.hashCode()
            r1 = -1
            switch(r0) {
                case 3107: goto L_0x0d2e;
                case 3108: goto L_0x0d23;
                case 3109: goto L_0x0d18;
                case 3110: goto L_0x0d0d;
                case 3112: goto L_0x0d02;
                case 3115: goto L_0x0cf7;
                case 3116: goto L_0x0cec;
                case 3118: goto L_0x0ce1;
                case 3120: goto L_0x0cd3;
                case 3121: goto L_0x0cc5;
                case 3122: goto L_0x0cb7;
                case 3123: goto L_0x0ca9;
                case 3124: goto L_0x0c9b;
                case 3126: goto L_0x0c8d;
                case 3127: goto L_0x0c7f;
                case 3129: goto L_0x0c71;
                case 3135: goto L_0x0c63;
                case 3136: goto L_0x0c55;
                case 3138: goto L_0x0c47;
                case 3139: goto L_0x0c39;
                case 3140: goto L_0x0c2b;
                case 3141: goto L_0x0c1d;
                case 3142: goto L_0x0c0f;
                case 3143: goto L_0x0c01;
                case 3144: goto L_0x0bf3;
                case 3146: goto L_0x0be5;
                case 3147: goto L_0x0bd7;
                case 3148: goto L_0x0bc9;
                case 3149: goto L_0x0bbb;
                case 3152: goto L_0x0bad;
                case 3153: goto L_0x0b9f;
                case 3154: goto L_0x0b91;
                case 3157: goto L_0x0b83;
                case 3159: goto L_0x0b75;
                case 3160: goto L_0x0b67;
                case 3166: goto L_0x0b59;
                case 3168: goto L_0x0b4b;
                case 3169: goto L_0x0b3d;
                case 3171: goto L_0x0b2f;
                case 3172: goto L_0x0b21;
                case 3173: goto L_0x0b13;
                case 3174: goto L_0x0b05;
                case 3176: goto L_0x0af7;
                case 3177: goto L_0x0ae9;
                case 3178: goto L_0x0adb;
                case 3179: goto L_0x0acd;
                case 3180: goto L_0x0abf;
                case 3183: goto L_0x0ab1;
                case 3186: goto L_0x0aa3;
                case 3187: goto L_0x0a95;
                case 3188: goto L_0x0a87;
                case 3189: goto L_0x0a79;
                case 3190: goto L_0x0a6b;
                case 3191: goto L_0x0a5d;
                case 3201: goto L_0x0a4f;
                case 3206: goto L_0x0a41;
                case 3207: goto L_0x0a33;
                case 3209: goto L_0x0a25;
                case 3211: goto L_0x0a17;
                case 3222: goto L_0x0a09;
                case 3230: goto L_0x09fb;
                case 3232: goto L_0x09ed;
                case 3234: goto L_0x09df;
                case 3245: goto L_0x09d1;
                case 3246: goto L_0x09c3;
                case 3247: goto L_0x09b5;
                case 3267: goto L_0x09a7;
                case 3268: goto L_0x0999;
                case 3269: goto L_0x098b;
                case 3271: goto L_0x097d;
                case 3273: goto L_0x096f;
                case 3276: goto L_0x0961;
                case 3290: goto L_0x0953;
                case 3291: goto L_0x0945;
                case 3293: goto L_0x0937;
                case 3294: goto L_0x0929;
                case 3295: goto L_0x091b;
                case 3296: goto L_0x090d;
                case 3297: goto L_0x08ff;
                case 3298: goto L_0x08f1;
                case 3301: goto L_0x08e3;
                case 3302: goto L_0x08d5;
                case 3303: goto L_0x08c7;
                case 3305: goto L_0x08b9;
                case 3306: goto L_0x08ab;
                case 3307: goto L_0x089d;
                case 3309: goto L_0x088f;
                case 3310: goto L_0x0881;
                case 3312: goto L_0x0873;
                case 3314: goto L_0x0865;
                case 3331: goto L_0x0857;
                case 3334: goto L_0x0849;
                case 3338: goto L_0x083b;
                case 3340: goto L_0x082d;
                case 3341: goto L_0x081f;
                case 3355: goto L_0x0811;
                case 3356: goto L_0x0803;
                case 3363: goto L_0x07f5;
                case 3364: goto L_0x07e7;
                case 3365: goto L_0x07d9;
                case 3366: goto L_0x07cb;
                case 3368: goto L_0x07bd;
                case 3369: goto L_0x07af;
                case 3370: goto L_0x07a1;
                case 3371: goto L_0x0793;
                case 3387: goto L_0x0785;
                case 3395: goto L_0x0777;
                case 3397: goto L_0x0769;
                case 3398: goto L_0x075b;
                case 3418: goto L_0x074d;
                case 3420: goto L_0x073f;
                case 3421: goto L_0x0731;
                case 3422: goto L_0x0723;
                case 3426: goto L_0x0715;
                case 3427: goto L_0x0707;
                case 3429: goto L_0x06f9;
                case 3431: goto L_0x06eb;
                case 3436: goto L_0x06dd;
                case 3438: goto L_0x06cf;
                case 3439: goto L_0x06c1;
                case 3445: goto L_0x06b3;
                case 3446: goto L_0x06a5;
                case 3447: goto L_0x0697;
                case 3453: goto L_0x0689;
                case 3455: goto L_0x067b;
                case 3462: goto L_0x066d;
                case 3463: goto L_0x065f;
                case 3464: goto L_0x0651;
                case 3465: goto L_0x0643;
                case 3466: goto L_0x0635;
                case 3469: goto L_0x0627;
                case 3476: goto L_0x0619;
                case 3478: goto L_0x060b;
                case 3479: goto L_0x05fd;
                case 3480: goto L_0x05ef;
                case 3481: goto L_0x05e1;
                case 3482: goto L_0x05d3;
                case 3483: goto L_0x05c5;
                case 3486: goto L_0x05b7;
                case 3487: goto L_0x05a9;
                case 3488: goto L_0x059b;
                case 3489: goto L_0x058d;
                case 3490: goto L_0x057f;
                case 3491: goto L_0x0571;
                case 3492: goto L_0x0563;
                case 3493: goto L_0x0555;
                case 3494: goto L_0x0547;
                case 3495: goto L_0x0539;
                case 3496: goto L_0x052b;
                case 3497: goto L_0x051d;
                case 3498: goto L_0x050f;
                case 3499: goto L_0x0501;
                case 3500: goto L_0x04f3;
                case 3501: goto L_0x04e5;
                case 3507: goto L_0x04d7;
                case 3509: goto L_0x04c9;
                case 3511: goto L_0x04bb;
                case 3512: goto L_0x04ad;
                case 3513: goto L_0x049f;
                case 3515: goto L_0x0491;
                case 3518: goto L_0x0483;
                case 3521: goto L_0x0475;
                case 3522: goto L_0x0467;
                case 3524: goto L_0x0459;
                case 3527: goto L_0x044b;
                case 3532: goto L_0x043d;
                case 3550: goto L_0x042f;
                case 3569: goto L_0x0421;
                case 3573: goto L_0x0413;
                case 3574: goto L_0x0405;
                case 3575: goto L_0x03f7;
                case 3576: goto L_0x03e9;
                case 3579: goto L_0x03db;
                case 3580: goto L_0x03cd;
                case 3581: goto L_0x03bf;
                case 3582: goto L_0x03b1;
                case 3586: goto L_0x03a3;
                case 3587: goto L_0x0395;
                case 3588: goto L_0x0387;
                case 3591: goto L_0x0379;
                case 3593: goto L_0x036b;
                case 3600: goto L_0x035d;
                case 3635: goto L_0x034f;
                case 3645: goto L_0x0341;
                case 3649: goto L_0x0333;
                case 3651: goto L_0x0325;
                case 3653: goto L_0x0317;
                case 3662: goto L_0x0309;
                case 3663: goto L_0x02fb;
                case 3664: goto L_0x02ed;
                case 3665: goto L_0x02df;
                case 3666: goto L_0x02d1;
                case 3668: goto L_0x02c3;
                case 3669: goto L_0x02b5;
                case 3670: goto L_0x02a7;
                case 3672: goto L_0x0299;
                case 3673: goto L_0x028b;
                case 3674: goto L_0x027d;
                case 3675: goto L_0x026f;
                case 3676: goto L_0x0261;
                case 3679: goto L_0x0253;
                case 3680: goto L_0x0245;
                case 3681: goto L_0x0237;
                case 3683: goto L_0x0229;
                case 3685: goto L_0x021b;
                case 3686: goto L_0x020d;
                case 3687: goto L_0x01ff;
                case 3695: goto L_0x01f1;
                case 3696: goto L_0x01e3;
                case 3699: goto L_0x01d5;
                case 3700: goto L_0x01c7;
                case 3702: goto L_0x01b9;
                case 3703: goto L_0x01ab;
                case 3704: goto L_0x019d;
                case 3705: goto L_0x018f;
                case 3706: goto L_0x0181;
                case 3707: goto L_0x0173;
                case 3710: goto L_0x0165;
                case 3712: goto L_0x0157;
                case 3714: goto L_0x0149;
                case 3715: goto L_0x013b;
                case 3718: goto L_0x012d;
                case 3724: goto L_0x011f;
                case 3730: goto L_0x0111;
                case 3742: goto L_0x0103;
                case 3748: goto L_0x00f5;
                case 3749: goto L_0x00e7;
                case 3755: goto L_0x00d9;
                case 3757: goto L_0x00cb;
                case 3759: goto L_0x00bd;
                case 3761: goto L_0x00af;
                case 3763: goto L_0x00a1;
                case 3768: goto L_0x0093;
                case 3775: goto L_0x0085;
                case 3791: goto L_0x0077;
                case 3804: goto L_0x0069;
                case 3827: goto L_0x005b;
                case 3852: goto L_0x004d;
                case 3867: goto L_0x003f;
                case 3879: goto L_0x0031;
                case 3891: goto L_0x0023;
                case 3901: goto L_0x0015;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0d38
        L_0x0015:
            java.lang.String r0 = "zw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x001f
            goto L_0x0d38
        L_0x001f:
            r1 = 241(0xf1, float:3.38E-43)
            goto L_0x0d38
        L_0x0023:
            java.lang.String r0 = "zm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x002d
            goto L_0x0d38
        L_0x002d:
            r1 = 240(0xf0, float:3.36E-43)
            goto L_0x0d38
        L_0x0031:
            java.lang.String r0 = "za"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x003b
            goto L_0x0d38
        L_0x003b:
            r1 = 239(0xef, float:3.35E-43)
            goto L_0x0d38
        L_0x003f:
            java.lang.String r0 = "yt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0049
            goto L_0x0d38
        L_0x0049:
            r1 = 238(0xee, float:3.34E-43)
            goto L_0x0d38
        L_0x004d:
            java.lang.String r0 = "ye"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0057
            goto L_0x0d38
        L_0x0057:
            r1 = 237(0xed, float:3.32E-43)
            goto L_0x0d38
        L_0x005b:
            java.lang.String r0 = "xk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0065
            goto L_0x0d38
        L_0x0065:
            r1 = 236(0xec, float:3.31E-43)
            goto L_0x0d38
        L_0x0069:
            java.lang.String r0 = "ws"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0073
            goto L_0x0d38
        L_0x0073:
            r1 = 235(0xeb, float:3.3E-43)
            goto L_0x0d38
        L_0x0077:
            java.lang.String r0 = "wf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0081
            goto L_0x0d38
        L_0x0081:
            r1 = 234(0xea, float:3.28E-43)
            goto L_0x0d38
        L_0x0085:
            java.lang.String r0 = "vu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x008f
            goto L_0x0d38
        L_0x008f:
            r1 = 233(0xe9, float:3.27E-43)
            goto L_0x0d38
        L_0x0093:
            java.lang.String r0 = "vn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x009d
            goto L_0x0d38
        L_0x009d:
            r1 = 232(0xe8, float:3.25E-43)
            goto L_0x0d38
        L_0x00a1:
            java.lang.String r0 = "vi"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00ab
            goto L_0x0d38
        L_0x00ab:
            r1 = 231(0xe7, float:3.24E-43)
            goto L_0x0d38
        L_0x00af:
            java.lang.String r0 = "vg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00b9
            goto L_0x0d38
        L_0x00b9:
            r1 = 230(0xe6, float:3.22E-43)
            goto L_0x0d38
        L_0x00bd:
            java.lang.String r0 = "ve"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00c7
            goto L_0x0d38
        L_0x00c7:
            r1 = 229(0xe5, float:3.21E-43)
            goto L_0x0d38
        L_0x00cb:
            java.lang.String r0 = "vc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00d5
            goto L_0x0d38
        L_0x00d5:
            r1 = 228(0xe4, float:3.2E-43)
            goto L_0x0d38
        L_0x00d9:
            java.lang.String r0 = "va"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00e3
            goto L_0x0d38
        L_0x00e3:
            r1 = 227(0xe3, float:3.18E-43)
            goto L_0x0d38
        L_0x00e7:
            java.lang.String r0 = "uz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00f1
            goto L_0x0d38
        L_0x00f1:
            r1 = 226(0xe2, float:3.17E-43)
            goto L_0x0d38
        L_0x00f5:
            java.lang.String r0 = "uy"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00ff
            goto L_0x0d38
        L_0x00ff:
            r1 = 225(0xe1, float:3.15E-43)
            goto L_0x0d38
        L_0x0103:
            java.lang.String r0 = "us"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x010d
            goto L_0x0d38
        L_0x010d:
            r1 = 224(0xe0, float:3.14E-43)
            goto L_0x0d38
        L_0x0111:
            java.lang.String r0 = "ug"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x011b
            goto L_0x0d38
        L_0x011b:
            r1 = 223(0xdf, float:3.12E-43)
            goto L_0x0d38
        L_0x011f:
            java.lang.String r0 = "ua"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0129
            goto L_0x0d38
        L_0x0129:
            r1 = 222(0xde, float:3.11E-43)
            goto L_0x0d38
        L_0x012d:
            java.lang.String r0 = "tz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0137
            goto L_0x0d38
        L_0x0137:
            r1 = 221(0xdd, float:3.1E-43)
            goto L_0x0d38
        L_0x013b:
            java.lang.String r0 = "tw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0145
            goto L_0x0d38
        L_0x0145:
            r1 = 220(0xdc, float:3.08E-43)
            goto L_0x0d38
        L_0x0149:
            java.lang.String r0 = "tv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0153
            goto L_0x0d38
        L_0x0153:
            r1 = 219(0xdb, float:3.07E-43)
            goto L_0x0d38
        L_0x0157:
            java.lang.String r0 = "tt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0161
            goto L_0x0d38
        L_0x0161:
            r1 = 218(0xda, float:3.05E-43)
            goto L_0x0d38
        L_0x0165:
            java.lang.String r0 = "tr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x016f
            goto L_0x0d38
        L_0x016f:
            r1 = 217(0xd9, float:3.04E-43)
            goto L_0x0d38
        L_0x0173:
            java.lang.String r0 = "to"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x017d
            goto L_0x0d38
        L_0x017d:
            r1 = 216(0xd8, float:3.03E-43)
            goto L_0x0d38
        L_0x0181:
            java.lang.String r0 = "tn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x018b
            goto L_0x0d38
        L_0x018b:
            r1 = 215(0xd7, float:3.01E-43)
            goto L_0x0d38
        L_0x018f:
            java.lang.String r0 = "tm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0199
            goto L_0x0d38
        L_0x0199:
            r1 = 214(0xd6, float:3.0E-43)
            goto L_0x0d38
        L_0x019d:
            java.lang.String r0 = "tl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01a7
            goto L_0x0d38
        L_0x01a7:
            r1 = 213(0xd5, float:2.98E-43)
            goto L_0x0d38
        L_0x01ab:
            java.lang.String r0 = "tk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01b5
            goto L_0x0d38
        L_0x01b5:
            r1 = 212(0xd4, float:2.97E-43)
            goto L_0x0d38
        L_0x01b9:
            java.lang.String r0 = "tj"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01c3
            goto L_0x0d38
        L_0x01c3:
            r1 = 211(0xd3, float:2.96E-43)
            goto L_0x0d38
        L_0x01c7:
            java.lang.String r0 = "th"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01d1
            goto L_0x0d38
        L_0x01d1:
            r1 = 210(0xd2, float:2.94E-43)
            goto L_0x0d38
        L_0x01d5:
            java.lang.String r0 = "tg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01df
            goto L_0x0d38
        L_0x01df:
            r1 = 209(0xd1, float:2.93E-43)
            goto L_0x0d38
        L_0x01e3:
            java.lang.String r0 = "td"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01ed
            goto L_0x0d38
        L_0x01ed:
            r1 = 208(0xd0, float:2.91E-43)
            goto L_0x0d38
        L_0x01f1:
            java.lang.String r0 = "tc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01fb
            goto L_0x0d38
        L_0x01fb:
            r1 = 207(0xcf, float:2.9E-43)
            goto L_0x0d38
        L_0x01ff:
            java.lang.String r0 = "sz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0209
            goto L_0x0d38
        L_0x0209:
            r1 = 206(0xce, float:2.89E-43)
            goto L_0x0d38
        L_0x020d:
            java.lang.String r0 = "sy"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0217
            goto L_0x0d38
        L_0x0217:
            r1 = 205(0xcd, float:2.87E-43)
            goto L_0x0d38
        L_0x021b:
            java.lang.String r0 = "sx"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0225
            goto L_0x0d38
        L_0x0225:
            r1 = 204(0xcc, float:2.86E-43)
            goto L_0x0d38
        L_0x0229:
            java.lang.String r0 = "sv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0233
            goto L_0x0d38
        L_0x0233:
            r1 = 203(0xcb, float:2.84E-43)
            goto L_0x0d38
        L_0x0237:
            java.lang.String r0 = "st"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0241
            goto L_0x0d38
        L_0x0241:
            r1 = 202(0xca, float:2.83E-43)
            goto L_0x0d38
        L_0x0245:
            java.lang.String r0 = "ss"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x024f
            goto L_0x0d38
        L_0x024f:
            r1 = 201(0xc9, float:2.82E-43)
            goto L_0x0d38
        L_0x0253:
            java.lang.String r0 = "sr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x025d
            goto L_0x0d38
        L_0x025d:
            r1 = 200(0xc8, float:2.8E-43)
            goto L_0x0d38
        L_0x0261:
            java.lang.String r0 = "so"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x026b
            goto L_0x0d38
        L_0x026b:
            r1 = 199(0xc7, float:2.79E-43)
            goto L_0x0d38
        L_0x026f:
            java.lang.String r0 = "sn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0279
            goto L_0x0d38
        L_0x0279:
            r1 = 198(0xc6, float:2.77E-43)
            goto L_0x0d38
        L_0x027d:
            java.lang.String r0 = "sm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0287
            goto L_0x0d38
        L_0x0287:
            r1 = 197(0xc5, float:2.76E-43)
            goto L_0x0d38
        L_0x028b:
            java.lang.String r0 = "sl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0295
            goto L_0x0d38
        L_0x0295:
            r1 = 196(0xc4, float:2.75E-43)
            goto L_0x0d38
        L_0x0299:
            java.lang.String r0 = "sk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02a3
            goto L_0x0d38
        L_0x02a3:
            r1 = 195(0xc3, float:2.73E-43)
            goto L_0x0d38
        L_0x02a7:
            java.lang.String r0 = "si"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02b1
            goto L_0x0d38
        L_0x02b1:
            r1 = 194(0xc2, float:2.72E-43)
            goto L_0x0d38
        L_0x02b5:
            java.lang.String r0 = "sh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02bf
            goto L_0x0d38
        L_0x02bf:
            r1 = 193(0xc1, float:2.7E-43)
            goto L_0x0d38
        L_0x02c3:
            java.lang.String r0 = "sg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02cd
            goto L_0x0d38
        L_0x02cd:
            r1 = 192(0xc0, float:2.69E-43)
            goto L_0x0d38
        L_0x02d1:
            java.lang.String r0 = "se"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02db
            goto L_0x0d38
        L_0x02db:
            r1 = 191(0xbf, float:2.68E-43)
            goto L_0x0d38
        L_0x02df:
            java.lang.String r0 = "sd"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02e9
            goto L_0x0d38
        L_0x02e9:
            r1 = 190(0xbe, float:2.66E-43)
            goto L_0x0d38
        L_0x02ed:
            java.lang.String r0 = "sc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02f7
            goto L_0x0d38
        L_0x02f7:
            r1 = 189(0xbd, float:2.65E-43)
            goto L_0x0d38
        L_0x02fb:
            java.lang.String r0 = "sb"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0305
            goto L_0x0d38
        L_0x0305:
            r1 = 188(0xbc, float:2.63E-43)
            goto L_0x0d38
        L_0x0309:
            java.lang.String r0 = "sa"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0313
            goto L_0x0d38
        L_0x0313:
            r1 = 187(0xbb, float:2.62E-43)
            goto L_0x0d38
        L_0x0317:
            java.lang.String r0 = "rw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0321
            goto L_0x0d38
        L_0x0321:
            r1 = 186(0xba, float:2.6E-43)
            goto L_0x0d38
        L_0x0325:
            java.lang.String r0 = "ru"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x032f
            goto L_0x0d38
        L_0x032f:
            r1 = 185(0xb9, float:2.59E-43)
            goto L_0x0d38
        L_0x0333:
            java.lang.String r0 = "rs"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x033d
            goto L_0x0d38
        L_0x033d:
            r1 = 184(0xb8, float:2.58E-43)
            goto L_0x0d38
        L_0x0341:
            java.lang.String r0 = "ro"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x034b
            goto L_0x0d38
        L_0x034b:
            r1 = 183(0xb7, float:2.56E-43)
            goto L_0x0d38
        L_0x034f:
            java.lang.String r0 = "re"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0359
            goto L_0x0d38
        L_0x0359:
            r1 = 182(0xb6, float:2.55E-43)
            goto L_0x0d38
        L_0x035d:
            java.lang.String r0 = "qa"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0367
            goto L_0x0d38
        L_0x0367:
            r1 = 181(0xb5, float:2.54E-43)
            goto L_0x0d38
        L_0x036b:
            java.lang.String r0 = "py"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0375
            goto L_0x0d38
        L_0x0375:
            r1 = 180(0xb4, float:2.52E-43)
            goto L_0x0d38
        L_0x0379:
            java.lang.String r0 = "pw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0383
            goto L_0x0d38
        L_0x0383:
            r1 = 179(0xb3, float:2.51E-43)
            goto L_0x0d38
        L_0x0387:
            java.lang.String r0 = "pt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0391
            goto L_0x0d38
        L_0x0391:
            r1 = 178(0xb2, float:2.5E-43)
            goto L_0x0d38
        L_0x0395:
            java.lang.String r0 = "ps"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x039f
            goto L_0x0d38
        L_0x039f:
            r1 = 177(0xb1, float:2.48E-43)
            goto L_0x0d38
        L_0x03a3:
            java.lang.String r0 = "pr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03ad
            goto L_0x0d38
        L_0x03ad:
            r1 = 176(0xb0, float:2.47E-43)
            goto L_0x0d38
        L_0x03b1:
            java.lang.String r0 = "pn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03bb
            goto L_0x0d38
        L_0x03bb:
            r1 = 175(0xaf, float:2.45E-43)
            goto L_0x0d38
        L_0x03bf:
            java.lang.String r0 = "pm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03c9
            goto L_0x0d38
        L_0x03c9:
            r1 = 174(0xae, float:2.44E-43)
            goto L_0x0d38
        L_0x03cd:
            java.lang.String r0 = "pl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03d7
            goto L_0x0d38
        L_0x03d7:
            r1 = 173(0xad, float:2.42E-43)
            goto L_0x0d38
        L_0x03db:
            java.lang.String r0 = "pk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03e5
            goto L_0x0d38
        L_0x03e5:
            r1 = 172(0xac, float:2.41E-43)
            goto L_0x0d38
        L_0x03e9:
            java.lang.String r0 = "ph"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03f3
            goto L_0x0d38
        L_0x03f3:
            r1 = 171(0xab, float:2.4E-43)
            goto L_0x0d38
        L_0x03f7:
            java.lang.String r0 = "pg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0401
            goto L_0x0d38
        L_0x0401:
            r1 = 170(0xaa, float:2.38E-43)
            goto L_0x0d38
        L_0x0405:
            java.lang.String r0 = "pf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x040f
            goto L_0x0d38
        L_0x040f:
            r1 = 169(0xa9, float:2.37E-43)
            goto L_0x0d38
        L_0x0413:
            java.lang.String r0 = "pe"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x041d
            goto L_0x0d38
        L_0x041d:
            r1 = 168(0xa8, float:2.35E-43)
            goto L_0x0d38
        L_0x0421:
            java.lang.String r0 = "pa"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x042b
            goto L_0x0d38
        L_0x042b:
            r1 = 167(0xa7, float:2.34E-43)
            goto L_0x0d38
        L_0x042f:
            java.lang.String r0 = "om"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0439
            goto L_0x0d38
        L_0x0439:
            r1 = 166(0xa6, float:2.33E-43)
            goto L_0x0d38
        L_0x043d:
            java.lang.String r0 = "nz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0447
            goto L_0x0d38
        L_0x0447:
            r1 = 165(0xa5, float:2.31E-43)
            goto L_0x0d38
        L_0x044b:
            java.lang.String r0 = "nu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0455
            goto L_0x0d38
        L_0x0455:
            r1 = 164(0xa4, float:2.3E-43)
            goto L_0x0d38
        L_0x0459:
            java.lang.String r0 = "nr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0463
            goto L_0x0d38
        L_0x0463:
            r1 = 163(0xa3, float:2.28E-43)
            goto L_0x0d38
        L_0x0467:
            java.lang.String r0 = "np"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0471
            goto L_0x0d38
        L_0x0471:
            r1 = 162(0xa2, float:2.27E-43)
            goto L_0x0d38
        L_0x0475:
            java.lang.String r0 = "no"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x047f
            goto L_0x0d38
        L_0x047f:
            r1 = 161(0xa1, float:2.26E-43)
            goto L_0x0d38
        L_0x0483:
            java.lang.String r0 = "nl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x048d
            goto L_0x0d38
        L_0x048d:
            r1 = 160(0xa0, float:2.24E-43)
            goto L_0x0d38
        L_0x0491:
            java.lang.String r0 = "ni"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x049b
            goto L_0x0d38
        L_0x049b:
            r1 = 159(0x9f, float:2.23E-43)
            goto L_0x0d38
        L_0x049f:
            java.lang.String r0 = "ng"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04a9
            goto L_0x0d38
        L_0x04a9:
            r1 = 158(0x9e, float:2.21E-43)
            goto L_0x0d38
        L_0x04ad:
            java.lang.String r0 = "nf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04b7
            goto L_0x0d38
        L_0x04b7:
            r1 = 157(0x9d, float:2.2E-43)
            goto L_0x0d38
        L_0x04bb:
            java.lang.String r0 = "ne"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04c5
            goto L_0x0d38
        L_0x04c5:
            r1 = 156(0x9c, float:2.19E-43)
            goto L_0x0d38
        L_0x04c9:
            java.lang.String r0 = "nc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04d3
            goto L_0x0d38
        L_0x04d3:
            r1 = 155(0x9b, float:2.17E-43)
            goto L_0x0d38
        L_0x04d7:
            java.lang.String r0 = "na"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04e1
            goto L_0x0d38
        L_0x04e1:
            r1 = 154(0x9a, float:2.16E-43)
            goto L_0x0d38
        L_0x04e5:
            java.lang.String r0 = "mz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04ef
            goto L_0x0d38
        L_0x04ef:
            r1 = 153(0x99, float:2.14E-43)
            goto L_0x0d38
        L_0x04f3:
            java.lang.String r0 = "my"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04fd
            goto L_0x0d38
        L_0x04fd:
            r1 = 152(0x98, float:2.13E-43)
            goto L_0x0d38
        L_0x0501:
            java.lang.String r0 = "mx"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x050b
            goto L_0x0d38
        L_0x050b:
            r1 = 151(0x97, float:2.12E-43)
            goto L_0x0d38
        L_0x050f:
            java.lang.String r0 = "mw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0519
            goto L_0x0d38
        L_0x0519:
            r1 = 150(0x96, float:2.1E-43)
            goto L_0x0d38
        L_0x051d:
            java.lang.String r0 = "mv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0527
            goto L_0x0d38
        L_0x0527:
            r1 = 149(0x95, float:2.09E-43)
            goto L_0x0d38
        L_0x052b:
            java.lang.String r0 = "mu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0535
            goto L_0x0d38
        L_0x0535:
            r1 = 148(0x94, float:2.07E-43)
            goto L_0x0d38
        L_0x0539:
            java.lang.String r0 = "mt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0543
            goto L_0x0d38
        L_0x0543:
            r1 = 147(0x93, float:2.06E-43)
            goto L_0x0d38
        L_0x0547:
            java.lang.String r0 = "ms"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0551
            goto L_0x0d38
        L_0x0551:
            r1 = 146(0x92, float:2.05E-43)
            goto L_0x0d38
        L_0x0555:
            java.lang.String r0 = "mr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x055f
            goto L_0x0d38
        L_0x055f:
            r1 = 145(0x91, float:2.03E-43)
            goto L_0x0d38
        L_0x0563:
            java.lang.String r0 = "mq"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x056d
            goto L_0x0d38
        L_0x056d:
            r1 = 144(0x90, float:2.02E-43)
            goto L_0x0d38
        L_0x0571:
            java.lang.String r0 = "mp"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x057b
            goto L_0x0d38
        L_0x057b:
            r1 = 143(0x8f, float:2.0E-43)
            goto L_0x0d38
        L_0x057f:
            java.lang.String r0 = "mo"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0589
            goto L_0x0d38
        L_0x0589:
            r1 = 142(0x8e, float:1.99E-43)
            goto L_0x0d38
        L_0x058d:
            java.lang.String r0 = "mn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0597
            goto L_0x0d38
        L_0x0597:
            r1 = 141(0x8d, float:1.98E-43)
            goto L_0x0d38
        L_0x059b:
            java.lang.String r0 = "mm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05a5
            goto L_0x0d38
        L_0x05a5:
            r1 = 140(0x8c, float:1.96E-43)
            goto L_0x0d38
        L_0x05a9:
            java.lang.String r0 = "ml"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05b3
            goto L_0x0d38
        L_0x05b3:
            r1 = 139(0x8b, float:1.95E-43)
            goto L_0x0d38
        L_0x05b7:
            java.lang.String r0 = "mk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05c1
            goto L_0x0d38
        L_0x05c1:
            r1 = 138(0x8a, float:1.93E-43)
            goto L_0x0d38
        L_0x05c5:
            java.lang.String r0 = "mh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05cf
            goto L_0x0d38
        L_0x05cf:
            r1 = 137(0x89, float:1.92E-43)
            goto L_0x0d38
        L_0x05d3:
            java.lang.String r0 = "mg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05dd
            goto L_0x0d38
        L_0x05dd:
            r1 = 136(0x88, float:1.9E-43)
            goto L_0x0d38
        L_0x05e1:
            java.lang.String r0 = "mf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05eb
            goto L_0x0d38
        L_0x05eb:
            r1 = 135(0x87, float:1.89E-43)
            goto L_0x0d38
        L_0x05ef:
            java.lang.String r0 = "me"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05f9
            goto L_0x0d38
        L_0x05f9:
            r1 = 134(0x86, float:1.88E-43)
            goto L_0x0d38
        L_0x05fd:
            java.lang.String r0 = "md"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0607
            goto L_0x0d38
        L_0x0607:
            r1 = 133(0x85, float:1.86E-43)
            goto L_0x0d38
        L_0x060b:
            java.lang.String r0 = "mc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0615
            goto L_0x0d38
        L_0x0615:
            r1 = 132(0x84, float:1.85E-43)
            goto L_0x0d38
        L_0x0619:
            java.lang.String r0 = "ma"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0623
            goto L_0x0d38
        L_0x0623:
            r1 = 131(0x83, float:1.84E-43)
            goto L_0x0d38
        L_0x0627:
            java.lang.String r0 = "ly"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0631
            goto L_0x0d38
        L_0x0631:
            r1 = 130(0x82, float:1.82E-43)
            goto L_0x0d38
        L_0x0635:
            java.lang.String r0 = "lv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x063f
            goto L_0x0d38
        L_0x063f:
            r1 = 129(0x81, float:1.81E-43)
            goto L_0x0d38
        L_0x0643:
            java.lang.String r0 = "lu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x064d
            goto L_0x0d38
        L_0x064d:
            r1 = 128(0x80, float:1.794E-43)
            goto L_0x0d38
        L_0x0651:
            java.lang.String r0 = "lt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x065b
            goto L_0x0d38
        L_0x065b:
            r1 = 127(0x7f, float:1.78E-43)
            goto L_0x0d38
        L_0x065f:
            java.lang.String r0 = "ls"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0669
            goto L_0x0d38
        L_0x0669:
            r1 = 126(0x7e, float:1.77E-43)
            goto L_0x0d38
        L_0x066d:
            java.lang.String r0 = "lr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0677
            goto L_0x0d38
        L_0x0677:
            r1 = 125(0x7d, float:1.75E-43)
            goto L_0x0d38
        L_0x067b:
            java.lang.String r0 = "lk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0685
            goto L_0x0d38
        L_0x0685:
            r1 = 124(0x7c, float:1.74E-43)
            goto L_0x0d38
        L_0x0689:
            java.lang.String r0 = "li"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0693
            goto L_0x0d38
        L_0x0693:
            r1 = 123(0x7b, float:1.72E-43)
            goto L_0x0d38
        L_0x0697:
            java.lang.String r0 = "lc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06a1
            goto L_0x0d38
        L_0x06a1:
            r1 = 122(0x7a, float:1.71E-43)
            goto L_0x0d38
        L_0x06a5:
            java.lang.String r0 = "lb"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06af
            goto L_0x0d38
        L_0x06af:
            r1 = 121(0x79, float:1.7E-43)
            goto L_0x0d38
        L_0x06b3:
            java.lang.String r0 = "la"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06bd
            goto L_0x0d38
        L_0x06bd:
            r1 = 120(0x78, float:1.68E-43)
            goto L_0x0d38
        L_0x06c1:
            java.lang.String r0 = "kz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06cb
            goto L_0x0d38
        L_0x06cb:
            r1 = 119(0x77, float:1.67E-43)
            goto L_0x0d38
        L_0x06cf:
            java.lang.String r0 = "ky"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06d9
            goto L_0x0d38
        L_0x06d9:
            r1 = 118(0x76, float:1.65E-43)
            goto L_0x0d38
        L_0x06dd:
            java.lang.String r0 = "kw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06e7
            goto L_0x0d38
        L_0x06e7:
            r1 = 117(0x75, float:1.64E-43)
            goto L_0x0d38
        L_0x06eb:
            java.lang.String r0 = "kr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06f5
            goto L_0x0d38
        L_0x06f5:
            r1 = 116(0x74, float:1.63E-43)
            goto L_0x0d38
        L_0x06f9:
            java.lang.String r0 = "kp"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0703
            goto L_0x0d38
        L_0x0703:
            r1 = 115(0x73, float:1.61E-43)
            goto L_0x0d38
        L_0x0707:
            java.lang.String r0 = "kn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0711
            goto L_0x0d38
        L_0x0711:
            r1 = 114(0x72, float:1.6E-43)
            goto L_0x0d38
        L_0x0715:
            java.lang.String r0 = "km"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x071f
            goto L_0x0d38
        L_0x071f:
            r1 = 113(0x71, float:1.58E-43)
            goto L_0x0d38
        L_0x0723:
            java.lang.String r0 = "ki"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x072d
            goto L_0x0d38
        L_0x072d:
            r1 = 112(0x70, float:1.57E-43)
            goto L_0x0d38
        L_0x0731:
            java.lang.String r0 = "kh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x073b
            goto L_0x0d38
        L_0x073b:
            r1 = 111(0x6f, float:1.56E-43)
            goto L_0x0d38
        L_0x073f:
            java.lang.String r0 = "kg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0749
            goto L_0x0d38
        L_0x0749:
            r1 = 110(0x6e, float:1.54E-43)
            goto L_0x0d38
        L_0x074d:
            java.lang.String r0 = "ke"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0757
            goto L_0x0d38
        L_0x0757:
            r1 = 109(0x6d, float:1.53E-43)
            goto L_0x0d38
        L_0x075b:
            java.lang.String r0 = "jp"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0765
            goto L_0x0d38
        L_0x0765:
            r1 = 108(0x6c, float:1.51E-43)
            goto L_0x0d38
        L_0x0769:
            java.lang.String r0 = "jo"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0773
            goto L_0x0d38
        L_0x0773:
            r1 = 107(0x6b, float:1.5E-43)
            goto L_0x0d38
        L_0x0777:
            java.lang.String r0 = "jm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0781
            goto L_0x0d38
        L_0x0781:
            r1 = 106(0x6a, float:1.49E-43)
            goto L_0x0d38
        L_0x0785:
            java.lang.String r0 = "je"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x078f
            goto L_0x0d38
        L_0x078f:
            r1 = 105(0x69, float:1.47E-43)
            goto L_0x0d38
        L_0x0793:
            java.lang.String r0 = "it"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x079d
            goto L_0x0d38
        L_0x079d:
            r1 = 104(0x68, float:1.46E-43)
            goto L_0x0d38
        L_0x07a1:
            java.lang.String r0 = "is"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07ab
            goto L_0x0d38
        L_0x07ab:
            r1 = 103(0x67, float:1.44E-43)
            goto L_0x0d38
        L_0x07af:
            java.lang.String r0 = "ir"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07b9
            goto L_0x0d38
        L_0x07b9:
            r1 = 102(0x66, float:1.43E-43)
            goto L_0x0d38
        L_0x07bd:
            java.lang.String r0 = "iq"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07c7
            goto L_0x0d38
        L_0x07c7:
            r1 = 101(0x65, float:1.42E-43)
            goto L_0x0d38
        L_0x07cb:
            java.lang.String r0 = "io"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07d5
            goto L_0x0d38
        L_0x07d5:
            r1 = 100
            goto L_0x0d38
        L_0x07d9:
            java.lang.String r0 = "in"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07e3
            goto L_0x0d38
        L_0x07e3:
            r1 = 99
            goto L_0x0d38
        L_0x07e7:
            java.lang.String r0 = "im"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07f1
            goto L_0x0d38
        L_0x07f1:
            r1 = 98
            goto L_0x0d38
        L_0x07f5:
            java.lang.String r0 = "il"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07ff
            goto L_0x0d38
        L_0x07ff:
            r1 = 97
            goto L_0x0d38
        L_0x0803:
            java.lang.String r0 = "ie"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x080d
            goto L_0x0d38
        L_0x080d:
            r1 = 96
            goto L_0x0d38
        L_0x0811:
            java.lang.String r0 = "id"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x081b
            goto L_0x0d38
        L_0x081b:
            r1 = 95
            goto L_0x0d38
        L_0x081f:
            java.lang.String r0 = "hu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0829
            goto L_0x0d38
        L_0x0829:
            r1 = 94
            goto L_0x0d38
        L_0x082d:
            java.lang.String r0 = "ht"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0837
            goto L_0x0d38
        L_0x0837:
            r1 = 93
            goto L_0x0d38
        L_0x083b:
            java.lang.String r0 = "hr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0845
            goto L_0x0d38
        L_0x0845:
            r1 = 92
            goto L_0x0d38
        L_0x0849:
            java.lang.String r0 = "hn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0853
            goto L_0x0d38
        L_0x0853:
            r1 = 91
            goto L_0x0d38
        L_0x0857:
            java.lang.String r0 = "hk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0861
            goto L_0x0d38
        L_0x0861:
            r1 = 90
            goto L_0x0d38
        L_0x0865:
            java.lang.String r0 = "gy"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x086f
            goto L_0x0d38
        L_0x086f:
            r1 = 89
            goto L_0x0d38
        L_0x0873:
            java.lang.String r0 = "gw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x087d
            goto L_0x0d38
        L_0x087d:
            r1 = 88
            goto L_0x0d38
        L_0x0881:
            java.lang.String r0 = "gu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x088b
            goto L_0x0d38
        L_0x088b:
            r1 = 87
            goto L_0x0d38
        L_0x088f:
            java.lang.String r0 = "gt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0899
            goto L_0x0d38
        L_0x0899:
            r1 = 86
            goto L_0x0d38
        L_0x089d:
            java.lang.String r0 = "gr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08a7
            goto L_0x0d38
        L_0x08a7:
            r1 = 85
            goto L_0x0d38
        L_0x08ab:
            java.lang.String r0 = "gq"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08b5
            goto L_0x0d38
        L_0x08b5:
            r1 = 84
            goto L_0x0d38
        L_0x08b9:
            java.lang.String r0 = "gp"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08c3
            goto L_0x0d38
        L_0x08c3:
            r1 = 83
            goto L_0x0d38
        L_0x08c7:
            java.lang.String r0 = "gn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08d1
            goto L_0x0d38
        L_0x08d1:
            r1 = 82
            goto L_0x0d38
        L_0x08d5:
            java.lang.String r0 = "gm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08df
            goto L_0x0d38
        L_0x08df:
            r1 = 81
            goto L_0x0d38
        L_0x08e3:
            java.lang.String r0 = "gl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08ed
            goto L_0x0d38
        L_0x08ed:
            r1 = 80
            goto L_0x0d38
        L_0x08f1:
            java.lang.String r0 = "gi"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08fb
            goto L_0x0d38
        L_0x08fb:
            r1 = 79
            goto L_0x0d38
        L_0x08ff:
            java.lang.String r0 = "gh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0909
            goto L_0x0d38
        L_0x0909:
            r1 = 78
            goto L_0x0d38
        L_0x090d:
            java.lang.String r0 = "gg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0917
            goto L_0x0d38
        L_0x0917:
            r1 = 77
            goto L_0x0d38
        L_0x091b:
            java.lang.String r0 = "gf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0925
            goto L_0x0d38
        L_0x0925:
            r1 = 76
            goto L_0x0d38
        L_0x0929:
            java.lang.String r0 = "ge"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0933
            goto L_0x0d38
        L_0x0933:
            r1 = 75
            goto L_0x0d38
        L_0x0937:
            java.lang.String r0 = "gd"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0941
            goto L_0x0d38
        L_0x0941:
            r1 = 74
            goto L_0x0d38
        L_0x0945:
            java.lang.String r0 = "gb"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x094f
            goto L_0x0d38
        L_0x094f:
            r1 = 73
            goto L_0x0d38
        L_0x0953:
            java.lang.String r0 = "ga"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x095d
            goto L_0x0d38
        L_0x095d:
            r1 = 72
            goto L_0x0d38
        L_0x0961:
            java.lang.String r0 = "fr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x096b
            goto L_0x0d38
        L_0x096b:
            r1 = 71
            goto L_0x0d38
        L_0x096f:
            java.lang.String r0 = "fo"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0979
            goto L_0x0d38
        L_0x0979:
            r1 = 70
            goto L_0x0d38
        L_0x097d:
            java.lang.String r0 = "fm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0987
            goto L_0x0d38
        L_0x0987:
            r1 = 69
            goto L_0x0d38
        L_0x098b:
            java.lang.String r0 = "fk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0995
            goto L_0x0d38
        L_0x0995:
            r1 = 68
            goto L_0x0d38
        L_0x0999:
            java.lang.String r0 = "fj"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09a3
            goto L_0x0d38
        L_0x09a3:
            r1 = 67
            goto L_0x0d38
        L_0x09a7:
            java.lang.String r0 = "fi"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09b1
            goto L_0x0d38
        L_0x09b1:
            r1 = 66
            goto L_0x0d38
        L_0x09b5:
            java.lang.String r0 = "et"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09bf
            goto L_0x0d38
        L_0x09bf:
            r1 = 65
            goto L_0x0d38
        L_0x09c3:
            java.lang.String r0 = "es"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09cd
            goto L_0x0d38
        L_0x09cd:
            r1 = 64
            goto L_0x0d38
        L_0x09d1:
            java.lang.String r0 = "er"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09db
            goto L_0x0d38
        L_0x09db:
            r1 = 63
            goto L_0x0d38
        L_0x09df:
            java.lang.String r0 = "eg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09e9
            goto L_0x0d38
        L_0x09e9:
            r1 = 62
            goto L_0x0d38
        L_0x09ed:
            java.lang.String r0 = "ee"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09f7
            goto L_0x0d38
        L_0x09f7:
            r1 = 61
            goto L_0x0d38
        L_0x09fb:
            java.lang.String r0 = "ec"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a05
            goto L_0x0d38
        L_0x0a05:
            r1 = 60
            goto L_0x0d38
        L_0x0a09:
            java.lang.String r0 = "dz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a13
            goto L_0x0d38
        L_0x0a13:
            r1 = 59
            goto L_0x0d38
        L_0x0a17:
            java.lang.String r0 = "do"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a21
            goto L_0x0d38
        L_0x0a21:
            r1 = 58
            goto L_0x0d38
        L_0x0a25:
            java.lang.String r0 = "dm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a2f
            goto L_0x0d38
        L_0x0a2f:
            r1 = 57
            goto L_0x0d38
        L_0x0a33:
            java.lang.String r0 = "dk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a3d
            goto L_0x0d38
        L_0x0a3d:
            r1 = 56
            goto L_0x0d38
        L_0x0a41:
            java.lang.String r0 = "dj"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a4b
            goto L_0x0d38
        L_0x0a4b:
            r1 = 55
            goto L_0x0d38
        L_0x0a4f:
            java.lang.String r0 = "de"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a59
            goto L_0x0d38
        L_0x0a59:
            r1 = 54
            goto L_0x0d38
        L_0x0a5d:
            java.lang.String r0 = "cz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a67
            goto L_0x0d38
        L_0x0a67:
            r1 = 53
            goto L_0x0d38
        L_0x0a6b:
            java.lang.String r0 = "cy"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a75
            goto L_0x0d38
        L_0x0a75:
            r1 = 52
            goto L_0x0d38
        L_0x0a79:
            java.lang.String r0 = "cx"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a83
            goto L_0x0d38
        L_0x0a83:
            r1 = 51
            goto L_0x0d38
        L_0x0a87:
            java.lang.String r0 = "cw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a91
            goto L_0x0d38
        L_0x0a91:
            r1 = 50
            goto L_0x0d38
        L_0x0a95:
            java.lang.String r0 = "cv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a9f
            goto L_0x0d38
        L_0x0a9f:
            r1 = 49
            goto L_0x0d38
        L_0x0aa3:
            java.lang.String r0 = "cu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0aad
            goto L_0x0d38
        L_0x0aad:
            r1 = 48
            goto L_0x0d38
        L_0x0ab1:
            java.lang.String r0 = "cr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0abb
            goto L_0x0d38
        L_0x0abb:
            r1 = 47
            goto L_0x0d38
        L_0x0abf:
            java.lang.String r0 = "co"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ac9
            goto L_0x0d38
        L_0x0ac9:
            r1 = 46
            goto L_0x0d38
        L_0x0acd:
            java.lang.String r0 = "cn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ad7
            goto L_0x0d38
        L_0x0ad7:
            r1 = 45
            goto L_0x0d38
        L_0x0adb:
            java.lang.String r0 = "cm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ae5
            goto L_0x0d38
        L_0x0ae5:
            r1 = 44
            goto L_0x0d38
        L_0x0ae9:
            java.lang.String r0 = "cl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0af3
            goto L_0x0d38
        L_0x0af3:
            r1 = 43
            goto L_0x0d38
        L_0x0af7:
            java.lang.String r0 = "ck"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b01
            goto L_0x0d38
        L_0x0b01:
            r1 = 42
            goto L_0x0d38
        L_0x0b05:
            java.lang.String r0 = "ci"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b0f
            goto L_0x0d38
        L_0x0b0f:
            r1 = 41
            goto L_0x0d38
        L_0x0b13:
            java.lang.String r0 = "ch"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b1d
            goto L_0x0d38
        L_0x0b1d:
            r1 = 40
            goto L_0x0d38
        L_0x0b21:
            java.lang.String r0 = "cg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b2b
            goto L_0x0d38
        L_0x0b2b:
            r1 = 39
            goto L_0x0d38
        L_0x0b2f:
            java.lang.String r0 = "cf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b39
            goto L_0x0d38
        L_0x0b39:
            r1 = 38
            goto L_0x0d38
        L_0x0b3d:
            java.lang.String r0 = "cd"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b47
            goto L_0x0d38
        L_0x0b47:
            r1 = 37
            goto L_0x0d38
        L_0x0b4b:
            java.lang.String r0 = "cc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b55
            goto L_0x0d38
        L_0x0b55:
            r1 = 36
            goto L_0x0d38
        L_0x0b59:
            java.lang.String r0 = "ca"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b63
            goto L_0x0d38
        L_0x0b63:
            r1 = 35
            goto L_0x0d38
        L_0x0b67:
            java.lang.String r0 = "bz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b71
            goto L_0x0d38
        L_0x0b71:
            r1 = 34
            goto L_0x0d38
        L_0x0b75:
            java.lang.String r0 = "by"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b7f
            goto L_0x0d38
        L_0x0b7f:
            r1 = 33
            goto L_0x0d38
        L_0x0b83:
            java.lang.String r0 = "bw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b8d
            goto L_0x0d38
        L_0x0b8d:
            r1 = 32
            goto L_0x0d38
        L_0x0b91:
            java.lang.String r0 = "bt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b9b
            goto L_0x0d38
        L_0x0b9b:
            r1 = 31
            goto L_0x0d38
        L_0x0b9f:
            java.lang.String r0 = "bs"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ba9
            goto L_0x0d38
        L_0x0ba9:
            r1 = 30
            goto L_0x0d38
        L_0x0bad:
            java.lang.String r0 = "br"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0bb7
            goto L_0x0d38
        L_0x0bb7:
            r1 = 29
            goto L_0x0d38
        L_0x0bbb:
            java.lang.String r0 = "bo"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0bc5
            goto L_0x0d38
        L_0x0bc5:
            r1 = 28
            goto L_0x0d38
        L_0x0bc9:
            java.lang.String r0 = "bn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0bd3
            goto L_0x0d38
        L_0x0bd3:
            r1 = 27
            goto L_0x0d38
        L_0x0bd7:
            java.lang.String r0 = "bm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0be1
            goto L_0x0d38
        L_0x0be1:
            r1 = 26
            goto L_0x0d38
        L_0x0be5:
            java.lang.String r0 = "bl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0bef
            goto L_0x0d38
        L_0x0bef:
            r1 = 25
            goto L_0x0d38
        L_0x0bf3:
            java.lang.String r0 = "bj"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0bfd
            goto L_0x0d38
        L_0x0bfd:
            r1 = 24
            goto L_0x0d38
        L_0x0c01:
            java.lang.String r0 = "bi"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c0b
            goto L_0x0d38
        L_0x0c0b:
            r1 = 23
            goto L_0x0d38
        L_0x0c0f:
            java.lang.String r0 = "bh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c19
            goto L_0x0d38
        L_0x0c19:
            r1 = 22
            goto L_0x0d38
        L_0x0c1d:
            java.lang.String r0 = "bg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c27
            goto L_0x0d38
        L_0x0c27:
            r1 = 21
            goto L_0x0d38
        L_0x0c2b:
            java.lang.String r0 = "bf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c35
            goto L_0x0d38
        L_0x0c35:
            r1 = 20
            goto L_0x0d38
        L_0x0c39:
            java.lang.String r0 = "be"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c43
            goto L_0x0d38
        L_0x0c43:
            r1 = 19
            goto L_0x0d38
        L_0x0c47:
            java.lang.String r0 = "bd"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c51
            goto L_0x0d38
        L_0x0c51:
            r1 = 18
            goto L_0x0d38
        L_0x0c55:
            java.lang.String r0 = "bb"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c5f
            goto L_0x0d38
        L_0x0c5f:
            r1 = 17
            goto L_0x0d38
        L_0x0c63:
            java.lang.String r0 = "ba"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c6d
            goto L_0x0d38
        L_0x0c6d:
            r1 = 16
            goto L_0x0d38
        L_0x0c71:
            java.lang.String r0 = "az"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c7b
            goto L_0x0d38
        L_0x0c7b:
            r1 = 15
            goto L_0x0d38
        L_0x0c7f:
            java.lang.String r0 = "ax"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c89
            goto L_0x0d38
        L_0x0c89:
            r1 = 14
            goto L_0x0d38
        L_0x0c8d:
            java.lang.String r0 = "aw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c97
            goto L_0x0d38
        L_0x0c97:
            r1 = 13
            goto L_0x0d38
        L_0x0c9b:
            java.lang.String r0 = "au"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ca5
            goto L_0x0d38
        L_0x0ca5:
            r1 = 12
            goto L_0x0d38
        L_0x0ca9:
            java.lang.String r0 = "at"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0cb3
            goto L_0x0d38
        L_0x0cb3:
            r1 = 11
            goto L_0x0d38
        L_0x0cb7:
            java.lang.String r0 = "as"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0cc1
            goto L_0x0d38
        L_0x0cc1:
            r1 = 10
            goto L_0x0d38
        L_0x0cc5:
            java.lang.String r0 = "ar"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ccf
            goto L_0x0d38
        L_0x0ccf:
            r1 = 9
            goto L_0x0d38
        L_0x0cd3:
            java.lang.String r0 = "aq"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0cdd
            goto L_0x0d38
        L_0x0cdd:
            r1 = 8
            goto L_0x0d38
        L_0x0ce1:
            java.lang.String r0 = "ao"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0cea
            goto L_0x0d38
        L_0x0cea:
            r1 = 7
            goto L_0x0d38
        L_0x0cec:
            java.lang.String r0 = "am"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0cf5
            goto L_0x0d38
        L_0x0cf5:
            r1 = 6
            goto L_0x0d38
        L_0x0cf7:
            java.lang.String r0 = "al"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d00
            goto L_0x0d38
        L_0x0d00:
            r1 = 5
            goto L_0x0d38
        L_0x0d02:
            java.lang.String r0 = "ai"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d0b
            goto L_0x0d38
        L_0x0d0b:
            r1 = 4
            goto L_0x0d38
        L_0x0d0d:
            java.lang.String r0 = "ag"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d16
            goto L_0x0d38
        L_0x0d16:
            r1 = 3
            goto L_0x0d38
        L_0x0d18:
            java.lang.String r0 = "af"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d21
            goto L_0x0d38
        L_0x0d21:
            r1 = 2
            goto L_0x0d38
        L_0x0d23:
            java.lang.String r0 = "ae"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d2c
            goto L_0x0d38
        L_0x0d2c:
            r1 = 1
            goto L_0x0d38
        L_0x0d2e:
            java.lang.String r0 = "ad"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d37
            goto L_0x0d38
        L_0x0d37:
            r1 = 0
        L_0x0d38:
            switch(r1) {
                case 0: goto L_0x1011;
                case 1: goto L_0x100e;
                case 2: goto L_0x100b;
                case 3: goto L_0x1008;
                case 4: goto L_0x1005;
                case 5: goto L_0x1002;
                case 6: goto L_0x0fff;
                case 7: goto L_0x0ffc;
                case 8: goto L_0x0ff9;
                case 9: goto L_0x0ff6;
                case 10: goto L_0x0ff3;
                case 11: goto L_0x0ff0;
                case 12: goto L_0x0fed;
                case 13: goto L_0x0fea;
                case 14: goto L_0x0fe7;
                case 15: goto L_0x0fe4;
                case 16: goto L_0x0fe1;
                case 17: goto L_0x0fde;
                case 18: goto L_0x0fdb;
                case 19: goto L_0x0fd8;
                case 20: goto L_0x0fd5;
                case 21: goto L_0x0fd2;
                case 22: goto L_0x0fcf;
                case 23: goto L_0x0fcc;
                case 24: goto L_0x0fc9;
                case 25: goto L_0x0fc6;
                case 26: goto L_0x0fc3;
                case 27: goto L_0x0fc0;
                case 28: goto L_0x0fbd;
                case 29: goto L_0x0fba;
                case 30: goto L_0x0fb7;
                case 31: goto L_0x0fb4;
                case 32: goto L_0x0fb1;
                case 33: goto L_0x0fae;
                case 34: goto L_0x0fab;
                case 35: goto L_0x0fa8;
                case 36: goto L_0x0fa5;
                case 37: goto L_0x0fa2;
                case 38: goto L_0x0f9f;
                case 39: goto L_0x0f9c;
                case 40: goto L_0x0f99;
                case 41: goto L_0x0f96;
                case 42: goto L_0x0f93;
                case 43: goto L_0x0f90;
                case 44: goto L_0x0f8d;
                case 45: goto L_0x0f8a;
                case 46: goto L_0x0f87;
                case 47: goto L_0x0f84;
                case 48: goto L_0x0f81;
                case 49: goto L_0x0f7e;
                case 50: goto L_0x0f7b;
                case 51: goto L_0x0f78;
                case 52: goto L_0x0f75;
                case 53: goto L_0x0f72;
                case 54: goto L_0x0f6f;
                case 55: goto L_0x0f6c;
                case 56: goto L_0x0f69;
                case 57: goto L_0x0f66;
                case 58: goto L_0x0f63;
                case 59: goto L_0x0f60;
                case 60: goto L_0x0f5d;
                case 61: goto L_0x0f5a;
                case 62: goto L_0x0f57;
                case 63: goto L_0x0f54;
                case 64: goto L_0x0f51;
                case 65: goto L_0x0f4e;
                case 66: goto L_0x0f4b;
                case 67: goto L_0x0f48;
                case 68: goto L_0x0f45;
                case 69: goto L_0x0f42;
                case 70: goto L_0x0f3f;
                case 71: goto L_0x0f3c;
                case 72: goto L_0x0f39;
                case 73: goto L_0x0f36;
                case 74: goto L_0x0f33;
                case 75: goto L_0x0f30;
                case 76: goto L_0x0f2d;
                case 77: goto L_0x0f2a;
                case 78: goto L_0x0f27;
                case 79: goto L_0x0f24;
                case 80: goto L_0x0f21;
                case 81: goto L_0x0f1e;
                case 82: goto L_0x0f1b;
                case 83: goto L_0x0f18;
                case 84: goto L_0x0f15;
                case 85: goto L_0x0f12;
                case 86: goto L_0x0f0f;
                case 87: goto L_0x0f0c;
                case 88: goto L_0x0f09;
                case 89: goto L_0x0f06;
                case 90: goto L_0x0f03;
                case 91: goto L_0x0f00;
                case 92: goto L_0x0efd;
                case 93: goto L_0x0efa;
                case 94: goto L_0x0ef7;
                case 95: goto L_0x0ef4;
                case 96: goto L_0x0ef1;
                case 97: goto L_0x0eee;
                case 98: goto L_0x0eeb;
                case 99: goto L_0x0ee8;
                case 100: goto L_0x0ee5;
                case 101: goto L_0x0ee2;
                case 102: goto L_0x0edf;
                case 103: goto L_0x0edc;
                case 104: goto L_0x0ed9;
                case 105: goto L_0x0ed6;
                case 106: goto L_0x0ed3;
                case 107: goto L_0x0ed0;
                case 108: goto L_0x0ecd;
                case 109: goto L_0x0eca;
                case 110: goto L_0x0ec7;
                case 111: goto L_0x0ec4;
                case 112: goto L_0x0ec1;
                case 113: goto L_0x0ebe;
                case 114: goto L_0x0ebb;
                case 115: goto L_0x0eb8;
                case 116: goto L_0x0eb5;
                case 117: goto L_0x0eb2;
                case 118: goto L_0x0eaf;
                case 119: goto L_0x0eac;
                case 120: goto L_0x0ea9;
                case 121: goto L_0x0ea6;
                case 122: goto L_0x0ea3;
                case 123: goto L_0x0ea0;
                case 124: goto L_0x0e9d;
                case 125: goto L_0x0e9a;
                case 126: goto L_0x0e97;
                case 127: goto L_0x0e94;
                case 128: goto L_0x0e91;
                case 129: goto L_0x0e8e;
                case 130: goto L_0x0e8b;
                case 131: goto L_0x0e88;
                case 132: goto L_0x0e85;
                case 133: goto L_0x0e82;
                case 134: goto L_0x0e7f;
                case 135: goto L_0x0e7c;
                case 136: goto L_0x0e79;
                case 137: goto L_0x0e76;
                case 138: goto L_0x0e73;
                case 139: goto L_0x0e70;
                case 140: goto L_0x0e6d;
                case 141: goto L_0x0e6a;
                case 142: goto L_0x0e67;
                case 143: goto L_0x0e64;
                case 144: goto L_0x0e61;
                case 145: goto L_0x0e5e;
                case 146: goto L_0x0e5b;
                case 147: goto L_0x0e58;
                case 148: goto L_0x0e55;
                case 149: goto L_0x0e52;
                case 150: goto L_0x0e4f;
                case 151: goto L_0x0e4c;
                case 152: goto L_0x0e49;
                case 153: goto L_0x0e46;
                case 154: goto L_0x0e43;
                case 155: goto L_0x0e40;
                case 156: goto L_0x0e3d;
                case 157: goto L_0x0e3a;
                case 158: goto L_0x0e37;
                case 159: goto L_0x0e34;
                case 160: goto L_0x0e31;
                case 161: goto L_0x0e2e;
                case 162: goto L_0x0e2b;
                case 163: goto L_0x0e28;
                case 164: goto L_0x0e25;
                case 165: goto L_0x0e22;
                case 166: goto L_0x0e1f;
                case 167: goto L_0x0e1c;
                case 168: goto L_0x0e19;
                case 169: goto L_0x0e16;
                case 170: goto L_0x0e13;
                case 171: goto L_0x0e10;
                case 172: goto L_0x0e0d;
                case 173: goto L_0x0e0a;
                case 174: goto L_0x0e07;
                case 175: goto L_0x0e04;
                case 176: goto L_0x0e01;
                case 177: goto L_0x0dfe;
                case 178: goto L_0x0dfb;
                case 179: goto L_0x0df8;
                case 180: goto L_0x0df5;
                case 181: goto L_0x0df2;
                case 182: goto L_0x0def;
                case 183: goto L_0x0dec;
                case 184: goto L_0x0de9;
                case 185: goto L_0x0de6;
                case 186: goto L_0x0de3;
                case 187: goto L_0x0de0;
                case 188: goto L_0x0ddd;
                case 189: goto L_0x0dda;
                case 190: goto L_0x0dd7;
                case 191: goto L_0x0dd4;
                case 192: goto L_0x0dd1;
                case 193: goto L_0x0dce;
                case 194: goto L_0x0dcb;
                case 195: goto L_0x0dc8;
                case 196: goto L_0x0dc5;
                case 197: goto L_0x0dc2;
                case 198: goto L_0x0dbf;
                case 199: goto L_0x0dbc;
                case 200: goto L_0x0db9;
                case 201: goto L_0x0db6;
                case 202: goto L_0x0db3;
                case 203: goto L_0x0db0;
                case 204: goto L_0x0dad;
                case 205: goto L_0x0daa;
                case 206: goto L_0x0da7;
                case 207: goto L_0x0da4;
                case 208: goto L_0x0da1;
                case 209: goto L_0x0d9e;
                case 210: goto L_0x0d9b;
                case 211: goto L_0x0d98;
                case 212: goto L_0x0d95;
                case 213: goto L_0x0d92;
                case 214: goto L_0x0d8f;
                case 215: goto L_0x0d8c;
                case 216: goto L_0x0d89;
                case 217: goto L_0x0d86;
                case 218: goto L_0x0d83;
                case 219: goto L_0x0d80;
                case 220: goto L_0x0d7d;
                case 221: goto L_0x0d7a;
                case 222: goto L_0x0d77;
                case 223: goto L_0x0d74;
                case 224: goto L_0x0d71;
                case 225: goto L_0x0d6e;
                case 226: goto L_0x0d6b;
                case 227: goto L_0x0d68;
                case 228: goto L_0x0d65;
                case 229: goto L_0x0d62;
                case 230: goto L_0x0d5f;
                case 231: goto L_0x0d5c;
                case 232: goto L_0x0d59;
                case 233: goto L_0x0d56;
                case 234: goto L_0x0d53;
                case 235: goto L_0x0d50;
                case 236: goto L_0x0d4d;
                case 237: goto L_0x0d4a;
                case 238: goto L_0x0d47;
                case 239: goto L_0x0d44;
                case 240: goto L_0x0d41;
                case 241: goto L_0x0d3e;
                default: goto L_0x0d3b;
            }
        L_0x0d3b:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_transparent
            return r2
        L_0x0d3e:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_zimbabwe
            return r2
        L_0x0d41:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_zambia
            return r2
        L_0x0d44:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_south_africa
            return r2
        L_0x0d47:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_martinique
            return r2
        L_0x0d4a:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_yemen
            return r2
        L_0x0d4d:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_kosovo
            return r2
        L_0x0d50:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_samoa
            return r2
        L_0x0d53:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_wallis_and_futuna
            return r2
        L_0x0d56:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_vanuatu
            return r2
        L_0x0d59:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_vietnam
            return r2
        L_0x0d5c:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_us_virgin_islands
            return r2
        L_0x0d5f:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_british_virgin_islands
            return r2
        L_0x0d62:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_venezuela
            return r2
        L_0x0d65:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_saint_vicent_and_the_grenadines
            return r2
        L_0x0d68:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_vatican_city
            return r2
        L_0x0d6b:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_uzbekistan
            return r2
        L_0x0d6e:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_uruguay
            return r2
        L_0x0d71:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_united_states_of_america
            return r2
        L_0x0d74:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_uganda
            return r2
        L_0x0d77:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_ukraine
            return r2
        L_0x0d7a:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_tanzania
            return r2
        L_0x0d7d:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_taiwan
            return r2
        L_0x0d80:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_tuvalu
            return r2
        L_0x0d83:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_trinidad_and_tobago
            return r2
        L_0x0d86:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_turkey
            return r2
        L_0x0d89:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_tonga
            return r2
        L_0x0d8c:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_tunisia
            return r2
        L_0x0d8f:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_turkmenistan
            return r2
        L_0x0d92:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_timor_leste
            return r2
        L_0x0d95:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_tokelau
            return r2
        L_0x0d98:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_tajikistan
            return r2
        L_0x0d9b:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_thailand
            return r2
        L_0x0d9e:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_togo
            return r2
        L_0x0da1:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_chad
            return r2
        L_0x0da4:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_turks_and_caicos_islands
            return r2
        L_0x0da7:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_swaziland
            return r2
        L_0x0daa:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_syria
            return r2
        L_0x0dad:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_sint_maarten
            return r2
        L_0x0db0:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_el_salvador
            return r2
        L_0x0db3:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_sao_tome_and_principe
            return r2
        L_0x0db6:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_south_sudan
            return r2
        L_0x0db9:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_suriname
            return r2
        L_0x0dbc:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_somalia
            return r2
        L_0x0dbf:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_senegal
            return r2
        L_0x0dc2:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_san_marino
            return r2
        L_0x0dc5:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_sierra_leone
            return r2
        L_0x0dc8:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_slovakia
            return r2
        L_0x0dcb:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_slovenia
            return r2
        L_0x0dce:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_saint_helena
            return r2
        L_0x0dd1:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_singapore
            return r2
        L_0x0dd4:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_sweden
            return r2
        L_0x0dd7:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_sudan
            return r2
        L_0x0dda:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_seychelles
            return r2
        L_0x0ddd:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_soloman_islands
            return r2
        L_0x0de0:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_saudi_arabia
            return r2
        L_0x0de3:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_rwanda
            return r2
        L_0x0de6:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_russian_federation
            return r2
        L_0x0de9:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_serbia
            return r2
        L_0x0dec:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_romania
            return r2
        L_0x0def:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_martinique
            return r2
        L_0x0df2:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_qatar
            return r2
        L_0x0df5:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_paraguay
            return r2
        L_0x0df8:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_palau
            return r2
        L_0x0dfb:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_portugal
            return r2
        L_0x0dfe:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_palestine
            return r2
        L_0x0e01:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_puerto_rico
            return r2
        L_0x0e04:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_pitcairn_islands
            return r2
        L_0x0e07:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_saint_pierre
            return r2
        L_0x0e0a:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_poland
            return r2
        L_0x0e0d:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_pakistan
            return r2
        L_0x0e10:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_philippines
            return r2
        L_0x0e13:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_papua_new_guinea
            return r2
        L_0x0e16:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_french_polynesia
            return r2
        L_0x0e19:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_peru
            return r2
        L_0x0e1c:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_panama
            return r2
        L_0x0e1f:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_oman
            return r2
        L_0x0e22:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_new_zealand
            return r2
        L_0x0e25:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_niue
            return r2
        L_0x0e28:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_nauru
            return r2
        L_0x0e2b:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_nepal
            return r2
        L_0x0e2e:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_norway
            return r2
        L_0x0e31:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_netherlands
            return r2
        L_0x0e34:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_nicaragua
            return r2
        L_0x0e37:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_nigeria
            return r2
        L_0x0e3a:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_norfolk_island
            return r2
        L_0x0e3d:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_niger
            return r2
        L_0x0e40:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_new_caledonia
            return r2
        L_0x0e43:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_namibia
            return r2
        L_0x0e46:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_mozambique
            return r2
        L_0x0e49:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_malaysia
            return r2
        L_0x0e4c:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_mexico
            return r2
        L_0x0e4f:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_malawi
            return r2
        L_0x0e52:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_maldives
            return r2
        L_0x0e55:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_mauritius
            return r2
        L_0x0e58:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_malta
            return r2
        L_0x0e5b:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_montserrat
            return r2
        L_0x0e5e:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_mauritania
            return r2
        L_0x0e61:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_martinique
            return r2
        L_0x0e64:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_northern_mariana_islands
            return r2
        L_0x0e67:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_macao
            return r2
        L_0x0e6a:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_mongolia
            return r2
        L_0x0e6d:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_myanmar
            return r2
        L_0x0e70:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_mali
            return r2
        L_0x0e73:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_macedonia
            return r2
        L_0x0e76:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_marshall_islands
            return r2
        L_0x0e79:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_madagascar
            return r2
        L_0x0e7c:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_saint_martin
            return r2
        L_0x0e7f:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_of_montenegro
            return r2
        L_0x0e82:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_moldova
            return r2
        L_0x0e85:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_monaco
            return r2
        L_0x0e88:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_morocco
            return r2
        L_0x0e8b:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_libya
            return r2
        L_0x0e8e:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_latvia
            return r2
        L_0x0e91:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_luxembourg
            return r2
        L_0x0e94:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_lithuania
            return r2
        L_0x0e97:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_lesotho
            return r2
        L_0x0e9a:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_liberia
            return r2
        L_0x0e9d:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_sri_lanka
            return r2
        L_0x0ea0:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_liechtenstein
            return r2
        L_0x0ea3:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_saint_lucia
            return r2
        L_0x0ea6:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_lebanon
            return r2
        L_0x0ea9:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_laos
            return r2
        L_0x0eac:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_kazakhstan
            return r2
        L_0x0eaf:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_cayman_islands
            return r2
        L_0x0eb2:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_kuwait
            return r2
        L_0x0eb5:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_south_korea
            return r2
        L_0x0eb8:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_north_korea
            return r2
        L_0x0ebb:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_saint_kitts_and_nevis
            return r2
        L_0x0ebe:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_comoros
            return r2
        L_0x0ec1:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_kiribati
            return r2
        L_0x0ec4:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_cambodia
            return r2
        L_0x0ec7:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_kyrgyzstan
            return r2
        L_0x0eca:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_kenya
            return r2
        L_0x0ecd:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_japan
            return r2
        L_0x0ed0:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_jordan
            return r2
        L_0x0ed3:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_jamaica
            return r2
        L_0x0ed6:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_jersey
            return r2
        L_0x0ed9:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_italy
            return r2
        L_0x0edc:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_iceland
            return r2
        L_0x0edf:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_iran
            return r2
        L_0x0ee2:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_iraq_new
            return r2
        L_0x0ee5:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_british_indian_ocean_territory
            return r2
        L_0x0ee8:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_india
            return r2
        L_0x0eeb:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_isleof_man
            return r2
        L_0x0eee:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_israel
            return r2
        L_0x0ef1:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_ireland
            return r2
        L_0x0ef4:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_indonesia
            return r2
        L_0x0ef7:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_hungary
            return r2
        L_0x0efa:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_haiti
            return r2
        L_0x0efd:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_croatia
            return r2
        L_0x0f00:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_honduras
            return r2
        L_0x0f03:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_hong_kong
            return r2
        L_0x0f06:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_guyana
            return r2
        L_0x0f09:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_guinea_bissau
            return r2
        L_0x0f0c:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_guam
            return r2
        L_0x0f0f:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_guatemala
            return r2
        L_0x0f12:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_greece
            return r2
        L_0x0f15:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_equatorial_guinea
            return r2
        L_0x0f18:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_guadeloupe
            return r2
        L_0x0f1b:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_guinea
            return r2
        L_0x0f1e:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_gambia
            return r2
        L_0x0f21:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_greenland
            return r2
        L_0x0f24:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_gibraltar
            return r2
        L_0x0f27:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_ghana
            return r2
        L_0x0f2a:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_guernsey
            return r2
        L_0x0f2d:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_guyane
            return r2
        L_0x0f30:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_georgia
            return r2
        L_0x0f33:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_grenada
            return r2
        L_0x0f36:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_united_kingdom
            return r2
        L_0x0f39:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_gabon
            return r2
        L_0x0f3c:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_france
            return r2
        L_0x0f3f:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_faroe_islands
            return r2
        L_0x0f42:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_micronesia
            return r2
        L_0x0f45:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_falkland_islands
            return r2
        L_0x0f48:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_fiji
            return r2
        L_0x0f4b:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_finland
            return r2
        L_0x0f4e:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_ethiopia
            return r2
        L_0x0f51:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_spain
            return r2
        L_0x0f54:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_eritrea
            return r2
        L_0x0f57:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_egypt
            return r2
        L_0x0f5a:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_estonia
            return r2
        L_0x0f5d:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_ecuador
            return r2
        L_0x0f60:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_algeria
            return r2
        L_0x0f63:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_dominican_republic
            return r2
        L_0x0f66:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_dominica
            return r2
        L_0x0f69:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_denmark
            return r2
        L_0x0f6c:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_djibouti
            return r2
        L_0x0f6f:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_germany
            return r2
        L_0x0f72:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_czech_republic
            return r2
        L_0x0f75:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_cyprus
            return r2
        L_0x0f78:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_christmas_island
            return r2
        L_0x0f7b:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_curacao
            return r2
        L_0x0f7e:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_cape_verde
            return r2
        L_0x0f81:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_cuba
            return r2
        L_0x0f84:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_costa_rica
            return r2
        L_0x0f87:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_colombia
            return r2
        L_0x0f8a:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_china
            return r2
        L_0x0f8d:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_cameroon
            return r2
        L_0x0f90:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_chile
            return r2
        L_0x0f93:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_cook_islands
            return r2
        L_0x0f96:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_cote_divoire
            return r2
        L_0x0f99:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_switzerland
            return r2
        L_0x0f9c:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_republic_of_the_congo
            return r2
        L_0x0f9f:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_central_african_republic
            return r2
        L_0x0fa2:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_democratic_republic_of_the_congo
            return r2
        L_0x0fa5:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_cocos
            return r2
        L_0x0fa8:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_canada
            return r2
        L_0x0fab:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_belize
            return r2
        L_0x0fae:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_belarus
            return r2
        L_0x0fb1:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_botswana
            return r2
        L_0x0fb4:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_bhutan
            return r2
        L_0x0fb7:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_bahamas
            return r2
        L_0x0fba:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_brazil
            return r2
        L_0x0fbd:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_bolivia
            return r2
        L_0x0fc0:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_brunei
            return r2
        L_0x0fc3:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_bermuda
            return r2
        L_0x0fc6:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_saint_barthelemy
            return r2
        L_0x0fc9:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_benin
            return r2
        L_0x0fcc:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_burundi
            return r2
        L_0x0fcf:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_bahrain
            return r2
        L_0x0fd2:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_bulgaria
            return r2
        L_0x0fd5:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_burkina_faso
            return r2
        L_0x0fd8:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_belgium
            return r2
        L_0x0fdb:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_bangladesh
            return r2
        L_0x0fde:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_barbados
            return r2
        L_0x0fe1:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_bosnia
            return r2
        L_0x0fe4:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_azerbaijan
            return r2
        L_0x0fe7:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_aland
            return r2
        L_0x0fea:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_aruba
            return r2
        L_0x0fed:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_australia
            return r2
        L_0x0ff0:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_austria
            return r2
        L_0x0ff3:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_american_samoa
            return r2
        L_0x0ff6:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_argentina
            return r2
        L_0x0ff9:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_antarctica
            return r2
        L_0x0ffc:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_angola
            return r2
        L_0x0fff:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_armenia
            return r2
        L_0x1002:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_albania
            return r2
        L_0x1005:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_anguilla
            return r2
        L_0x1008:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_antigua_and_barbuda
            return r2
        L_0x100b:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_afghanistan
            return r2
        L_0x100e:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_uae
            return r2
        L_0x1011:
            int r2 = com.hbb20.C2219R.C2221drawable.flag_andorra
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hbb20.CCPCountry.getFlagMasterResID(com.hbb20.CCPCountry):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v37, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v39, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v41, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v43, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v44, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v45, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v46, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v47, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v48, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v49, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v52, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v53, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v55, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v56, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v57, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v58, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v59, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v60, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v61, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v62, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v63, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v67, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v68, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v70, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v71, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v72, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v73, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v74, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v75, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v76, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v77, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v78, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v79, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v80, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v81, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v82, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v83, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v84, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v85, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v86, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v87, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v88, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v89, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v90, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v91, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v92, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v93, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v94, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v95, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v96, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v97, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v98, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v99, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v100, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v101, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v102, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v103, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v104, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v105, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v106, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v107, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v108, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v109, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v110, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v111, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v112, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v113, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v114, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v115, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v116, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v117, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v118, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v119, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v120, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v121, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v122, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v123, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v124, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v125, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v126, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v127, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v128, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v129, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v130, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v131, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v132, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v133, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v134, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v135, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v136, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v137, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v138, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v139, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v140, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v141, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v142, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v143, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v144, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v145, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v146, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v147, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v148, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v149, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v150, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v151, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v152, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v153, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v154, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v155, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v156, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v157, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v158, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v159, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v160, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v161, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v162, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v163, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v164, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v165, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v166, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v167, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v168, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v169, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v170, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v171, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v172, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v173, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v174, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v175, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v176, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v177, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v178, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v179, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v180, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v181, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v182, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v183, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v184, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v185, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v186, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v187, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v188, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v189, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v190, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v191, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v192, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v193, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v194, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v195, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v196, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v197, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v198, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v199, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v200, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v201, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v202, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v203, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v204, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v205, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v206, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v207, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v208, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v209, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v210, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v211, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v212, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v213, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v214, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v215, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v216, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v217, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v218, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v219, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v220, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v221, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v222, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v223, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v224, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v225, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v226, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v227, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v228, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v229, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v230, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v231, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v232, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v233, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v234, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v235, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v236, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v237, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v238, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v239, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v240, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v241, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v242, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v243, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v244, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v245, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v246, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v247, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v248, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v249, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v250, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v251, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String getFlagEmoji(com.hbb20.CCPCountry r2) {
        /*
            java.lang.String r2 = r2.getNameCode()
            java.lang.String r2 = r2.toLowerCase()
            r2.hashCode()
            int r0 = r2.hashCode()
            r1 = -1
            switch(r0) {
                case 3107: goto L_0x0d9e;
                case 3108: goto L_0x0d93;
                case 3109: goto L_0x0d88;
                case 3110: goto L_0x0d7d;
                case 3112: goto L_0x0d72;
                case 3115: goto L_0x0d67;
                case 3116: goto L_0x0d5c;
                case 3118: goto L_0x0d51;
                case 3120: goto L_0x0d43;
                case 3121: goto L_0x0d35;
                case 3122: goto L_0x0d27;
                case 3123: goto L_0x0d19;
                case 3124: goto L_0x0d0b;
                case 3126: goto L_0x0cfd;
                case 3127: goto L_0x0cef;
                case 3129: goto L_0x0ce1;
                case 3135: goto L_0x0cd3;
                case 3136: goto L_0x0cc5;
                case 3138: goto L_0x0cb7;
                case 3139: goto L_0x0ca9;
                case 3140: goto L_0x0c9b;
                case 3141: goto L_0x0c8d;
                case 3142: goto L_0x0c7f;
                case 3143: goto L_0x0c71;
                case 3144: goto L_0x0c63;
                case 3146: goto L_0x0c55;
                case 3147: goto L_0x0c47;
                case 3148: goto L_0x0c39;
                case 3149: goto L_0x0c2b;
                case 3151: goto L_0x0c1d;
                case 3152: goto L_0x0c0f;
                case 3153: goto L_0x0c01;
                case 3154: goto L_0x0bf3;
                case 3156: goto L_0x0be5;
                case 3157: goto L_0x0bd7;
                case 3159: goto L_0x0bc9;
                case 3160: goto L_0x0bbb;
                case 3166: goto L_0x0bad;
                case 3168: goto L_0x0b9f;
                case 3169: goto L_0x0b91;
                case 3171: goto L_0x0b83;
                case 3172: goto L_0x0b75;
                case 3173: goto L_0x0b67;
                case 3174: goto L_0x0b59;
                case 3176: goto L_0x0b4b;
                case 3177: goto L_0x0b3d;
                case 3178: goto L_0x0b2f;
                case 3179: goto L_0x0b21;
                case 3180: goto L_0x0b13;
                case 3183: goto L_0x0b05;
                case 3186: goto L_0x0af7;
                case 3187: goto L_0x0ae9;
                case 3188: goto L_0x0adb;
                case 3189: goto L_0x0acd;
                case 3190: goto L_0x0abf;
                case 3191: goto L_0x0ab1;
                case 3201: goto L_0x0aa3;
                case 3206: goto L_0x0a95;
                case 3207: goto L_0x0a87;
                case 3209: goto L_0x0a79;
                case 3211: goto L_0x0a6b;
                case 3222: goto L_0x0a5d;
                case 3230: goto L_0x0a4f;
                case 3232: goto L_0x0a41;
                case 3234: goto L_0x0a33;
                case 3235: goto L_0x0a25;
                case 3245: goto L_0x0a17;
                case 3246: goto L_0x0a09;
                case 3247: goto L_0x09fb;
                case 3267: goto L_0x09ed;
                case 3268: goto L_0x09df;
                case 3269: goto L_0x09d1;
                case 3271: goto L_0x09c3;
                case 3273: goto L_0x09b5;
                case 3276: goto L_0x09a7;
                case 3290: goto L_0x0999;
                case 3291: goto L_0x098b;
                case 3293: goto L_0x097d;
                case 3294: goto L_0x096f;
                case 3295: goto L_0x0961;
                case 3296: goto L_0x0953;
                case 3297: goto L_0x0945;
                case 3298: goto L_0x0937;
                case 3301: goto L_0x0929;
                case 3302: goto L_0x091b;
                case 3303: goto L_0x090d;
                case 3305: goto L_0x08ff;
                case 3306: goto L_0x08f1;
                case 3307: goto L_0x08e3;
                case 3308: goto L_0x08d5;
                case 3309: goto L_0x08c7;
                case 3310: goto L_0x08b9;
                case 3312: goto L_0x08ab;
                case 3314: goto L_0x089d;
                case 3331: goto L_0x088f;
                case 3333: goto L_0x0881;
                case 3334: goto L_0x0873;
                case 3338: goto L_0x0865;
                case 3340: goto L_0x0857;
                case 3341: goto L_0x0849;
                case 3355: goto L_0x083b;
                case 3356: goto L_0x082d;
                case 3363: goto L_0x081f;
                case 3364: goto L_0x0811;
                case 3365: goto L_0x0803;
                case 3366: goto L_0x07f5;
                case 3368: goto L_0x07e7;
                case 3369: goto L_0x07d9;
                case 3370: goto L_0x07cb;
                case 3371: goto L_0x07bd;
                case 3387: goto L_0x07af;
                case 3395: goto L_0x07a1;
                case 3397: goto L_0x0793;
                case 3398: goto L_0x0785;
                case 3418: goto L_0x0777;
                case 3420: goto L_0x0769;
                case 3421: goto L_0x075b;
                case 3422: goto L_0x074d;
                case 3426: goto L_0x073f;
                case 3427: goto L_0x0731;
                case 3429: goto L_0x0723;
                case 3431: goto L_0x0715;
                case 3436: goto L_0x0707;
                case 3438: goto L_0x06f9;
                case 3439: goto L_0x06eb;
                case 3445: goto L_0x06dd;
                case 3446: goto L_0x06cf;
                case 3447: goto L_0x06c1;
                case 3453: goto L_0x06b3;
                case 3455: goto L_0x06a5;
                case 3462: goto L_0x0697;
                case 3463: goto L_0x0689;
                case 3464: goto L_0x067b;
                case 3465: goto L_0x066d;
                case 3466: goto L_0x065f;
                case 3469: goto L_0x0651;
                case 3476: goto L_0x0643;
                case 3478: goto L_0x0635;
                case 3479: goto L_0x0627;
                case 3480: goto L_0x0619;
                case 3481: goto L_0x060b;
                case 3482: goto L_0x05fd;
                case 3483: goto L_0x05ef;
                case 3486: goto L_0x05e1;
                case 3487: goto L_0x05d3;
                case 3488: goto L_0x05c5;
                case 3489: goto L_0x05b7;
                case 3490: goto L_0x05a9;
                case 3491: goto L_0x059b;
                case 3492: goto L_0x058d;
                case 3493: goto L_0x057f;
                case 3494: goto L_0x0571;
                case 3495: goto L_0x0563;
                case 3496: goto L_0x0555;
                case 3497: goto L_0x0547;
                case 3498: goto L_0x0539;
                case 3499: goto L_0x052b;
                case 3500: goto L_0x051d;
                case 3501: goto L_0x050f;
                case 3507: goto L_0x0501;
                case 3509: goto L_0x04f3;
                case 3511: goto L_0x04e5;
                case 3512: goto L_0x04d7;
                case 3513: goto L_0x04c9;
                case 3515: goto L_0x04bb;
                case 3518: goto L_0x04ad;
                case 3521: goto L_0x049f;
                case 3522: goto L_0x0491;
                case 3524: goto L_0x0483;
                case 3527: goto L_0x0475;
                case 3532: goto L_0x0467;
                case 3550: goto L_0x0459;
                case 3569: goto L_0x044b;
                case 3573: goto L_0x043d;
                case 3574: goto L_0x042f;
                case 3575: goto L_0x0421;
                case 3576: goto L_0x0413;
                case 3579: goto L_0x0405;
                case 3580: goto L_0x03f7;
                case 3581: goto L_0x03e9;
                case 3582: goto L_0x03db;
                case 3586: goto L_0x03cd;
                case 3587: goto L_0x03bf;
                case 3588: goto L_0x03b1;
                case 3591: goto L_0x03a3;
                case 3593: goto L_0x0395;
                case 3600: goto L_0x0387;
                case 3635: goto L_0x0379;
                case 3645: goto L_0x036b;
                case 3649: goto L_0x035d;
                case 3651: goto L_0x034f;
                case 3653: goto L_0x0341;
                case 3662: goto L_0x0333;
                case 3663: goto L_0x0325;
                case 3664: goto L_0x0317;
                case 3665: goto L_0x0309;
                case 3666: goto L_0x02fb;
                case 3668: goto L_0x02ed;
                case 3669: goto L_0x02df;
                case 3670: goto L_0x02d1;
                case 3671: goto L_0x02c3;
                case 3672: goto L_0x02b5;
                case 3673: goto L_0x02a7;
                case 3674: goto L_0x0299;
                case 3675: goto L_0x028b;
                case 3676: goto L_0x027d;
                case 3679: goto L_0x026f;
                case 3680: goto L_0x0261;
                case 3681: goto L_0x0253;
                case 3683: goto L_0x0245;
                case 3685: goto L_0x0237;
                case 3686: goto L_0x0229;
                case 3687: goto L_0x021b;
                case 3695: goto L_0x020d;
                case 3696: goto L_0x01ff;
                case 3698: goto L_0x01f1;
                case 3699: goto L_0x01e3;
                case 3700: goto L_0x01d5;
                case 3702: goto L_0x01c7;
                case 3703: goto L_0x01b9;
                case 3704: goto L_0x01ab;
                case 3705: goto L_0x019d;
                case 3706: goto L_0x018f;
                case 3707: goto L_0x0181;
                case 3710: goto L_0x0173;
                case 3712: goto L_0x0165;
                case 3714: goto L_0x0157;
                case 3715: goto L_0x0149;
                case 3718: goto L_0x013b;
                case 3724: goto L_0x012d;
                case 3730: goto L_0x011f;
                case 3736: goto L_0x0111;
                case 3742: goto L_0x0103;
                case 3748: goto L_0x00f5;
                case 3749: goto L_0x00e7;
                case 3755: goto L_0x00d9;
                case 3757: goto L_0x00cb;
                case 3759: goto L_0x00bd;
                case 3761: goto L_0x00af;
                case 3763: goto L_0x00a1;
                case 3768: goto L_0x0093;
                case 3775: goto L_0x0085;
                case 3791: goto L_0x0077;
                case 3804: goto L_0x0069;
                case 3827: goto L_0x005b;
                case 3852: goto L_0x004d;
                case 3867: goto L_0x003f;
                case 3879: goto L_0x0031;
                case 3891: goto L_0x0023;
                case 3901: goto L_0x0015;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0da8
        L_0x0015:
            java.lang.String r0 = "zw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x001f
            goto L_0x0da8
        L_0x001f:
            r1 = 249(0xf9, float:3.49E-43)
            goto L_0x0da8
        L_0x0023:
            java.lang.String r0 = "zm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x002d
            goto L_0x0da8
        L_0x002d:
            r1 = 248(0xf8, float:3.48E-43)
            goto L_0x0da8
        L_0x0031:
            java.lang.String r0 = "za"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x003b
            goto L_0x0da8
        L_0x003b:
            r1 = 247(0xf7, float:3.46E-43)
            goto L_0x0da8
        L_0x003f:
            java.lang.String r0 = "yt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0049
            goto L_0x0da8
        L_0x0049:
            r1 = 246(0xf6, float:3.45E-43)
            goto L_0x0da8
        L_0x004d:
            java.lang.String r0 = "ye"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0057
            goto L_0x0da8
        L_0x0057:
            r1 = 245(0xf5, float:3.43E-43)
            goto L_0x0da8
        L_0x005b:
            java.lang.String r0 = "xk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0065
            goto L_0x0da8
        L_0x0065:
            r1 = 244(0xf4, float:3.42E-43)
            goto L_0x0da8
        L_0x0069:
            java.lang.String r0 = "ws"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0073
            goto L_0x0da8
        L_0x0073:
            r1 = 243(0xf3, float:3.4E-43)
            goto L_0x0da8
        L_0x0077:
            java.lang.String r0 = "wf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0081
            goto L_0x0da8
        L_0x0081:
            r1 = 242(0xf2, float:3.39E-43)
            goto L_0x0da8
        L_0x0085:
            java.lang.String r0 = "vu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x008f
            goto L_0x0da8
        L_0x008f:
            r1 = 241(0xf1, float:3.38E-43)
            goto L_0x0da8
        L_0x0093:
            java.lang.String r0 = "vn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x009d
            goto L_0x0da8
        L_0x009d:
            r1 = 240(0xf0, float:3.36E-43)
            goto L_0x0da8
        L_0x00a1:
            java.lang.String r0 = "vi"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00ab
            goto L_0x0da8
        L_0x00ab:
            r1 = 239(0xef, float:3.35E-43)
            goto L_0x0da8
        L_0x00af:
            java.lang.String r0 = "vg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00b9
            goto L_0x0da8
        L_0x00b9:
            r1 = 238(0xee, float:3.34E-43)
            goto L_0x0da8
        L_0x00bd:
            java.lang.String r0 = "ve"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00c7
            goto L_0x0da8
        L_0x00c7:
            r1 = 237(0xed, float:3.32E-43)
            goto L_0x0da8
        L_0x00cb:
            java.lang.String r0 = "vc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00d5
            goto L_0x0da8
        L_0x00d5:
            r1 = 236(0xec, float:3.31E-43)
            goto L_0x0da8
        L_0x00d9:
            java.lang.String r0 = "va"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00e3
            goto L_0x0da8
        L_0x00e3:
            r1 = 235(0xeb, float:3.3E-43)
            goto L_0x0da8
        L_0x00e7:
            java.lang.String r0 = "uz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00f1
            goto L_0x0da8
        L_0x00f1:
            r1 = 234(0xea, float:3.28E-43)
            goto L_0x0da8
        L_0x00f5:
            java.lang.String r0 = "uy"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00ff
            goto L_0x0da8
        L_0x00ff:
            r1 = 233(0xe9, float:3.27E-43)
            goto L_0x0da8
        L_0x0103:
            java.lang.String r0 = "us"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x010d
            goto L_0x0da8
        L_0x010d:
            r1 = 232(0xe8, float:3.25E-43)
            goto L_0x0da8
        L_0x0111:
            java.lang.String r0 = "um"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x011b
            goto L_0x0da8
        L_0x011b:
            r1 = 231(0xe7, float:3.24E-43)
            goto L_0x0da8
        L_0x011f:
            java.lang.String r0 = "ug"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0129
            goto L_0x0da8
        L_0x0129:
            r1 = 230(0xe6, float:3.22E-43)
            goto L_0x0da8
        L_0x012d:
            java.lang.String r0 = "ua"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0137
            goto L_0x0da8
        L_0x0137:
            r1 = 229(0xe5, float:3.21E-43)
            goto L_0x0da8
        L_0x013b:
            java.lang.String r0 = "tz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0145
            goto L_0x0da8
        L_0x0145:
            r1 = 228(0xe4, float:3.2E-43)
            goto L_0x0da8
        L_0x0149:
            java.lang.String r0 = "tw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0153
            goto L_0x0da8
        L_0x0153:
            r1 = 227(0xe3, float:3.18E-43)
            goto L_0x0da8
        L_0x0157:
            java.lang.String r0 = "tv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0161
            goto L_0x0da8
        L_0x0161:
            r1 = 226(0xe2, float:3.17E-43)
            goto L_0x0da8
        L_0x0165:
            java.lang.String r0 = "tt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x016f
            goto L_0x0da8
        L_0x016f:
            r1 = 225(0xe1, float:3.15E-43)
            goto L_0x0da8
        L_0x0173:
            java.lang.String r0 = "tr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x017d
            goto L_0x0da8
        L_0x017d:
            r1 = 224(0xe0, float:3.14E-43)
            goto L_0x0da8
        L_0x0181:
            java.lang.String r0 = "to"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x018b
            goto L_0x0da8
        L_0x018b:
            r1 = 223(0xdf, float:3.12E-43)
            goto L_0x0da8
        L_0x018f:
            java.lang.String r0 = "tn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0199
            goto L_0x0da8
        L_0x0199:
            r1 = 222(0xde, float:3.11E-43)
            goto L_0x0da8
        L_0x019d:
            java.lang.String r0 = "tm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01a7
            goto L_0x0da8
        L_0x01a7:
            r1 = 221(0xdd, float:3.1E-43)
            goto L_0x0da8
        L_0x01ab:
            java.lang.String r0 = "tl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01b5
            goto L_0x0da8
        L_0x01b5:
            r1 = 220(0xdc, float:3.08E-43)
            goto L_0x0da8
        L_0x01b9:
            java.lang.String r0 = "tk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01c3
            goto L_0x0da8
        L_0x01c3:
            r1 = 219(0xdb, float:3.07E-43)
            goto L_0x0da8
        L_0x01c7:
            java.lang.String r0 = "tj"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01d1
            goto L_0x0da8
        L_0x01d1:
            r1 = 218(0xda, float:3.05E-43)
            goto L_0x0da8
        L_0x01d5:
            java.lang.String r0 = "th"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01df
            goto L_0x0da8
        L_0x01df:
            r1 = 217(0xd9, float:3.04E-43)
            goto L_0x0da8
        L_0x01e3:
            java.lang.String r0 = "tg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01ed
            goto L_0x0da8
        L_0x01ed:
            r1 = 216(0xd8, float:3.03E-43)
            goto L_0x0da8
        L_0x01f1:
            java.lang.String r0 = "tf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x01fb
            goto L_0x0da8
        L_0x01fb:
            r1 = 215(0xd7, float:3.01E-43)
            goto L_0x0da8
        L_0x01ff:
            java.lang.String r0 = "td"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0209
            goto L_0x0da8
        L_0x0209:
            r1 = 214(0xd6, float:3.0E-43)
            goto L_0x0da8
        L_0x020d:
            java.lang.String r0 = "tc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0217
            goto L_0x0da8
        L_0x0217:
            r1 = 213(0xd5, float:2.98E-43)
            goto L_0x0da8
        L_0x021b:
            java.lang.String r0 = "sz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0225
            goto L_0x0da8
        L_0x0225:
            r1 = 212(0xd4, float:2.97E-43)
            goto L_0x0da8
        L_0x0229:
            java.lang.String r0 = "sy"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0233
            goto L_0x0da8
        L_0x0233:
            r1 = 211(0xd3, float:2.96E-43)
            goto L_0x0da8
        L_0x0237:
            java.lang.String r0 = "sx"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0241
            goto L_0x0da8
        L_0x0241:
            r1 = 210(0xd2, float:2.94E-43)
            goto L_0x0da8
        L_0x0245:
            java.lang.String r0 = "sv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x024f
            goto L_0x0da8
        L_0x024f:
            r1 = 209(0xd1, float:2.93E-43)
            goto L_0x0da8
        L_0x0253:
            java.lang.String r0 = "st"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x025d
            goto L_0x0da8
        L_0x025d:
            r1 = 208(0xd0, float:2.91E-43)
            goto L_0x0da8
        L_0x0261:
            java.lang.String r0 = "ss"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x026b
            goto L_0x0da8
        L_0x026b:
            r1 = 207(0xcf, float:2.9E-43)
            goto L_0x0da8
        L_0x026f:
            java.lang.String r0 = "sr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0279
            goto L_0x0da8
        L_0x0279:
            r1 = 206(0xce, float:2.89E-43)
            goto L_0x0da8
        L_0x027d:
            java.lang.String r0 = "so"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0287
            goto L_0x0da8
        L_0x0287:
            r1 = 205(0xcd, float:2.87E-43)
            goto L_0x0da8
        L_0x028b:
            java.lang.String r0 = "sn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0295
            goto L_0x0da8
        L_0x0295:
            r1 = 204(0xcc, float:2.86E-43)
            goto L_0x0da8
        L_0x0299:
            java.lang.String r0 = "sm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02a3
            goto L_0x0da8
        L_0x02a3:
            r1 = 203(0xcb, float:2.84E-43)
            goto L_0x0da8
        L_0x02a7:
            java.lang.String r0 = "sl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02b1
            goto L_0x0da8
        L_0x02b1:
            r1 = 202(0xca, float:2.83E-43)
            goto L_0x0da8
        L_0x02b5:
            java.lang.String r0 = "sk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02bf
            goto L_0x0da8
        L_0x02bf:
            r1 = 201(0xc9, float:2.82E-43)
            goto L_0x0da8
        L_0x02c3:
            java.lang.String r0 = "sj"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02cd
            goto L_0x0da8
        L_0x02cd:
            r1 = 200(0xc8, float:2.8E-43)
            goto L_0x0da8
        L_0x02d1:
            java.lang.String r0 = "si"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02db
            goto L_0x0da8
        L_0x02db:
            r1 = 199(0xc7, float:2.79E-43)
            goto L_0x0da8
        L_0x02df:
            java.lang.String r0 = "sh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02e9
            goto L_0x0da8
        L_0x02e9:
            r1 = 198(0xc6, float:2.77E-43)
            goto L_0x0da8
        L_0x02ed:
            java.lang.String r0 = "sg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x02f7
            goto L_0x0da8
        L_0x02f7:
            r1 = 197(0xc5, float:2.76E-43)
            goto L_0x0da8
        L_0x02fb:
            java.lang.String r0 = "se"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0305
            goto L_0x0da8
        L_0x0305:
            r1 = 196(0xc4, float:2.75E-43)
            goto L_0x0da8
        L_0x0309:
            java.lang.String r0 = "sd"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0313
            goto L_0x0da8
        L_0x0313:
            r1 = 195(0xc3, float:2.73E-43)
            goto L_0x0da8
        L_0x0317:
            java.lang.String r0 = "sc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0321
            goto L_0x0da8
        L_0x0321:
            r1 = 194(0xc2, float:2.72E-43)
            goto L_0x0da8
        L_0x0325:
            java.lang.String r0 = "sb"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x032f
            goto L_0x0da8
        L_0x032f:
            r1 = 193(0xc1, float:2.7E-43)
            goto L_0x0da8
        L_0x0333:
            java.lang.String r0 = "sa"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x033d
            goto L_0x0da8
        L_0x033d:
            r1 = 192(0xc0, float:2.69E-43)
            goto L_0x0da8
        L_0x0341:
            java.lang.String r0 = "rw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x034b
            goto L_0x0da8
        L_0x034b:
            r1 = 191(0xbf, float:2.68E-43)
            goto L_0x0da8
        L_0x034f:
            java.lang.String r0 = "ru"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0359
            goto L_0x0da8
        L_0x0359:
            r1 = 190(0xbe, float:2.66E-43)
            goto L_0x0da8
        L_0x035d:
            java.lang.String r0 = "rs"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0367
            goto L_0x0da8
        L_0x0367:
            r1 = 189(0xbd, float:2.65E-43)
            goto L_0x0da8
        L_0x036b:
            java.lang.String r0 = "ro"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0375
            goto L_0x0da8
        L_0x0375:
            r1 = 188(0xbc, float:2.63E-43)
            goto L_0x0da8
        L_0x0379:
            java.lang.String r0 = "re"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0383
            goto L_0x0da8
        L_0x0383:
            r1 = 187(0xbb, float:2.62E-43)
            goto L_0x0da8
        L_0x0387:
            java.lang.String r0 = "qa"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0391
            goto L_0x0da8
        L_0x0391:
            r1 = 186(0xba, float:2.6E-43)
            goto L_0x0da8
        L_0x0395:
            java.lang.String r0 = "py"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x039f
            goto L_0x0da8
        L_0x039f:
            r1 = 185(0xb9, float:2.59E-43)
            goto L_0x0da8
        L_0x03a3:
            java.lang.String r0 = "pw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03ad
            goto L_0x0da8
        L_0x03ad:
            r1 = 184(0xb8, float:2.58E-43)
            goto L_0x0da8
        L_0x03b1:
            java.lang.String r0 = "pt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03bb
            goto L_0x0da8
        L_0x03bb:
            r1 = 183(0xb7, float:2.56E-43)
            goto L_0x0da8
        L_0x03bf:
            java.lang.String r0 = "ps"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03c9
            goto L_0x0da8
        L_0x03c9:
            r1 = 182(0xb6, float:2.55E-43)
            goto L_0x0da8
        L_0x03cd:
            java.lang.String r0 = "pr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03d7
            goto L_0x0da8
        L_0x03d7:
            r1 = 181(0xb5, float:2.54E-43)
            goto L_0x0da8
        L_0x03db:
            java.lang.String r0 = "pn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03e5
            goto L_0x0da8
        L_0x03e5:
            r1 = 180(0xb4, float:2.52E-43)
            goto L_0x0da8
        L_0x03e9:
            java.lang.String r0 = "pm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x03f3
            goto L_0x0da8
        L_0x03f3:
            r1 = 179(0xb3, float:2.51E-43)
            goto L_0x0da8
        L_0x03f7:
            java.lang.String r0 = "pl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0401
            goto L_0x0da8
        L_0x0401:
            r1 = 178(0xb2, float:2.5E-43)
            goto L_0x0da8
        L_0x0405:
            java.lang.String r0 = "pk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x040f
            goto L_0x0da8
        L_0x040f:
            r1 = 177(0xb1, float:2.48E-43)
            goto L_0x0da8
        L_0x0413:
            java.lang.String r0 = "ph"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x041d
            goto L_0x0da8
        L_0x041d:
            r1 = 176(0xb0, float:2.47E-43)
            goto L_0x0da8
        L_0x0421:
            java.lang.String r0 = "pg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x042b
            goto L_0x0da8
        L_0x042b:
            r1 = 175(0xaf, float:2.45E-43)
            goto L_0x0da8
        L_0x042f:
            java.lang.String r0 = "pf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0439
            goto L_0x0da8
        L_0x0439:
            r1 = 174(0xae, float:2.44E-43)
            goto L_0x0da8
        L_0x043d:
            java.lang.String r0 = "pe"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0447
            goto L_0x0da8
        L_0x0447:
            r1 = 173(0xad, float:2.42E-43)
            goto L_0x0da8
        L_0x044b:
            java.lang.String r0 = "pa"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0455
            goto L_0x0da8
        L_0x0455:
            r1 = 172(0xac, float:2.41E-43)
            goto L_0x0da8
        L_0x0459:
            java.lang.String r0 = "om"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0463
            goto L_0x0da8
        L_0x0463:
            r1 = 171(0xab, float:2.4E-43)
            goto L_0x0da8
        L_0x0467:
            java.lang.String r0 = "nz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0471
            goto L_0x0da8
        L_0x0471:
            r1 = 170(0xaa, float:2.38E-43)
            goto L_0x0da8
        L_0x0475:
            java.lang.String r0 = "nu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x047f
            goto L_0x0da8
        L_0x047f:
            r1 = 169(0xa9, float:2.37E-43)
            goto L_0x0da8
        L_0x0483:
            java.lang.String r0 = "nr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x048d
            goto L_0x0da8
        L_0x048d:
            r1 = 168(0xa8, float:2.35E-43)
            goto L_0x0da8
        L_0x0491:
            java.lang.String r0 = "np"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x049b
            goto L_0x0da8
        L_0x049b:
            r1 = 167(0xa7, float:2.34E-43)
            goto L_0x0da8
        L_0x049f:
            java.lang.String r0 = "no"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04a9
            goto L_0x0da8
        L_0x04a9:
            r1 = 166(0xa6, float:2.33E-43)
            goto L_0x0da8
        L_0x04ad:
            java.lang.String r0 = "nl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04b7
            goto L_0x0da8
        L_0x04b7:
            r1 = 165(0xa5, float:2.31E-43)
            goto L_0x0da8
        L_0x04bb:
            java.lang.String r0 = "ni"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04c5
            goto L_0x0da8
        L_0x04c5:
            r1 = 164(0xa4, float:2.3E-43)
            goto L_0x0da8
        L_0x04c9:
            java.lang.String r0 = "ng"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04d3
            goto L_0x0da8
        L_0x04d3:
            r1 = 163(0xa3, float:2.28E-43)
            goto L_0x0da8
        L_0x04d7:
            java.lang.String r0 = "nf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04e1
            goto L_0x0da8
        L_0x04e1:
            r1 = 162(0xa2, float:2.27E-43)
            goto L_0x0da8
        L_0x04e5:
            java.lang.String r0 = "ne"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04ef
            goto L_0x0da8
        L_0x04ef:
            r1 = 161(0xa1, float:2.26E-43)
            goto L_0x0da8
        L_0x04f3:
            java.lang.String r0 = "nc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x04fd
            goto L_0x0da8
        L_0x04fd:
            r1 = 160(0xa0, float:2.24E-43)
            goto L_0x0da8
        L_0x0501:
            java.lang.String r0 = "na"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x050b
            goto L_0x0da8
        L_0x050b:
            r1 = 159(0x9f, float:2.23E-43)
            goto L_0x0da8
        L_0x050f:
            java.lang.String r0 = "mz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0519
            goto L_0x0da8
        L_0x0519:
            r1 = 158(0x9e, float:2.21E-43)
            goto L_0x0da8
        L_0x051d:
            java.lang.String r0 = "my"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0527
            goto L_0x0da8
        L_0x0527:
            r1 = 157(0x9d, float:2.2E-43)
            goto L_0x0da8
        L_0x052b:
            java.lang.String r0 = "mx"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0535
            goto L_0x0da8
        L_0x0535:
            r1 = 156(0x9c, float:2.19E-43)
            goto L_0x0da8
        L_0x0539:
            java.lang.String r0 = "mw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0543
            goto L_0x0da8
        L_0x0543:
            r1 = 155(0x9b, float:2.17E-43)
            goto L_0x0da8
        L_0x0547:
            java.lang.String r0 = "mv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0551
            goto L_0x0da8
        L_0x0551:
            r1 = 154(0x9a, float:2.16E-43)
            goto L_0x0da8
        L_0x0555:
            java.lang.String r0 = "mu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x055f
            goto L_0x0da8
        L_0x055f:
            r1 = 153(0x99, float:2.14E-43)
            goto L_0x0da8
        L_0x0563:
            java.lang.String r0 = "mt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x056d
            goto L_0x0da8
        L_0x056d:
            r1 = 152(0x98, float:2.13E-43)
            goto L_0x0da8
        L_0x0571:
            java.lang.String r0 = "ms"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x057b
            goto L_0x0da8
        L_0x057b:
            r1 = 151(0x97, float:2.12E-43)
            goto L_0x0da8
        L_0x057f:
            java.lang.String r0 = "mr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0589
            goto L_0x0da8
        L_0x0589:
            r1 = 150(0x96, float:2.1E-43)
            goto L_0x0da8
        L_0x058d:
            java.lang.String r0 = "mq"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0597
            goto L_0x0da8
        L_0x0597:
            r1 = 149(0x95, float:2.09E-43)
            goto L_0x0da8
        L_0x059b:
            java.lang.String r0 = "mp"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05a5
            goto L_0x0da8
        L_0x05a5:
            r1 = 148(0x94, float:2.07E-43)
            goto L_0x0da8
        L_0x05a9:
            java.lang.String r0 = "mo"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05b3
            goto L_0x0da8
        L_0x05b3:
            r1 = 147(0x93, float:2.06E-43)
            goto L_0x0da8
        L_0x05b7:
            java.lang.String r0 = "mn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05c1
            goto L_0x0da8
        L_0x05c1:
            r1 = 146(0x92, float:2.05E-43)
            goto L_0x0da8
        L_0x05c5:
            java.lang.String r0 = "mm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05cf
            goto L_0x0da8
        L_0x05cf:
            r1 = 145(0x91, float:2.03E-43)
            goto L_0x0da8
        L_0x05d3:
            java.lang.String r0 = "ml"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05dd
            goto L_0x0da8
        L_0x05dd:
            r1 = 144(0x90, float:2.02E-43)
            goto L_0x0da8
        L_0x05e1:
            java.lang.String r0 = "mk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05eb
            goto L_0x0da8
        L_0x05eb:
            r1 = 143(0x8f, float:2.0E-43)
            goto L_0x0da8
        L_0x05ef:
            java.lang.String r0 = "mh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x05f9
            goto L_0x0da8
        L_0x05f9:
            r1 = 142(0x8e, float:1.99E-43)
            goto L_0x0da8
        L_0x05fd:
            java.lang.String r0 = "mg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0607
            goto L_0x0da8
        L_0x0607:
            r1 = 141(0x8d, float:1.98E-43)
            goto L_0x0da8
        L_0x060b:
            java.lang.String r0 = "mf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0615
            goto L_0x0da8
        L_0x0615:
            r1 = 140(0x8c, float:1.96E-43)
            goto L_0x0da8
        L_0x0619:
            java.lang.String r0 = "me"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0623
            goto L_0x0da8
        L_0x0623:
            r1 = 139(0x8b, float:1.95E-43)
            goto L_0x0da8
        L_0x0627:
            java.lang.String r0 = "md"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0631
            goto L_0x0da8
        L_0x0631:
            r1 = 138(0x8a, float:1.93E-43)
            goto L_0x0da8
        L_0x0635:
            java.lang.String r0 = "mc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x063f
            goto L_0x0da8
        L_0x063f:
            r1 = 137(0x89, float:1.92E-43)
            goto L_0x0da8
        L_0x0643:
            java.lang.String r0 = "ma"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x064d
            goto L_0x0da8
        L_0x064d:
            r1 = 136(0x88, float:1.9E-43)
            goto L_0x0da8
        L_0x0651:
            java.lang.String r0 = "ly"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x065b
            goto L_0x0da8
        L_0x065b:
            r1 = 135(0x87, float:1.89E-43)
            goto L_0x0da8
        L_0x065f:
            java.lang.String r0 = "lv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0669
            goto L_0x0da8
        L_0x0669:
            r1 = 134(0x86, float:1.88E-43)
            goto L_0x0da8
        L_0x066d:
            java.lang.String r0 = "lu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0677
            goto L_0x0da8
        L_0x0677:
            r1 = 133(0x85, float:1.86E-43)
            goto L_0x0da8
        L_0x067b:
            java.lang.String r0 = "lt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0685
            goto L_0x0da8
        L_0x0685:
            r1 = 132(0x84, float:1.85E-43)
            goto L_0x0da8
        L_0x0689:
            java.lang.String r0 = "ls"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0693
            goto L_0x0da8
        L_0x0693:
            r1 = 131(0x83, float:1.84E-43)
            goto L_0x0da8
        L_0x0697:
            java.lang.String r0 = "lr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06a1
            goto L_0x0da8
        L_0x06a1:
            r1 = 130(0x82, float:1.82E-43)
            goto L_0x0da8
        L_0x06a5:
            java.lang.String r0 = "lk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06af
            goto L_0x0da8
        L_0x06af:
            r1 = 129(0x81, float:1.81E-43)
            goto L_0x0da8
        L_0x06b3:
            java.lang.String r0 = "li"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06bd
            goto L_0x0da8
        L_0x06bd:
            r1 = 128(0x80, float:1.794E-43)
            goto L_0x0da8
        L_0x06c1:
            java.lang.String r0 = "lc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06cb
            goto L_0x0da8
        L_0x06cb:
            r1 = 127(0x7f, float:1.78E-43)
            goto L_0x0da8
        L_0x06cf:
            java.lang.String r0 = "lb"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06d9
            goto L_0x0da8
        L_0x06d9:
            r1 = 126(0x7e, float:1.77E-43)
            goto L_0x0da8
        L_0x06dd:
            java.lang.String r0 = "la"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06e7
            goto L_0x0da8
        L_0x06e7:
            r1 = 125(0x7d, float:1.75E-43)
            goto L_0x0da8
        L_0x06eb:
            java.lang.String r0 = "kz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x06f5
            goto L_0x0da8
        L_0x06f5:
            r1 = 124(0x7c, float:1.74E-43)
            goto L_0x0da8
        L_0x06f9:
            java.lang.String r0 = "ky"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0703
            goto L_0x0da8
        L_0x0703:
            r1 = 123(0x7b, float:1.72E-43)
            goto L_0x0da8
        L_0x0707:
            java.lang.String r0 = "kw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0711
            goto L_0x0da8
        L_0x0711:
            r1 = 122(0x7a, float:1.71E-43)
            goto L_0x0da8
        L_0x0715:
            java.lang.String r0 = "kr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x071f
            goto L_0x0da8
        L_0x071f:
            r1 = 121(0x79, float:1.7E-43)
            goto L_0x0da8
        L_0x0723:
            java.lang.String r0 = "kp"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x072d
            goto L_0x0da8
        L_0x072d:
            r1 = 120(0x78, float:1.68E-43)
            goto L_0x0da8
        L_0x0731:
            java.lang.String r0 = "kn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x073b
            goto L_0x0da8
        L_0x073b:
            r1 = 119(0x77, float:1.67E-43)
            goto L_0x0da8
        L_0x073f:
            java.lang.String r0 = "km"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0749
            goto L_0x0da8
        L_0x0749:
            r1 = 118(0x76, float:1.65E-43)
            goto L_0x0da8
        L_0x074d:
            java.lang.String r0 = "ki"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0757
            goto L_0x0da8
        L_0x0757:
            r1 = 117(0x75, float:1.64E-43)
            goto L_0x0da8
        L_0x075b:
            java.lang.String r0 = "kh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0765
            goto L_0x0da8
        L_0x0765:
            r1 = 116(0x74, float:1.63E-43)
            goto L_0x0da8
        L_0x0769:
            java.lang.String r0 = "kg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0773
            goto L_0x0da8
        L_0x0773:
            r1 = 115(0x73, float:1.61E-43)
            goto L_0x0da8
        L_0x0777:
            java.lang.String r0 = "ke"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0781
            goto L_0x0da8
        L_0x0781:
            r1 = 114(0x72, float:1.6E-43)
            goto L_0x0da8
        L_0x0785:
            java.lang.String r0 = "jp"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x078f
            goto L_0x0da8
        L_0x078f:
            r1 = 113(0x71, float:1.58E-43)
            goto L_0x0da8
        L_0x0793:
            java.lang.String r0 = "jo"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x079d
            goto L_0x0da8
        L_0x079d:
            r1 = 112(0x70, float:1.57E-43)
            goto L_0x0da8
        L_0x07a1:
            java.lang.String r0 = "jm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07ab
            goto L_0x0da8
        L_0x07ab:
            r1 = 111(0x6f, float:1.56E-43)
            goto L_0x0da8
        L_0x07af:
            java.lang.String r0 = "je"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07b9
            goto L_0x0da8
        L_0x07b9:
            r1 = 110(0x6e, float:1.54E-43)
            goto L_0x0da8
        L_0x07bd:
            java.lang.String r0 = "it"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07c7
            goto L_0x0da8
        L_0x07c7:
            r1 = 109(0x6d, float:1.53E-43)
            goto L_0x0da8
        L_0x07cb:
            java.lang.String r0 = "is"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07d5
            goto L_0x0da8
        L_0x07d5:
            r1 = 108(0x6c, float:1.51E-43)
            goto L_0x0da8
        L_0x07d9:
            java.lang.String r0 = "ir"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07e3
            goto L_0x0da8
        L_0x07e3:
            r1 = 107(0x6b, float:1.5E-43)
            goto L_0x0da8
        L_0x07e7:
            java.lang.String r0 = "iq"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07f1
            goto L_0x0da8
        L_0x07f1:
            r1 = 106(0x6a, float:1.49E-43)
            goto L_0x0da8
        L_0x07f5:
            java.lang.String r0 = "io"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x07ff
            goto L_0x0da8
        L_0x07ff:
            r1 = 105(0x69, float:1.47E-43)
            goto L_0x0da8
        L_0x0803:
            java.lang.String r0 = "in"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x080d
            goto L_0x0da8
        L_0x080d:
            r1 = 104(0x68, float:1.46E-43)
            goto L_0x0da8
        L_0x0811:
            java.lang.String r0 = "im"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x081b
            goto L_0x0da8
        L_0x081b:
            r1 = 103(0x67, float:1.44E-43)
            goto L_0x0da8
        L_0x081f:
            java.lang.String r0 = "il"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0829
            goto L_0x0da8
        L_0x0829:
            r1 = 102(0x66, float:1.43E-43)
            goto L_0x0da8
        L_0x082d:
            java.lang.String r0 = "ie"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0837
            goto L_0x0da8
        L_0x0837:
            r1 = 101(0x65, float:1.42E-43)
            goto L_0x0da8
        L_0x083b:
            java.lang.String r0 = "id"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0845
            goto L_0x0da8
        L_0x0845:
            r1 = 100
            goto L_0x0da8
        L_0x0849:
            java.lang.String r0 = "hu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0853
            goto L_0x0da8
        L_0x0853:
            r1 = 99
            goto L_0x0da8
        L_0x0857:
            java.lang.String r0 = "ht"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0861
            goto L_0x0da8
        L_0x0861:
            r1 = 98
            goto L_0x0da8
        L_0x0865:
            java.lang.String r0 = "hr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x086f
            goto L_0x0da8
        L_0x086f:
            r1 = 97
            goto L_0x0da8
        L_0x0873:
            java.lang.String r0 = "hn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x087d
            goto L_0x0da8
        L_0x087d:
            r1 = 96
            goto L_0x0da8
        L_0x0881:
            java.lang.String r0 = "hm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x088b
            goto L_0x0da8
        L_0x088b:
            r1 = 95
            goto L_0x0da8
        L_0x088f:
            java.lang.String r0 = "hk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0899
            goto L_0x0da8
        L_0x0899:
            r1 = 94
            goto L_0x0da8
        L_0x089d:
            java.lang.String r0 = "gy"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08a7
            goto L_0x0da8
        L_0x08a7:
            r1 = 93
            goto L_0x0da8
        L_0x08ab:
            java.lang.String r0 = "gw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08b5
            goto L_0x0da8
        L_0x08b5:
            r1 = 92
            goto L_0x0da8
        L_0x08b9:
            java.lang.String r0 = "gu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08c3
            goto L_0x0da8
        L_0x08c3:
            r1 = 91
            goto L_0x0da8
        L_0x08c7:
            java.lang.String r0 = "gt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08d1
            goto L_0x0da8
        L_0x08d1:
            r1 = 90
            goto L_0x0da8
        L_0x08d5:
            java.lang.String r0 = "gs"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08df
            goto L_0x0da8
        L_0x08df:
            r1 = 89
            goto L_0x0da8
        L_0x08e3:
            java.lang.String r0 = "gr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08ed
            goto L_0x0da8
        L_0x08ed:
            r1 = 88
            goto L_0x0da8
        L_0x08f1:
            java.lang.String r0 = "gq"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x08fb
            goto L_0x0da8
        L_0x08fb:
            r1 = 87
            goto L_0x0da8
        L_0x08ff:
            java.lang.String r0 = "gp"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0909
            goto L_0x0da8
        L_0x0909:
            r1 = 86
            goto L_0x0da8
        L_0x090d:
            java.lang.String r0 = "gn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0917
            goto L_0x0da8
        L_0x0917:
            r1 = 85
            goto L_0x0da8
        L_0x091b:
            java.lang.String r0 = "gm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0925
            goto L_0x0da8
        L_0x0925:
            r1 = 84
            goto L_0x0da8
        L_0x0929:
            java.lang.String r0 = "gl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0933
            goto L_0x0da8
        L_0x0933:
            r1 = 83
            goto L_0x0da8
        L_0x0937:
            java.lang.String r0 = "gi"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0941
            goto L_0x0da8
        L_0x0941:
            r1 = 82
            goto L_0x0da8
        L_0x0945:
            java.lang.String r0 = "gh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x094f
            goto L_0x0da8
        L_0x094f:
            r1 = 81
            goto L_0x0da8
        L_0x0953:
            java.lang.String r0 = "gg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x095d
            goto L_0x0da8
        L_0x095d:
            r1 = 80
            goto L_0x0da8
        L_0x0961:
            java.lang.String r0 = "gf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x096b
            goto L_0x0da8
        L_0x096b:
            r1 = 79
            goto L_0x0da8
        L_0x096f:
            java.lang.String r0 = "ge"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0979
            goto L_0x0da8
        L_0x0979:
            r1 = 78
            goto L_0x0da8
        L_0x097d:
            java.lang.String r0 = "gd"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0987
            goto L_0x0da8
        L_0x0987:
            r1 = 77
            goto L_0x0da8
        L_0x098b:
            java.lang.String r0 = "gb"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0995
            goto L_0x0da8
        L_0x0995:
            r1 = 76
            goto L_0x0da8
        L_0x0999:
            java.lang.String r0 = "ga"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09a3
            goto L_0x0da8
        L_0x09a3:
            r1 = 75
            goto L_0x0da8
        L_0x09a7:
            java.lang.String r0 = "fr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09b1
            goto L_0x0da8
        L_0x09b1:
            r1 = 74
            goto L_0x0da8
        L_0x09b5:
            java.lang.String r0 = "fo"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09bf
            goto L_0x0da8
        L_0x09bf:
            r1 = 73
            goto L_0x0da8
        L_0x09c3:
            java.lang.String r0 = "fm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09cd
            goto L_0x0da8
        L_0x09cd:
            r1 = 72
            goto L_0x0da8
        L_0x09d1:
            java.lang.String r0 = "fk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09db
            goto L_0x0da8
        L_0x09db:
            r1 = 71
            goto L_0x0da8
        L_0x09df:
            java.lang.String r0 = "fj"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09e9
            goto L_0x0da8
        L_0x09e9:
            r1 = 70
            goto L_0x0da8
        L_0x09ed:
            java.lang.String r0 = "fi"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x09f7
            goto L_0x0da8
        L_0x09f7:
            r1 = 69
            goto L_0x0da8
        L_0x09fb:
            java.lang.String r0 = "et"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a05
            goto L_0x0da8
        L_0x0a05:
            r1 = 68
            goto L_0x0da8
        L_0x0a09:
            java.lang.String r0 = "es"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a13
            goto L_0x0da8
        L_0x0a13:
            r1 = 67
            goto L_0x0da8
        L_0x0a17:
            java.lang.String r0 = "er"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a21
            goto L_0x0da8
        L_0x0a21:
            r1 = 66
            goto L_0x0da8
        L_0x0a25:
            java.lang.String r0 = "eh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a2f
            goto L_0x0da8
        L_0x0a2f:
            r1 = 65
            goto L_0x0da8
        L_0x0a33:
            java.lang.String r0 = "eg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a3d
            goto L_0x0da8
        L_0x0a3d:
            r1 = 64
            goto L_0x0da8
        L_0x0a41:
            java.lang.String r0 = "ee"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a4b
            goto L_0x0da8
        L_0x0a4b:
            r1 = 63
            goto L_0x0da8
        L_0x0a4f:
            java.lang.String r0 = "ec"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a59
            goto L_0x0da8
        L_0x0a59:
            r1 = 62
            goto L_0x0da8
        L_0x0a5d:
            java.lang.String r0 = "dz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a67
            goto L_0x0da8
        L_0x0a67:
            r1 = 61
            goto L_0x0da8
        L_0x0a6b:
            java.lang.String r0 = "do"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a75
            goto L_0x0da8
        L_0x0a75:
            r1 = 60
            goto L_0x0da8
        L_0x0a79:
            java.lang.String r0 = "dm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a83
            goto L_0x0da8
        L_0x0a83:
            r1 = 59
            goto L_0x0da8
        L_0x0a87:
            java.lang.String r0 = "dk"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a91
            goto L_0x0da8
        L_0x0a91:
            r1 = 58
            goto L_0x0da8
        L_0x0a95:
            java.lang.String r0 = "dj"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0a9f
            goto L_0x0da8
        L_0x0a9f:
            r1 = 57
            goto L_0x0da8
        L_0x0aa3:
            java.lang.String r0 = "de"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0aad
            goto L_0x0da8
        L_0x0aad:
            r1 = 56
            goto L_0x0da8
        L_0x0ab1:
            java.lang.String r0 = "cz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0abb
            goto L_0x0da8
        L_0x0abb:
            r1 = 55
            goto L_0x0da8
        L_0x0abf:
            java.lang.String r0 = "cy"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ac9
            goto L_0x0da8
        L_0x0ac9:
            r1 = 54
            goto L_0x0da8
        L_0x0acd:
            java.lang.String r0 = "cx"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ad7
            goto L_0x0da8
        L_0x0ad7:
            r1 = 53
            goto L_0x0da8
        L_0x0adb:
            java.lang.String r0 = "cw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ae5
            goto L_0x0da8
        L_0x0ae5:
            r1 = 52
            goto L_0x0da8
        L_0x0ae9:
            java.lang.String r0 = "cv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0af3
            goto L_0x0da8
        L_0x0af3:
            r1 = 51
            goto L_0x0da8
        L_0x0af7:
            java.lang.String r0 = "cu"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b01
            goto L_0x0da8
        L_0x0b01:
            r1 = 50
            goto L_0x0da8
        L_0x0b05:
            java.lang.String r0 = "cr"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b0f
            goto L_0x0da8
        L_0x0b0f:
            r1 = 49
            goto L_0x0da8
        L_0x0b13:
            java.lang.String r0 = "co"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b1d
            goto L_0x0da8
        L_0x0b1d:
            r1 = 48
            goto L_0x0da8
        L_0x0b21:
            java.lang.String r0 = "cn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b2b
            goto L_0x0da8
        L_0x0b2b:
            r1 = 47
            goto L_0x0da8
        L_0x0b2f:
            java.lang.String r0 = "cm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b39
            goto L_0x0da8
        L_0x0b39:
            r1 = 46
            goto L_0x0da8
        L_0x0b3d:
            java.lang.String r0 = "cl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b47
            goto L_0x0da8
        L_0x0b47:
            r1 = 45
            goto L_0x0da8
        L_0x0b4b:
            java.lang.String r0 = "ck"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b55
            goto L_0x0da8
        L_0x0b55:
            r1 = 44
            goto L_0x0da8
        L_0x0b59:
            java.lang.String r0 = "ci"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b63
            goto L_0x0da8
        L_0x0b63:
            r1 = 43
            goto L_0x0da8
        L_0x0b67:
            java.lang.String r0 = "ch"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b71
            goto L_0x0da8
        L_0x0b71:
            r1 = 42
            goto L_0x0da8
        L_0x0b75:
            java.lang.String r0 = "cg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b7f
            goto L_0x0da8
        L_0x0b7f:
            r1 = 41
            goto L_0x0da8
        L_0x0b83:
            java.lang.String r0 = "cf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b8d
            goto L_0x0da8
        L_0x0b8d:
            r1 = 40
            goto L_0x0da8
        L_0x0b91:
            java.lang.String r0 = "cd"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0b9b
            goto L_0x0da8
        L_0x0b9b:
            r1 = 39
            goto L_0x0da8
        L_0x0b9f:
            java.lang.String r0 = "cc"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ba9
            goto L_0x0da8
        L_0x0ba9:
            r1 = 38
            goto L_0x0da8
        L_0x0bad:
            java.lang.String r0 = "ca"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0bb7
            goto L_0x0da8
        L_0x0bb7:
            r1 = 37
            goto L_0x0da8
        L_0x0bbb:
            java.lang.String r0 = "bz"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0bc5
            goto L_0x0da8
        L_0x0bc5:
            r1 = 36
            goto L_0x0da8
        L_0x0bc9:
            java.lang.String r0 = "by"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0bd3
            goto L_0x0da8
        L_0x0bd3:
            r1 = 35
            goto L_0x0da8
        L_0x0bd7:
            java.lang.String r0 = "bw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0be1
            goto L_0x0da8
        L_0x0be1:
            r1 = 34
            goto L_0x0da8
        L_0x0be5:
            java.lang.String r0 = "bv"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0bef
            goto L_0x0da8
        L_0x0bef:
            r1 = 33
            goto L_0x0da8
        L_0x0bf3:
            java.lang.String r0 = "bt"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0bfd
            goto L_0x0da8
        L_0x0bfd:
            r1 = 32
            goto L_0x0da8
        L_0x0c01:
            java.lang.String r0 = "bs"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c0b
            goto L_0x0da8
        L_0x0c0b:
            r1 = 31
            goto L_0x0da8
        L_0x0c0f:
            java.lang.String r0 = "br"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c19
            goto L_0x0da8
        L_0x0c19:
            r1 = 30
            goto L_0x0da8
        L_0x0c1d:
            java.lang.String r0 = "bq"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c27
            goto L_0x0da8
        L_0x0c27:
            r1 = 29
            goto L_0x0da8
        L_0x0c2b:
            java.lang.String r0 = "bo"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c35
            goto L_0x0da8
        L_0x0c35:
            r1 = 28
            goto L_0x0da8
        L_0x0c39:
            java.lang.String r0 = "bn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c43
            goto L_0x0da8
        L_0x0c43:
            r1 = 27
            goto L_0x0da8
        L_0x0c47:
            java.lang.String r0 = "bm"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c51
            goto L_0x0da8
        L_0x0c51:
            r1 = 26
            goto L_0x0da8
        L_0x0c55:
            java.lang.String r0 = "bl"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c5f
            goto L_0x0da8
        L_0x0c5f:
            r1 = 25
            goto L_0x0da8
        L_0x0c63:
            java.lang.String r0 = "bj"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c6d
            goto L_0x0da8
        L_0x0c6d:
            r1 = 24
            goto L_0x0da8
        L_0x0c71:
            java.lang.String r0 = "bi"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c7b
            goto L_0x0da8
        L_0x0c7b:
            r1 = 23
            goto L_0x0da8
        L_0x0c7f:
            java.lang.String r0 = "bh"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c89
            goto L_0x0da8
        L_0x0c89:
            r1 = 22
            goto L_0x0da8
        L_0x0c8d:
            java.lang.String r0 = "bg"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0c97
            goto L_0x0da8
        L_0x0c97:
            r1 = 21
            goto L_0x0da8
        L_0x0c9b:
            java.lang.String r0 = "bf"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ca5
            goto L_0x0da8
        L_0x0ca5:
            r1 = 20
            goto L_0x0da8
        L_0x0ca9:
            java.lang.String r0 = "be"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0cb3
            goto L_0x0da8
        L_0x0cb3:
            r1 = 19
            goto L_0x0da8
        L_0x0cb7:
            java.lang.String r0 = "bd"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0cc1
            goto L_0x0da8
        L_0x0cc1:
            r1 = 18
            goto L_0x0da8
        L_0x0cc5:
            java.lang.String r0 = "bb"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ccf
            goto L_0x0da8
        L_0x0ccf:
            r1 = 17
            goto L_0x0da8
        L_0x0cd3:
            java.lang.String r0 = "ba"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0cdd
            goto L_0x0da8
        L_0x0cdd:
            r1 = 16
            goto L_0x0da8
        L_0x0ce1:
            java.lang.String r0 = "az"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0ceb
            goto L_0x0da8
        L_0x0ceb:
            r1 = 15
            goto L_0x0da8
        L_0x0cef:
            java.lang.String r0 = "ax"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0cf9
            goto L_0x0da8
        L_0x0cf9:
            r1 = 14
            goto L_0x0da8
        L_0x0cfd:
            java.lang.String r0 = "aw"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d07
            goto L_0x0da8
        L_0x0d07:
            r1 = 13
            goto L_0x0da8
        L_0x0d0b:
            java.lang.String r0 = "au"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d15
            goto L_0x0da8
        L_0x0d15:
            r1 = 12
            goto L_0x0da8
        L_0x0d19:
            java.lang.String r0 = "at"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d23
            goto L_0x0da8
        L_0x0d23:
            r1 = 11
            goto L_0x0da8
        L_0x0d27:
            java.lang.String r0 = "as"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d31
            goto L_0x0da8
        L_0x0d31:
            r1 = 10
            goto L_0x0da8
        L_0x0d35:
            java.lang.String r0 = "ar"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d3f
            goto L_0x0da8
        L_0x0d3f:
            r1 = 9
            goto L_0x0da8
        L_0x0d43:
            java.lang.String r0 = "aq"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d4d
            goto L_0x0da8
        L_0x0d4d:
            r1 = 8
            goto L_0x0da8
        L_0x0d51:
            java.lang.String r0 = "ao"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d5a
            goto L_0x0da8
        L_0x0d5a:
            r1 = 7
            goto L_0x0da8
        L_0x0d5c:
            java.lang.String r0 = "am"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d65
            goto L_0x0da8
        L_0x0d65:
            r1 = 6
            goto L_0x0da8
        L_0x0d67:
            java.lang.String r0 = "al"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d70
            goto L_0x0da8
        L_0x0d70:
            r1 = 5
            goto L_0x0da8
        L_0x0d72:
            java.lang.String r0 = "ai"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d7b
            goto L_0x0da8
        L_0x0d7b:
            r1 = 4
            goto L_0x0da8
        L_0x0d7d:
            java.lang.String r0 = "ag"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d86
            goto L_0x0da8
        L_0x0d86:
            r1 = 3
            goto L_0x0da8
        L_0x0d88:
            java.lang.String r0 = "af"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d91
            goto L_0x0da8
        L_0x0d91:
            r1 = 2
            goto L_0x0da8
        L_0x0d93:
            java.lang.String r0 = "ae"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0d9c
            goto L_0x0da8
        L_0x0d9c:
            r1 = 1
            goto L_0x0da8
        L_0x0d9e:
            java.lang.String r0 = "ad"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0da7
            goto L_0x0da8
        L_0x0da7:
            r1 = 0
        L_0x0da8:
            switch(r1) {
                case 0: goto L_0x1099;
                case 1: goto L_0x1096;
                case 2: goto L_0x1093;
                case 3: goto L_0x1090;
                case 4: goto L_0x108d;
                case 5: goto L_0x108a;
                case 6: goto L_0x1087;
                case 7: goto L_0x1084;
                case 8: goto L_0x1081;
                case 9: goto L_0x107e;
                case 10: goto L_0x107b;
                case 11: goto L_0x1078;
                case 12: goto L_0x1075;
                case 13: goto L_0x1072;
                case 14: goto L_0x106f;
                case 15: goto L_0x106c;
                case 16: goto L_0x1069;
                case 17: goto L_0x1066;
                case 18: goto L_0x1063;
                case 19: goto L_0x1060;
                case 20: goto L_0x105d;
                case 21: goto L_0x105a;
                case 22: goto L_0x1057;
                case 23: goto L_0x1054;
                case 24: goto L_0x1051;
                case 25: goto L_0x104e;
                case 26: goto L_0x104b;
                case 27: goto L_0x1048;
                case 28: goto L_0x1045;
                case 29: goto L_0x1042;
                case 30: goto L_0x103f;
                case 31: goto L_0x103c;
                case 32: goto L_0x1039;
                case 33: goto L_0x1036;
                case 34: goto L_0x1033;
                case 35: goto L_0x1030;
                case 36: goto L_0x102d;
                case 37: goto L_0x102a;
                case 38: goto L_0x1027;
                case 39: goto L_0x1024;
                case 40: goto L_0x1021;
                case 41: goto L_0x101e;
                case 42: goto L_0x101b;
                case 43: goto L_0x1018;
                case 44: goto L_0x1015;
                case 45: goto L_0x1012;
                case 46: goto L_0x100f;
                case 47: goto L_0x100c;
                case 48: goto L_0x1009;
                case 49: goto L_0x1006;
                case 50: goto L_0x1003;
                case 51: goto L_0x1000;
                case 52: goto L_0x0ffd;
                case 53: goto L_0x0ffa;
                case 54: goto L_0x0ff7;
                case 55: goto L_0x0ff4;
                case 56: goto L_0x0ff1;
                case 57: goto L_0x0fee;
                case 58: goto L_0x0feb;
                case 59: goto L_0x0fe8;
                case 60: goto L_0x0fe5;
                case 61: goto L_0x0fe2;
                case 62: goto L_0x0fdf;
                case 63: goto L_0x0fdc;
                case 64: goto L_0x0fd9;
                case 65: goto L_0x0fd6;
                case 66: goto L_0x0fd3;
                case 67: goto L_0x0fd0;
                case 68: goto L_0x0fcd;
                case 69: goto L_0x0fca;
                case 70: goto L_0x0fc7;
                case 71: goto L_0x0fc4;
                case 72: goto L_0x0fc1;
                case 73: goto L_0x0fbe;
                case 74: goto L_0x0fbb;
                case 75: goto L_0x0fb8;
                case 76: goto L_0x0fb5;
                case 77: goto L_0x0fb2;
                case 78: goto L_0x0faf;
                case 79: goto L_0x0fac;
                case 80: goto L_0x0fa9;
                case 81: goto L_0x0fa6;
                case 82: goto L_0x0fa3;
                case 83: goto L_0x0fa0;
                case 84: goto L_0x0f9d;
                case 85: goto L_0x0f9a;
                case 86: goto L_0x0f97;
                case 87: goto L_0x0f94;
                case 88: goto L_0x0f91;
                case 89: goto L_0x0f8e;
                case 90: goto L_0x0f8b;
                case 91: goto L_0x0f88;
                case 92: goto L_0x0f85;
                case 93: goto L_0x0f82;
                case 94: goto L_0x0f7f;
                case 95: goto L_0x0f7c;
                case 96: goto L_0x0f79;
                case 97: goto L_0x0f76;
                case 98: goto L_0x0f73;
                case 99: goto L_0x0f70;
                case 100: goto L_0x0f6d;
                case 101: goto L_0x0f6a;
                case 102: goto L_0x0f67;
                case 103: goto L_0x0f64;
                case 104: goto L_0x0f61;
                case 105: goto L_0x0f5e;
                case 106: goto L_0x0f5b;
                case 107: goto L_0x0f58;
                case 108: goto L_0x0f55;
                case 109: goto L_0x0f52;
                case 110: goto L_0x0f4f;
                case 111: goto L_0x0f4c;
                case 112: goto L_0x0f49;
                case 113: goto L_0x0f46;
                case 114: goto L_0x0f43;
                case 115: goto L_0x0f40;
                case 116: goto L_0x0f3d;
                case 117: goto L_0x0f3a;
                case 118: goto L_0x0f37;
                case 119: goto L_0x0f34;
                case 120: goto L_0x0f31;
                case 121: goto L_0x0f2e;
                case 122: goto L_0x0f2b;
                case 123: goto L_0x0f28;
                case 124: goto L_0x0f25;
                case 125: goto L_0x0f22;
                case 126: goto L_0x0f1f;
                case 127: goto L_0x0f1c;
                case 128: goto L_0x0f19;
                case 129: goto L_0x0f16;
                case 130: goto L_0x0f13;
                case 131: goto L_0x0f10;
                case 132: goto L_0x0f0d;
                case 133: goto L_0x0f0a;
                case 134: goto L_0x0f07;
                case 135: goto L_0x0f04;
                case 136: goto L_0x0f01;
                case 137: goto L_0x0efe;
                case 138: goto L_0x0efb;
                case 139: goto L_0x0ef8;
                case 140: goto L_0x0ef5;
                case 141: goto L_0x0ef2;
                case 142: goto L_0x0eef;
                case 143: goto L_0x0eec;
                case 144: goto L_0x0ee9;
                case 145: goto L_0x0ee6;
                case 146: goto L_0x0ee3;
                case 147: goto L_0x0ee0;
                case 148: goto L_0x0edd;
                case 149: goto L_0x0eda;
                case 150: goto L_0x0ed7;
                case 151: goto L_0x0ed4;
                case 152: goto L_0x0ed1;
                case 153: goto L_0x0ece;
                case 154: goto L_0x0ecb;
                case 155: goto L_0x0ec8;
                case 156: goto L_0x0ec5;
                case 157: goto L_0x0ec2;
                case 158: goto L_0x0ebf;
                case 159: goto L_0x0ebc;
                case 160: goto L_0x0eb9;
                case 161: goto L_0x0eb6;
                case 162: goto L_0x0eb3;
                case 163: goto L_0x0eb0;
                case 164: goto L_0x0ead;
                case 165: goto L_0x0eaa;
                case 166: goto L_0x0ea7;
                case 167: goto L_0x0ea4;
                case 168: goto L_0x0ea1;
                case 169: goto L_0x0e9e;
                case 170: goto L_0x0e9b;
                case 171: goto L_0x0e98;
                case 172: goto L_0x0e95;
                case 173: goto L_0x0e92;
                case 174: goto L_0x0e8f;
                case 175: goto L_0x0e8c;
                case 176: goto L_0x0e89;
                case 177: goto L_0x0e86;
                case 178: goto L_0x0e83;
                case 179: goto L_0x0e80;
                case 180: goto L_0x0e7d;
                case 181: goto L_0x0e7a;
                case 182: goto L_0x0e77;
                case 183: goto L_0x0e74;
                case 184: goto L_0x0e71;
                case 185: goto L_0x0e6e;
                case 186: goto L_0x0e6b;
                case 187: goto L_0x0e68;
                case 188: goto L_0x0e65;
                case 189: goto L_0x0e62;
                case 190: goto L_0x0e5f;
                case 191: goto L_0x0e5c;
                case 192: goto L_0x0e59;
                case 193: goto L_0x0e56;
                case 194: goto L_0x0e53;
                case 195: goto L_0x0e50;
                case 196: goto L_0x0e4d;
                case 197: goto L_0x0e4a;
                case 198: goto L_0x0e47;
                case 199: goto L_0x0e44;
                case 200: goto L_0x0e41;
                case 201: goto L_0x0e3e;
                case 202: goto L_0x0e3b;
                case 203: goto L_0x0e38;
                case 204: goto L_0x0e35;
                case 205: goto L_0x0e32;
                case 206: goto L_0x0e2f;
                case 207: goto L_0x0e2c;
                case 208: goto L_0x0e29;
                case 209: goto L_0x0e26;
                case 210: goto L_0x0e23;
                case 211: goto L_0x0e20;
                case 212: goto L_0x0e1d;
                case 213: goto L_0x0e1a;
                case 214: goto L_0x0e17;
                case 215: goto L_0x0e14;
                case 216: goto L_0x0e11;
                case 217: goto L_0x0e0e;
                case 218: goto L_0x0e0b;
                case 219: goto L_0x0e08;
                case 220: goto L_0x0e05;
                case 221: goto L_0x0e02;
                case 222: goto L_0x0dff;
                case 223: goto L_0x0dfc;
                case 224: goto L_0x0df9;
                case 225: goto L_0x0df6;
                case 226: goto L_0x0df3;
                case 227: goto L_0x0df0;
                case 228: goto L_0x0ded;
                case 229: goto L_0x0dea;
                case 230: goto L_0x0de7;
                case 231: goto L_0x0de4;
                case 232: goto L_0x0de1;
                case 233: goto L_0x0dde;
                case 234: goto L_0x0ddb;
                case 235: goto L_0x0dd8;
                case 236: goto L_0x0dd5;
                case 237: goto L_0x0dd2;
                case 238: goto L_0x0dcf;
                case 239: goto L_0x0dcc;
                case 240: goto L_0x0dc9;
                case 241: goto L_0x0dc6;
                case 242: goto L_0x0dc3;
                case 243: goto L_0x0dc0;
                case 244: goto L_0x0dbd;
                case 245: goto L_0x0dba;
                case 246: goto L_0x0db7;
                case 247: goto L_0x0db4;
                case 248: goto L_0x0db1;
                case 249: goto L_0x0dae;
                default: goto L_0x0dab;
            }
        L_0x0dab:
            java.lang.String r2 = " "
            return r2
        L_0x0dae:
            java.lang.String r2 = "🇿🇼"
            return r2
        L_0x0db1:
            java.lang.String r2 = "🇿🇲"
            return r2
        L_0x0db4:
            java.lang.String r2 = "🇿🇦"
            return r2
        L_0x0db7:
            java.lang.String r2 = "🇾🇹"
            return r2
        L_0x0dba:
            java.lang.String r2 = "🇾🇪"
            return r2
        L_0x0dbd:
            java.lang.String r2 = "🇽🇰"
            return r2
        L_0x0dc0:
            java.lang.String r2 = "🇼🇸"
            return r2
        L_0x0dc3:
            java.lang.String r2 = "🇼🇫"
            return r2
        L_0x0dc6:
            java.lang.String r2 = "🇻🇺"
            return r2
        L_0x0dc9:
            java.lang.String r2 = "🇻🇳"
            return r2
        L_0x0dcc:
            java.lang.String r2 = "🇻🇮"
            return r2
        L_0x0dcf:
            java.lang.String r2 = "🇻🇬"
            return r2
        L_0x0dd2:
            java.lang.String r2 = "🇻🇪"
            return r2
        L_0x0dd5:
            java.lang.String r2 = "🇻🇨"
            return r2
        L_0x0dd8:
            java.lang.String r2 = "🇻🇦"
            return r2
        L_0x0ddb:
            java.lang.String r2 = "🇺🇿"
            return r2
        L_0x0dde:
            java.lang.String r2 = "🇺🇾"
            return r2
        L_0x0de1:
            java.lang.String r2 = "🇺🇸"
            return r2
        L_0x0de4:
            java.lang.String r2 = "🇺🇲"
            return r2
        L_0x0de7:
            java.lang.String r2 = "🇺🇬"
            return r2
        L_0x0dea:
            java.lang.String r2 = "🇺🇦"
            return r2
        L_0x0ded:
            java.lang.String r2 = "🇹🇿"
            return r2
        L_0x0df0:
            java.lang.String r2 = "🇹🇼"
            return r2
        L_0x0df3:
            java.lang.String r2 = "🇹🇻"
            return r2
        L_0x0df6:
            java.lang.String r2 = "🇹🇹"
            return r2
        L_0x0df9:
            java.lang.String r2 = "🇹🇷"
            return r2
        L_0x0dfc:
            java.lang.String r2 = "🇹🇴"
            return r2
        L_0x0dff:
            java.lang.String r2 = "🇹🇳"
            return r2
        L_0x0e02:
            java.lang.String r2 = "🇹🇲"
            return r2
        L_0x0e05:
            java.lang.String r2 = "🇹🇱"
            return r2
        L_0x0e08:
            java.lang.String r2 = "🇹🇰"
            return r2
        L_0x0e0b:
            java.lang.String r2 = "🇹🇯"
            return r2
        L_0x0e0e:
            java.lang.String r2 = "🇹🇭"
            return r2
        L_0x0e11:
            java.lang.String r2 = "🇹🇬"
            return r2
        L_0x0e14:
            java.lang.String r2 = "🇹🇫"
            return r2
        L_0x0e17:
            java.lang.String r2 = "🇹🇩"
            return r2
        L_0x0e1a:
            java.lang.String r2 = "🇹🇨"
            return r2
        L_0x0e1d:
            java.lang.String r2 = "🇸🇿"
            return r2
        L_0x0e20:
            java.lang.String r2 = "🇸🇾"
            return r2
        L_0x0e23:
            java.lang.String r2 = "🇸🇽"
            return r2
        L_0x0e26:
            java.lang.String r2 = "🇸🇻"
            return r2
        L_0x0e29:
            java.lang.String r2 = "🇸🇹"
            return r2
        L_0x0e2c:
            java.lang.String r2 = "🇸🇸"
            return r2
        L_0x0e2f:
            java.lang.String r2 = "🇸🇷"
            return r2
        L_0x0e32:
            java.lang.String r2 = "🇸🇴"
            return r2
        L_0x0e35:
            java.lang.String r2 = "🇸🇳"
            return r2
        L_0x0e38:
            java.lang.String r2 = "🇸🇲"
            return r2
        L_0x0e3b:
            java.lang.String r2 = "🇸🇱"
            return r2
        L_0x0e3e:
            java.lang.String r2 = "🇸🇰"
            return r2
        L_0x0e41:
            java.lang.String r2 = "🇸🇯"
            return r2
        L_0x0e44:
            java.lang.String r2 = "🇸🇮"
            return r2
        L_0x0e47:
            java.lang.String r2 = "🇸🇭"
            return r2
        L_0x0e4a:
            java.lang.String r2 = "🇸🇬"
            return r2
        L_0x0e4d:
            java.lang.String r2 = "🇸🇪"
            return r2
        L_0x0e50:
            java.lang.String r2 = "🇸🇩"
            return r2
        L_0x0e53:
            java.lang.String r2 = "🇸🇨"
            return r2
        L_0x0e56:
            java.lang.String r2 = "🇸🇧"
            return r2
        L_0x0e59:
            java.lang.String r2 = "🇸🇦"
            return r2
        L_0x0e5c:
            java.lang.String r2 = "🇷🇼"
            return r2
        L_0x0e5f:
            java.lang.String r2 = "🇷🇺"
            return r2
        L_0x0e62:
            java.lang.String r2 = "🇷🇸"
            return r2
        L_0x0e65:
            java.lang.String r2 = "🇷🇴"
            return r2
        L_0x0e68:
            java.lang.String r2 = "🇷🇪"
            return r2
        L_0x0e6b:
            java.lang.String r2 = "🇶🇦"
            return r2
        L_0x0e6e:
            java.lang.String r2 = "🇵🇾"
            return r2
        L_0x0e71:
            java.lang.String r2 = "🇵🇼"
            return r2
        L_0x0e74:
            java.lang.String r2 = "🇵🇹"
            return r2
        L_0x0e77:
            java.lang.String r2 = "🇵🇸"
            return r2
        L_0x0e7a:
            java.lang.String r2 = "🇵🇷"
            return r2
        L_0x0e7d:
            java.lang.String r2 = "🇵🇳"
            return r2
        L_0x0e80:
            java.lang.String r2 = "🇵🇲"
            return r2
        L_0x0e83:
            java.lang.String r2 = "🇵🇱"
            return r2
        L_0x0e86:
            java.lang.String r2 = "🇵🇰"
            return r2
        L_0x0e89:
            java.lang.String r2 = "🇵🇭"
            return r2
        L_0x0e8c:
            java.lang.String r2 = "🇵🇬"
            return r2
        L_0x0e8f:
            java.lang.String r2 = "🇵🇫"
            return r2
        L_0x0e92:
            java.lang.String r2 = "🇵🇪"
            return r2
        L_0x0e95:
            java.lang.String r2 = "🇵🇦"
            return r2
        L_0x0e98:
            java.lang.String r2 = "🇴🇲"
            return r2
        L_0x0e9b:
            java.lang.String r2 = "🇳🇿"
            return r2
        L_0x0e9e:
            java.lang.String r2 = "🇳🇺"
            return r2
        L_0x0ea1:
            java.lang.String r2 = "🇳🇷"
            return r2
        L_0x0ea4:
            java.lang.String r2 = "🇳🇵"
            return r2
        L_0x0ea7:
            java.lang.String r2 = "🇳🇴"
            return r2
        L_0x0eaa:
            java.lang.String r2 = "🇳🇱"
            return r2
        L_0x0ead:
            java.lang.String r2 = "🇳🇮"
            return r2
        L_0x0eb0:
            java.lang.String r2 = "🇳🇬"
            return r2
        L_0x0eb3:
            java.lang.String r2 = "🇳🇫"
            return r2
        L_0x0eb6:
            java.lang.String r2 = "🇳🇪"
            return r2
        L_0x0eb9:
            java.lang.String r2 = "🇳🇨"
            return r2
        L_0x0ebc:
            java.lang.String r2 = "🇳🇦"
            return r2
        L_0x0ebf:
            java.lang.String r2 = "🇲🇿"
            return r2
        L_0x0ec2:
            java.lang.String r2 = "🇲🇾"
            return r2
        L_0x0ec5:
            java.lang.String r2 = "🇲🇽"
            return r2
        L_0x0ec8:
            java.lang.String r2 = "🇲🇼"
            return r2
        L_0x0ecb:
            java.lang.String r2 = "🇲🇻"
            return r2
        L_0x0ece:
            java.lang.String r2 = "🇲🇺"
            return r2
        L_0x0ed1:
            java.lang.String r2 = "🇲🇹"
            return r2
        L_0x0ed4:
            java.lang.String r2 = "🇲🇸"
            return r2
        L_0x0ed7:
            java.lang.String r2 = "🇲🇷"
            return r2
        L_0x0eda:
            java.lang.String r2 = "🇲🇶"
            return r2
        L_0x0edd:
            java.lang.String r2 = "🇲🇵"
            return r2
        L_0x0ee0:
            java.lang.String r2 = "🇲🇴"
            return r2
        L_0x0ee3:
            java.lang.String r2 = "🇲🇳"
            return r2
        L_0x0ee6:
            java.lang.String r2 = "🇲🇲"
            return r2
        L_0x0ee9:
            java.lang.String r2 = "🇲🇱"
            return r2
        L_0x0eec:
            java.lang.String r2 = "🇲🇰"
            return r2
        L_0x0eef:
            java.lang.String r2 = "🇲🇭"
            return r2
        L_0x0ef2:
            java.lang.String r2 = "🇲🇬"
            return r2
        L_0x0ef5:
            java.lang.String r2 = "🇲🇫"
            return r2
        L_0x0ef8:
            java.lang.String r2 = "🇲🇪"
            return r2
        L_0x0efb:
            java.lang.String r2 = "🇲🇩"
            return r2
        L_0x0efe:
            java.lang.String r2 = "🇲🇨"
            return r2
        L_0x0f01:
            java.lang.String r2 = "🇲🇦"
            return r2
        L_0x0f04:
            java.lang.String r2 = "🇱🇾"
            return r2
        L_0x0f07:
            java.lang.String r2 = "🇱🇻"
            return r2
        L_0x0f0a:
            java.lang.String r2 = "🇱🇺"
            return r2
        L_0x0f0d:
            java.lang.String r2 = "🇱🇹"
            return r2
        L_0x0f10:
            java.lang.String r2 = "🇱🇸"
            return r2
        L_0x0f13:
            java.lang.String r2 = "🇱🇷"
            return r2
        L_0x0f16:
            java.lang.String r2 = "🇱🇰"
            return r2
        L_0x0f19:
            java.lang.String r2 = "🇱🇮"
            return r2
        L_0x0f1c:
            java.lang.String r2 = "🇱🇨"
            return r2
        L_0x0f1f:
            java.lang.String r2 = "🇱🇧"
            return r2
        L_0x0f22:
            java.lang.String r2 = "🇱🇦"
            return r2
        L_0x0f25:
            java.lang.String r2 = "🇰🇿"
            return r2
        L_0x0f28:
            java.lang.String r2 = "🇰🇾"
            return r2
        L_0x0f2b:
            java.lang.String r2 = "🇰🇼"
            return r2
        L_0x0f2e:
            java.lang.String r2 = "🇰🇷"
            return r2
        L_0x0f31:
            java.lang.String r2 = "🇰🇵"
            return r2
        L_0x0f34:
            java.lang.String r2 = "🇰🇳"
            return r2
        L_0x0f37:
            java.lang.String r2 = "🇰🇲"
            return r2
        L_0x0f3a:
            java.lang.String r2 = "🇰🇮"
            return r2
        L_0x0f3d:
            java.lang.String r2 = "🇰🇭"
            return r2
        L_0x0f40:
            java.lang.String r2 = "🇰🇬"
            return r2
        L_0x0f43:
            java.lang.String r2 = "🇰🇪"
            return r2
        L_0x0f46:
            java.lang.String r2 = "🇯🇵"
            return r2
        L_0x0f49:
            java.lang.String r2 = "🇯🇴"
            return r2
        L_0x0f4c:
            java.lang.String r2 = "🇯🇲"
            return r2
        L_0x0f4f:
            java.lang.String r2 = "🇯🇪"
            return r2
        L_0x0f52:
            java.lang.String r2 = "🇮🇹"
            return r2
        L_0x0f55:
            java.lang.String r2 = "🇮🇸"
            return r2
        L_0x0f58:
            java.lang.String r2 = "🇮🇷"
            return r2
        L_0x0f5b:
            java.lang.String r2 = "🇮🇶"
            return r2
        L_0x0f5e:
            java.lang.String r2 = "🇮🇴"
            return r2
        L_0x0f61:
            java.lang.String r2 = "🇮🇳"
            return r2
        L_0x0f64:
            java.lang.String r2 = "🇮🇲"
            return r2
        L_0x0f67:
            java.lang.String r2 = "🇮🇱"
            return r2
        L_0x0f6a:
            java.lang.String r2 = "🇮🇪"
            return r2
        L_0x0f6d:
            java.lang.String r2 = "🇮🇩"
            return r2
        L_0x0f70:
            java.lang.String r2 = "🇭🇺"
            return r2
        L_0x0f73:
            java.lang.String r2 = "🇭🇹"
            return r2
        L_0x0f76:
            java.lang.String r2 = "🇭🇷"
            return r2
        L_0x0f79:
            java.lang.String r2 = "🇭🇳"
            return r2
        L_0x0f7c:
            java.lang.String r2 = "🇭🇲"
            return r2
        L_0x0f7f:
            java.lang.String r2 = "🇭🇰"
            return r2
        L_0x0f82:
            java.lang.String r2 = "🇬🇾"
            return r2
        L_0x0f85:
            java.lang.String r2 = "🇬🇼"
            return r2
        L_0x0f88:
            java.lang.String r2 = "🇬🇺"
            return r2
        L_0x0f8b:
            java.lang.String r2 = "🇬🇹"
            return r2
        L_0x0f8e:
            java.lang.String r2 = "🇬🇸"
            return r2
        L_0x0f91:
            java.lang.String r2 = "🇬🇷"
            return r2
        L_0x0f94:
            java.lang.String r2 = "🇬🇶"
            return r2
        L_0x0f97:
            java.lang.String r2 = "🇬🇵"
            return r2
        L_0x0f9a:
            java.lang.String r2 = "🇬🇳"
            return r2
        L_0x0f9d:
            java.lang.String r2 = "🇬🇲"
            return r2
        L_0x0fa0:
            java.lang.String r2 = "🇬🇱"
            return r2
        L_0x0fa3:
            java.lang.String r2 = "🇬🇮"
            return r2
        L_0x0fa6:
            java.lang.String r2 = "🇬🇭"
            return r2
        L_0x0fa9:
            java.lang.String r2 = "🇬🇬"
            return r2
        L_0x0fac:
            java.lang.String r2 = "🇬🇫"
            return r2
        L_0x0faf:
            java.lang.String r2 = "🇬🇪"
            return r2
        L_0x0fb2:
            java.lang.String r2 = "🇬🇩"
            return r2
        L_0x0fb5:
            java.lang.String r2 = "🇬🇧"
            return r2
        L_0x0fb8:
            java.lang.String r2 = "🇬🇦"
            return r2
        L_0x0fbb:
            java.lang.String r2 = "🇫🇷"
            return r2
        L_0x0fbe:
            java.lang.String r2 = "🇫🇴"
            return r2
        L_0x0fc1:
            java.lang.String r2 = "🇫🇲"
            return r2
        L_0x0fc4:
            java.lang.String r2 = "🇫🇰"
            return r2
        L_0x0fc7:
            java.lang.String r2 = "🇫🇯"
            return r2
        L_0x0fca:
            java.lang.String r2 = "🇫🇮"
            return r2
        L_0x0fcd:
            java.lang.String r2 = "🇪🇹"
            return r2
        L_0x0fd0:
            java.lang.String r2 = "🇪🇸"
            return r2
        L_0x0fd3:
            java.lang.String r2 = "🇪🇷"
            return r2
        L_0x0fd6:
            java.lang.String r2 = "🇪🇭"
            return r2
        L_0x0fd9:
            java.lang.String r2 = "🇪🇬"
            return r2
        L_0x0fdc:
            java.lang.String r2 = "🇪🇪"
            return r2
        L_0x0fdf:
            java.lang.String r2 = "🇪🇨"
            return r2
        L_0x0fe2:
            java.lang.String r2 = "🇩🇿"
            return r2
        L_0x0fe5:
            java.lang.String r2 = "🇩🇴"
            return r2
        L_0x0fe8:
            java.lang.String r2 = "🇩🇲"
            return r2
        L_0x0feb:
            java.lang.String r2 = "🇩🇰"
            return r2
        L_0x0fee:
            java.lang.String r2 = "🇩🇯"
            return r2
        L_0x0ff1:
            java.lang.String r2 = "🇩🇪"
            return r2
        L_0x0ff4:
            java.lang.String r2 = "🇨🇿"
            return r2
        L_0x0ff7:
            java.lang.String r2 = "🇨🇾"
            return r2
        L_0x0ffa:
            java.lang.String r2 = "🇨🇽"
            return r2
        L_0x0ffd:
            java.lang.String r2 = "🇨🇼"
            return r2
        L_0x1000:
            java.lang.String r2 = "🇨🇻"
            return r2
        L_0x1003:
            java.lang.String r2 = "🇨🇺"
            return r2
        L_0x1006:
            java.lang.String r2 = "🇨🇷"
            return r2
        L_0x1009:
            java.lang.String r2 = "🇨🇴"
            return r2
        L_0x100c:
            java.lang.String r2 = "🇨🇳"
            return r2
        L_0x100f:
            java.lang.String r2 = "🇨🇲"
            return r2
        L_0x1012:
            java.lang.String r2 = "🇨🇱"
            return r2
        L_0x1015:
            java.lang.String r2 = "🇨🇰"
            return r2
        L_0x1018:
            java.lang.String r2 = "🇨🇮"
            return r2
        L_0x101b:
            java.lang.String r2 = "🇨🇭"
            return r2
        L_0x101e:
            java.lang.String r2 = "🇨🇬"
            return r2
        L_0x1021:
            java.lang.String r2 = "🇨🇫"
            return r2
        L_0x1024:
            java.lang.String r2 = "🇨🇩"
            return r2
        L_0x1027:
            java.lang.String r2 = "🇨🇨"
            return r2
        L_0x102a:
            java.lang.String r2 = "🇨🇦"
            return r2
        L_0x102d:
            java.lang.String r2 = "🇧🇿"
            return r2
        L_0x1030:
            java.lang.String r2 = "🇧🇾"
            return r2
        L_0x1033:
            java.lang.String r2 = "🇧🇼"
            return r2
        L_0x1036:
            java.lang.String r2 = "🇧🇻"
            return r2
        L_0x1039:
            java.lang.String r2 = "🇧🇹"
            return r2
        L_0x103c:
            java.lang.String r2 = "🇧🇸"
            return r2
        L_0x103f:
            java.lang.String r2 = "🇧🇷"
            return r2
        L_0x1042:
            java.lang.String r2 = "🇧🇶"
            return r2
        L_0x1045:
            java.lang.String r2 = "🇧🇴"
            return r2
        L_0x1048:
            java.lang.String r2 = "🇧🇳"
            return r2
        L_0x104b:
            java.lang.String r2 = "🇧🇲"
            return r2
        L_0x104e:
            java.lang.String r2 = "🇧🇱"
            return r2
        L_0x1051:
            java.lang.String r2 = "🇧🇯"
            return r2
        L_0x1054:
            java.lang.String r2 = "🇧🇮"
            return r2
        L_0x1057:
            java.lang.String r2 = "🇧🇭"
            return r2
        L_0x105a:
            java.lang.String r2 = "🇧🇬"
            return r2
        L_0x105d:
            java.lang.String r2 = "🇧🇫"
            return r2
        L_0x1060:
            java.lang.String r2 = "🇧🇪"
            return r2
        L_0x1063:
            java.lang.String r2 = "🇧🇩"
            return r2
        L_0x1066:
            java.lang.String r2 = "🇧🇧"
            return r2
        L_0x1069:
            java.lang.String r2 = "🇧🇦"
            return r2
        L_0x106c:
            java.lang.String r2 = "🇦🇿"
            return r2
        L_0x106f:
            java.lang.String r2 = "🇦🇽"
            return r2
        L_0x1072:
            java.lang.String r2 = "🇦🇼"
            return r2
        L_0x1075:
            java.lang.String r2 = "🇦🇺"
            return r2
        L_0x1078:
            java.lang.String r2 = "🇦🇹"
            return r2
        L_0x107b:
            java.lang.String r2 = "🇦🇸"
            return r2
        L_0x107e:
            java.lang.String r2 = "🇦🇷"
            return r2
        L_0x1081:
            java.lang.String r2 = "🇦🇶"
            return r2
        L_0x1084:
            java.lang.String r2 = "🇦🇴"
            return r2
        L_0x1087:
            java.lang.String r2 = "🇦🇲"
            return r2
        L_0x108a:
            java.lang.String r2 = "🇦🇱"
            return r2
        L_0x108d:
            java.lang.String r2 = "🇦🇮"
            return r2
        L_0x1090:
            java.lang.String r2 = "🇦🇬"
            return r2
        L_0x1093:
            java.lang.String r2 = "🇦🇫"
            return r2
        L_0x1096:
            java.lang.String r2 = "🇦🇪"
            return r2
        L_0x1099:
            java.lang.String r2 = "🇦🇩"
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hbb20.CCPCountry.getFlagEmoji(com.hbb20.CCPCountry):java.lang.String");
    }

    public static List<CCPCountry> getLibraryMasterCountryList(Context context, CountryCodePicker.Language language) {
        List<CCPCountry> list;
        CountryCodePicker.Language language2 = loadedLibraryMasterListLanguage;
        if (language2 == null || language != language2 || (list = loadedLibraryMaterList) == null || list.size() == 0) {
            loadDataFromXML(context, language);
        }
        return loadedLibraryMaterList;
    }

    public static List<CCPCountry> getLibraryMasterCountriesEnglish() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CCPCountry("ad", "376", "Andorra", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ae", "971", "United Arab Emirates (UAE)", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("af", "93", "Afghanistan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ag", "1", "Antigua and Barbuda", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ai", "1", "Anguilla", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("al", "355", "Albania", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("am", "374", "Armenia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ao", "244", "Angola", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("aq", "672", "Antarctica", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ar", "54", "Argentina", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("as", "1", "American Samoa", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("at", "43", "Austria", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("au", "61", "Australia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("aw", "297", "Aruba", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ax", "358", "Åland Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("az", "994", "Azerbaijan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ba", "387", "Bosnia And Herzegovina", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bb", "1", "Barbados", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bd", "880", "Bangladesh", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("be", "32", "Belgium", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bf", "226", "Burkina Faso", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bg", "359", "Bulgaria", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bh", "973", "Bahrain", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bi", "257", "Burundi", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bj", "229", "Benin", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bl", "590", "Saint Barthélemy", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bm", "1", "Bermuda", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bn", "673", "Brunei Darussalam", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bo", "591", "Bolivia, Plurinational State Of", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("br", "55", "Brazil", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bs", "1", "Bahamas", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bt", "975", "Bhutan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bw", "267", "Botswana", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("by", "375", "Belarus", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("bz", "501", "Belize", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ca", "1", "Canada", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cc", "61", "Cocos (keeling) Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cd", "243", "Congo, The Democratic Republic Of The", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cf", "236", "Central African Republic", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cg", "242", "Congo", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ch", "41", "Switzerland", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ci", "225", "Côte D'ivoire", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ck", "682", "Cook Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cl", "56", "Chile", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cm", "237", "Cameroon", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cn", "86", "China", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("co", "57", "Colombia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cr", "506", "Costa Rica", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cu", "53", "Cuba", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cv", "238", "Cape Verde", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cw", "599", "Curaçao", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cx", "61", "Christmas Island", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cy", "357", "Cyprus", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("cz", "420", "Czech Republic", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("de", "49", "Germany", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("dj", "253", "Djibouti", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("dk", "45", "Denmark", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("dm", "1", "Dominica", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("do", "1", "Dominican Republic", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("dz", "213", "Algeria", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ec", "593", "Ecuador", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ee", "372", "Estonia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("eg", "20", "Egypt", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("er", "291", "Eritrea", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("es", "34", "Spain", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("et", "251", "Ethiopia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("fi", "358", "Finland", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("fj", "679", "Fiji", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("fk", "500", "Falkland Islands (malvinas)", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("fm", "691", "Micronesia, Federated States Of", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("fo", "298", "Faroe Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("fr", "33", "France", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ga", "241", "Gabon", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gb", "44", "United Kingdom", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gd", "1", "Grenada", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ge", "995", "Georgia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gf", "594", "French Guyana", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gh", "233", "Ghana", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gi", "350", "Gibraltar", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gl", "299", "Greenland", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gm", "220", "Gambia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gn", "224", "Guinea", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gp", "450", "Guadeloupe", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gq", "240", "Equatorial Guinea", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gr", "30", "Greece", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gt", "502", "Guatemala", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gu", "1", "Guam", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gw", "245", "Guinea-bissau", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("gy", "592", "Guyana", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("hk", "852", "Hong Kong", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("hn", "504", "Honduras", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("hr", "385", "Croatia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ht", "509", "Haiti", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("hu", "36", "Hungary", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("id", "62", "Indonesia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ie", "353", "Ireland", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("il", "972", "Israel", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("im", "44", "Isle Of Man", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("is", "354", "Iceland", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("in", "91", "India", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("io", "246", "British Indian Ocean Territory", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("iq", "964", "Iraq", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ir", "98", "Iran, Islamic Republic Of", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("it", "39", "Italy", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("je", "44", "Jersey ", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("jm", "1", "Jamaica", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("jo", "962", "Jordan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("jp", "81", "Japan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ke", "254", "Kenya", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("kg", "996", "Kyrgyzstan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("kh", "855", "Cambodia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ki", "686", "Kiribati", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("km", "269", "Comoros", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("kn", "1", "Saint Kitts and Nevis", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("kp", "850", "North Korea", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("kr", "82", "South Korea", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("kw", "965", "Kuwait", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ky", "1", "Cayman Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("kz", "7", "Kazakhstan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("la", "856", "Lao People's Democratic Republic", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("lb", "961", "Lebanon", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("lc", "1", "Saint Lucia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("li", "423", "Liechtenstein", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("lk", "94", "Sri Lanka", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("lr", "231", "Liberia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ls", "266", "Lesotho", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("lt", "370", "Lithuania", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("lu", "352", "Luxembourg", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("lv", "371", "Latvia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ly", "218", "Libya", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ma", "212", "Morocco", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mc", "377", "Monaco", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("md", "373", "Moldova, Republic Of", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("me", "382", "Montenegro", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mf", "590", "Saint Martin", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mg", "261", "Madagascar", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mh", "692", "Marshall Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mk", "389", "Macedonia (FYROM)", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ml", "223", "Mali", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mm", "95", "Myanmar", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mn", "976", "Mongolia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mo", "853", "Macau", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mp", "1", "Northern Mariana Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mq", "596", "Martinique", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mr", "222", "Mauritania", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ms", "1", "Montserrat", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mt", "356", "Malta", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mu", "230", "Mauritius", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mv", "960", "Maldives", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mw", "265", "Malawi", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mx", "52", "Mexico", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("my", "60", "Malaysia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("mz", "258", "Mozambique", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("na", "264", "Namibia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("nc", "687", "New Caledonia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ne", "227", "Niger", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("nf", "672", "Norfolk Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ng", "234", "Nigeria", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ni", "505", "Nicaragua", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("nl", "31", "Netherlands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("no", "47", "Norway", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("np", "977", "Nepal", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("nr", "674", "Nauru", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("nu", "683", "Niue", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("nz", "64", "New Zealand", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("om", "968", "Oman", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pa", "507", "Panama", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pe", "51", "Peru", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pf", "689", "French Polynesia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pg", "675", "Papua New Guinea", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ph", "63", "Philippines", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pk", "92", "Pakistan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pl", "48", "Poland", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pm", "508", "Saint Pierre And Miquelon", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pn", "870", "Pitcairn Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pr", "1", "Puerto Rico", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ps", "970", "Palestine", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pt", "351", "Portugal", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("pw", "680", "Palau", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("py", "595", "Paraguay", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("qa", "974", "Qatar", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("re", "262", "Réunion", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ro", "40", "Romania", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("rs", "381", "Serbia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ru", "7", "Russian Federation", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("rw", "250", "Rwanda", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sa", "966", "Saudi Arabia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sb", "677", "Solomon Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sc", "248", "Seychelles", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sd", "249", "Sudan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("se", "46", "Sweden", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sg", "65", "Singapore", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sh", "290", "Saint Helena, Ascension And Tristan Da Cunha", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("si", "386", "Slovenia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sk", "421", "Slovakia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sl", "232", "Sierra Leone", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sm", "378", "San Marino", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sn", "221", "Senegal", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("so", "252", "Somalia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sr", "597", "Suriname", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ss", "211", "South Sudan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("st", "239", "Sao Tome And Principe", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sv", "503", "El Salvador", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sx", "1", "Sint Maarten", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sy", "963", "Syrian Arab Republic", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("sz", "268", "Swaziland", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tc", "1", "Turks and Caicos Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("td", "235", "Chad", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tg", "228", "Togo", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("th", "66", "Thailand", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tj", "992", "Tajikistan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tk", "690", "Tokelau", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tl", "670", "Timor-leste", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tm", "993", "Turkmenistan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tn", "216", "Tunisia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("to", "676", "Tonga", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tr", "90", "Turkey", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tt", "1", "Trinidad &amp; Tobago", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tv", "688", "Tuvalu", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tw", "886", "Taiwan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("tz", "255", "Tanzania, United Republic Of", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ua", "380", "Ukraine", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ug", "256", "Uganda", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("us", "1", "United States", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("uy", "598", "Uruguay", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("uz", "998", "Uzbekistan", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("va", "379", "Holy See (vatican City State)", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("vc", "1", "Saint Vincent &amp; The Grenadines", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ve", "58", "Venezuela, Bolivarian Republic Of", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("vg", "1", "British Virgin Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("vi", "1", "US Virgin Islands", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("vn", "84", "Vietnam", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("vu", "678", "Vanuatu", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("wf", "681", "Wallis And Futuna", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ws", "685", "Samoa", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("xk", "383", "Kosovo", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("ye", "967", "Yemen", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("yt", "262", "Mayotte", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("za", "27", "South Africa", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("zm", "260", "Zambia", DEFAULT_FLAG_RES));
        arrayList.add(new CCPCountry("zw", "263", "Zimbabwe", DEFAULT_FLAG_RES));
        return arrayList;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public void setEnglishName(String str) {
        this.englishName = str;
    }

    public int getFlagID() {
        if (this.flagResID == -99) {
            this.flagResID = getFlagMasterResID(this);
        }
        return this.flagResID;
    }

    public String getNameCode() {
        return this.nameCode;
    }

    public void setNameCode(String str) {
        this.nameCode = str;
    }

    public String getPhoneCode() {
        return this.phoneCode;
    }

    public void setPhoneCode(String str) {
        this.phoneCode = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void log() {
        try {
            String str = TAG;
            Log.d(str, "Country->" + this.nameCode + ":" + this.phoneCode + ":" + this.name);
        } catch (NullPointerException unused) {
            Log.d(TAG, "Null");
        }
    }

    /* access modifiers changed from: package-private */
    public String logString() {
        return this.nameCode.toUpperCase() + " +" + this.phoneCode + "(" + this.name + ")";
    }

    /* access modifiers changed from: package-private */
    public boolean isEligibleForQuery(String str) {
        String lowerCase = str.toLowerCase();
        return containsQueryWord("Name", getName(), lowerCase) || containsQueryWord("NameCode", getNameCode(), lowerCase) || containsQueryWord("PhoneCode", getPhoneCode(), lowerCase) || containsQueryWord("EnglishName", getEnglishName(), lowerCase);
    }

    private boolean containsQueryWord(String str, String str2, String str3) {
        if (!(str2 == null || str3 == null)) {
            try {
                return str2.toLowerCase(Locale.ROOT).contains(str3);
            } catch (Exception unused) {
                Log.w("CCPCountry", str + ":" + str2 + " failed to execute toLowerCase(Locale.ROOT).contains(query) for query:" + str3);
            }
        }
        return false;
    }

    public int compareTo(CCPCountry cCPCountry) {
        return Collator.getInstance().compare(getName(), cCPCountry.getName());
    }
}
