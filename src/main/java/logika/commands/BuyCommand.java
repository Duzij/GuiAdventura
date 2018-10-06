package logika.commands;

import logika.GamePlan;
import java.util.HashMap;
import java.util.Map;

public class BuyCommand  implements ICommand {
    private static final String NAME = "koupit";
    private GamePlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public BuyCommand(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "koupit" a po kontrole, zda hráč disponuje dostatečným kapitálem,
     * je mu věc přidána do inventáře.
     *
     * @param params - jako  parametr obsahuje jméno věci z nabídky
     *
     *
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String execute(String... params) {
        if (params.length == 1) {

            Map<String,Integer> options = new HashMap<>();
            options.put("dláto", 19);
            options.put("plakát", 9);
            options.put("kladivo", 15);
            options.put("kniha", 5);
            options.put("nůž", 9);

            if (options.containsKey(params[0]))
            {
                if(plan.getCigarettesCount() > options.get(params[0]))
                {
              plan.setCigarettesCount(plan.getCigarettesCount()- options.get(params[0]));
              plan.addPlayerItem(params[0]);
              return "\nPrávě jste nakoupili "+ params[0];
                }
                else{
                    return "Bohužel máte nedostatek cigaret.";
                }
            }
            else{
                return "Tento typ zboží se zde neeviduje.";
            }
        }
        return "Nesprávný tvar příkazu\n" +
                "Napište co z nabídky chcete koupit\n" +
                "např. koupit kladivo";
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


