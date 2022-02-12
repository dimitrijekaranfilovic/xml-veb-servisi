import Vue from 'vue';

Vue.filter("deRdf", (param: any) => {
  console.log(param);
  if (param) {
    if ("value" in param) {
      return param.value;
    } else {
      return param;
    }
  } else {
    return "N/A";
  }
});