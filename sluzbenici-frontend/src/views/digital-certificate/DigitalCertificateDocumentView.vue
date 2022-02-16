<template>
  <document v-if="certificateHTML" :documentHTML="certificateHTML"></document>
</template>

<script>
import Document from "@/components/Document.vue";
import digitalCertificateService from "@/services/DigitalCertificateService.ts";
import Vue from "vue";

export default Vue.extend({
  components: { Document },
  data: () => ({
    certificateHTML: null,
  }),
  async mounted() {
    const response = await digitalCertificateService.readHtml(
      this.$route.params.id
    );
    this.certificateHTML = response.data;
  },
});
</script>