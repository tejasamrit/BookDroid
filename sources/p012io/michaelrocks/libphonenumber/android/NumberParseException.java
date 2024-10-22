package p012io.michaelrocks.libphonenumber.android;

/* renamed from: io.michaelrocks.libphonenumber.android.NumberParseException */
public class NumberParseException extends Exception {
    private ErrorType errorType;
    private String message;

    /* renamed from: io.michaelrocks.libphonenumber.android.NumberParseException$ErrorType */
    public enum ErrorType {
        INVALID_COUNTRY_CODE,
        NOT_A_NUMBER,
        TOO_SHORT_AFTER_IDD,
        TOO_SHORT_NSN,
        TOO_LONG
    }

    public NumberParseException(ErrorType errorType2, String str) {
        super(str);
        this.message = str;
        this.errorType = errorType2;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public String toString() {
        return "Error type: " + this.errorType + ". " + this.message;
    }
}
