package sintatic;

import lexic.Lexic;
import lexic.Token;
import lexic.Type;


public class Sintatic {
    
    private Lexic lexic;
    private String fileName;
    private Token  token;

    public Sintatic(String fileName) {
        this.fileName = fileName;
        this.lexic = new Lexic(fileName);
    }

    public void analyze() {
        System.out.println("Analisando "  + fileName);
        token = lexic.nextToken();
        program();
    }

    public boolean isUsedReservedWord(String word){
        return token.getType() == Type.reservedWord && token.getValue().getStringValue().equals(word);
    }
    // <programa> ::= program <id> {A01} ; <corpo> • {A45}
    private void program() {
        if (isUsedReservedWord("program")) {
            token = lexic.nextToken();
            if (token.getType() == Type.identifier) {
                token = lexic.nextToken();
                if (token.getType() == Type.semicolon) {
                    token = lexic.nextToken();
                    corpo();
                    if(token.getType() == Type.dot){
                        token = lexic.nextToken();
                        //{A45}
                    } else {
                        System.err.println(token.getLine() + ", " + token.getColumn() + "- (.) Erro: era esperado um ponto final");
                    }
                } else {
                    System.err.println(token.getLine() + ", " + token.getColumn() + "- (;) Erro: era esperado um ponto e vírgula");
                }
            } else {
                System.err.println(token.getLine() + ", " + token.getColumn() + "- (id) Erro: era esperado um identificador");
            }
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn() + "- (program) Erro: era esperado a palavra reservada 'program'");
        }
    }
    
    //<corpo> ::= <declara> <rotina> {A44} begin <sentencas> end {A46}
    private void corpo() {
        //declara();
        //rotina();
        if (isUsedReservedWord("begin")) {
            token = lexic.nextToken();
            //sentencas();
            if (isUsedReservedWord("end")) {
                token = lexic.nextToken();
                //{A46}
            } else {
                System.err.println(token.getLine() + ", " + token.getColumn() + "- (end) Erro: era esperado a palavra reservada 'end'");
            }
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn() + "- (begin) Erro: era esperado a palavra reservada 'begin'");
        }
    }

    //<declara> ::= var <dvar> <mais_dc> | ε


}
