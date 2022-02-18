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
    path: "/pregled/digitalni-sertifikat/:id",
    name: "DigitalCertificateDocumentView",
    component: () =>
      import("../views/digital-certificate/DigitalCertificateDocumentView.vue"),
  },
  {
    path: "/pregled/izvestaj/:id",
    name: "ReportDocumentView",
    component: () => import("../views/reports/ReportDocumentView.vue"),
  },
  {
    path: "/pregled/saglasnost/:id",
    name: "VaccinationConsentDocumentView",
    component: () =>
      import("../views/vaccination-consent/VaccinationConsentDocumentView.vue"),
  },
  {
    path: "/pregled/potvrda-o-vakcinaciji/:id",
    name: "VaccinationConfirmationDocumentView",
    component: () =>
      import(
        "../views/vaccination-confirmation/VaccinationConfirmationDocumentView.vue"
      ),
  },
  {
    path: "/pregled/zahtev-za-sertifikat/:id",
    name: "DigitalCertificateRequestDocumentView",
    component: () =>
      import(
        "../views/digital-certificate/DigitalCertificateRequestDocumentView.vue"
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
    path: "/vakcinacije",
    name: "VaccinationView",
    component: () => import("../views/vaccination-process/VaccinationView.vue"),
  },
  {
    path: "/vakcinacija/:id",
    name: "SingleVaccinationView",
    component: () =>
      import("../views/vaccination-process/SingleVaccinationView.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
