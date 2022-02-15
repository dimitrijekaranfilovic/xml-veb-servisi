<template>
  <v-container>
    <vaccination-table :consents="consents" />
  </v-container>
</template>

<script>
import VaccinationTable from "@/components/vaccination-process/VaccinationTable.vue";

export default {
  name: "VaccinationView",
  components: {
    VaccinationTable,
  },
  data() {
    return {
      consents: [],
    };
  },
};
</script>
