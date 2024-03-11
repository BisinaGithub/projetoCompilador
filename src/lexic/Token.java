package lexic;

public class Token {
    private int line;
    private int column;
    private Type type;
    private Value value;

    public Token(int line, int column, Type type, Value value) {
        this.line = line;
        this.column = column;
        this.type = type;
        this.value = value;
    }

    public Token(int line, int column, Type type) {
        this.line = line;
        this.column = column;
        this.type = type;
    }

    public Token(int line, int column) {
        this.line = line;
        this.column = column;

    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String toString() {
        return "Token [line=" + line + ", column=" + column + ", type=" + type + ", valor=" + value + "]";
    }

}
