<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <body>
        <div th:fragment="modal_dialog" class="modal fade text-center" id="warningModal"><!-- bootstrap modal -->
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="modalTitle">Warning</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">
                        <span id="modalBody"></span>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div th:fragment="confirm_modal" class="modal fade" id="confirmModal"><!-- bootstrap modal -->
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <strong class="modal-title">Xác nhận xóa</strong>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class="modal-body">
                        <span id="confirmText"></span>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-sm btn-danger" href="" id="yesButton">Yes</button>
                        <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">No</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>