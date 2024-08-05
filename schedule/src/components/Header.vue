<script setup>
/**导入pinia数据来判断登陆状态 */
    import {ref,watch} from 'vue'
    import {defineUser} from '../store/userStore.js'
    import {defineSchedule} from '../store/scheduleStore.js'
    import {useRouter} from 'vue-router'
    import DatePicker from '@vuepic/vue-datepicker'
    import '@vuepic/vue-datepicker/dist/main.css'
    let router = useRouter()
    let sysUser =defineUser()
    let schedule = defineSchedule();
    let selectedDate = ref(null)
  watch(() => sysUser.logindate, (newDate) => {
  if (newDate) {
    selectedDate.value = new Date(newDate) // 登陆的时候 将date传递给sysUser 将字符串日期转换为 Date 对象
  }
})
    function logout(){
        sysUser.$reset()
        schedule.$reset()
        router.push("/login")
    }
  function onDateSelected(date) {
    if (date) {
    const formattedDate = date.toISOString().split('T')[0] // 格式化日期为 YYYY-MM-DD
    console.log(`Navigating to /showSchedule/${formattedDate}`)
    router.push(`/showSchedule/${formattedDate}`)
  } else {
    console.log('No date selected')
  }
}
</script>
   
<template>
  <div>
    <h1 class="ht">欢迎使用日程管理系统</h1>
    <div>
      <div  class="optionDiv" v-if="sysUser.username==''">
        <router-link to="/login">
          <button class="b1s">登录</button>
        </router-link>   
        <router-link to="/regist">
          <button class="b1s">注册</button>
        </router-link>
      </div>


      <div   class="optionDiv" v-else>
       
        欢迎{{sysUser.username}}  
        <button class="b1b" @click = "logout()">退出登录</button> 
        <router-link to="/ShowSchedule">
          <button class="b1b">查看我的日程</button>
        </router-link>
      </div>

      <br>
      <!-- 添加日期选择器 -->
      <div v-if="sysUser.username !== ''">
        <date-picker
          v-model="selectedDate" 
          @update:modelValue="onDateSelected"
          format="yyyy-MM-dd"/> <!-- 
          把日期绑定在selectdate上进行交互
          然后更新的方法是onDateSeleted
          确保日期格式为 YYYY-MM-DD
           -->
        
      </div>
    </div>
    </div>
  
</template>

<style scoped>

  .ht{
      text-align: center;
      color: cadetblue;
      font-family: 幼圆;
  }
  .b1s{
        border: 2px solid powderblue;
        border-radius: 4px;
        width:60px;
        background-color: antiquewhite;

    }

    .b1b{
        border: 2px solid powderblue;
        border-radius: 4px;
        width:100px;
        background-color: antiquewhite;
    }
    .optionDiv{
      width: 400px;
      float: right;
    }
</style>