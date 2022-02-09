export {cartModal,getProductsByFilter};
import {showModal} from "./view.js";


export let dataHandler = {
    fetchProductId: async function (productId) {
        await urlResponse(`/cart?product_id=${productId}`);
    }
}
async function cartModal() {
    let datas = await getResponse("/api/review_cart")
    showModal(datas);

}

async function urlResponse(url){
    await fetch(url);
}


async function getResponse(url) {
    let response = await fetch(url);
    return response.json();
}

async function postResponse(url, data) {
    await fetch(url, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)}
    )
}

async function getProductsByFilter(categoryId, supplierId) {
    let response = await fetch(`/api/product?categoryId=${categoryId}&supplierId=${supplierId}`);
    return response.json();
}
