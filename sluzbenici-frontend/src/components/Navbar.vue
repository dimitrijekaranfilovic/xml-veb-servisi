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
      <span class="title"> СЛУЖБЕНИЦИ </span>
    </div>

    <v-spacer></v-spacer>

    <v-btn text :to="{ name: 'DigitalCertifikateRequestsView' }">
      Захтеви за дигитални сертификат
    </v-btn>
    <v-btn text :to="{ name: 'VaccinationPlaceManagementView' }">
      Пунктови
    </v-btn>
    <v-btn text :to="{ name: 'ReportsView' }"> Извештаји </v-btn>
    <v-btn text :to="{ name: 'VaccinationView' }"> Вакцинација </v-btn>
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
