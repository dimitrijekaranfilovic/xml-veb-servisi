<template>
  <div>
    <v-form @submit="fetchData">
      <v-text-field
        v-model="query"
        label="Унесите критеријум за претрагу..."
      ></v-text-field>
    </v-form>
    <v-dialog v-model="dialog" max-width="1000px">
      <v-card>
        <v-card-title>
          <span class="text-h5"
            >Референце обрасца сагласности {{ selectedItem.id }}</span
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
                  <v-btn plain text color="primary">Прегледај документ</v-btn>
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
    <v-data-table :headers="headers" :items="consents" class="elevation-1">
      <template v-slot:item="{ item }">
        <tr>
          <td>{{ item.id }}</td>
          <td>
            {{ item.datum | deRdf | moment("DD.MM.YYYY.") }}
          </td>
          <td>
            {{ item.pacijent.saglasnost.izjava | deRdf }}
          </td>
          <td>
            {{ item.pacijent.saglasnost.nazivImunoloskogLeka | deRdf }}
          </td>
          <td>
            <v-btn plain text color="primary">Прегледај документ</v-btn>
          </td>
          <td>
            <v-btn plain text color="primary" @click="showReferences(item)"
              >Прегледај референце</v-btn
            >
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
    consents: [],
    query: "",
    dialog: false,
    selectedItem: {},
    headers: [
      { text: "Идентификатор Потврде" },
      { text: "Датум издавања" },
      { text: "Изјава" },
      { text: "Назив имунолошког лека" },
      { text: "Преглед документа" },
      { text: "Преглед референци" },
    ],
    documentName: {
      "pred:DigitalniSertifikat": "Дигитални сертификат",
      "pred:PotvrdaOVakcinaciji": "Потврда о вакцинацији",
      "pred:ObrazacSaglasnosti": "Образац сагласности",
      "pred:ZahtevZaSertifikat": "Захтев за сертификат",
      "pred:Interesovanje": "Документ интересовања",
    },
  }),
  async mounted() {
    await this.fetchData();
  },
  methods: {
    async fetchData() {
      const response = await citizenDataService.readVaccinationConsents(
        this.email,
        this.query
      );
      this.consents = response.data.saglasnosti || [];
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
  },
});
</script>