<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="4" sm="12">
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="name"
            :rules="nameRules"
            label="Име"
            required
          ></v-text-field>

          <v-text-field
            v-model="surname"
            :rules="surnameRules"
            label="Презиме"
            required
          ></v-text-field>

          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="Адреса електронске поште"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
            :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show ? 'text' : 'password'"
            :rules="passwordRules"
            label="Лозинка"
            @click:append="show = !show"
            counter
            required
          ></v-text-field>

          <v-text-field
            v-model="confirmPassword"
            :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show ? 'text' : 'password'"
            :rules="[checkMatching]"
            label="Потврди лозинку"
            @click:append="show = !show"
            counter
            required
          ></v-text-field>

          <v-flex class="text-center">
            <v-btn
              :disabled="!valid"
              color="success"
              class="mr-4"
              @click="register"
            >
              Региструј се
            </v-btn>
          </v-flex>
        </v-form>
      </v-col>
    </v-row>
    <v-snackbar v-model="snackbar" :timeout="timeout">
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn color="blue" text v-bind="attrs" @click="snackbar = false">
          Затвори
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import AuthenticationService from "../services/AuthenticationService";

export default {
  name: "RegisterForm",
  data: () => ({
    snackbar: false,
    text: "Корисник са том адресом електронске поште већ постоји.",
    timeout: 2000,
    valid: true,
    show: false,
    name: "",
    surname: "",
    confirmPassword: "",
    nameRules: [
      (v) => !!v || "Име је обавезно",
      (v) => (v && v.trim() !== "") || "Име је обавезно",
      (v) => (v && v.length <= 20) || "Име не може бити дуже од 20 карактера",
    ],
    surnameRules: [
      (v) => !!v || "Презиме је обавезно",
      (v) => (v && v.trim() !== "") || "Презиме је обавезно",
      (v) =>
        (v && v.length <= 20) || "Презиме не може бити дуже од 20 карактера",
    ],
    email: "",
    password: "",
    emailRules: [
      (v) => !!v || "Адреса електронске поште је обавезна",
      (v) =>
        /.+@.+\..+/.test(v) || "Адреса електронске поште мора бити валидна",
    ],
    passwordRules: [
      (v) => !!v || "Лозинка је обавезна",
      (v) => (v && v.trim() !== "") || "Лозинка је обавезна",
      (v) =>
        (v && v.length >= 8) || "Лозинка мора садржати 8 или више карактера",
    ],
    confirmPasswordRules: [(v) => !!v || "Морате потврдити лозинку"],
  }),

  methods: {
    register() {
      let registrationRequest = {
        gradjanin: {
          name: this.name,
          surname: this.surname,
          email: this.email,
          rdfpassword: this.password,
        },
      };
      let that = this;
      let response = AuthenticationService.register(registrationRequest);
      response
        .then(() => {
          that.text = "Успешна регистрација";
          that.snackbar = true;
        })
        .catch((err) => {
          if (err.message.includes("500")) {
            that.text = "Корисник са том адресом електронске поште већ постоји";
          } else {
            that.text = "Морате попунити сва поља";
          }
          that.snackbar = true;
        });
    },
    checkMatching(value) {
      if (value !== this.password) {
        return "Лозинке се не подударају";
      }
      return true;
    },
  },
};
</script>
