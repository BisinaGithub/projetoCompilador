package lexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Lexico {

    private String filename;
    private Token token;
    private BufferedReader br;
    private char character;
    private StringBuilder lexeme = new StringBuilder();
    private int line;
    private int column;

    public Lexico(String filename) {
        this.filename = filename;
        String filepath = Paths.get(filename).toAbsolutePath().toString();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath, StandardCharsets.UTF_8));
            this.br = br;
            line = 1;
            column = 1;
            character = nextChar();
        } catch (IOException e) {
            System.err.println("Não foi possível abrir o arquivo: " + filename);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private char nextChar() {
        try {
            return (char) br.read();
        } catch (IOException e) {
            System.err.println("Não foi possível ler do arquivo: " + filename);
            e.printStackTrace();
            System.exit(-1);
            return 0;
        }

    }

    public boolean isReservedWord(String lexeme) {
        List<String> reservedWords = new ArrayList<String>(Arrays.asList(
                "and", "array", "begin", "case", "const",
                "div", "do", "downto", "else", "end",
                "file", "for", "function", "goto", "if",
                "in", "label", "mod", "nil", "not",
                "of", "or", "packed", "procedure", "program",
                "record", "repeat", "set", "then", "to",
                "type", "until", "var", "while", "with",
                "integer", "real", "boolean", "char", "string"));
        return reservedWords.contains(lexeme);
    }

    public Token nextToken() {
        do {
            lexeme.setLength(0);
            if (character == 65535) {
                return new Token(line, column, Classe.EOF);
            } else if (character == '{') {
                character = nextChar();
                column++;
                while (character != '}') {
                    if (character == 65535) {
                        System.err.println("Erro na linha " + line + " e coluna " + column + ": comentário não fechado");
                        System.exit(-1);
                    } else if (character == '\n') {
                        character = nextChar();
                        line++;
                        column = 1;
                    } else {
                        character = nextChar();
                        column++;
                    }
                }
                character = nextChar();
                column++;
            } else if (Character.isLetter(character)) {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                while (Character.isLetterOrDigit(character)) {
                    lexeme.append(character);
                    character = nextChar();
                    column++;
                }
                if (isReservedWord(lexeme.toString())) {
                    token.setClasse(Classe.palavraReservada);
                    token.setValor(new Valor(lexeme.toString()));
                } else {
                    token.setClasse(Classe.identificador);
                    token.setValor(new Valor(lexeme.toString()));
                }
                return token;
            } else if (character == '\n') {
                character = nextChar();
                line++;
                column = 1;
            } else if (Character.isDigit(character)) {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                while (Character.isDigit(character)) {
                    lexeme.append(character);
                    character = nextChar();
                    column++;
                }
                token.setClasse(Classe.identificador);
                token.setValor(new Valor(Integer.parseInt(lexeme.toString())));
                return token;
            } else if (Character.isWhitespace(character)) {
                character = nextChar();
                column++;
            } else if (character == '\t') {
                character = nextChar();
                column = column + 4;
            } else if (character == '.') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                token.setClasse(Classe.ponto);
                return token;
            } else if (character == ';') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                token.setClasse(Classe.pontoEVirgula);
                return token;
            } else if (character == ',') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                token.setClasse(Classe.virgula);
                return token;
            } else if (character == '+') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                token.setClasse(Classe.operadorSoma);
                return token;
            } else if (character == '-') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                token.setClasse(Classe.operadorSubtracao);
                return token;
            } else if (character == '*') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                token.setClasse(Classe.operadorMultiplicacao);
                return token;
            } else if (character == '/') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                token.setClasse(Classe.operadorDivisao);
                return token;
            } else if (character == '>') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                if (character == '=') {
                    lexeme.append(character);
                    character = nextChar();
                    column++;
                    token.setClasse(Classe.operadorMaiorIgual);
                } else {
                    token.setClasse(Classe.operadorMaior);
                }
            } else if (character == ':') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                if (character == '=') {
                    lexeme.append(character);
                    character = nextChar();
                    column++;
                    token.setClasse(Classe.atribuicao);
                } else {
                    token.setClasse(Classe.doisPontos);
                }
                return token;
            } else if (character == '<') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                if (character == '=') {
                    lexeme.append(character);
                    character = nextChar();
                    column++;
                    token.setClasse(Classe.operadorMenorIgual);
                } else if (character == '>') {
                    lexeme.append(character);
                    character = nextChar();
                    column++;
                    token.setClasse(Classe.operadorDiferente);
                } else {
                    token.setClasse(Classe.operadorMenor);
                }
                return token;
            } else if (character == '=') {
                token = new Token(line, column);
                lexeme.append(character);
                character = nextChar();
                column++;
                token.setClasse(Classe.atribuicao);
                return token;
            } else {
                System.err.println("Erro na linha " + line + " e coluna " + column + ": caracter inválido");
                System.exit(-1);
            }

        } while (character != 65535);
        return new Token(line, column, Classe.EOF);
        // } else if (Character.isWhitespace(character)) {
        // System.out.println("Espaço");
        // } else if (character == '.') {
        // System.out.println("Ponto");
        // } else {
        // System.out.println("Outra coisa: " + character);
        // }return token;
    }
}
