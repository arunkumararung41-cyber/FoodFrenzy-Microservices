function FoodCategories() {

  const categories = [
    { name: "Pizza", icon: "🍕" },
    { name: "Burgers", icon: "🍔" },
    { name: "Biryani", icon: "🍛" },
    { name: "Chinese", icon: "🥡" },
    { name: "Desserts", icon: "🍰" },
    { name: "Healthy", icon: "🥗" },
    { name: "Drinks", icon: "🥤" }
  ];

  return (
    <section className="categories">

      <div className="section-title">
        <h2>Explore Categories</h2>
        <p>Choose what you're craving today</p>
      </div>

      <div className="category-list">

        {categories.map((category) => (
          <div className="category-card" key={category.name}>

            <div className="category-icon">
              {category.icon}
            </div>

            <h3>{category.name}</h3>

          </div>
        ))}

      </div>

    </section>
  );
}

export default FoodCategories;