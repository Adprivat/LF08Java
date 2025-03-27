import java.util.List;
import java.util.Scanner;

public class Main {
    private static LuftfahrzeugVerwaltung verwaltung = new LuftfahrzeugVerwaltung();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean running = true;
        
        // Beispieldaten zum Testen
        verwaltung.addLuftfahrzeug(new Flugzeug("Boeing 747", 183500.0, 1969, 64.4, 4));
        verwaltung.addLuftfahrzeug(new Heissluftballon("Cameron Z-90", 150.0, 2010, 2550.0, 1.2));
        
        while (running) {
            zeigeHauptmenue();
            int auswahl = liesGanzzahl("Wählen Sie eine Option: ");
            
            switch (auswahl) {
                case 1:
                    zeigeAlleLuftfahrzeuge();
                    break;
                case 2:
                    zeigeLuftfahrzeug();
                    break;
                case 3:
                    neuesLuftfahrzeugAnlegen();
                    break;
                case 4:
                    loescheLuftfahrzeug();
                    break;
                case 0:
                    running = false;
                    System.out.println("Programm wird beendet. Auf Wiedersehen!");
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte erneut versuchen.");
            }
        }
        
        scanner.close();
    }
    
    private static void zeigeHauptmenue() {
        System.out.println("\n==== Luftfahrzeug-Verwaltung ====");
        System.out.println("1. Alle Luftfahrzeuge anzeigen");
        System.out.println("2. Detailansicht eines Luftfahrzeugs");
        System.out.println("3. Neues Luftfahrzeug anlegen");
        System.out.println("4. Luftfahrzeug löschen");
        System.out.println("0. Beenden");
    }
    
    private static void zeigeAlleLuftfahrzeuge() {
        List<Luftfahrzeug> luftfahrzeuge = verwaltung.getAlleLuftfahrzeuge();
        
        if (luftfahrzeuge.isEmpty()) {
            System.out.println("Keine Luftfahrzeuge vorhanden.");
            return;
        }
        
        System.out.println("\n-- Liste aller Luftfahrzeuge --");
        for (int i = 0; i < luftfahrzeuge.size(); i++) {
            Luftfahrzeug lf = luftfahrzeuge.get(i);
            System.out.println((i + 1) + ". " + lf.getBezeichnung() + " (" + lf.getClass().getSimpleName() + ")");
        }
    }
    
    private static void zeigeLuftfahrzeug() {
        zeigeAlleLuftfahrzeuge();
        
        if (verwaltung.getAnzahlLuftfahrzeuge() == 0) {
            return;
        }
        
        int auswahl = liesGanzzahl("Wählen Sie ein Luftfahrzeug (1-" + verwaltung.getAnzahlLuftfahrzeuge() + "): ");
        
        if (auswahl < 1 || auswahl > verwaltung.getAnzahlLuftfahrzeuge()) {
            System.out.println("Ungültige Auswahl.");
            return;
        }
        
        Luftfahrzeug lf = verwaltung.getLuftfahrzeug(auswahl - 1);
        System.out.println("\n-- Detailansicht --");
        System.out.println("Typ: " + lf.getClass().getSimpleName());
        System.out.println(lf.getDaten());
    }
    
    private static void neuesLuftfahrzeugAnlegen() {
        System.out.println("\n-- Neues Luftfahrzeug anlegen --");
        System.out.println("Typ auswählen:");
        System.out.println("1. Flugzeug");
        System.out.println("2. Heißluftballon");
        
        int typ = liesGanzzahl("Wählen Sie den Typ (1-2): ");
        
        if (typ != 1 && typ != 2) {
            System.out.println("Ungültige Auswahl.");
            return;
        }
        
        scanner.nextLine(); // Puffer leeren
        System.out.print("Bezeichnung: ");
        String bezeichnung = scanner.nextLine();
        
        double gewicht = liesKommazahl("Gewicht (kg): ");
        int baujahr = liesGanzzahl("Baujahr: ");
        
        if (typ == 1) {
            double spannweite = liesKommazahl("Spannweite (m): ");
            int motorenAnzahl = liesGanzzahl("Anzahl Motoren: ");
            
            verwaltung.addLuftfahrzeug(new Flugzeug(bezeichnung, gewicht, baujahr, spannweite, motorenAnzahl));
            System.out.println("Flugzeug wurde hinzugefügt.");
        } else {
            double volumen = liesKommazahl("Ballonvolumen (m³): ");
            double korbhoehe = liesKommazahl("Korbhöhe (m): ");
            
            verwaltung.addLuftfahrzeug(new Heissluftballon(bezeichnung, gewicht, baujahr, volumen, korbhoehe));
            System.out.println("Heißluftballon wurde hinzugefügt.");
        }
    }
    
    private static void loescheLuftfahrzeug() {
        zeigeAlleLuftfahrzeuge();
        
        if (verwaltung.getAnzahlLuftfahrzeuge() == 0) {
            return;
        }
        
        int auswahl = liesGanzzahl("Welches Luftfahrzeug soll gelöscht werden? (1-" + verwaltung.getAnzahlLuftfahrzeuge() + "): ");
        
        if (auswahl < 1 || auswahl > verwaltung.getAnzahlLuftfahrzeuge()) {
            System.out.println("Ungültige Auswahl.");
            return;
        }
        
        Luftfahrzeug lf = verwaltung.getLuftfahrzeug(auswahl - 1);
        System.out.println("Möchten Sie das folgende Luftfahrzeug wirklich löschen?");
        System.out.println(lf.getBezeichnung() + " (" + lf.getClass().getSimpleName() + ")");
        
        scanner.nextLine(); // Puffer leeren
        System.out.print("Löschen bestätigen (j/n)? ");
        String bestaetigung = scanner.nextLine().trim().toLowerCase();
        
        if (bestaetigung.equals("j")) {
            verwaltung.removeLuftfahrzeug(auswahl - 1);
            System.out.println("Luftfahrzeug wurde gelöscht.");
        } else {
            System.out.println("Löschvorgang abgebrochen.");
        }
    }
    
    private static int liesGanzzahl(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Bitte geben Sie eine gültige Zahl ein.");
                scanner.nextLine(); // Puffer leeren
            }
        }
    }
    
    private static double liesKommazahl(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Bitte geben Sie eine gültige Zahl ein.");
                scanner.nextLine(); // Puffer leeren
            }
        }
    }
} 