package lexic;

public class Value {
    private int integerValue;
    private String stringValue;

    public Value(int integerValue) {
        this.integerValue = integerValue;
    }
    public Value(String stringValue) {
        this.stringValue = stringValue;
    }
    public void setintegerValue(int integerValue) {
        this.integerValue = integerValue;
    }

    public void setstringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntegerValue() {
        return integerValue;
    }

    public String getStringValue() {
        return stringValue;
    }
    public String toString() {
        if (stringValue != null) {
            return stringValue;
        } else {
            return Integer.toString(integerValue);
        }
    }

}