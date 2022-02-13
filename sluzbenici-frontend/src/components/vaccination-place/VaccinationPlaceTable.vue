<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Naziv punkta</th>
          <th class="text-left">Detalji</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, i) in vaccinationPlaces" :key="i">
          <td>
            {{ item.nazivPunkta }}
          </td>
          <td>
            <v-btn
              text
              color="primary"
              plain
              :to="{
                name: 'SingleVaccinationPlaceView',
                params: { id: item.id },
              }"
              >Prikazi detalje</v-btn
            >
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import vaccinationPlaceService from "@/services/VaccinationPlaceService";

export default {
  name: "VaccinationPlaceTable",
  props: ["vaccinationPlaces"],

  mounted() {},
  methods: {
    showDetails(id) {
      alert(id);
    },
  },
};
</script>
