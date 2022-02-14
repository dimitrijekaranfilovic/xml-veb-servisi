<template>
  <v-simple-table v-if="requests">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Име</th>
          <th class="text-left">Презиме</th>
          <th class="text-left">ЈМБГ</th>
          <th class="text-left">Пол</th>
          <th class="text-left">Датум рођења</th>
          <th class="text-left">Датум подношења</th>
          <th class="text-left">Место</th>
          <th class="text-left">Разлог</th>
          <th class="text-left">Детаљи</th>
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
          <td>
            {{
              request.podnosilacZahteva.datumRodjenja
                | deRdf
                | moment("DD.MM.YYYY.")
            }}
          </td>
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
              >Прикажи детаље</v-btn
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
