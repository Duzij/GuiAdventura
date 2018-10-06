package logika.commands;

import logika.GamePlan;
import logika.situations.Situation;

public class YesCommand implements ICommand {
    private static final String NAME = "ano";
    private GamePlan plan;
    private Situation situation;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public YesCommand(GamePlan plan, Situation situation) {
        this.plan = plan;
        this.situation = situation;
    }

    /**
     * Příkaz ano se provádí jako odpověď na otázku, kterou pokládá třída Situation
     *
     * @param params - příkaz nemá parametry, všechny potřebné údaje zjistím z GamePlanu
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String execute(String... params) {
            return "Odpověděli jste ANO! \n"
                    + plan.getCurrentRoom().getSituation(situation.getClass().getName()).getPositiveAnswerConsequences();
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @ return nazev prikazu
     */
    @Override
    public String getName() {
        return NAME;
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }
}
