package logika.situations;

import logika.GamePlan;
import logika.commands.ICommand;
import logika.commands.TilePuzzleNumberCommand;


import java.util.HashSet;

public class TilePuzzleSituation extends Situation {

    /**
     * Nastaví se potřebné příkazy a pravděpodobnost situace
     * @param plan předá se zde herní plán
     */
    public TilePuzzleSituation(GamePlan plan) {
        super(plan);
        HashSet<ICommand> commands = new HashSet<>();
        commands.add(new TilePuzzleNumberCommand(plan));

        setProbability(100);
        setSituationCommands(commands);
        setQuestion("Možná půjde nějaká dlaždička posunout...");
    }

    @Override
    public String getNegativeAnswerConsequences() {
        return "Vypadá to jako obyčejná dlaždička.\nNic zde není.";
    }

    /**
     *  Pravděpodobnost této situace nastaví na nulu, protože skrýš již bude vždy prázdná
     */
    @Override
    public String getPositiveAnswerConsequences() {
        getPlan().addPlayerCigarettes(20);
        getPlan().getCurrentRoom().getSituation(this.getClass().getName()).setProbability(0);
        return "Výborně! Našli jste tajnou skrýš.\n" +
                "Ve skrýši jste našli 20 cigaret.";
    }
}
