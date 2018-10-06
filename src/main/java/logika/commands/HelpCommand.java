package logika.commands;


import logika.GamePlan;

/**
 * Třída HelpCommand implementuje pro hru příkaz napoveda.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Maxim Dužij
 * @version pro školní rok 2018
 */
public class HelpCommand implements ICommand {

    private static final String NAME = "nápověda";
    private GamePlan plan;


    /**
     * Konstruktor třídy
     *
     * @param plan herní plán,
     *                      ze kterého je možné ve hře použít,
     *                      aby je nápověda mohla zobrazit uživateli.
     */
    public HelpCommand(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Vrací základní nápovědu po zadání příkazu "napoveda".
     * Vypisuje se hlavní cíl hry, seznam globálně dostupných příkazů (v každé místnosti).
     * Taktéž se vypíšou příkazy dostupné jenom v tomto pokoji a kudy můžu z tohoto pokoje odejít
     *
     * @return napoveda ke hre
     */
    @Override
    public String execute(String... parametry) {
        return    "Tvým úkolem je vykopat díru v cele a dostat se ven.\n"
                + "Musíš mít ve svém inventáři kladivo, dláto a plakát zároveň.\n"
                + "\n"
                + "Můžeš zadat tyto příkazy:\n"
                + plan.getGlobalCommads().returnCommandList()
                + plan.getCurrentRoomCommads().returnCommandList() + "\n"
                + plan.getCurrentRoom().exitsDescription() + "\n";
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
