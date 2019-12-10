<template>
  <div id="app">
<!--    <router-view></router-view>-->
      <NaviBar></NaviBar>
      <router-view></router-view>
  </div>
</template>

<script>
import NaviBar from "@/components/NaviBar";
/*import ItemList from "@/components/ItemList";*/
export default {
  name: 'app',
  components: {
/*    ItemList,*/
    NaviBar
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
/*  margin-top: 60px;*/
}
</style>
