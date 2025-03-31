import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Main extends JFrame {
    private static LuftfahrzeugVerwaltung verwaltung = new LuftfahrzeugVerwaltung();
    private JList<String> luftfahrzeugListe;
    private DefaultListModel<String> listModel;
    private JTextArea detailAnzeige;
    
    public Main() {
        // Fenster-Einstellungen
        setTitle("Luftfahrzeug-Verwaltung");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Hauptcontainer
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Linke Seite: Liste und Buttons
        JPanel leftPanel = new JPanel(new BorderLayout());
        
        // Liste
        listModel = new DefaultListModel<>();
        luftfahrzeugListe = new JList<>(listModel);
        luftfahrzeugListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(luftfahrzeugListe);
        
        // Button-Panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        JButton anzeigenButton = new JButton("Details anzeigen");
        JButton neuButton = new JButton("Neu");
        JButton loeschenButton = new JButton("Löschen");
        JButton aktualisierenButton = new JButton("Liste aktualisieren");
        
        buttonPanel.add(anzeigenButton);
        buttonPanel.add(neuButton);
        buttonPanel.add(loeschenButton);
        buttonPanel.add(aktualisierenButton);
        
        leftPanel.add(listScrollPane, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Rechte Seite: Detailansicht
        detailAnzeige = new JTextArea();
        detailAnzeige.setEditable(false);
        JScrollPane detailScrollPane = new JScrollPane(detailAnzeige);
        
        // Layout zusammenfügen
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(detailScrollPane, BorderLayout.CENTER);
        
        // Padding hinzufügen
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel zum Fenster hinzufügen
        add(mainPanel);
        
        // Event-Handler
        anzeigenButton.addActionListener(e -> zeigeDetails());
        neuButton.addActionListener(e -> neuesLuftfahrzeugAnlegen());
        loeschenButton.addActionListener(e -> loescheLuftfahrzeug());
        aktualisierenButton.addActionListener(e -> aktualisiereListe());
        
        // Beispieldaten laden
        verwaltung.addLuftfahrzeug(new Flugzeug("Boeing 747", 183500.0, 1969, 64.4, 4));
        verwaltung.addLuftfahrzeug(new Heissluftballon("Cameron Z-90", 150.0, 2010, 2550.0, 1.2));
        
        // Liste initialisieren
        aktualisiereListe();
    }
    
    private void aktualisiereListe() {
        listModel.clear();
        List<Luftfahrzeug> luftfahrzeuge = verwaltung.getAlleLuftfahrzeuge();
        for (Luftfahrzeug lf : luftfahrzeuge) {
            listModel.addElement(lf.getBezeichnung() + " (" + lf.getClass().getSimpleName() + ")");
        }
    }
    
    private void zeigeDetails() {
        int selectedIndex = luftfahrzeugListe.getSelectedIndex();
        if (selectedIndex >= 0) {
            Luftfahrzeug lf = verwaltung.getLuftfahrzeug(selectedIndex);
            detailAnzeige.setText("Typ: " + lf.getClass().getSimpleName() + "\n\n" + lf.getDaten());
        } else {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie ein Luftfahrzeug aus.");
        }
    }
    
    private void neuesLuftfahrzeugAnlegen() {
        JDialog dialog = new JDialog(this, "Neues Luftfahrzeug", true);
        dialog.setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Typ-Auswahl
        JComboBox<String> typCombo = new JComboBox<>(new String[]{"Flugzeug", "Heißluftballon"});
        
        // Textfelder
        JTextField bezeichnungField = new JTextField(20);
        JTextField gewichtField = new JTextField(20);
        JTextField baujahrField = new JTextField(20);
        JTextField spezial1Field = new JTextField(20);
        JTextField spezial2Field = new JTextField(20);
        
        // Labels
        JLabel spezial1Label = new JLabel("Spannweite (m):");
        JLabel spezial2Label = new JLabel("Anzahl Motoren:");
        
        // Komponenten hinzufügen
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Typ:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        mainPanel.add(typCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Bezeichnung:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(bezeichnungField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Gewicht (kg):"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(gewichtField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Baujahr:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        mainPanel.add(baujahrField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(spezial1Label, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        mainPanel.add(spezial1Field, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        mainPanel.add(spezial2Label, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        mainPanel.add(spezial2Field, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Abbrechen");
        
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        dialog.add(mainPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        // Event-Handler für Typ-Änderung
        typCombo.addActionListener(e -> {
            boolean isFlugzeug = typCombo.getSelectedItem().equals("Flugzeug");
            spezial1Label.setText(isFlugzeug ? "Spannweite (m):" : "Ballonvolumen (m³):");
            spezial2Label.setText(isFlugzeug ? "Anzahl Motoren:" : "Korbhöhe (m):");
        });
        
        // Event-Handler für Buttons
        okButton.addActionListener(e -> {
            try {
                String bezeichnung = bezeichnungField.getText();
                double gewicht = Double.parseDouble(gewichtField.getText());
                int baujahr = Integer.parseInt(baujahrField.getText());
                double spezial1 = Double.parseDouble(spezial1Field.getText());
                double spezial2 = Double.parseDouble(spezial2Field.getText());
                
                if (typCombo.getSelectedItem().equals("Flugzeug")) {
                    verwaltung.addLuftfahrzeug(new Flugzeug(bezeichnung, gewicht, baujahr, spezial1, (int)spezial2));
                } else {
                    verwaltung.addLuftfahrzeug(new Heissluftballon(bezeichnung, gewicht, baujahr, spezial1, spezial2));
                }
                
                aktualisiereListe();
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Bitte geben Sie gültige Zahlen ein.");
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void loescheLuftfahrzeug() {
        int selectedIndex = luftfahrzeugListe.getSelectedIndex();
        if (selectedIndex >= 0) {
            Luftfahrzeug lf = verwaltung.getLuftfahrzeug(selectedIndex);
            int bestaetigung = JOptionPane.showConfirmDialog(
                this,
                "Möchten Sie das folgende Luftfahrzeug wirklich löschen?\n" +
                lf.getBezeichnung() + " (" + lf.getClass().getSimpleName() + ")",
                "Löschen bestätigen",
                JOptionPane.YES_NO_OPTION
            );
            
            if (bestaetigung == JOptionPane.YES_OPTION) {
                verwaltung.removeLuftfahrzeug(selectedIndex);
                aktualisiereListe();
                detailAnzeige.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie ein Luftfahrzeug aus.");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
} 