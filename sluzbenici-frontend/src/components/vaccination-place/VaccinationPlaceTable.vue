<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Назив пункта</th>
          <th class="text-left">Детаљи</th>
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
              >Прикажи детаље</v-btn
            >
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
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
