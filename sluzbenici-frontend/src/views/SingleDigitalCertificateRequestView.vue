<template>
  <v-container fluid>
    <v-row align="center" justify="center">
      <v-col>
        <v-card elevation="2" v-if="request.id">
          <v-card-title>Detalji o zahtevu za digitalni sertifikat</v-card-title>
          <v-card-subtitle
            >Identifikator zahteva: {{ request.id }}. <br />
            Podneto u {{ request.mesto | deRdf }}, dana
            {{ request.datum | deRdf | moment("DD.MM.YYYY.") }}
            godine.</v-card-subtitle
          >
          <v-card-text>
            <digital-certificate-request-details
              v-bind:request="request"
            ></digital-certificate-request-details>
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" text @click="issueDigitalCertificate"
              >Izdaj sertifikat</v-btn
            >
            <v-dialog
              v-model="dialog"
              max-width="600px"
              @click:outside="$refs.form.resetValidation()"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn text color="error" v-bind="attrs" v-on="on">
                  Odbij zahtev
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="text-h5">Razlog za odbijanje zahteva</span>
                </v-card-title>
                <v-card-text>
                  <v-form ref="form" v-model="valid">
                    <v-container fluid>
                      <v-textarea
                        v-model="reason.model"
                        :rules="reason.rules"
                        label="Razlog za odbijanje zahteva"
                        auto-grow
                        hint="Detaljno obrazložite zašto odbijate zahtev za izdavanje Digitalnog sertifikata."
                        required
                      ></v-textarea>
                    </v-container>
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="primary" text @click="toggleDialog(false)">
                    Odustani
                  </v-btn>
                  <v-btn
                    :disabled="!valid"
                    color="error"
                    text
                    @click="rejectRequest"
                  >
                    Odbij zahtev
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import Vue from "vue";
import DigitalCertificateRequestDetails from "@/components/DigitalCertificateRequestDetails.vue";
import digitalCertificateRequestService from "@/services/DigitalCertificateRequestService";

export default Vue.extend({
  components: { DigitalCertificateRequestDetails },
  name: "SingleDigitalCertificateRequestView",
  data: () => ({
    request: {},
    valid: true,
    reason: {
      model: "",
      rules: [(v) => !!v || "Razlog odbijanja zazhteva je obavezno polje."],
    },
    dialog: false,
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
      await digitalCertificateRequestService.reject(this.request.id, {
        OdbijZahtevZaSertifikatRequest: {
          razlog: this.reason.model,
        },
      });
      this.toggleDialog(false);
      this.navigateAllRequests();
    },
    navigateAllRequests() {
      this.$router.push({ name: "DigitalCertifikateRequestsView" });
    },
    toggleDialog(state) {
      this.dialog = state;
      this.$refs.form.resetValidation();
    },
  },
});
</script>
