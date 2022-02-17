<template>
  <document
    v-if="certificateHTML"
    :documentHTML="certificateHTML"
    documentType="izvestaj-o-imunizaciji"
    :documentId="$route.params.id"
  ></document>
</template>

<script>
import Document from "@/components/Document.vue";
import reportService from "@/services/ReportsService";
import Vue from "vue";

export default Vue.extend({
  components: { Document },
  data: () => ({
    certificateHTML: null,
  }),
  mounted() {
    reportService
      .getReportAsHtml(this.$route.params.id)
      .then((response) => (this.certificateHTML = response.data));
  },
});
</script>
