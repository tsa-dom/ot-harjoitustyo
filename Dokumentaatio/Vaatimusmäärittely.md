# Vaatimusmäärittely
## Sovelluksen tarkoitus
Sovellus on tarkoitettu peliksi, jossa pelaaja voi pelata noppapeliä Yatzy. Ohjelman ei ole tarkoitus rajoittua vain peruspeliin, vaan se tulisi haastamaan pelaajan myös toinen toistaan erikoisemmilla pelimuodoilla.
## Pelin valmiit toiminnallisuudet
Peliin on lisätty seuraavat toiminnallisuudet
* alustava toiminnallisuus custom pelimuodoille
* custom pelimuotojen cluster.properties tiedostoja voi muokkailla omalla vastuulla, virheellisillä syötteillä voi tulla vielä virheitä
* päivitetty noppalaskuria siten, että se kykenee nyt laskemaan noppien silmälukuja, jotka ovat suurempia kuin 9
* peliin lisätty testipelimuoto *Fast*, jolla voi saada nopeasti pelattuun pelin joitain toiminnallisuuksia
* peliin lisätty alustava tuki scoreboardille, jota voi testailla pelaamalla millä tahansa pelimuodolla, mutta testimielessä pelimuodolla *Fast*
* peliin kirjaudutaan käyttäjätunnuksella ja salasanalla
* peliä pystyy pelaamaan, mutta siitä puuttuu toiminnallisuus laskea bonus
* peli kykenee nyt laskemaan bonuksen ja pisteet kokonaisuudessaan
* pelissä tableview on muokattu siten, että se jaoittelee nyt järjestyksen pelaajien pisteille, jota se ei aiemmin tehnyt
* lisätty pelimuoto maxi, kuudella nopalla
* nyt peli tallentaa scoreboardiin tiedon pelatun pelimuodon maksimi pistemäärästä
* poistettu sceneistä turhaksi jäänyt yläpalkki
* tämä ei  käyttäjälle näkyvää toiminnallisuutta, mutta tehty kaikille sovelluslogiikan metodeille rajapinnat ja käyttöliittymä käyttää sovelluslogiikkaa ensisijaiseti rajapintojen kautta

## Käyttäjät
Pelissä on vain yhdenlaisia käyttäjiä, eli peruskäyttäjiä.
## Pelin toiminnallisuudet
### Kirjautuminen
* mikäli pelaajalla ei ole vielä käyttäjää, voi hän luoda käyttäjän, jonka käyttäjänimen ja salasanan tulee olla toivotunlainen
* jos pelaajalla on jo olemassa oleva käyttäjä, voi hän kirjautua suoraan peliin
### Päävalikko
* pelaaja voi aloittaa uuden pelin
* pelaaja voi siirtyä tarkastelemaan sijoituksia ja verrata omaa sijoitusta muiden rekisteröityneiden käyttäjien sijoituksiin
* pelaajalla on mahdollisuus kirjautua ulos tai lopettaa ohjelman suoritus
### Peli ja sen eri pelimuodot
* peli 
* pääosin pelimuodot tullaan suunnittelemaan eri sceneihin niiden mahdollisten uniikkien vaatimusten perusteella
* pelin keskeiset elementit tulevat olemaan Yatzy henkisiä, kuten noppien heittoa ja pisteiden keräämistä Yatzylle ominaisella tavalla
* pelaajan tulisi pelin aikana huolehtia vain omista valinnoistaan ja ohjelma suorittaa pelaajan pisteiden laskemisen automaattisesti, sekä sen hetkisen session reaalitallennuksen
* peli tallentaa pelaajien pelihistoriaa, joka mahdollistaa aikaisemman pistehistorian tarkastelun

## Kehitysideoita
* peliin voitaisiin luoda parempi tuki custom pelimuodoille, nykyisessä versiossa tämä ominaisuus on hieman kömpelö ja rajoitettu
* pelitilannetta voitaisiin tallentaa reaaliaikaisesti, jolloin mahdollinen pelistä lähteminen ei muuta pelitilannetta
* tuki useammille kielille kuin englanti

### Pelinäkymä classic pelimuodossa
![Classic screen](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/classicscreen.png "Classic screen")
