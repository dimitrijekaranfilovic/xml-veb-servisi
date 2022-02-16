<template>
  <v-container>
    <v-row align="center" justify="start" class="btn-row">
      <v-col cols="12">
        <v-card elevation="0">
          <v-card-actions>
            <v-btn
              color="primary"
              @click="addDoctorDialog = true"
              v-if="
                !vakcinacija.zdravstvenaUstanova.naziv || !vakcinacija.lekar.ime
              "
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
          <span class="data-span">{{
            vakcinacija.zdravstvenaUstanova.naziv | deRdf
          }}</span>
        </v-col>
        <v-col cols="12" md="4" v-if="vakcinacija.zdravstvenaUstanova">
          <v-subheader>Пункт</v-subheader>
          <span class="data-span">{{
            vakcinacija.zdravstvenaUstanova.punkt | deRdf
          }}</span>
        </v-col>
        <v-col cols="12" md="4" v-if="vakcinacija.lekar">
          <v-subheader>Лекар</v-subheader>
          <span class="data-span">
            {{ vakcinacija.lekar.ime | deRdf }}
            {{ vakcinacija.lekar.prezime | deRdf }},
            {{ vakcinacija.lekar.telefon.brojFiksnog | deRdf }}
            {{ vakcinacija.lekar.telefon.brojMobilnog | deRdf }}
          </span>
        </v-col>
      </v-row>
    </v-row>
    <given-vaccines-table :vakcine="vakcinacija.vakcine.vakcine" />
    <side-effects-table
      :kontraindikacije="
        vakcinacija.privremeneKontraindikacije.kontraindikacije
      "
    />
    <v-row>
      <h4>Одлука комисије за трајне контраиндикације</h4>
      <v-card-text>
        <p v-if="vakcinacija.odlukaKomisije.value">Одлука донешена</p>
        <p v-else>Нема одлуке</p>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" @click="changeDecision()">Промени одлуку</v-btn>
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
                    v-model="
                      firstSetupObj.podaci_o_lekaru_ustanovi
                        .zdravstvena_ustanova.naziv
                    "
                  ></v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Пункт"
                    type="number"
                    min="0"
                    v-model="
                      firstSetupObj.podaci_o_lekaru_ustanovi
                        .zdravstvena_ustanova.punkt
                    "
                  />
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Име лекара"
                    v-model="firstSetupObj.podaci_o_lekaru_ustanovi.lekar.ime"
                  />
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Презиме лекара"
                    v-model="
                      firstSetupObj.podaci_o_lekaru_ustanovi.lekar.prezime
                    "
                  />
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Број мобилног"
                    v-model="
                      firstSetupObj.podaci_o_lekaru_ustanovi.lekar.telefon
                        .broj_mobilnog
                    "
                  />
                </v-col>
                <v-col cols="12" md="6">
                  <v-text-field
                    label="Број фиксног"
                    v-model="
                      firstSetupObj.podaci_o_lekaru_ustanovi.lekar.telefon
                        .broj_fiksnog
                    "
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
            <v-btn
              color="primary"
              text
              :disabled="!formValid"
              @click="addDoctorBuilding()"
            >
              Потврди
            </v-btn>
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
                        label="Датум утврђивања"
                        prepend-icon="mdi-calendar"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        v-model="
                          sideEffectObj.kontraindikacija.datum_utvrdjivanja
                        "
                      ></v-text-field>
                    </template>
                    <v-date-picker
                      no-title
                      scrollable
                      v-model="
                        sideEffectObj.kontraindikacija.datum_utvrdjivanja
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
                            sideEffectObj.kontraindikacija.datum_utvrdjivanja
                          )
                        "
                      >
                        Потврди
                      </v-btn>
                    </v-date-picker>
                  </v-menu>
                </v-col>
              </v-row>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    label="Дијагноза"
                    v-model="sideEffectObj.kontraindikacija.dijagnoza"
                  />
                </v-col>
              </v-row>
            </v-container>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="sideEffectsDialog = false"
                >Одустани</v-btn
              >
              <v-btn
                color="primary"
                :disabled="
                  sideEffectObj.kontraindikacija.dijagnoza === '' ||
                  sideEffectObj.kontraindikacija.datum_utvrdjivanja === null
                "
                @click="addSideEffect()"
                >Додај</v-btn
              >
            </v-card-actions>
          </v-card-text>
        </v-card>
      </v-dialog>
    </div>
  </v-container>
</template>

<script>
import vaccinationService from "@/services/VaccinationService";
import GivenVaccinesTable from "@/components/vaccination-process/GivenVaccinesTable.vue";
import SideEffectsTable from "@/components/vaccination-process/SideEffectsTable.vue";

export default {
  name: "HealthWorkerVaccinationPart",
  components: {
    GivenVaccinesTable,
    SideEffectsTable,
  },
  props: ["vakcinacija"],
  data() {
    return {
      menuDate1: false,
      date1: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
      addDoctorDialog: false,
      addVaccineDialog: false,
      sideEffectsDialog: false,
      firstSetupObj: {
        podaci_o_lekaru_ustanovi: {
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
            punkt: 0,
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
  methods: {
    addDoctorBuilding() {
      vaccinationService
        .createDoctorAndBuilding(this.$route.params.id, this.firstSetupObj)
        .then((_) => {
          //TODO: vidi ovo malo bolje i ovo dvoje ispod
          this.$router.go(0);
        });
    },
    changeDecision() {
      vaccinationService.changeDecision(this.$route.params.id).then((_) => {
        this.$router.go(0);
      });
    },
    addSideEffect() {
      vaccinationService
        .addSideEffect(this.$route.params.id, this.sideEffectObj)
        .then((_) => {
          this.$router.go(0);
        });
    },
  },
  computed: {
    formValid() {
      return (
        this.firstSetupObj.podaci_o_lekaru_ustanovi.lekar.telefon
          .broj_fiksnog !== "" &&
        this.firstSetupObj.podaci_o_lekaru_ustanovi.lekar.telefon
          .broj_mobilnog !== "" &&
        this.firstSetupObj.podaci_o_lekaru_ustanovi.lekar.ime !== "" &&
        this.firstSetupObj.podaci_o_lekaru_ustanovi.lekar.prezime !== "" &&
        this.firstSetupObj.podaci_o_lekaru_ustanovi.zdravstvena_ustanova
          .naziv !== "" &&
        this.firstSetupObj.podaci_o_lekaru_ustanovi.zdravstvena_ustanova
          .punkt !== ""
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

.data-span {
  margin-left: 15px;
}
</style>
