import {dataHandler, getProductsByFilter} from "./dataHandler.js";
import {cartView,showProducts} from "./view.js";
import {cartModal} from "./dataHandler.js";

export {addEventCartButtons,setupEventListeners};

let buttons = document.querySelectorAll(".cart-button");
let cart = document.getElementById("cart")


function addEventCartButtons () {
    cart.addEventListener("click", function () {
        cartModal()
    })
    for (let button of buttons) {
        button.addEventListener("click", function () {
            let buttonId = button.getAttribute("buttonId");
            dataHandler.fetchProductId(buttonId);
            cartView(buttonId);
        })
    }
}

function setupEventListeners() {
    document.getElementById("categories").addEventListener("change", loadFilteredProducts)
    document.getElementById("suppliers").addEventListener("change", loadFilteredProducts)
    addEventCartButtons();

}

async function loadFilteredProducts() {
    let categoryId = document.getElementById('categories').value;
    let supplierId = document.getElementById('suppliers').value;
    let filterProduct = await getProductsByFilter(categoryId, supplierId);
    showProducts(filterProduct);
}




