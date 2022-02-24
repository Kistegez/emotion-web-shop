import {dataHandler, getProductsByFilter, postResponse, logoutFetch} from "./dataHandler.js";
import {showModal, showProducts} from "./view.js";
import {cartModal} from "./dataHandler.js";
import {editCart} from "./dataHandler.js";

export {addEventCartButtons,setupEventListeners};

let buttons = document.querySelectorAll(".cart-button");
let cart = document.getElementById("cart")


function addEventCartButtons (){
    cart.addEventListener("click",function () {
        cartModal()
            .then(
                () => {
                    editTotalPrice()
                    addEventEditButtons()
                }
            )
       })
    for (let button of buttons){
        button.addEventListener("click", function () {
            let buttonId = button.getAttribute("buttonId");
            dataHandler.fetchProductId(buttonId);
        })
    }
}


function addEventEditButtons(){
    let editButtons = document.querySelectorAll(".edit-button");
    for(let button of editButtons){
        button.addEventListener("click", function () {
            let value = button.getAttribute("data-value");
            let id = button.parentElement.getAttribute("data-product-id");
            editCart(value, id)
            cartModal()
                .then( () => {
                    addEventEditButtons();
                    editTotalPrice();
                })
        })
    }
}

function editTotalPrice(){
    document.getElementById("total-cart-price").innerText = 0;
    let amountPrices = document.querySelectorAll(".product-total-price")
    for (let amountPrice of amountPrices){
        let price = parseFloat(amountPrice.innerText)
        let total = document.getElementById("total-cart-price").innerText;
        let totalPrice = parseFloat(total);
        let newPrice = price + totalPrice;
        document.getElementById("total-cart-price").innerText = newPrice.toFixed(2) + " $";

    }
}

function setupEventListeners() {
    document.getElementById("categories").addEventListener("change", loadFilteredProducts)
    document.getElementById("suppliers").addEventListener("change", loadFilteredProducts)
    document.getElementById("save").addEventListener("click", saveCart)
    addEventCartButtons();
    document.getElementById("register").addEventListener("click",registration)
    document.getElementById("login").addEventListener("click",login)
    document.getElementById("logout").addEventListener("click",logout)

}

async function loadFilteredProducts() {
    let categoryId = document.getElementById("categories").value;
    let supplierId = document.getElementById("suppliers").value;
    let filterProduct = await getProductsByFilter(categoryId, supplierId);
    showProducts(filterProduct);
    addEventListenerToAll(".cart-btn", addToCart);
}

async function addToCart(e) {
    const id = e.target.dataset.btnId;
    await dataHandler.fetchProductId(id);
}

function addEventListenerToAll(selector, func) {
    const elements = document.querySelectorAll(selector)
    for (let i = 0; i < elements.length; i++) {
        elements[i].addEventListener("click", func)
    }
}


async function saveCart() {
    await fetch(`/api/save_cart?user_id=${localStorage.getItem("user_id")}`)
}

async function registration() {
    let error = document.getElementById("register-error")
    let name = document.getElementById("reg-name").value
    let email = document.getElementById("email").value
    let password = document.getElementById("reg-psw").value
    if (email.includes('@') && email.includes('.')) {
        error.innerText = ""
        document.getElementById("reg-name").value = ""
        document.getElementById("email").value = ""
        document.getElementById("reg-psw").value = ""
        document.getElementById("reg-close").click()
        await postResponse("/api/registration", {"name": name, "email": email, "password": password}).then()

    } else {
        error.innerText = "Wrong email format. Please try again!"
    }
}

async function login() {
    let name = document.getElementById("log-name").value
    let password = document.getElementById("log-psw").value
    let login = await postResponse("/api/login", { "name" : name, "password" : password})
    if (login == null) {
        document.getElementById("login-error").innerText = "Email or Password was incorrect! Please try again!"
    }
    else {
        console.log(login)
        document.getElementById("log-name").value = ""
        document.getElementById("log-psw").value = ""
        localStorage.setItem("user_id", login.id)
        localStorage.setItem("user_name", login.name)
        document.getElementById("log-close").click()
        document.getElementById("user-name").style.display = "block"
        document.getElementById("name-place").innerText = "Hello " + login.name + "!"
        checkUserInSession()
        document.getElementById("logout").addEventListener('click', function () {
            logoutFetch()});
    }
}

function logout() {
    localStorage.removeItem("user_id")
    localStorage.removeItem("user_name")
    checkUserInSession()

}


function checkUserInSession() {
    if (localStorage.getItem("user_id") != null) {
        document.getElementById("login-modal").style.display = "none"
        document.getElementById("register-modal").style.display = "none"
        document.getElementById("logout").style.display = "block"
    } else {
        document.getElementById("login-modal").style.display = "block"
        document.getElementById("register-modal").style.display = "block"
        document.getElementById("logout").style.display = "none"
        document.getElementById("user-name").style.display = "none"
        document.getElementById("name-place").innerText = ""
        }
}


