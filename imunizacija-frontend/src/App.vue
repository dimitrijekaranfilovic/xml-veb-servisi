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

        <h1>Имунизација</h1>
      </div>

      <v-spacer></v-spacer>

      <v-btn v-if="!loggedIn" href="#/register" text> Регистрација </v-btn>
      <v-btn v-if="!loggedIn" href="#/" text> Пријава </v-btn>
      <v-btn v-if="loggedIn" href="#/saglasnost" text> Сагласност </v-btn>
      <v-btn v-if="loggedIn" href="#/interesovanje" text> Интересовање </v-btn>
      <v-btn v-if="loggedIn" href="#/zahtev-za-sertifikat" text>
        Захтев за сертификат
      </v-btn>

      <v-btn v-if="loggedIn" v-on:click="logout" text>Одјава</v-btn>
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
