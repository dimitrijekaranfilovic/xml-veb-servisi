<template>
  <v-data-table
    :headers="headers"
    :items="zahtevi"
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
        <td>{{ row.item.mesto.value }}</td>
        <td>{{ row.item.status.value }}</td>
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
import ZahtevZaSertifikatService from "../../services/ZahtevZaSertifikatService";

export default {
  name: "SaglasnostTable",
  data() {
    return {
      headers: [
        {
          text: "Поднешено дана",
          align: "start",
          value: "datum.value",
        },
        {
          text: "Место подношења",
          value: "mesto.value",
        },
        {
          text: "Статус",
          value: "status.value",
        },
        {
          text: "Акција",
          align: "right",
        },
      ],
      zahtevi: [],
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      let that = this;
      ZahtevZaSertifikatService.getAllForUser().then((data) => {
        for (let doc of data.data.zahteviZaSertifikat) {
          let dateToekns = new Date(doc.datum.value).toString().split(" ");
          doc.datum.value =
            dateToekns[1] + " " + dateToekns[2] + " " + dateToekns[3];
        }
        that.zahtevi = data.data.zahteviZaSertifikat;
        that.$root.$emit("zahteviFetched");
      });
    },
    detaljnijiPrikaz(document) {
      this.$router.push("dokument/zahtev-za-sertifikat/" + document.id);
    },
  },
};
</script>
