package codegenerator.models;

public enum AddressType {
    DIRECT, INDIRECT, IMMEDIATE;

    public Address createAddress(int num, VarType varType) {
        switch (this) {
            case DIRECT:
                return new DirectAddress(num, varType);
            case INDIRECT:
                return new IndirectAddress(num, varType);
            case IMMEDIATE:
                return new ImmediateAddress(num, varType);
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
