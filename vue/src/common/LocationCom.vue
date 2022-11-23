<template>
  <div class="location-wrap">
    <div class="location-box">
      <ul class="location">
        <li>Home</li>
        <li v-for="(item, index) in location" :key="index">{{ item }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      location: ['전자결재', '결재중'],
    };
  },
};
</script>
