<template>
  <v-container>
    <v-flex class="text-center">
      <h1>Преглед свих докумената</h1>
    </v-flex>
    <br />
    <br />
    <v-tabs color="primary accent-4">
      <v-tab>Интересовања</v-tab>
      <v-tab>Обрасци сагласности</v-tab>
      <v-tab>Захтеви за сертификат</v-tab>
      <v-tab>Дигитални сертификати</v-tab>
      <v-tab-item>
        <br />
        <br />
        <interesovanje-table></interesovanje-table>
      </v-tab-item>
      <v-tab-item>
        <br />
        <br />
        <saglasnost-table></saglasnost-table>
      </v-tab-item>
      <v-tab-item>
        <br />
        <br />
        <zahtev-za-sertifikat-table></zahtev-za-sertifikat-table>
      </v-tab-item>
      <v-tab-item>
        <br />
        <br />
        <digitalni-sertifikat-table></digitalni-sertifikat-table>
      </v-tab-item>
    </v-tabs>
  </v-container>
</template>

<script>
import InteresovanjeTable from "@/components/pregledSvih/InteresovanjeTable.vue";
import SaglasnostTable from "../components/pregledSvih/SaglasnostTable.vue";
import ZahtevZaSertifikatTable from "@/components/pregledSvih/ZahtevZaSertifikatTable.vue";
import DigitalniSertifikatTable from '@/components/pregledSvih/DigitalniSertifikatTable.vue';

export default {
  components: { SaglasnostTable, InteresovanjeTable, ZahtevZaSertifikatTable, DigitalniSertifikatTable },
  data: () => ({}),
};
</script>
