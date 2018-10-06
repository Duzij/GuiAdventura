package uiText;

import logika.IGame;

import java.io.*;
import java.util.*;

/**
 * Class TextInterface
 * <p>
 * Toto je uživatelského rozhraní aplikace Adventura
 * Tato třída vytváří instanci třídy Game, která představuje logiku aplikace.
 * Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */

public class TextInterface {
    private IGame game;

    /**
     * Vytváří hru.
     */
    public TextInterface(IGame hra) {
        this.game = hra;
    }

    /**
     * Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     * příkazu od hráče do konce hry (dokud metoda endOfTheGame() z logiky nevrátí
     * hodnotu true). Nakonec vypíše text epilogu.
     */
    public void play(String fileName) {
        System.out.println(game.returnGreetings());

        List<String> commands = ReadFile(fileName);
        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

            if (commands != null && !commands.isEmpty()) {
                int index = 0;
                    while (!game.endOfTheGame()) {
                        for (String command : commands) {
                        if(commands.size() != index) {
                            index++;
                            System.out.println(game.executeCommand(command));
                        }
                        else{
                            String radek = readString();
                            System.out.println(game.executeCommand(radek));
                        }
                }
            }} else {
                while (!game.endOfTheGame()) {
                    String radek = readString();
                    System.out.println(game.executeCommand(radek));
                }
            }
            System.out.println(game.returnEpilogue());
    }

    public List<String> ReadFile(String filename) {
        List<String> records = new ArrayList<String>();
        if(!filename.isEmpty()){
        try {
            String path = new File(filename).getAbsoluteFile().toString();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line.replaceAll("\\P{Print}",""));
            }
            reader.close();
            return records;
        } catch (Exception e) {
            System.out.println("Nastala chyba při čtení debug souboru: " + filename);
            e.printStackTrace();
            return null;
        }}
        return null;
    }

    /**
     * Metoda přečte příkaz z příkazového řádku
     *
     * @return Vrací přečtený příkaz jako instanci třídy String
     */
    private String readString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void play() {
        this.play("");
    }
}
