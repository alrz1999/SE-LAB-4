package parser;

import codegenerator.CodeGenerator;
import errorhandler.ErrorHandler;
import log.Log;
import parser.action.Action;
import parser.rule.Rule;
import scanner.LexicalAnalyzer;
import scanner.token.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class Parser {
    private final ArrayList<Rule> rules;
    private final Stack<Integer> parsStack;
    private final CodeGenerator cg;
    private ParseTable parseTable;

    public Parser() {
        this("src/main/resources/parseTable", "src/main/resources/Rules");
    }

    public Parser(String strr1, String strr2) {
        parsStack = new Stack<>();
        parsStack.push(0);
        try {
            parseTable = new ParseTable(Files.readAllLines(Paths.get(strr1)).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        rules = new ArrayList<>();
        try {
            for (String stringRule : Files.readAllLines(Paths.get(strr2))) {
                rules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cg = new CodeGenerator();
    }

    public void startParse(java.util.Scanner sc) {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(sc);
        Token lookAhead = lexicalAnalyzer.getNextToken();
        boolean finish = false;
        Action currentAction;
        while (!finish) {
            try {
                Log.print(/*"lookahead : "+*/ lookAhead.toString() + "\t" + parsStack.peek());
//                Log.print("state : "+ parsStack.peek());
                currentAction = parseTable.getActionTable(parsStack.peek(), lookAhead);
                Log.print(currentAction.toString());
                //Log.print("");

                switch (currentAction.getAction()) {
                    case SHIFT:
                        parsStack.push(currentAction.getNumber());
                        lookAhead = lexicalAnalyzer.getNextToken();

                        break;
                    case REDUCE:
                        Rule rule = rules.get(currentAction.getNumber());
                        for (int i = 0; i < rule.getRHS().size(); i++) {
                            parsStack.pop();
                        }

                        Log.print(/*"state : " +*/ parsStack.peek() + "\t" + rule.getLHS());
//                        Log.print("LHS : "+rule.LHS);
                        parsStack.push(parseTable.getGotoTable(parsStack.peek(), rule.getLHS()));
                        Log.print(/*"new State : " + */parsStack.peek() + "");
//                        Log.print("");
                        try {
                            cg.semanticFunction(rule.getSemanticAction(), lookAhead);
                        } catch (Exception e) {
                            Log.print("Code Genetator Error");
                        }
                        break;
                    case ACCEPT:
                        finish = true;
                        break;
                }
                Log.print("");
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        if (!ErrorHandler.hasError) cg.printMemory();
    }
}
