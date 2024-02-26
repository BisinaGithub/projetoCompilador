package lexico;

public class Valor {
    private int valorInteiro;
    private String valorString;

    public Valor(int valorInteiro) {
        this.valorInteiro = valorInteiro;
    }
    public Valor(String valorString) {
        this.valorString = valorString;
    }
    public void setValorInteiro(int valorInteiro) {
        this.valorInteiro = valorInteiro;
    }

    public void setValorString(String valorString) {
        this.valorString = valorString;
    }

    public int getValorInteiro() {
        return valorInteiro;
    }

    public String getValorString() {
        return valorString;
    }
    public String toString() {
        if (valorString != null) {
            return valorString;
        } else {
            return Integer.toString(valorInteiro);
        }
    }

}