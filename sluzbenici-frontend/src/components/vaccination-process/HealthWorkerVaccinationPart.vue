<template>
  <v-container>
    <v-row align="center" justify="start" class="btn-row">
      <v-col cols="12" md="4">
        <v-btn color="primary" @click="addDoctorDialog = true"
          >Унеси податке о лекару и установи</v-btn
        >
      </v-col>
      <v-col cols="12" md="4">
        <v-btn color="primary" @click="addVaccineDialog = true"
          >Унеси нову дозу</v-btn
        >
      </v-col>
    </v-row>
    <v-row v-if="vakcinacija">
      <v-row v-if="vakcinacija.zdravstvenaUstanova">
        <v-col cols="12" md="6">
          <v-subheader>Назив</v-subheader>
          <span>{{ vakcinacija.zdravstvenaUstanova.naziv }}</span>
        </v-col>
        <v-col cols="12" md="6">
          <v-subheader>Пункт</v-subheader>
          <span>{{ vakcinacija.zdravstvenaUstanova.punkt }}</span>
        </v-col>
      </v-row>
      <v-row v-if="vakcinacija.lekar">
        <v-col cols="12">
          <v-subheader>Име, презиме и бр.телефона лекара</v-subheader>
          <span
            >{{ vakcinacija.lekar.ime }} {{ vakcinacija.lekar.prezime }},
            {{ vakcinacija.lekar.telefon.brojFiksnog }}
            {{ vakcinacija.lekar.telefon.brojMobilnog }}</span
          >
        </v-col>
      </v-row>
    </v-row>
    <v-row>
      <v-col>
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
            <tbody v-if="vakcinacija">
              <!-- <tr v-for="(item, i) in vaccinationPlaces" :key="i">
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
        </tr> -->
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
    <div id="dialogs">
      <v-dialog v-model="addDoctorDialog" max-width="600px">
        <v-card>
          <v-card-title>
            <span class="text-h5">Унеси податке о лекару и установи</span>
          </v-card-title>
          <v-card-text>
            <v-container fluid>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Назив установе"
                    v-model="buildingObj.zdravstvena_ustanova.naziv"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Пункт"
                    v-model="buildingObj.zdravstvena_ustanova.punkt"
                  />
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Име лекара"
                    v-model="newDoctorObj.lekar.ime"
                  />
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Презиме лекара"
                    v-model="newDoctorObj.lekar.prezime"
                  />
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Број мобилног"
                    v-model="newDoctorObj.lekar.telefon.broj_mobilnog"
                  />
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Број фискног"
                    v-model="newDoctorObj.lekar.telefon.broj_fiksnog"
                  />
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="addDoctorDialog = false">
              Одустани
            </v-btn>
            <v-btn color="primary" text :disabled="!formValid"> Потврди </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-dialog v-model="addVaccineDialog" max-width="600px">
        <v-card>
          <v-card-title>
            <span class="text-h5">Унеси нову дозу</span>
          </v-card-title>
          <v-card-text>
            <v-container fluid>
              <!-- 
                <th class="text-left">Датум давања</th>
                <th class="text-left">Екстремитет</th>
                <th class="text-left">Нуспојава</th> -->
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    type="number"
                    label="Број дозе"
                    min="1"
                    v-model="newVaccineObj.vakcina.broj_doze"
                  />
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Број серије"
                    v-model="newVaccineObj.vakcina.broj_serije"
                  />
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Тип"
                    v-model="newVaccineObj.vakcina.tip"
                  />
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Произвођач"
                    v-model="newVaccineObj.vakcina.proizvodjac"
                  />
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Екстремитет"
                    v-model="newVaccineObj.vakcina.ekstremitet"
                  />
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Нуспојава"
                    v-model="newVaccineObj.vakcina.nuspojava"
                  />
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="addVaccineDialog = false">
              Одустани
            </v-btn>
            <v-btn color="primary" text :disabled="!formValid2">
              Потврди
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "HealthWorkerVaccinationPart",
  props: ["vakcinacija"],
  data() {
    return {
      addDoctorDialog: false,
      addVaccineDialog: false,
      newDoctorObj: {
        lekar: {
          telefon: {
            broj_fiksnog: "",
            broj_mobilnog: "",
          },
          ime: "",
          prezime: "",
        },
      },
      buildingObj: {
        zdravstvena_ustanova: {
          naziv: "",
          punkt: "",
        },
      },
      newVaccineObj: {
        vakcina: {
          ekstremitet: "",
          nuspojava: "",
          tip: "",
          proizvodjac: "",
          broj_doze: 1,
          broj_serije: "",
        },
      },
    };
  },
  computed: {
    formValid() {
      return (
        this.newDoctorObj.lekar.telefon.broj_fiksnog !== "" &&
        this.newDoctorObj.lekar.telefon.broj_mobilnog !== "" &&
        this.newDoctorObj.lekar.ime !== "" &&
        this.newDoctorObj.lekar.prezime !== "" &&
        this.buildingObj.zdravstvena_ustanova.naziv !== "" &&
        this.buildingObj.zdravstvena_ustanova.punkt !== ""
      );
    },
    formValid2() {
      return (
        this.newVaccineObj.vakcina.ekstremitet !== "" &&
        this.newVaccineObj.vakcina.nuspojava !== "" &&
        this.newVaccineObj.vakcina.tip !== "" &&
        this.newVaccineObj.vakcina.proizvodjac !== "" &&
        this.newVaccineObj.vakcina.broj_serije !== ""
      );
    },
  },
};
</script>

<style scoped>
.btn-row {
  margin-top: 5px;
}
</style>
