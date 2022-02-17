<template>
  <document
    v-if="docHtml"
    :documentHTML="docHtml"
    documentType="saglasnost"
    :documentId="$route.params.id"
  ></document>
</template>

<script>
import Document from "@/components/Document.vue";
import vaccinationConsentService from "@/services/VaccinationConsentService.ts";
import Vue from "vue";

export default Vue.extend({
  components: { Document },
  data: () => ({
    docHtml: null,
  }),
  async mounted() {
    const response = await vaccinationConsentService.readHtml(
      this.$route.params.id
    );
    this.docHtml = response.data;
  },
});
</script>
