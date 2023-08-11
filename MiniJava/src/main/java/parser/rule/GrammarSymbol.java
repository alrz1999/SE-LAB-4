package parser.rule;

import scanner.token.Token;

public interface GrammarSymbol {
    boolean isTerminal();
}

class TerminalGrammarSymbol implements GrammarSymbol {
    private Token terminal;

    public TerminalGrammarSymbol(Token terminal) {
        this.setTerminal(terminal);
    }

    @Override
    public boolean isTerminal() {
        return true;
    }

    public Token getTerminal() {
        return terminal;
    }

    public void setTerminal(Token terminal) {
        this.terminal = terminal;
    }
}

class NonTerminalGrammarSymbol implements GrammarSymbol {
    private NonTerminal nonTerminal;

    public NonTerminalGrammarSymbol(NonTerminal nonTerminal) {
        this.setNonTerminal(nonTerminal);
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    public NonTerminal getNonTerminal() {
        return nonTerminal;
    }

    public void setNonTerminal(NonTerminal nonTerminal) {
        this.nonTerminal = nonTerminal;
    }
}