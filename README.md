# Yatzy
##### Sovellus antaa pelaajalle mahdollisuuden sukeltaa noppapeli Yatzyn ihmeelliseen maailmaan. Sen lisäksi, että pelaaja pääsee pelaamaan peruspeli Yatzya, pääsee hän pelaamaan peliin asetettuja erityisiä pelimuotoja, sekä tekemään oman pelimuotonsa muokkaamalla custom pelimuotoja sisältäviä cluster properties tiedostoja. Nykyisessä versiossa pelaajalla on jo mahdollisuus päästä pelaamaan peliä, mutta joitain ominaisuuksia pelistä puuttuu ja pisteiden laskussa voi ilmetä odottomattomia ongelmia.
##### Sovellus toimii javan versiolla 11 ja se on testattu Windows 10 ja Cubbli Linux käyttöjärjestelmillä. Toistaiseksi Linuxilla suoritettuna asettelussa saattaa ilmetä ongelmia, mutta ne tullaan korjaamaan tulevaisuudessa myös Linux yhteensopiviksi.
## Dokumentaatio
##### [Vaatimusmäärittely.md](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Dokumentaatio/Vaatimusmaarittely.md)
##### [Työaikakirjanpito.md](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Dokumentaatio/Tyoaikakirjanpito.md)
## Release
##### Ei valmiita julkaisuja
## Komentorivitoiminnot
### Testaaminen
##### Ohjelman testaus tapahtuu komennolla
<p>mvn test</p>
##### Testikattavuusraportti voidaan generoida komennolla
<p>mvn jacoco:report</p>
##### Testikattavuus raportti tallennetaan polkuun *target/site/jacoco/index.html*.
### Checkstyle
##### Checkstyle raportti voidaan generoida komennolla
<p>mvn jxr:jxr checkstyle:checkstyle</p>
##### Checkstyle raportti on tarkasteltavissa polussa *target/site/checkstyle.html*
### Jarin generointi
##### Suoritettavissa oleva jar tiedosto voidaan generoida komennolla
<p>mvn package</p>
##### Jar tiedosto löytyy polusta *target/Yatzy-1.0-SNAPSHOT*
