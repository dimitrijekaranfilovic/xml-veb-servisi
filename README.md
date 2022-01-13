# xml-veb-servisi

Predmetni projekat iz predmeta XML i Veb servisi.

## Pokretanje projekta

Spring servisi se za sada pokreću ručno. U docker-compose.yml fajlu svakog servisa je zakomentarisan deo koji pokreće spring projekat. Ovako je lakše zbog debagovanja, kasnije se samo otkomentariše taj deo i sve će raditi. Projekti su konfigurisani tako da se i baze mogu ručno pokretati. Kredencijali za Fuseki su username=`admin` i password=`password`. Kredencijali za RabbitMQ su username=`guest` i password=`guest`.

### Pokretanje RabbitMQ Message Broker-a

 * Iz message-broker foldera pokrenuti `docker-compose up` (samo prvi put).
 * Svaki sledeći put pokrenuti `docker start rabbitmq-message-broker`.

### Pokretanje E-Mail servisa

 * Iz email-service foldera pokrenuti `mvn install` (samo prvi put).
 * Iz email-service foldera pokrenuti `docker-compose up` (samo prvi put).
 * Svaki sledeći put pokrenuti `docker start email-service-api`.

### Pokretanje infrastrukture za Imunizacija servis

 * Iz imunizacija foldera pokrenuti `docker-compose up` (samo prvi put).
 * Svaki sledeći put pokrenuti `docker start imunizacija-rdf-database` i `docker start imunizacija-xml-exist-database`.

### Pokretanje infrastrukture za Sluzbenici servis

 * Iz sluzbenici foldera pokrenuti `docker-compose up` (samo prvi put).
 * Svaki sledeći put pokrenuti `docker start sluzbenici-rdf-database` i `docker start sluzbenici-xml-exist-database`.
  
## Zajednički projekat

Dodat je projekat zajednicko, u kojem se nalazi korenska ExistRepository klasa, kao i klase modela koje se ponavljaju u dokumentima. Njegova zavisnost se nalazi u pom.xml fajlovima u projektima sluzbenici i imunizacija.

Prilikom ažuriranja ovog projekta, potrebno je ažurirati njegovu Maven instalaciju. To se može odraditi na 2 načina.

### Iz terminala

Pozicionirati se u folder zajednicko i pokrenuti komandu `mvn install`.

### Iz IntelliJIDEA

Ako se koristi ovaj IDE, instalacija se može ažurirati i na sledeći način:
![image](https://user-images.githubusercontent.com/53261053/148428756-72a2a7fd-f3e4-4dfd-949e-b3f2c7355f4f.png)

Izabrati sa strane jezičak Maven, u njemu odabrati meni Lifecycle, odabrati install i pokrenuti ga na zeleno dugme Run Maven Build.

