// Código fonte do analisador léxico do compilador da linguagem de programação "Pascal", criado por Vinícius Silveira Bisinoto.
import sintatic.Sintatic;

public class Compiler {
    public static void main(String[] args) {

        if(args.length == 0){
            System.out.println("Modo de usar: java -jar NomePrograma NomeArquivoCodigo");
            return;
        }
        Sintatic sintatic = new Sintatic(args[0]);
        sintatic.analyze();
    }
}
