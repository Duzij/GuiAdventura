package logika.situations;

import logika.GamePlan;
import logika.commands.ICommand;
import logika.commands.RockPaperScissorsGameCommand;

import java.util.HashSet;

public class RockPaperScissorsGameSituation  extends Situation {

    /**
     * Nastaví se potřebné příkazy a pravděpodobnost situace
     * @param plan předá se zde herní plán
     */
    public RockPaperScissorsGameSituation(GamePlan plan) {
        super(plan);
        HashSet<ICommand> commands = new HashSet<>();
        commands.add(new RockPaperScissorsGameCommand(plan));

        setProbability(100);
        setSituationCommands(commands);
        setQuestion("Chcete si zahrát kámen nůžky papír? Kolik budete sázet cigaret?\n" +
                    "(napište číslicí a čím budete hrát např. 'sázet 8 nůžky')\n");
    }

    @Override
    public String getNegativeAnswerConsequences() {
        return "Prohráli jste :(";
    }

    @Override
    public String getPositiveAnswerConsequences() {
        return "Blahopřeji, vyhráli jste dvakrát více cigaret, než jste sázeli.";
    }
}
