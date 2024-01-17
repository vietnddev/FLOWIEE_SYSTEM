function showErrorModal(message) {
    showModalDialog("Error", message);
}

function showModalDialog(title, message) {
    $("#modalTitle").text(title);
    $("#modalBody").text(message);
    $("#modalDialog").modal();
}

function showConfirmModal(linkObject, title, text) {
    let entity = linkObject.attr("entity");
    let entityId = linkObject.attr("entityId");//link là 1 đối tượng JQuery -->lấy ra giá trị của thuộc tính entityId
    let entityName = linkObject.attr("entityName");
    let actionType = linkObject.attr("actionType");

    $("#yesButton").attr("entity", linkObject.attr("entity"));
    $("#yesButton").attr("entityId", linkObject.attr("entityId"));
    $("#yesButton").attr("entityName", linkObject.attr("entityName"));
    $("#yesButton").attr("actionType", linkObject.attr("actionType"));

    if (actionType === 'create') {
        $("#confirmTitle").text(title);
        $("#confirmText").text(text);
    }
    if (actionType === 'delete') {
        $("#confirmTitle").text("Xác nhận xóa");
        $("#confirmText").text("Bạn chắc chắn muốn xóa: " + entityName + "?");
    }
    $("#confirmModal").modal();//hiển thị modal
}