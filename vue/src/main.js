 import '@/assets/board.scss';

import Vue from "vue";
import App from "./App.vue";
import { router } from './router';

Vue.config.productionTip = false;

const app = new Vue({
  render: h => h(App),
  router,
}).$mount('#app');

window.app = app;