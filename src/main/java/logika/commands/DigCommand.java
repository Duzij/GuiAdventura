package logika.commands;

import logika.GamePlan;

public class DigCommand implements ICommand {
    private static final String NAME = "kopat";
    private GamePlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public DigCommand(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "kopat". Nejpre proběhne kontrola, zda jsme v cele, protože kopat můžeme jen tam
     * Poté proběhne validace, zda máme všechny potřebné předměty pro kopání
     *
     * @param params - příkaz nemá parametry, všechny potřebné údaje zjistím z GamePlanu
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String execute(String... params) {
        if (!plan.getCurrentRoom().getName().equals("cela")) {
            return "Zde nemůžu kopat";
        }
        if(!hasItem("plakát") || !hasItem("dláto") || !hasItem("kladivo")){
            return  "Nemáte všechny tři věci ve svém inventáři. Potřebujete kladivo, dláto a plakát.";
        }

        plan.quitGame();
        return "Začal jste kopat a velice brzo jste se dostal pryč z vězení, gratuluji!";
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
