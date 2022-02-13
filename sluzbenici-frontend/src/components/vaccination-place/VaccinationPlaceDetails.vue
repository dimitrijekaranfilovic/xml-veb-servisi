<template>
  <div v-if="vaccinationPlace">{{ vaccinationPlace.id }}</div>
</template>

<script>
export default {
  name: "VaccinationPlaceDetails",
  props: ["vaccinationPlace"],
};
</script>
