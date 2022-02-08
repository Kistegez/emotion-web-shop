export {getPrice};
import {cartView} from "./view.js";

let productPrice = document.querySelectorAll(".product-price");
function getPrice(buttonId) {
    for (let price of productPrice) {
        let priceId = price.getAttribute("priceId");
        if (priceId === buttonId) {
            let priceInt = price.getAttribute("price");
            return priceInt
        }
    }
}

