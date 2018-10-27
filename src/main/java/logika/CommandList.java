package logika;

import logika.commands.ICommand;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Class CommandList - eviduje seznam přípustných příkazů adventury.
 * Používá se pro rozpoznávání příkazů
 * a vrácení odkazu na třídu implementující konkrétní příkaz.
 * Každý nový příkaz (instance implementující rozhraní Prikaz) se
 * musí do seznamu přidat metodou addCommand.
 * <p>
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Maxim Dužij
 * @version pro školní rok 2018
 */
public class CommandList {
    private Map<String, ICommand> commandsMap;

    /**
     * Konstruktor
     */
    public CommandList() {
        commandsMap = new HashMap<>();
    }

    /**
     * Vyměňuje příkaz. Používá se to zejména u příkazu HelpCommand, pro který je seznam příkazu k vypsání jiný.
     *
     * @param command Instance třídy implementující rozhraní ICommand se v seznamu nahrazuje za jiný Command
     *
     */
    public void replaceCommand(ICommand command) {
        commandsMap.replace(command.getName(), command);
    }


    /**
     * Vkládá nový příkaz.
     *
     * @param command Instance třídy implementující rozhraní ICommand
     */
    public void addCommand(ICommand command) {
        commandsMap.put(command.getName(), command);
    }

    /**
     * Vkládá nové příkazy.
     *
     * @param commands bere do sebe neomezený počet příkazů
     */
    public void addCommands(ICommand...commands) {
        for (ICommand c : commands)
        {
            addCommand(c);
        }
    }

    /**
     * Vkládá nové příkazy.
     *
     * @param commands bere do sebe omezený počet příkazů
     */
    public void addCommands(HashSet<ICommand> commands) {
        for (ICommand c : commands)
        {
            addCommand(c);
        }
    }

    /**
     * Maže příkazy.
     *
     * @param command bere do sebe jeden příkaz
     */
    public void removeCommand(ICommand command) {
        commandsMap.remove(command.getName());
    }


    /**
     * Vrací odkaz na instanci třídy implementující rozhraní IPrikaz,
     * která provádí příkaz uvedený jako parametr.
     *
     * @param retezec klíčové slovo příkazu, který chce hráč zavolat
     * @return instance třídy, která provede požadovaný příkaz
     */
    public ICommand returnCommand(String retezec) {
        if (commandsMap.containsKey(retezec)) {
            return commandsMap.get(retezec);
        } else {
            return null;
        }
    }

    /**
     * Smažou se nepotřebné příkazy předané v HashSet<ICommand>
     * @param commands předaný parametr
     */
    public void removeCommand(HashSet<ICommand> commands) {
        for (ICommand c : commands)
        {
            removeCommand(c);
        }
    }

    /**
     * Kontroluje, zda zadaný řetězec je přípustný příkaz.
     *
     * @param command Řetězec, který se má otestovat, zda je přípustný příkaz
     * @return Vrací hodnotu true, pokud je zadaný
     * řetězec přípustný příkaz
     */
    public boolean isValidCommand(String command) {
        return commandsMap.containsKey(command);
    }

    /**
     * Vrací seznam přípustných příkazů, jednotlivé příkazy jsou oddělené středníkem.
     *
     * @return Řetězec, který obsahuje seznam přípustných příkazů
     */
    public String returnCommandList() {
        String list = "";
        for (String commandWord : commandsMap.keySet()) {
            list += commandWord + ";";
        }
        return list;
    }

}

