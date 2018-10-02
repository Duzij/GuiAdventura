package logika.situations;

import logika.GamePlan;
import logika.commands.ICommand;
import logika.commands.WorkoutCommand;

import java.util.HashSet;

public class WorkoutSituation  extends Situation {

    /**
     * Nastaví se potřebné příkazy a pravděpodobnost situace
     * @param plan předá se zde herní plán
     */
    public WorkoutSituation(GamePlan plan) {
        super(plan);
        HashSet<ICommand> commands = new HashSet<>();
        commands.add(new WorkoutCommand(plan));

        setProbability(100);
        setSituationCommands(commands);
        setQuestion("Chcete si zde zaposilovat?\n" +
                    "(Napište příkaz posilovat a po mezeře počet teček větší než 20)\n");
    }

    @Override
    public String getNegativeAnswerConsequences() {
        return "Tento počet teček je nedostačujicí pro získání dalšího bodu v aktivitě posilování :(";
    }

    @Override
    public String getPositiveAnswerConsequences() {
        return "Blahopřeji, dostali jste se na další úroveň síly díky posilování!\n" +
                "Momentální úroveň síly je: " + getPlan().getPower();
    }
}
