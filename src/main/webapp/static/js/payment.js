let totalPrice = 0;

function setupEventListener() {
    document.getElementById("company_true").addEventListener("click",hideElement);
    document.getElementById("company_false").addEventListener("click",hideElement);
    document.getElementById("paypal").addEventListener("click", hidePaying);
    document.getElementById("CreditCardInput").addEventListener("click", hidePaying);

}


function hideElement() {
    let billingCompanyField = document.getElementById("billing_input");
    if (billingCompanyField.style.display === "none") {
        billingCompanyField.style.display = "block";
    } else {
        billingCompanyField.style.display = "none";
    }
}

function hidePaying() {
    let creditCardInput = document.getElementById("CreditCard");
    let payPalInput = document.getElementById("payPal_input");
    if (creditCardInput.style.display === "none") {
        creditCardInput.style.display = "block";
        payPalInput.style.display = "none"
    } else {
        creditCardInput.style.display = "none"
        payPalInput.style.display = "block"
    }
}

async function showCart() {
    let products = await getCartProducts();
    let content = "";
    for (let product of products) {
        content += showProducts(product);
        totalPrice += parseInt(product.defaultPrice * product.amount)
    }
    document.getElementById("payment-cart").innerHTML = content;
    document.getElementById("total-price").innerHTML = totalPrice.toString() + " USD";
}

async function getCartProducts() {
    let response = await fetch("/api/review_cart");
    return response.json();
}


function showProducts(data) {
    return `<tr>
            <td class="w-25">
                <img src="/static/img/product_${data.id}.jpg"  alt="${data.name} + '.jpg'" class="image" >
            </td>
            <td>${data.name}</td>
            <td>${data.defaultPrice} ${data.defaultCurrency}</td>
            <td class="qty"><p id=${"amountId" + data.id} type="text" class="amount form-control" >${data.amount}</p></td>   
            <td id=${"product-total" + data.id} data-default-price=${data.defaultPrice} class="product-total-price" data-default-currency=${data.defaultCurrency}>${(data.defaultPrice * data.amount).toFixed(2)} ${data.defaultCurrency}</td>
        </tr>`
}


showCart();
setupEventListener();