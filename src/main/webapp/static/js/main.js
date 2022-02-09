import {addEventCartButtons} from "./controller.js";

function main(){
    let cart = document.getElementById("cart")
    cart.addEventListener("click", cartModal() )
    addEventCartButtons();
}



function cartModal() {
    fetch("/api/review_cart")
        .then((res)=> res.json())
        .then((datas) => showModal(datas))

}

function showModal(datas) {
    let content = ""
    for (let data of datas){
        content += createOneNew(data)
    }
    document.getElementById("cart-body").innerHTML = content;
}

function createOneNew(data){
    return `<tr>
            <td class="w-25">
                <img src="/static/img/product_${data.id}.jpg"  alt="${data.name} + '.jpg'" class="image" >
            </td>
            <td>${data.name}</td>
            <td>${data.defaultPrice} ${data.defaultCurrency}</td>
            <td class="qty"><p id=${"amountId" + data.id} type="text" class="amount form-control" >${data.amount}</p></td>
            <td id=${"product-total" + data.id} data-default-price=${data.defaultPrice} data-default-currency=${data.defaultCurrency}>${data.defaultPrice * data.amount} ${data.defaultCurrency}</td>
            <td data-product-id=${data.id}>
                <h2 data-value="1" class="edit">+</h2>
                <h2 data-value="-1" class="edit"> -</h2>
            </td>
        </tr>`
}

window.addEventListener('load', main());