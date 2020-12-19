# Vaatimusmäärittely
## Sovelluksen tarkoitus
Sovellus on tarkoitettu peliksi, jossa pelaaja voi pelata noppapeliä Yatzy. Ohjelman ei ole tarkoitus rajoittua vain peruspeliin, vaan se tulisi haastamaan pelaajan myös toinen toistaan erikoisemmilla pelimuodoilla.

## Käyttäjät
Pelissä on vain yhdenlaisia käyttäjiä, eli peruskäyttäjiä.
## Pelin toiminnallisuudet
### Kirjautuminen
* pelaaja voi kirjautua peliin olemassa olevalla käyttäjätunnuksella ja salasanalla
* kirjautumisen jälkeen pelaaja pääsee päävalikkoon
* kirjautumisruudusta on mahdollisuus lopettaa peli tai siirtyä luomaan uutta käyttäjää peliin
### Uuden käyttäjän luominen
* pelaajalla on mahdollisuus luoda peliin uusikäyttäjä, jonka käyttäjänimi ja salasana ovat oikean kokoisia
* ei ole mahdollista luoda jo käytetyllä nimellä uutta käyttäjätunnusta
### Päävalikko
* pelaaja voi aloittaa uuden pelin
* pelaaja voi siirtyä tarkastelemaan sijoituksia ja verrata omaa sijoitusta muiden rekisteröityneiden käyttäjien sijoituksiin
* pelaajalla on mahdollisuus kirjautua ulos tai lopettaa ohjelman suoritus
### Uusi peli
* uusi peli ruudussa pelaajan on mahdollista aloittaa uusi peli haluamassaan pelimuodossa
* pelaajalla on mahdollisuus siirtyä valikkoon tai aloittaa valitsemansa peli
### Peli
* peli sisältää useita pelimuotoja ja jokaisella on niille uniikit vaikutukset
* pää idea jokaisen peliruudun asettelussa on samankaltainen
  * pelaaja voi päättää mitkä nopat se haluaa valita
  * noppien uudelleen heittäminen
  * objektiivin valitseminen
  * valinnan tekeminen
  * pisteiden tarkastelu
  * joissain pelimuodossa mahdollisuus nähdä top 3 tilastot pelin aikana
  * pelaaja voi lähteä pelistä jolloin se menettää pelistauksensa
  
 ### Tilastot
 * tilastot osiossa pelaaja pääsee näkemään kaikki loppuun pelatut pelit ja eri pelimuodoissa ansaitut pisteet
 * tilastoissa näkyvät kaikkien pelaajien tilastot, ei pelkästään pelaajan omat

## Kehitysideoita
* peliin voitaisiin luoda parempi tuki custom pelimuodoille, nykyisessä versiossa tämä ominaisuus on hieman kömpelö ja rajoitettu
* pelitilannetta voitaisiin tallentaa reaaliaikaisesti, jolloin mahdollinen pelistä lähteminen ei muuta pelitilannetta
* tuki useammille kielille kuin englanti
