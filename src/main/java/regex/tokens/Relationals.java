package regex.tokens;

public class Relationals {
    private static int serial = 80;
    private int id = 0;
    private String relational;

    public String toString() {
        return "RELATIONAL: " + id + " " +  relational;
    }

    public Relationals(String relational){
        this.id = serial++;
        this.relational = relational;
    }
}
