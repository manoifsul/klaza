import { boot } from 'quasar/wrappers';
import Vue from 'vue';

import VueSweetalert2 from 'vue-sweetalert2';

import 'sweetalert2/dist/sweetalert2.min.css';

Vue.use(VueSweetalert2);

declare module 'vue/types/vue' {
    interface Vue {
        
    }
}

export default boot(({ Vue }) => {

    
    
});