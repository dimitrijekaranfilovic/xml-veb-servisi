<template>
  <v-container>
    <v-flex class="text-center">
      <h1>Преглед свих докумената</h1>
    </v-flex>
    <br />
    <br />
    <h3>Интересовања</h3>
    <interesovanje-table></interesovanje-table>
    <br />
    <br />
    <h3>Обрасци сагласности</h3>
    <saglasnost-table></saglasnost-table>
  </v-container>
</template>

<script>
import InteresovanjeTable from "@/components/pregledSvih/InteresovanjeTable.vue";
import SaglasnostTable from "../components/pregledSvih/SaglasnostTable.vue";

export default {
  components: { SaglasnostTable, InteresovanjeTable },
  data: () => ({}),
};
</script>
