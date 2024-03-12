<script>
  export default {
    name:"AllDataGet",
  }
</script>

<script setup>
  import axios from "axios";
  import {ref} from "vue";

  //获取数据
  let responseData = ref('')
  function getAllData(){
    axios.get('http://localhost:4000/GetAllData')
        .then(response=>{
          responseData.value = response.data['data']
        })
  }

  //根据当前月份日有多少天
  let days = ref()
  let dateNow = ref()
  function getDuration(date){
    date = dateNow.value
    let dt = new Date(date)
    let month = dt.getMonth()
    dt.setMonth(dt.getMonth() + 1)
    dt.setDate(0)
    days.value = dt.getDate()
    console.log(days.value)
  }
</script>


<template>
  <div class="date-get">
    <button @click="getAllData">getDAta</button>
    <span>{{responseData}}</span>
    <input v-model="dateNow"/>
    <button @click="getDuration">提交</button>
  </div>

  <div class="num-table">
    <span v-for="day in days" :key="day" class="num-items">{{ day }}</span>
  </div>

</template>

<style>
  .date-get{
    margin-bottom: 10px;
  }
  .num-table{
    flex: auto;
    justify-content: center;
  }
  .num-items{
    display: inline-block;
    margin-right: 10px;
    padding: 5px;
    width: 30px; /* 设置固定宽度 */
    text-align: center; /* 水平居中文本 */
    //border: 1px solid black;
    border-radius: 10px;
    margin-bottom: 10px;
    background-color: mediumspringgreen;
  }
</style>