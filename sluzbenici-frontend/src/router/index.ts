import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
  {
    path: "/zahtevi-za-digitalni-sertifikat",
    name: "DigitalCertifikateRequestsView",
    component: () => import("../views/DigitalCertificateRequestsView.vue"),
  },
  {
    path: "/detalji-o-zahtevu/:id",
    name: "SingleDigitalCertificateRequestView",
    component: () => import("../views/SingleDigitalCertificateRequestView.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
