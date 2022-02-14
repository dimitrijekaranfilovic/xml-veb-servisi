<template>
  <h1>Prikaz svih obrazaca saglasnosti</h1>
</template>

<script>
import Vue from "vue";
import citizenDataService from "@/services/CitizenDataService.ts";

export default Vue.extend({
  data: () => ({}),
  async mounted() {
    const response = await citizenDataService.readVaccinationConsents();
    console.log(response.data);
  },
});
</script>