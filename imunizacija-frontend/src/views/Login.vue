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
  </v-container>
</template>

<script>
import Vue from "vue";

export default Vue.extend({
  name: "Login",
  data: () => ({
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

  methods: {
    login: () => {
      console.log("aaaaa");
    },
  },
});
</script>
