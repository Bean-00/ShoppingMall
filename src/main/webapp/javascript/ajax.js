
function sendGetAjax (url, successCallback) {
    sendAjax(url, 'GET', null, 'json', successCallback)
}

function sendPostAjax (url, body, successCallback) {
    sendAjax(url, 'POST', body, 'text', successCallback)
}

function sendPutAjax (url, body, successCallback) {
    sendAjax(url, 'PUT', body, 'text', successCallback)
}

function sendDeleteAjax (url, successCallback) {
    sendAjax(url, 'DELETE', null, 'text', successCallback)
}

function sendAjax (url, method, body, dataType, successCallback) {
    //contentType: request body type, dataType: response body type
    $.ajax({
        url,
        type: method,
        data: JSON.stringify(body),
        contentType: 'application/json',
        dataType: dataType,
        success: successCallback,
        error(xhr, status, error) {
            console.error("ERROR !", error)
        }
    })
}

