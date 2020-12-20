# Sovelluksen arkkitehtuuri
## Pakkausrakenne
Sovelluksella on neljäkerroksinen pakkausrakenne. Joidenkin pakkausten sisällä on olemaasa alipakkauksia, joiden tarkoitus on jaoitella toiminnallisuutta.

![Pakkausrakenne](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/pakkauskaavio.png)

## Käyttöliittymä

Pakkaus *main* sisältää vain yhden luokan, jonka tarkoitus on käynnistää sovelluksen graafinen käyttöliittymä *YatzyUi*. Sovelluksen pääkäyttöliittymä *YatzyUi* kuuluu yksin pakkaukseen *ui* ja sen tarkoitus on alustaa sovelluksen hermokeskus *core* ja antaa sille käsky suorittaa ohjelman lataamiseen vaadittava aliydin *InstallCore*. Kun alustus on valmis, ladataan login scene ja käyttöliittymä vastuu siirretään controllereille. Nyt pääkäyttöliittymän ainoaksi tehtäväksi jää controllerluokkien kuunteleminen *setRoot* käskyn varalta, jolla scenejen vaihto toiseen tapahtuu ja samalla vaihdetaan controlleria.

Controller luokka on jaettu kahden tyyppiseen toimintaan, controllerien toimintaan ja luokan controllerien käyttöliittymän informaation solmuun *ItemNode*. *ItemNode* tallentaa tiedon ohjelman käyttämistä käyttöliittymän omista listoista eli *Observable* listoista muiden controllerien välillä.

Tällä hetkellä Controllerit käyttävät ennen sovelluslogiikkaa pienissä määrin *Core*:n palveluita syystä, että muuten jouduttaisiin muodostaa kiertopolkuja, joita on valitettavasti ohjelmistoon muodostunut. Tilanteita, joissa käyttöliittymä kutsuu sovelluslogiikan metodia joka vain kutsuu *Core*:n metodia ovat ongelmallisia ja aiheuttavat tällä hetkellä kiertoa ohjelmassa.

Sovelluksen käyttöliittymä on jaettu kahteen osaan, pääkäyttöliittymään *YatzyUi*:hin ja sen käyttämiin controllereihin. Ohjelman scenet on määritetlty FXML tiedostoformaatissa ja täten jokaiselle scenelle on olemassa yksi controller, mutta yhdellä controllerilla voi olla useampi FXML määritelty scene. Hyvä esimerkkki controlleriin, joka sisältää tuen useille sceneille on *gameController*. Ei olisi koodin laadun kannalta järkevää, jos jokaiselle pelimuodolle olisi oma controller, näin vältytään myös toisteiselta koodilta.

## Sovelluslogiikka
Sovelluslogiikka on jaettu kolmeen osaan, pelin logiikkaan, solmu logiikkaan ja muuhun logiikkaan. Sovelluslogiikka sisältää lisäksi pakkauksen domain, joka sisältää rajapinnat kaikille sovellulogiikan metodeille. Käyttöliittymä keskustelee siis sovelluslogiikan kanssa pelkkien rajapintojen kautta. Pelin logiikan piiriin kuuluvat vain pelicontrollerin antamien viittausten kuunteleminen. Loput loogiikasta ovat pelin ulkopuolella tapahtuvaa sovelluslogiikkaa. Sovelluslogiikan osat ovat toiminnaltaan pyritty eriyttämään toisistaan mahdollisimman hyvin. Sovelluslogiikan luokka *NewGameLogic* on ainut luokka, joka hyödyntää logiikan pelipakkaukseen kuuluvaa *GameMode* oliota sen rajapinnan kautta viitaten ensin domainiin.

Sovelluslogiikassa on kolme olio luokkaa *Objective*, *Statistic* ja *GameMode*. Luokka *Objective* sisältää informaation pelissä olevista objektiivesta, eli suoritettavista tehtävistä. Luokka *Statistic* sisältää oliot, joita on tarkoitus hyödyntää kun käyttöliittymän *ItemNode* haluaa muodostaa pelitilastot aiemmin pelatuista peleistä. Kolmas olioluokka *GameMode* sisältää kaiken tarvittavan tiedon että haluttua pelimuotoa voidaan pelata. Käytännössä tietoa pelimuodosta jota pelataan parhaillaan säilytetään pelin ytimessä.

Logiikan luokat tekevät yhteistyötä sovelluksen alimman osan eli ytimen kanssa. Ydin jakautuu kolmeen aliytimeen, joilla on omat tehtävänsä asennuksen ydin, pelin ydin ja daon ydin. Sovelluslogiikka voi viitata kaikkiin aliytimiin paitsi asennuksen ytimeen pääytimen *Core* kautta. Ydin sisältää dao palvelut, pelimuodon alustuksen ja session tallennuksen, sekä asennukseen liittyvät toimenpiteet.

Solmulogiikan *(Node)* tarkoitus on nimensä mukaisesti toimia välikäten controller luokille *ItemNode*:n kautta. Käytännössä solmulogiikan luokat ovat *ItemNode*:n sovelluslogiikan luokkia, jotka sisältävät esimerkiksi dao käskyt. 

Ytimen daon luokat ovat ytimen ainoa luokat joilla on omat rajapintansa ytimen omassa domain pakkauksessa. Ydin ei siis tee viittauksia dao:on suoraan, vaan keskustelee rajapintojen kautta.

![Logic](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/logic.png)

## Tietojen tallentaminen
Pelin käyttämät tiedostot on tallennettu ohjelmiston sisältävän tiedostopolun *Programfiles* kansioon. Kansio sisältää tietokannan *data.db* ja kansion *Cluster*. Cluster kansio sisältää viisi eri properties tiedostoa, jotka on numeroitu. Tietokantaan tallennetaan rekisteröityneet pelaajat, peliin tallennetut objektiivit ja pelattujen pelien data. Cluster kansion properties tiedostot sisältävät custom pelimuotojen configuraatioita. Cluster tiedostot ovat vain lukua varten eikä niihin ohjelman aikana tallenneta mitään poislukien asennuksen aikana luodut configuraatiot.

## Toiminnalisuudet
Ohessa on sekvenssikaavio tapahtumasta, jossa käyttäjä painaa *Make choice* nappulaa. Kun pelaaja tekee jonkin valinnan, niin pisteet lasketaan pelaajan valitsemaansa kohtaan tai vaihtoehtoisesti jos on enään yksi objektiivi jäljellä, asetetaan pelaaja pelin päätös sceneen.
![Make choice](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgbWFrZUNob2ljZQoKR2FtZUNvbnRyb2xsZXItPgACDjogYWRkUG9pbnRzKCkACyFjcmVhdGVEaWNlTGlzdAAeE09iamVjdGl2ZUxvZ2ljOiBnZXQAVgdvABIILCAgezEsIDIsIDIsIDN9KQoAJg4tPkNhbGN1bGF0b3IAKRcAMQ4AJQoALQ5jAEAHZSgieDIiLCAwABEodGltZXMoMgBNGwCBFg40AAEaAA4NAIEQDnVzdG9tT3JOb3QoIngiLCA0LCB0cnVlAE0bLTEAgUgkbQBGBgCBCDQAgQcaAE4HAIECHwCBeg8Ag3wQNACDWhAAhHoSNACELSRVcHBlclN0YXR1cygAhC0RAIVOEnRydWUAhXQRSXRlbU5vZGU6IHVwcGVySWQKAAoIAIEEEi0xAIVNGgCFXQVJZAB8DACBRBMzAGUbAIYPCXMuZ2V0KDMpLnMAhisJIjQiAIcCEgCBMQpuYW1lc0lzRW1wdHkoKQCBJxsAgW4WWWF0enlVaTogc2V0Um9vdCgiZW5kZ2FtZSIpCgAVBwAaC2xvYWRGWE1MABEVRW5kAIhHEGluaXRpYWxpemUoKQoK&s=default)

Oheisessa sekvenssikaaviossa pelaaja painaa *Reroll* nappulaa.

![Reroll](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgcmVSb2xsCgpnYW1lQ29udHJvbGxlciAtPiBzY2VuZUxvZ2ljOiBnZXRSACQFU3RhdHVzKCkKABQKABwQMQAPDS0-IABNDjogMQBaEwAUEGdldFN1bSgpAIEHEQA8FDMAgR8fdXBkYXRlAIE7BkNvdW50AEUTAIFZFwAnCACBMiEKCgo&s=default)

Pelin alustamista kuvaavassa sekvenssi kaaviossa on jätettu pois luokassa *ItemNode* tapahtuvat asiat. Tässä tapauksessa luokka *ItemNode* luo käyttöliittymän käyttämät ObservableArrayList:it.

![New game](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgc3RhcnRHYW1lCgpOZXdHYW1lQ29udHJvbGxlciAtPiAADgdMb2dpYzogc2UAJwVtb2RlKEdhbWVNb2RlSUYgZwAEBykAPAgAKgUgLT4gQ29yZQAuCU1vZGUoABsRAFQcZ2V0AIEFCigATxAAgRUKAIEqCjogU3RyaW5nIGMAgT4JAIFDFllhdHp5VUkAgU8FUm9vdCgAJwopCgAXBmkgLT4gAE8QaW5pdGlhbGl6ZSgpCgCCHhIAFRoAgj4FAB8VRGljAIJZCG5ldwAFCgBMFU9iamVjdGl2ACYMAAYOAIEDFVNjZW4AWQwABgoAgSIlbG9hZEZhY3RvcmllcwCBZBVJdGVtTm9kAINoBlRvcFN0YXRpc3RpYwAMIwCBSwlzKCk&s=default)

## Sovellukseen jääneet heikkoudet
Sovellus ei tarkkaan noudata haluttua nelitasoista mallia, sillä on tapauksia, joissa käyttöliittymä asio *Core*:n kanssa eikä sovelluslogiikan. Toisaalta tämä järjestely vähentää kiertoa ohjelman sisällä. Kuitenkin sovelluksessa on myös tapauksia, joissa käyttöliittymän mahdollinen oikopolku suoraan *core*:en onkin toteutettu kiertotienä sovelluslogiikan kautta.

Pelin custom pelimuotoihin jäi turhan vähän customointi pelivaraa, joka saattaa hieman rajoittaa käyttökokemusta pelille, sekä sen pidempiaikaista pelattavuutta.

Dao luokkien käyttö ei ole välttämättä täysin selkeä, sillä sovellukseen jäi kohtia, joissa käyttöliittymä käyttää *Core*:n sisältämiä daoluokkia suoraan, ilman että käytetään ensin *SQLNode*:n metodeja, joka sisältää siis sovelluslogiikan dao kyselyt pääsosin.
