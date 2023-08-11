package semantic.symbol;

import codegenerator.VarType;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public abstract class Symbol {
    private int address;

    public Symbol(int address) {
        this.setAddress(address);
    }

    public abstract VarType getVarType();

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }
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
