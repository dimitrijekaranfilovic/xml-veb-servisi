<template>
  <v-container>
    <v-flex class="text-center">
      <h1>Obrazac saglasnosti</h1>
    </v-flex>
    <saglasnost-form></saglasnost-form>
  </v-container>
</template>

<script>
import SaglasnostForm from "../components/saglasnost/SaglasnostForm.vue";

export default {
  components: { SaglasnostForm },
  data: () => ({}),
};
</script>
