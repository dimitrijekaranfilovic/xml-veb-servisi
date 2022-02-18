<template>
  <div>
    <v-form @submit="fetchData">
      <v-row justify="center" align="center">
        <v-col lg="10" md="12" sm="12" xs="12">
          <v-combobox
            @input="onChipInput()"
            v-model="select"
            :items="items"
            label="Напредна претрага (крените да куцате филтере...)"
            :clearable="true"
            :deletable-chips="true"
            multiple
            chips
          ></v-combobox>
        </v-col>
        <v-col lg="2" md="12" sm="12" xs="12">
          <v-btn block color="primary" @click="fetchData"
            >Примени филтере</v-btn
          >
        </v-col>
      </v-row>
    </v-form>
    <v-dialog v-model="dialog" max-width="1000px">
      <v-card>
        <v-card-title>
          <span class="text-h5"
            >Референце потврде о вакцинацији {{ selectedItem.id }}</span
          >
        </v-card-title>
        <v-card-text>
          <v-simple-table
            v-if="
              selectedItem.reference &&
              selectedItem.reference.reference.length > 0
            "
          >
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">Идентификатор</th>
                  <th class="text-left">Тип документа</th>
                  <th class="text-left">Погледај документ</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(referenca, index) in selectedItem.reference.reference"
                  :key="index"
                >
                  <td>{{ referenca | deRdf }}</td>
                  <td>{{ getDocumentName(referenca.typeof) }}</td>
                  <v-btn plain text color="primary" :to="buildRoute(referenca)"
                    >Прегледај документ</v-btn
                  >
                </tr>
              </tbody>
            </template>
          </v-simple-table>
          <h3 v-else>Изабрани документ не садржи референце.</h3>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="dialog = false" text> Затвори </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-data-table :headers="headers" :items="certificates" class="elevation-1">
      <template v-slot:item="{ item }">
        <tr>
          <td>{{ item.id }}</td>
          <td>
            {{ item.datumIzdavanja | deRdf | moment("DD.MM.YYYY.") }}
          </td>
          <td>
            {{ item.vakcinacija.nazivVakcine | deRdf }}
          </td>
          <td>
            {{ item.vakcinacija.ustanova | deRdf }}
          </td>
          <td>
            <v-btn
              plain
              text
              color="primary"
              :to="{
                name: 'VaccinationConfirmationDocumentView',
                params: { id: item.id },
              }"
              >Прегледај документ</v-btn
            >
          </td>
          <td>
            <v-btn plain text color="primary" @click="showReferences(item)">
              Погледај референце
            </v-btn>
          </td>
        </tr>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import Vue from "vue";
import citizenDataService from "@/services/CitizenDataService.ts";

export default Vue.extend({
  props: ["email"],
  data: () => ({
    certificates: [],
    query: "",
    dialog: false,
    selectedItem: {},
    headers: [
      { text: "Идентификатор Потврде" },
      { text: "Датум издавања" },
      { text: "Вакцина" },
      { text: "Установа" },
      { text: "Преглед документа" },
      { text: "Преглед референци" },
    ],
    documentName: {
      "pred:DigitalniSertifikat": "Дигитални сертификат",
      "pred:PotvrdaOVakcinaciji": "Потврда о вакцинацији",
      "pred:SaglasnostZaSprovodjenjeImunizacije": "Образац сагласности",
      "pred:ZahtevZaSertifikat": "Захтев за сертификат",
      "pred:Interesovanje": "Документ интересовања",
    },
    documentRoute: {
      "pred:DigitalniSertifikat": "DigitalCertificateDocumentView",
      "pred:PotvrdaOVakcinaciji": "VaccinationConfirmationDocumentView",
      "pred:SaglasnostZaSprovodjenjeImunizacije":
        "VaccinationConsentDocumentView",
      "pred:ZahtevZaSertifikat": "DigitalCertificateRequestDocumentView",
      "pred:Interesovanje": "Документ интересовања",
    },
    select: ["Текст:"],
    items: ["Текст:", "Датум издавања:", "Установа:", "Шифра документа:"],
    itemToQuery: {
      Текст: "query",
      "Датум издавања": "issueDate",
      Установа: "place",
      "Шифра документа": "documentId",
    },
  }),
  async mounted() {
    await this.fetchData();
  },
  methods: {
    async fetchData() {
      const params = this.processQuery();
      const response = await citizenDataService.readVaccinationCertificates(
        params
      );
      this.certificates = response.data.potvrde || [];
    },
    onChipInput() {
      const last = this.select.pop();
      if (this.items.includes(last)) {
        this.select.push(last);
      } else if (this.select.length > 0) {
        const preLast = this.select.pop();
        this.select.push(preLast + last);
      }
    },
    processQuery() {
      const params = {};
      params["email"] = this.email;
      this.select.forEach((selection) => {
        const tokens = selection.split(":");
        if (tokens.length == 2 && tokens[1].trim() !== "") {
          params[this.itemToQuery[tokens[0].trim()]] = tokens[1].trim();
        }
      });
      return params;
    },
    showReferences(item) {
      this.selectedItem = item;
      this.dialog = true;
    },
    getDocumentName(name) {
      if (name in this.documentName) {
        return this.documentName[name];
      }
      console.warn("Got unexpected document type: ", name);
      return "Непознати документ";
    },
    buildRoute(reference) {
      return {
        name: this.documentRoute[reference.typeof],
        params: { id: reference.value },
      };
    },
  },
});
</script>
