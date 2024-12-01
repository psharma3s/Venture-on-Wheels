<template>
  <div class="home">
    <div class="slideshow">
      <div class="slide"></div>
    </div>
    <div class="overlay">
      <header>
        <h1>Venture on Wheels</h1>
        <p class="tagline">Unleash Your Adventure Spirit</p>
      </header>
      <section>
        <p class="description">
          Welcome to your ultimate road trip planning tool! Discover routes, create trips, and explore the unknown.
        </p>
        <router-link to="/create-trip" class="cta">Start Planning</router-link>
      </section>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
};
</script>

<style scoped>
@font-face {
  font-family: "Blackberry Jam";
  src: url("@/assets/fonts/blackberry-jam.ttf") format("truetype");
}

/* General Styling */
.home {
  height: 100vh;
  width: 100%;
  position: relative;
  overflow: hidden;
}

.slideshow {
  height: 100%;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
}

.slide {
  height: 100%;
  width: 100%;
  background-size: cover;
  background-position: center;
  animation: slideshow 20s infinite;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background: rgba(0, 0, 0, 0.5); /* Dark overlay for contrast */
  z-index: 2;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding-left: 10%;
  text-align: left;
  color: white;
}

/* Header Styling */
header h1 {
  font-family: "Blackberry Jam", cursive;
  font-size: 5rem;
  margin-bottom: 10px;
  text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7);
  color: #fff;
}

.tagline {
  font-family: "Blackberry Jam", cursive;
  font-size: 2.5rem;
  color: #f7b733;
  margin-bottom: 20px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.6);
}

/* Description Text */
.description {
  font-family: "Poppins", sans-serif;
  font-size: 1.2rem;
  margin-bottom: 30px;
  max-width: 600px;
  color: #eaeaea;
}

/* Call-to-Action Button */
.cta {
  display: inline-block;
  padding: 15px 30px;
  font-size: 1.5rem;
  color: #fff;
  background-color: #42b983;
  text-decoration: none;
  border-radius: 50px;
  text-transform: uppercase;
  transition: background-color 0.3s, transform 0.2s;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
}

.cta:hover {
  background-color: #369c6b;
  transform: scale(1.1);
}

/* Keyframes for Slideshow */
@keyframes slideshow {
  0% { background-image: url('@/assets/images/20211028_181527.jpg'); }
  33% { background-image: url('@/assets/images/20211214_145632.jpg'); }
  66% { background-image: url('@/assets/images/20211107_082425.jpg'); }
  100% { background-image: url('@/assets/images/20211214_144144.jpg'); }
}
</style>
