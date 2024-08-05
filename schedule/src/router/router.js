import {createRouter,createWebHashHistory} from 'vue-router'
import Login from '../components/Login.vue'
import Regist from '../components/Regist.vue'
import ShowSchedule from '../components/ShowSchedule.vue'
import { defineUser } from '../store/userStore.js'


const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
           path:"/",
           component:Login 
        },
        {
            path: '/showSchedule/:date?',
            component: ShowSchedule,
            name: 'ShowSchedule',
            props: true // 允许将路由参数作为 props 传递给组件
         },
         {
            path:"/login",
            component:Login 
         },
         {
            path:"/regist",
            component:Regist 
         }
    ]
})
//通过路由的全局前置守卫 判断是否可以访问shouwSchedule
router.beforeEach((to,from,next)=>{
   const sysUser = defineUser()
   //登陆过放行 不然阻止通过
   if(to.path=='/showSchedule'){
         if(sysUser.username==""){
         
            next("/login")
         }
         else{next()}
   }else{
         next()
   }
})
export default router