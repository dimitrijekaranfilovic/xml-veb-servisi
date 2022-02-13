<template>
  <v-app-bar app color="primary" dark>
    <div class="d-flex align-center">
      <v-img
        alt="Vuetify Logo"
        class="shrink mr-2"
        contain
        src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
        transition="scale-transition"
        width="40"
      />
      <span class="title"> SLUŽBENICI </span>
    </div>

    <v-spacer></v-spacer>

    <v-btn text :to="{ name: 'DigitalCertifikateRequestsView' }">
      Zahtevi za digitalni sertifikat
    </v-btn>
    <v-btn text :to="{ name: 'VaccinationPlaceManagementView' }">
      Punktovi
    </v-btn>
  </v-app-bar>
</template>

<script>
export default {
  name: "Navbar",
};
</script>

<style scoped>
.title {
  font-family: "Roboto";
}
</style>
