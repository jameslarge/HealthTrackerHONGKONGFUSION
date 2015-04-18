package Model.PhysicalHealth;

public class Weight {
    
    private int grams;

    public Weight(int grams) {
        this.grams = grams;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }
    
    /**
     * Method to convert kilograms to pounds
     * @param kilos value in kilograms
     * @return value in pounds
     */
    public double toPounds(double kilos) {
        return kilos*2.20462;
    }
    
    /**
     * Method to convert pounds to kilogram
     * @param pounds value in Pounds
     * @return value in kilograms
     */
    public double toKilos(double pounds) {
        return pounds*0.453592;
    }
    
    @Override
    public String toString() {
        return Double.toString(toKg());
    }
    
    public String forGraph(){
        return Integer.toString((int)grams);
    }
    
    public double toKg(){
        return grams/1000.0;
    }
    
    
    
   
}
