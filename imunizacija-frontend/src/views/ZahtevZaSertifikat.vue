<template>
  <v-container>
    <v-flex class="text-center">
      <h1>ЗАХТЕВ</h1>
      <h2>за издавање зеленог дигиталног сертификата</h2>
    </v-flex>
    <zahtev-za-sertifikat-form></zahtev-za-sertifikat-form>
  </v-container>
</template>

<script>
import ZahtevZaSertifikatForm from "@/components/zahtevZaSertifikat/ZahtevZaSertifikatForm.vue";
export default {
  components: { ZahtevZaSertifikatForm },
};
</script>

<style></style>
