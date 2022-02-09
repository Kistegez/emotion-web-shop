import {dataHandler} from "./dataHandler.js";
import {cartView} from "./view.js";
import {cartModal} from "./dataHandler.js";

export {addEventCartButtons};

let buttons = document.querySelectorAll(".cart-button");
let cart = document.getElementById("cart")


function addEventCartButtons (){
    cart.addEventListener("click", cartModal() )
    for (let button of buttons){
        button.addEventListener("click", function () {
            let buttonId = button.getAttribute("buttonId");
            dataHandler.fetchProductId(buttonId);
            cartView(buttonId);
        })
    }
}



