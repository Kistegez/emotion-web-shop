import {dataHandler} from "./dataHandler.js";
import {getPrice} from "./domHandler.js";
export {addEventCartButtons};
let buttons = document.querySelectorAll(".cart-button");

function addEventCartButtons (){
    for (let button of buttons){
        button.addEventListener("click", function () {
            let buttonId = button.getAttribute("buttonId");
            dataHandler.fetchProductId(buttonId);
            getPrice(buttonId)
        })
    }
}


