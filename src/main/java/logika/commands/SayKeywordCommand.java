package logika.commands;

import logika.GamePlan;
import logika.situations.ShopEnterSituation;

public class SayKeywordCommand implements ICommand {
    private static final String NAME = "říct_slovo";
    private GamePlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public SayKeywordCommand(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "říct_slovo". Používá se ve hře k přístupu do tajného obchodu
     *
     * @param params - jako  parametr obsahuje jméno prostoru (východu),
     *                  do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String execute(String... params) {
        if (params.length == 1 && params[0].equals("volavka")) {
            return plan.getCurrentRoom().getSituation(ShopEnterSituation.class.getName()).getPositiveAnswerConsequences();
        }
        else{
            return plan.getCurrentRoom().getSituation(ShopEnterSituation.class.getName()).getPositiveAnswerConsequences();
        }
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

}

