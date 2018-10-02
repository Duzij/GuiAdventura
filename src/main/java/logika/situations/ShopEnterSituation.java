package logika.situations;

import logika.GamePlan;
import logika.Room;
import logika.commands.BuyCommand;
import logika.commands.ICommand;
import logika.commands.SayKeywordCommand;

import java.util.HashSet;

public class ShopEnterSituation extends Situation {

    /**
     * Nastaví se potřebné příkazy a pravděpodobnost situace
     * @param plan předá se zde herní plán
     */
    public ShopEnterSituation(GamePlan plan) {
        super(plan);
        HashSet<ICommand> commands = new HashSet<>();
        commands.add(new SayKeywordCommand(plan));

        setProbability(100);
        setSituationCommands(commands);
        setQuestion("Jeden z vězňů se Vás zeptal na nějaké klíčové slovo");
    }

    @Override
    public String getNegativeAnswerConsequences() {
        return "Vypadá to, že se jedná o nějaké jiné klíčové slovo.";
    }

    @Override
    public String getPositiveAnswerConsequences() {
        Room obchod = new Room("obchod", "nacházíte se v tajném obchodě, kde můžete měnit cigarety na věci", false);
        //bude se vždy jednat o prostor s názvem "skupinka věžňů"
        obchod.setExit(getPlan().getCurrentRoom());
        obchod.setRoomCommands(new BuyCommand(getPlan()));
        getPlan().setCurrentRoom(obchod);
        return "Bylo to spávné klíčové svolo. Vítejte v tajném vězeňském obchodě\n" +
                "Jeden z vězňů se Vás ptá, co si koupíte.\n" +
                "Zde je nabídka \n" +
                "dláto: 19 cigaret\n" +
                "plagát: 9 cigaret\n" +
                "kaldivo: 15 cigaret\n" +
                "kniha: 5 cigaret\n" +
                "nůž: 9 cigaret\n";
    }
}