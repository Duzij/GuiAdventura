package logika.commands;

import logika.GamePlan;

/**
 * Třída EndGameCommand implementuje pro hru příkaz konec.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Maxim Dužij
 * @version pro školní rok 2018
 */
public class EndGameCommand implements ICommand {

    private static final String NAME = "konec";

    private GamePlan game;

    /**
     * Konstruktor třídy
     *
     * @param hra odkaz na hru, která má být příkazem konec ukončena
     */
    public EndGameCommand(GamePlan hra) {
        this.game = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     *
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String execute(String... params) {
        if (params.length > 0) {
            return "Ukončit co? Nechápu, proč jste zadal druhé slovo.";
        } else {
            game.quitGame();
            return "hra ukončena příkazem konec";
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
