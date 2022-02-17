<template>
  <v-row>
    <v-col cols="12">
      <h3>Вакцине</h3>
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-left">Број дозе</th>
              <th class="text-left">Датум давања</th>
              <th class="text-left">Број серије</th>
              <th class="text-left">Тип</th>
              <th class="text-left">Произвођач</th>
              <th class="text-left">Екстремитет</th>
              <th class="text-left">Нуспојава</th>
            </tr>
          </thead>
          <tbody v-if="vakcine">
            <tr v-for="(item, i) in vakcine" :key="i">
              <td>{{ item.brojDoze | deRdf }}</td>
              <td>{{ item.datumDavanja | deRdf | moment("DD.MM.YYYY.") }}</td>
              <td>{{ item.brojSerije | deRdf }}</td>
              <td>{{ item.tip | deRdf }}</td>
              <td>{{ item.proizvodjac | deRdf }}</td>
              <td>{{ item.ekstremitet | deRdf }}</td>
              <td>{{ item.nuspojava | deRdf }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "GivenVaccinesTable",
  props: ["vakcine"],
};
</script>
