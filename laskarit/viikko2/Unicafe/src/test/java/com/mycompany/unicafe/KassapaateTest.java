/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tapio Salonen
 */
public class KassapaateTest {
    Maksukortti kortti;
    Kassapaate kassapaate;
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(450);
        kassapaate = new Kassapaate();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunKassapaatteenRahamaaraOikea(){
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    @Test
    public void luodunKassapaatteenMyytyjenLounaidenMaaraOikea(){
        int myyty = kassapaate.edullisiaLounaitaMyyty()+kassapaate.maukkaitaLounaitaMyyty();
        assertEquals(0,myyty);
    }
    @Test
    public void maksuRiittavaVaihtorahanSuuruusOikeaEdullisille(){
        assertEquals(260,kassapaate.syoEdullisesti(500));
    }
    @Test
    public void maksuRiittavaVaihtorahanSuuruusOikeaEdullisille2(){
        assertEquals(0,kassapaate.syoEdullisesti(240));
    }
    @Test
    public void maksuRiittavaVaihtorahanSuuruusOikeaMaukkaille(){
        assertEquals(100,kassapaate.syoMaukkaasti(500));
    }
    @Test
    public void maksuRiittavaVaihtorahanSuuruusOikeaMaukkaille2(){
        assertEquals(0,kassapaate.syoMaukkaasti(400));
    }
    @Test
    public void maksuRiittavaMyytyjenLounaidenMaaraKasvaa(){
        kassapaate.syoEdullisesti(300);
        kassapaate.syoEdullisesti(400);
        kassapaate.syoEdullisesti(500);
        kassapaate.syoMaukkaasti(600);
        kassapaate.syoMaukkaasti(700);
        assertEquals(5,kassapaate.maukkaitaLounaitaMyyty()+kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void maksuEiRiittavaKassanRahamaaraEiMuutu(){
        kassapaate.syoEdullisesti(200);
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    @Test
    public void maksuEiRiittavaKassanRahamaaraEiMuutu2(){
        kassapaate.syoMaukkaasti(200);
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    @Test
    public void maksuEiRiittavaKaikkiRahaPalautetaan(){
        assertEquals(200,kassapaate.syoEdullisesti(200));
    }
    @Test
    public void maksuEiRiittavaKaikkiRahaPalautetaan2(){
        assertEquals(200,kassapaate.syoMaukkaasti(200));
    }
    @Test
    public void maksuEiRiittavaMyytyjenLounaidenMaaraEiMuutu(){
        kassapaate.syoEdullisesti(200);
        kassapaate.syoMaukkaasti(200);
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty()+kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kortillaTarpeeksiRahaaVeloitetaanSumma(){
        assertTrue(kassapaate.syoEdullisesti(kortti));
    }
    @Test
    public void kortillaTarpeeksiRahaaVeloitetaanSumma2(){
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    @Test
    public void kortillaTarpeeksiRahaaMyytyjenLounaidenMaaraKasvaa(){
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty()+kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kortillaTarpeeksiRahaaMyytyjenLounaidenMaaraKasvaa2(){
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty()+kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kortillaEiTarpeeksiRahaaRahamaaraEiMuutu(){
        kassapaate.syoEdullisesti(kortti);
        assertFalse(kassapaate.syoEdullisesti(kortti));
    }
    @Test
    public void kortillaEiTarpeeksiRahaaRahamaaraEiMuutu2(){
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
    }
    @Test
    public void kortillaEiTarpeeksiRahaaMyytyjenLounaidenMaaraEiMuutu(){
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty()+kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kortillaEiTarpeeksiRahaaMyytyjenLounaidenMaaraEiMuutu2(){
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty()+kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstettaessa(){
        kassapaate.lataaRahaaKortille(kortti, 1000);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(101000,kassapaate.kassassaRahaa());
    }
    @Test
    public void rahaaLadatessaSaldoMuuttuu(){
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(1450,kortti.saldo());
    }
    @Test
    public void rahaaLadatessaKassanRahamaaraKasvaa(){
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000,kassapaate.kassassaRahaa());
    }
    @Test
    public void kortilleNegatiivinenSaldoKortinSaldoMuuttuu(){
        kassapaate.lataaRahaaKortille(kortti, -1000);
        assertEquals(450,kortti.saldo());
    }
    @Test
    public void kortilleNegatiivinenSaldoKassanRahamaaraEiMuutu(){
        kassapaate.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
