package logika.commands;

import logika.GamePlan;
import logika.situations.RockPaperScissorsGameSituation;

import java.util.Arrays;
import java.util.Random;

public class RockPaperScissorsGameCommand implements ICommand {
    private static final String NAME = "sázet";
    private GamePlan plan;
    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public RockPaperScissorsGameCommand(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Tento příkaz dokáže nasimulovat hru kámen nůžky, papír se strojem
     * Hráč napíše příkaz ve formátu "sázet 6 (kolik chce sázet) nůžky (na výběr z kamene, nůžek nebo papíru)"
     * Příkaz na základě náhodného čísla rozhodne, co hráči odpoví 
     * Hráč buď vyhraje a dostene dvoujnásobek toho, co sázel
     * Nebo prohraje a sázené cigarety se mu z inventáře odečtou
     *
     * @param params - příkaz nemá parametry, všechny potřebné údaje zjistím z GamePlanu
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String execute(String... params) {
        if(params.length == 2)
        {
        try {
                //sázka

                int bet = Integer.parseInt(params[0]);

                if(bet > plan.getCigarettesCount())
                {
                    return "Nemůžete tolik sázet.\n" +
                            "Momentálně máte v inventáři " + plan.getCigarettesCount() + " cigaret.";
                }

                String[] options = new String[] {"kámen", "nůžky", "papír"};

                if (Arrays.asList(options).contains(params[1]))
                {
                    Random r = new Random();
                    String random = options[r.nextInt(2)];

                    if(random.equals(params[1]))
                    {
                       return "remíza, zkuste to znovu.";
                    }
                    else{
                       if(random.equals("kámen") && params[1].equals("nůžky"))
                       {
                           return loseGame(bet);
                       }
                       else{
                           if(random.equals("kámen") && params[1].equals("papír"))
                           {
                               return winGame(bet);
                           }
                           else{
                               if(random.equals("nůžky") && params[1].equals("kámen"))
                               {
                                   return winGame(bet);
                               }
                               else{
                                   if(random.equals("nůžky") && params[1].equals("papír"))
                                   {
                                       return loseGame(bet);
                                   }
                                   else{
                                       if(random.equals("papír") && params[1].equals("kámen"))
                                       {
                                           return loseGame(bet);
                                       }
                                       else{
                                           if(random.equals("papír") && params[1].equals("nůžky"))
                                           {
                                               return winGame(bet);
                                           }
                                       }
                                   }
                               }
                           }
                       }
                    }
                }
                else{
                    return "máte na výber jen kámen, nůžky a papír \n" +
                        "(příkaz vypadá jako např. takto 'sázet 6 nůžky')";
                }
            }
            catch (NumberFormatException nfe)
            {
                return "Napište kolik sázíte cigaret číslicí";
            }
        }
            return "Nesprávný tvar příkazu!\n" +
                    "(příkaz vypadá jako např. takto 'sázet 6 nůžky')";
    }

    private String winGame(int bet) {
        plan.addPlayerCigarettes(bet);
        return plan.getCurrentRoom().getSituation(RockPaperScissorsGameSituation.class.getName()).getPositiveAnswerConsequences();
    }

    private String loseGame(int bet) {
        plan.removeCigarettes(bet);
        return plan.getCurrentRoom().getSituation(RockPaperScissorsGameSituation.class.getName()).getNegativeAnswerConsequences();
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
