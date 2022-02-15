<template>
  <v-tabs>
    <v-tab>Обрасци сагласности</v-tab>
    <v-tab>Потврде о вакцинацији</v-tab>
    <v-tab>Дигитални сертификати</v-tab>

    <v-tab-item>
      <vaccination-consent-table :email="email"></vaccination-consent-table>
    </v-tab-item>
    <v-tab-item>
      <vaccination-certificate-table
        :email="email"
      ></vaccination-certificate-table>
    </v-tab-item>
    <v-tab-item>
      <digital-certificate-table :email="email"></digital-certificate-table>
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
  props: ["email"],
  name: "CitizenDocumentsTabs",
  data: () => ({}),
});
</script>