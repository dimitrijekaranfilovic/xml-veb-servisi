<template>
  <v-data-table
    :headers="headers"
    :items="saglasnosti"
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
  ></v-data-table>
</template>

<script>
import SaglasnostService from "../../services/SaglasnostService";

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
          text: "Пристанак",
          value: "pacijent.saglasnost.izjava.value",
        },
        {
          text: "Одабрана вакцина",
          value: "pacijent.saglasnost.nazivImunoloskogLeka.value",
        },
      ],
      saglasnosti: [],
    };
  },
  mounted() {
    let that = this;
    this.$root.$on("interesovanjeFetched", function () {
      that.fetchData();
    });
  },
  methods: {
    fetchData() {
      let that = this;
      SaglasnostService.getAllForUser().then((data) => {
        for (let doc of data.data.saglasnosti) {
          let dateToekns = new Date(doc.datum.value).toString().split(" ");
          doc.pacijent.saglasnost.izjava.value =
            doc.pacijent.saglasnost.izjava.value === true ? "Да" : "Не";
          doc.datum.value =
            dateToekns[1] + " " + dateToekns[2] + " " + dateToekns[3];
        }
        that.saglasnosti = data.data.saglasnosti;
      });
    },
  },
};
</script>
