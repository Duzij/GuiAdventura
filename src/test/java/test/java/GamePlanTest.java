package test.java;

import logika.GamePlan;
import logika.Room;
import logika.commands.*;
import logika.situations.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*******************************************************************************
 * Testovací třída GamePlanTest slouží ke komplexnímu otestování
 * třídy GamePlan

 *@author Maxim Dužij
 *@version pro školní rok 2017/2018
 */
public class GamePlanTest
{
    
    GamePlan plan;
    /**
     * Default constructor for test class GamePlanTest
     */
    public GamePlanTest()
    {
         
    }
    
    @Before
    public void setUp() {
        plan = new GamePlan();
    }

    @Test
    public void addCigarettesTest()
    {
        plan.addPlayerCigarettes(20);
        assertEquals(20, plan.getCigarettesCount());
    }
    
    @Test
    public void removeCigarettesTest()
    {
        plan.addPlayerCigarettes(20);
        assertEquals(20, plan.getCigarettesCount());
        plan.removeCigarettes(10);
        assertEquals(10, plan.getCigarettesCount());
    }
    
    @Test
    public void removeSituationCommandsTest()
    {
        Room test = new Room("test", "", false);
        WorkoutSituation s = new WorkoutSituation(plan);
        test.setSituation(s);
        plan.setCurrentRoom(test);
        plan.removeSituationCommands(s.getClass().getName());

        assertFalse(plan.getCurrentRoom().getRoomCommands().isValidCommand("posilovat"));
    }

}
