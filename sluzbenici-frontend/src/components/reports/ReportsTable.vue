<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">Период од</th>
          <th class="text-left">Период до</th>
          <th class="text-left">Датум издавања</th>
          <th class="text-left">Детаљи</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, i) in reports" :key="i">
          <td>{{ item.periodOd | deRdf | moment("DD.MM.YYYY.") }}</td>
          <td>{{ item.periodDo | deRdf | moment("DD.MM.YYYY.") }}</td>
          <td>{{ item.datumIzdavanja | deRdf | moment("DD.MM.YYYY.") }}</td>
          <td>
            <v-btn
              text
              color="primary"
              plain
              :to="{ name: 'SingleReportView', params: { id: item.id } }"
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
  name: "ReportsTable",
  props: ["reports"],
};
</script>
