public class Flugzeug extends Luftfahrzeug {
    private double spannweite;
    private int motorenAnzahl;
    
    public Flugzeug() {
        super();
        this.spannweite = 0.0;
        this.motorenAnzahl = 0;
    }
    
    public Flugzeug(String bezeichnung, double gewicht, int baujahr, double spannweite, int motorenAnzahl) {
        super(bezeichnung, gewicht, baujahr);
        this.spannweite = spannweite;
        this.motorenAnzahl = motorenAnzahl;
    }
    
    public double getSpannweite() {
        return spannweite;
    }
    
    public void setSpannweite(double spannweite) {
        this.spannweite = spannweite;
    }
    
    public int getAnzahlMotoren() {
        return motorenAnzahl;
    }
    
    public void setAnzahlMotoren(int motorenAnzahl) {
        this.motorenAnzahl = motorenAnzahl;
    }
    
    @Override
    public String getDaten() {
        return super.getDaten() + 
               "\nSpannweite: " + spannweite + " m" +
               "\nAnzahl Motoren: " + motorenAnzahl;
    }
} 