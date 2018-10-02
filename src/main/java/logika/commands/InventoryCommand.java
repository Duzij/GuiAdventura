package logika.commands;

import logika.GamePlan;

public class InventoryCommand implements ICommand {

    private static final String NAME = "inventář";
    private GamePlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán,
     *                      ze kterého je možné ve hře použít,
     *                      aby je nápověda mohla zobrazit uživateli.
     */
    public InventoryCommand(GamePlan plan) {
        this.plan = plan;
    }


    /**
     * Příkaz se vypíše věci v inventáři, počet cigaret (vnitřní měnu věznice) a úroveň síly
     */
    @Override
    public String execute(String... params) {
        String returnText;
        if (plan.getPlayerItems().isEmpty())
        {
            returnText = "Nemáte žádné důležité věci v inventáři. \n";
        }
        else {
            returnText = "Máte v inventáři: ";
            for (String i : plan.getPlayerItems())
            {
                returnText += i;
            }
        }

         returnText += "\nPočet cigaret je: " + plan.getCigarettesCount() + "\n";
        return returnText += "Úroveň síly je: " + plan.getPower();
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
