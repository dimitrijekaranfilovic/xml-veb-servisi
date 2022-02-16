<template>
  <patient-vaccination-part :vaccination="vaccination" />
</template>

<script>
import vaccinationService from "@/services/VaccinationService";
import PatientVaccinationPart from "@/components/vaccination-process/PatientVaccinationPart.vue";

export default {
  name: "SingleVaccinationView",
  components: {
    PatientVaccinationPart,
  },
  data() {
    return {
      vaccination: {},
    };
  },

  mounted() {
    vaccinationService.getConsent(this.$route.params.id).then((response) => {
      this.vaccination = response.data;
    });
  },
};
</script>
