package logika.situations;

import logika.GamePlan;
import logika.commands.ICommand;
import logika.commands.NoCommand;
import logika.commands.YesCommand;

import java.util.HashSet;

public class FoundCigaretteSituation  extends Situation {

    /**
     * Nastaví se potřebné příkazy a pravděpodobnost situace
     * @param plan předá se zde herní plán
     */
    public FoundCigaretteSituation(GamePlan plan) {
        super(plan);
        HashSet<ICommand> commands = new HashSet<>();
        commands.add(new YesCommand(plan, this));
        commands.add(new NoCommand(plan, this));

        setProbability(30);
        setSituationCommands(commands);
        setQuestion("Vidíte na zemi cigaretu. Vezmete si jí? ano/ne");
    }

    /**
     * Příkaz vymaže příkazy ano a ne, aby hráč nemohl tu samou cigaretu několikrát
     *
     * @return vráti negativní východisko situace
     */
    @Override
    public String getNegativeAnswerConsequences() {
        getPlan().removeSituationCommands(FoundCigaretteSituation.class.getName());
        return "Jdete dál. Cizí cigarety nepotřebujete.";
    }

    /**
     * Příkaz vymaže příkazy ano a ne, aby hráč nemohl tu samou cigaretu několikrát
     * Taktéž příkaz přidá cigaretu, kterou hráč najde
     *
     * @return vráti pozitivní východisko situace
     */
    @Override
    public String getPositiveAnswerConsequences() {
        getPlan().removeSituationCommands(FoundCigaretteSituation.class.getName());
        getPlan().addPlayerCigarettes(1);
        return "Teď máte v inventáři o cigaretu navíc.\n" +
                "Aktuální počet cigaret v inventáři je " + getPlan().getCigarettesCount();
    }
}

