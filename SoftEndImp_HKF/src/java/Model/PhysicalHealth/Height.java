package Model.PhysicalHealth;

public class Height {
    
    private int centimetres;
    
    public Height() {}

    public Height(int centimetres) {
        this.centimetres = centimetres;
    }

    public int getCentimetres() {
        return centimetres;
    }

    public void setCentimetres(int centimetres) {
        this.centimetres = centimetres;
    }
    
    /**
     * Method to convert feet to metres
     * @param feet value in feet
     * @return value in metres
     */
    public int toMetres(int feet){
        return (int) (feet*0.3048);
    }
    
    /**
     * Method to convert metres to feet
     * @param metres value in metres
     * @return value in feet
     */
    public int toFeet(int metres){
        return (int) (metres*3.2808399);
    }
    
    @Override
    public String toString() { 
        return ""+centimetres;
    }
}
