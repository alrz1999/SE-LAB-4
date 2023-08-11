package semantic.symbol;

import codegenerator.Address;
import codegenerator.Memory;
import codegenerator.TypeAddress;
import codegenerator.VarType;
import errorhandler.ErrorHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private final Map<String, Klass> klasses;
    private final Map<String, Address> keyWords;
    private final Memory mem;
    private SymbolType lastType;

    public SymbolTable(Memory memory) {
        mem = memory;
        klasses = new HashMap<>();
        keyWords = new HashMap<>();
        keyWords.put("true", TypeAddress.IMMEDIATE.createAddress(1, VarType.BOOL));
        keyWords.put("false", TypeAddress.IMMEDIATE.createAddress(0, VarType.BOOL));
    }

    public void setLastType(SymbolType type) {
        lastType = type;
    }

    public SymbolType getLastType() {
        return lastType;
    }

    public Memory getMem() {
        return mem;
    }

    public void addClass(String className) {
        if (klasses.containsKey(className)) {
            ErrorHandler.printError("This class already defined");
        }
        klasses.put(className, new Klass());
    }

    public void addField(String fieldName, String className) {
        klasses.get(className).getFields().put(fieldName, createNewSymbol());
    }

    public void addMethod(String className, String methodName, int address) {
        if (klasses.get(className).getMethods().containsKey(methodName)) {
            ErrorHandler.printError("This method already defined");
        }
        klasses.get(className).getMethods().put(methodName, new Method(address, getLastType()));
    }

    public void addMethodParameter(String className, String methodName, String parameterName) {
        klasses.get(className).getMethods().get(methodName).addParameter(parameterName);
    }

    public void addMethodLocalVariable(String className, String methodName, String localVariableName) {
        if (klasses.get(className).getMethods().get(methodName).getLocalVariable().containsKey(localVariableName)) {
            ErrorHandler.printError("This variable already defined");
        }

        klasses.get(className).getMethods().get(methodName).getLocalVariable().put(localVariableName, createNewSymbol());
    }

    public void setSuperClass(String superClass, String className) {
        klasses.get(className).setSuperClass(klasses.get(superClass));
    }

    public Address get(String keywordName) {
        return keyWords.get(keywordName);
    }

    public Symbol get(String fieldName, String className) {
        return klasses.get(className).getField(fieldName);
    }

    public Symbol get(String className, String methodName, String variable) {
        Symbol res = klasses.get(className).getMethods().get(methodName).getVariable(variable);
        if (res == null) res = get(variable, className);
        return res;
    }

    public Symbol getNextParam(String className, String methodName) {
        return klasses.get(className).getMethods().get(methodName).getNextParameter();
    }

    public void increaseIndex(String className, String methodName) {
        klasses.get(className).getMethods().get(methodName).increaseIndex();
    }

    public void startCall(String className, String methodName) {
        klasses.get(className).getMethods().get(methodName).reset();
    }

    public int getMethodCallerAddress(String className, String methodName) {
        return klasses.get(className).getMethods().get(methodName).getCallerAddress();
    }

    public int getMethodReturnAddress(String className, String methodName) {
        return klasses.get(className).getMethods().get(methodName).getReturnAddress();
    }

    public SymbolType getMethodReturnType(String className, String methodName) {
        return klasses.get(className).getMethods().get(methodName).getReturnType();
    }

    public int getMethodAddress(String className, String methodName) {
        return klasses.get(className).getMethods().get(methodName).getCodeAddress();
    }

    private Symbol createNewSymbol() {
        int address = getMem().getDateAddress();
        return getLastType().createSymbol(address);
    }

    class Klass {
        private Map<String, Symbol> Fields;
        private Map<String, Method> Methods;
        private Klass superClass;

        public Klass() {
            setFields(new HashMap<>());
            setMethods(new HashMap<>());
        }

        public Symbol getField(String fieldName) {
            if (getFields().containsKey(fieldName)) {
                return getFields().get(fieldName);
            }
            return getSuperClass().getField(fieldName);
        }

        public Map<String, Symbol> getFields() {
            return Fields;
        }

        public void setFields(Map<String, Symbol> fields) {
            Fields = fields;
        }

        public Map<String, Method> getMethods() {
            return Methods;
        }

        public void setMethods(Map<String, Method> methods) {
            Methods = methods;
        }

        public Klass getSuperClass() {
            return superClass;
        }

        public void setSuperClass(Klass superClass) {
            this.superClass = superClass;
        }
    }

    class Method {
        private final ArrayList<String> orderedParameters;
        private int codeAddress;
        private Map<String, Symbol> parameters;
        private Map<String, Symbol> localVariable;
        private int callerAddress;
        private int returnAddress;
        private SymbolType returnType;
        private int index;

        public Method(int codeAddress, SymbolType returnType) {
            this.setCodeAddress(codeAddress);
            this.setParameters(new HashMap<>());
            this.setLocalVariable(new HashMap<>());
            this.setCallerAddress(getMem().getDateAddress());
            this.setReturnAddress(getMem().getDateAddress());
            this.setReturnType(returnType);
            this.orderedParameters = new ArrayList<>();
        }

        public Symbol getVariable(String variableName) {
            if (getParameters().containsKey(variableName)) return getParameters().get(variableName);
            if (getLocalVariable().containsKey(variableName)) return getLocalVariable().get(variableName);
            return null;
        }

        public void addParameter(String parameterName) {
            getParameters().put(parameterName, createNewSymbol());
            orderedParameters.add(parameterName);
        }

        private void reset() {
            index = 0;
        }

        private Symbol getNextParameter() {
            return getParameters().get(orderedParameters.get(index));
        }

        public void increaseIndex() {
            index += 1;
        }

        public int getCodeAddress() {
            return codeAddress;
        }

        public void setCodeAddress(int codeAddress) {
            this.codeAddress = codeAddress;
        }

        public Map<String, Symbol> getParameters() {
            return parameters;
        }

        public void setParameters(Map<String, Symbol> parameters) {
            this.parameters = parameters;
        }

        public Map<String, Symbol> getLocalVariable() {
            return localVariable;
        }

        public void setLocalVariable(Map<String, Symbol> localVariable) {
            this.localVariable = localVariable;
        }

        public int getCallerAddress() {
            return callerAddress;
        }

        public void setCallerAddress(int callerAddress) {
            this.callerAddress = callerAddress;
        }

        public int getReturnAddress() {
            return returnAddress;
        }

        public void setReturnAddress(int returnAddress) {
            this.returnAddress = returnAddress;
        }

        public SymbolType getReturnType() {
            return returnType;
        }

        public void setReturnType(SymbolType returnType) {
            this.returnType = returnType;
        }
    }
}