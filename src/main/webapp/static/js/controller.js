import {dataHandler} from "./dataHandler.js";
import {cartView} from "./view";
export {addEventCartButtons};
let buttons = document.querySelectorAll(".cart-button");
let cart = document.getElementById("cart_icon")

function addEventCartButtons (){
    for (let button of buttons){
        button.addEventListener("click", function () {
            let buttonId = button.getAttribute("buttonId");
            dataHandler.fetchProductId(buttonId);
            cartView(buttonId);
        })
    }
    cart.addEventListener("click", cartModal() )
}


function cartModal() {
    alert("success")
}


