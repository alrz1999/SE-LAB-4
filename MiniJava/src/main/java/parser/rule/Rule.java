package parser.rule;

import scanner.token.Token;

import java.util.ArrayList;

/**
 * Created by mohammad hosein on 6/25/2015.
 */

public class Rule {
    private NonTerminal LHS;
    private ArrayList<GrammarSymbol> RHS;
    private int semanticAction;

    public Rule(String stringRule) {
        int index = stringRule.indexOf("#");
        if (index != -1) {
            try {
                setSemanticAction(Integer.parseInt(stringRule.substring(index + 1)));
            } catch (NumberFormatException ex) {
                setSemanticAction(0);
            }
            stringRule = stringRule.substring(0, index);
        } else {
            setSemanticAction(0);
        }
        String[] splited = stringRule.split("->");
        setLHS(NonTerminal.valueOf(splited[0]));
        setRHS(new ArrayList<>());
        if (splited.length > 1) {
            String[] RHSs = splited[1].split(" ");
            for (String s : RHSs) {
                try {
                    getRHS().add(new NonTerminalGrammarSymbol(NonTerminal.valueOf(s)));
                } catch (Exception e) {
                    getRHS().add(new TerminalGrammarSymbol(new Token(Token.getTypeFormString(s), s)));
                }
            }
        }
    }

    public NonTerminal getLHS() {
        return LHS;
    }

    public void setLHS(NonTerminal LHS) {
        this.LHS = LHS;
    }

    public ArrayList<GrammarSymbol> getRHS() {
        return RHS;
    }

    public void setRHS(ArrayList<GrammarSymbol> RHS) {
        this.RHS = RHS;
    }

    public int getSemanticAction() {
        return semanticAction;
    }

    public void setSemanticAction(int semanticAction) {
        this.semanticAction = semanticAction;
    }
}