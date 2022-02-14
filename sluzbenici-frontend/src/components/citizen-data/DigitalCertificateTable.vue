<template>
  <h1>Prikaz svih digitalnih sertifikata</h1>
</template>

<script>
import Vue from "vue";
import citizenDataService from "@/services/CitizenDataService.ts";

export default Vue.extend({
  data: () => ({}),
  async mounted() {
    const response = await citizenDataService.readDigitalCertificates();
    console.log(response.data);
  },
});
</script>