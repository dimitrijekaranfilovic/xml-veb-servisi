<template>
  <div>
    <v-form @submit="fetchData">
      <v-text-field
        v-model="query"
        label="Унесите критеријум за претрагу..."
      ></v-text-field>
    </v-form>
    <v-data-table :headers="headers" :items="certificates" class="elevation-1">
      <template v-slot:item="{ item }">
        <tr>
          <td>{{ item.id }}</td>
          <td>
            {{ item.datumVremeIzdavanja | deRdf | moment("DD.MM.YYYY.") }}
          </td>
          <td>
            {{ item.informacijeOSertifikatu.digitalniPotpis.drzava | deRdf }}
          </td>
          <td>
            <v-btn plain text color="primary">Прегледај документ</v-btn>
          </td>
          <td>
            <v-btn plain text color="primary">Прегледај референце</v-btn>
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
  data: () => ({
    query: "",
    certificates: [],
    headers: [
      { text: "Идентификатор Дигиталног сертификата" },
      { text: "Датум издавања" },
      { text: "Дигитално потписано од" },
      { text: "Преглед документа" },
      { text: "Преглед референци" },
    ],
  }),
  async mounted() {
    await this.fetchData();
  },
  methods: {
    async fetchData() {
      const response = await citizenDataService.readDigitalCertificates(
        this.query
      );
      this.certificates = response.data.digitalniSertifikati || [];
    },
  },
});
</script>