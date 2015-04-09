package Model.PhysicalHealth;

public class Weight {
    
    private double grams;

    public Weight(double grams) {
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
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
        return ""+grams;
    }
}
