package logika.commands;

import logika.GamePlan;
import logika.situations.TilePuzzleSituation;

public class TilePuzzleNumberCommand implements ICommand {
    private static final String NAME = "posunout_dlaždičku_číslo";
    private GamePlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public TilePuzzleNumberCommand(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Příkaz se provádí jako odpověď na otázku, kterou pokládá třída TilePuzzleSituation
     * musí se zadat správná výsledek x rovnice:  x * 2 - 2 = 18
     * taktéž se provede validace, zda uživatel zadal číslici
     *
     * @param params - příkaz nemá parametry, všechny potřebné údaje zjistím z GamePlanu
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String execute(String... params) {
        if (params.length == 1){
            try
            {
                int i = Integer.parseInt(params[0]);
                if(i == 10)
                {
                    return plan.getCurrentRoom().getSituation(TilePuzzleSituation.class.getName()).getPositiveAnswerConsequences();
                }
            }
            catch (NumberFormatException nfe)
            {
                return "Napište pořadí dlaždičky číslicí";
            }
        }
        return plan.getCurrentRoom().getSituation(TilePuzzleSituation.class.getName()).getNegativeAnswerConsequences();
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
}
