public class Heissluftballon extends Luftfahrzeug {
    private double ballonVolumen;
    private double korbhoehe;
    
    public Heissluftballon() {
        super();
        this.ballonVolumen = 0.0;
        this.korbhoehe = 0.0;
    }
    
    public Heissluftballon(String bezeichnung, double gewicht, int baujahr, double ballonVolumen, double korbhoehe) {
        super(bezeichnung, gewicht, baujahr);
        this.ballonVolumen = ballonVolumen;
        this.korbhoehe = korbhoehe;
    }
    
    public double getBallonVolumen() {
        return ballonVolumen;
    }
    
    public void setBallonVolumen(double ballonVolumen) {
        this.ballonVolumen = ballonVolumen;
    }
    
    public double getKorbhoehe() {
        return korbhoehe;
    }
    
    public void setKorbhoehe(double korbhoehe) {
        this.korbhoehe = korbhoehe;
    }
    
    @Override
    public String getDaten() {
        return super.getDaten() + 
               "\nBallonvolumen: " + ballonVolumen + " m³" +
               "\nKorbhöhe: " + korbhoehe + " m";
    }
} 