package semantic.symbol;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public enum SymbolType {
    INT, BOOL;

    public Symbol createSymbol(int address){
        switch (this){
            case INT:
                return new IntSymbol(address);
            case BOOL:
                return new BoolSymbol(address);
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
