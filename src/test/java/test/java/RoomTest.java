package test.java;


import logika.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída GameTest slouží ke komplexnímu otestování
 * třídy Room

 *@author Maxim Dužij
 *@version pro školní rok 2017/2018
 */
public class RoomTest {
    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře,
     */
    @Test
    public void testLzeProjit() {
        Room prostor1 = new Room("hala", "vstupní hala budovy VŠE na Jižním městě", false);
        Room prostor2 = new Room("bufet", "bufet, kam si můžete zajít na svačinku", false);
        prostor1.setExit(prostor2);
        prostor2.setExit(prostor1);
        assertEquals(prostor2, prostor1.returnNextdoor("bufet"));
        assertNull(prostor2.returnNextdoor("pokoj"));
    }

}
