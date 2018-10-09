package test.java;

import logika.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída GameTest slouží ke komplexnímu otestování
 * třídy Game

 *@author Maxim Dužij
 *@version pro školní rok 2017/2018
 */

public class GameTest {
    private Game game;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        game = new Game();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     *
     */
    @Test
    public void testPrubehHry() {
        assertEquals("cela", game.getGamePlan().getCurrentRoom().getName());
        game.executeCommand("jdi pred_celou");
        assertEquals(false, game.endOfTheGame());
        assertEquals("pred_celou", game.getGamePlan().getCurrentRoom().getName());
        game.executeCommand("jdi cela");
        game.getGamePlan().addPlayerItem("dláto");
        game.getGamePlan().addPlayerItem("plakát");
        game.getGamePlan().addPlayerItem("kladivo");
        game.executeCommand("kopat");
        assertEquals(true, game.endOfTheGame());
    }
}
