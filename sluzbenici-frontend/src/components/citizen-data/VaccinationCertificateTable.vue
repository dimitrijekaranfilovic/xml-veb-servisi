<template>
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
</template>

<script>
import Vue from "vue";
import citizenDataService from "@/services/CitizenDataService.ts";

export default Vue.extend({
  data: () => ({
    certificates: [],
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
    const response = await citizenDataService.readVaccinationCertificates();
    this.certificates = response.data.potvrde;
  },
});
</script>