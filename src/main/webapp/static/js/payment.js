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

setupEventListener();

