# xml-veb-servisi

Predmetni projekat iz predmeta XML i Veb servisi.

## Pokretanje projekta

Spring servisi se za sada pokreću ručno. U docker-compose.yml fajlu svakog servisa je zakomentarisan deo koji pokreće spring projekat. Ovako je lakše zbog debagovanja, kasnije se samo otkomentariše taj deo i sve će raditi. Projekti su konfigurisani tako da se i baze mogu ručno pokretati.

### Opcija 1

 * Iz sluzbenici foldera pokrenuti `docker-compose up` (samo prvi put)
 * Iz imunizacija foldera pokrenuti `docker-compose up` (samo prvi put)
 * Svaki sledeci put moguće je pokrenuti kontejner iz Docker Desktop-a:
 ![image](https://user-images.githubusercontent.com/27950949/148374518-eea2ded7-edc4-4295-904a-d743e0c3b45b.png)
 * Ili iz terminala, na primer: `docker {start | stop} sluzbenici-xml-exist-database`

### Opcija 2

 * Iz korenskog foldera pokrenuti `docker-compose -f .\imunizacija\docker-compose.yml -f .\sluzbenici\docker-compose.yml up` (samo prvi put)
 * Svaki sledeci put moguće je pokrenuti kontejner iz Docker Desktop-a:
 ![image](https://user-images.githubusercontent.com/27950949/148374375-889d7997-81cc-4989-a588-cc8fcc865071.png)
 * Ili iz terminala, na primer: `docker {start | stop} sluzbenici-xml-exist-database`
  
  
## Zajednički projekat

Dodat je projekat zajednicko, u kojem se nalazi korenska ExistRepository klasa, kao i klase modela koje se ponavljaju u dokumentima. Njegova zavisnost se nalazi u pom.xml fajlovima u projektima sluzbenici i imunizacija.

Prilikom ažuriranja ovog projekta, potrebno je ažurirati njegovu Maven instalaciju. To se može odraditi na 2 načina.

### Iz terminala

Pozicionirati se u folder zajednicko i pokrenuti komandu `mvn install`.

### Iz IntelliJIDEA

Ako se koristi ovaj IDE, instalacija se može ažurirati i na sledeći način:
![image](https://user-images.githubusercontent.com/53261053/148428756-72a2a7fd-f3e4-4dfd-949e-b3f2c7355f4f.png)

Izabrati sa strane jezičak Maven, u njemu odabrati meni Lifecycle, odabrati install i pokrenuti ga na zeleno dugme Run Maven Build.

