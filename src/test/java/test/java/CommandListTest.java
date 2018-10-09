package test.java;

import logika.CommandList;
import logika.GamePlan;
import logika.commands.EndGameCommand;
import logika.commands.GoCommand;
import org.junit.*;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída GameTest slouží ke komplexnímu otestování
 * třídy CommandListTest

 *@author Maxim Dužij
 *@version pro školní rok 2017/2018
 */

public class CommandListTest {
    private logika.GamePlan game;
    private EndGameCommand endGameCommand;
    private GoCommand prJdi;

    @Before
    public void setUp() {
        game = new GamePlan();
        endGameCommand = new EndGameCommand(game);
        prJdi = new GoCommand(game);
    }

    @Test
    public void testVlozeniVybrani() {
        CommandList seznPrikazu = new CommandList();
        seznPrikazu.addCommand(endGameCommand);
        seznPrikazu.addCommand(prJdi);
        assertEquals(endGameCommand, seznPrikazu.returnCommand("konec"));
        assertEquals(prJdi, seznPrikazu.returnCommand("jdi"));
        assertEquals(null, seznPrikazu.returnCommand("nápověda"));
    }

    @Test
    public void testJePlatnyPrikaz() {
        CommandList seznPrikazu = new CommandList();
        seznPrikazu.addCommand(endGameCommand);
        seznPrikazu.addCommand(prJdi);
        assertEquals(true, seznPrikazu.isValidCommand("konec"));
        assertEquals(true, seznPrikazu.isValidCommand("jdi"));
        assertEquals(false, seznPrikazu.isValidCommand("nápověda"));
        assertEquals(false, seznPrikazu.isValidCommand("Konec"));
    }

    @Test
    public void testNazvyPrikazu() {
        CommandList seznPrikazu = new CommandList();
        seznPrikazu.addCommand(endGameCommand);
        seznPrikazu.addCommand(prJdi);
        String nazvy = seznPrikazu.returnCommandList();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("nápověda"));
        assertEquals(false, nazvy.contains("Konec"));
    }

}
