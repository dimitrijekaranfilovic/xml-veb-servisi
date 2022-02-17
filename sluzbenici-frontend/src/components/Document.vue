<template>
  <div>
    <div class="top-level-container" v-html="documentHTML"></div>
    <v-flex class="text-center">
      <v-btn class="my-10" color="primary" @click.prevent="downloadXHTML()"
        >Преузми као .xhtml</v-btn
      >
      <span class="mx-5" />
      <v-btn class="my-10" color="primary" @click.prevent="downloadPDF()"
        >Преузми као .pdf</v-btn
      >
    </v-flex>
  </div>
</template>

<script>
import httpClient from "@/services/HttpClient";

export default {
  props: ["documentHTML", "documentType", "documentId"],
  methods: {
    downloadXHTML() {
      httpClient.downloadXHTML(this.documentId, this.documentType);
    },
    downloadPDF() {
      httpClient.downloadPDF(this.documentId, this.documentType);
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
