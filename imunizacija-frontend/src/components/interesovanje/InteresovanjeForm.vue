<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="8" md="12">
        <v-form ref="form" v-model="valid">
          <v-row>
            <v-select
              :items="drzavljanstvo_items"
              label="Državljanstvo"
            ></v-select>
          </v-row>
          <v-row>
            <v-text-field
              v-model="jmbg"
              label="JMBG"
              :counter="13"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="ime" label="Ime"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="prezime" label="Prezime"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="email"
              label="Adresa elektronske pošte"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="broj_mobilnog"
              label="Broj mobilnog telefona (navesti broj u formatu 06X..... bez razmaka i crtica)"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="broj_fiksnog"
              label="Broj fiksnog telefona (navesti broj u formatu npr. 011..... bez razmaka i crtica)"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="odabrana_lokacija_primanja_vakcine"
              label="Odaberite lokaciju gde želite da primite vakcinu (unesite opštinu)"
            ></v-text-field>
          </v-row>
          <v-row>
            <p class="text-justify">
              Iskazujem interesovanje da primim isključivo vakcinu sledećih
              proizvođača za koji Agencija za lekove i medicinska sredstva
              potvrdi bezbednost, efikasnost i kvalitet i izda dozvolu za
              upotrebu leka
            </p>
            <v-select
              v-model="odabrani_proizvodjaci"
              :items="proizvodjaci_items"
              attach
              chips
              label="Odaberite proizvodjače"
              multiple
            ></v-select>
          </v-row>
          <v-row>
            <v-checkbox
              v-model="davalac_krvi"
              label="Da li ste dobrovoljni davalac krvi?"
            ></v-checkbox>
          </v-row>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    drzavljanstvo_items: [
      "Državljanin Republike Srbije",
      "Strani državljanin sa boravkom u RS",
      "Strani državljanin bez boravka u RS",
    ],
    proizvodjaci_items: [
      "Pfizer-BioNTech",
      "Sputnik V (Gamaleya istraživački centar)",
      "Sinopharm",
      "AstraZeneca",
      "Moderna",
      "Bilo koja",
    ],
    davalac_krvi: false,
  }),
};
</script>
