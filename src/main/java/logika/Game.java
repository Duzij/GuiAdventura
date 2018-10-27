package logika;

import logika.commands.*;

/**
 * Třída Game - třída představující logiku adventury.
 * <p>
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy GamePlan, která inicializuje mistnosti hry
 * a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 * Vypisuje uvítací a ukončovací text hry.
 * Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author Maxim Dužij
 * @version pro školní rok 2018
 */

public class Game implements IGame {
    private GamePlan herniPlan;
    private boolean konecHry;

    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy GamePlan) a seznam platných příkazů.
     */
    public Game() {
        herniPlan = new GamePlan();

    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    public String returnGreetings() {
        return "Vítejte!\n" +
                "Toto je příběh o útěku z vězení.\n" +
                "Probouzite se v cele a musíte najít 3 věci:\n" +
                "Kladivo, dláto a plakát.\n" +
                "Kladivem a dlátem vykopete díru a plakátem jí pak při útěku zakryjete.\n" +
                "\n" +
                "Pro nápovědu můžete po celou dobu hry napsat: nápověda.\n" +
                "\n" +
                herniPlan.getCurrentRoom().getDescription();
    }

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     */
    public String returnEpilogue() {
        return "Gratuluji, vyhráli jste!";
    }

    /**
     * Vrací true, pokud hra skončila.
     */
    public boolean endOfTheGame() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param radek text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    public String executeCommand(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani = " .... ";


        if (herniPlan.getGlobalCommads().isValidCommand(slovoPrikazu)) {
            ICommand prikaz = herniPlan.getGlobalCommads().returnCommand(slovoPrikazu);
            textKVypsani = prikaz.execute(parametry);
        } else {
            if (herniPlan.getCurrentRoomCommads().isValidCommand(slovoPrikazu)) {
                ICommand prikaz = herniPlan.getCurrentRoomCommads().returnCommand(slovoPrikazu);
                textKVypsani = prikaz.execute(parametry);
            }
            else{
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám. ";
            }
        }

        if (getGamePlan().getEndOfTheGame()) {
            setEndGame(true);
        }

        return textKVypsani;
    }


    /**
     * Nastaví, že je konec hry, metodu využívá třída EndGameCommand,
     * mohou ji použít i další implementace rozhraní Prikaz.
     *
     * @param konecHry hodnota false= konec hry, true = hra pokračuje
     */
    public void setEndGame(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return odkaz na herní plán
     */
    public GamePlan getGamePlan() {
        return herniPlan;
    }

    /**
     * Metoda spouští novou hru
     */
    public void startNewGame() {
        herniPlan = new GamePlan();
    }


}

