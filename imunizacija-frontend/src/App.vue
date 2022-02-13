<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <v-img
          alt="Imunizacija Logo"
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

      <v-btn v-if="!loggedIn" href="#/register" text> Registracija </v-btn>
      <v-btn v-if="!loggedIn" href="#/" text> Prijava </v-btn>
      <v-btn v-if="loggedIn" href="#/saglasnost" text> Saglasnost </v-btn>
      <v-btn v-if="loggedIn" href="#/interesobanje" text> Interesovanje </v-btn>
      <v-btn v-if="loggedIn" href="#/zahtev" text> Zahtev za sertifikat </v-btn>

      <v-btn v-if="loggedIn" v-on:click="logout" text>Odjava</v-btn>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";
import AuthenticationService from "./services/AuthenticationService";

export default Vue.extend({
  name: "App",

  data: () => ({
    loggedIn: AuthenticationService.userLoggedIn(),
  }),
  mounted: function () {
    var that = this;
    this.$root.$on("loginSuccess", function () {
      that.loggedIn = AuthenticationService.userLoggedIn();
    });
  },
  methods: {
    logout() {
      localStorage.removeItem("jwt");
      this.loggedIn = false;
      this.$router.push("/");
    },
  },
});
</script>
