package test.java;


import logika.GamePlan;
import logika.Room;
import logika.situations.WorkoutSituation;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * The test class WorkoutCommandTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WorkoutCommandTest
{
    
    private GamePlan plan;
    private logika.commands.WorkoutCommand workoutCommand;

    @Before
    public void setUp() {
        plan = new GamePlan();
        Room room = new Room("test", "test", false);
        workoutCommand = new logika.commands.WorkoutCommand(plan);
        room.setRoomCommands(workoutCommand);
        room.setSituation(new WorkoutSituation(plan));
        plan.setCurrentRoom(room);
    }

    @Test
    public void testNedostatecnePosilovat() {
        assertEquals(workoutCommand.execute("................."), "Tento počet teček je nedostačujicí pro získání dalšího bodu v aktivitě posilování :(");
    }

    @Test
    public void testPosilovat() {
        assertEquals(workoutCommand.execute("................................."), "Blahopřeji, dostali jste se na další úroveň síly díky posilování!\nMomentální úroveň síly je: 1");
    }

}
