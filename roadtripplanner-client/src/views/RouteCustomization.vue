<template>
    <div class="route-customization-container">
      <h1 class="page-title">Customize Your Road Trip</h1>
      <div class="map-container">
        <div id="map"></div>
      </div>
      <div class="action-buttons">
        <button @click="finalizeRoute">Finalize Route</button>
        <button @click="resetStops">Reset Stops</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        map: null,
        directionsService: null,
        directionsRenderer: null,
        startLocation: "",
        destination: "",
        stops: [], // Array to store stops along the route
      };
    },
    mounted() {
      // Initialize map and route after the component is mounted
      this.initializeMap();
      this.plotRoute();
    },
    methods: {
      initializeMap() {
        // Create a Google Map instance
        this.map = new google.maps.Map(document.getElementById("map"), {
          center: { lat: 39.8283, lng: -98.5795 }, // Center of the US
          zoom: 4,
        });
  
        // Initialize DirectionsService and DirectionsRenderer
        this.directionsService = new google.maps.DirectionsService();
        this.directionsRenderer = new google.maps.DirectionsRenderer({
          draggable: true, // Allow route customization
        });
        this.directionsRenderer.setMap(this.map);
  
        // Listen for clicks to add stops
        this.map.addListener("click", (event) => {
          this.addStop(event.latLng);
        });
      },
      plotRoute() {
        // Retrieve trip details from route parameters
        this.startLocation = this.$route.params.startLocation;
        this.destination = this.$route.params.destination;
  
        // if (!this.startLocation || !this.destination) {
        //   alert("Start location and destination are required!");
        //   return;
        // }
  
        // Generate the initial route
        const request = {
          origin: this.startLocation,
          destination: this.destination,
          travelMode: google.maps.TravelMode.DRIVING,
          waypoints: this.stops.map((stop) => ({
            location: stop,
            stopover: true,
          })),
        };
  
        this.directionsService.route(request, (result, status) => {
          if (status === google.maps.DirectionsStatus.OK) {
            this.directionsRenderer.setDirections(result);
          } else {
            alert("Could not generate route: " + status);
          }
        });
      },
      addStop(latLng) {
        // Add the clicked location as a stop
        this.stops.push(latLng);
  
        // Recalculate the route with new stops
        this.plotRoute();
      },
      resetStops() {
        // Clear all stops and recalculate the route
        this.stops = [];
        this.plotRoute();
      },
      finalizeRoute() {
        // Handle route finalization (e.g., save to database or proceed to summary)
        alert("Route finalized with the following stops: " + this.stops.length);
      },
    },
  };
  </script>
  
  <style scoped>
  .route-customization-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
  }
  
  .page-title {
    font-family: 'Blackberry Jam', cursive;
    font-size: 3rem;
    color: #333;
    margin-bottom: 20px;
  }
  
  .map-container {
    width: 100%;
    max-width: 1200px;
    height: 500px;
    border: 1px solid #ccc;
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
  }
  
  #map {
    width: 100%;
    height: 100%;
  }
  
  .action-buttons {
    margin-top: 20px;
    display: flex;
    gap: 10px;
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
  