# Yatzy
Sovellus antaa pelaajalle mahdollisuuden sukeltaa noppapeli Yatzyn ihmeelliseen maailmaan. Sen lisäksi, että pelaaja pääsee pelaamaan peruspeli Yatzya, pääsee hän pelaamaan peliin asetettuja erityisiä pelimuotoja, sekä tekemään oman pelimuotonsa muokkaamalla custom pelimuotoja sisältäviä cluster properties tiedostoja.

Sovellus toimii javan versiolla 11 ja se on testattu Windows 10 ja Cubbli Linux käyttöjärjestelmillä. Toistaiseksi Linuxilla suoritettuna asettelussa saattaa ilmetä ongelmia, mutta ne tullaan korjaamaan tulevaisuudessa myös Linux yhteensopiviksi.
## Dokumentaatio
#### [Vaatimusmäärittely.md](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Dokumentaatio/Vaatimusmäärittely.md)
#### [Työaikakirjanpito.md](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Dokumentaatio/Työaikakirjanpito.md)
#### [Arkkitehtuuri.md](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Dokumentaatio/Arkkitehtuuri.md)
#### [Käyttöohje](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Dokumentaatio/K%C3%A4ytt%C3%B6ohje.md)
## Release
#### [Viikko 5 - linux](https://github.com/tsa-dom/ot-harjoitustyo/releases/tag/viikko5)
#### [Viikko 5 - windows](https://github.com/tsa-dom/ot-harjoitustyo/releases/tag/Viikko5)
#### [Viikko 6 - linux](https://github.com/tsa-dom/ot-harjoitustyo/releases/tag/viikko6-linux)
#### [Viikko 6 - windows](https://github.com/tsa-dom/ot-harjoitustyo/releases/tag/viikko6-win)
## Komentorivitoiminnot
### Testaaminen
Ohjelman testaus tapahtuu komennolla
> mvn test

Testikattavuusraportti voidaan generoida komennolla
> mvn jacoco:report

Testikattavuus raportti tallennetaan polkuun *target/site/jacoco/index.html*.
### Checkstyle
Checkstyle raportti voidaan generoida komennolla
> mvn jxr:jxr checkstyle:checkstyle

Checkstyle raportti on tarkasteltavissa polussa *target/site/checkstyle.html*
### Jarin generointi
Suoritettavissa oleva jar tiedosto voidaan generoida komennolla
> mvn package

Jar tiedosto löytyy polusta *target/Yatzy-1.0-SNAPSHOT*
### Javadoc
Javadocin voi generoida komennolla
> mvn javadoc:javadoc

Javadoc löytyy polusta *target/site/apidocs/*
