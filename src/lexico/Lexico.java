package lexico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class Lexico {

    private String filename;
    private Token token;
    private BufferedReader br;
    private char character;
    private StringBuilder lexeme = new StringBuilder();
    public int line;
    public int column;

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
            char lido = (char) br.read();
            if (lido == '\n') {
                line++;
                column = 1;
            } else {
                column++;
            }
            return lido;  
        } catch (IOException e) {
            System.err.println("Não foi possível ler do arquivo: " + filename);
            e.printStackTrace();
            System.exit(-1);
            return 0;
        }
        
    }

    public Token nextToken() {
        lexeme.setLength(0);
        if (Character.isLetter(character)) {
            token = new Token(line, column-1);
            lexeme.append(character);
            character = nextChar();
            while (Character.isLetterOrDigit(character)) {
                lexeme.append(character);
                character = nextChar();
            }
            token.setClasse(Classe.identificador);
            token.setValor(new Valor(lexeme.toString()));
            return token;
        } else if (Character.isDigit(character)) {
            token = new Token(line, column-1);
            lexeme.append(character);
            character = nextChar();
            while(Character.isDigit(character)){
                lexeme.append(character);
                character = nextChar();
            }
            token.setClasse(Classe.identificador);
            token.setValor(new Valor(Integer.parseInt(lexeme.toString())));
            return token;
        }else{
            character = nextChar();
            return null;
        }
        // } else if (Character.isWhitespace(character)) {
        //     System.out.println("Espaço");
        // } else if (character == '.') {
        //     System.out.println("Ponto");
        // } else {
        //     System.out.println("Outra coisa: " + character);
        // }return token;
}}
