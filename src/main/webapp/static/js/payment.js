function setupEventListener() {
    document.getElementById("company_true").addEventListener("click",hideElement);
    document.getElementById("company_false").addEventListener("click",hideElement);
}


function hideElement() {
    let billingCompanyField = document.getElementById("billing_input");
    if (billingCompanyField.style.display === "none") {
        billingCompanyField.style.display = "block";
    } else {
        billingCompanyField.style.display = "none";
    }
}

setupEventListener();

