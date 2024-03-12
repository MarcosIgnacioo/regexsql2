package regex.tokens;

public class Identifier {
    
    private int id=400;
    private String identifier;
    private int line;
    private int column;

    public String toString(){
        return id+" "+identifier+" "+line+","+column;  
       }

       public Identifier(int id, String identifier, int line, int column) {
        // Asignar los valores de los parámetros a las variables de instancia
        this.id++;
        this.identifier = identifier;
        this.line = line;
        this.column = column;
    }
}
