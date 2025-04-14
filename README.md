# AstroMap
## Aplikacija za astronomiju + igra prepoznavanja zvijezda

AstroMap aplikacija omogućuje korisnicima da na jednostavan način uče o svemiru. Sadrži informacije o planetima, zvijezdama, galaksijama i zviježđima. Cilj aplikacije je edukacija, ali na zabavan način.

Aplikacija će imati glavnu stranicu s pregledom tema koje korisnik može istraživati. Korisnik može kliknuti na određenu temu (npr. "Sunčev sustav") i dobiti osnovne informacije o planetima, njihovim karakteristikama i zanimljivostima. Slično će biti i s ostalim dijelovima svemira – zvijezdama, crnim rupama, kometima itd.

Osim edukativnog dijela, aplikacija će imati i igru. U igri će korisnik morati prepoznati zviježđa tako što će ih povezivati linijama na noćnom nebu. Kada igra započne, na ekranu će se pojaviti nasumične zvijezde, a korisnik će dobiti zadatak pronaći određeno zviježđe (npr. Veliki Medvjed). Ako pravilno spoji zvijezde i formira zadano zviježđe, dobiva bodove i prelazi na sljedeću razinu. Igra može imati različite težine – na početku su zviježđa jednostavnija i lakše ih je prepoznati, dok kasnije postaju složenija.

## Projektni tim
### bobek-golubic-lazar

ime i prezime | e-mail adresa (FOI) | JMBAG | Github korisničko ime
------------  | ------------------- | ----- | ---------------------
Kaja Bobek | kbobek22@foi.hr | 0016162684 | kbobek22
Lana Golubić | lgolubic22@foi.hr | 0016160723 | LanaG7
Julianne Lazar | jlazar22@foi.hr | 0016159185 | juliannelazar

## Opis domene

Projekt se bavi razvojem aplikacije za astronomiju koja uključuje edukativne sadržaje o svemiru, zvijezdama, planetima i galaksijama. Aplikacija će pružiti korisnicima jednostavan način da nauče o osnovnim astronomskim pojmovima i o strukturama svemira. Dodatni element igre omogućit će korisnicima da testiraju svoje znanje o zviježđima i planetima kroz interaktivnu igru u kojoj će prepoznavati zvijezde i spajati ih u odgovarajuća zviježđa. Aplikacija će imati za cilj povećati znanje o astronomiji među korisnicima, a posebice mladima i studentima, koji žele proširiti svoje znanje o svemiru na zabavan način.

## Specifikacija projekta

### Funkcionalnosti
Oznaka | Naziv | Kratki opis 
------ | ----- | ----------- 
F01 | Edukativni moduli o strukturama svemira | Aplikacija pruža korisniku pregled edukativnih sadržaja kroz tematske cjeline (npr. Sunčev sustav, zvijezde, galaksije). Svaka cjelina uključuje slike i zanimljivosti kroz koje korisnici mogu učiti. 
F02 | Interaktivna igra prepoznavanja zviježđa | Igra u kojoj korisnik mora pronaći i povezati zvijezde na noćnom nebu kako bi formirao određeno zviježđe. 
F03 | Enciklopedija zvijezda | Baza podataka o poznatim zvijezdama s detaljnim informacijama – tip zvijezde, spektralna klasa, udaljenost od Zemlje, vidljivost tijekom godine, zanimljivosti.
F04 | Integracija neba u stvarnom vremenu | Prikaz virtualnog neba koji se mijenja ovisno o datumu.


### Podjela po zadatcima

Oznaka | Naziv | Broj zadatka | Opis zadatka | Zaduženi
------ | ----- | -----------  | ------------ | --------
F01 | Edukativni moduli |1| Dizajn strukture edukativnog sadržaja: odrediti tematske cjeline (npr. Sunčev sustav, galaksije, crne rupe, kometi). Definirati sadržaj za svaku temu (tekstovi, slike, zanimljivosti). | Lana
    |        |2| Kreiranje korisničkog sučelja (UI): XML layouti za pregled tema i prikaz sadržaja. Navigacija između tema (npr. preko RecyclerView i Fragmenta). | Lana

  |        |3| Testiranje i optimizacija: provjera responzivnosti na raznim uređajima. Provjera točnosti prikazanog sadržaja. | Lana


## Tehnologije i oprema
Za razvoj:
* Android Studio: razvojno okruženje za razvoj mobilne aplikacije za Android uređaje
* Kotlin: programski jezik za razvoj aplikacije
* XML: dizajn korisničkog sučelja

Za verzioniranje i dokumentaciju:
* Git: praćenje promjena u kodu i verzioniranje
* GitHub: pohrana koda, korištenje GitHub Wiki za tehničku dokumentaciju i GitHub Projects za praćenje zadataka
* Jira: upravljanje projektom, planiranje sprintova i praćenje napretka

Za početnih skica:
* Lucidchart i draw.io

Oprema:
* računalo s 16GB RAM-a
* mobilni uređaji s 4GB RAM-a
* tablet s 6GB RAM-a
* svi uređaji imaju više od potrebnih 30GB slobodnog prostora memorije
