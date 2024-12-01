<template>
  <div id="app">
    <div class="overlay">
      <router-view />
    </div>
    <footer>
      <p>&copy; 2024 Venture on Wheels. All rights reserved.</p>
    </footer>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
/* Background Slideshow */
html,
body {
  height: 100%;
  margin: 0;
  font-family: Arial, sans-serif;
  overflow: hidden;
}

body::before {
  content: "";
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background-size: cover;
  background-position: center;
  animation: slideshow 20s infinite;
}

/* @keyframes slideshow {
  0% {
    background-image: url('@/assets/images/20211214_144144.jpg');
  }
  33% {
    background-image: url('@/assets/images/20211214_145632.jpg');
  }
  66% {
    background-image: url('@/assets/images/20211107_082425.jpg');
  }
 
  100% {
    background-image: url('@/assets/images/20211214_144144.jpg');
  }
} */

/* Overlay for content */
.overlay {
  position: relative;
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

/* Footer */
footer {
  text-align: center;
  color: white;
  padding: 10px;
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.5);
}
</style>
