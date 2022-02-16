<template>
  <v-list flat>
    <v-list-item>
      <v-list-item-content>
        <v-list-item-title class="mb-2"
          ><b>Подносилац захтева: </b></v-list-item-title
        >
        <p class="mb-2">
          Име и презиме:
          {{ request.podnosilacZahteva.licniPodaci.ime | deRdf }}
          {{ request.podnosilacZahteva.licniPodaci.prezime | deRdf }}
        </p>
        <p class="mb-2">
          Пол:
          {{ request.podnosilacZahteva.licniPodaci.pol | deRdf }}
        </p>
        <p class="mb-2" v-if="request.podnosilacZahteva.licniPodaci.jmbg.value">
          Јединствени матични број грађанина:
          {{ request.podnosilacZahteva.licniPodaci.jmbg | deRdf }}
        </p>
        <p class="mb-2" v-if="request.podnosilacZahteva.brojPasosa.value">
          Број пасоша:
          {{ request.podnosilacZahteva.brojPasosa | deRdf }}
        </p>
        <p class="mb-2">
          Адреса електронске поште:
          {{ request.podnosilacZahteva.email | deRdf }}
        </p>
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
      <v-list-item-content>
        <v-list-item-title class="mb-2"
          ><b>Разлог за подношење захтева: </b></v-list-item-title
        >
        <quill-editor
          class="editor"
          ref="myTextEditor"
          v-model="request.razlogZaPodnosenjeZahteva.value"
          :options="editorOption"
          :disabled="true"
        />
      </v-list-item-content>
    </v-list-item>
  </v-list>
</template>

<script>
import Vue from "vue";
import { quillEditor } from "vue-quill-editor";
// import theme style
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";

export default Vue.extend({
  name: "DigitalCertificateRequestDetails",
  components: { quillEditor },
  props: ["request"],
  data: () => ({
    editorOption: {
      placeholder: "Разлог за подношење захтева...",
      modules: {
        toolbar: false,
        syntax: {
          highlight: (text) => hljs.highlightAuto(text).value,
        },
      },
    },
  }),
});
</script>
