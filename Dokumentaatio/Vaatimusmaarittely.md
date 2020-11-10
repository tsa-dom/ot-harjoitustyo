# Vaatimusmäärittely
## Sovelluksen tarkoitus
#### Sovellus on tarkoitettu peliksi, jossa pelaaja voi pelata noppapeliä Yatzy. Ohjelman ei ole tarkoitus rajoittua vain peruspeliin, vaan se tulisi haastamaan pelaajan myös toinen toistaan erikoisemmilla pelimuodoilla.
## Käyttäjät
#### Peliin on tarkoitettu vain yhden tyyppisiä eli tavallisia käyttäjiä. Kuitenkin suunnitteilla olisi toteutus, että peliä voisi hallinnoida paremmin erillisellä admin käyttäjätilillä, joka tulisi erikseen aktivoida käynnistämällä peli admin-modessa. Adminin oikeudet tulisivat rajoittumaan siten, että se voisi hallinnoida asetuksia ja muiden pelaajien käyttäjiä, mutta ei voisi itse pelata.
## Pelin toiminnallisuudet
### Kirjautuminen
* mikäli pelaajalla ei ole vielä käyttäjää, voi hän luoda käyttäjän, jonka käyttäjänimen ja salasanan pituus on toivotunkokoinen
* jos pelaajalla on jo olemassa oleva käyttäjä, voi hän kirjautua suoraan peliin
### Päävalikko
* pelaaja voi aloittaa uuden pelin
* pelaaja voi ladata viimeksi kesken jääneen pelin suorituksen
* pelaaja voi siirtyä tarkastelemaan sijoituksia ja verrata omaa sijoitusta muiden rekisteröityneiden käyttäjien sijoituksiin
* pelaajalla on myös mahdollisuus kirjautua ulos tai lopettaa ohjelman suoritus
### Peli ja eri pelimuodot
* pääosin pelimuodot tullaan suunnittelemaan eri sceneihin niiden mahdollisten uniikkien vaatimusten perusteella
* pelin keskeiset elementit tulevat olemaan Yatzy henkisiä, kuten noppien heittoa ja pisteiden keräämistä Yatzylle ominaisella tavalla
* pelaajan tulisi pelin aikana huolehtia vain omista valinnoistaan ja ohjelma suorittaa pelaajan pisteiden laskemisen automaattisesti, sekä sen hetkisen session reaalitallennuksen
### Admin-mode
* käyttäjämuoto jolla ei ole mahdollisuutta pelata
* oletuksena poissa käytöstä ja aktivoitava erikseen
* mahdollisuus jäädyttää pelaajia
* mahdollisuus poistaa pelaajia
* peliin liittyvien muiden mahdollisten asetusten muuttaminen
### Kehitysideoita
#### Peliä olisi tarkoitus voida kehittää siten, että pelissä olisi useita pelimuotoja ja mahdollisuus custom pelimuodoille. Peliä voitaisiin kehittää myös siten, että se tukisi myös pelimuotojen luontia, jossa käyttäjä lähettää pelisääntöjä vastaavan oikein kirjoitetun tekstitiedoston generoidakseen pelille säännöt. Vaihtoehtoisesti peli voisi luoda ensimmäisen käynnistyksensä yhteydessä tiedoston, jota kautta pelaajalla olisi mahdollisuus kenties luoda oma erityinen pelimuotonsa. Tällaisten pelimuotojen scene toteutus voisi kuitenkin aiheutua haasteeksi.
#### Mahdollinen toinen kehitysidea voisi olla tuki useammille kielille englannin kielen lisäksi. Peli voisi antaa mahdollisuuden asettaa kieli ainakin englanniksi ja suomeksi, sekä ehkä myös norjaksi. Peliin voitaisiin myös luoda mahdollisuus, jossa pelaaja voisi ladata niin sanotun kielipaketin, joka syötetään ohjelmalle oikein täytettynä teksittiedostona. Uusi ladattu kieli tallennettaisiin tämän jälkeen pelin tietokantaan.
### Pelinäkymä classic pelimuodossa
![Classic screen](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/classicscreen.png "Classic screen")
