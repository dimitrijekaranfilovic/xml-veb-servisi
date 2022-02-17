<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-col cols="12" md="6">
        <v-text-field
          label="Унесите адресу електронске поште"
          v-model="email"
        />
      </v-col>
      <v-col cols="12" md="6">
        <v-btn color="primary" @click="getConsents()">Претражи</v-btn>
      </v-col>
    </v-row>
    <vaccination-table :consents="consents" />
  </v-container>
</template>

<script>
import vaccinationService from "@/services/VaccinationService";
import VaccinationTable from "@/components/vaccination-process/VaccinationTable.vue";

export default {
  name: "VaccinationView",
  components: {
    VaccinationTable,
  },
  data() {
    return {
      email: "",
      consents: [],
    };
  },
  mounted() {
    this.getConsents();
  },
  methods: {
    getConsents() {
      vaccinationService.getConsents(this.email).then((response) => {
        this.consents = response.data.saglasnosti;
      });
    },
  },
};
</script>
