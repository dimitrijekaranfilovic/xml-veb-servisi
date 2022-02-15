<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="8" md="12">
        <v-form ref="form" v-model="valid">
          <v-row>
            <h3 class="mt-5">Држављанин републике Србије:</h3>
            <v-divider class="mx-5" vertical> </v-divider>
            <v-text-field
              v-model="jmbg"
              :rules="[checkJMBG]"
              label="ЈМБГ"
              required
              :disabled="brojPasosa !== '' || nazivStranogDrzavljanstva !== ''"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="nazivStranogDrzavljanstva"
              label="Назив страног држављанства"
              required
              :disabled="jmbg !== ''"
            ></v-text-field>
            <v-divider class="mx-5" vertical> </v-divider>
            <v-text-field
              v-model="brojPasosa"
              label="Број пасоша или ЕБС"
              required
              :disabled="jmbg !== ''"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="name"
              label="Име"
              required
              disabled
            ></v-text-field>
            <v-divider class="mx-5" vertical> </v-divider>
            <v-text-field
              v-model="surname"
              label="Презиме"
              required
              disabled
            ></v-text-field>
            <v-divider class="mx-5" vertical> </v-divider>
            <v-text-field
              v-model="imeRoditelja"
              :rules="imeRoditeljaRules"
              label="Име родитеља"
              required
            ></v-text-field>
          </v-row>
          <v-row>
            <v-select
              v-model="pol"
              :items="polovi"
              :rules="polRules"
              item-text="text"
              item-value="value"
              label="Пол"
              data-vv-name="pol"
              required
            ></v-select>
            <v-divider class="mx-5" vertical> </v-divider>

            <v-text-field
              :rules="datumRodjenjaRules"
              v-model="datumRodjenja"
              label="Датум рођења"
              single-line
            >
              <template v-slot:append-outer>
                <date-picker v-model="datumRodjenja" />
              </template>
            </v-text-field>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-text-field
              v-model="mestoRodjenja"
              :rules="mestoRodjenjaRules"
              label="Место рођења"
              required
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="ulica"
              :rules="ulicaRules"
              label="Улица"
              required
            ></v-text-field>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-text-field
              v-model="brojZgrade"
              :rules="brojZgradeRules"
              label="Број"
              required
            ></v-text-field>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-text-field
              v-model="mesto"
              :rules="mestoRules"
              label="Место"
              required
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="opstina"
              :rules="opstinaRules"
              label="Општина"
              required
            ></v-text-field>
            <v-divider class="mx-5" vertical> </v-divider>
            <v-text-field
              v-model="fiksni"
              :rules="fiksniRules"
              label="Тел. фиксни"
              required
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="mobilni"
              :rules="mobilniRules"
              label="Тел. мобилни"
              required
            ></v-text-field>
            <v-divider class="mx-5" vertical> </v-divider>
            <v-text-field
              v-model="email"
              label="Имејл"
              required
              disabled
            ></v-text-field>
          </v-row>
          <v-row>
            <v-select
              v-model="radniStatus"
              :items="radniStatusi"
              :rules="radniStatusRules"
              item-text="text"
              item-value="value"
              label="Радни статус"
              data-vv-name="radniStatus"
              required
              @change="clearZanimanje"
            ></v-select>
          </v-row>
          <v-row>
            <v-select
              v-model="zanimanjeZaposlenog"
              :items="zanimanja"
              item-text="text"
              item-value="value"
              label="Занимање запосленог"
              data-vv-name="zanimanjeZaposlenog"
              :disabled="radniStatus !== 'ZAPOSLEN'"
            ></v-select>
          </v-row>
          <v-row>
            <v-checkbox
              v-model="korisnikUstanoveSocZastite"
              label="Корисник установе социјалне заштите?"
              @change="clearNazivIOpstinaSedista"
            ></v-checkbox>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-text-field
              v-model="nazivSedista"
              :rules="[checkNazivSedista]"
              label="Назив седишта"
              :disabled="!korisnikUstanoveSocZastite"
            ></v-text-field>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-text-field
              v-model="opstinaSedista"
              :rules="[checkOpstinaSedista]"
              label="Општина седишта"
              :disabled="!korisnikUstanoveSocZastite"
            ></v-text-field>
          </v-row>

          <v-row>
            <v-checkbox
              class="mr-5"
              v-model="izjava"
              label="Сагласан сам са спровођењем активне/пасивне имунизације"
              @change="clearVakcina"
            ></v-checkbox>
            <v-text-field
              class="ml-5"
              v-model="vakcina"
              :rules="[checkVakcina]"
              label="Назив имунолошког лека"
              :disabled="!izjava"
            ></v-text-field>
          </v-row>

          <v-row>
            <p class="justify-text">
              <b
                >Лекар ми је објаснио предности и ризике од спровођења
                активне/пасивне имунизације наведеним имунолошким леком.</b
              >
            </p>
          </v-row>

          <v-flex class="text-center mt-5">
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
import DatePicker from "../shared/DatePicker.vue";
import SaglasnostService from "../../services/SaglasnostService";
import jwt_decode from "jwt-decode";

export default {
  name: "SaglasnostForm",
  components: { DatePicker },
  data: () => ({
    extended: false,
    snackbar: false,
    text: "Документ је успешно поднет",
    timeout: 2000,
    valid: true,
    show: false,
    name: "",
    surname: "",
    jmbg: "",
    email: "",
    nazivStranogDrzavljanstva: "",
    brojPasosa: "",
    imeRoditelja: "",
    imeRoditeljaRules: [
      (v) => !!v || "Име родитеља је обавезно",
      (v) => (v && v.trim() !== "") || "Име родитеља је обавезно",
    ],
    pol: "",
    polRules: [(v) => !!v || "Морате изабрати пол"],
    polovi: [
      { value: "MUSKI", text: "Мушки" },
      { value: "ZENSKI", text: "Женски" },
    ],
    datumRodjenja: null,
    datumRodjenjaRules: [
      (v) => !!v || "Морате изабрати датум рођења",
      (v) => (v && v.trim() !== "") || "Морате изабрати датум рођења",
    ],
    mestoRodjenja: "",
    mestoRodjenjaRules: [
      (v) => !!v || "Место рођења је обавезно",
      (v) => (v && v.trim() !== "") || "Место рођења је обавезно",
    ],
    ulica: "",
    ulicaRules: [
      (v) => !!v || "Назив улице је обавезан",
      (v) => (v && v.trim() !== "") || "Назив улице је обавезан",
    ],
    brojZgrade: "",
    brojZgradeRules: [
      (v) => !!v || "Број зграде је обавезан",
      (v) => (v && v.trim() !== "") || "Број зграде је обавезан",
    ],
    mesto: "",
    mestoRules: [
      (v) => !!v || "Место становања је обавезно",
      (v) => (v && v.trim() !== "") || "Место становања је обавезно",
    ],
    opstina: "",
    opstinaRules: [
      (v) => !!v || "Општина становања је обавезна",
      (v) => (v && v.trim() !== "") || "Општина становања је обавезна",
    ],
    fiksni: "",
    fiksniRules: [
      (v) => !!v || "Фиксни телефон је обавезан",
      (v) => (v && v.trim() !== "") || "Фиксни телефон је обавезан",
    ],
    mobilni: "",
    mobilniRules: [
      (v) => !!v || "Мобилни телефон је обавезан",
      (v) => (v && v.trim() !== "") || "Мобилни телефон је обавезан",
    ],
    radniStatus: "",
    radniStatusi: [
      { value: "ZAPOSLEN", text: "Запослен" },
      { value: "NEZAPOSLEN", text: "Незапослен" },
      { value: "PENZIONER", text: "Пензионер" },
      { value: "UCENIK", text: "Ученик" },
      { value: "STUDENT", text: "Студент" },
      { value: "DETE", text: "Дете" },
    ],
    radniStatusRules: [(v) => !!v || "Морате изабрати радни статус"],
    zanimanjeZaposlenog: "",
    zanimanja: [
      { value: "ZDRAVSTVENA ZASTITA", text: "Здравствена заштита" },
      { value: "SOCIJALNA ZASTITA", text: "Социјална заштита" },
      { value: "PROSVETA", text: "Просвета" },
      { value: "MUP", text: "МУП" },
      { value: "VOJSKA RS", text: "Војска РС" },
      { value: "DRUGO", text: "Друго" },
    ],
    korisnikUstanoveSocZastite: false,
    nazivSedista: "",
    opstinaSedista: "",
    izjava: false,
    vakcina: "",
  }),
  mounted() {
    let jwt = localStorage.getItem("jwt");
    let decoded = jwt_decode(jwt);

    this.email = decoded.sub;
    this.name = decoded.name;
    this.surname = decoded.surname;
  },
  methods: {
    submit() {
      if (this.izjava && this.vakcina.trim() === "") {
        this.text = "Унесите назив имунолошког лека";
        this.snackbar = true;
        return;
      } else if (
        this.korisnikUstanoveSocZastite &&
        (this.opstinaSedista.trim() === "" || this.nazivSedista.trim() === "")
      ) {
        this.text = "Унесите назив и седиште установе соц. заштите";
        this.snackbar = true;
        return;
      }
      if (this.radniStatus === "ZAPOSLEN" && this.zanimanjeZaposlenog === "") {
        this.text = "Морате унети занимање";
        this.snackbar = true;
        return;
      }
      if (
        this.jmbg.trim() === "" &&
        (this.nazivStranogDrzavljanstva.trim() === "" ||
          this.brojPasosa.trim() === "")
      ) {
        this.text = "Морате унети идентификационе информације";
        this.snackbar = true;
        return;
      }

      let drzavljanstvo;
      if (this.jmbg === "") {
        drzavljanstvo = {
          straniDrzavljanin: {
            nazivDrzavljanstva: this.nazivStranogDrzavljanstva,
            brojPasosa: this.brojPasosa,
          },
        };
      } else {
        drzavljanstvo = {
          srpskiDrzavljanin: {
            jmbg: this.jmbg,
          },
        };
      }

      let saglasnostJSON = {
        saglasnostZaSprovodjenjeImunizacije: {
          pacijent: {
            licneInformacije: {
              drzavljanstvo: drzavljanstvo,
              punoIme: {
                ime: this.name,
                prezime: this.surname,
                imeRoditelja: this.imeRoditelja,
              },
              datumRodjenja: this.datumRodjenja,
              mestoRodjenja: this.mestoRodjenja,
              pol: this.pol,
              adresa: {
                ulica: this.ulica,
                brojZgrade: this.brojZgrade,
                mesto: this.mesto,
                opstina: this.opstina,
              },
              kontakt: {
                brojFiksnog: this.fiksni,
                brojMobilnog: this.mobilni,
                email: this.email,
              },
              radniStatus: this.radniStatus,
              zanimanjeZaposlenog: this.zanimanjeZaposlenog,
              socijalnaZastita: {
                korisnikUstanove: this.korisnikUstanoveSocZastite,
                nazivSedista: this.nazivSedista,
                opstinaSedista: this.opstinaSedista,
              },
            },
            saglasnost: {
              izjava: this.izjava,
              nazivImunoloskogLeka: this.vakcina,
            },
          },
        },
      };

      let response = SaglasnostService.submitSaglasnost(saglasnostJSON);
      let that = this;
      response
        .then((res) => {
          if (res.status === 201) {
            that.text = "Документ успешно поднешен";
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
    clearZanimanje() {
      if (this.radniStatus !== "ZAPOSLEN") {
        this.zanimanjeZaposlenog = "";
      }
    },
    clearVakcina() {
      if (!this.izjava) {
        this.vakcina = "";
      }
    },
    clearNazivIOpstinaSedista() {
      if (!this.korisnikUstanoveSocZastite) {
        this.nazivSedista = "";
        this.opstinaSedista = "";
      }
    },
    checkNazivSedista(value) {
      if (this.korisnikUstanoveSocZastite && value.trim() === "") {
        return "Назив седишта је обавезан";
      }
      return true;
    },
    checkOpstinaSedista(value) {
      if (this.korisnikUstanoveSocZastite && value.trim() === "") {
        return "Општина седишта је обавезна";
      }
      return true;
    },
    checkVakcina(value) {
      if (this.izjava && value.trim() === "") {
        return "Назив имунолошког лека је обавезан";
      }
      return true;
    },
    checkJMBG(value) {
      if (
        this.nazivStranogDrzavljanstva.trim() === "" &&
        this.brojPasosa.trim() === ""
      ) {
        if (
          (value.length === 13 && /[0-9]{13} || [0-9]{0}/.test(value)) ||
          value.length === 0
        ) {
          return true;
        } else {
          return "Јмбг мора имати тачно 13 цифара.";
        }
      }
      return true;
    },
  },
};
</script>

<style>
.v-text-field {
  font-size: 1.2em;
}
</style>
