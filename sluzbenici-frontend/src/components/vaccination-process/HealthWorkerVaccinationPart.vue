<template>
  <v-container>
    <v-row align="center" justify="start" class="btn-row">
      <v-col cols="12">
        <v-card elevation="0">
          <v-card-actions>
            <v-btn color="primary" @click="addDoctorDialog = true"
              >Унеси податке о лекару и установи</v-btn
            >
            <v-btn color="primary" @click="addVaccineDialog = true"
              >Унеси нову дозу</v-btn
            >
            <v-btn color="primary" @click="sideEffectsDialog = true">
              Унеси привремену контраиндикацију
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-row>
        <v-col cols="12" md="4" v-if="vakcinacija.zdravstvenaUstanova">
          <v-subheader>Назив установе</v-subheader>
          <span>{{ vakcinacija.zdravstvenaUstanova.naziv }}</span>
        </v-col>
        <v-col cols="12" md="4" v-if="vakcinacija.zdravstvenaUstanova">
          <v-subheader>Пункт</v-subheader>
          <span>{{ vakcinacija.zdravstvenaUstanova.punkt }}</span>
        </v-col>
        <v-col cols="12" md="4" v-if="vakcinacija.lekar">
          <v-subheader>Лекар</v-subheader>
          <span>
            {{ vakcinacija.lekar.ime }} {{ vakcinacija.lekar.prezime }}
            <span v-if="vakcinacija.lekar.telefon">
              ,{{ vakcinacija.lekar.telefon.brojFiksnog }}
              {{ vakcinacija.lekar.telefon.brojMobilnog }}
            </span>
          </span>
        </v-col>
      </v-row>
    </v-row>
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
            <tbody v-if="vakcinacija.vakcine.vakcine">
              <tr v-for="(item, i) in vakcinacija.vakcine.vakcine" :key="i">
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
            <tbody
              v-if="vakcinacija.privremeneKontraindikacije.kontraindikacije"
            >
              <tr
                v-for="(item, i) in vakcinacija.privremeneKontraindikacije
                  .kontraindikacije"
                :key="i"
              >
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

    <v-row>
      <h4>Одлука комисије за трајне контраиндикације</h4>
      <v-card-text>
        <p v-if="vakcinacija.odlukaKomisije.value">Одлука донешена</p>
        <p v-else>Нема одлуке</p>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary">Промени одлуку</v-btn>
      </v-card-actions>
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
                    v-model="firstSetupObj.kreiranje.zdravstvena_ustanova.naziv"
                  ></v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Пункт"
                    v-model="firstSetupObj.kreiranje.zdravstvena_ustanova.punkt"
                  />
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Име лекара"
                    v-model="firstSetupObj.kreiranje.lekar.ime"
                  />
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Презиме лекара"
                    v-model="firstSetupObj.kreiranje.lekar.prezime"
                  />
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Број мобилног"
                    v-model="
                      firstSetupObj.kreiranje.lekar.telefon.broj_mobilnog
                    "
                  />
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Број фискног"
                    v-model="firstSetupObj.kreiranje.lekar.telefon.broj_fiksnog"
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
      <v-dialog v-model="sideEffectsDialog" max-width="600px">
        <v-card>
          <v-card-title>
            <span class="text-h5">Унеси привремену контраиндикацију</span>
          </v-card-title>
          <v-card-text>
            <v-container fluid>
              <v-row>
                <v-col cols="12">
                  <v-text-field label="Датум утврђивања" />
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12">
                  <v-text-field label="Дијагноза" />
                </v-col>
              </v-row>
            </v-container>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="sideEffectsDialog = false"
                >Одустани</v-btn
              >
              <v-btn color="primary">Додај</v-btn>
            </v-card-actions>
          </v-card-text>
        </v-card>
      </v-dialog>
    </div>
  </v-container>
</template>

<script>
//TODO: dodaj privremene kontraindikacije
export default {
  name: "HealthWorkerVaccinationPart",
  props: ["vakcinacija"],
  data() {
    return {
      addDoctorDialog: false,
      addVaccineDialog: false,
      sideEffectsDialog: false,
      firstSetupObj: {
        kreiranje: {
          lekar: {
            telefon: {
              broj_fiksnog: "",
              broj_mobilnog: "",
            },
            ime: "",
            prezime: "",
          },
          zdravstvena_ustanova: {
            naziv: "",
            punkt: "",
          },
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

      sideEffectObj: {
        kontraindikacija: {
          datum_utvrdjivanja: null,
          dijagnoza: "",
        },
      },
    };
  },
  computed: {
    formValid() {
      return (
        this.firstSetupObj.kreiranje.lekar.telefon.broj_fiksnog !== "" &&
        this.firstSetupObj.kreiranje.lekar.telefon.broj_mobilnog !== "" &&
        this.firstSetupObj.kreiranje.lekar.ime !== "" &&
        this.firstSetupObj.kreiranje.lekar.prezime !== "" &&
        this.firstSetupObj.kreiranje.zdravstvena_ustanova.naziv !== "" &&
        this.firstSetupObj.kreiranje.zdravstvena_ustanova.punkt !== ""
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
