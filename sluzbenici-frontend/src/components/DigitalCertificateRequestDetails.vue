<template>
  <v-list flat>
    <v-list-item>
      <v-list-item-content>
        <v-list-item-title
          >Име и презиме:
          {{ request.podnosilacZahteva.licniPodaci.ime | deRdf }}
          {{ request.podnosilacZahteva.licniPodaci.prezime | deRdf }}
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
      <v-list-item-content>
        <v-list-item-title
          >Пол:
          {{
            request.podnosilacZahteva.licniPodaci.pol | deRdf
          }}</v-list-item-title
        >
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
      <v-list-item-content>
        <v-list-item-title
          >Јединствени матични број грађанина:
          {{
            request.podnosilacZahteva.licniPodaci.jmbg | deRdf
          }}</v-list-item-title
        >
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
      <v-list-item-content>
        <v-list-item-title
          >Број пасоша:
          {{ request.podnosilacZahteva.brojPasosa | deRdf }}</v-list-item-title
        >
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
      <v-list-item-content>
        <v-list-item-title>Разлог за подношење захтева:</v-list-item-title>
        {{ request.razlogZaPodnosenjeZahteva | deRdf }}
      </v-list-item-content>
    </v-list-item>
  </v-list>
</template>

<script>
import Vue from "vue";
export default Vue.extend({
  name: "DigitalCertificateRequestDetails",
  props: ["request"],
});
</script>
