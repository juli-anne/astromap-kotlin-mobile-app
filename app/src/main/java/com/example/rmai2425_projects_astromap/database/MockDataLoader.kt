package com.example.rmai2425_projects_astromap.database

object MockDataLoader {

    data class SuncevSustavInfo(
        val naslov: String,
        val kratkiOpis: String,
        val detaljanOpis: String,
        val kategorija: String
    )

    data class MjesecInfo(
        val ime: String,
        val planetIme: String,
        val kratkiOpis: String,
        val detaljanOpis: String,
        val velicina: Double,
        val zanimljivosti: String
    )

    fun getSunSystemInfo(): List<SuncevSustavInfo> = listOf(
        SuncevSustavInfo(
            naslov = "Sunčev sustav",
            kratkiOpis = "Gravitacijski vezan sustav star 4,6 milijardi godina",
            detaljanOpis = "Sunčev sustav je gravitacijski vezan sustav koji se sastoji od Sunca i svih nebeskih tijela koja kruže oko njega. Formiran je prije približno 4,6 milijardi godina iz kolapsa divovske molekularne oblaka. U središtu se nalazi naše Sunce, zvijezda spektralnog tipa G2V koja sadrži 99,86% ukupne mase cijelog sustava.",
            kategorija = "Opće"
        ),
        SuncevSustavInfo(
            naslov = "Struktura sustava",
            kratkiOpis = "Podjela na unutarnji i vanjski dio",
            detaljanOpis = "Sunčev sustav možemo podijeliti na nekoliko glavnih zona. Unutarnji dio čine četiri kamenita planeta - Merkur, Venera, Zemlja i Mars. Ovi planeti su relativno mali, gusti i imaju čvrste površine. Između Marsa i Jupitera nalazi se asteroidni pojas, područje prepuno kamenih ostataka iz vremena formiranja sustava. Vanjski dio dominiraju četiri plinovita diva - Jupiter, Saturn, Uran i Neptun.",
            kategorija = "Struktura"
        ),
        SuncevSustavInfo(
            naslov = "Raznolikost objekata",
            kratkiOpis = "Više od 200 mjeseca i milijuni malih tijela",
            detaljanOpis = "Sustav je dom više od 200 mjeseca, od kojih su neki veći od planeta Merkura. Jupiter predvodi s više od 90 mjeseca, uključujući Europa, Ganimed i Io - neke od najfascinantnijih objekata u sustavu. Kometi i asteroidi predstavljaju ostatke iz ranih dana formiranja sustava.",
            kategorija = "Objekti"
        ),
        SuncevSustavInfo(
            naslov = "Jedinstvene karakteristike",
            kratkiOpis = "Svaki objekt ima svoju priču",
            detaljanOpis = "Svaki objekt u Sunčevom sustavu ima svoju jedinstvenu priču - od vulkanske aktivnosti na Io do spektakularnih prstenova Saturna, od polarnih kapa na Marsu do gejzira leda na Europi. Ova raznolikost čini naš sustav jednim od najfascinantnijih mjesta za istraživanje u svemiru.",
            kategorija = "Zanimljivosti"
        )
    )

    fun getPlanets(): List<Planet> = listOf(
        Planet(
            ime = "Merkur",
            kratkiOpis = "Merkur je najmanji planet u Sunčevom sustavu i najbliži Suncu. Njegova površina je prekrivena kraterima, slična Mjesecu.",
            detaljanOpis = "Merkur je najbrži planet u Sunčevom sustavu, jer obiđe Sunce za samo 88 dana. Ima gotovo nikakvu atmosferu, pa temperature na njegovoj " +
                            "površini variraju od +430°C na sunčanoj strani do -180°C na tamnoj strani. Merkur ima mnoge sličnosti s Mjesecom, uključujući mnoge " +
                            "kratere, ali nema mjesece. Zbog svoje blizine Suncu, njegovo kretanje je izuzetno teško pratiti s Zemlje.",
            povrsinskaTemperaturaDan = "+430°C",
            povrsinskaTemperaturaNoc = "-180°C",
            promjer = 4880.0,
            imaMjesec = false
        ),
        Planet(
            ime = "Venera",
            kratkiOpis = "Venera je drugi planet od Sunca i najviše nalikuje Zemlji po veličini, no ima izuzetno gustu atmosferu.",
            detaljanOpis = "Venera ima najgušću atmosferu u Sunčevom sustavu, koja se sastoji uglavnom od ugljičnog dioksida. Zbog toga se stvara izuzetno snažan efekt staklenke, zbog kojeg je temperatura na površini vrlo visoka, dosežući i do 460°C. Atmosfera je toliko gusta da je površina planeta potpuno nevidljiva s površine kroz teleskope. Venera ima mnoge vulkane i planine, a rotira u suprotnom smjeru od većine planeta u Sunčevom sustavu, zbog čega dan na Veneri traje duže od godine.",
            povrsinskaTemperaturaDan = "460°C",
            povrsinskaTemperaturaNoc = "460°C",
            promjer = 12104.0,
            imaMjesec = false
        ),
        Planet(
            ime = "Zemlja",
            kratkiOpis = "Zemlja je treći planet od Sunca i jedini poznat planet na kojem postoji život.",
            detaljanOpis = "Zemlja je jedini planet u Sunčevom sustavu koji ima tekuću vodu na svojoj površini. Njezina atmosfera sadrži 21% kisika, što omogućava život. Zemlja je ujedno jedini planet koji ima tekuću vodu, stabilnu klimu i složenu biotsku zajednicu. Na Zemlji postoje kontinenti i oceani, a ljudi, životinje i biljke čine složene ekosustave. Zemlja se okreće oko Sunca u 365 dana, a rotira oko svoje osi svakih 24 sata, što uzrokuje izmjenu dana i noći.",
            povrsinskaTemperaturaDan = "15°C",
            povrsinskaTemperaturaNoc = "15°C",
            promjer = 12742.0,
            imaMjesec = true
        ),
        Planet(
            ime = "Mars",
            kratkiOpis = "Mars je poznat kao Crveni planet zbog svoje crvene boje koju uzrokuje željezo u njegovoj prašini.",
            detaljanOpis = "Mars je četvrti planet od Sunca i ima površinu prepunu prašinskih oluja, kanjona i vulkana. Atmosfera Marsa je vrlo tanka, sastoji se uglavnom od ugljičnog dioksida, a temperatura na njegovoj površini može pasti ispod -100°C. Znanstvenici vjeruju da je Mars nekada imao vodu na svojoj površini, a danas postoje znakovi da bi mogao imati podzemne rezervoare vode. Mars ima dva mala mjeseca, Phobos i Deimos, koji su vjerojatno uhvaćeni asteroidi.",
            povrsinskaTemperaturaDan = "-63°C",
            povrsinskaTemperaturaNoc = "-63°C",
            promjer = 6779.0,
            imaMjesec = true
        ),
        Planet(
            ime = "Jupiter",
            kratkiOpis = "Jupiter je najveći planet u Sunčevom sustavu i poznat je po svojoj masivnoj atmosferi i snažnoj gravitaciji.",
            detaljanOpis = "Jupiter je plinoviti div, a njegova atmosfera sastoji se uglavnom od vodika i helija. Na Jupiteru se nalazi poznati Veliki crveni gori, ogromna oluja koja traje već stoljećima. Jupiter ima više od 90 mjeseca, uključujući Europa, Ganimed i Io, koji su među najvećim mjesecevima u Sunčevom sustavu. Zbog svoje ogromne mase, Jupiter ima izuzetno snažnu gravitaciju koja utječe na mnoge objekte u Sunčevom sustavu, uključujući komete i asteroide.",
            povrsinskaTemperaturaDan = "-108°C",
            povrsinskaTemperaturaNoc = "-108°C",
            promjer = 139820.0,
            imaMjesec = true
        ),
        Planet(
            ime = "Saturn",
            kratkiOpis = "Saturn je planet poznat po svojim spektakularnim prstenovima, koji su najljepši u Sunčevom sustavu.",
            detaljanOpis = "Saturn je drugi po veličini planet u Sunčevom sustavu i plinoviti je div. Atmosfera Saturna sastoji se od vodika, helija i malih količina metana i amonijaka. Planetu krase prstenovi koji se sastoje od leda i prašine, a najpoznatiji prstenovi su A, B i C prsten. Saturn ima više od 80 mjeseca, uključujući Titan, drugi najveći mjesec u Sunčevom sustavu. Titan je jedini mjesec u Sunčevom sustavu koji ima gustu atmosferu, tekuću metansku kišu i tekući metan na površini.",
            povrsinskaTemperaturaDan = "-178°C",
            povrsinskaTemperaturaNoc = "-178°C",
            promjer = 116460.0,
            imaMjesec = true
        ),
        Planet(
            ime = "Uran",
            kratkiOpis = "Uran je plinoviti div s posebnim kutom rotacije, koji je gotovo vodoravan u odnosu na svoju orbitu.",
            detaljanOpis = "Uran je sedmi planet od Sunca i poznat je po svom neobičnom kutu rotacije, jer se njegova osa rotacije gotovo potpuno poklapa s ravninom njegove orbite. Uranova atmosfera sastoji se od vodika, helija i metana, zbog čega planet ima plavičasto-zelenu boju. Uran je hladan planet, s najnižom poznatom temperaturom među planetima Sunčevog sustava. Uran ima 27 poznatih mjeseca, a najpoznatiji je Titania.",
            povrsinskaTemperaturaDan = "-224°C",
            povrsinskaTemperaturaNoc = "-224°C",
            promjer = 50724.0,
            imaMjesec = true
        ),
        Planet(
            ime = "Neptun",
            kratkiOpis = "Neptun je posljednji planet Sunčevog sustava i ima najjače vjetrove u Sunčevom sustavu.",
            detaljanOpis = "Neptun je osmi planet od Sunca i plinoviti je div. Sastoji se od vodika, helija i metana, koji mu daje plavičastu boju. Neptun je poznat po svojim izuzetno jakim vjetrovima koji dosežu brzine do 2.400 km/h. Ovaj planet također ima ogromne oluje koje mogu trajati godinama. Neptun ima 14 mjeseca, a najpoznatiji je Triton, koji ima retrogradnu orbitu, što znači da orbitira u suprotnom smjeru od većine drugih mjeseca u Sunčevom sustavu.",
            povrsinskaTemperaturaDan = "-214°C",
            povrsinskaTemperaturaNoc = "-214°C",
            promjer = 49244.0,
            imaMjesec = true
        )
    )

    fun getMoonsInfo(): List<MjesecInfo> = listOf(
        MjesecInfo(
            ime = "Mjesec",
            planetIme = "Zemlja",
            kratkiOpis = "Mjesec je jedini prirodni satelit Zemlje i najbliži objekt na nebu. Njegov površinski izgled dominira kraterima i velikim ravnicama.",
            detaljanOpis = "Mjesec je najveći prirodni satelit u Sunčevom sustavu u odnosu na veličinu planeta kojem pripada. Iako nema atmosferu, " +
                            "na Mjesecu su prisutni veliki krateri, ravnice i planine, a najpoznatija područja su \"morske\" (mare), koja su tamnija. " +
                            "Mjesec je odgovoran za plime i oseke na Zemlji, jer gravitacija Mjeseca vuče vodu prema sebi. Također, Mjesec ima stalnu " +
                            "temperaturu, od -173°C noću do +127°C danju. Mjesec ima faze (novi mjesec, prva četvrt, pun mjesec) zbog svoje rotacije oko Zemlje.",
            velicina = 3474.0,
            zanimljivosti = "Mjesec je jedini objekt u svemiru na kojem su ljudi hodali. Misija Apollo 11 1969. godine, Neil Armstrong i Buzz Aldrin, " +
                            "prvi su ljudi koji su kročili na Mjesec."
        ),
        MjesecInfo(
            ime = "Europa",
            planetIme = "Jupiter",
            kratkiOpis = "Europa je mjesec Jupitera i jedno od najsvjetlijih tijela u Sunčevom sustavu. Poznata je po svom ledenom pokrovu i mogućem oceanu " +
                         "ispod površine.",
            detaljanOpis = "Europa je prekrivena slojem leda debljine nekoliko kilometara, a pod njim se vjeruje da postoji veliki slani ocean. Znanstvenici " +
                            "smatraju da bi ovaj ocean mogao imati uvjete pogodne za život, jer se ispod površine može nalaziti topla voda zbog geotermalne " +
                            "energije. Na Europi su uočeni gejziri leda koji eruptiraju iz oceana, što dodatno sugerira prisutnost tekuće vode. S obzirom na " +
                            "njezinu veličinu, Europa je najzanimljiviji mjesec Jupitera i često je predmet istraživanja za misije koje traže potencijalne " +
                            "uvjete za život.",
            velicina = 3121.0,
            zanimljivosti = "Europa je jedno od najzanimljivijih mjesta za buduća istraživanja, jer znanstvenici vjeruju da bi pod njenom ledenom korom mogao " +
                            "postojati život. Misija NASA-e \"Europa Clipper\" ima za cilj detaljno istražiti ovaj mjesec."
        ),
        MjesecInfo(
            ime = "Titan",
            planetIme = "Saturn",
            kratkiOpis = "Titan je najveći mjesec Saturna i drugi po veličini u Sunčevom sustavu. Ima atmosferu koja je vrlo gusta i bogata metanom.",
            detaljanOpis = "Titan je jedini mjesec u Sunčevom sustavu koji ima gustu atmosferu, koja je uglavnom sastavljena od dušika i metana. Zbog toga, " +
                            "na Titanovoj površini padaju metanske kiše, a tekućina na njegovoj površini nije voda, već metan i etan. Titan također ima tekuće " +
                            "jezera i mora, koja su formirana od tekućih ugljikovodika. Pod njegovom površinom se smatra da bi mogao postojati ocean koji je " +
                            "vrući i vodenasti. Titanov interes za znanstvenike leži u njegovoj atmosferi i mogućnosti razvoja prebiotskih kemijskih procesa.",
            velicina = 5151.0,
            zanimljivosti = "Titan je jedini mjesec koji ima gustu atmosferu i možda je najviše sliči ranim uvjetima na Zemlji. NASA-ina misija \"Cassini-Huygens\" " +
                            "iz 2005. godine omogućila je spuštanje na Titan i prva je napravila analize njegove površine."
        ),
        MjesecInfo(
            ime = "Ganimed",
            planetIme = "Jupiter",
            kratkiOpis = "Ganimed je najveći mjesec u Sunčevom sustavu i jedini mjesec koji ima magnetsko polje.",
            detaljanOpis = "Ganimed je veći od Merkura i jedini mjesec u Sunčevom sustavu koji ima vlastito magnetsko polje. Njegova površina je prekrivena slojem " +
                            "leda, a ispod nje se nalazi vjerojatno tekući ocean. Ganimed također ima atmosferu koja je vrlo tanka i sastoji se od kisika, ali je " +
                            "suviše tanka da bi podržala ljudski život. Svi podaci upućuju na to da bi Ganimed mogao imati uvjete pogodne za razvoj života pod " +
                            "njegovom površinom, zbog geotermalne energije koja može održavati tekuću vodu.",
            velicina = 5268.0,
            zanimljivosti = "Ganimed je jedini mjesec u Sunčevom sustavu koji ima magnetsko polje, što ga čini jedinstvenim među mjesecima. Misije poput \"JUICE\" " +
                            "(Jupiter Icy Moons Explorer) planiraju detaljnije istražiti Ganimed u budućnosti."
        ),
        MjesecInfo(
            ime = "Io",
            planetIme = "Jupiter",
            kratkiOpis = "Io je mjesec Jupitera, poznat po svojoj vulkanskoj aktivnosti. Ima najveći broj vulkana u Sunčevom sustavu.",
            detaljanOpis = "Io je jedan od najaktivnijih svjetlosnih tijela u Sunčevom sustavu, zahvaljujući svojoj snažnoj vulkanskoj aktivnosti. Iako je ledena, " +
                            "površina Ioa prekrivena je lavama, dok vulkani eruptiraju u veliku visinu. Zbog plimskih sila koje Io doživljava od strane Jupitera i " +
                            "njegovih drugih mjeseca (Europa i Ganimed), planeti uzrokuju trenje u njegovoj unutrašnjosti, što generira toplinu koja uzrokuje vulkane. " +
                            "Io je neobičan jer nema nijednu vodu, a atmosfera mu je izuzetno tanka.",
            velicina = 3643.0,
            zanimljivosti = "Io je planet s najviše vulkanske aktivnosti u Sunčevom sustavu, što znači da je geološki najaktivniji mjesec. U njegovoj atmosferi nalazi " +
                            "se uglavnom sumporni dioksid."
        ),
        MjesecInfo(
            ime = "Triton",
            planetIme = "Neptun",
            kratkiOpis = "Triton je najveći mjesec Neptuna i jedini veliki mjesec koji ima retrogradnu orbitu.",
            detaljanOpis = "Triton je poznat po svojoj retrogradnoj orbiti, što znači da orbitira u suprotnom smjeru od većine drugih mjeseca u Sunčevom sustavu. Na " +
                            "Tritonu se nalaze gejziri koji izbacuju tekući dušik u svemir, zbog čega je ovo jedno od najzanimljivijih tijela u Sunčevom sustavu. " +
                            "Triton ima vrlo hladnu površinu, koja se sastoji od leda i stijena, a njegovi gejziri ukazuju na unutrašnju toplinu, što sugerira da bi " +
                            "mogao imati podzemni ocean.",
            velicina = 2710.0,
            zanimljivosti = "Triton je jedini veliki mjesec u Sunčevom sustavu koji ima retrogradnu orbitu, a to znači da je vjerojatno bio uhvaćen od strane Neptuna."
        ),
        MjesecInfo(
            ime = "Miranda",
            planetIme = "Uran",
            kratkiOpis = "Miranda je jedan od mjeseca Urana, poznat po svojoj neobičnoj geološkoj povijesti.",
            detaljanOpis = "Miranda je mjesec Urana koji je zanimljiv zbog svojih velikih geoloških razlika. Površina Mirande prekrivena je visokim planinama, dubokim " +
                            "kanjonima i velikim udubinama. Znanstvenici vjeruju da je Miranda doživjela masivnu sudar koja je stvorila mnoge od ovih značajki. Njezin " +
                            "geološki 'kaotičan' izgled ukazuje na to da je mjesec prošao kroz različite faze evolucije, uključujući preoblikovanje koje se dogodilo " +
                            "prije milijarde godina.",
            velicina = 471.6,
            zanimljivosti = "Miranda ima jedne od najnevjerojatnijih geoloških značajki u Sunčevom sustavu, uključujući kanjone duboke do 20 km."
        )
    )

    fun getSunce(): List<Sunce> = listOf(
        Sunce(
            ime = "Sunce",
            kratkiOpis = "Sunce je zvijezda smještena u središtu Sunčevog sustava i glavni je izvor energije za život na Zemlji.",
            detaljanOpis = "Sunce je zvijezda tipa G2V, smještena u središtu Sunčevog sustava, i najvažniji objekt za sve planete koje kruže oko njega, uključujući " +
                            "Zemlju. Sastoji se uglavnom od vodika i helija, a generira energiju kroz proces nuklearne fuzije u svojoj jezgri. Proces fuzije, koji " +
                            "spaja vodik u helij, oslobađa ogromnu količinu energije koja se širi kroz Sunce i dolazi do Zemlje u obliku svjetlosti i topline.",
            povrsinskaTemperatura = "5500°C",
            temperaturaJezgre = "15.000.000°C",
            promjer = 1391000.0,
            masa = 1.989e30,
            sastav = "Vodik: 74%, Helij: 24%, Ostali elementi: 2%"
        )
    )

    fun getAsteroids(): List<Asteroid> = listOf(
        Asteroid(
            ime = "Ceres",
            kratkiOpis = "Ceres je najveći objekt u asteroidnom pojasu i jedini patuljasti planet unutar ovog područja.",
            detaljanOpis = "Ceres je prvi asteroid otkriven 1801. godine i dugo je smatran asteroidom sve do 2006. godine kada je re-kategoriziran kao patuljasti " +
                            "planet. Ima svoj vlastiti ledeni pokrov i u njegovim unutrašnjim dijelovima možda se nalaze podzemna jezera tekuće vode. Misija NASA-e " +
                            "'Dawn' pružila je detaljne slike i podatke o Ceresu, otkrivši fascinantne značajke kao što su svjetleće mrlje u kraterima, koje mogu " +
                            "biti ostaci slane vode.",
            smjestaj = "Asteroidni pojas između Marsa i Jupitera",
            sastav = "Stijene, led"
        ),
        Asteroid(
            ime = "Vesta",
            kratkiOpis = "Vesta je drugi po veličini objekt u asteroidnom pojasu i jedini asteroid proučavan misijom 'Dawn'.",
            detaljanOpis = "Vesta je prekrivena velikim udubljenjima, koja su posljedica sudara s drugim objektima. Zanimljiva je zbog svoje geološke povijesti, " +
                            "a vjeruje se da je dio njene površine nastao kroz sudare. Vesta posjeduje složenu unutrašnju strukturu, s jednim velikim jezerom " +
                            "lave u svom središtu.",
            smjestaj = "Asteroidni pojas između Marsa i Jupitera",
            sastav = "Stijene, minerali"
        ),
        Asteroid(
            ime = "Eros",
            kratkiOpis = "Eros je asteroid tipa S, poznat po tome što je bio prva velika misija koja je poslala svemirski brod na njegovu površinu.",
            detaljanOpis = "Eros je otkriven 1898. godine i od tada je predmet brojnih istraživanja. NASA-ina misija 'NEAR Shoemaker' 2000. godine bila je prva " +
                            "koja je uspješno orbitirala i sletjela na površinu asteroida Eros, pružajući nevjerojatne podatke o njegovoj strukturi i sastavu.",
            smjestaj = "Unutarnji Sunčev sustav",
            sastav = "Stijene, metali"
        ),
        Asteroid(
            ime = "Itokawa",
            kratkiOpis = "Itokawa je mali asteroid tipa S, poznat po svojoj neobičnoj, duguljastoj formi.",
            detaljanOpis = "Otkriven je 1998. godine, a najpoznatiji je po tome što je bio ciljana destinacija misije japanske svemirske agencije JAXA, " +
                            "'Hayabusa', koja je 2005. godine poslala sondu na asteroid i vratila uzorke s njegove površine. Sonda 'Hayabusa' dala je prvi " +
                            "uspješan povrat uzoraka s asteroida.",
            smjestaj = "Unutarnji Sunčev sustav",
            sastav = "Stijene, minerali"
        ),
        Asteroid(
            ime = "Pallas",
            kratkiOpis = "Pallas je treći po veličini asteroid u Sunčevom sustavu i ima nezemaljski, nepravilni oblik.",
            detaljanOpis = "Otkriven je 1802. godine, a zbog svojih velikih dimenzija bio je jedan od prvih objekata koji su prepoznati kao asteroidi. Pallas " +
                            "je među najneobičnijim asteroidima zbog svog složenog orbitalnog kretanja i specifične reflektivnosti koja se razlikuje od drugih " +
                            "objekata u asteroidnom pojasu.",
            smjestaj = "Asteroidni pojas između Marsa i Jupitera",
            sastav = "Stijene, minerali"
        )
    )

    fun getComets(): List<Komet> = listOf(
        Komet(
            ime = "Halleyjev komet",
            kratkiOpis = "Halleyjev komet je jedan od najpoznatijih kometa u Sunčevom sustavu, a najpoznatiji je zbog toga što je jedini komet koji je vidljiv " +
                         "iz Zemlje s povremenim intervalima od oko 76 godina.",
            orbitalniPeriod = 76,
            posljednjiPerihel = "1986.",
            sljedeciPerihel = "2061.",
            velicinaJezgre = 15.0
        ),
        Komet(
            ime = "Hale-Bopp",
            kratkiOpis = "Hale-Bopp je jedan od najsvjetlijih kometa ikada zabilježenih. Prošao je kroz Sunčev sustav 1997. godine i bio je vidljiv golim okom, " +
                         "čak i iz gradova.",
            orbitalniPeriod = 2533,
            posljednjiPerihel = "1997.",
            sljedeciPerihel = "4530.",
            velicinaJezgre = 40.0
        ),
        Komet(
            ime = "Neowise",
            kratkiOpis = "Neowise je komet koji je postao vrlo poznat tijekom 2020. godine, jer je bio jasno vidljiv golim okom s površine Zemlje.",
            orbitalniPeriod = 6766,
            posljednjiPerihel = "2020.",
            sljedeciPerihel = "8786.",
            velicinaJezgre = 5.0
        ),
        Komet(
            ime = "Tempel 1",
            kratkiOpis = "Tempel 1 je komet koji je postao poznat kada je NASA-ina misija 'Deep Impact' 2005. godine ispalila projektile na njegovu površinu " +
                         "kako bi analizirali materijal ispod nje.",
            orbitalniPeriod = 6,
            posljednjiPerihel = "2022.",
            sljedeciPerihel = "2028.",
            velicinaJezgre = 14.0
        ),
        Komet(
            ime = "Enckeov komet",
            kratkiOpis = "Enckeov komet je komet koji ima najkraći poznati orbitalni period od svih kometa, jer mu je potrebno samo oko 3,3 godine da obiđe Sunce.",
            orbitalniPeriod = 3,
            posljednjiPerihel = "2017.",
            sljedeciPerihel = "2020.",
            velicinaJezgre = 4.8
        )
    )

    fun getObjects(): List<ObjektSuncevogSustava> = listOf(
        ObjektSuncevogSustava(
            ime = "Pluton",
            tip = "Patuljasti planet",
            smjestaj = "Kuiperov pojas (izvan orbite Neptuna)",
            opis = "Pluton je nekada bio deveti planet Sunčevog sustava, ali je 2006. godine re-kategoriziran kao patuljasti planet zbog svoje male veličine i " +
                    "neobičnog orbitiranja. Vrlo je hladan, s površinskim temperaturama od oko -230°C.",
            zanimljivosti = "Pluton ima pet mjeseca, od kojih je najpoznatiji Charon, gotovo polovica njegove veličine. Njegova orbita je vrlo eliptična i " +
                            "presijeca orbitu Neptuna.",
            velicina = "2,377 km",
            sastav = "Led, stijene"
        ),
        ObjektSuncevogSustava(
            ime = "Ceres",
            tip = "Patuljasti planet",
            smjestaj = "Asteroidni pojas između Marsa i Jupitera",
            opis = "Ceres je najveći objekt u asteroidnom pojasu i jedini patuljasti planet unutar ovog područja. Prvobitno je bio klasificiran kao asteroid, " +
                    "ali je 2006. re-kategoriziran kao patuljasti planet.",
            zanimljivosti = "Ceres sadrži velike količine leda, a postoje naznake da ispod površine možda postoji tekući vodeni ocean. Misija NASA-e Dawn " +
                            "otkrila je svjetleće mrlje u nekim kraterima.",
            velicina = "940 km",
            sastav = "Stijene, led"
        ),
        ObjektSuncevogSustava(
            ime = "Meteoroid",
            tip = "Mali kameniti ili metalni objekt",
            smjestaj = "Kruži Sunčevim sustavom",
            opis = "Meteoroidi su manji komadići svemirske prašine i kamenja veličine od nekoliko mikrometara do nekoliko metara. Najčešće potječu od kometa " +
                    "ili asteroida.",
            zanimljivosti = "Kada meteoroid uđe u Zemljinu atmosferu i izgori, postaje meteor ('zvijezda padalica').",
            velicina = "od mikrometara do nekoliko metara",
            sastav = "Stijene, metal"
        ),
        ObjektSuncevogSustava(
            ime = "Meteor",
            tip = "Svjetlosna pojava",
            smjestaj = "Zemljina atmosfera",
            opis = "Meteor je meteoroid koji ulazi u Zemljinu atmosferu i izgara zbog trenja, stvarajući svjetlosnu pojavu poznatu kao 'zvijezda padalica'.",
            zanimljivosti = "Većina meteora izgarava visoko u atmosferi, ali oni koji prežive postaju meteoriti.",
            velicina = "Varijabilna",
            sastav = "Stijene, metal"
        ),
        ObjektSuncevogSustava(
            ime = "Meteorit",
            tip = "Kameniti ili metalni objekt",
            smjestaj = "Površina Zemlje",
            opis = "Meteoriti su meteoroidi koji prežive prolazak kroz atmosferu i padnu na površinu Zemlje.",
            zanimljivosti = "Najveći poznati meteorit je Hoba u Namibiji, težak oko 60 tona.",
            velicina = "od grama do desetaka tona",
            sastav = "Stijene, metal"
        ),
        ObjektSuncevogSustava(
            ime = "Svemirska prašina",
            tip = "Mikroskopske čestice",
            smjestaj = "Cijeli Sunčev sustav",
            opis = "Svemirska prašina su sitne čestice koje plutaju kroz svemir. Mogu biti prašina, dim ili manji komadići leda i stijena.",
            zanimljivosti = "Svemirska prašina može pomoći u stvaranju novih zvijezda i planeta te sudjeluje u stvaranju meteora i prstenova planeta.",
            velicina = "Mikrometarska",
            sastav = "Prašina, led, stijene"
        )
    )

    fun getZvijezdja(): List<Zvijezdje> = listOf(
        Zvijezdje(
            imeHr = "Lovac",
            imeLat = "Orion",
            pozicija = "Orion je jedno od najpoznatijih i najsvjetlijih zviježđa na nebu, prepoznaje se po 'Orionovom pojasu', grupi od tri svijetle zvijezde " +
                       "koje formiraju središnji dio.",
            znacaj = "Poznat u mnogim kulturama, u starom Egiptu povezan s bogom Osirisom. Orionova maglica (M42) je jedno od najbližih područja stvaranja novih zvijezda.",
            svjetleZvijezde = "Betelgeuse, Rigel"
        ),
        Zvijezdje(
            imeHr = "Veliki Medvjed",
            imeLat = "Ursa Major",
            pozicija = "Jedno od najvećih i najprepoznatljivijih zviježđa na sjevernom nebu. Najpoznatiji dio je Velika Kolica, grupa sedam svijetlih zvijezda.",
            znacaj = "Važan za navigaciju jer je Polarna zvijezda (u zviježđu Ursa Minor) usmjerena prema Velikom Medvjedu.",
            svjetleZvijezde = "Dubhe, Merak"
        ),
        Zvijezdje(
            imeHr = "Maleni Medvjed",
            imeLat = "Ursa Minor",
            pozicija = "Smješten bliže sjevernom polu, najpoznatiji dio je Polarna zvijezda (Polaris).",
            znacaj = "Polarna zvijezda je bila ključna za navigaciju jer pokazuje pravi smjer prema sjeveru.",
            svjetleZvijezde = "Polaris"
        ),
        Zvijezdje(
            imeHr = "Kasiopeja",
            imeLat = "Cassiopeia",
            pozicija = "Jedno od najpoznatijih zviježđa u sjevernoj hemisferi, lako se prepoznaje po obliku slova 'W' ili 'M'.",
            znacaj = "Poznata u mitologiji kao kraljica Etiopije, povezano s njezinom pričom.",
            svjetleZvijezde = "Schedar, Caph, Ruchbah"
        ),
        Zvijezdje(
            imeHr = "Lav",
            imeLat = "Leo",
            pozicija = "Jedno od najsvjetlijih zviježđa na nebu, prepoznaje se po obliku lavljeg tijela.",
            znacaj = "Povezano s mnogim mitologijama, simbol moći i snage.",
            svjetleZvijezde = "Regulus, Algieba"
        ),
        Zvijezdje(
            imeHr = "Vaga",
            imeLat = "Libra",
            pozicija = "Zviježđe Zodijaka, smješteno između Škorpiona i Jarca.",
            znacaj = "Često povezana s pravdom, predstavlja vagu boginje Temide, simbol pravednosti.",
            svjetleZvijezde = "Zuben el-Akrab, Zuben el-Shamali"
        ),
        Zvijezdje(
            imeHr = "Škorpion",
            imeLat = "Scorpius",
            pozicija = "Vrlo prepoznatljivo zviježđe na jugoistočnom dijelu neba.",
            znacaj = "U mitologiji povezano s opasnostima i borbom protiv zla.",
            svjetleZvijezde = "Antares"
        ),
        Zvijezdje(
            imeHr = "Strijelac",
            imeLat = "Sagittarius",
            pozicija = "Zviježđe Zodijaka, prepoznaje se po obliku strijelca s lukom.",
            znacaj = "Povezan s lovom i osvajanjima, u mitologiji predstavljalo centaura.",
            svjetleZvijezde = "Kaus Australis"
        ),
        Zvijezdje(
            imeHr = "Kentaur",
            imeLat = "Centaurus",
            pozicija = "Jedno od najvećih zviježđa na južnoj hemisferi.",
            znacaj = "Sadrži dvije od najsvjetlijih zvijezda na nebu, važno u navigaciji na južnoj hemisferi.",
            svjetleZvijezde = "Alpha Centauri, Beta Centauri"
        ),
        Zvijezdje(
            imeHr = "Pješčani sat",
            imeLat = "Hourglass Nebula",
            pozicija = "Maglica u zviježđu Sagittariusa.",
            znacaj = "Jedno od najpoznatijih područja u svemiru zbog prepoznatljive oblika, simbol svemirskih promjena.",
            svjetleZvijezde = ""
        )
    )

    fun getKvizPitanjaOPlanetima(): List<KvizPitanje> = listOf(
        KvizPitanje(
            kategorija = "Planeti",
            pitanje = "Koji je najmanji planet u Sunčevom sustavu?",
            tocniOdgovor = "Merkur",
            netocniOdgovor1 = "Mars",
            netocniOdgovor2 = "Venera",
            netocniOdgovor3 = "Zemlja"
        ),
        KvizPitanje(
            kategorija = "Planeti",
            pitanje = "Koji planet ima najgušću atmosferu u Sunčevom sustavu?",
            tocniOdgovor = "Venera",
            netocniOdgovor1 = "Jupiter",
            netocniOdgovor2 = "Saturn",
            netocniOdgovor3 = "Mars"
        ),
        KvizPitanje(
            kategorija = "Planeti",
            pitanje = "Koji je jedini planet na kojem je potvrđeno postojanje života?",
            tocniOdgovor = "Zemlja",
            netocniOdgovor1 = "Mars",
            netocniOdgovor2 = "Venera",
            netocniOdgovor3 = "Europa"
        ),
        KvizPitanje(
            kategorija = "Planeti",
            pitanje = "Koji planet je poznat kao 'Crveni planet'?",
            tocniOdgovor = "Mars",
            netocniOdgovor1 = "Venera",
            netocniOdgovor2 = "Jupiter",
            netocniOdgovor3 = "Merkur"
        ),
        KvizPitanje(
            kategorija = "Planeti",
            pitanje = "Koji je najveći planet u Sunčevom sustavu?",
            tocniOdgovor = "Jupiter",
            netocniOdgovor1 = "Saturn",
            netocniOdgovor2 = "Neptun",
            netocniOdgovor3 = "Uran"
        ),
        KvizPitanje(
            kategorija = "Planeti",
            pitanje = "Koji planet je poznat po svojim spektakularnim prstenovima?",
            tocniOdgovor = "Saturn",
            netocniOdgovor1 = "Jupiter",
            netocniOdgovor2 = "Uran",
            netocniOdgovor3 = "Neptun"
        ),
        KvizPitanje(
            kategorija = "Planeti",
            pitanje = "Koji planet ima os rotacije koja je gotovo vodoravna u odnosu na svoju orbitu?",
            tocniOdgovor = "Uran",
            netocniOdgovor1 = "Saturn",
            netocniOdgovor2 = "Neptun",
            netocniOdgovor3 = "Jupiter"
        ),
        KvizPitanje(
            kategorija = "Planeti",
            pitanje = "Koji planet ima najjače vjetrove u Sunčevom sustavu?",
            tocniOdgovor = "Neptun",
            netocniOdgovor1 = "Jupiter",
            netocniOdgovor2 = "Saturn",
            netocniOdgovor3 = "Uran"
        ),
        KvizPitanje(
            kategorija = "Planeti",
            pitanje = "Koja je prosječna površinska temperatura na Veneri?",
            tocniOdgovor = "460°C",
            netocniOdgovor1 = "100°C",
            netocniOdgovor2 = "250°C",
            netocniOdgovor3 = "1000°C"
        ),
        KvizPitanje(
            kategorija = "Planeti",
            pitanje = "Koji planet ima najveći broj poznatih mjeseca?",
            tocniOdgovor = "Jupiter",
            netocniOdgovor1 = "Saturn",
            netocniOdgovor2 = "Uran",
            netocniOdgovor3 = "Neptun"
        )
    )

    fun getKvizPitanjaOSuncu(): List<KvizPitanje> = listOf(
        KvizPitanje(
            kategorija = "Sunce",
            pitanje = "Kolika je temperatura na površini Sunca?",
            tocniOdgovor = "5500°C",
            netocniOdgovor1 = "3000°C",
            netocniOdgovor2 = "8000°C",
            netocniOdgovor3 = "10000°C"
        ),
        KvizPitanje(
            kategorija = "Sunce",
            pitanje = "Kolika je temperatura u jezgri Sunca?",
            tocniOdgovor = "15,000,000°C",
            netocniOdgovor1 = "5,000,000°C",
            netocniOdgovor2 = "10,000,000°C",
            netocniOdgovor3 = "20,000,000°C"
        ),
        KvizPitanje(
            kategorija = "Sunce",
            pitanje = "Od čega se uglavnom sastoji Sunce?",
            tocniOdgovor = "Vodik i helij",
            netocniOdgovor1 = "Kisik i dušik",
            netocniOdgovor2 = "Ugljik i kisik",
            netocniOdgovor3 = "Helij i neon"
        ),
        KvizPitanje(
            kategorija = "Sunce",
            pitanje = "Koji proces omogućava Suncu da proizvodi energiju?",
            tocniOdgovor = "Nuklearna fuzija",
            netocniOdgovor1 = "Nuklearna fisija",
            netocniOdgovor2 = "Kemijska reakcija",
            netocniOdgovor3 = "Gravitacijska kompresija"
        ),
        KvizPitanje(
            kategorija = "Sunce",
            pitanje = "Koliko posto vodika sadržava Sunce?",
            tocniOdgovor = "74%",
            netocniOdgovor1 = "50%",
            netocniOdgovor2 = "85%",
            netocniOdgovor3 = "60%"
        ),
        KvizPitanje(
            kategorija = "Sunce",
            pitanje = "Koliko posto helija sadržava Sunce?",
            tocniOdgovor = "24%",
            netocniOdgovor1 = "15%",
            netocniOdgovor2 = "30%",
            netocniOdgovor3 = "40%"
        ),
        KvizPitanje(
            kategorija = "Sunce",
            pitanje = "Koliki je promjer Sunca?",
            tocniOdgovor = "1,391,000 km",
            netocniOdgovor1 = "696,000 km",
            netocniOdgovor2 = "2,000,000 km",
            netocniOdgovor3 = "500,000 km"
        ),
        KvizPitanje(
            kategorija = "Sunce",
            pitanje = "Koliko je puta Sunce veće od Zemlje?",
            tocniOdgovor = "109 puta",
            netocniOdgovor1 = "50 puta",
            netocniOdgovor2 = "200 puta",
            netocniOdgovor3 = "75 puta"
        ),
        KvizPitanje(
            kategorija = "Sunce",
            pitanje = "Koji je tip zvijezde Sunce?",
            tocniOdgovor = "G2V",
            netocniOdgovor1 = "M5V",
            netocniOdgovor2 = "K1V",
            netocniOdgovor3 = "F8V"
        ),
        KvizPitanje(
            kategorija = "Sunce",
            pitanje = "Koliko vremena treba svjetlosti da stigne od Sunca do Zemlje?",
            tocniOdgovor = "8 minuta",
            netocniOdgovor1 = "4 minute",
            netocniOdgovor2 = "15 minuta",
            netocniOdgovor3 = "1 sat"
        )
    )

    fun getKvizPitanjaOMjesecima(): List<KvizPitanje> = listOf(
        KvizPitanje(
            kategorija = "Mjeseci",
            pitanje = "Koji je najveći mjesec u Sunevom sustavu?",
            tocniOdgovor = "Ganimed",
            netocniOdgovor1 = "Titan",
            netocniOdgovor2 = "Mjesec",
            netocniOdgovor3 = "Europa"
        ),
        KvizPitanje(
            kategorija = "Mjeseci",
            pitanje = "Koji mjesec ima najgušću atmosferu u Sunevom sustavu?",
            tocniOdgovor = "Titan",
            netocniOdgovor1 = "Europa",
            netocniOdgovor2 = "Ganimed",
            netocniOdgovor3 = "Io"
        ),
        KvizPitanje(
            kategorija = "Mjeseci",
            pitanje = "Koji mjesec je poznat po svojoj vulkanskoj aktivnosti?",
            tocniOdgovor = "Io",
            netocniOdgovor1 = "Europa",
            netocniOdgovor2 = "Titan",
            netocniOdgovor3 = "Ganimed"
        ),
        KvizPitanje(
            kategorija = "Mjeseci",
            pitanje = "Koji mjesec ima retrogradnu orbitu?",
            tocniOdgovor = "Triton",
            netocniOdgovor1 = "Titan",
            netocniOdgovor2 = "Europa",
            netocniOdgovor3 = "Ganimed"
        ),
        KvizPitanje(
            kategorija = "Mjeseci",
            pitanje = "Koji mjesec ima vlastito magnetsko polje?",
            tocniOdgovor = "Ganimed",
            netocniOdgovor1 = "Europa",
            netocniOdgovor2 = "Titan",
            netocniOdgovor3 = "Io"
        ),
        KvizPitanje(
            kategorija = "Mjeseci",
            pitanje = "Na kojem mjesecu su ljudi prvi put hodali?",
            tocniOdgovor = "Mjesec",
            netocniOdgovor1 = "Mars",
            netocniOdgovor2 = "Europa",
            netocniOdgovor3 = "Titan"
        ),
        KvizPitanje(
            kategorija = "Mjeseci",
            pitanje = "Koji mjesec ima tekuće ugljikovodike na površini?",
            tocniOdgovor = "Titan",
            netocniOdgovor1 = "Europa",
            netocniOdgovor2 = "Ganimed",
            netocniOdgovor3 = "Io"
        ),
        KvizPitanje(
            kategorija = "Mjeseci",
            pitanje = "Koji mjesec se smatra najzanimljivijim za potragu za životom?",
            tocniOdgovor = "Europa",
            netocniOdgovor1 = "Titan",
            netocniOdgovor2 = "Ganimed",
            netocniOdgovor3 = "Io"
        ),
        KvizPitanje(
            kategorija = "Mjeseci",
            pitanje = "Koliko mjeseca ima Mars?",
            tocniOdgovor = "2",
            netocniOdgovor1 = "1",
            netocniOdgovor2 = "3",
            netocniOdgovor3 = "4"
        ),
        KvizPitanje(
            kategorija = "Mjeseci",
            pitanje = "Koji planet ima najveći broj mjeseca?",
            tocniOdgovor = "Jupiter",
            netocniOdgovor1 = "Saturn",
            netocniOdgovor2 = "Uran",
            netocniOdgovor3 = "Neptun"
        )
    )

    fun getKvizPitanjaOAsteroidima(): List<KvizPitanje> = listOf(
        KvizPitanje(
            kategorija = "Asteroidi",
            pitanje = "Koji je najveći asteroid u asteroidnom pojasu?",
            tocniOdgovor = "Ceres",
            netocniOdgovor1 = "Vesta",
            netocniOdgovor2 = "Pallas",
            netocniOdgovor3 = "Eros"
        ),
        KvizPitanje(
            kategorija = "Asteroidi",
            pitanje = "Gdje se nalazi asteroidni pojas?",
            tocniOdgovor = "Između Marsa i Jupitera",
            netocniOdgovor1 = "Između Zemlje i Marsa",
            netocniOdgovor2 = "Između Jupitera i Saturna",
            netocniOdgovor3 = "Između Venere i Zemlje"
        ),
        KvizPitanje(
            kategorija = "Asteroidi",
            pitanje = "Koji asteroid je bio prva destinacija misije Dawn?",
            tocniOdgovor = "Vesta",
            netocniOdgovor1 = "Ceres",
            netocniOdgovor2 = "Eros",
            netocniOdgovor3 = "Itokawa"
        ),
        KvizPitanje(
            kategorija = "Asteroidi",
            pitanje = "Koji asteroid je poznat po svojoj duguljastoj formi?",
            tocniOdgovor = "Itokawa",
            netocniOdgovor1 = "Vesta",
            netocniOdgovor2 = "Ceres",
            netocniOdgovor3 = "Pallas"
        ),
        KvizPitanje(
            kategorija = "Asteroidi",
            pitanje = "Kada je otkriven prvi asteroid Ceres?",
            tocniOdgovor = "1801. godine",
            netocniOdgovor1 = "1802. godine",
            netocniOdgovor2 = "1898. godine",
            netocniOdgovor3 = "1900. godine"
        ),
        KvizPitanje(
            kategorija = "Asteroidi",
            pitanje = "Koji asteroid je bio cilj misije NEAR Shoemaker?",
            tocniOdgovor = "Eros",
            netocniOdgovor1 = "Vesta",
            netocniOdgovor2 = "Ceres",
            netocniOdgovor3 = "Itokawa"
        ),
        KvizPitanje(
            kategorija = "Asteroidi",
            pitanje = "Koja je japanska misija uspješno vratila uzorke s asteroida?",
            tocniOdgovor = "Hayabusa",
            netocniOdgovor1 = "Dawn",
            netocniOdgovor2 = "NEAR Shoemaker",
            netocniOdgovor3 = "Deep Impact"
        ),
        KvizPitanje(
            kategorija = "Asteroidi",
            pitanje = "Koji asteroid je treći po veličini u Sunevom sustavu?",
            tocniOdgovor = "Pallas",
            netocniOdgovor1 = "Vesta",
            netocniOdgovor2 = "Eros",
            netocniOdgovor3 = "Itokawa"
        ),
        KvizPitanje(
            kategorija = "Asteroidi",
            pitanje = "Od čega se uglavnom sastoje asteroidi?",
            tocniOdgovor = "Stijene i metali",
            netocniOdgovor1 = "Led i plin",
            netocniOdgovor2 = "Samo metal",
            netocniOdgovor3 = "Samo led"
        ),
        KvizPitanje(
            kategorija = "Asteroidi",
            pitanje = "Koji status ima Ceres od 2006. godine?",
            tocniOdgovor = "Patuljasti planet",
            netocniOdgovor1 = "Asteroid",
            netocniOdgovor2 = "Planet",
            netocniOdgovor3 = "Komet"
        )
    )

    fun getKvizPitanjaOKometima(): List<KvizPitanje> = listOf(
        KvizPitanje(
            kategorija = "Kometi",
            pitanje = "Koji je najpoznatiji komet koji je vidljiv s Zemlje svakih 76 godina?",
            tocniOdgovor = "Halleyjev komet",
            netocniOdgovor1 = "Hale-Bopp",
            netocniOdgovor2 = "Neowise",
            netocniOdgovor3 = "Enckeov komet"
        ),
        KvizPitanje(
            kategorija = "Kometi",
            pitanje = "Koji komet ima najkraći orbitalni period od samo 3 godine?",
            tocniOdgovor = "Enckeov komet",
            netocniOdgovor1 = "Halleyjev komet",
            netocniOdgovor2 = "Tempel 1",
            netocniOdgovor3 = "Neowise"
        ),
        KvizPitanje(
            kategorija = "Kometi",
            pitanje = "Koji komet je bio vrlo vidljiv tijekom 2020. godine?",
            tocniOdgovor = "Neowise",
            netocniOdgovor1 = "Hale-Bopp",
            netocniOdgovor2 = "Halleyjev komet",
            netocniOdgovor3 = "Enckeov komet"
        ),
        KvizPitanje(
            kategorija = "Kometi",
            pitanje = "Koji komet je bio cilj NASA-ine misije Deep Impact 2005. godine?",
            tocniOdgovor = "Tempel 1",
            netocniOdgovor1 = "Halleyjev komet",
            netocniOdgovor2 = "Hale-Bopp",
            netocniOdgovor3 = "Neowise"
        ),
        KvizPitanje(
            kategorija = "Kometi",
            pitanje = "Koji komet je bio jedan od najsvjetlijih ikada zabilježenih i prošao kroz Sunčev sustav 1997. godine?",
            tocniOdgovor = "Hale-Bopp",
            netocniOdgovor1 = "Halleyjev komet",
            netocniOdgovor2 = "Neowise",
            netocniOdgovor3 = "Enckeov komet"
        ),
        KvizPitanje(
            kategorija = "Kometi",
            pitanje = "Koliki je orbitalni period Halleyjevog kometa?",
            tocniOdgovor = "76 godina",
            netocniOdgovor1 = "50 godina",
            netocniOdgovor2 = "100 godina",
            netocniOdgovor3 = "25 godina"
        ),
        KvizPitanje(
            kategorija = "Kometi",
            pitanje = "Kada je Halleyjev komet zadnji put bio u perihelu?",
            tocniOdgovor = "1986. godine",
            netocniOdgovor1 = "1990. godine",
            netocniOdgovor2 = "1980. godine",
            netocniOdgovor3 = "1995. godine"
        ),
        KvizPitanje(
            kategorija = "Kometi",
            pitanje = "Kada će Halleyjev komet sljedeći put biti u perihelu?",
            tocniOdgovor = "2061. godine",
            netocniOdgovor1 = "2055. godine",
            netocniOdgovor2 = "2070. godine",
            netocniOdgovor3 = "2050. godine"
        ),
        KvizPitanje(
            kategorija = "Kometi",
            pitanje = "Koji komet ima najveću jezgru od navedenih?",
            tocniOdgovor = "Hale-Bopp",
            netocniOdgovor1 = "Halleyjev komet",
            netocniOdgovor2 = "Neowise",
            netocniOdgovor3 = "Enckeov komet"
        ),
        KvizPitanje(
            kategorija = "Kometi",
            pitanje = "Koliki je orbitalni period kometa Hale-Bopp?",
            tocniOdgovor = "2533 godine",
            netocniOdgovor1 = "1000 godina",
            netocniOdgovor2 = "5000 godina",
            netocniOdgovor3 = "500 godina"
        )
    )

    fun getKvizPitanjaOObjektima(): List<KvizPitanje> = listOf(
        KvizPitanje(
            kategorija = "Objekti",
            pitanje = "Koji je status Plutona od 2006. godine?",
            tocniOdgovor = "Patuljasti planet",
            netocniOdgovor1 = "Planet",
            netocniOdgovor2 = "Asteroid",
            netocniOdgovor3 = "Komet"
        ),
        KvizPitanje(
            kategorija = "Objekti",
            pitanje = "Gdje se nalazi Pluton?",
            tocniOdgovor = "Kuiperov pojas",
            netocniOdgovor1 = "Asteroidni pojas",
            netocniOdgovor2 = "Između Marsa i Jupitera",
            netocniOdgovor3 = "Između Zemlje i Marsa"
        ),
        KvizPitanje(
            kategorija = "Objekti",
            pitanje = "Koji je najveći mjesec Plutona?",
            tocniOdgovor = "Charon",
            netocniOdgovor1 = "Titan",
            netocniOdgovor2 = "Europa",
            netocniOdgovor3 = "Ganimed"
        ),
        KvizPitanje(
            kategorija = "Objekti",
            pitanje = "Koliko mjeseca ima Pluton?",
            tocniOdgovor = "5",
            netocniOdgovor1 = "3",
            netocniOdgovor2 = "7",
            netocniOdgovor3 = "1"
        ),
        KvizPitanje(
            kategorija = "Objekti",
            pitanje = "Kolika je približna temperatura na Plutonu?",
            tocniOdgovor = "-230°C",
            netocniOdgovor1 = "-100°C",
            netocniOdgovor2 = "-180°C",
            netocniOdgovor3 = "-300°C"
        ),
        KvizPitanje(
            kategorija = "Objekti",
            pitanje = "Što se događa kada meteoroid uđe u Zemljinu atmosferu?",
            tocniOdgovor = "Postaje meteor",
            netocniOdgovor1 = "Postaje meteorit",
            netocniOdgovor2 = "Nestaje",
            netocniOdgovor3 = "Postaje asteroid"
        ),
        KvizPitanje(
            kategorija = "Objekti",
            pitanje = "Kako se naziva meteoroid koji padne na Zemljinu površinu?",
            tocniOdgovor = "Meteorit",
            netocniOdgovor1 = "Meteor",
            netocniOdgovor2 = "Asteroid",
            netocniOdgovor3 = "Komet"
        ),
        KvizPitanje(
            kategorija = "Objekti",
            pitanje = "Koji je najveći poznati meteorit?",
            tocniOdgovor = "Hoba",
            netocniOdgovor1 = "Tunguska",
            netocniOdgovor2 = "Chelyabinsk",
            netocniOdgovor3 = "Barringer"
        ),
        KvizPitanje(
            kategorija = "Objekti",
            pitanje = "Koliko teži najveći poznati meteorit Hoba?",
            tocniOdgovor = "60 tona",
            netocniOdgovor1 = "30 tona",
            netocniOdgovor2 = "100 tona",
            netocniOdgovor3 = "15 tona"
        ),
        KvizPitanje(
            kategorija = "Objekti",
            pitanje = "Od čega se uglavnom sastoje meteoroidi?",
            tocniOdgovor = "Stijene i metal",
            netocniOdgovor1 = "Samo led",
            netocniOdgovor2 = "Samo plin",
            netocniOdgovor3 = "Samo prašina"
        )
    )

    fun getKvizPitanjaOZvijezdjima(): List<KvizPitanje> = listOf(
        KvizPitanje(
            kategorija = "Zviježđa",
            pitanje = "Koje zviježđe je poznato po Orionovom pojasu?",
            tocniOdgovor = "Orion",
            netocniOdgovor1 = "Ursa Major",
            netocniOdgovor2 = "Cassiopeia",
            netocniOdgovor3 = "Leo"
        ),
        KvizPitanje(
            kategorija = "Zviježđa",
            pitanje = "Koje zviježđe sadrži Polarnu zvijezdu?",
            tocniOdgovor = "Ursa Minor",
            netocniOdgovor1 = "Ursa Major",
            netocniOdgovor2 = "Orion",
            netocniOdgovor3 = "Cassiopeia"
        ),
        KvizPitanje(
            kategorija = "Zviježđa",
            pitanje = "Koje zviježđe se prepoznaje po obliku slova W ili M?",
            tocniOdgovor = "Cassiopeia",
            netocniOdgovor1 = "Orion",
            netocniOdgovor2 = "Leo",
            netocniOdgovor3 = "Libra"
        ),
        KvizPitanje(
            kategorija = "Zviježđa",
            pitanje = "Koje zviježđe je poznato kao Veliki Medvjed?",
            tocniOdgovor = "Ursa Major",
            netocniOdgovor1 = "Ursa Minor",
            netocniOdgovor2 = "Leo",
            netocniOdgovor3 = "Scorpius"
        ),
        KvizPitanje(
            kategorija = "Zviježđa",
            pitanje = "Koje zviježđe se prepoznaje po obliku lavljeg tijela?",
            tocniOdgovor = "Leo",
            netocniOdgovor1 = "Orion",
            netocniOdgovor2 = "Sagittarius",
            netocniOdgovor3 = "Centaurus"
        ),
        KvizPitanje(
            kategorija = "Zviježđa",
            pitanje = "Koje zviježđe predstavlja vagu boginje Temide?",
            tocniOdgovor = "Libra",
            netocniOdgovor1 = "Leo",
            netocniOdgovor2 = "Scorpius",
            netocniOdgovor3 = "Sagittarius"
        ),
        KvizPitanje(
            kategorija = "Zviježđa",
            pitanje = "Koje zviježđe sadrži zvijezdu Antares?",
            tocniOdgovor = "Scorpius",
            netocniOdgovor1 = "Leo",
            netocniOdgovor2 = "Sagittarius",
            netocniOdgovor3 = "Centaurus"
        ),
        KvizPitanje(
            kategorija = "Zviježđa",
            pitanje = "Koje zviježđe se prepoznaje po obliku strijelca s lukom?",
            tocniOdgovor = "Sagittarius",
            netocniOdgovor1 = "Orion",
            netocniOdgovor2 = "Centaurus",
            netocniOdgovor3 = "Scorpius"
        ),
        KvizPitanje(
            kategorija = "Zviježđa",
            pitanje = "Koje zviježđe sadrži Alpha Centauri i Beta Centauri?",
            tocniOdgovor = "Centaurus",
            netocniOdgovor1 = "Sagittarius",
            netocniOdgovor2 = "Scorpius",
            netocniOdgovor3 = "Libra"
        ),
        KvizPitanje(
            kategorija = "Zviježđa",
            pitanje = "Koje zviježđe sadrži Orionovu maglicu M42?",
            tocniOdgovor = "Orion",
            netocniOdgovor1 = "Ursa Major",
            netocniOdgovor2 = "Leo",
            netocniOdgovor3 = "Cassiopeia"
        )
    )
}
