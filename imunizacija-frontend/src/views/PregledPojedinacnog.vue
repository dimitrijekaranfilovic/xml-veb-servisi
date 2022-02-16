<template>
  <div>
    <div class="top-level-container" v-html="legacySystemHTML"></div>
    <v-flex class="text-center">
      <v-btn class="my-10" dark color="pink" @click.prevent="downloadItem()"
        >Преузми као .xhtml</v-btn
      >
      <span class="mx-5" />
      <v-btn class="my-10" dark color="pink" @click.prevent="downloadPDFItem()"
        >Преузми као .pdf</v-btn
      >
    </v-flex>
  </div>
</template>

<script>
import SaglasnostService from "../services/SaglasnostService";

export default {
  data() {
    return {
      legacySystemHTML: "",
    };
  },
  mounted() {
    let that = this;
    SaglasnostService.getXHTMLRepresentation(this.$route.params.id).then(
      (response) => (that.legacySystemHTML = response.data)
    );
  },
  methods: {
    downloadItem() {
      SaglasnostService.downloadXHTML(this.$route.params.id);
    },
    downloadPDFItem() {
      SaglasnostService.downloadPDF(this.$route.params.id);
    },
  },
};
</script>

<style scoped>
.top-level-container {
  margin-left: 15%;
  margin-right: 15%;
}
</style>
