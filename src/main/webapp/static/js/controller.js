import {dataHandler, getProductsByFilter} from "./dataHandler.js";
import {cartView,showProducts} from "./view.js";
import {cartModal} from "./dataHandler.js";
import {editCart} from "./dataHandler.js";

export {addEventCartButtons,setupEventListeners};

let buttons = document.querySelectorAll(".cart-button");
let cart = document.getElementById("cart")


function addEventCartButtons (){
    cart.addEventListener("click",function () {
        cartModal()
            .then(
                () => {
                    addEventEditButtons()
                }
            )
       })
    for (let button of buttons){
        button.addEventListener("click", function () {
            let buttonId = button.getAttribute("buttonId");
            dataHandler.fetchProductId(buttonId);
        })
    }
}


function addEventEditButtons(){
    let editButtons = document.querySelectorAll(".edit-button");
    for(let button of editButtons){
        button.addEventListener("click", function () {
            let value = button.getAttribute("data-value");
            let id = button.parentElement.getAttribute("data-product-id");
            editCart(value, id)
            cartModal()
                .then( () => {
                    addEventEditButtons();
                })
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

