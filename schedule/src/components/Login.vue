<script setup>
 import {ref,reactive} from 'vue'
 import {useRouter} from 'vue-router'
 import { nextTick } from 'vue'
 const router = useRouter()

 import {defineUser} from '../store/userStore.js' 
 let sysUser = defineUser()

 import request from '../utils/request'
 let loginUser = reactive({
    username:"",
    userPwd:""
 })
 let usernameMsg = ref("");
 let userPwdMsg = ref("");

 function checkUsername(){
    let usernameReg = /^[a-zA-Z0-9]{5,10}$/
    if(!usernameReg.test(loginUser.username)){
        usernameMsg.value="格式有误"
        return false}
        else{
            usernameMsg.value = "OK"
            return true
        }
    }

function checkUserPwd(){
    // 定义正则
    var passwordReg=/^[0-9]{6}$/
             // 校验密码
             if(!passwordReg.test(loginUser.userPwd)){
                // 格式不合法
                userPwdMsg.value="格式有误"
                return false
            }
            userPwdMsg.value="ok"
            return true
}
async function login(){
    let flag1 = checkUsername()
    let flag2 = checkUserPwd()
    if(flag1&&flag2)
    {{let {data} =await request.post("user/login",loginUser)
    console.log(data.code)
    if(data.code==501){alert("用户名错误")}
    else if(data.code==503){alert("密码错误")}
    else if(data.code==200){
        sysUser.uid = data.data.uid
        sysUser.username = data.data.username
        sysUser.logindate = new Date().toISOString().split('T')[0]
        console.log(sysUser.logindate)
        console.log("登录成功，跳转到 /showSchedule")
        await nextTick() // 确保状态更新
        router.push(`/showSchedule/${sysUser.logindate}`);
    }
    else {
        alert("未知错误")
    }}}
    else{
        return
    }
   
}
 
</script>

<template>
    <div>
    <div class="container">
    <h3 class="ht">请登录</h3>
    
        <table class="tab" cellspacing="0px">
            <tr class="ltr">
                <td>请输入账号</td>
                <td>
                    <input class="ipt" type="text" v-model="loginUser.username" @blur="checkUsername()">
                    <span id="usernameMsg" class="msg" v-text="usernameMsg"></span>
                </td>
            </tr>
            <tr class="ltr">
                <td>请输入密码</td>
                <td>
                    <input class="ipt" type="password" v-model="loginUser.userPwd"  @blur="checkUserPwd()">
                    <span id="userPwdMsg" class="msg" v-text="userPwdMsg"></span>
                </td>
            </tr>
            <tr class="ltr">
                <td colspan="2" class="buttonContainer">
                    <input class="btn1" type="button" @click="login()" value="登录"> 
                    <input class="btn1" type="button" value="重置">
                    <router-link to="/regist"> 
                      <button class="btn1">去注册</button>
                    </router-link>
                </td>
            </tr>
        </table>
  
</div>
 </div>
</template>

<style>
body {
    font-family: '幼圆', sans-serif;
    background-color: #f0f8ff;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

.container {
    width: 100%;
    max-width: 500px;
    background: #ffffff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.ht {
    text-align: center;
    color: cadetblue;
    font-family: '幼圆', sans-serif;
}

.tab {
    width: 100%;
    border: 5px solid cadetblue;
    margin: 0 auto;
    border-radius: 5px;
    font-family: '幼圆', sans-serif;
    background-color: #ffffff;
}

.ltr td {
    border: 1px solid powderblue;
    padding: 10px;
}

.ipt {
    border: 1px solid #ddd;
    width: calc(100% - 20px);
    padding: 10px;
    margin-top: 5px;
    margin-bottom: 5px;
    border-radius: 4px;
}

.btn1 {
    border: 2px solid powderblue;
    border-radius: 4px;
    width: 100px;
    background-color: antiquewhite;
    padding: 10px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s, color 0.3s;
}

.btn1:hover {
    background-color: powderblue;
    color: white;
}

.msg {
    color: gold;
    font-size: 12px;
}

.buttonContainer {
    text-align: center;
    padding: 10px 0;
}

.buttonContainer a {
    text-decoration: none;
    color: inherit;
}

.buttonContainer .btn1 {
    display: inline-block;
    margin: 0 10px;
}
.optionDiv{
      width: 300px;
      float: right;
    }
</style>