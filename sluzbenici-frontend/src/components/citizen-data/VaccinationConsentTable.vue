<template>
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
    consents: [],
    headers: [
      { text: "Идентификатор Потврде" },
      { text: "Датум издавања" },
      { text: "Изјава" },
      { text: "Назив имунолошког лека" },
      { text: "Преглед документа" },
      { text: "Преглед референци" },
    ],
  }),
  async mounted() {
    const response = await citizenDataService.readVaccinationConsents();
    this.consents = response.data.saglasnosti;
  },
});
</script>