package sintatic;

import lexic.Lexic;
import lexic.Token;
import lexic.Type;

public class Sintatic {

    private Lexic lexic;
    private String fileName;
    private Token token;

    public Sintatic(String fileName) {
        this.fileName = fileName;
        this.lexic = new Lexic(fileName);
    }

    public void analyze() {
        System.out.println("Analisando " + fileName);
        token = lexic.nextToken();
        program();
    }

    public boolean isUsedReservedWord(String word) {
        return token.getType() == Type.RESERVED_WORD && token.getValue().getStringValue().equals(word);
    }

    // <programa> ::= program <id> {A01} ; <corpo> • {A45}
    private void program() {
        if (isUsedReservedWord("program")) {
            token = lexic.nextToken();
            if (token.getType() == Type.IDENTIFIER) {
                token = lexic.nextToken();
                if (token.getType() == Type.semicolon) {
                    token = lexic.nextToken();
                    body();
                    if (token.getType() == Type.dot) {
                        token = lexic.nextToken();
                    } else {
                        System.err.println(
                                token.getLine() + ", " + token.getColumn() + "- (.) Erro: era esperado um ponto final");
                    }
                } else {
                    System.err.println(
                            token.getLine() + ", " + token.getColumn() + "- (;) Erro: era esperado um ponto e vírgula");
                }
            } else {
                System.err.println(
                        token.getLine() + ", " + token.getColumn() + "- (id) Erro: era esperado um identificador");
            }
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn()
                    + "- (program) Erro: era esperado a palavra reservada 'program'");
        }
    }

    // <corpo> ::= <declara> <rotina> {A44} begin <sentencas> end {A46}
    private void body() {
        declare();
        if (isUsedReservedWord("begin")) {
            token = lexic.nextToken();
            sentences();
            if (isUsedReservedWord("end")) {
                token = lexic.nextToken();
            } else {
                System.err.println(token.getLine() + ", " + token.getColumn()
                        + "- (end) Erro: era esperado a palavra reservada 'end'");
            }
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn()
                    + "- (begin) Erro: era esperado a palavra reservada 'begin'");
        }
    }

    // <declara> ::= var <dvar> <mais_dc> | ε
    private void declare() {
        if (isUsedReservedWord("var")) {
            token = lexic.nextToken();
            dvar();
            moreDc();
        }
    }

    // <dvar> ::= <variaveis> : <tipo_var> {A02}
    private void dvar() {
        variables();
        if (token.getType() == Type.colon) {
            token = lexic.nextToken();
            typeVar();
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn()
                    + "- (:) Erro: era esperado dois pontos na declaração de variaveis");
        }
    }

    // <variaveis> ::= <id> {A03} <mais_var>
    private void variables() {
        if (token.getType() == Type.IDENTIFIER) {
            token = lexic.nextToken();
            moreVar();
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn()
                    + "- (id) Erro: era esperado um identificador de variáveis");
        }
    }

    // <tipo_var> ::= integer
    private void typeVar() {
        if (isUsedReservedWord("integer")) {
            token = lexic.nextToken();
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn()
                    + "- (integer) Erro: era esperado a palavra reservada 'integer'");
        }
    }

    // <mais_var> ::= , <variaveis> | ε
    private void moreVar() {
        if (token.getType() == Type.comma) {
            token = lexic.nextToken();
            variables();
        }
    }

    // <mais_dc> ::= ; <cont_dc>
    private void moreDc() {
        if (token.getType() == Type.semicolon) {
            token = lexic.nextToken();
            contDc();
        } else {
            System.err.println(
                    token.getLine() + ", " + token.getColumn() + "- (;) Erro: era esperado um ponto e vírgula");
        }
    }

    // <cont_dc> ::= <dvar> <mais_dc> | ε
    private void contDc() {
        if (token.getType() == Type.IDENTIFIER) {
            dvar();
            moreDc();
        }
    }

    // <sentencas> ::= <comando> <mais_sentencas>
    private void sentences() {
        if (token.getType() == Type.IDENTIFIER || token.getType() == Type.RESERVED_WORD) {
            command();
            moreSentences();
        }
    }

    // <mais_sentencas> ::= ; <cont_sentencas>
    private void moreSentences() {
        if (token.getType() == Type.semicolon) {
            token = lexic.nextToken();
            contSentences();
        } else {
            System.err.println(
                    token.getLine() + ", " + token.getColumn() + "- (;) Erro: era esperado um ponto e vírgula");
        }
    }

    // <cont_sentencas> ::= <sentencas> | ε
    private void contSentences() {
        if (token.getType() == Type.IDENTIFIER || token.getType() == Type.RESERVED_WORD) {
            sentences();
        }
    }


    //<var_read> ::= <id> {A08} <mais_var_read>
    private void varRead() {
        if (token.getType() == Type.IDENTIFIER) {
            token = lexic.nextToken();
            moreVarRead();
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn()
                    + "- (id) Erro: era esperado um identificador");
        }
    }

    //<mais_var_read> ::= , <var_read> | ε
    private void moreVarRead() {
        if (token.getType() == Type.comma) {
            token = lexic.nextToken();
            varRead();
        }
    }

    //<exp_write> ::= <id> {A09} <mais_exp_write> | <string> {A59} <mais_exp_write> | <intnum> {A43} <mais_exp_write>
    private void expWrite() {
        if (token.getType() == Type.IDENTIFIER || token.getType() == Type.STRING || token.getType() == Type.INTEGER_NUMBER){
            token = lexic.nextToken();
            moreExpWrite();
        } else {
            System.err.println(token.getLine() + ", " + token.getColumn()
                    + "- (id, string, intnum) Erro: era esperado um identificador, string ou número inteiro");
        }
    }

    //<mais_exp_write> ::=  ,  <exp_write> | ε
    private void moreExpWrite() {
        if (token.getType() == Type.comma) {
            token = lexic.nextToken();
            expWrite();
        }
    }

    private void command() {
        if (token.getType() == Type.IDENTIFIER) {
            token = lexic.nextToken();
            if (token.getType() == Type.allocation) {
                token = lexic.nextToken();
                expression();
            } else {
                System.err.println(token.getLine() + ", " + token.getColumn()
                        + "- (:=) Erro: era esperado um operador de atribuição");
            }
        } else if (isUsedReservedWord("read")) {
            token = lexic.nextToken();
            if (token.getType() == Type.LEFT_PARENTHESES) {
                token = lexic.nextToken();
            } else {
                System.err.println(token.getLine() + ", " + token.getColumn()
                        + "- (()) Erro: era esperado um parênteses aberto");
            }
            varRead();
            if (token.getType() == Type.RIGHT_PARENTHESES) {
                token = lexic.nextToken();
            } else {
                System.err.println(token.getLine() + ", " + token.getColumn()
                        + "- (()) Erro: era esperado um parênteses fechado");
            }
        } else if (isUsedReservedWord("if")) {
            token = lexic.nextToken();
            expression();
            if (isUsedReservedWord("then")) {
                token = lexic.nextToken();
                sentences();
                if (isUsedReservedWord("else")) {
                    token = lexic.nextToken();
                    sentences();
                }
            }
        } else if (isUsedReservedWord("while")) {
            token = lexic.nextToken();
            expression();
            if (isUsedReservedWord("do")) {
                token = lexic.nextToken();
                sentences();
            }
        } else if (isUsedReservedWord("write")) {
            token = lexic.nextToken();
            expression();
        }
    }

    //<expressao> ::= <termo> <mais_expressao>
    private void expression() {


    }

}
