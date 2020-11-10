package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    @Test
    public void saldoAlussaOikein(){
        assertEquals("saldo: 0.10",kortti.toString());
    }
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(54321);
        assertEquals("saldo: 543.31",kortti.toString());
    }
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi(){
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5",kortti.toString());
    }
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi(){
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10",kortti.toString());
    }
    @Test
    public void metodiPalauttaaTrueJosRahatRiittavat(){
        assertTrue(kortti.otaRahaa(5));
    }
    @Test
    public void metoriPalauttaaFalseJosRahatEivätRiita(){
        assertFalse(kortti.otaRahaa(15));
    }
    @Test
    public void metoriPalauttaaTrueJosRahaaOikeaMaara(){
        assertTrue(kortti.otaRahaa(10));
    }
    @Test
    public void palauttaakoOikeanSaldon(){
        assertEquals(10,kortti.saldo());
    }
}
