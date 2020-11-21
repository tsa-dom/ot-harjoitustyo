# Sovelluksen arkkitehtuuri
## Pakkausrakenne
Sovelluksella on kuusitasoinen pakkausrakenne

![Pakkausrakenne](https://github.com/tsa-dom/ot-harjoitustyo/blob/master/Images/pakkauskaavio.png)


Sovelluksen toiminnan kannalta main pakkauksella ei ole muuta merkitystä, kuin että se käynnistää sovelluksen. Sovelluksen käynnistyksen jälkeen käynnistetään sovelluksen pääkäyttöliittymä YatzyUi, joka sijoitsee pakkauskessa ui. Käyttöliittymän YatzyUi ainut tehtävä on suorittaa tarvittavien tiedostojen lataaminen, graafisen käyttöliittymän käynnistäminen ja controller luokkien scenenvaihtokäskyjen kuunteleminen. Uicontroller pakkauksen luokat toimivat käytössä olevan scenen kuuntelijoina eli rekisteröivät kaikki toimet mitä pelaaja sovellukselle antaa. Uicontroller pakkaus on osa käyttöliittymää, mutta tietyissä tapauksissa se hoitaa myös pienissä määrin sovelluslogiikkaa.

Controllerluokat tekevät viittauksia pelin kahteen sovelluslogiikkapakkaukseen game ja service, jotka ovat erotettu toisistaan siten, että game pakkaus sisältää vain pelin logiikan ja service kaiken muun. Sovelluslogiikka toimii yhteistyössä sovelluksen hermokeskuksen sisältävän core pakkauksen kanssa, joka sisältää pelin ytimen. Sovelluksen ydin käynnistetään sovelluksen käynnistysvaiheessa, mutta sen toiminta vastuu asennuksen jälkeen on vain sovelluslogiikalla, eikä muiden luokkien kuulu viitata siten ytimeen.

Ydin jakautuu kolmeen osaan, pelin ydin, daon ydin ja asennuksen ydin. Aliytimet ovat protected metodeja sisältäviä luokkia, joten ainut tapa viitata aliytimiin on pääytimen kautta tehty viittaaminen. Itse pääydin sisältää suurelta osin staattisia metodeja, joihin käytännössä mikä tahansa luokka voisi viitata, mutta käytännössä pääytimen käyttö on vain sovelluslogiikan käytettävissä, joissain tapauksessa controllerit saattavat tehdä ytimen kanssa yhteistyötä. Pelin ytimen tarkoitus on ylläpitää tieto kaikista käytettävissä olevista pelimuodoista ja vastuu pelin aikana olevan pelimuodon pitämisestä ladattuna, jotta peli voisi toimia oikein. Daon ydin tekee viitaukset sovelluksen dao:on, jossa tapahtuu esimerkiksi tiedon hakeminen ja varastoiminen tietokantaan. Asennuksen ytimen toiminta on rajoitettu käyttöliittymän käynnistyksen yhteyteen, jonka tehtävä on tehdä alku setup ennen kuin varsinainen ohjelma käynnistetään.
