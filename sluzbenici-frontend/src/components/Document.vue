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
export default {
  props: ["documentHTML"],
  methods: {
    downloadXHTML() {
      console.log("XHTML Download");
    },
    downloadPDF() {
      console.log("PDF Download");
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