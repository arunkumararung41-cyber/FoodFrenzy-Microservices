import { useEffect, useState } from "react";
import { getCartByUserId } from "../services/cartService";
import { createOrder } from "../services/orderService";
function Checkout() {

  const [cartItems, setCartItems] = useState([]);
  const [paymentMethod, setPaymentMethod] = useState("CASH_ON_DELIVERY");

  const userId = 1;

  useEffect(() => {

    getCartByUserId(userId)
      .then((response) => {
        setCartItems(response.data);
      })
      .catch((error) => {
        console.error("Checkout Cart Error:", error);
      });

  }, []);

  const totalAmount = cartItems.reduce(
    (total, item) =>
      total + item.price * item.quantity,
    0
  );

const handlePlaceOrder = async () => {

  try {

    const orderData = {
      userId: userId,
      paymentMethod: paymentMethod
    };

    console.log("Creating Order:", orderData);

    const response = await createOrder(orderData);

    console.log("Order Created:", response.data);

    alert("Order placed successfully! 🎉");

  }catch (error) {

  console.error("Order Error:", error);

  console.log(
    "Backend Response:",
    error.response?.data
  );

  alert(
    error.response?.data?.message ||
    JSON.stringify(error.response?.data) ||
    "Failed to place order"
  );
}
};

  return (

    <div className="checkout-page">

      <h1>Checkout</h1>

      <div className="checkout-container">

        {/* Payment Section */}

        <div className="payment-section">

          <h2>Payment Method</h2>

          <label className="payment-option">

            <input
              type="radio"
              value="CASH_ON_DELIVERY"
              checked={paymentMethod === "CASH_ON_DELIVERY"}
              onChange={(e) =>
                setPaymentMethod(e.target.value)
              }
            />

            Cash on Delivery

          </label>

          <label className="payment-option">

            <input
              type="radio"
              value="UPI"
              checked={paymentMethod === "UPI"}
              onChange={(e) =>
                setPaymentMethod(e.target.value)
              }
            />

            UPI

          </label>

          <label className="payment-option">

            <input
              type="radio"
              value="CREDIT_CARD"
              checked={paymentMethod === "CREDIT_CARD"}
              onChange={(e) =>
                setPaymentMethod(e.target.value)
              }
            />

            Credit Card

          </label>

          <label className="payment-option">

            <input
              type="radio"
              value="DEBIT_CARD"
              checked={paymentMethod === "DEBIT_CARD"}
              onChange={(e) =>
                setPaymentMethod(e.target.value)
              }
            />

            Debit Card

          </label>

        </div>

        {/* Order Summary */}

        <div className="checkout-summary">

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
            className="place-order-btn"
            onClick={handlePlaceOrder}
          >
            Place Order
          </button>

        </div>

      </div>

    </div>

  );
}

export default Checkout;