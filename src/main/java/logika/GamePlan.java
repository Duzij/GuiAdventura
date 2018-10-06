package logika;



import logika.commands.*;
import logika.situations.*;

import java.util.HashSet;

/**
 * Class GamePlan - třída představující mapu a stav adventury.
 * <p>
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny Roomy, průchody mezi nimi
 * nastavuje případné příkazy navíc a potřebné situace
 * <p>
 * V této třídě je uložen aktuální Room, ve kterém se hráč právě nachází,
 * jeho úroveň síly power, počet cigaret cigarettesCount, věci v inventáři playerItems
 *@author Maxim Dužij
 *@version pro školní rok 2017/2018
 */
public class GamePlan {

    private Room currentRoom;
    private Boolean endOfTheGame;
    private int cigarettesCount;
    private int power;
    private HashSet<String> playerItems = new HashSet();
    private Boolean helpedGuy;

    public CommandList getGlobalCommads() {
        return globalCommads;
    }

    private CommandList globalCommads;    // seznam příkazů, které jsou dostupné ve všech místnostech

    public CommandList getCurrentRoomCommads() {
        return currentRoomCommads;
    }

    private CommandList currentRoomCommads;    // seznam příkazů, které jsou dostupné ve konkrétní místnostech


    /**
     *  Konstruktor který vytváří jednotlivé Roomy a propojuje je pomocí východů.
     *  Jako výchozí aktuální Room nastaví halu.
     */
    public GamePlan() {
        defineGameRooms();

        this.endOfTheGame = false;
        this.cigarettesCount = 0;
        this.power = 0;
        this.helpedGuy = false;

        globalCommads = new CommandList();
        globalCommads.addCommand(new HelpCommand(this));
        globalCommads.addCommand(new GoCommand(this));
        globalCommads.addCommand(new EndGameCommand(this));
        globalCommads.addCommands(new InventoryCommand(this));
    }

    public void quitGame() {
        this.endOfTheGame = true;
    }

    public Boolean getEndOfTheGame() {
        return this.endOfTheGame;
    }

    /**
     *  Vytváří jednotlivé Roomy a propojuje je pomocí východů.
     *  Jako výchozí aktuální Room nastaví domeček.
     */
    private void defineGameRooms() {
        Room cela = new Room("cela", "Je to tvoje cela, ale nejsi tu nadlouho. Pokud se v tvém inventáři objeví zároveň plakát, dláto a kladivo, můžeš začít kopat díru a utéct", false);
        Room predCelou = new Room("pred_celou", "Stojíš před svojí celou", false);
        Room toalety = new Room("toalety", "Staré plesnivé toalety", false);
        Room kabinka = new Room("kabinka", "Stará plesnivá kabinka, na dveřích které vidíš matematickou rovnici: x * 2 - 2 = 18", false);
        Room vychazkovy_prostor = new Room("vychazkovy_prostor", "zde vězni tráví svůj volný čas", false);
        Room misto_pro_posilovani = new Room("misto_pro_posilovani", "tady si můžete zaposilovat", false);
        Room skupinka_veznu = new Room("skupinka_veznu", "stojíte uprostřed menší skupinky vězňu a oni se Vás nepřátelsky tváří", false);
        Room hra_kamen_nuzky = new Room("hra_kamen_nuzky", "zde můžete vyhrát nějaké cigarety navíc", false);

        definceExits(cela, predCelou, toalety, kabinka, vychazkovy_prostor, misto_pro_posilovani, skupinka_veznu, hra_kamen_nuzky);
        defineCommands(cela, kabinka, hra_kamen_nuzky);
        defineSituations(cela, predCelou, toalety, kabinka, vychazkovy_prostor, misto_pro_posilovani, skupinka_veznu, hra_kamen_nuzky);

        setCurrentRoom(cela);
    }

    /**
     *  Metoda definuje východy pro jednotlivé pokoje
     */
    private void definceExits(Room cela, Room predCelou, Room toalety, Room kabinka, Room vychazkovy_prostor, Room misto_pro_posilovani, Room skupinka_veznu, Room hra_kamen_nuzky) {
        cela.setExit(predCelou);
        predCelou.setExit(cela, toalety, vychazkovy_prostor);
        toalety.setExit(predCelou, kabinka);
        kabinka.setExit(toalety);
        vychazkovy_prostor.setExit(skupinka_veznu, misto_pro_posilovani, hra_kamen_nuzky, predCelou);
        skupinka_veznu.setExit(vychazkovy_prostor);
        misto_pro_posilovani.setExit(vychazkovy_prostor);
        hra_kamen_nuzky.setExit(vychazkovy_prostor);
    }

    /**
     *  Metoda přiřazuje příkazy dostupné jenom v těchto pokojích
     */
    private void defineCommands(Room cela, Room kabinka, Room hra_kamen_nuzky) {
        cela.setRoomCommands(new DigCommand(this));
        kabinka.setRoomCommands(new TilePuzzleNumberCommand(this));
        hra_kamen_nuzky.setRoomCommands(new RockPaperScissorsGameCommand(this));
    }

    /**
     *  Metoda přiřazuje situace jednotlivým pokojům
     */
    private void defineSituations(Room cela, Room predCelou, Room toalety, Room kabinka, Room vychazkovy_prostor, Room misto_pro_posilovani, Room skupinka_veznu, Room hra_kamen_nuzky) {
        kabinka.setSituation(new TilePuzzleSituation(this));
        kabinka.setSituation(new FoundCigaretteSituation(this));
        predCelou.setSituation(new HelpGuySituation(this));
        predCelou.setSituation(new FoundCigaretteSituation(this));
        hra_kamen_nuzky.setSituation(new RockPaperScissorsGameSituation(this));
        hra_kamen_nuzky.setSituation(new FoundCigaretteSituation(this));
        vychazkovy_prostor.setSituation(new FoundCigaretteSituation(this));
        cela.setSituation(new FoundCigaretteSituation(this));
        toalety.setSituation(new FoundCigaretteSituation(this));
        misto_pro_posilovani.setSituation(new WorkoutSituation(this));
        skupinka_veznu.setSituation(new ShopEnterSituation(this));
    }

    /**
     *  Metoda vrací odkaz na aktuální Room, ve ktetém se hráč právě nachází.
     *
     *@return aktuální Room
     */

    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     *  Metoda nastaví aktuální Room, používá se nejčastěji při přechodu mezi Roomy
     *  Taktéž se nastaví validní příkazy, které se můžou v této místnosti použít
     *
     *@param  Room nový aktuální Room
     */
    public void setCurrentRoom(Room Room) {
        currentRoom = Room;
        currentRoomCommads = Room.getRoomCommands();
    }

    public int getCigarettesCount() {
        return cigarettesCount;
    }

    public HashSet<String> getPlayerItems() {
        return playerItems;
    }

    public void addPlayerItem(String item) {
        getPlayerItems().add(item);
    }

    public void addPlayerCigarettes(int number) {
        setCigarettesCount(getCigarettesCount() + number);
    }

    public void setCigarettesCount(int cigarettesCount) {
        this.cigarettesCount = cigarettesCount;
    }

    public void removeCigarettes(int number) {
        setCigarettesCount(getCigarettesCount() - number);
    }

    /**
     * Příkaz vymaže příkazy konkrétní situace
     */
    public void removeSituationCommands(String situationName) {
        currentRoomCommads.removeCommand(currentRoom.getSituation(situationName).getSituationCommands());
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Boolean getHelpedGuy() {
        return helpedGuy;
    }

    public void setHelpedGuy(Boolean helpedGuy) {
        this.helpedGuy = helpedGuy;
    }
}
