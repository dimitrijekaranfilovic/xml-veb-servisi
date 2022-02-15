import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/Home.vue"),
  },
  {
    path: "/zahtevi-za-digitalni-sertifikat",
    name: "DigitalCertifikateRequestsView",
    component: () =>
      import("../views/digital-certificate/DigitalCertificateRequestsView.vue"),
  },
  {
    path: "/detalji-o-zahtevu/:id",
    name: "SingleDigitalCertificateRequestView",
    component: () =>
      import(
        "../views/digital-certificate/SingleDigitalCertificateRequestView.vue"
      ),
  },
  {
    path: "/punktovi",
    name: "VaccinationPlaceManagementView",
    component: () =>
      import("../views/vaccination-place/VaccinationPlaceManagementView.vue"),
  },
  {
    path: "/punkt/:id",
    name: "SingleVaccinationPlaceView",
    component: () =>
      import("../views/vaccination-place/SingleVaccinationPlaceView.vue"),
  },
  {
    path: "/izvestaji",
    name: "ReportsView",
    component: () => import("../views/reports/ReportsView.vue"),
  },
  {
    path: "/izvestaj/:id",
    name: "SingleReportView",
    component: () => import("../views/reports/SingleReportView.vue"),
  },
  {
    path: "/vakcinacija",
    name: "VaccinationView",
    component: () => import("../views/vaccination-process/VaccinationView.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
