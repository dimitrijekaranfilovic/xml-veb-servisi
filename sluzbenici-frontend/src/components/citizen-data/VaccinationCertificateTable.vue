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
            {{ item.datumIzdavanja | deRdf | moment("DD.MM.YYYY.") }}
          </td>
          <td>
            {{ item.vakcinacija.nazivVakcine | deRdf }}
          </td>
          <td>
            {{ item.vakcinacija.ustanova | deRdf }}
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
    certificates: [],
    query: "",
    headers: [
      { text: "Идентификатор Потврде" },
      { text: "Датум издавања" },
      { text: "Вакцина" },
      { text: "Установа" },
      { text: "Преглед документа" },
      { text: "Преглед референци" },
    ],
  }),
  async mounted() {
    await this.fetchData();
  },
  methods: {
    async fetchData() {
      const response = await citizenDataService.readVaccinationCertificates(
        this.query
      );
      this.certificates = response.data.potvrde || [];
    },
  },
});
</script>