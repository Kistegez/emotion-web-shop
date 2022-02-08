export let dataHandler = {
    fetchProductId: async function (productId) {
        await urlResponse(`/cart?product_id=${productId}`);
    }
}

async function urlResponse(url){
    await fetch(url);
}

async function getResponse(url) {
    const response = await fetch(url);
    return response.json();
}

async function postResponse(url, data) {
    await fetch(url, {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    })
}