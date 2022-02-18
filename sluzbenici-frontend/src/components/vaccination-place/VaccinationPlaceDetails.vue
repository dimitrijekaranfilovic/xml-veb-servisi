<template>
  <v-container v-if="vaccinationPlace">
    <v-row align="center" justify="center">
      <v-col cols="12">
        <v-card elevation="2">
          <v-card-title>{{ vaccinationPlace.nazivPunkta }}</v-card-title>
          <v-divider />
          <v-card-text>
            <h2 class="table-title">Доступне вакцине</h2>
            <v-simple-table>
              <template v-slot:default>
                <thead>
                  <tr>
                    <th class="text-left">Назив вакцине</th>
                    <th class="text-left">Стање</th>
                    <th class="text-left">Ажурирање</th>
                  </tr>
                </thead>
                <tbody v-if="vaccinationPlace.dostupneVakcine">
                  <tr
                    v-for="(item, i) in vaccinationPlace.dostupneVakcine
                      .dostupneVakcine"
                    :key="i"
                  >
                    <td>
                      {{ item.nazivVakcine }}
                    </td>
                    <td>
                      {{ item.stanjeVakcine }}
                    </td>
                    <td>
                      <v-btn
                        text
                        plain
                        color="primary"
                        @click="openUpdateVaccineDialog(item)"
                      >
                        Ажурирај стање вакцине
                      </v-btn>
                    </td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
            <h2 class="table-title">Доступни термини</h2>
            <v-simple-table>
              <template v-slot:default>
                <thead>
                  <tr>
                    <th class="text-left">Датум и време</th>
                  </tr>
                </thead>
                <tbody v-if="vaccinationPlace.termini">
                  <tr
                    v-for="(item, i) in vaccinationPlace.termini.termini"
                    :key="i"
                  >
                    <td>{{ item | moment("DD.MM.YYYY. HH:mm:ss") }}</td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-card-text>
          <v-card-actions>
            <v-btn color="primary" @click="addVaccineDialog = true"
              >Додај вакцину</v-btn
            >
            <v-btn color="primary" @click="addAppointmentDialog = true"
              >Додај термин
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
      <v-col cols="12">
        <v-dialog v-model="updateVaccineDialog" max-width="600px">
          <v-card>
            <v-card-title>
              <span class="text-h5">Ажурирај вакцину</span>
            </v-card-title>
            <v-card-text>
              <v-container fluid>
                <v-text-field
                  label="Назив вакцине"
                  required
                  readonly
                  :value="updateVaccineObj.nova_vakcina.naziv_vakcine"
                ></v-text-field>
                <v-text-field
                  type="number"
                  label="Стање вакцине"
                  min="0"
                  v-model="updateVaccineObj.nova_vakcina.stanje_vakcine"
                  required
                >
                </v-text-field>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="updateVaccineDialog = false">
                Одустани
              </v-btn>
              <v-btn
                color="primary"
                text
                :disabled="updateVaccineObj.nova_vakcina.naziv_vakcine === ''"
                @click="updateVaccine()"
                :loading="loadingUpdateVaccine"
              >
                Потврди
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="addVaccineDialog" max-width="600px">
          <v-card>
            <v-card-title>
              <span class="text-h5">Додај вакцину</span>
            </v-card-title>
            <v-card-text>
              <v-container fluid>
                <v-text-field
                  label="Назив вакцине"
                  required
                  v-model="newVaccineObj.nova_vakcina.naziv_vakcine"
                ></v-text-field>
                <v-text-field
                  type="number"
                  label="Стање вакцине"
                  min="0"
                  v-model="newVaccineObj.nova_vakcina.stanje_vakcine"
                  required
                >
                </v-text-field>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="addVaccineDialog = false">
                Одустани
              </v-btn>
              <v-btn
                color="primary"
                text
                :disabled="newVaccineObj.nova_vakcina.naziv_vakcine === ''"
                @click="addVaccine()"
                :loading="loadingAddVaccine"
              >
                Потврди
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
      <v-col cols="12">
        <v-dialog v-model="addAppointmentDialog" max-width="600px">
          <v-card>
            <v-card-title>
              <span class="text-h5">Додај термин</span>
            </v-card-title>
            <v-card-text>
              <v-container fluid>
                <v-row align="center" justify="center">
                  <v-col cols="12" md="6">
                    <v-menu
                      ref="menuDate"
                      v-model="menuDate"
                      :close-on-content-click="false"
                      :return-value.sync="date"
                      transition="scale-transition"
                      offset-y
                      min-width="auto"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="newAppointmentObj.termin.datum"
                          label="Датум"
                          prepend-icon="mdi-calendar"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker
                        v-model="newAppointmentObj.termin.datum"
                        no-title
                        scrollable
                      >
                        <v-spacer></v-spacer>
                        <v-btn text color="primary" @click="menuDate = false">
                          Откажи
                        </v-btn>
                        <v-btn
                          text
                          color="primary"
                          @click="
                            $refs.menuDate.save(newAppointmentObj.termin.datum)
                          "
                        >
                          Потврди
                        </v-btn>
                      </v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col cols="12" md="6">
                    <v-menu
                      ref="menuTime"
                      v-model="menuTime"
                      :close-on-content-click="false"
                      :nudge-right="40"
                      :return-value.sync="newAppointmentObj.termin.vreme"
                      transition="scale-transition"
                      offset-y
                      max-width="290px"
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-text-field
                          v-model="newAppointmentObj.termin.vreme"
                          label="Време"
                          prepend-icon="mdi-clock-time-four-outline"
                          readonly
                          v-bind="attrs"
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-time-picker
                        v-if="menuTime"
                        v-model="newAppointmentObj.termin.vreme"
                        full-width
                        format="24hr"
                        @click:minute="
                          $refs.menuTime.save(newAppointmentObj.termin.vreme)
                        "
                      ></v-time-picker>
                    </v-menu>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="addAppointmentDialog = false">
                Одустани
              </v-btn>
              <v-btn
                color="primary"
                text
                :disabled="
                  newAppointmentObj.termin.datum === null ||
                  newAppointmentObj.termin.vreme === null
                "
                @click="addAppointment()"
                :loading="loadingAddAppintment"
              >
                Потврди
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import vaccinationPlaceService from "@/services/VaccinationPlaceService";
export default {
  name: "VaccinationPlaceDetails",
  props: ["vaccinationPlace"],
  data() {
    return {
      loadingUpdateVaccine: false,
      loadingAddAppintment: false,
      loadingAddVaccine: false,
      timeProps: {
        format: "24hr",
      },
      date: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      time: null,
      menuDate: false,
      menuTime: false,
      updateVaccineDialog: false,
      addAppointmentDialog: false,
      addVaccineDialog: false,

      updateVaccineObj: {
        nova_vakcina: {
          naziv_vakcine: "",
          stanje_vakcine: 0,
        },
      },

      newVaccineObj: {
        nova_vakcina: {
          naziv_vakcine: "",
          stanje_vakcine: 0,
        },
      },
      newAppointmentObj: {
        termin: {
          datum: null,
          vreme: null,
        },
      },
    };
  },
  computed: {
    newAppointment() {
      return {
        termin: {
          datum_vreme: `${this.newAppointmentObj.termin.datum}T${this.newAppointmentObj.termin.vreme}:00`,
        },
      };
    },
  },
  methods: {
    openUpdateVaccineDialog(item) {
      this.updateVaccineObj.nova_vakcina.naziv_vakcine = item.nazivVakcine;
      this.updateVaccineObj.nova_vakcina.stanje_vakcine = item.stanjeVakcine;
      this.updateVaccineDialog = true;
    },
    addAppointment() {
      this.loadingAddAppintment = true;
      const apppointment = this.newAppointment;
      vaccinationPlaceService
        .addAppointment(this.vaccinationPlace.id, apppointment)
        .then((_) => {
          this.loadingAddAppintment = false;
          //TODO: vidi ovo malo bolje
          this.$router.go(0);
        });
    },
    addVaccine() {
      this.loadingAddVaccine = true;
      vaccinationPlaceService
        .addVaccine(this.vaccinationPlace.id, this.newVaccineObj)
        .then((_) => {
          //TODO: vidi ovo malo bolje
          this.loadingAddVaccine = false;
          this.$router.go(0);
        });
    },
    updateVaccine() {
      this.loadingUpdateVaccine = true;
      vaccinationPlaceService
        .updateVaccine(this.vaccinationPlace.id, this.updateVaccineObj)
        .then((_) => {
          this.loadingUpdateVaccine = false;
          this.$router.go(0);
        });
    },
  },
};
</script>

<style scoped>
.table-title {
  margin-top: 15px;
}
</style>
