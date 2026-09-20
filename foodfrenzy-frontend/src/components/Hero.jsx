function Hero() {
  return (
    <section className="hero">

      <div className="hero-content">

        <h1>
          Delicious Food,
          <br />
          Delivered Fast 🚀
        </h1>

        <p>
          Discover delicious meals from your favorite restaurants
          and get them delivered to your doorstep.
        </p>

        <div className="search-box">
          <input
            type="text"
            placeholder="Search for food or restaurants..."
          />

          <button>Search</button>
        </div>

        <button className="order-btn">
          Order Now
        </button>

      </div>

      <div className="hero-image">
        <div className="food-circle">
          🍕
        </div>
      </div>

    </section>
  );
}

export default Hero;