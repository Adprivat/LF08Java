public abstract class Luftfahrzeug {
    private String bezeichnung;
    private double gewicht;
    private int baujahr;
    
    public Luftfahrzeug() {
        this.bezeichnung = "";
        this.gewicht = 0.0;
        this.baujahr = 0;
    }
    
    public Luftfahrzeug(String bezeichnung, double gewicht, int baujahr) {
        this.bezeichnung = bezeichnung;
        this.gewicht = gewicht;
        this.baujahr = baujahr;
    }
    
    public String getBezeichnung() {
        return bezeichnung;
    }
    
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    
    public double getGewicht() {
        return gewicht;
    }
    
    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }
    
    public int getBaujahr() {
        return baujahr;
    }
    
    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }
    
    public String getDaten() {
        return "Bezeichnung: " + bezeichnung + 
               "\nGewicht: " + gewicht + " kg" +
               "\nBaujahr: " + baujahr;
    }
} 