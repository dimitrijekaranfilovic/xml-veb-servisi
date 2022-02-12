<template>
  <v-container fluid>
    <v-row align="center" justify="center">
      <h1>Zahtevi za digitalni sertifikat</h1>
    </v-row>
    <v-row align="center" justify="center">
      <v-col>
        <digital-certificate-request-table></digital-certificate-request-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import DigitalCertificateRequestTable from "@/components/DigitalCertificateRequestTable.vue";
import Vue from "vue";

export default Vue.extend({
  components: { DigitalCertificateRequestTable },
  name: "DigitalCertifikateRequestsView",
});
</script>
