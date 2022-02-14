<template>
  <v-data-table :headers="headers" :items="requests" class="elevation-1">
    <template v-slot:item="{ item }">
      <tr>
        <td>{{ item.podnosilacZahteva.licniPodaci.ime | deRdf }}</td>
        <td>
          {{ item.podnosilacZahteva.licniPodaci.prezime | deRdf }}
        </td>
        <td>
          {{ item.podnosilacZahteva.licniPodaci.jmbg | deRdf }}
        </td>
        <td>{{ item.podnosilacZahteva.licniPodaci.pol | deRdf }}</td>
        <td>
          {{
            item.podnosilacZahteva.datumRodjenja | deRdf | moment("DD.MM.YYYY.")
          }}
        </td>
        <td>{{ item.datum | deRdf | moment("DD.MM.YYYY.") }}</td>
        <td>{{ item.mesto | deRdf }}</td>
        <td>{{ item.razlogZaPodnosenjeZahteva | deRdf }}</td>
        <td>
          <v-btn
            plain
            text
            color="primary"
            :to="{
              name: 'SingleDigitalCertificateRequestView',
              params: { id: item.id },
            }"
            >Прикажи детаље</v-btn
          >
        </td>
      </tr>
    </template>
  </v-data-table>
</template>

<style scoped></style>

<script>
import digitalCertificateRequestService from "@/services/DigitalCertificateRequestService";
import Vue from "vue";

export default Vue.extend({
  name: "DigitalCertificateRequestTable",
  data: () => ({
    requests: [],
    headers: [
      { text: "Име" },
      { text: "Презиме" },
      { text: "ЈМБГ" },
      { text: "Пол" },
      { text: "Датум рођења" },
      { text: "Датум подношења захтева" },
      { text: "Место" },
      { text: "Разлог подношења захтева" },
      { text: "Детаљи" },
    ],
  }),
  async mounted() {
    const response = await digitalCertificateRequestService.read();
    this.requests = response.data.zahteviZaSertifikat;
  },
});
</script>
