import { useEffect, useState } from "react";
import { getAllFoods } from "../services/foodService";
import { addToCart } from "../services/cartService";

function FoodList() {

  const [foods, setFoods] = useState([]);
  const [message, setMessage] = useState("");

  const userId = 1;

  useEffect(() => {
    getAllFoods()
      .then((response) => {
        console.log("FOODS:", response.data);
        setFoods(response.data);
      })
      .catch((error) => {
        console.error("ERROR:", error);
      });
  }, []);

  const handleAddToCart = (food) => {

    const cartData = {
      userId: userId,
      foodId: food.id,
      quantity: 1,
      price: food.price
    };

    addToCart(cartData)
      .then((response) => {

        console.log("Cart Response:", response.data);

        setMessage(`${food.name} added to cart 🛒`);

        setTimeout(() => {
          setMessage("");
        }, 2000);

      })
      .catch((error) => {

        console.error("Cart Error:", error);

        setMessage("Failed to add item to cart");

        setTimeout(() => {
          setMessage("");
        }, 2000);

      });
  };

  return (
    <section className="food-section">

      <div className="section-title">
        <h2>Popular Food</h2>
        <p>Delicious food waiting for you</p>
      </div>

      {message && (
        <div className="cart-message">
          {message}
        </div>
      )}

      <div className="food-grid">

        {foods.map((food) => (

          <div className="food-card" key={food.id}>

            <div className="food-image">
              <img
                src={food.imageUrl}
                alt={food.name}
              />
            </div>

            <div className="food-details">

              <h3>{food.name}</h3>

              <p className="food-description">
                {food.description}
              </p>

              <div className="food-bottom">

                <span className="food-price">
                  ₹{food.price}
                </span>

                <button
                  className="add-cart-btn"
                  onClick={() => handleAddToCart(food)}
                >
                  Add to Cart
                </button>

              </div>

            </div>

          </div>

        ))}

      </div>

    </section>
  );
}

export default FoodList;