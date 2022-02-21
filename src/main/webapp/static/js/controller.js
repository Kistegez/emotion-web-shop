import {dataHandler, getProductsByFilter, sendProductToCart} from "./dataHandler.js";
import {showProducts} from "./view.js";
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
                    editTotalPrice()
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
                    editTotalPrice();
                })
        })
    }
}

function editTotalPrice(){
    document.getElementById("total-cart-price").innerText = 0;
    let amountPrices = document.querySelectorAll(".product-total-price")
    for (let amountPrice of amountPrices){
        let price = parseFloat(amountPrice.innerText)
        let total = document.getElementById("total-cart-price").innerText;
        let totalPrice = parseFloat(total);
        let newPrice = price + totalPrice;
        document.getElementById("total-cart-price").innerText = newPrice.toFixed(2) + " $";

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
    addEventListenerToAll(".cart-btn", addToCart);
}

async function addToCart(e) {
    const id = e.target.dataset.btnId;
    await dataHandler.fetchProductId(id);
}

function addEventListenerToAll(selector, func) {
    const elements = document.querySelectorAll(selector)
    for (let i = 0; i < elements.length; i++) {
        elements[i].addEventListener('click', func)
    }
}


