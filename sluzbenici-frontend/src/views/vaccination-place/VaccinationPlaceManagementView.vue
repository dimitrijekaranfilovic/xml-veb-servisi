<template>
  <v-container>
    <v-row align="start">
      <v-col>
        <!-- <v-btn color="primary"> Dodaj punkt </v-btn> -->
        <v-dialog v-model="dialog" max-width="600px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="primary" v-bind="attrs" v-on="on">
              Dodaj punkt
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="text-h5">Dodaj punkt</span>
            </v-card-title>
            <v-card-text>
              <v-container fluid>
                <v-text-field
                  label="Naziv punkta"
                  v-model="obj.vakcinacioni_punkt.naziv_punkta"
                  required
                ></v-text-field>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="primary" text @click="dialog = false">
                Odustani
              </v-btn>
              <v-btn
                color="primary"
                text
                @click="addVaccinationPlace()"
                :disabled="obj.vakcinacioni_punkt.naziv_punkta === ''"
              >
                Potvrdi
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
    <vaccination-place-table :vaccinationPlaces="vaccinationPlaces" />
  </v-container>
</template>

<script>
import VaccinationPlaceTable from "@/components/vaccination-place/VaccinationPlaceTable.vue";
import vaccinationPlaceService from "@/services/VaccinationPlaceService";
export default {
  name: "VaccinationPlaceManagement",
  components: {
    VaccinationPlaceTable,
  },
  data() {
    return {
      dialog: false,
      vaccinationPlaces: [],
      obj: {
        vakcinacioni_punkt: {
          naziv_punkta: "",
        },
      },
    };
  },
  mounted() {
    vaccinationPlaceService.getVaccinationPlaces().then((response) => {
      this.vaccinationPlaces = response.data.vakcinacioniPunktovi;
    });
  },
  methods: {
    addVaccinationPlace() {
      vaccinationPlaceService.createVaccinationPlace(this.obj).then((_) => {
        //this.$router.push({ name: "VaccinationPlaceManagementView" });
        this.$router.go(0);
      });
    },
  },
};
</script>
