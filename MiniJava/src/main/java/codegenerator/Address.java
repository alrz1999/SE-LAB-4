package codegenerator;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public class Address {
    private int num;
    private TypeAddress type;
    private VarType varType;

    public Address(int num, VarType varType, TypeAddress type) {
        this.setNum(num);
        this.setType(type);
        this.setVarType(varType);
    }

    public Address(int num, VarType varType) {
        this.setNum(num);
        this.setType(TypeAddress.DIRECT);
        this.setVarType(varType);
    }

    public String toString() {
        switch (getType()) {
            case DIRECT:
                return getNum() + "";
            case INDIRECT:
                return "@" + getNum();
            case IMMEDIATE:
                return "#" + getNum();
        }
        return getNum() + "";
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public TypeAddress getType() {
        return type;
    }

    public void setType(TypeAddress type) {
        this.type = type;
    }

    public VarType getVarType() {
        return varType;
    }

    public void setVarType(VarType varType) {
        this.varType = varType;
    }
}
