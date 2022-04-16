# **OMO Smart Home**
**Vytvořil:**
<br>
Jan Bajer & Benjamin Rodr
<br>

# **Popis**
<br>
Naše semestrální práce je na téma Smart Home.
Jednotlivé iterace probíhají po 10 minutách kdy se ve třídě MainEventHandler zkontroluje, zda každý člověk/zvíře něco dělá. Jestliže je nalezena entita, která nemá nic na práci, přířadí se jí náhodná aktivita. Tuto aktivitu ovlivňuje i část dne. Jestliže je noc, entita půjde spát. Jestliže je ráno, člověk půjde do práce(pokud je dospělý).
Každé zařízení spotřebovává prostředky rodiny - vodu, elektřinu a používáním se opotřebovává. S opotřebením se zvyšuje pravděpodobnost rozbití zařízení. Poté si člověk náhodně vybere zda zařízení opraví sám, odnese do servisu nebo koupí nové. Na konci simulace se vytvoří reporty o konfiguraci domu, utracených penězích za energie, použitých zařízeních a eventech které proběhly.



# **Funkční požadavky**

- **F1**: Splněno, chybí nám auto
- **F2**: Splněno, jednotlivá zařízaní mají své API
- **F3**: Splněno, spotřebiče mají svojí spotřebu v aktivním stavu, idle stavu, vypnutém stavu
- **F4**: Splněno, jednotlivá zařízení mají API na sběr dat o tomto zařízení. O zařízeních sbíráme data jako spotřeba elektřiny, vody a funkčnost (klesá lineárně s časem)
- **F5**: Splněno, jednotlivé osoby a zvířata mohou provádět aktivity(akce), které mají nějaký efekt na zařízení nebo jinou osobu. 
- **F6**: Splněno, jednotlivá zařízení a osoby se v každém okamžiku vyskytují v jedné místnosti (pokud nesportují) a náhodně generují eventy (eventem může být důležitá informace a nebo alert)
- **F7**: Splněno, eventy jsou přebírány a odbavovány vhodnou osobou (osobami) nebo zařízením (zařízeními)
- **F8**: Splněno, vygenerování reportů
- **F9**: Splněno, při rozbití zařízení se vhodný obyvatel domu, pokusí vyřešit problém s rozbitým spotřebičem. Buď ho opraví / koupí nový spotřebič / odnese do servisu kde to někdo opraví.
- **F10**: Splněno, F10.	Rodina je aktivní a volný čas tráví zhruba v poměru (50% používání spotřebičů v domě a 50% sport kdy používá sportovní náčiní kolo nebo lyže). Lidé chodí dle denní doby do práce, mají volno a "něco" dělají a poté sportují.
Chybí -  Když není volné zařízení nebo sportovní náčiní, tak osoba čeká. Místo toho jde dělat něco jiného (Zdál se nám temnto scénář reálnější).

# **Nefunkční požadavky**

- NF1: **Splněno** - Není požadována autentizace ani autorizace
- NF2: **Splněno** - Aplikace může běžet pouze v jedné JVM
- NF3: **Splněno** - Aplikaci pište tak, aby byly dobře schované metody a proměnné, které nemají být dostupné ostatním třídám. Vygenerovný javadoc by měl mít co nejméně public metod a proměnných.
- NF4: **Splněno** - Reporty jsou generovány do textového souboru 
- NF5: **Splněno** - (Třída DataLoader - 2 konfigurace domu)	Konfigurace domu, zařízení a obyvatel domu může být nahrávána přímo z třídy nebo externího souboru (preferován je json)

# **Použité design patterny**
V aplikace se používají následující design patterny:
- visitor - Generování dokumenů
- Singleton - ID, MainEventHandler, DeviceFactory
- StateMachine - stavy spotřebičů - package cz.cvut.fel.omo.smarthome.devices
- Factory - třída DeviceFactory
- observer - Vykonání eventů v Person, Pet, House
- Composite - House - floor - room - window - blinds
- Strategy - PartOfDay - eventy, část dne rozhoduje o aktivitě.
- Builder - Slouží pro vytvoření domu

# **Druhy spotřebičů**
V aplikace se používají následující spotřebiče:
- Air_Conditioner
- CD_Player
- Fridge
- Microwave
- Televisoin
- Washing_Machine
- Thermometer
- Oven
- Light

# **Druhy eventů**
V aplikace se používají následující eventy:
- DIYRepairEvent
- PersonMoveEvent
- PetMoveEvent
- PetSleepEvent
- WashingMachineEvent
- FridgeEvent
- HouseBoomEvent
- BuyEvent
- ServiceRepair
- ACEvent
- WatchTV
- ListenToMusic
- LightTurnOn
- LightTurnOff
- GoSkiingEvent
- GoBikingEvent
- GoRunningEvent
- OvenEvent
- MicrowaveEvent
- CloseBlindsEvent
- SleepEvent
- WorkEvent
- OpenBlindsEvent
