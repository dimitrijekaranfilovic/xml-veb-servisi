<template>
  <document
    v-if="confirmationHtml"
    :documentHTML="confirmationHtml"
    documentType="potvrda-o-vakcinaciji"
    :documentId="$route.params.id"
  ></document>
</template>

<script>
import Document from "@/components/Document.vue";
import vaccinationConfirmationService from "@/services/VaccinatioConfirmationService";
import Vue from "vue";

export default Vue.extend({
  components: { Document },
  data: () => ({
    confirmationHtml: null,
  }),
  mounted() {
    vaccinationConfirmationService
      .getConfirmationAsHtml(this.$route.params.id)
      .then((response) => (this.confirmationHtml = response.data));
  },
});
</script>
