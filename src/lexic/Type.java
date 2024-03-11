package lexic;

public enum Type {
    identifier,
    reservedWord,
    String,
    integerNumber,
    sumOperator,
    subtractOperator,
    multiplyOperator,
    divisionOperator,
    greaterOperator,
    lessesOperator,

    greaterEqualOperator,
    lesserEqualOperator,

    equalOperator, // =
    distinctOperator, // <>
    andOperator, // and
    orOperator, // or
    notOperador, // not
    allocation, // :=
    semicolon, // :
    comma,
    dot,
    colon,
    EOF
}
