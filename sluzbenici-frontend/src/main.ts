import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import "./filters/DeRDFFilter.ts";

Vue.config.productionTip = false;
// eslint-disable-next-line @typescript-eslint/no-var-requires
Vue.use(require("vue-moment"));

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
