import { useEffect, useState } from "react";
import {
  getCartByUserId,
  updateCartItem,
  removeFromCart
} from "../services/cartService";

import { getAllFoods } from "../services/foodService";

function Cart() {

  const [cartItems, setCartItems] = useState([]);
  const [foods, setFoods] = useState([]);
  const [loading, setLoading] = useState(true);

  const userId = 1;

  useEffect(() => {
    loadCart();
  }, []);

  const loadCart = async () => {

    try {

      const [cartResponse, foodResponse] =
        await Promise.all([
          getCartByUserId(userId),
          getAllFoods()
        ]);

      setCartItems(cartResponse.data);
      setFoods(foodResponse.data);

    } catch (error) {

      console.error("Cart Error:", error);

    } finally {

      setLoading(false);

    }
  };

  const getFood = (foodId) => {
    return foods.find((food) => food.id === foodId);
  };

  // Increase quantity
  const increaseQuantity = async (item) => {

    try {

      const request = {
        userId: item.userId,
        foodId: item.foodId,
        quantity: item.quantity + 1,
        price: item.price
      };

      await updateCartItem(item.id, request);

      loadCart();

    } catch (error) {

      console.error("Increase Error:", error);

    }
  };

  // Decrease quantity
  const decreaseQuantity = async (item) => {

    if (item.quantity === 1) {
      return;
    }

    try {

      const request = {
        userId: item.userId,
        foodId: item.foodId,
        quantity: item.quantity - 1,
        price: item.price
      };

      await updateCartItem(item.id, request);

      loadCart();

    } catch (error) {

      console.error("Decrease Error:", error);

    }
  };

  // Remove item
  const handleRemove = async (cartId) => {

    try {

      await removeFromCart(cartId);

      loadCart();

    } catch (error) {

      console.error("Remove Error:", error);

    }
  };

  const totalAmount = cartItems.reduce(
    (total, item) =>
      total + item.price * item.quantity,
    0
  );

  if (loading) {

    return (
      <div className="cart-page">
        <h2>Loading Cart...</h2>
      </div>
    );

  }

  return (

    <div className="cart-page">

      <h1>Your Cart 🛒</h1>

      {cartItems.length === 0 ? (

        <div className="empty-cart">

          <h2>Your cart is empty</h2>

          <p>
            Add some delicious food to continue.
          </p>

        </div>

      ) : (

        <div className="cart-container">

          {/* Cart Items */}

          <div className="cart-items">

            {cartItems.map((item) => {

              const food = getFood(item.foodId);

              return (

                <div
                  className="cart-item"
                  key={item.id}
                >

                  <div className="cart-food-image">

                    <img
                      src={food?.imageUrl}
                      alt={food?.name || "Food"}
                    />

                  </div>

                  <div className="cart-item-info">

                    <h3>
                      {food?.name ||
                        `Food ID: ${item.foodId}`}
                    </h3>

                    <p>
                      ₹{item.price}
                    </p>

                    {/* Quantity */}

                    <div className="quantity-control">

                      <button
                        onClick={() =>
                          decreaseQuantity(item)
                        }
                      >
                        −
                      </button>

                      <span>
                        {item.quantity}
                      </span>

                      <button
                        onClick={() =>
                          increaseQuantity(item)
                        }
                      >
                        +
                      </button>

                    </div>

                  </div>

                  <div className="cart-item-actions">

                    <div className="cart-item-total">
                      ₹{item.price * item.quantity}
                    </div>

                    <button
                      className="remove-btn"
                      onClick={() =>
                        handleRemove(item.id)
                      }
                    >
                      Remove
                    </button>

                  </div>

                </div>

              );

            })}

          </div>

          {/* Summary */}

          <div className="cart-summary">

            <h2>Order Summary</h2>

            <div className="summary-row">

              <span>Items</span>

              <span>
                {cartItems.reduce(
                  (total, item) =>
                    total + item.quantity,
                  0
                )}
              </span>

            </div>

            <div className="summary-row">

              <span>Total</span>

              <strong>
                ₹{totalAmount}
              </strong>

            </div>

            <button
  className="checkout-btn"
  onClick={() => window.location.href = "/checkout"}
>
  Proceed to Checkout
</button>

          </div>

        </div>

      )}

    </div>

  );
}

export default Cart;