package lexic;

public enum Type {
    IDENTIFIER,
    RESERVED_WORD,
    STRING,
    INTEGER_NUMBER,
    sumOperator,
    subtractOperator,
    multiplyOperator,
    divisionOperator,
    greaterOperator,
    lesserOperator,
    LEFT_PARENTHESES,
    RIGHT_PARENTHESES,
    greaterEqualOperator,
    lesserEqualOperator,
    equalOperator, // =
    distinctOperator, // <>
    allocation, // :=
    semicolon, // :
    comma,
    dot,
    colon,
    EOF
}
