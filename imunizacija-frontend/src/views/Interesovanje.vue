<template>
  <v-container>
    <v-flex class="text-center">
      <h1>Iskazivanje interesovanja za vakcinisanje protiv COVID-19</h1>
    </v-flex>
    <interesovanje-form></interesovanje-form>
  </v-container>
</template>

<script>
import InteresovanjeForm from "@/components/saglasnost/InteresovanjeForm.vue";
export default {
  components: { InteresovanjeForm },
  name: "Interesovanje",
};
</script>

<style></style>
