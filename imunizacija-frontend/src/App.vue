<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="mr-2"
          contain
          src="./assets/logo.png"
          transition="scale-transition"
          width="60"
          height="70"
        />

        <h1>Imunizacija</h1>
      </div>

      <v-spacer></v-spacer>

      <v-btn href="#/register" text> Registracija </v-btn>
      <v-btn href="#/" text> Prijava </v-btn>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "App",

  data: () => ({
    //
  }),
});
</script>
