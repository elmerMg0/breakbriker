
/**
 * Write a description of class Jugador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jugador
{
     String nombre;
     int puntaje;
    public Jugador(String name){
        nombre = name;
        puntaje = 0;
    }
    
    public String toString(){
            return "Nombre Jugador:"+nombre+" ,con un puntaje maximo de:"+puntaje;
    }
    
    public void setScore(int n){
        puntaje = n;
    }
    
    public int getPuntaje(){
        return puntaje;
    }
    
    public String getName(){
        return nombre;
    }
}
