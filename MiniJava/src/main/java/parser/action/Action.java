package parser.action;

public class Action {
    private ActionType action;
    private int number;

    public Action(ActionType action, int number) {
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

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
