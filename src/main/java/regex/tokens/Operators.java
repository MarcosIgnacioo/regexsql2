package regex.tokens;

public class Operators {
    private static int serial = 70;
    private int id = 0;
    private String operator;

    public String toString() {
        return "OPERATOR: " + id + " " +  operator;
    }

    public Operators(String operator){
        this.id = serial++;
        this.operator = operator;
    }
}
