<template>
  <reports-detail :report="report" />
</template>

<script>
import ReportsDetail from "@/components/reports/ReportsDetail.vue";
import reportsService from "@/services/ReportsService";

export default {
  name: "SingleReportsView",
  components: {
    ReportsDetail,
  },
  data() {
    return {
      report: {},
    };
  },
  mounted() {
    reportsService.getReport(this.$route.params.id).then((response) => {
      this.report = response.data;
    });
  },
};
</script>
