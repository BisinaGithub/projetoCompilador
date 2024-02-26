package lexico;

public class Token {
    private int line;
    private int column;
    private Classe classe;
    private Valor valor;

    public Token(int line, int column, Classe classe, Valor valor) {
        this.line = line;
        this.column = column;
        this.classe = classe;
        this.valor = valor;
    }

    public Token(int line, int column, Classe classe) {
        this.line = line;
        this.column = column;
        this.classe = classe;
    }

    public Token(int line, int column) {
        this.line = line;
        this.column = column;

    }

    public int getline() {
        return line;
    }

    public void setline(int line) {
        this.line = line;
    }

    public int getcolumn() {
        return column;
    }

    public void setcolumn(int column) {
        this.column = column;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public String toString() {
        return "Token [line=" + line + ", column=" + column + ", classe=" + classe + ", valor=" + valor + "]";
    }

}
