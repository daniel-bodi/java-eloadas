# Forex Dashboard Webalkalmazás

**Készítette:** Bódi Dániel (GFEKAK)

**Tantárgy:** JAVA Alkalmazások

**Előadás beadandó**

Ez a projekt egy **webalapú Forex kezelő alkalmazás**, amely lehetővé teszi a felhasználók számára, hogy az OANDA API segítségével valós idejű és historikus devizaárfolyamokat lekérdezzenek, pozíciókat nyissanak és zárjanak, valamint az account adataikat megtekintsék.

## Funkciók

1. **Főoldal**
    - Bemutatja az alkalmazást egy látványos, reszponzív weboldalon.

2. **SOAP lekérés az MNB-től**
    - Kiválasztható deviza (EUR, USD, GBP, JPY) és dátum intervallum.
    - A lekért árfolyamok megjelenítése táblázatban és grafikonon (Chart.js).

3. **Account információk**
    - A felhasználó OANDA számlájának részletes adatai, mint pl. balance, margin, nyitott pozíciók száma.

4. **Forex Aktuális ár**
    - Lenyíló listából kiválasztható instrumentum.
    - Megjeleníti a kiválasztott instrumentum aktuális bid és ask árát.

5. **Forex Historikus ár (HistÁr)**
    - Kiválasztható instrumentum és granularity (pl. D = daily).
    - Lekérdezi és táblázatban jeleníti meg az utolsó 10 historikus árat.
    - Igény esetén grafikonos megjelenítés Chart.js segítségével.

6. **Forex Pozíció nyitás (Nyit)**
    - Lenyíló listából kiválasztható instrumentum és numerikus mező a mennyiséghez.
    - Pozitív érték: Long, negatív: Short.
    - Piaci áron nyitja meg a pozíciót.

7. **Nyitott pozíciók (Poz)**
    - Táblázatban listázza a felhasználó összes nyitott pozícióját, beleértve a long/short egységeket, átlagárakat és unrealized P/L-t.

8. **Pozíció zárás (Zár)**
    - Numerikus mezőben megadható a `tradeId`.
    - Piaci áron lezárja a pozíciót és visszajelzést ad a felhasználónak.

## Használt technológiák

- **Backend:** Spring Boot (Java)
- **Frontend:** Thymeleaf + Bootstrap 5
- **Charting:** Chart.js (historikus árak vizualizálásához)
- **API-k:**
    - OANDA REST API (Forex adatok, pozíciók kezelése)
    - Magyar Nemzeti Bank SOAP Webszolgáltatás (árfolyam adatok)

## Követelmények

- Java 21+
- Maven 3+
- Internetkapcsolat az OANDA API és MNB SOAP szolgáltatás eléréséhez
- OANDA gyakorló számla (`practice account`) token és account ID

## Használat

1. Töltsd le a projektet, és nyisd meg IDE-ben (pl. IntelliJ IDEA).
2. Állítsd be az `application.properties`-ben az API url-t, a tokened és az account ID-d
3. Futtasd a Spring Boot alkalmazást (`mvn spring-boot:run` vagy IDE-ből).
4. Nyisd meg a böngészőben: `http://localhost:8080`.

## Megjegyzés

Ez az alkalmazás **demo célokra** készült, gyakorló OANDA számlával tesztelve. Valós pénzes használathoz biztonsági és hitelesítési intézkedéseket kell bevezetni.
