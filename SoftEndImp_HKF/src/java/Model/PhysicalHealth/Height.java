package Model.PhysicalHealth;

public class Height {
    
    private double centimetres;

    public Height(double centimetres) {
        this.centimetres = centimetres;
    }

    public double getCentimetres() {
        return centimetres;
    }

    public void setCentimetres(double centimetres) {
        this.centimetres = centimetres;
    }
    
    /**
     * Method to convert feet to metres
     * @param feet value in feet
     * @return value in metres
     */
    public double toMetres(double feet){
        return feet*0.3048;
    }
    
    /**
     * Method to convert metres to feet
     * @param metres value in metres
     * @return value in feet
     */
    public double toFeet(double metres){
        return metres*3.2808399;
    }
}
