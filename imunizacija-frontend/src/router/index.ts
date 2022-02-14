import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Login from "../views/Login.vue";

Vue.use(VueRouter);

const roles = { gradjanin: "GRADJANIN" };

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Login",
    component: Login,
    meta: {
      authenticated: false,
      authorities: [],
    },
  },
  {
    path: "/register",
    name: "Register",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Register.vue"),
    meta: {
      authenticated: false,
      authorities: [],
    },
  },
  {
    path: "/saglasnost",
    name: "Saglasnost",
    component: () => import("../views/Saglasnost.vue"),
    meta: {
      authenticated: true,
      authorities: [roles.gradjanin],
    },
  },
  {
    path: "/interesovanje",
    name: "Interesovanje",
    component: () => import("../views/Interesovanje.vue"),
    meta: {
      authenticated: true,
      authorities: [roles.gradjanin],
    },
  },
  {
    path: "/zahtev-za-sertifikat",
    name: "ZahtevZaSertifikat",
    component: () => import("../views/ZahtevZaSertifikat.vue"),
    meta: {
      authenticated: true,
      authorities: [roles.gradjanin],
    },
  },
  {
    path: "/pregled-svih",
    name: "PregledSvih",
    component: () => import("../views/PregledSvih.vue"),
    meta: {
      authenticated: true,
      authorities: [roles.gradjanin],
    },
  },
];

const router = new VueRouter({
  routes,
});

export default router;
