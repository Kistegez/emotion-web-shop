let totalPrice = 0;
let previousCost = 0;


function addDeliveryCost(e) {
    totalPrice -= previousCost;
    let deliveryCost = parseInt(e.target.value);
    previousCost = deliveryCost;
    totalPrice += deliveryCost;
    document.getElementById("total-price").innerHTML = totalPrice.toString() + " USD";
}

function setupEventListener() {
    document.getElementById("company_true").addEventListener("click",hideElement);
    document.getElementById("company_false").addEventListener("click",hideElement);
    document.getElementById("paypal").addEventListener("click", hidePaying);
    document.getElementById("CreditCardInput").addEventListener("click", hidePaying);
    document.getElementById("standard-gross1").addEventListener("click", addDeliveryCost);
    document.getElementById("standard-gross2").addEventListener("click", addDeliveryCost);
    document.getElementById("standard-gross3").addEventListener("click", addDeliveryCost);

}


function hideElement(e) {
    let billingCompanyField = document.getElementById("billing_input");
    if (e.target.id === "company_false") {
        billingCompanyField.style.display = "block";
    } else {
        billingCompanyField.style.display = "none";
    }
}

function hidePaying(e) {
    let creditCardInput = document.getElementById("CreditCard");
    let payPalInput = document.getElementById("payPal_input");
    if (e.target.id === "CreditCardInput") {
        creditCardInput.style.display = "block";
        payPalInput.style.display = "none"
    } else {
        creditCardInput.style.display = "none"
        payPalInput.style.display = "block"
    }
}

async function showCart(e) {
    let products = await getCartProducts();
    let content = "";
    for (let product of products) {
        content += showProducts(product);
        totalPrice += parseInt(product.defaultPrice * product.amount)
    }
    document.getElementById("cart-body").innerHTML = content;
    document.getElementById("total-price").innerHTML = totalPrice.toString() + " USD";
}

async function getCartProducts() {
    let response = await fetch("/api/review_cart");
    return response.json();
}


function showProducts(data) {
return `<tr>
            <td class="w-25">
                <img src="/static/img/product_${data.id}.jpg"  alt="${data.name} + '.jpg'" class="image" width="60" >
            </td>
            <td>${data.name}</td> 
            <td id=${"product-total" + data.id} data-default-price=${data.defaultPrice} class="product-total-price" data-default-currency=${data.defaultCurrency}>${(data.defaultPrice * data.amount).toFixed(2)} ${data.defaultCurrency}</td>
        </tr>`
}

showCart();
setupEventListener();