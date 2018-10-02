package logika.commands;

import logika.GamePlan;
import logika.Room;

/**
 * Třída GoCommand implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Maxim Dužij
 * @version pro školní rok 2018
 */
public class GoCommand implements ICommand {
    private static final String NAME = "jdi";
    private GamePlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public GoCommand(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     * existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param params - jako  parametr obsahuje jméno prostoru (východu),
     *                  do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String execute(String... params) {
        if (params.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = params[0];

        // zkoušíme přejít do sousedního prostoru
        Room nextRoom = plan.getCurrentRoom().returnNextdoor(smer);

        if (nextRoom == null) {
            return "Tam se odsud jít nedá!";
        } else {
            if (nextRoom.isFinal) {
                plan.quitGame();
                return "Vyhráli jste hru";
            } else {
                plan.setCurrentRoom(nextRoom);
                return  nextRoom.getDescription();
            }
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @ return nazev prikazu
     */
    @Override
    public String getName() {
        return NAME;
    }

}
