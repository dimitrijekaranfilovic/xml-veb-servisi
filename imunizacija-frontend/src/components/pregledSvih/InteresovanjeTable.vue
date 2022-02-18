<template>
  <v-data-table
    :headers="headers"
    :items="interesovanja"
    :items-per-page="5"
    item-key="id"
    class="elevation-1"
    :footer-props="{
      showFirstLastPage: true,
      firstIcon: 'mdi-arrow-collapse-left',
      lastIcon: 'mdi-arrow-collapse-right',
      prevIcon: 'mdi-minus',
      nextIcon: 'mdi-plus',
    }"
  >
    <template v-slot:item="row">
      <tr>
        <td>{{ row.item.datum.value }}</td>
        <td>{{ row.item.licneInformacije.davalacKrvi.value }}</td>
        <td>{{ row.item.odabranaLokacijaPrimanjaVakcine.value }}</td>
        <td align="right">
          <v-btn
            class="mx-2"
            dark
            small
            color="primary"
            @click="detaljnijiPrikaz(row.item)"
          >
            Детаљнији преглед
          </v-btn>
        </td>
      </tr>
    </template>
  </v-data-table>
</template>

<script>
import InteresovanjeService from "../../services/InteresovanjeService";

export default {
  name: "InteresovanjeTable",
  data() {
    return {
      headers: [
        {
          text: "Поднешено дана",
          align: "start",
          value: "datum.value",
        },
        {
          text: "Давалац крви",
          value: "licneInformacije.davalacKrvi.value",
        },
        {
          text: "Одабрана локација примања вакцине",
          value: "odabranaLokacijaPrimanjaVakcine.value",
        },
        {
          text: "Акција",
          align: "right",
        },
      ],
      interesovanja: [],
    };
  },
  mounted() {
    let that = this;
    InteresovanjeService.getAllForUser().then((data) => {
      for (let doc of data.data.interesovanja) {
        let dateToekns = new Date(doc.datum.value).toString().split(" ");
        doc.datum.value =
          dateToekns[1] + " " + dateToekns[2] + " " + dateToekns[3];
        doc.licneInformacije.davalacKrvi.value =
          doc.licneInformacije.davalacKrvi.value === true ? "Да" : "Не";
      }
      that.interesovanja = data.data.interesovanja;
      that.$root.$emit("interesovanjeFetched");
    });
  },
  methods: {
    detaljnijiPrikaz(document) {
      this.$router.push("dokument/interesovanje/" + document.id);
    },
  },
};
</script>
