import {addEventCartButtons} from "./controller.js";


function main(){
    addEventCartButtons();
}


main();

function setupEventListeners() {
    document.getElementById("categories").addEventListener("change", loadFilteredProducts)
    document.getElementById("suppliers").addEventListener("change", loadFilteredProducts)
    addClickEventCartButtons();

}

async function loadFilteredProducts() {
    let categoryId = document.getElementById('categories').value;
    let supplierId = document.getElementById('suppliers').value;
    let filterProduct = await getProductsByFilter(categoryId, supplierId);
    showProducts(filterProduct);
}

setupEventListeners();

