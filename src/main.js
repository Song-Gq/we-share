import Vue from 'vue/dist/vue.js'
import VueRouter from 'vue-router'
import App from './App.vue'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import Edit from "@/components/Edit";
import ItemList from "@/components/ItemList";
import Login from "@/components/Login";
import Register from "@/components/Register";

const NotFound = { template: '<p>Page not found</p>' }

/*const routes = {
    '/': App,
    '/index': ItemList,
    '/edit': Edit
}*/
/*const routes = [
    { path: '/', component: App },
    { path: '/#/index', component: ItemList },
    { path: '/#/edit', component: Edit },
]*/
const routes = [
    { path: '/', component: App, redirect: 'index',
    children: [
        { path: 'index', component: ItemList },
        { path: 'edit', component: Edit },
        {path:'login',component:Login},
        {path:'register',component:Register}
    ]},
    { path: '*', component: NotFound }
]

const router = new VueRouter({
    routes: routes
})

Vue.config.productionTip = false
Vue.use(ViewUI, {
/*      size: 'large'*/
  });
Vue.use(VueRouter);
/*Vue.use(router)*/

/*new Vue({
    router,
/!*    el: '#app',*!/
    data: {
        currentRoute: window.location.pathname
    },
    computed: {
        ViewComponent () {
            return routes[this.currentRoute] || NotFound
        }
    },
    render: h => h(this.ViewComponent)
/!*    render (h) { return h(this.ViewComponent) }*!/
/!*    render: h => h(App)*!/
}).$mount('#app')*/

/*new Vue({
    el: '#app',
    router: router,
    data: {
        currentRoute: window.location.pathname
    },
    computed: {
        ViewComponent () {
            var self = this
            return routes.find(function (obj) {
                return obj.path === self.currentRoute
            }).component || NotFound
/!*            return routes[this.currentRoute] || NotFound*!/
        }
    },
    render (h) { return h(this.ViewComponent) }
})*/

new Vue({
    router
}).$mount('#app')
