<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="8" md="12">
        <v-form ref="form" v-model="valid">
          <p class="text-justify">
            У складу са одредбом Републике Србије о издавању дигиталног зеленог
            сертификата као потврде о извршеној вакцинацији против COVID-19,
            резултатима тестирања на заразну болест SARS-CoV-2 или опоравку од
            болести COVID-19, подносим захтев за издавање дигиталног зеленог
            сертификата.
          </p>
          <p class="text-justify">Подносилац захтева:</p>
          <v-row>
            <v-text-field v-model="ime" label="Име"></v-text-field>
            <v-text-field v-model="prezime" label="Презиме"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="datum_rodjenja"
              label="Датум рођења"
              single-line
            >
              <template v-slot:append-outer>
                <date-picker v-model="datumRodjenja" />
              </template>
            </v-text-field>
          </v-row>
          <v-row>
            <v-select
              v-model="pol"
              :items="pol_items"
              item-text="text"
              item-value="value"
              label="Пол"
            ></v-select>
          </v-row>
          <v-row>
            <v-text-field
              v-model="jmbg"
              label="Јединствени матични број грађанина"
              :counter="13"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="broj_pasosa"
              label="Број пасоша"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="razlog"
              label="Разлог за подношење захтева"
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
    pol_items: [
      { value: "MUSKI", text: "Мушки" },
      { value: "ZENSKI", text: "Женски" },
    ],
  }),
};
</script>

<style></style>
