<template>
  <div>
    <div class="top-level-container" v-html="legacySystemHTML"></div>
    <br />
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
import BaseService from "@/services/BaseService";

export default {
  data() {
    return {
      legacySystemHTML: "",
    };
  },
  mounted() {
    let that = this;
    let documentType = this.$route.params.documentType;
    BaseService.getXHTMLRepresentation(
      this.$route.params.id,
      documentType
    ).then((response) => {
      that.legacySystemHTML = response.data;
    });
  },
  methods: {
    downloadItem() {
      BaseService.downloadXHTML(
        this.$route.params.id,
        this.$route.params.documentType
      );
    },
    downloadPDFItem() {
      BaseService.downloadPDF(
        this.$route.params.id,
        this.$route.params.documentType
      );
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
