<template>
    <div id="app">
        <NaviBar></NaviBar>
        <router-view></router-view>
    </div>
</template>

<script>
    import NaviBar from "@/components/NaviBar";
    export default {
        name: 'app',
        components: {
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
    }
</style>
