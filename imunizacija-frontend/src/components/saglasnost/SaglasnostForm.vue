<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="8" md="12">
        <v-form ref="form" v-model="valid">
          <v-row>
            <h3 class="mr-5 mt-5">Drzavljanin republike srbije:</h3>
            <v-divider vertical> </v-divider>
            <v-text-field
              class="ml-5"
              v-model="jmbg"
              :rules="jmbgRules"
              label="JMBG"
              required
              :disabled="brojPasosa !== '' || nazivStranogDrzavljanstva !== ''"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              class="mr-5"
              v-model="nazivStranogDrzavljanstva"
              label="Naziv stranog drzavljanstva"
              required
              :disabled="jmbg !== ''"
            ></v-text-field>
            <v-divider vertical> </v-divider>
            <v-text-field
              class="ml-5"
              v-model="brojPasosa"
              label="Broj pasosa ili EBS"
              required
              :disabled="jmbg !== ''"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              class="mr-5"
              v-model="name"
              :rules="nameRules"
              label="Ime"
              required
            ></v-text-field>
            <v-divider vertical> </v-divider>
            <v-text-field
              class="mx-5"
              v-model="surname"
              :rules="surnameRules"
              label="Prezime"
              required
            ></v-text-field>
            <v-divider vertical> </v-divider>
            <v-text-field
              class="ml-5"
              v-model="imeRoditelja"
              :rules="imeRoditeljaRules"
              label="Ime roditelja"
              required
            ></v-text-field>
          </v-row>
          <v-row>
            <v-select
              class="mr-5"
              v-model="pol"
              :items="polovi"
              :rules="polRules"
              label="Pol"
              data-vv-name="pol"
              required
            ></v-select>
            <v-divider vertical> </v-divider>

            <v-text-field
              class="mx-5"
              :rules="datumRodjenjaRules"
              v-model="datumRodjenja"
              single-line
            >
              <template v-slot:append-outer>
                <date-picker v-model="datumRodjenja" />
              </template>
            </v-text-field>

            <v-divider vertical> </v-divider>

            <v-text-field
              class="ml-5"
              v-model="mestoRodjenja"
              :rules="mestoRodjenjaRules"
              label="Mesto rodjenja"
              required
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              class="mr-5"
              v-model="ulica"
              :rules="ulicaRules"
              label="Ulica"
              required
            ></v-text-field>

            <v-divider vertical> </v-divider>

            <v-text-field
              class="mx-5"
              v-model="brojZgrade"
              :rules="brojZgradeRules"
              label="Broj"
              required
            ></v-text-field>

            <v-divider vertical> </v-divider>

            <v-text-field
              class="ml-5"
              v-model="mesto"
              :rules="mestoRules"
              label="Mesto"
              required
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              class="mr-5"
              v-model="opstina"
              :rules="opstinaRules"
              label="Opstina"
              required
            ></v-text-field>
            <v-divider vertical> </v-divider>
            <v-text-field
              class="ml-5"
              v-model="fiksni"
              :rules="fiksniRules"
              label="Tel. fiksni"
              required
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              class="mr-5"
              v-model="mobilni"
              :rules="mobilniRules"
              label="Tel. mobilni"
              required
            ></v-text-field>
            <v-divider vertical> </v-divider>
            <v-text-field
              class="ml-5"
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required
            ></v-text-field>
          </v-row>

          <v-select
            v-model="radniStatus"
            :items="radniStatusi"
            :rules="radniStatusRules"
            label="Radni status"
            data-vv-name="radniStatus"
            required
            @change="clearZanimanje"
          ></v-select>

          <v-select
            v-model="zanimanjeZaposlenog"
            :items="zanimanja"
            label="Zanimanje zaposlenog"
            data-vv-name="zanimanjeZaposlenog"
            :disabled="radniStatus !== 'ZAPOSLEN'"
          ></v-select>

          <v-row>
            <v-checkbox
              class="mr-5"
              v-model="korisnikUstanoveSocZastite"
              label="Korisnik ustanove socijalne zastite?"
              @change="clearNazivIOpstinaSedista"
            ></v-checkbox>

            <v-divider vertical> </v-divider>

            <v-text-field
              class="mx-5"
              v-model="nazivSedista"
              label="Naziv sedista"
              :disabled="!korisnikUstanoveSocZastite"
            ></v-text-field>

            <v-divider vertical> </v-divider>

            <v-text-field
              class="ml-5"
              v-model="opstinaSedista"
              label="Opstina sedista"
              :disabled="!korisnikUstanoveSocZastite"
            ></v-text-field>
          </v-row>

          <v-row>
            <v-checkbox
              class="mr-5"
              v-model="izjava"
              label="Saglasan sam"
              @change="clearVakcina"
            ></v-checkbox>
            <v-divider vertical> </v-divider>
            <v-text-field
              class="ml-5"
              v-model="vakcina"
              label="Naziv imunoloskog leka"
              :disabled="!izjava"
            ></v-text-field>
          </v-row>

          <v-flex class="text-center">
            <v-btn :disabled="!valid" color="success" @click="submit">
              Podnesi dokument
            </v-btn>
          </v-flex>
        </v-form>
      </v-col>
    </v-row>
    <v-snackbar v-model="snackbar" :timeout="timeout">
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn color="blue" text v-bind="attrs" @click="snackbar = false">
          Zatvori
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import DatePicker from "../shared/DatePicker.vue";
import SaglasnostService from "../../services/SaglasnostService";

export default {
  name: "SaglasnostForm",
  components: { DatePicker },
  data: () => ({
    extended: false,
    snackbar: false,
    text: "Dokument je uspesno podnet.",
    timeout: 2000,
    valid: true,
    show: false,
    name: "",
    surname: "",
    jmbg: "",
    nameRules: [
      (v) => !!v || "Ime je obavezno",
      (v) => (v && v.length <= 20) || "Ime ne moze biti duze od 20 karaktera",
    ],
    jmbgRules: [
      (v) =>
        ((v.length == 13 || v.length == 0) &&
          /[0-9]{13} || [0-9]{0}/.test(v)) ||
        "JMBG mora imati tacno 13 cifara",
    ],
    surnameRules: [
      (v) => !!v || "Prezime je obavezno",
      (v) =>
        (v && v.length <= 20) || "Prezime ne moze biti duze od 20 karaktera",
    ],
    email: "",
    emailRules: [
      (v) => !!v || "E-mail je obavezan",
      (v) => /.+@.+\..+/.test(v) || "E-mail mora biti validan",
    ],
    nazivStranogDrzavljanstva: "",
    brojPasosa: "",
    imeRoditelja: "",
    imeRoditeljaRules: [(v) => !!v || "Ime roditelja je obavezno"],
    pol: "",
    polRules: [(v) => !!v || "Morati izabrati pol"],
    polovi: ["MUSKI", "ZENSKI"],
    datumRodjenja: null,
    datumRodjenjaRules: [(v) => !!v || "Morate izabrati datum rodjenja"],
    mestoRodjenja: "",
    mestoRodjenjaRules: [(v) => !!v || "Mesto rodjenja je obavezno"],
    ulica: "",
    ulicaRules: [(v) => !!v || "Naziv ulice je obavezan"],
    brojZgrade: "",
    brojZgradeRules: [(v) => !!v || "Broj zgrade je obavezan"],
    mesto: "",
    mestoRules: [(v) => !!v || "Mesto stanovanja je obavezno"],
    opstina: "",
    opstinaRules: [(v) => !!v || "Opstina stanovanja je obavezna"],
    fiksni: "",
    fiksniRules: [(v) => !!v || "Fiksni telefon je obavezan"],
    mobilni: "",
    mobilniRules: [(v) => !!v || "Mobilni telefon je obavezan"],
    radniStatus: "",
    radniStatusi: [
      "ZAPOSLEN",
      "NEZAPOSLEN",
      "PENZIONER",
      "UCENIK",
      "STUDENT",
      "DETE",
    ],
    radniStatusRules: [(v) => !!v || "Morate izabrati radni status"],
    zanimanjeZaposlenog: "",
    zanimanja: [
      "ZDRAVSTVENA ZASTITA",
      "SOCIJALNA ZASTITA",
      "PROSVETA",
      "MUP",
      "VOJSKA RS",
      "DRUGO",
    ],
    korisnikUstanoveSocZastite: false,
    nazivSedista: "",
    opstinaSedista: "",
    izjava: false,
    vakcina: "",
  }),

  methods: {
    submit() {
      if (this.izjava && this.vakcina.trim() === "") {
        this.text = "Unesite naziv imunoloskog leka";
        this.snackbar = true;
        return;
      } else if (
        this.korisnikUstanoveSocZastite &&
        (this.opstinaSedista.trim() === "" || this.nazivSedista.trim() === "")
      ) {
        this.text = "Unesite naziv i sediste ustanove soc. zastite";
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
            that.text = "Dokument uspesno podnesen";
            that.snackbar = true;
          } else {
            that.text = "Podnosenje dokumenta nije uspelo";
            that.snackbar = true;
          }
        })
        .catch((err) => {
          that.text = "Podnosenje dokumenta nije uspelo";
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
  },
};
</script>

<style>
.v-text-field {
  font-size: 1.2em;
}
</style>
