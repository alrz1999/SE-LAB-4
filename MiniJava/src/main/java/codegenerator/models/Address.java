package codegenerator.models;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public abstract class Address {
    private int num;
    private VarType varType;

    public Address(int num, VarType varType) {
        this.setNum(num);
        this.setVarType(varType);
    }

    public abstract String toString();

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public abstract AddressType getType();

    public VarType getVarType() {
        return varType;
    }

    public void setVarType(VarType varType) {
        this.varType = varType;
    }
}

class DirectAddress extends Address {
    public DirectAddress(int num, VarType varType) {
        super(num, varType);
    }

    @Override
    public String toString() {
        return getNum() + "";
    }

    @Override
    public AddressType getType() {
        return AddressType.DIRECT;
    }
}

class IndirectAddress extends Address {
    public IndirectAddress(int num, VarType varType) {
        super(num, varType);
    }

    @Override
    public String toString() {
        return "@" + getNum();
    }

    @Override
    public AddressType getType() {
        return AddressType.INDIRECT;
    }
}

class ImmediateAddress extends Address {
    public ImmediateAddress(int num, VarType varType) {
        super(num, varType);
    }

    @Override
    public String toString() {
        return "#" + getNum();
    }

    @Override
    public AddressType getType() {
        return AddressType.IMMEDIATE;
    }
}