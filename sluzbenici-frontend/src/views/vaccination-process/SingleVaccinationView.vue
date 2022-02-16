<template>
  <div>
    <v-tabs v-model="tab">
      <v-tab>Попунио пацијент</v-tab>
      <v-tab>Попуњава здравствени радник</v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item>
        <patient-vaccination-part :pacijent="vaccination.pacijent" />
      </v-tab-item>
      <v-tab-item>
        <health-worker-vaccination-part
          :vakcinacija="vaccination.vakcinacija"
        />
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
import vaccinationService from "@/services/VaccinationService";
import PatientVaccinationPart from "@/components/vaccination-process/PatientVaccinationPart.vue";
import HealthWorkerVaccinationPart from "@/components/vaccination-process/HealthWorkerVaccinationPart.vue";

export default {
  name: "SingleVaccinationView",
  components: {
    PatientVaccinationPart,
    HealthWorkerVaccinationPart,
  },
  data() {
    return {
      vaccination: {},
      tab: null,
    };
  },

  mounted() {
    vaccinationService.getConsent(this.$route.params.id).then((response) => {
      this.vaccination = response.data;
    });
  },
};
</script>
