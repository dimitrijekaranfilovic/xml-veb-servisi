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
            <v-text-field v-model="formData.ime" label="Име"></v-text-field>
            <v-text-field
              v-model="formData.prezime"
              label="Презиме"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.datumRodjenja"
              label="Датум рођења"
              single-line
            >
              <template v-slot:append-outer>
                <date-picker v-model="formData.datumRodjenja" />
              </template>
            </v-text-field>
          </v-row>
          <v-row>
            <v-select
              v-model="formData.pol"
              :items="pol_items"
              item-text="text"
              item-value="value"
              label="Пол"
            ></v-select>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.jmbg"
              label="Јединствени матични број грађанина"
              :counter="13"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.brojPasosa"
              label="Број пасоша"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field
              v-model="formData.razlogZaPodnosenjeZahteva"
              label="Разлог за подношење захтева"
            ></v-text-field>
          </v-row>
          <v-row>
            <v-text-field v-model="formData.mesto" label="Место"></v-text-field>
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
import ZahtevZaSertifikatService from "@/services/ZahtevZaSertifikatService";
import DatePicker from "../shared/DatePicker.vue";
import jwt_decode from "jwt-decode";

export default {
  components: { DatePicker },
  data: () => ({
    pol_items: [
      { value: "MUSKI", text: "Мушки" },
      { value: "ZENSKI", text: "Женски" },
    ],
    formData: {
      mesto: "",
      ime: "",
      prezime: "",
      datumRodjenja: "",
      jmbg: "",
      pol: "",
      brojPasosa: "",
      razlogZaPodnosenjeZahteva: "",
    },
    valid: false,
  }),
  mounted() {
    let jwt = localStorage.getItem("jwt");
    let decoded = jwt_decode(jwt);

    this.formData.ime = decoded.name;
    this.formData.prezime = decoded.surname;
  },
  methods: {
    submit() {
      let currentDate = new Date().toJSON().slice(0, 10);
      let zahtevZaSertifikatJSON = {
        zahtev_za_sertifikat: {
          _attributes: {
            xmlns: "https://www.vakcinacija.rs/zahtev_za_sertifikat",
            "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
            "xmlns:za": "https://www.vakcinacija.rs/zajednicko",
          },
          mesto: this.formData.mesto,
          datum: currentDate,
          podnosilac_zahteva: {
            licni_podaci: {
              "za:ime": this.formData.ime,
              "za:prezime": this.formData.prezime,
              "za:jmbg": this.formData.jmbg,
              "za:pol": this.formData.pol,
            },
            datum_rodjenja: this.formData.datumRodjenja,
            broj_pasosa: this.formData.brojPasosa,
          },
          razlog_za_podnosenje_zahteva: this.formData.razlogZaPodnosenjeZahteva,
        },
      };
      let response = ZahtevZaSertifikatService.postZahtevZaSertifikat(
        zahtevZaSertifikatJSON
      );
    },
  },
};
</script>

<style></style>
