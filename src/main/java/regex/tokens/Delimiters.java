package regex.tokens;

public class Delimiters {
    private static int serial = 50;
    private int id = 0;
    private String delimiter;

    public String toString() {
        return "DELIMITER: " + id + " " +  delimiter;
    }

    public Delimiters(String delimiter){
        this.id = serial++;
        this.delimiter = delimiter;
    }

}
