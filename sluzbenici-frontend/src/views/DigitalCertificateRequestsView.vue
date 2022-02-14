<template>
  <v-container fluid>
    <v-row align="center" justify="center">
      <h1>Захтеви за дигитални сертификат</h1>
    </v-row>
    <v-row align="center" justify="center">
      <v-col>
        <digital-certificate-request-table></digital-certificate-request-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import DigitalCertificateRequestTable from "@/components/DigitalCertificateRequestTable.vue";
import Vue from "vue";

export default Vue.extend({
  components: { DigitalCertificateRequestTable },
  name: "DigitalCertifikateRequestsView",
});
</script>
