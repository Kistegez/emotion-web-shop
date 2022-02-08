let buttons = document.querySelectorAll(".cart-button");
let productPrice = document.querySelectorAll(".product-price");
let productName = document.querySelectorAll(".product-name");

function addClickEventCartButtons(){
    for (let button of buttons){
        button.addEventListener("click", function () {
            let buttonId = button.getAttribute("buttonId");
            console.log(buttonId);
            fetchUrl(buttonId);

            for (price of productPrice) {
                let priceId = price.getAttribute("priceId");
                if (priceId === buttonId){
                    let priceInt = price.getAttribute("price");
                    console.log(priceInt)
                }
            }
        })
    }
}

async function fetchUrl(productId) {
    url = `/?product_id=${productId}`;
    await fetch(url);
}

addClickEventCartButtons();