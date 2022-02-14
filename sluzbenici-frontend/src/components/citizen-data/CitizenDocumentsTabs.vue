<template>
  <v-tabs>
    <v-tab> Obrasci saglasnosti </v-tab>
    <v-tab>Potvrde o vakcinaciji</v-tab>
    <v-tab>Digitalni sertifikati</v-tab>

    <v-tab-item>
      <vaccination-consent-table></vaccination-consent-table>
    </v-tab-item>
    <v-tab-item>
      <vaccination-certificate-table></vaccination-certificate-table>
    </v-tab-item>
    <v-tab-item>
      <digital-certificate-table></digital-certificate-table>
    </v-tab-item>
  </v-tabs>
</template>

<script>
import Vue from "vue";
import DigitalCertificateTable from "./DigitalCertificateTable.vue";
import VaccinationCertificateTable from "./VaccinationCertificateTable.vue";
import VaccinationConsentTable from "./VaccinationConsentTable.vue";

export default Vue.extend({
  components: {
    VaccinationConsentTable,
    VaccinationCertificateTable,
    DigitalCertificateTable,
  },
  name: "CitizenDocumentsTabs",
  data: () => ({}),
});
</script>