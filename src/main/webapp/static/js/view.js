export {cartView};

let productPrice = document.querySelectorAll(".product-price");
function cartView(buttonId) {
    for (let price of productPrice) {
        let priceId = price.getAttribute("priceId");
        if (priceId === buttonId) {
            let priceInt = price.getAttribute("price");
            console.log(priceInt);
        }
    }
}