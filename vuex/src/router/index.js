import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/Login.vue";
import Main from "../views/Main.vue";
import Header from "../components/Header.vue";
import Aside from "../components/Aside.vue";
import ManageOrder from "../components/manage/ManageOrder.vue";
import ManageCustomer from "../components/manage/ManageCustomer.vue";
import ManageUser from "../components/manage/ManageUser.vue";
import ManageTotal from "../components/manage/ManageTotal.vue"

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component:Login,
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/main",
    component: Main,
    redirect:'/manageOrder',
    children: [
      {
        path: "/manageOrder",
        component: ManageOrder,
      },
      {
        path: "/manageCustomer",
        component: ManageCustomer,
      },
      {
        path: "/manageUser",
        component: ManageUser,
      },
      {
        path:"/manageTotal",
        component:ManageTotal,
      }
    ],
  },
  {
    path: "/header",
    component: Header,
  },
  {
    path: "/aside",
    component: Aside,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  const isLogin = window.sessionStorage.getItem("isLogin")
  if(to.path==="/login"){
    window.sessionStorage.clear()
    next()
  }else{
    if(isLogin!==null){
      next()
    }else{
      window.sessionStorage.clear()
      next("/login")
    }
  }
});

export default router;
