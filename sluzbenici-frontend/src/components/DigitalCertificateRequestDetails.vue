<template>
  <v-list flat>
    <v-list-item>
      <v-list-item-content>
        <v-list-item-title
          >Ime i prezime:
          {{ request.podnosilacZahteva.licniPodaci.ime | deRdf }}
          {{ request.podnosilacZahteva.licniPodaci.prezime | deRdf }}
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
      <v-list-item-content>
        <v-list-item-title
          >Pol:
          {{
            request.podnosilacZahteva.licniPodaci.pol | deRdf
          }}</v-list-item-title
        >
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
      <v-list-item-content>
        <v-list-item-title
          >Jedinstveni matični broj građanina:
          {{
            request.podnosilacZahteva.licniPodaci.jmbg | deRdf
          }}</v-list-item-title
        >
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
      <v-list-item-content>
        <v-list-item-title
          >Broj pasoša:
          {{ request.podnosilacZahteva.brojPasosa | deRdf }}</v-list-item-title
        >
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
      <v-list-item-content>
        <v-list-item-title>Razlog za podnošenje zahteva:</v-list-item-title>
        {{ request.razlogZaPodnosenjeZahteva | deRdf }}
      </v-list-item-content>
    </v-list-item>
  </v-list>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  name: "DigitalCertificateRequestDetails",
  props: ["request"],
});
</script>
