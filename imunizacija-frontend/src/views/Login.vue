<template>
  <v-container>
    <v-row justify="center">
      <v-col lg="4" sm="12">
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="E-mail"
            required
          ></v-text-field>
          <v-text-field
            v-model="password"
            :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="passwordRules"
            :type="show ? 'text' : 'password'"
            name="input-10-1"
            label="Unesite vasu lozinku"
            counter
            @click:append="show = !show"
          ></v-text-field>
          <v-flex class="text-center">
            <v-btn
              :disabled="!valid"
              color="success"
              class="align-center"
              @click="login"
            >
              Prijava na sistem
            </v-btn>
          </v-flex>
        </v-form>
      </v-col>
    </v-row>
    <v-snackbar v-model="snackbar" :timeout="timeout">
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn color="blue" text v-bind="attrs" @click="snackbar = false">
          Zatvori
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import Vue from "vue";
import AuthenticationService from "../services/AuthenticationService";

export default Vue.extend({
  name: "Login",
  data: () => ({
    snackbar: false,
    text: "Pogresan username/password",
    timeout: 2000,
    valid: true,
    show: false,
    email: "",
    emailRules: [
      (v) => !!v || "E-mail je obavezan",
      (v) => /.+@.+\..+/.test(v) || "E-mail mora biti validan",
    ],
    password: "",
    passwordRules: [(p) => !!p || "Lozinka je obavezna"],
  }),
  mounted: function () {
    if (AuthenticationService.userLoggedIn()) {
      this.$router.push("/saglasnost");
    }
  },
  methods: {
    login() {
      let that = this;
      let loginRequest = {
        auth_request: {
          email: that.email,
          password: that.password,
        },
      };
      let loginResponse = AuthenticationService.login(loginRequest);
      loginResponse
        .then((res) => {
          localStorage.setItem("jwt", res.data.jwt);
          that.$root.$emit("loginSuccess");
          that.$router.push("/saglasnost");
        })
        .catch((err) => {
          that.snackbar = true;
        });
    },
  },
});
</script>
