import java.util.ArrayList;
import java.util.List;

public class LuftfahrzeugVerwaltung {
    private List<Luftfahrzeug> luftfahrzeuge;
    
    public LuftfahrzeugVerwaltung() {
        this.luftfahrzeuge = new ArrayList<>();
    }
    
    public void addLuftfahrzeug(Luftfahrzeug luftfahrzeug) {
        luftfahrzeuge.add(luftfahrzeug);
    }
    
    public boolean removeLuftfahrzeug(int index) {
        if (index >= 0 && index < luftfahrzeuge.size()) {
            luftfahrzeuge.remove(index);
            return true;
        }
        return false;
    }
    
    public Luftfahrzeug getLuftfahrzeug(int index) {
        if (index >= 0 && index < luftfahrzeuge.size()) {
            return luftfahrzeuge.get(index);
        }
        return null;
    }
    
    public List<Luftfahrzeug> getAlleLuftfahrzeuge() {
        return new ArrayList<>(luftfahrzeuge);
    }
    
    public int getAnzahlLuftfahrzeuge() {
        return luftfahrzeuge.size();
    }
} 