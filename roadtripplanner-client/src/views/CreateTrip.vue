<template>
  <div class="create-trip-container">
    <h1 class="app-title">Venture on Wheels</h1>
    <div class="content">
      <!-- Image Section -->
      <div class="image-box">
        <img src="@/assets/images/road-trip.jpg" alt="Road Trip" />
      </div>

      <div class="progress-bar">
  <div :style="{ width: progressPercentage + '%' }"></div>
</div>


      <!-- Form Section -->
      <div class="form-box">
        <h2 class="form-title">Create a New Trip</h2>
        <form @submit.prevent="createTrip">
          <div class="form-row">
            <div class="form-field">
              <label>Trip Name</label>
              <input type="text" placeholder="Enter trip name" v-model="tripName" />
            </div>
            <div class="form-field">
              <label>Start Location</label>
              <input
                id="start-location"
                type="text"
                placeholder="Enter start location"
                v-model="startLocation"
              />
            </div>
            <div class="form-field">
              <label>Destination</label>
              <input
                id="destination"
                type="text"
                placeholder="Enter destination"
                v-model="destination"
              />
            </div>
            <div class="form-field">
              <label>Travel Date</label>
              <input type="date" v-model="travelDate" />
            </div>
          </div>
          <div class="form-action">
            <button type="submit">Create Trip</button>
          </div>
        </form>
      </div>

      <!-- Map Section -->
      <div class="map-box">
        <h3>Preview Your Route</h3>
        <div id="map-preview"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tripName: "",
      startLocation: "",
      destination: "",
      travelDate: "",
      map: null,
      directionsService: null,
      directionsRenderer: null,
    };
  },
  mounted() {
    this.initMap();
    this.initAutocomplete();
  },

  computed: {
  progressPercentage() {
    let filledFields = 0;
    if (this.tripName) filledFields++;
    if (this.startLocation) filledFields++;
    if (this.destination) filledFields++;
    if (this.travelDate) filledFields++;
    return (filledFields / 4) * 100;
  },
},

  methods: {
    initMap() {
      this.map = new google.maps.Map(document.getElementById("map-preview"), {
        center: { lat: 39.8283, lng: -98.5795 }, // Center of the US
        zoom: 4,
      });
      this.directionsService = new google.maps.DirectionsService();
      this.directionsRenderer = new google.maps.DirectionsRenderer();
      this.directionsRenderer.setMap(this.map);
    },
    initAutocomplete() {
  const startLocationInput = document.getElementById("start-location");
  const destinationInput = document.getElementById("destination");

  const options = { types: ["geocode"] }; // Limit results to geocodes
  const startAutocomplete = new google.maps.places.Autocomplete(startLocationInput, options);
  const destinationAutocomplete = new google.maps.places.Autocomplete(destinationInput, options);

  // Update the v-model bindings when a place is selected
  startAutocomplete.addListener("place_changed", () => {
    const place = startAutocomplete.getPlace();
    this.startLocation = place.formatted_address || this.startLocation;
  });

  destinationAutocomplete.addListener("place_changed", () => {
    const place = destinationAutocomplete.getPlace();
    this.destination = place.formatted_address || this.destination;
  });
},

    createTrip() {
      if (!this.tripName || !this.startLocation || !this.destination || !this.travelDate) {
        alert("Please fill out all fields before creating a trip.");
        return;
      }

      const tripData = {
        name: this.tripName,
        startLocation: this.startLocation,
        destination: this.destination,
        travelDate: this.travelDate,
      };

      console.log("Trip created:", tripData);
      this.$router.push({
    name: "RouteCustomization",
    query: {
      tripName: this.tripName,
      startLocation: this.startLocation,
      destination: this.destination,
      travelDate: this.travelDate,
    },
  });
    },
    previewRoute() {
      if (this.startLocation && this.destination) {
        const request = {
          origin: this.startLocation,
          destination: this.destination,
          travelMode: google.maps.TravelMode.DRIVING,
        };

        this.directionsService.route(request, (result, status) => {
          if (status === google.maps.DirectionsStatus.OK) {
            this.directionsRenderer.setDirections(result);
          }
        });
      }
    },
  },
};
</script>

<style scoped>
.create-trip-container {
  background: url('@/assets/images/IMG-20211203-WA0009.jpg') no-repeat center center fixed;
  background-size: cover;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #181414fb;
  text-align: center;
}

.app-title {
  font-family: 'Blackberry Jam', cursive;
  font-size: 4rem;
  margin-bottom: 20px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
}

.content {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-start;
  gap: 20px;
  width: 90%;
  max-width: 1200px;
}

.image-box {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.image-box img {
  max-width: 100%;
  max-height: 100%;
  border-radius: 12px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.form-box {
  flex: 1.5;
  background: rgba(255, 255, 255, 0.356);
  border-radius: 12px;
  padding: 20px 40px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}
.progress-bar {
  height: 10px;
  background: #ccc;
  border-radius: 5px;
  overflow: hidden;
  margin-bottom: 20px;
}
.progress-bar div {
  height: 100%;
  background: #007bff;
  transition: width 0.3s ease-in-out;
}


.map-box {
  flex: 1;
  background: rgba(255, 255, 255, 0.8);
  border-radius: 12px;
  padding: 15px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

#map-preview {
  width: 100%;
  height: 250px;
  border-radius: 8px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

label {
  font-family: 'Blackberry Jam';
  display: block;
  font-weight: bold;
  margin-bottom: 6px;
  font-size: 1.2rem;
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1rem;
}

button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 1rem;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}
</style>
