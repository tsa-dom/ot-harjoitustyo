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

## Sekvenssikaaviot
Ohessa on sekvenssikaavio tapahtumasta, jossa käyttäjä painaa *Make choice* nappulaa
![Make choice](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgbWFrZUNob2ljZQoKR2FtZUNvbnRyb2xsZXItPgACDjogYWRkUG9pbnRzKCkACyFjcmVhdGVEaWNlTGlzdAAeE09iamVjdGl2ZUxvZ2ljOiBnZXQAVgdvABIILCAgezEsIDIsIDIsIDN9KQoAJg4tPkNhbGN1bGF0b3IAKRcAMQ4AJQoALQ5jAEAHZSgieDIiLCAwABEodGltZXMoMgBNGwCBFg40AAEaAA4NAIEQDnVzdG9tT3JOb3QoIngiLCA0LCB0cnVlAE0bLTEAgUgkbQBGBgCBCDQAgQcaAE4HAIECHwCBeg8Ag3wQNACDWhAAhHoSNACELSRVcHBlclN0YXR1cygAhC0RAIVOEnRydWUAhXQRSXRlbU5vZGU6IHVwcGVySWQKAAoIAIEEEi0xAIVNGgCFXQVJZAB8DACBRBMzAGUbAIYPCXMuZ2V0KDMpLnMAhisJIjQiAIcCEgCBMQpuYW1lc0lzRW1wdHkoKQCBJxsAgW4WWWF0enlVaTogc2V0Um9vdCgiZW5kZ2FtZSIpCgAVBwAaC2xvYWRGWE1MABEVRW5kAIhHEGluaXRpYWxpemUoKQoK&s=default)
