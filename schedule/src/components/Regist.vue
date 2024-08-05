<script setup>
    import{ ref,reactive} from 'vue'
       // 响应式数据,保存用户输入的表单信息 
       //导入发送请求的对象
       import request from '../utils/request'
       import {useRouter} from 'vue-router'
       const router = useRouter()
       let registUser =reactive({
        username:'',
        userPwd:''
       })

       // 响应式数据,保存校验的提示信息
       let reUserPwd =ref('')
       let reUserPwdMsg =ref('')
       let usernameMsg =ref('')
       let userPwdMsg = ref('')

          // 校验用户名的方法
        async function checkUsername(){
            // 定义正则
            let usernameReg=/^[a-zA-Z0-9]{5,10}$/
            // 校验
            if(!usernameReg.test(registUser.username)){
              // 提示
              usernameMsg.value = "不合法"
              return false
            }
            //继续校验是否已经被占用
            let {data}= await request.post(`user/checkUsernameUsed?username=${registUser.username}`)
           
            if(data.code!=200){
                usernameMsg.value="已占用"
                return false
            }
            // 通过校验
            usernameMsg.value="可用"
            return true
        }
        // 校验密码的方法
        async function checkUserPwd(){
            // 定义正则
            let passwordReg=/^[0-9]{6}$/
             // 校验
             if(!passwordReg.test(registUser.userPwd)){
              // 提示
              userPwdMsg.value = "不合法"
              return false
            }
            // 通过校验
            userPwdMsg.value="OK"
            return true
        }
        // 再次密码的方法
        async function checkReUserPwd(){
            // 定义正则
            let passwordReg=/^[0-9]{6}$/
            // 校验
            if(!passwordReg.test(reUserPwd.value)){
              // 提示
              reUserPwdMsg.value = "不合法"
              return false
            }
            console.log(registUser.userPwd,reUserPwd.value)
            // 校验
            if(!(registUser.userPwd==reUserPwd.value)){
              // 提示
              reUserPwdMsg.value = "不一致"
              return false
            }


            // 通过校验
            reUserPwdMsg.value="OK"
            return true
        }
        //注册的方法
        async function regist(){
            //校验所有输入框是否合法
            let flag1 = await checkUsername()
            let flag2 = await checkReUserPwd()
            let flag3 = await checkUserPwd();
            if(flag1&&flag2&&flag3){
                let {data} = await request.post("user/regist",registUser)
                if(data.code == 200){
                    alert("恭喜你注册成功")
                    router.push("/login")
                }else{
                    alert("用户名被抢注了")
                }
                }
                else{
                    alert("校验不通过，请检查")
                }
        }

</script>

<template>
   <div class="container">
        <h3 class="ht">请注册</h3>

    <table class="tab" cellspacing="0px">
        <tr class="ltr">
            <td>请输入账号</td>
            <td>
                <input class="ipt" 
                  id="usernameInput" 
                  type="text" 
                  name="username" 
                  v-model="registUser.username"
                  @blur="checkUsername()">
                  <span id="usernameMsg" class="msg" v-text="usernameMsg"></span>
            </td>
        </tr>
        <tr class="ltr">
            <td>请输入密码</td>
            <td>
                <input class="ipt" 
                id="userPwdInput" 
                type="password" 
                name="userPwd" 
                v-model="registUser.userPwd"
                @blur="checkUserPwd()">
                <span id="userPwdMsg" class="msg" v-text="userPwdMsg"></span>
            </td>
        </tr>
        <tr class="ltr">
            <td>确认密码</td>
            <td>
                <input class="ipt" 
                id="reUserPwdInput" 
                type="password" 
                v-model="reUserPwd"
                @blur="checkReUserPwd()">
                <span id="reUserPwdMsg" class="msg" v-text="reUserPwdMsg"></span>
            </td>
        </tr>
        <tr class="ltr">
            <td colspan="2" class="buttonContainer">
                <input class="btn1" type="button" @click="regist()" value="注册">
                <input class="btn1" type="reset" value="重置">
                <router-link to="/login">
                  <button class="btn1">去登录</button>
                </router-link>
            </td>
        </tr>
    </table>


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
    </style>
 