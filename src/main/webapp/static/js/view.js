export {showModal,showProducts}


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
            <td id=${"product-total" + data.id} data-default-price=${data.defaultPrice} class="product-total-price" data-default-currency=${data.defaultCurrency}>${(data.defaultPrice * data.amount).toFixed(2)} ${data.defaultCurrency}</td>
            <td data-product-id=${data.id}>
                <button class= "edit-button" data-value="1" class="edit">+</button>
                <button class= "edit-button" data-value="-1" class="edit">-</button>
            </td>
        </tr>`
}
function showProducts(products) {
    let content = ""
    for (let product of products) {
        content += buildCard(product);
    }
    document.getElementById("products").innerHTML = content;
}

function buildCard(product) {
    return `<div class="col col-sm-12 col-md-6 col-lg-4">
                <div class="card">
                    <img class="" src="/static/img/product_${product.id}.jpg" alt="http://placehold.it/400x250/000/fff"/>
                    <div class="card-header">
                        <h4 class="card-title">${product.name}</h4>
                        <p class="card-text">${product.description}</p>
                    </div>
                    <div class="card-body">
                        <div class="card-text">
                            <p class="lead">${product.defaultPrice} ${product.defaultCurrency}</p>
                        </div>
                        <div class="card-text">
                        <button data-btn-id="${product.id}" class="btn btn-success cart-btn">Add to cart</button>
                        </div>
                    </div>
                </div>
            </div>`;
}