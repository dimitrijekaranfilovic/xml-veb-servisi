<template>
  <v-data-table
    :headers="headers"
    :items="potvrde"
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
        <td>{{ row.item.datumIzdavanja.value }}</td>
        <td>{{ row.item.vakcinacija.ustanova.value }}</td>
        <td>{{ row.item.vakcinacija.nazivVakcine.value }}</td>
        <td align="right">
          <v-btn
            class="mx-2"
            dark
            small
            color="pink"
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
import PotvrdaOVakcinacijiService from "../../services/PotvrdaOVakcinacijiService";

export default {
  name: "PotvrdaOVakcinacijiTable",
  data() {
    return {
      headers: [
        {
          text: "Датум издавања",
          align: "start",
          value: "datumIzdavanja.value",
        },
        {
          text: "Установа",
          value: "vakcinacija.ustanova.value",
        },
        {
          text: "Назив вакцине",
          value: "vakcinacija.nazivVakcine.value",
        },
        {
          text: "Акција",
          align: "right",
        },
      ],
      potvrde: [],
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      let that = this;
      PotvrdaOVakcinacijiService.getAllForUser().then((data) => {
        for (let doc of data.data.potvrde) {
          let dateTokens = new Date(doc.datumIzdavanja.value)
            .toString()
            .split(" ");
          doc.datumIzdavanja.value =
            dateTokens[1] + " " + dateTokens[2] + " " + dateTokens[3];
        }
        that.potvrde = data.data.potvrde;
        that.$root.$emit("potvrdeOVakcinacijiFetched");
      });
    },
    detaljnijiPrikaz(document) {
      this.$router.push("dokument/potvrda-o-vakcinaciji/" + document.id);
    },
  },
};
</script>
