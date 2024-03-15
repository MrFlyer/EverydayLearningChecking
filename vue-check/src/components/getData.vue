<script>
  export default {
    name:"AllDataGet",
  }
</script>

<script setup>
  import axios from "axios";
  import {reactive, ref} from "vue";

  //获取数据
  let responseData = ref([])

  let haveDataDays = ref([])
  function getAllData(){
    axios.get('http://localhost:4000/GetAllData')
        .then(response=>{
          responseData.value = response.data['data']
          for (let i = 0; i < responseData.value.length; i++){
            haveDataDays.value.push(responseData.value[i])
            console.log(haveDataDays)
          }
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
  //TODO 将根据日期改为根据提交次数
  //根据日期调色
  function getColor(number){
    const hue = (number / 31) * 120 + 150; // 0-240 蓝色的色调范围
    return `hsl(${hue}, 70%, 70%)`; // 使用HSL颜色表示法 hsl为{色相（0-360），饱和度，亮度}
  }

</script>


<template>
  <div class="date-get">
    <button @click="getAllData">getDAta</button>
    <ul v-for="haveData in haveDataDays" :key="haveData">{{haveData}}</ul>
    <span>
      <input v-model="dateNow"/>
      <button @click="getDuration">提交</button>
    </span>

  </div>

  <div class="num-table">
    <ul>
      <li v-for="day in days" :key="day" :style="{backgroundColor:getColor(day)}" class="num-items">{{ day }}</li>

    </ul>
  </div>

</template>

<style scoped>
  .date-get{
    margin-bottom: 10px;
  }
  .num-table{
    flex: auto;
    justify-content: center;
  }
  .num-items{
    //display: inline-block;
    margin-right: 10px;
    padding: 5px;
    width: 30px; /* 设置固定宽度 */
    text-align: center; /* 水平居中文本 */
    //border: 1px solid black;
    border-radius: 10px;
    margin-bottom: 10px;
    list-style-type: none;
  }
</style>