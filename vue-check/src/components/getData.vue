<script>
  export default {
    name:"AllDataGet",
  }
</script>

<script setup>
  import axios from "axios";
  import {ref} from "vue";
  let responseData = ref('')
  function getAllData(){
    axios.get('http://localhost:4000/GetAllData')
        .then(response=>{
          responseData.value = response.data['data']
        })
  }
</script>


<template>
  <div class="person">
    <button @click="getAllData">getDAta</button>
    <span>{{responseData}}</span>
  </div>
</template>

<style>
 .person{
   background-color: antiquewhite;
 }
</style>