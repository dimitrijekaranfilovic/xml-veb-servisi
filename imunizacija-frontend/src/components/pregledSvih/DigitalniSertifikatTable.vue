<template>
  <v-data-table
    :headers="headers"
    :items="sertifikati"
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
        <td>{{ row.item.datumVremeIzdavanja.value }}</td>
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
import DigitalniSertifikatService from "../../services/DigitalniSertifikatService";

export default {
  name: "DigitalniSertifikatTable",
  data() {
    return {
      headers: [
        {
          text: "Датум издавања",
          align: "start",
          value: "datumVremeIzdavanja.value",
        },
        {
          text: "Акција",
          align: "right",
        },
      ],
      sertifikati: [],
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      let that = this;
      DigitalniSertifikatService.getAllForUser().then((data) => {
        if (data && data.data && data.data.digitalniSertifikati) {
          for (let doc of data.data.digitalniSertifikati) {
            let dateToekns = new Date(doc.datumVremeIzdavanja.value)
              .toString()
              .split(" ");
            doc.datumVremeIzdavanja.value =
              dateToekns[1] + " " + dateToekns[2] + " " + dateToekns[3];
          }
        }
        that.sertifikati = data.data.digitalniSertifikati || [];
        that.$root.$emit("digitalniSertifikatiFetched");
      });
    },
    detaljnijiPrikaz(document) {
      this.$router.push("dokument/digitalni-sertifikat/" + document.id);
    },
  },
};
</script>
