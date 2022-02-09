<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col lg="4" sm="12">
        <v-form ref="form" v-model="valid" lazy-validation>
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
            v-model="jmbg"
            :rules="jmbgRules"
            label="JMBG"
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
    text: "Korisnik sa tim email-om vec postoji.",
    timeout: 2000,
    valid: true,
    show: false,
    name: "",
    surname: "",
    jmbg: "",
    nameRules: [
      (v) => !!v || "Ime je obavezno",
      (v) => (v && v.length <= 20) || "Ime ne moze biti duze od 20 karaktera",
    ],
    jmbgRules: [
      (v) => !!v || "JMBG je obavezan",
      (v) =>
        (v && v.length == 13 && /[0-9]{13}/.test(v)) ||
        "JMBG mora imati tacno 13 cifara",
    ],
    surnameRules: [
      (v) => !!v || "Prezime je obavezno",
      (v) =>
        (v && v.length <= 20) || "Prezime ne moze biti duze od 20 karaktera",
    ],
    email: "",
    password: "",
    emailRules: [
      (v) => !!v || "E-mail je obavezan",
      (v) => /.+@.+\..+/.test(v) || "E-mail mora biti validan",
    ],
    passwordRules: [
      (v) => !!v || "Lozinka je obavezna",
      (v) =>
        (v && v.length >= 6 && v.length <= 12) ||
        "Lozinka mora sadrzati izmedju 6 i 12 karaktera",
    ],
  }),

  methods: {
    register() {
      let registrationRequest = {
        gradjanin: {
          name: this.name,
          surname: this.surname,
          email: this.email,
          rdfpassword: this.password,
          jmbg: this.jmbg,
        },
      };
      let that = this;
      let response = AuthenticationService.register(registrationRequest);
      response
        .then(() => {
          that.text = "Uspesna registracija.";
          that.snackbar = true;
        })
        .catch((err) => {
          if (err.message.includes("500")) {
            that.text = "Korisnik sa tim email-om vec postoji.";
          } else {
            that.text = "Morate popuniti sva polja.";
          }
          that.snackbar = true;
        });
    },
  },
};
</script>
