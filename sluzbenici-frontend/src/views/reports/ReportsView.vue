<template>
  <v-container>
    <v-row align="start">
      <v-col>
        <v-dialog v-model="dialog" max-width="600px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary" v-bind="attrs" v-on="on">
              Генериши извештај
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="text-h5">Генериши извештај</span>
            </v-card-title>
            <v-card-text>
              <v-container fluid>
                <v-row align="center" justify="center">
                  <v-col cols="12" md="6">
                    <v-menu
                      ref="menu1"
                      v-model="menuDate1"
                      :close-on-content-click="false"
                      :return-value.sync="date1"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          label="Од"
                          prepend-icon="mdi-calendar"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                          v-model="
                            newReportObj.zahtev_za_kreiranje_izvestaja.period_od
                          "
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        no-title
                        scrollable
                        v-model="
                          newReportObj.zahtev_za_kreiranje_izvestaja.period_od
                        "
                      >
                        <v-spacer></v-spacer>
                        <v-btn text color="primary" @click="menuDate1 = false">
                          Откажи
                        </v-btn>
                        <v-btn
                          text
                          color="primary"
                          @click="
                            $refs.menu1.save(
                              newReportObj.zahtev_za_kreiranje_izvestaja
                                .period_od
                            )
                          "
                        >
                          Потврди
                        </v-btn>
                      </v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-menu
                      ref="menu2"
                      v-model="menuDate2"
                      :close-on-content-click="false"
                      :return-value.sync="date2"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          label="До"
                          prepend-icon="mdi-calendar"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                          v-model="
                            newReportObj.zahtev_za_kreiranje_izvestaja.period_do
                          "
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        no-title
                        scrollable
                        v-model="
                          newReportObj.zahtev_za_kreiranje_izvestaja.period_do
                        "
                      >
                        <v-spacer></v-spacer>
                        <v-btn text color="primary" @click="menuDate2 = false">
                          Откажи
                        </v-btn>
                        <v-btn
                          text
                          color="primary"
                          @click="
                            $refs.menu2.save(
                              newReportObj.zahtev_za_kreiranje_izvestaja
                                .period_do
                            )
                          "
                        >
                          Потврди
                        </v-btn>
                      </v-date-picker>
                    </v-menu>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="dialog = false">
                Одустани
              </v-btn>
              <v-btn
                color="primary"
                text
                @click="generateReport()"
                :loading="reportBeingGenerated"
                :disabled="
                  newReportObj.zahtev_za_kreiranje_izvestaja.period_od ===
                    null ||
                  newReportObj.zahtev_za_kreiranje_izvestaja.period_do === null
                "
              >
                Потврди
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
    <reports-table :reports="reports" />
    <v-snackbar v-model="snackbar" :timeout="5000">
      {{ message }}

      <template v-slot:action="{ attrs }">
        <v-btn color="primary" text v-bind="attrs" @click="snackbar = false">
          Затвори
        </v-btn>
      </template>
    </v-snackbar>
  </v-container>
</template>

<script>
import ReportsTable from "@/components/reports/ReportsTable.vue";
import reportsService from "@/services/ReportsService";

export default {
  name: "ReportsView",
  data() {
    return {
      reportBeingGenerated: false,
      message: "",
      snackbar: false,
      date1: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      date2: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      dialog: false,
      menuDate1: false,
      menuDate2: false,
      reports: [],
      newReportObj: {
        zahtev_za_kreiranje_izvestaja: {
          period_od: null,
          period_do: null,
        },
      },
    };
  },
  components: {
    ReportsTable,
  },
  mounted() {
    reportsService.getReports().then((response) => {
      this.reports = response.data.izvestajiOImunizaciji;
    });
  },
  methods: {
    generateReport() {
      this.reportBeingGenerated = true;
      reportsService.generateReport(this.newReportObj).then((response) => {
        const izvestaj = response.data;
        this.reports.push(izvestaj);
        this.dialog = false;
        this.reportBeingGenerated = false;
        this.message = "Report successfully generated.";
        this.snackbar = true;
      });
    },
  },
};
</script>
