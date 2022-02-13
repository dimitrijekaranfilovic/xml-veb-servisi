<template>
  <v-container>
    <v-row align="start">
      <v-col>
        <v-btn color="primary"> Dodaj punkt </v-btn>
      </v-col>
    </v-row>
    <vaccination-place-table />
  </v-container>
</template>

<script>
import VaccinationPlaceTable from "@/components/vaccination-place/VaccinationPlaceTable.vue";
export default {
  name: "VaccinationPlaceManagement",
  components: {
    VaccinationPlaceTable,
  },
};
</script>
