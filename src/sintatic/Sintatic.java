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
                    body();
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
    private void body() {
        declare();
        //rotina();
        //{A44}
        if (isUsedReservedWord("begin")) {
            token = lexic.nextToken();
            sentences();
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
    private void declare() {
        if (isUsedReservedWord("var")) {
            token = lexic.nextToken();
            dvar();
            more_dc();
        }
    }

    //<dvar> ::= <variaveis> : <tipo_var> {A02}
    private void dvar() {
        variables();
        if (token.getType() == Type.colon) {
            token = lexic.nextToken();
            type_var();
            //{A02}
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn() + "- (:) Erro: era esperado dois pontos na declaração de variaveis");
        }
    }

    //<variaveis> ::= <id> {A03} <mais_var>
    private void variables() {
        if (token.getType() == Type.identifier) {
            token = lexic.nextToken();
            //{A03}
            more_var();
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn() + "- (id) Erro: era esperado um identificador de variáveis");
        }
    }

    //<tipo_var> ::= integer
    private void type_var() {
        if (isUsedReservedWord("integer")) {
            token = lexic.nextToken();
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn() + "- (integer) Erro: era esperado a palavra reservada 'integer'");
        }
    }

    //<mais_var> ::= , <variaveis> | ε
    private void more_var() {
        if (token.getType() == Type.comma) {
            token = lexic.nextToken();
            variables();
        }
    }

    //<mais_dc> ::=  ; <cont_dc>
    private void more_dc() {
        if (token.getType() == Type.semicolon) {
            token = lexic.nextToken();
            cont_dc();
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn() + "- (;) Erro: era esperado um ponto e vírgula");
        }
    }

    //<cont_dc> ::= <dvar> <mais_dc> | ε
    private void cont_dc() {
        if(token.getType() == Type.identifier){
            dvar();
            more_dc();
        }
    }

    //<sentencas> ::= <comando> <mais_sentencas>
    private void sentences() {
        command();
        more_sentences();
    }

    //<mais_sentencas> ::= ; <cont_sentencas>
    private void more_sentences() {
        if (token.getType() == Type.semicolon) {
            token = lexic.nextToken();
            cont_sentences();
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn() + "- (;) Erro: era esperado um ponto e vírgula");
        }
    }

    //<cont_sentencas> ::= <sentencas> | ε
    private void cont_sentences() {
            
        sentences();
    }

}
