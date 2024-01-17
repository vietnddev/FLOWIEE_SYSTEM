/*
async function callApiDelete(apiURL) {
    let response = await fetch(apiURL, {
        method: 'DELETE'
    });
    if (response.ok && (await response.text() === 'OK')) {
        alert('Delete success!')
        window.location.reload()
    } else {
        alert('Delete fail!')
    }
}
*/

function callApiDelete(apiURL) {
    $.ajax({
        url: apiURL,
        type: 'DELETE',
        success: function(result) {
            // Xử lý kết quả nếu cần thiết
            alert(result.data)
            window.location.reload()
        },
        error: function(xhr, status, error) {
            // Xử lý lỗi nếu có
            alert(status + ': ' + JSON.stringify(xhr.responseJSON));
        }
    });
}