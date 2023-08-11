package parser;

import scanner.token.Token;

import java.util.ArrayList;

/**
 * Created by mohammad hosein on 6/25/2015.
 */

public class Rule {
    public NonTerminal LHS;
    public ArrayList<GrammarSymbol> RHS;
    public int semanticAction;

    public Rule(String stringRule) {
        int index = stringRule.indexOf("#");
        if (index != -1) {
            try {
                semanticAction = Integer.parseInt(stringRule.substring(index + 1));
            } catch (NumberFormatException ex) {
                semanticAction = 0;
            }
            stringRule = stringRule.substring(0, index);
        } else {
            semanticAction = 0;
        }
        String[] splited = stringRule.split("->");
        LHS = NonTerminal.valueOf(splited[0]);
        RHS = new ArrayList<>();
        if (splited.length > 1) {
            String[] RHSs = splited[1].split(" ");
            for (String s : RHSs) {
                try {
                    RHS.add(new NonTerminalGrammarSymbol(NonTerminal.valueOf(s)));
                } catch (Exception e) {
                    RHS.add(new TerminalGrammarSymbol(new Token(Token.getTypeFormString(s), s)));
                }
            }
        }
    }
}

interface GrammarSymbol {
    boolean isTerminal();
}

class TerminalGrammarSymbol implements GrammarSymbol {
    public Token terminal;

    public TerminalGrammarSymbol(Token terminal) {
        this.terminal = terminal;
    }

    @Override
    public boolean isTerminal() {
        return true;
    }
}

class NonTerminalGrammarSymbol implements GrammarSymbol {
    public NonTerminal nonTerminal;

    public NonTerminalGrammarSymbol(NonTerminal nonTerminal) {
        this.nonTerminal = nonTerminal;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }
}