<template>
  <v-container>
    <v-flex class="text-center">
      <h1>Исказивање интересовања за вакцинисање против COVID-19</h1>
    </v-flex>
    <interesovanje-form></interesovanje-form>
  </v-container>
</template>

<script>
import InteresovanjeForm from "@/components/interesovanje/InteresovanjeForm.vue";
export default {
  components: { InteresovanjeForm },
  name: "Interesovanje",
};
</script>

<style></style>
