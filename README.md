# xml-veb-servisi

Predmetni projekat iz predmeta XML i Veb servisi.

## Pokretanje projekta

Spring servisi se za sada pokreću ručno. U docker-compose.yml fajlu svakog servisa je zakomentarisan deo koji pokreće spring projekat. Ovako je lakše zbog debagovanja, kasnije se samo otkomentariše taj deo i sve će raditi. Projekti su konfigurisani tako da se i baze mogu ručno pokretati.

### Opcija 1

- Iz sluzbenici foldera pokrenuti `docker-compose up` (samo prvi put)
- Iz imunizacija foldera pokrenuti `docker-compose up` (samo prvi put)
- Svaki sledeci put moguće je pokrenuti kontejner iz Docker Desktop-a:
  ![image](https://user-images.githubusercontent.com/27950949/148374518-eea2ded7-edc4-4295-904a-d743e0c3b45b.png)

### Opcija 2

- Iz korenskog foldera pokrenuti `docker-compose -f .\imunizacija\docker-compose.yml -f .\sluzbenici\docker-compose.yml up` (samo prvi put)
- Svaki sledeci put moguće je pokrenuti kontejner iz Docker Desktop-a:
  ![image](https://user-images.githubusercontent.com/27950949/148374375-889d7997-81cc-4989-a588-cc8fcc865071.png)
