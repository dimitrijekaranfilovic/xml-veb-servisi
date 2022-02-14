<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="8" md="12">
        <v-form ref="form" v-model="valid">
          <v-row>
            <v-select
              :items="drzavljanstvo_items"
              label="Држављанство"
            ></v-select>
          </v-row>
          <v-row>
            <v-text-field
              v-model="jmbg"
              label="ЈМБГ"
              :counter="13"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="ime" label="Име"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="prezime" label="Презиме"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="email"
              label="Адреса електронске поште"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="broj_mobilnog"
              label="Број мобилног телефона (навести број у формату 06Х..... без размака и цртица)"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="broj_fiksnog"
              label="Број фиксног телефона (навести број у формату нпр. 011..... без размака и цртица)"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="odabrana_lokacija_primanja_vakcine"
              label="Одаберите локацију где желите да примите вакцину (унесите општину)"
            ></v-text-field>
          </v-row>
          <v-row>
            <p class="text-justify">
              Исказујем интересовање да примим искључиво вакцину следећих
              произвођача за који Агенција за лекове и медицинска средства
              потврди безбедност, ефикасност и квалитет и изда дозволу за
              употребу лека.
            </p>
            <v-select
              v-model="odabrani_proizvodjaci"
              :items="proizvodjaci_items"
              attach
              chips
              label="Одаберите произвођаче"
              multiple
            ></v-select>
          </v-row>
          <v-row>
            <v-checkbox
              v-model="davalac_krvi"
              label="Да ли сте добровољни давалац крви?"
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
