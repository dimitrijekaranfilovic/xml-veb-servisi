<template>
  <v-container fluid v-if="vaccination">
    <v-row justify="center" v-if="vaccination.pacijent">
      <v-col lg="8" md="12">
        <v-form ref="form">
          <v-row
            v-if="
              vaccination.pacijent.licneInformacije.drzavljanstvo
                .srpskiDrzavljanin
            "
          >
            <h3 class="mt-5">Држављанин републике Србије:</h3>
            <v-divider class="mx-5" vertical> </v-divider>
            <v-text-field
              :value="
                vaccination.pacijent.licneInformacije.drzavljanstvo
                  .srpskiDrzavljanin.jmbg.value
              "
              readonly
              label="ЈМБГ"
            ></v-text-field>
          </v-row>
          <v-row v-else>
            <v-text-field
              :value="
                vaccination.pacijent.licneInformacije.drzavljanstvo
                  .straniDrzavljanin.nazivDrzavljanstva.value
              "
              label="Назив страног држављанства"
              readonly
            ></v-text-field>
            <v-divider class="mx-5" vertical> </v-divider>
            <v-text-field
              :value="
                vaccination.pacijent.licneInformacije.drzavljanstvo
                  .straniDrzavljanin.brojPasosa.value
              "
              label="Број пасоша или ЕБС"
              readonly
            ></v-text-field>
          </v-row>
          <v-row class="form-row">
            <v-col cols="12" md="3">
              <v-text-field
                :value="vaccination.pacijent.licneInformacije.punoIme.ime.value"
                label="Име"
                readonly
              ></v-text-field>
            </v-col>
            <v-divider class="mx-5" vertical> </v-divider>

            <v-col cols="12" md="3">
              <v-text-field
                :value="
                  vaccination.pacijent.licneInformacije.punoIme.prezime.value
                "
                label="Презиме"
                readonly
              ></v-text-field>
            </v-col>
            <v-divider class="mx-5" vertical> </v-divider>

            <v-col cols="12" md="3">
              <v-text-field
                v-model="
                  vaccination.pacijent.licneInformacije.punoIme.imeRoditelja
                    .value
                "
                label="Име родитеља"
                readonly
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row class="form-row">
            <v-col cols="12" md="3">
              <v-select
                :value="vaccination.pacijent.licneInformacije.pol.value"
                :items="sexes"
                item-text="text"
                item-value="value"
                label="Пол"
                data-vv-name="pol"
                readonly
              ></v-select>
            </v-col>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-col cols="12" md="3">
              <v-menu
                ref="menu1"
                v-model="menuDate1"
                :close-on-content-click="false"
                :return-value.sync="date1"
                transition="scale-transition"
                offset-y
                min-width="auto"
                disabled
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    label="Датум рођења"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                    v-model="
                      vaccination.pacijent.licneInformacije.datumRodjenja.value
                    "
                  ></v-text-field>
                </template>
                <v-date-picker
                  no-title
                  scrollable
                  readonly
                  v-model="
                    vaccination.pacijent.licneInformacije.datumRodjenja.value
                  "
                >
                </v-date-picker>
              </v-menu>
            </v-col>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-col cols="12" md="3">
              <v-text-field
                :value="
                  vaccination.pacijent.licneInformacije.mestoRodjenja.value
                "
                label="Место рођења"
                readonly
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row class="form-row">
            <v-col cols="12" md="3">
              <v-text-field
                :value="
                  vaccination.pacijent.licneInformacije.adresa.ulica.value
                "
                readonly
                label="Улица"
              ></v-text-field>
            </v-col>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-col cols="12" md="3">
              <v-text-field
                :value="
                  vaccination.pacijent.licneInformacije.adresa.brojZgrade.value
                "
                label="Број"
                readonly
              ></v-text-field>
            </v-col>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-col cols="12" md="3">
              <v-text-field
                :value="
                  vaccination.pacijent.licneInformacije.adresa.mesto.value
                "
                label="Место"
                readonly
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row class="form-row">
            <v-col cols="12" md="5">
              <v-text-field
                :value="
                  vaccination.pacijent.licneInformacije.adresa.opstina.value
                "
                label="Општина"
                readonly
              ></v-text-field>
            </v-col>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-col cols="12" md="5">
              <v-text-field
                :value="
                  vaccination.pacijent.licneInformacije.kontakt.brojFiksnog
                    .value
                "
                label="Тел. фиксни"
                readonly
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row class="form-row">
            <v-col cols="12" md="5">
              <v-text-field
                :value="
                  vaccination.pacijent.licneInformacije.kontakt.brojMobilnog
                    .value
                "
                label="Тел. мобилни"
                readonly
              ></v-text-field>
            </v-col>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-col cols="12" md="5">
              <v-text-field
                :value="
                  vaccination.pacijent.licneInformacije.kontakt.email.value
                "
                label="Имејл"
                readonly
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-select
              :value="vaccination.pacijent.licneInformacije.radniStatus.value"
              :items="workStatuses"
              item-text="text"
              item-value="value"
              label="Радни статус"
              data-vv-name="radniStatus"
              readonly
            ></v-select>
          </v-row>
          <v-row>
            <v-select
              :value="
                vaccination.pacijent.licneInformacije.zanimanjeZaposlenog.value
              "
              :items="occupations"
              item-text="text"
              item-value="value"
              label="Занимање запосленог"
              data-vv-name="zanimanjeZaposlenog"
              readonly
            ></v-select>
          </v-row>
          <v-row>
            <v-checkbox
              :value="
                vaccination.pacijent.licneInformacije.socijalnaZastita
                  .korisnikUstanove.value
              "
              label="Корисник установе социјалне заштите?"
              readonly
            ></v-checkbox>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-text-field
              :value="
                vaccination.pacijent.licneInformacije.socijalnaZastita
                  .nazivSedista.value
              "
              label="Назив седишта"
              readonly
            ></v-text-field>

            <v-divider class="mx-5" vertical> </v-divider>

            <v-text-field
              :value="
                vaccination.pacijent.licneInformacije.socijalnaZastita
                  .opstinaSedista.value
              "
              label="Општина седишта"
              readonly
            ></v-text-field>
          </v-row>

          <v-row>
            <v-checkbox
              class="mr-5"
              :value="vaccination.pacijent.saglasnost.izjava.value"
              label="Сагласан сам са спровођењем активне/пасивне имунизације"
              readonly
            ></v-checkbox>
            <v-text-field
              class="ml-5"
              :value="
                vaccination.pacijent.saglasnost.nazivImunoloskogLeka.value
              "
              label="Назив имунолошког лека"
              readonly
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
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
//TODO: vidi za checkboxove
export default {
  name: "PatientVaccinationPart",
  props: ["vaccination"],
  data() {
    return {
      menuDate1: false,
      date1: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      sexes: [
        { value: "MUSKI", text: "Мушки" },
        { value: "ZENSKI", text: "Женски" },
      ],
      workStatuses: [
        { value: "ZAPOSLEN", text: "Запослен" },
        { value: "NEZAPOSLEN", text: "Незапослен" },
        { value: "PENZIONER", text: "Пензионер" },
        { value: "UCENIK", text: "Ученик" },
        { value: "STUDENT", text: "Студент" },
        { value: "DETE", text: "Дете" },
      ],
      occupations: [
        { value: "ZDRAVSTVENA ZASTITA", text: "Здравствена заштита" },
        { value: "SOCIJALNA ZASTITA", text: "Социјална заштита" },
        { value: "PROSVETA", text: "Просвета" },
        { value: "MUP", text: "МУП" },
        { value: "VOJSKA RS", text: "Војска РС" },
        { value: "DRUGO", text: "Друго" },
      ],
    };
  },
  mounted() {},
};
</script>

<style scoped>
.form-row {
  margin-top: 20px;
}
</style>
