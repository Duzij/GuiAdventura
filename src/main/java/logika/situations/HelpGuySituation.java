package logika.situations;

import logika.GamePlan;
import logika.commands.ICommand;
import logika.commands.NoCommand;
import logika.commands.YesCommand;

import java.util.HashSet;

public class HelpGuySituation extends Situation {

    /**
     * Nastaví se potřebné příkazy a pravděpodobnost situace
     * @param plan předá se zde herní plán
     */
    public HelpGuySituation(GamePlan plan) {
        super(plan);
        HashSet<ICommand> commands = new HashSet<>();
        commands.add(new YesCommand(plan, this));
        commands.add(new NoCommand(plan, this));

        setProbability(30);
        setSituationCommands(commands);
        setQuestion("Vidíte jak dva vězni mlátí mladíka. Pomůžete mu?\n" +
                    "Musíte mít úroveň síly alespoň 3. ano/ne");
    }

    @Override
    public String getNegativeAnswerConsequences() {
        return "Jdete dál, mladík se snad obejde bez Vaší pomoci.";
    }

    /**
     *  Pokud má hlavní postava dostatečnou úroveň síly, tak může mladíkovi pomoct
     *  Pokud je úroveň síly nedostatečná, nastaví se počet cigaret na nulu
     *  Když maldíkovi pomůže, dostane plakát
     *  Taktéž to ovlivní situaci, kdy hlavní postavě tento mladík poradí klíčové slovo do tajného obchodu
     *  Pravděpodobnost této situace nastaví na nulu, aby jsme nemohli tomu samému člověku pomoct dvakrát
     */
    @Override
    public String getPositiveAnswerConsequences() {
        if(getPlan().getPower() < 3)
        {
            getPlan().setCigarettesCount(0);
           return "Zmlátili vas oba a sebrali Vám všechny Vaše cigarety :(";
        }
        else    {
        getPlan().addPlayerItem("plakát");
        getPlan().getCurrentRoom().getSituation(this.getClass().getName()).setProbability(0);
        getPlan().setHelpedGuy(true);
        return "Pomohli jste mladíkovi. Jako dárek Vám dal svůj plakát.\n";
        }
    }
}
