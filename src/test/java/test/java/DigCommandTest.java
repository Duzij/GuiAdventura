package test.java;

import logika.CommandList;
import logika.GamePlan;
import logika.commands.DigCommand;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/*******************************************************************************
 * Testovací třída GameTest slouží ke komplexnímu otestování
 * třídy CommandListTest

 *@author Maxim Dužij
 *@version pro školní rok 2017/2018
 */

public class DigCommandTest {

    private GamePlan game;
    private DigCommand digCommand;

    @Before
    public void setUp() {
        game = new GamePlan();
    }

    @Test
    public void testKopatBezInventare() {
        digCommand = new DigCommand(game);
        CommandList seznPrikazu = new CommandList();
        seznPrikazu.addCommand(digCommand);
        assertEquals(digCommand.execute(), "Nemáte všechny tři věci ve svém inventáři. Potřebujete kladivo, dláto a plakát.");
    }

    @Test
    public void testKopatSInventarem() {
        digCommand = new DigCommand(game);
        game.addPlayerItem("plakát");
        game.addPlayerItem("dláto");
        game.addPlayerItem("kladivo");
        CommandList seznPrikazu = new CommandList();
        seznPrikazu.addCommand(digCommand);
        assertEquals(digCommand.execute(), "Začal jste kopat a velice brzo jste se dostal pryč z vězení, gratuluji!");
    }


}
