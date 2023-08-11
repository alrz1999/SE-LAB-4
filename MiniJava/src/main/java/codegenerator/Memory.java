package codegenerator;

import java.util.ArrayList;

/**
 * Created by mohammad hosein on 6/27/2015.
 */
public class Memory {
    private static final int TEMP_MEMORY_START_ADDRESS = 500;
    private static final int DATA_MEMORY_START_ADDRESS = 200;
    private static final int DATA_SIZE = 4;
    private static final int TEMP_SIZE = 4;
    private final ArrayList<_3AddressCode> codeBlock;
    private int lastTempIndex;
    private int lastDataAddress;

    public Memory() {
        codeBlock = new ArrayList<>();
        lastTempIndex = TEMP_MEMORY_START_ADDRESS;
        lastDataAddress = DATA_MEMORY_START_ADDRESS;
    }

    public int getTemp() {
        lastTempIndex += TEMP_SIZE;
        return lastTempIndex - TEMP_SIZE;
    }

    public int getDateAddress() {
        lastDataAddress += DATA_SIZE;
        return lastDataAddress - DATA_SIZE;
    }

    public int saveMemory() {
        codeBlock.add(new _3AddressCode());
        return codeBlock.size() - 1;
    }

    public void add3AddressCode(Operation op, Address opr1, Address opr2, Address opr3) {
        codeBlock.add(new _3AddressCode(op, opr1, opr2, opr3));
    }

    public void add3AddressCode(int i, Operation op, Address opr1, Address opr2, Address opr3) {
        codeBlock.remove(i);
        codeBlock.add(i, new _3AddressCode(op, opr1, opr2, opr3));
    }

    public int getCurrentCodeBlockAddress() {
        return codeBlock.size();
    }

    public void pintCodeBlock() {
        System.out.println("Code Block");
        for (int i = 0; i < codeBlock.size(); i++) {
            System.out.println(i + " : " + codeBlock.get(i).toString());
        }
    }
}

class _3AddressCode {
    public Operation operation;
    public Address operand1;
    public Address operand2;
    public Address operand3;

    public _3AddressCode() {

    }

    public _3AddressCode(Operation op, Address opr1, Address opr2, Address opr3) {
        operation = op;
        operand1 = opr1;
        operand2 = opr2;
        operand3 = opr3;
    }

    public String toString() {
        if (operation == null) return "";
        StringBuffer res = new StringBuffer("(");
        res.append(operation.toString()).append(",");
        if (operand1 != null) res.append(operand1);
        res.append(",");
        if (operand2 != null) res.append(operand2);
        res.append(",");
        if (operand3 != null) res.append(operand3);
        res.append(")");

        return res.toString();
    }
}
