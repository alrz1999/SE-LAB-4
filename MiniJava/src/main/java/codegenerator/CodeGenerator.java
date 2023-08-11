package codegenerator;

import scanner.token.Token;

public interface CodeGenerator {
    void semanticFunction(int func, Token next);
    void printMemory();
}
