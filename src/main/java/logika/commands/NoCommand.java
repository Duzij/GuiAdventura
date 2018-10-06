package logika.commands;

import logika.GamePlan;
import logika.situations.Situation;

public class NoCommand  implements ICommand {
    private static final String NAME = "ne";
    private GamePlan plan;
    private Situation situation;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public NoCommand(GamePlan plan, Situation situation) {
        this.plan = plan;
        this.situation = situation;
    }

    /**
     * Příkaz ne se provádí jako odpověď na otázku, kterou pokládá třída Situation
     *
     * @param params - příkaz nemá parametry, všechny potřebné údaje zjistím z GamePlanu
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String execute(String... params) {
        plan.removeSituationCommands(situation.getClass().getName());
        return "Odpověděli jste NE! \n"
                + plan.getCurrentRoom().getSituation(situation.getClass().getName()).getNegativeAnswerConsequences();
    }

    private boolean hasItem(String item) {
        return plan.getPlayerItems().contains(item);
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
