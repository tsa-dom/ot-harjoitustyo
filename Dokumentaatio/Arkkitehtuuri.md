# Sovelluksen arkkitehtuuri
## Pakkausrakenne
Sovelluksella on neljäkerroksinen pakkausrakenne. Joidenkin pakkausten sisällä on olemaasa alipakkauksia, joiden tarkoitus on jaoitella toiminnallisuutta.

![Pakkausrakenne](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/pakkauskaavio.png)

Pakkaus *main* sisältää vain yhden luokan, jonka tarkoitus on käynnistää sovelluksen graafinen käyttöliittymä *YatzyUi*. Sovelluksen pääkäyttöliittymä *YatzyUi* kuuluu yksin pakkaukseen *ui* ja sen tarkoitus on alustaa sovelluksen hermokeskus *core* ja antaa sille käsky suorittaa ohjelman lataamiseen vaadittava aliydin *InstallCore*. Kun alustus on valmis, ladataan login scene ja käyttöliittymä vastuu siirretään controllereille. Nyt pääkäyttöliittymän ainoaksi tehtäväksi jää controllerluokkien kuunteleminen *setRoot* käskyn varalta, jolla scenejen vaihto toiseen tapahtuu ja samalla vaihdetaan controlleria.

Controllerit ovat osa käyttöliittymää ja jokaisella controllerilla on vastuulla ainakin yksi scene. Controllerien tehtävä on kuunnella käyttäjän toimintaa ja kutsua sovelluslogiikka tarvittaessa halutulla tavalla. Sceneen tehdyt muutokset eivät täysin tapahdu controllerien puolella, vaan controllerit antavat viitteinä sceneen liittyviä parametreja sovelluslogiikalle. Tämä järjestely vaikeuttaa hieman sovelluksen testaamista, mutta sovelluksen rakenne pysyy helpompilukuisena.

Sovelluslogiikka on jaettu kahteen osaan, pelin logiikkaan ja muuhun logiikkaan. Pelin logiikan piiriin kuuluvat vain pelicontrollerin antamien viittausten kuunteleminen. Loput loogiikasta ovat pelin ulkopuolella tapahtuvaa sovelluslogiikkaa. Logiikan luokat tekevät yhteistyötä sovelluksen alimman osan eli ytimen kanssa. Ydin jakautuu kolmeen aliytimeen, joilla on omat tehtävänsä asennuksen ydin, pelin ydin ja daon ydin. Sovelluslogiikka voi viitata kaikkiin aliytimiin paitsi asennuksen ytimeen pääytimen *Core* kautta. Ydin sisältää dao palvelut, pelimuodon alustuksen ja session tallennuksen, sekä asennukseen liittyvät toimenpiteet.

## Käyttöliittymä
Sovelluksen käyttöliittymä on jaettu kahteen osaan, pääkäyttöliittymään *YatzyUi*:hin ja sen käyttämiin controllereihin. Ohjelman scenet on määritetlty FXML tiedostoformaatissa ja täten jokaiselle scenelle on olemassa yksi controller, mutta yhdellä controllerilla voi olla useampi FXML määritelty scene. Hyvä esimerkkki controlleriin, joka sisältää tuen useille sceneille on *gameController*. Ei olisi koodin laadun kannalta järkevää, jos jokaiselle pelimuodolle olisi oma controller, näin vältytään myös toisteiselta koodilta.
