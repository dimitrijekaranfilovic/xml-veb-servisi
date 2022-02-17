<template>
  <document
    v-if="docHtml"
    :documentHTML="docHtml"
    documentType="zahtev-za-sertifikat"
    :documentId="$route.params.id"
  ></document>
</template>

<script>
import Document from "@/components/Document.vue";
import digitalCertificateRequestService from "@/services/DigitalCertificateRequestService.ts";
import Vue from "vue";

export default Vue.extend({
  components: { Document },
  data: () => ({
    docHtml: null,
  }),
  async mounted() {
    const response = await digitalCertificateRequestService.readHtml(
      this.$route.params.id
    );
    this.docHtml = response.data;
  },
});
</script>
