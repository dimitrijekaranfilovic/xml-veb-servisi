import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import jwt_decode from "jwt-decode";

Vue.config.productionTip = false;

// Configure axios to always include JWT when sending a request
axios.interceptors.request.use(
  (config) => {
    let jwt = localStorage.getItem("jwt");
    if (jwt) {
      if (config.headers) {
        config.headers.Authorization = `Bearer ${jwt}`;
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

router.beforeEach((to: any, from: any, next: any) => {
  const { authenticated, authorities } = to.meta;

  if (authenticated) {
    let jwt = localStorage.getItem("jwt");
    if (jwt) {
      let decodedToken: any = jwt_decode(jwt);
      if (
        authorities.some((element: string) =>
          decodedToken.roles.includes(element)
        )
      ) {
        next();
      } else {
        next({ name: "Login" });
      }
    } else {
      next({ name: "Login" });
    }
  } else {
    next();
  }
});

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
