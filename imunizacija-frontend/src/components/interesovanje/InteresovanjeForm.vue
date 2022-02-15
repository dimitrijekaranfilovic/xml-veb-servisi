<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="8" md="12">
        <v-form ref="form" v-model="valid">
          <v-row>
            <v-select
              v-model="formData.drzavljanstvo"
              :items="drzavljanstvoItems"
              item-text="text"
              item-value="value"
              label="Држављанство"
            ></v-select>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.jmbg"
              label="ЈМБГ"
              :counter="13"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="formData.ime" label="Име"></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.prezime"
              label="Презиме"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.email"
              label="Адреса електронске поште"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.brojMobilnog"
              label="Број мобилног телефона (навести број у формату 06Х..... без размака и цртица)"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.brojFiksnog"
              label="Број фиксног телефона (навести број у формату нпр. 011..... без размака и цртица)"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.odabranaLokacijaPrimanjaVakcine"
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
              v-model="formData.odabraniProizvodjaci"
              :items="proizvodjaciItems"
              attach
              chips
              label="Одаберите произвођаче"
              multiple
            ></v-select>
          </v-row>
          <v-row>
            <v-radio-group
              v-model="formData.davalacKrvi"
              label="Да ли сте добровољни давалац крви?"
            >
              <v-radio
                v-for="(item, index) in davalacKrviItems"
                :key="index"
                :value="item.value"
                :label="item.text"
              ></v-radio>
            </v-radio-group>
          </v-row>
          <v-flex class="text-center">
            <v-btn :disabled="!valid" color="success" @click="submit">
              Поднеси документ
            </v-btn>
          </v-flex>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import InteresovanjeService from "@/services/InteresovanjeService";
import jwt_decode from "jwt-decode";

export default {
  data: () => ({
    drzavljanstvoItems: [
      {
        value: "DRZAVLJANIN_REPUBLIKE_SRBIJE",
        text: "Држављанин Републике Србије",
      },
      {
        value: "STRANI_DRZAVLJANIN_SA_BORAVKOM_U_RS",
        text: "Страни држављанин са боравком у РС",
      },
      {
        value: "STRANI_DRZAVLJANIN_BEZ_BORAVKA_U_RS",
        text: "Страни држављанин без боравка у РС",
      },
    ],
    davalacKrviItems: [
      { value: true, text: "Да" },
      { value: false, text: "Не" },
    ],
    proizvodjaciItems: [
      "Pfizer-BioNTech",
      "Sputnik V (Gamaleya истраживачки центар)",
      "Sinopharm",
      "AstraZeneca",
      "Moderna",
      "Било која",
    ],
    formData: {
      drzavljanstvo: "",
      jmbg: "",
      email: "",
      brojMobilnog: "",
      brojFiksnog: "",
      ime: "",
      prezime: "",
      davalacKrvi: false,
      odabranaLokacijaPrimanjaVakcine: "",
      odabraniProizvodjaci: [],
    },
    valid: false,
  }),
  mounted() {
    let jwt = localStorage.getItem("jwt");
    let decoded = jwt_decode(jwt);

    this.formData.email = decoded.sub;
    this.formData.ime = decoded.name;
    this.formData.prezime = decoded.surname;
    this.formData.drzavljanstvo = this.drzavljanstvoItems[0].value;
  },
  methods: {
    submit() {
      let currentDate = new Date().toJSON().slice(0, 10);
      let interesovanjeJSON = {
        interesovanje: {
          _attributes: {
            xmlns: "https://www.vakcinacija.rs/interesovanje",
            "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
            "xmlns:za": "https://www.vakcinacija.rs/zajednicko",
          },
          datum: currentDate,
          licne_informacije: {
            drzavljanstvo: this.formData.drzavljanstvo,
            jmbg: this.formData.jmbg,
            kontakt: {
              "za:broj_fiksnog": this.formData.brojFiksnog,
              "za:broj_mobilnog": this.formData.brojMobilnog,
              "za:email": this.formData.email,
            },
            puno_ime: {
              "za:ime": this.formData.ime,
              "za:prezime": this.formData.prezime,
            },
            davalac_krvi: this.formData.davalacKrvi,
          },
          odabrana_lokacija_primanja_vakcine:
            this.formData.odabranaLokacijaPrimanjaVakcine,
          odabrani_proizvodjaci: {
            proizvodjac: this.formData.odabraniProizvodjaci,
          },
        },
      };

      let response = InteresovanjeService.postInteresovanje(interesovanjeJSON);
    },
  },
};
</script>
