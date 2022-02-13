<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="4" sm="12">
        <v-form ref="form" v-model="valid">
          <v-text-field
            v-model="name"
            :rules="nameRules"
            label="Ime"
            required
          ></v-text-field>

          <v-text-field
            v-model="surname"
            :rules="surnameRules"
            label="Prezime"
            required
          ></v-text-field>

          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="E-mail"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
            :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show ? 'text' : 'password'"
            :rules="passwordRules"
            label="Lozinka"
            @click:append="show = !show"
            counter
            required
          ></v-text-field>

          <v-text-field
            v-model="confirmPassword"
            :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show ? 'text' : 'password'"
            :rules="[checkMatching]"
            label="Potvrdi lozinku"
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
              Registruj se
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
import AuthenticationService from "../services/AuthenticationService";

export default {
  name: "RegisterForm",
  data: () => ({
    snackbar: false,
    text: "Korisnik sa tim email-om već postoji.",
    timeout: 2000,
    valid: true,
    show: false,
    name: "",
    surname: "",
    confirmPassword: "",
    nameRules: [
      (v) => !!v || "Ime je obavezno",
      (v) => (v && v.trim() !== "") || "Ime je obavezno",
      (v) => (v && v.length <= 20) || "Ime ne može biti duže od 20 karaktera",
    ],
    surnameRules: [
      (v) => !!v || "Prezime je obavezno",
      (v) => (v && v.trim() !== "") || "Prezime je obavezno",
      (v) =>
        (v && v.length <= 20) || "Prezime ne može biti duže od 20 karaktera",
    ],
    email: "",
    password: "",
    emailRules: [
      (v) => !!v || "E-mail je obavezan",
      (v) => /.+@.+\..+/.test(v) || "E-mail mora biti validan",
    ],
    passwordRules: [
      (v) => !!v || "Lozinka je obavezna",
      (v) => (v && v.trim() !== "") || "Lozinka je obavezna",
      (v) =>
        (v && v.length >= 6) || "Lozinka mora sadržati 6 ili više karaktera",
    ],
    confirmPasswordRules: [(v) => !!v || "Morate potvrditi lozinku"],
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
          that.text = "Uspešna registracija.";
          that.snackbar = true;
        })
        .catch((err) => {
          if (err.message.includes("500")) {
            that.text = "Korisnik sa tim email-om već postoji.";
          } else {
            that.text = "Morate popuniti sva polja.";
          }
          that.snackbar = true;
        });
    },
    checkMatching(value) {
      if (value !== this.password) {
        return "Lozinke se ne podudaraju";
      }
      return true;
    },
  },
};
</script>
