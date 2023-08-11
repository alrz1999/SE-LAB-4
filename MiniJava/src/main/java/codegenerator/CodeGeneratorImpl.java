package codegenerator;

import codegenerator.models.*;
import errorhandler.ErrorHandler;
import log.Log;
import scanner.token.Token;

import java.util.Stack;

/**
 * Created by Alireza on 6/27/2015.
 */
public class CodeGeneratorImpl implements CodeGenerator {
    private final Memory memory = new Memory();
    private final Stack<Address> ss = new Stack<>();
    private final Stack<String> symbolStack = new Stack<>();
    private final Stack<String> callStack = new Stack<>();
    private final SymbolTable symbolTable;

    public CodeGeneratorImpl() {
        symbolTable = new SymbolTable(memory);
    }

    public void printMemory() {
        memory.pintCodeBlock();
    }

    public void semanticFunction(int func, Token next) {
        Log.print("codegenerator : " + func);
        switch (func) {
            case 0:
                return;
            case 1:
                checkID();
                break;
            case 2:
                pid(next);
                break;
            case 3:
                fpid();
                break;
            case 4:
                kpid(next);
                break;
            case 5:
                intpid(next);
                break;
            case 6:
                startCall();
                break;
            case 7:
                call();
                break;
            case 8:
                arg();
                break;
            case 9:
                assign();
                break;
            case 10:
                add();
                break;
            case 11:
                sub();
                break;
            case 12:
                mult();
                break;
            case 13:
                label();
                break;
            case 14:
                save();
                break;
            case 15:
                _while();
                break;
            case 16:
                jpf_save();
                break;
            case 17:
                jpHere();
                break;
            case 18:
                print();
                break;
            case 19:
                equal();
                break;
            case 20:
                less_than();
                break;
            case 21:
                and();
                break;
            case 22:
                not();
                break;
            case 23:
                defClass();
                break;
            case 24:
                defMethod();
                break;
            case 25:
                popClass();
                break;
            case 26:
                extend();
                break;
            case 27:
                defField();
                break;
            case 28:
                defVar();
                break;
            case 29:
                methodReturn();
                break;
            case 30:
                defParam();
                break;
            case 31:
                lastTypeBool();
                break;
            case 32:
                lastTypeInt();
                break;
            case 33:
                defMain();
                break;
        }
    }

    private void defMain() {
        memory.add3AddressCode(ss.pop().getNum(), Operation.JP, AddressType.DIRECT.createAddress(memory.getCurrentCodeBlockAddress(), VarType.ADDRESS), null, null);
        String methodName = "main";
        String className = symbolStack.pop();

        symbolTable.addMethod(className, methodName, memory.getCurrentCodeBlockAddress());

        symbolStack.push(className);
        symbolStack.push(methodName);
    }

    private void checkID() {
        symbolStack.pop();
        ss.peek();
    }

    private void pid(Token next) {
        if (symbolStack.size() > 1) {
            String methodName = symbolStack.pop();
            String className = symbolStack.pop();
            try {

                Symbol s = symbolTable.get(className, methodName, next.getValue());
                VarType t = s.getVarType();
                ss.push(AddressType.DIRECT.createAddress(s.getAddress(), t));


            } catch (Exception e) {
                ss.push(AddressType.DIRECT.createAddress(0, VarType.NON));
            }
            symbolStack.push(className);
            symbolStack.push(methodName);
        } else {
            ss.push(AddressType.DIRECT.createAddress(0, VarType.NON));
        }
        symbolStack.push(next.getValue());
    }

    private void fpid() {
        ss.pop();
        ss.pop();

        Symbol s = symbolTable.get(symbolStack.pop(), symbolStack.pop());
        VarType t = s.getVarType();
        ss.push(AddressType.DIRECT.createAddress(s.getAddress(), t));

    }

    private void kpid(Token next) {
        ss.push(symbolTable.get(next.getValue()));
    }

    private void intpid(Token next) {
        ss.push(AddressType.IMMEDIATE.createAddress(Integer.parseInt(next.getValue()), VarType.INT));
    }

    private void startCall() {
        ss.pop();
        ss.pop();
        String methodName = symbolStack.pop();
        String className = symbolStack.pop();
        symbolTable.startCall(className, methodName);
        callStack.push(className);
        callStack.push(methodName);
    }

    private void call() {
        String methodName = callStack.pop();
        String className = callStack.pop();
        try {
            symbolTable.getNextParam(className, methodName);
            symbolTable.increaseIndex(className, methodName);
            ErrorHandler.printError("The few argument pass for method");
        } catch (IndexOutOfBoundsException e) {
        }
        VarType t = VarType.INT;
        switch (symbolTable.getMethodReturnType(className, methodName)) {
            case INT:
                t = VarType.INT;
                break;
            case BOOL:
                t = VarType.BOOL;
                break;
        }
        Address temp = AddressType.DIRECT.createAddress(memory.getTemp(), t);
        ss.push(temp);
        memory.add3AddressCode(Operation.ASSIGN, AddressType.IMMEDIATE.createAddress(temp.getNum(), VarType.ADDRESS), AddressType.DIRECT.createAddress(symbolTable.getMethodReturnAddress(className, methodName), VarType.ADDRESS), null);
        memory.add3AddressCode(Operation.ASSIGN, AddressType.IMMEDIATE.createAddress(memory.getCurrentCodeBlockAddress() + 2, VarType.ADDRESS), AddressType.DIRECT.createAddress(symbolTable.getMethodCallerAddress(className, methodName), VarType.ADDRESS), null);
        memory.add3AddressCode(Operation.JP, AddressType.DIRECT.createAddress(symbolTable.getMethodAddress(className, methodName), VarType.ADDRESS), null, null);
    }

    private void arg() {
        String methodName = callStack.pop();
        try {
            String className = callStack.peek();
            Symbol s = symbolTable.getNextParam(className, methodName);
            symbolTable.increaseIndex(className, methodName);
            VarType t = s.getVarType();
            Address param = ss.pop();
            if (param.getVarType() != t) {
                ErrorHandler.printError("The argument type isn't match");
            }
            memory.add3AddressCode(Operation.ASSIGN, param, AddressType.DIRECT.createAddress(s.getAddress(), t), null);

        } catch (IndexOutOfBoundsException e) {
            ErrorHandler.printError("Too many arguments pass for method");
        }
        callStack.push(methodName);
    }

    private void assign() {
        Address s1 = ss.pop();
        Address s2 = ss.pop();
        if (s1.getVarType() != s2.getVarType()) {
            ErrorHandler.printError("The type of operands in assign is different ");
        }

        memory.add3AddressCode(Operation.ASSIGN, s1, s2, null);
    }

    private void add() {
        Address temp = AddressType.DIRECT.createAddress(memory.getTemp(), VarType.INT);
        Address s2 = ss.pop();
        Address s1 = ss.pop();

        if (s1.getVarType() != VarType.INT || s2.getVarType() != VarType.INT) {
            ErrorHandler.printError("In add two operands must be integer");
        }
        memory.add3AddressCode(Operation.ADD, s1, s2, temp);
        ss.push(temp);
    }

    private void sub() {
        Address temp = AddressType.DIRECT.createAddress(memory.getTemp(), VarType.INT);
        Address s2 = ss.pop();
        Address s1 = ss.pop();
        if (s1.getVarType() != VarType.INT || s2.getVarType() != VarType.INT) {
            ErrorHandler.printError("In sub two operands must be integer");
        }
        memory.add3AddressCode(Operation.SUB, s1, s2, temp);
        ss.push(temp);
    }

    private void mult() {
        Address temp = AddressType.DIRECT.createAddress(memory.getTemp(), VarType.INT);
        Address s2 = ss.pop();
        Address s1 = ss.pop();
        if (s1.getVarType() != VarType.INT || s2.getVarType() != VarType.INT) {
            ErrorHandler.printError("In mult two operands must be integer");
        }
        memory.add3AddressCode(Operation.MULT, s1, s2, temp);
        ss.push(temp);
    }

    private void label() {
        ss.push(AddressType.DIRECT.createAddress(memory.getCurrentCodeBlockAddress(), VarType.ADDRESS));
    }

    private void save() {
        ss.push(AddressType.DIRECT.createAddress(memory.saveMemory(), VarType.ADDRESS));
    }

    private void _while() {
        memory.add3AddressCode(ss.pop().getNum(), Operation.JPF, ss.pop(), AddressType.DIRECT.createAddress(memory.getCurrentCodeBlockAddress() + 1, VarType.ADDRESS), null);
        memory.add3AddressCode(Operation.JP, ss.pop(), null, null);
    }

    private void jpf_save() {
        Address save = AddressType.DIRECT.createAddress(memory.saveMemory(), VarType.ADDRESS);
        memory.add3AddressCode(ss.pop().getNum(), Operation.JPF, ss.pop(), AddressType.DIRECT.createAddress(memory.getCurrentCodeBlockAddress(), VarType.ADDRESS), null);
        ss.push(save);
    }

    private void jpHere() {
        memory.add3AddressCode(ss.pop().getNum(), Operation.JP, AddressType.DIRECT.createAddress(memory.getCurrentCodeBlockAddress(), VarType.ADDRESS), null, null);
    }

    private void print() {
        memory.add3AddressCode(Operation.PRINT, ss.pop(), null, null);
    }

    private void equal() {
        Address temp = AddressType.DIRECT.createAddress(memory.getTemp(), VarType.BOOL);
        Address s2 = ss.pop();
        Address s1 = ss.pop();
        if (s1.getVarType() != s2.getVarType()) {
            ErrorHandler.printError("The type of operands in equal operator is different");
        }
        memory.add3AddressCode(Operation.EQ, s1, s2, temp);
        ss.push(temp);
    }

    private void less_than() {
        Address temp = AddressType.DIRECT.createAddress(memory.getTemp(), VarType.BOOL);
        Address s2 = ss.pop();
        Address s1 = ss.pop();
        if (s1.getVarType() != VarType.INT || s2.getVarType() != VarType.INT) {
            ErrorHandler.printError("The type of operands in less than operator is different");
        }
        memory.add3AddressCode(Operation.LT, s1, s2, temp);
        ss.push(temp);
    }

    private void and() {
        Address temp = AddressType.DIRECT.createAddress(memory.getTemp(), VarType.BOOL);
        Address s2 = ss.pop();
        Address s1 = ss.pop();
        if (s1.getVarType() != VarType.BOOL || s2.getVarType() != VarType.BOOL) {
            ErrorHandler.printError("In and operator the operands must be boolean");
        }
        memory.add3AddressCode(Operation.AND, s1, s2, temp);
        ss.push(temp);
    }

    private void not() {
        Address temp = AddressType.DIRECT.createAddress(memory.getTemp(), VarType.BOOL);
        Address s2 = ss.pop();
        Address s1 = ss.pop();
        if (s1.getVarType() != VarType.BOOL) {
            ErrorHandler.printError("In not operator the operand must be boolean");
        }
        memory.add3AddressCode(Operation.NOT, s1, s2, temp);
        ss.push(temp);
    }

    private void defClass() {
        ss.pop();
        symbolTable.addClass(symbolStack.peek());
    }

    private void defMethod() {
        ss.pop();
        String methodName = symbolStack.pop();
        String className = symbolStack.pop();

        symbolTable.addMethod(className, methodName, memory.getCurrentCodeBlockAddress());

        symbolStack.push(className);
        symbolStack.push(methodName);
    }

    private void popClass() {
        symbolStack.pop();
    }

    private void extend() {
        ss.pop();
        symbolTable.setSuperClass(symbolStack.pop(), symbolStack.peek());
    }

    private void defField() {
        ss.pop();
        symbolTable.addField(symbolStack.pop(), symbolStack.peek());
    }

    private void defVar() {
        ss.pop();

        String var = symbolStack.pop();
        String methodName = symbolStack.pop();
        String className = symbolStack.pop();

        symbolTable.addMethodLocalVariable(className, methodName, var);

        symbolStack.push(className);
        symbolStack.push(methodName);
    }

    private void methodReturn() {
        String methodName = symbolStack.pop();
        Address s = ss.pop();
        SymbolType t = symbolTable.getMethodReturnType(symbolStack.peek(), methodName);
        VarType temp = VarType.INT;
        switch (t) {
            case INT:
                break;
            case BOOL:
                temp = VarType.BOOL;
        }
        if (s.getVarType() != temp) {
            ErrorHandler.printError("The type of method and return address was not match");
        }
        memory.add3AddressCode(Operation.ASSIGN, s, AddressType.INDIRECT.createAddress(symbolTable.getMethodReturnAddress(symbolStack.peek(), methodName), VarType.ADDRESS), null);
        memory.add3AddressCode(Operation.JP, AddressType.DIRECT.createAddress(symbolTable.getMethodCallerAddress(symbolStack.peek(), methodName), VarType.ADDRESS), null, null);
    }

    private void defParam() {
        ss.pop();
        String param = symbolStack.pop();
        String methodName = symbolStack.pop();
        String className = symbolStack.pop();

        symbolTable.addMethodParameter(className, methodName, param);

        symbolStack.push(className);
        symbolStack.push(methodName);
    }

    private void lastTypeBool() {
        symbolTable.setLastType(SymbolType.BOOL);
    }

    private void lastTypeInt() {
        symbolTable.setLastType(SymbolType.INT);
    }
}
