export {cartModal,getProductsByFilter,editCart, postResponse};

import {showModal} from "./view.js";


export let dataHandler = {
    fetchProductId: async function (productId) {
        await urlResponse(`/cart?product_id=${productId}`);
    }
}

async function editCart(value, id){
    let url = await urlResponse(`/api/cart/edit?value=${value}&id=${id}`)
    await fetch(url)
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


async function getProductsByFilter(categoryId, supplierId) {
    return getResponse(`/api/product?categoryId=${categoryId}&supplierId=${supplierId}`);
}



async function postResponse(url, data) {
    return (await fetch(url, {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    })).json()}

