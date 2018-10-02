package logika.situations;

import logika.GamePlan;
import logika.commands.ICommand;
import logika.commands.NoCommand;
import logika.commands.YesCommand;

import java.util.HashSet;

public class GuyDialogSituation extends Situation {

    /**
     * Nastaví se potřebné příkazy a pravděpodobnost situace
     * @param plan předá se zde herní plán
     */
    public GuyDialogSituation(GamePlan plan) {
        super(plan);
        HashSet<ICommand> commands = new HashSet<>();
        commands.add(new YesCommand(plan, this));
        commands.add(new NoCommand(plan, this));

        setProbability(30);
        setSituationCommands(commands);
        setQuestion("Vidíte jak na Vás máva nějaký mladík, jako kbyby Vám chtěl něco sdělit.\n" +
                    "Půjdete si s nim popovídat?  ano/ne\n");
    }

    @Override
    public String getNegativeAnswerConsequences() {
        return "Jdete dál...";
    }

    /**
     *  Mladík hlavní postavě prozradí klíčové slovo pouze v případě, že mu hlavní postava pomohla v minulosti
     *
     *  Pravděpodobnost této situace nastaví na nulu.
     *  Klíčové slovo nám prozradí jen jednou
     */
    @Override
    public String getPositiveAnswerConsequences() {
        if(getPlan().getHelpedGuy())
        {
            getPlan().getCurrentRoom().getSituation(this.getClass().getName()).setProbability(0);
            return "Je to mladík, kterému jste kdysi pomohl. \n" +
                    "Prozradil Vám slovo pro vstup do obchodu kolem skupinky vězňů\n" +
                    "Tajné klíčové slovo je: 'volavka'";
        }
        else    {
            return "Spletl si Vás s někým jiným a šel dál.";
        }
    }
}