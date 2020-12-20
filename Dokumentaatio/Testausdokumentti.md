# Testausdokumentti
Sovelluksen yksikkötestit on tehty JUnitilla. Sovellukse testauksen yhteydessä luodaan erikseen oma testi kansio, joka sisältää samat tiedostot kuin kansio *Programfiles*. Sovellus ei poista testikansiota testauksen jälkeen, vaan se on poistettava manuaalisesti. Testi kansion olemassa olo ei vaikuta muun sovelluksen toimintaa. Sovelluksessa jäi testaamatta osa sovelluslogiikasta.

## Testikattavuus
Sovelluksen rivikattavuus on 86% ja haarautuma kattavuus 91%.

![Testi kattavuus](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/image9.png)

## Käyttöliittymä
Sovelluksen käyttöliittymää ei testata yksikkötesteissä, vaan testaaminen on suoritettu sovelluksen käyttötestauksena.

## Puutteet testeissä
Testesihin jäi hieman puutteita nimentään ja testaurakenteeseen. Testejä olisi voinut jakaa tarkemmin useammiksi eri testeiksi.
