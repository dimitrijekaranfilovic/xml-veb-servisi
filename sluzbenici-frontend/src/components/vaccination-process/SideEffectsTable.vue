<template>
  <v-row>
    <v-col cols="12">
      <h3>Привремене контраиндикације</h3>
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-left">Датум утврђивања</th>
              <th class="text-left">Дијагноза</th>
            </tr>
          </thead>
          <tbody v-if="kontraindikacije">
            <tr v-for="(item, i) in kontraindikacije" :key="i">
              <td>
                {{ item.datumUtvrdjivanja | deRdf | moment("DD.MM.YYYY.") }}
              </td>
              <td>{{ item.dijagnoza | deRdf }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "SideEffectsTable",
  props: ["kontraindikacije"],
};
</script>
