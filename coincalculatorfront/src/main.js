import {createApp} from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';
import i18n from './i18n'

createApp(App).use(i18n).mount('#app')
