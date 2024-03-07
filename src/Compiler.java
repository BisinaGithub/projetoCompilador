// Código fonte do analisador léxico do compilador da linguagem de programação "Pascal", criado por Vinícius Silveira Bisinoto.
import lexic.Class;
import lexic.Lexic;
import lexic.Token;

public class Compiler {
    public static void main(String[] args) {

        if(args.length == 0){
            System.out.println("Modo de usar: java -jar NomePrograma NomeArquivoCodigo");
            return;
        }
        Lexic lexico = new Lexic(args[0]);
        Token token;
        do {
            token = lexico.nextToken();
            System.out.println(token);
        } while (token.getClasse() != Class.EOF);
    }
}
