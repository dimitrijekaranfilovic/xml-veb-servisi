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
              label="ЈМБГ*"
              :counter="13"
              :rules="jmbgRules"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.ime"
              label="Име*"
              disabled
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.prezime"
              label="Презиме*"
              disabled
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.email"
              label="Адреса електронске поште*"
              disabled
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.brojMobilnog"
              label="Број мобилног телефона (навести број у формату 06Х..... без размака и цртица)*"
              :rules="brojMobilnogRules"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.brojFiksnog"
              label="Број фиксног телефона (навести број у формату нпр. 011..... без размака и цртица)*"
              :rules="brojFiksnogRules"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-select
              v-model="formData.odabranaLokacijaPrimanjaVakcine"
              label="Одаберите локацију где желите да примите вакцину (унесите општину)*"
              :rules="odabranaLokacijaPrimanjaVakcineRules"
              :items="mestoItems"
            ></v-select>
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
              label="Одаберите произвођаче*"
              multiple
              :deletable-chips="true"
              :rules="odabraniProizvodjaciRules"
              required
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
    <v-snackbar v-model="snackbar" :timeout="timeout">
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn color="blue" text v-bind="attrs" @click="snackbar = false">
          Затвори
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import InteresovanjeService from "@/services/InteresovanjeService";
import jwt_decode from "jwt-decode";
import Opstine from "@/utility/Places";

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
      { value: false, text: "Не" },
      { value: true, text: "Да" },
    ],
    proizvodjaciItems: [
      "Pfizer-BioNTech",
      "Sputnik V (Gamaleya истраживачки центар)",
      "Sinopharm",
      "AstraZeneca",
      "Moderna",
      "Било која",
    ],
    mestoItems: Opstine.listaOpstina,
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
    snackbar: false,
    timeout: 2000,
    text: "Документ је успешно поднет",
    brojMobilnogRules: [
      (v) => (!!v && v && v.trim() !== "") || "Број мобилног је обавезан",
      (v) =>
        (v && /^06\d{7,8}$/.test(v)) ||
        "Број мобилног мора бити у формату 06Х... без размака и цртица и са 9 до 10 цифара",
    ],
    brojFiksnogRules: [
      (v) => (!!v && v && v.trim() !== "") || "Број мобилног је обавезан",
      (v) =>
        (v && /^\d{9,10}$/.test(v)) ||
        "Број фиксног мора бити у формату нпр. 011... без размака и цртица и са 9 до 10 цифара",
    ],
    odabranaLokacijaPrimanjaVakcineRules: [
      (v) => (!!v && v && v.trim() !== "") || "Локација је обавезна",
    ],
    odabraniProizvodjaciRules: [
      (v) => (!!v && v.length > 0) || "Морате изабрати бар једног произвођача",
    ],
    jmbgRules: [
      (v) => (!!v && v && v.trim() !== "") || "Морате унети ЈМБГ",
      (v) => (v && /^\d{13}$/.test(v)) || "ЈМБГ мора имати тачно 13 цифара",
    ],
  }),
  mounted() {
    let jwt = localStorage.getItem("jwt");
    let decoded = jwt_decode(jwt);

    this.formData.email = decoded.sub;
    this.formData.ime = decoded.name;
    this.formData.prezime = decoded.surname;
    this.formData.drzavljanstvo = this.drzavljanstvoItems[0].value;
    this.formData.odabranaLokacijaPrimanjaVakcine = this.mestoItems[0];
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
      let that = this;
      response
        .then((res) => {
          if (res.status === 201) {
            that.text = "Документ успешно поднесен";
            that.snackbar = true;
          } else {
            that.text = "Подношење документа није успело";
            that.snackbar = true;
          }
        })
        .catch((err) => {
          that.text = "Подношење документа није успело";
          that.snackbar = true;
        });
    },
  },
};
</script>
