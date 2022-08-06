<template>
  <img alt="logo" src="https://www.sgveteris.com/img/logo.svg">
  <CoinCalculator headerTitle="SGVETERIS" />
</template>

<script>
import CoinCalculator from './components/CoinCalculator.vue'


export default {
  name: 'App',
  components: {
    CoinCalculator
  }
}
</script>

<style>
body{
  background: #2b3990 !important;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
