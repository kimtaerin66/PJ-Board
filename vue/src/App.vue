<template>
  <div id="app">
    <transition name="fade-view" mode="out-in">
    <router-view @updatePage="updatePage"></router-view>
    </transition>
  </div>
</template>

<script>

export default {
  name: "App",
  data() {
    return {
      currentPath: '',
    }
  },
  methods: {
    updatePage(value) {
      this.currentPath = value;
    },
  }
};
</script>
