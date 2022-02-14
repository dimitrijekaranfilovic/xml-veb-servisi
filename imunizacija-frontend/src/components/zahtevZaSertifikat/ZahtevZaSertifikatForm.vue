<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="8" md="12">
        <v-form ref="form" v-model="valid">
          <p class="text-justify">
            U skladu sa odredbom Republike Srbije o izdavanju digitalnog zelenog
            sertifikata kao potvrde o izvršenoj vakcinaciji protiv COVID-19,
            rezultatima testiranja na zaraznu bolest SARS-CoV-2 ili oporavku od
            bolesti COVID-19, podnosim zahtev za izdavanje digitalnog zelenog
            sertifikata.
          </p>
          <p class="text-justify">Podnosilac zahteva:</p>
          <v-row>
            <v-text-field v-model="ime" label="Ime"></v-text-field>
            <v-text-field v-model="prezime" label="Prezime"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="datum_rodjenja"
              label="Datum rođenja"
              single-line
            >
              <template v-slot:append-outer>
                <date-picker v-model="datumRodjenja" />
              </template>
            </v-text-field>
          </v-row>
          <v-row>
            <v-select v-model="pol" :items="pol_items" label="Pol"></v-select>
          </v-row>
          <v-row>
            <v-text-field
              v-model="jmbg"
              label="Jedinstveni matični broj građanina"
              :counter="13"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="broj_pasosa"
              label="Broj pasoša"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="razlog"
              label="Razlog za podnošenje zahteva"
            ></v-text-field>
          </v-row>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import DatePicker from "../shared/DatePicker.vue";
export default {
  components: { DatePicker },
  data: () => ({
    pol_items: ["MUSKI", "ZENSKI"],
  }),
};
</script>

<style></style>
