package logika.situations;

import logika.GamePlan;
import logika.commands.ICommand;

import java.util.HashSet;

/**
 * Class Situation - abstraktní třída představujicí situaci ve hře
 * Probability je pravděpodobnost, se kterou se daná situace bude objevovat
 * Question je otázka, která se přitom zobrazí
 * Každá situace si nastaví otázku, příkazy a pravděpodobnost
 * <p>
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Maxim Dužij
 * @version pro školní rok 2018
 */
public abstract class Situation {
    private int probability;
    private String question;

    /**
     * @param plan předá se zde herní plán
     */
    public Situation(GamePlan plan) {
        this.plan = plan;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    private HashSet<ICommand> situationCommands;
    private GamePlan plan;

    public HashSet<ICommand> getSituationCommands() {
        return situationCommands;
    }

    public void setSituationCommands(HashSet<ICommand> situationCommands) {
        this.situationCommands = situationCommands;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return vráti negativní východisko situace
     */
    public abstract String getNegativeAnswerConsequences();

    /**
     * @return vráti pozitivní východisko situace
     */
    public abstract String getPositiveAnswerConsequences();

    public GamePlan getPlan() {
        return plan;
    }
}
