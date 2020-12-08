# Käyttöohje

## Ensimmäinen käynnistys
Käyttäjän ei tarvitse huolehtia siitä, että onko hänellä tarvittavia tiedostoja, että peli toimisi oikein, sillä peli luo tarvittavat tietokannat ja cluster tiedostot ensimmäisen käynnistyksen yhteydessä. Mikäli jokin vaadituista tiedostoista puuttuu, luo peli tällöin uudet tiedostot tilalle.

## Kirjautuminen
Ohjelman käynnistyessä sovellus aukaisee kirjautumisikkunan. Käyttäjällä on mahdollisuus kirjautua jo olemassa olevalle käyttäjälle tai hän voi tehdä peliin uuden käyttäjän painamalla sign up nappulaa. Pelaajalla on mahdollisuus sulkea ohjelma painamalla quit.

![Login](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/image1.png)

Mikäli pelaajalla ei ole olemassa olevaa käyttäjää tai hän haluaa luoda uuden käyttäjän tulee hänen luoda käyttäjälleen käyttäjätunnus ja salasana sign up näkymässä. Pelaaja voi tehdä uuden käyttäjän painamalla sign up, jolloin ohjelma tarkastaa voidaanko käyttäjää muodostaa. Halutessaan pelaaja voi palata login sceneen.

![Sign up](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/image2.png)

## Päävalikko
Päävalikossa näkyy vasemmassa alakulmassa nykyisen pelaajan käyttäjätunnus. Pelaajalle aukeaa useita eri vaihtoehtoja päävalikossa. Päävalikkossa pelaaja voi valita, haluaako hän aloittaa uuden pelin, *jatkaa edellista peliä*, tarkastella aiempien peliensä sijoituksia, kirjautua ulos tai lopettaa peli. 

![Päävalikko](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/image3.png)

### Uusi peli
Uusi peli ruudussa pelaaja voi valita pelimuodon, jota hän haluaa pelata painamalla valintaruutua. Valintaruudun alapuolella on kaksi nappulaa, palaa päävalikkoon ja aloita peli. Aloittamalla pelin pelaaja siirtyy pelaamaan valitsemaansa pelimuotoon.

![New game](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/image4.png)

## Pelinäkymä
Pelinäkymässä pelaaja pääsee pelaamaan itse peliä. Alla olevassa pelinäkymässä pelaaja on päättänyt että haluaa pelata pelimuotoa maxi, joka on siis Yatzy kuudella nopalla. Sovelluksen yläosassa näkyvät kuusi noppaa ja niiden alapuolella nappulat, joiden avulla pelaaja voi päättää haluaako se pitää valitsemansa nopat seuraavalle nopan heitto kierrokselle. Pelimekaniikkojen kannalta pelaajalla on käytössä kaksi nappulaa *reroll* ja *make your choise*, sekä valintaruutu, jossa pelaaja valitsee pistetaulukosta sen kohdan mihin haluaa että pisteet lasketaan. Pelaaja voi halutessaan myös poistua pelistä.

![Game](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/image5.png)
