package lexico;

public enum Classe {
    identificador,
    palavraReservada,
    numeroInteiro,
    operadorSoma,
    operadorSubtracao,
    operadorMultiplicacao,
    operadorDivisao,
    operadorMaior,
    operadorMenor,

    operadorMaiorIgual,
    operadorMenorIgual,

    operadorIgual, // =
    operadorDiferente, // <>
    operadorE, // and
    operadorOu, // or
    operadorNegacao, // not
    atribuicao, // :=
    pontoEVirgula, // 
    virgula,
    ponto,
    doisPontos,
    EOF
}
