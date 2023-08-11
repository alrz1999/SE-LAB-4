package parser;

enum Act {
    SHIFT, REDUCE, ACCEPT
}

public class Action {
    private Act action;
    private int number;

    public Action(Act action, int number) {
        this.setAction(action);
        this.setNumber(number);
    }

    public String toString() {
        switch (getAction()) {
            case ACCEPT:
                return "acc";
            case SHIFT:
                return "s" + getNumber();
            case REDUCE:
                return "r" + getNumber();
        }
        return getAction().toString() + getNumber();
    }

    public Act getAction() {
        return action;
    }

    public void setAction(Act action) {
        this.action = action;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
