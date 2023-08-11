package codegenerator.models;

public enum SymbolType {
    INT, BOOL;

    public Symbol createSymbol(int address) {
        switch (this) {
            case INT:
                return new IntSymbol(address);
            case BOOL:
                return new BoolSymbol(address);
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
