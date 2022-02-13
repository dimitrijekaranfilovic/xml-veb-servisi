<template>
  <v-simple-table v-if="requests">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Ime</th>
          <th class="text-left">Prezime</th>
          <th class="text-left">JMBG</th>
          <th class="text-left">Pol</th>
          <th class="text-left">Datum podnošenja</th>
          <th class="text-left">Mesto</th>
          <th class="text-left">Razlog</th>
          <th class="text-left">Detalji</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(request, index) in requests" :key="index">
          <td>{{ request.podnosilacZahteva.licniPodaci.ime | deRdf }}</td>
          <td>
            {{ request.podnosilacZahteva.licniPodaci.prezime | deRdf }}
          </td>
          <td>
            {{ request.podnosilacZahteva.licniPodaci.jmbg | deRdf }}
          </td>
          <td>{{ request.podnosilacZahteva.licniPodaci.pol | deRdf }}</td>
          <td>{{ request.datum | deRdf | moment("DD.MM.YYYY.") }}</td>
          <td>{{ request.mesto | deRdf }}</td>
          <td>{{ request.razlogZaPodnosenjeZahteva | deRdf }}</td>
          <td>
            <v-btn
              plain
              text
              color="primary"
              :to="{
                name: 'SingleDigitalCertificateRequestView',
                params: { id: request.id },
              }"
              >Prikaži detalje</v-btn
            >
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<style scoped></style>

<script>
import Vue from "vue";
import digitalCertificateRequestService from "@/services/DigitalCertificateRequestService";

export default Vue.extend({
  name: "DigitalCertificateRequestTable",
  data: () => ({
    requests: [],
  }),
  async mounted() {
    const response = await digitalCertificateRequestService.read();
    this.requests = response.data.zahteviZaSertifikat;
  },
});
</script>
