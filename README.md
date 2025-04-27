# AstroMap
## Aplikacija za astronomiju + igra prepoznavanja zviježđa

AstroMap aplikacija omogućuje korisnicima da na jednostavan način uče o svemiru. Sadrži informacije o planetima, zvijezdama, galaksijama i zviježđima. Cilj aplikacije je edukacija, ali na zabavan način.

Aplikacija će imati glavnu stranicu s pregledom tema o SUnčevom sustavu koje korisnik može istraživati. Korisnik može kliknuti na određenu temu i dobiti osnovne informacije o planetima, njihovim karakteristikama i zanimljivostima. Slično će biti i s ostalim dijelovima svemira – zvijezdama, mjesecima, kometima itd.

Osim edukativnog dijela, aplikacija će imati i igru. U igri će korisnik morati prepoznati zviježđa tako što će ih povezivati linijama na noćnom nebu. Kada igra započne, na ekranu će se pojaviti nasumične zvijezde, a korisnik će dobiti zadatak pronaći određeno zviježđe (npr. Veliki Medvjed). Ako pravilno spoji zvijezde i formira zadano zviježđe, dobiva postignuća i prelazi na sljedeću razinu. 

## Projektni tim
### bobek-golubic-lazar

ime i prezime | e-mail adresa (FOI) | JMBAG | Github korisničko ime
------------  | ------------------- | ----- | ---------------------
Kaja Bobek | kbobek22@foi.hr | 0016162684 | kbobek22
Lana Golubić | lgolubic22@foi.hr | 0016160723 | LanaG7
Julianne Lazar | jlazar22@foi.hr | 0016159185 | juliannelazar

## Opis domene

Projekt se bavi razvojem aplikacije za astronomiju koja uključuje edukativne sadržaje o svemiru, zvijezdama, planetima i galaksijama. Aplikacija će pružiti korisnicima jednostavan način da nauče o osnovnim astronomskim pojmovima i o strukturama svemira. Dodatni element igre omogućit će korisnicima da testiraju svoje znanje o zviježđima kroz interaktivnu igru u kojoj će prepoznavati zvijezde i spajati ih u odgovarajuća zviježđa. Aplikacija će imati za cilj povećati znanje o astronomiji među korisnicima.

## Specifikacija projekta

### Funkcionalnosti
Oznaka | Naziv | Kratki opis 
------ | ----- | ----------- 
F01 | Edukativni moduli o strukturama svemira | Korisniku će biti omogućen pregled edukativnih sadržaja kroz tematske cjeline. Svaka cjelina uključivat će slike i zanimljivosti kroz koje će korisnici moći učiti. Funkcionalnost će obuhvaćati kreiranje interaktivnih edukacijskih stranica te navigaciju kroz teme i dizajn.
F02 | Interaktivna igra prepoznavanja zviježđa | Korisnik će u igri moći pronaći i povezati zvijezde na noćnom nebu pri čemu će se formirati određeno zviježđe. Implementirat će se logika igre, generiranje zvijezda na ekranu, validacija spojeva te prelazak na iduće levele.
F03 | Enciklopedija struktura svemira | Baza podataka o zviježđima - pozicije, koje ih zvijezde tvore i slično. Baza također sadrži sve podatke o svim strukturama Sunčevog sustava koji se koriste u modulima (imena, opisi, slike). 
F04 | Sustav postignuća | Omogućava korisnicima da prate svoj napredak.


### Podjela po zadatcima

Oznaka | Naziv | Broj zadatka | Opis zadatka | Zaduženi
------ | ----- | -----------  | ------------ | --------
F01 | Edukativni moduli |1| Dizajn strukture edukativnog sadržaja: odrediti tematske cjeline (npr. Sunčev sustav, galaksije, crne rupe, kometi). Definirati sadržaj za svaku temu (tekstovi, slike, zanimljivosti). | Lana 
F01 | Edukativni moduli |2| Definirati sadržaj za svaku temu (tekstovi, slike, zanimljivosti). | Julianne 
F01 | Edukativni moduli |3| Kreiranje korisničkog sučelja (UI): XML layouti za pregled tema i prikaz sadržaja. Navigacija između tema (npr. preko RecyclerView i Fragmenta). | Lana
F01 | Edukativni moduli |4| Testiranje i optimizacija: provjera responzivnosti na raznim uređajima. Provjera točnosti prikazanog sadržaja.| Lana
F02 | Igra prepoznavanja zviježđa |5| Generiranje zvijezda na ekranu: slučajno postavljanje točaka koje predstavljaju zvijezde. Animacija i izgled noćnog neba (Canvas, SurfaceView, ili custom View).| Kaja
F02 | Igra prepoznavanja zviježđa |6| Zadavanje cilja: na početku igre odabrati zviježđe koje korisnik mora pronaći. Prikaz imena i forme zviježđa koje treba formirati.| Kaja
F02 | Igra prepoznavanja zviježđa |7| Logika povezivanja zvijezda: implementacija povlačenja linija između zvijezda. Provjera je li korisnik pravilno spojio zadano zviježđe.| Kaja
F02 | Igra prepoznavanja zviježđa |8| Bilježenje prijeđenih razina. | Julianne
F03 | Enciklopedija struktura svemira |9| Kreiranje baze podataka (lokalno): korištenje Room baze za pohranu podataka o svemirskim strukturama. | Julianne
F04 | Sustav postignuća |10| Registracija i prijava korisnika.| Lana
F04 | Sustav postignuća |11| Prikaz postignuća korisnika.| Julianne



## Tehnologije i oprema
Za razvoj:
* Android Studio: razvojno okruženje za razvoj mobilne aplikacije za Android uređaje
* Kotlin: programski jezik za razvoj aplikacije
* XML: dizajn korisničkog sučelja

Za dokumentaciju:
* GitHub: pohrana koda
* Jira: upravljanje projektom, planiranje sprintova i praćenje napretka
* Confluence: dokumentiranje sastanaka

Za početne skice:
* papie, draw.io

Oprema:
* računalo s 16GB RAM-a
* mobilni uređaji s 4GB RAM-a
* tablet s 6GB RAM-a
* svi uređaji imaju više od potrebnih 30GB slobodnog prostora memorije
