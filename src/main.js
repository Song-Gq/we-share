import Vue from 'vue/dist/vue.js'
import VueRouter from 'vue-router'
import App from './App.vue'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import Edit from "@/components/Edit";
import ItemList from "@/components/ItemList";
import Router from 'vue-router'
import Posting from "@/components/Posting";
import Login from "@/components/Login";
import Register from "@/components/Register";
import PersonalPage from "@/components/PersonalPage";
import Question from "@/components/Question";
import Answer from "@/components/Answer";
import PersonalList from "@/components/PersonalList";
import ChangeInfo from "@/components/ChangeInfo";

const NotFound = { template: '<p>Page not found</p>' }

const routes = [
    { path: '/', component: App, redirect: 'index',
    children: [
        { path: 'index', component: ItemList },
        { path: 'edit', component: Edit },
        { path: 'posting', component: Posting, redirect: 'posting/question',
            children: [
                { path: 'question', component: Question },
                { path: 'answer', component: Answer }
            ]
        },
        { path: 'login', component: Login },
        { path: 'register', component: Register },
        { path: 'personalPage', component: PersonalPage,redirect:"personalPage/personalList",
            children:[
                {path: "personalList",component: PersonalList}
            ]
        },
        {path:"changeInfo",component:ChangeInfo},
        { path: 'search', component: ItemList }
    ]},
    { path: '*', component: NotFound }
]

const router = new VueRouter({
    routes: routes
})

const originalPush = Router.prototype.push
Router.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
    return originalPush.call(this, location).catch(err => err)
}

Vue.config.productionTip = false
Vue.use(ViewUI, {
});
Vue.use(VueRouter);

new Vue({
    router
}).$mount('#app')
