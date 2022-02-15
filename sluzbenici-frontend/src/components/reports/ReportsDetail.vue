<template>
  <v-container v-if="report">
    <v-row align="center" justify="center">
      <v-card elevation="2">
        <v-card-title>Детаљи о извештају #{{ report.id }}</v-card-title>
        <v-card-subtitle v-if="report.periodOd">
          {{ report.periodOd | deRdf | moment("DD.MM.YYYY") }}-{{
            report.periodDo | deRdf | moment("DD.MM.YYYY")
          }}, издат {{ report.datumIzdavanja | deRdf | moment("DD.MM.YYYY.") }}
        </v-card-subtitle>
        <v-card-text>
          <v-row align="center" justify="center">
            <v-col cols="12" md="4">
              <v-subheader>Поднето докумената о интересовању</v-subheader>
              <span class="number-span">0</span>
            </v-col>
            <v-col cols="12" md="4">
              <v-subheader>Примљено захтева за сертификат</v-subheader>
              <span class="number-span">0</span>
            </v-col>
            <v-col cols="12" md="4">
              <v-subheader>Издато захтева за сертификат</v-subheader>
              <span class="number-span">0</span>
            </v-col>
          </v-row>
          <v-divider />
          <h3 class="header">Расподела по дозама</h3>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">Редни број дозе</th>
                  <th class="text-left">Број датих доза</th>
                </tr>
              </thead>
              <tbody v-if="report.dateDozeVakcina">
                <tr
                  v-for="(item, i) in report.dateDozeVakcina.dateDoze"
                  :key="i"
                >
                  <td>{{ item.redniBroj | deRdf }}</td>
                  <td>{{ item.brojDatihDoza | deRdf }}</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>

          <h3 class="header">Расподела по произвођачима</h3>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">Назив вакцине</th>
                  <th class="text-left">Број датих доза</th>
                </tr>
              </thead>
              <tbody v-if="report.raspodelaPoProizvodjacima">
                <tr
                  v-for="(item, i) in report.raspodelaPoProizvodjacima.stavke"
                  :key="i"
                >
                  <td>{{ item.proizvodjac | deRdf }}</td>
                  <td>{{ item.brojDoza | deRdf }}</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" text plain>Види пдф</v-btn>
          <v-btn color="primary">Преузми извештај</v-btn>
        </v-card-actions>
      </v-card>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "ReportsDetail",
  props: ["report"],
};
</script>

<style scoped>
.number-span {
  margin-left: 17px;
}

.header {
  margin-top: 10px;
}
</style>
