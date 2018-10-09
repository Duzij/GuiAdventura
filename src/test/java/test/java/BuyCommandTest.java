package test.java;
import logika.CommandList;
import logika.GamePlan;
import static org.junit.Assert.*;

import logika.commands.BuyCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BuyCommandTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BuyCommandTest
{
    private GamePlan plan;
    private BuyCommand buyCommand;

    @Before
    public void setUp() {
        plan = new GamePlan();
    }

    @Test
    public void testKoupitBezPenez() {
        buyCommand = new BuyCommand(plan);
        CommandList seznPrikazu = new CommandList();
        seznPrikazu.addCommand(buyCommand);
        assertEquals(buyCommand.execute(), "Bohužel máte nedostatek cigaret.");
    }

    @Test
    public void testKoupit() {
        buyCommand = new BuyCommand(plan);
        plan.setCigarettesCount(30);
        CommandList seznPrikazu = new CommandList();
        seznPrikazu.addCommand(buyCommand);
        assertEquals(buyCommand.execute("dláto"), "Právě jste nakoupili dláto");
        assertEquals(plan.getCigarettesCount(), 11);
        assertEquals(plan.getPlayerItems().contains("dláto"), true);
    }
}
