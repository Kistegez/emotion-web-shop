import {getProductsByFilter} from "./dataHandler.js";
import {showProducts} from "./view.js";

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

function setupEventListeners() {
    document.getElementById("categories").addEventListener("change", loadFilteredProducts)
    document.getElementById("suppliers").addEventListener("change", loadFilteredProducts)
    addClickEventCartButtons();

}

async function fetchUrl(productId) {
    let url;
    url = `/cart?product_id=${productId}`;
    await fetch(url);
}

async function loadFilteredProducts() {
    let categoryId = document.getElementById('categories').value;
    let supplierId = document.getElementById('suppliers').value;
    let filterProduct = await getProductsByFilter(categoryId, supplierId);
    showProducts(filterProduct);
}

setupEventListeners();

