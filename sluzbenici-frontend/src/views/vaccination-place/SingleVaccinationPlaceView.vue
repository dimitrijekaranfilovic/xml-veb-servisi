<template>
  <vaccination-place-details :vaccinationPlace="vaccinationPlace" />
</template>

<script>
import VaccinationPlaceDetails from "@/components/vaccination-place/VaccinationPlaceDetails.vue";
import vaccinationPlaceService from "@/services/VaccinationPlaceService";

export default {
  name: "SingleVaccinationPlaceView",
  data() {
    return {
      vaccinationPlace: {},
    };
  },
  components: {
    VaccinationPlaceDetails,
  },
  mounted() {
    vaccinationPlaceService
      .getVaccinationPlace(this.$route.params.id)
      .then((response) => {
        this.vaccinationPlace = response.data;
        //console.log(response.data);
      });
  },
};
</script>
