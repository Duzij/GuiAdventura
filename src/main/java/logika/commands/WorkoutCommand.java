package logika.commands;

import logika.GamePlan;
import logika.situations.WorkoutSituation;

public class WorkoutCommand  implements ICommand {
    private static final String NAME = "posilovat";
    private GamePlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public WorkoutCommand(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Hráč při vykonání tohoto příkazu může yvzkoušet posilovat ve hře
     * po konkrétnímu počtu teček se přičte jedna úroveň k síle hlavní postavy 
     *
     * @param params - příkaz nemá parametry, všechny potřebné údaje zjistím z GamePlanu
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String execute(String... params) {
        if (params.length == 1){
            if(countStringOccurrences(params[0],".") > 20)
            {
                plan.setPower(plan.getPower() + 1);
                return plan.getCurrentRoom().getSituation(WorkoutSituation.class.getName()).getPositiveAnswerConsequences();
            }
            else{
                return plan.getCurrentRoom().getSituation(WorkoutSituation.class.getName()).getNegativeAnswerConsequences();
            }
        }
        else{
            return "Nesprávný tvar příkazu:\n" +
                    "Příkaz zapište ve tvaru např.\n" +
                    "posilovat .................................";
        }
    }

    private boolean hasItem(String item) {
        return plan.getPlayerItems().contains(item);
    }

    public static int countStringOccurrences(String text, String pattern) {

        int count = 0;
        int i = 0;
        // Keep calling indexOf for the pattern.
        while ((i = text.indexOf(pattern, i)) != -1) {
            // Advance starting index.
            i += pattern.length();
            // Increment count.
            count++;
        }
        return count;
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
