package logika;

import logika.commands.ICommand;
import logika.situations.Situation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Room - popisuje jednotlivé prostory (místnosti) hry
 * <p>
 * Tato třída je součástí jednoduché textové hry.
 * <p>
 * "Room" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Room může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Maxim Dužij
 * @version pro školní rok 2018
 */
public class Room {

    public Boolean isFinal;
    private String name;
    private String description;
    private Set<Room> exits;
    private List<Situation> situations;

    private CommandList roomCommands;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param name        name prostoru, jednoznačný identifikátor, jedno slovo nebo
     *                    víceslovný název bez mezer.
     * @param description Popis prostoru.
     */
    public Room(String name, String description, Boolean isFinal) {
        this.name = name;
        this.description = description;
        this.isFinal = isFinal;
        exits = new HashSet<>();
        roomCommands = new CommandList();

        situations = new ArrayList<>();
    }

    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean vyherny) {
        isFinal = vyherny;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param exit prostor, který sousedi s aktualnim prostorem.
     */
    public void setExit(Room exit) {
        exits.add(exit);
    }

    public void setExit(Room... exists) {
        for (Room exit : exists)
        {
            exits.add(exit);
        }
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     * <p>
     * Bližší description metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Room)) {
            return false;    // pokud parametr není typu Room, vrátíme false
        }
        // přetypujeme parametr na typ Room 
        Room druhy = (Room) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.name, druhy.name));
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * description pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.name);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getName() {
        return name;
    }

    /**
     * Vrací "dlouhý" description prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. exits:
     * chodba bufet ucebna
     *
     * @return Dlouhý description prostoru
     */
    public String getDescription() {
        String returnText = "Nacházíš se v místnosti " + name + "\n"
                + description + ".\n";

        returnText += !exits.isEmpty() ? exitsDescription() + ".\n" : "";

        if(getSituations() != null)
        {
            returnText += executeSituation();
        }

        return  returnText;
    }

    /**
     * Otázka se vrátí pouze když bude dostatečně velká pravděpodobnost
     *
     */
    private String executeSituation() {
        Random r = new Random();
        for (Situation situation : getSituations()) {
            if (r.nextInt(100) < situation.getProbability()) {
                this.getRoomCommands().addCommands(situation.getSituationCommands());
                return situation.getQuestion();
            }
        }
        return "";
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "východy: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    public String exitsDescription() {
        String vracenyText = "východy:";
        for (Room sousedni : exits) {
            vracenyText += " " + sousedni.getName();
        }
        return vracenyText;
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Room, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Room returnNextdoor(String nazevSouseda) {
        List<Room> hledaneProstory =
                exits.stream()
                        .filter(sousedni -> sousedni.getName().equals(nazevSouseda))
                        .collect(Collectors.toList());
        if (hledaneProstory.isEmpty()) {
            return null;
        } else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Room.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Room> getExits() {
        return Collections.unmodifiableCollection(exits);
    }

    public CommandList getRoomCommands() {
        return this.roomCommands;
    }

    public void setRoomCommands(CommandList roomCommands) {
        this.roomCommands = roomCommands;
    }

    public void setRoomCommands(ICommand command) {
        if(!getRoomCommands().returnCommandList().isEmpty())
        {
            getRoomCommands().addCommands(command);
        }
        else{
            this.roomCommands=new CommandList();
            this.roomCommands.addCommands(command);
        }
    }

    public Situation getSituation(String situation) {
        for (Situation s : getSituations())
        {
            if(s.getClass().getName().equals(situation))
            {
                return s;
            }
        }
        return null;

    }

    public List<Situation> getSituations() {
        return situations;
    }

    public void setSituations(List<Situation> situations) {
        this.situations = situations;
    }

    public void setSituation(Situation situation) {
        getSituations().add(situation);
    }
}
