<template>
  <div>
    <Header/>
    <div style="display: flex;">
      <Aside />
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<script>

import Header from "../components/Header.vue";
import Aside from "../components/Aside.vue";

export default {
  name: "Layout",
  data:function (){
    return{

    }
  },
  components:{
    Header,
    Aside,
  },
  created:function(){
    
  }
}
</script>
