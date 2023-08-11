package semantic.symbol;

import codegenerator.VarType;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public abstract class Symbol {
    public int address;

    public Symbol(int address) {
        this.address = address;
    }

    public abstract VarType getVarType();
}

class IntSymbol extends Symbol {
    public IntSymbol(int address) {
        super(address);
    }

    @Override
    public VarType getVarType() {
        return VarType.INT;
    }
}

class BoolSymbol extends Symbol {
    public BoolSymbol(int address) {
        super(address);
    }

    @Override
    public VarType getVarType() {
        return VarType.BOOL;
    }
}
