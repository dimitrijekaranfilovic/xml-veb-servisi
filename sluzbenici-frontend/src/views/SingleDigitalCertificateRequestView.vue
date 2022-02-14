<template>
  <v-container fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card elevation="2" v-if="request.id">
          <v-card-title>Детаљи о захтеву за дигитални сертификат</v-card-title>
          <v-card-subtitle
            >Идентификатор захтева: {{ request.id }}. <br />
            Поднето у {{ request.mesto | deRdf }}, дана
            {{ request.datum | deRdf | moment("DD.MM.YYYY.") }}
            године.</v-card-subtitle
          >
          <v-card-text>
            <digital-certificate-request-details
              v-bind:request="request"
            ></digital-certificate-request-details>
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" text @click="issueDigitalCertificate"
              >Издај сертификат</v-btn
            >
            <v-dialog
              v-model="dialog"
              max-width="600px"
              @click:outside="$refs.form.resetValidation()"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn text color="error" v-bind="attrs" v-on="on">
                  Одбиј захтев
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="text-h5">Разлог за одбијање захтева</span>
                </v-card-title>
                <v-card-text>
                  <v-form ref="form" v-model="valid">
                    <v-container fluid>
                      <v-textarea
                        v-model="reason.model"
                        :rules="reason.rules"
                        label="Разлог за одбијање захтева"
                        auto-grow
                        hint="Детаљно образложите разлог за одбијање захтева за дигитални сертификат."
                        required
                      ></v-textarea>
                    </v-container>
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="primary" text @click="toggleDialog(false)">
                    Одустани
                  </v-btn>
                  <v-btn
                    :disabled="!valid"
                    color="error"
                    text
                    @click="rejectRequest"
                  >
                    Одбиј захтев
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    <v-snackbar v-model="snackbar" :timeout="5000">
      {{ errorMessage }}

      <template v-slot:action="{ attrs }">
        <v-btn color="red" text v-bind="attrs" @click="snackbar = false">
          Затвори
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import DigitalCertificateRequestDetails from "@/components/DigitalCertificateRequestDetails.vue";
import digitalCertificateRequestService from "@/services/DigitalCertificateRequestService";
import Vue from "vue";

export default Vue.extend({
  components: { DigitalCertificateRequestDetails },
  name: "SingleDigitalCertificateRequestView",
  data: () => ({
    request: {},
    valid: true,
    reason: {
      model: "",
      rules: [(v) => !!v || "Разлог одбијања захтева је обавезно поље."],
    },
    dialog: false,
    snackbar: false,
    errorMessage: "",
  }),
  async mounted() {
    const response = await digitalCertificateRequestService.readOne(
      this.$route.params.id
    );
    this.request = response.data;
  },
  methods: {
    async issueDigitalCertificate() {
      await digitalCertificateRequestService.approve(this.request.id);
      this.navigateAllRequests();
    },
    async rejectRequest() {
      try {
        await digitalCertificateRequestService.reject(this.request.id, {
          OdbijZahtevZaSertifikatRequest: {
            razlog: this.reason.model,
          },
        });
        this.navigateAllRequests();
      } catch (error) {
        this.handleError(error);
      }
      this.toggleDialog(false);
    },
    navigateAllRequests() {
      this.$router.push({ name: "DigitalCertifikateRequestsView" });
    },
    toggleDialog(state) {
      this.dialog = state;
      this.$refs.form.resetValidation();
    },
    handleError(error) {
      this.errorMessage = error.response.data.message;
      this.snackbar = true;
    },
  },
});
</script>
