<script setup>
import {ref,reactive,onUpdated,onMounted,watch} from 'vue'
/*导入pina数据*/
import {defineUser} from '../store/userStore.js'
import {defineSchedule} from '../store/scheduleStore.js'
import { useRoute } from 'vue-router'
//挂载完毕后，立刻查询当前用户的所有日程信息，赋值给pinia

import request from '../utils/request'
const route = useRoute()
const sysUser = defineUser()
const schedule = defineSchedule()
console.log(sysUser.uid)
const date = ref(route.params.date)
console.log('Selected date:', date)
onMounted(async()=>{
  //发送异步请求，获取当前用户的所有日程记录
 showSchedule()
})
watch(() => route.params.date, (newDate) => {
  console.log('Route date changed:', newDate)
  date.value = newDate
  showSchedule()
})
async function showSchedule(){
  //const { data } = await request.get("schedule/findAllSchedule", { params: { uid: sysUser.uid, date } })
  console.log(date)
  let {data} = await request.get("schedule/findAllSchedule",{params:{"uid":sysUser.uid,"date":date.value}})
  schedule.itemList =data.data.itemlist
}
//为当前用户增加一个空的日程记录
async function addItem(){
   let{data} = await request.get('schedule/addDefaultShedule',{params:{"uid":sysUser.uid,"date":date.value}})
   console.log(data.code)
   if(data.code == 200){
      //增加成功 刷新数据
      showSchedule()
   }
   else{
    alert("增添失败")
   }
}
async function updateItem(index){
  //找到对应行的数据
   let{data} = await request.post("schedule/updateSchedule",schedule.itemList[index])
   if(data.code == 200){
      //增加成功 刷新数据
      alert("更新成功")
      showSchedule()
   }
   else{
    alert("更新失败")
   }
}
async function remove(index){
  //找到对应行的数据
   let{data} = await request.post("schedule/removeSchedule",schedule.itemList[index])
   console.log(data.code)
   if(data.code == 200){
      //增加成功 刷新数据
      alert("删除成功")
      showSchedule()
   }
   else{
    alert("删除失败")
   }
}

</script>

<template>
    <div>
      <h3 class="ht">您的日程如下</h3>
      <table class="tab" cellspacing="0">
          <tr class="header-row">
              <th>编号</th>
              <th>内容</th>
              <th>日期</th>
              <th>进度</th>
              <th>操作</th>
          </tr>
          <tr class="data-row" v-for="item,index in schedule.itemList" :key="index">
              <td v-text = "index+1">
              </td>
              <td>
                  <input type="input" v-model = "item.title">
              </td> 
              <td>
                  <input type="date" v-model="item.sys_date"> <!-- 日期输入字段 -->
                </td>
              <td>
                  <input type="radio" value="1" v-model="item.completed"> 已完成
                  <input type="radio" value="0" v-model="item.completed"> 未完成 
              </td>
              <td class="buttonContainer">
                  <button class="btn1" @click="remove(index)">删除</button>
                  <button class="btn1" @click="updateItem(index)">保存修改</button>
              </td>
          </tr>
          <tr class="footer-row buttonContainer">
              <td colspan="4">
                  <button class="btn1" @click="addItem">新增日程</button>
              </td>
          </tr>
      </table>
    </div>
  </template>
  
  <style scoped>
    .ht {
      text-align: center;
      color: cadetblue;
      font-family: 幼圆;
    }
  
    .tab {
      width: 80%;
      margin: 20px auto;
      border: 5px solid cadetblue;
      border-radius: 5px;
      font-family: 幼圆;
      border-collapse: collapse;
    }
  
    .tab th, .tab td {
      border: 1px solid powderblue;
      padding: 8px;
      text-align: center;
    }
  
    .header-row th {
      background-color: antiquewhite;
      color: cadetblue;
      font-weight: bold;
      font-size: 1.2em;
    }
  
    .data-row td {
      background-color: white;
    }
  
    .footer-row button {
      margin-top: 10px;
    }
  
    .btn1 {
      border: 2px solid powderblue;
      border-radius: 4px;
      width: 100px;
      background-color: antiquewhite;
      cursor: pointer;
    }
  
    #usernameMsg, #userPwdMsg {
      color: gold;
    }
  
    .buttonContainer {
      text-align: center;
    }
  </style>
  