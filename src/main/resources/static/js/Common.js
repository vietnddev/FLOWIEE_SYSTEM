//Host
const mvHostURLCallApi = 'http://localhost:8085/api/v1';
const mvHostURL = 'http://localhost:8085';

//Language to use
let mvLang = "vi";

//User login info
const mvCurrentAccountId = '';
const mvCurrentAccountUsername = '';

//Config product status
const mvProductStatus = {};
mvProductStatus["A"] = "Đang kinh doanh";
mvProductStatus["I"] = "Ngừng kinh doanh";

//Config ticket import status
const mvTicketImportStatus = {};
mvTicketImportStatus["DRAFT"] = "Nháp";
mvTicketImportStatus["COMPLETED"] = "Hoàn thành";
mvTicketImportStatus["CANCEL"] = "Hủy";

//Config ticket export status
const mvTicketExportStatus = {};
mvTicketExportStatus["DRAFT"] = "Nháp";
mvTicketExportStatus["COMPLETED"] = "Hoàn thành";
mvTicketExportStatus["CANCEL"] = "Hủy";

const mvStorageStatusInit = {};
mvStorageStatusInit["Y"] = "Đang sử dụng";
mvStorageStatusInit["N"] = "Không sử dụng";

//Pagination
const mvPageSizeDefault = 10;
function updatePaginationUI(pageNum, pageSize, totalPage, totalElements) {
    $('#paginationInfo').attr("pageNum", pageNum);
    $('#paginationInfo').attr("pageSize", pageSize);
    $('#paginationInfo').attr("totalPage", totalPage);
    $('#paginationInfo').attr("totalElements", totalElements);
    $('#pageNum').text(pageNum);

    let startCount = (pageNum - 1) * pageSize + 1;
    let endCount = startCount + pageSize - 1;
    if (endCount > totalElements) {
        endCount = totalElements;
    }
    $('#paginationInfo').text("Showing " + startCount + " to " + endCount + " of " + totalElements + " entries");

    $('#totalPages').text("Total pages " + totalPage);

    if(pageNum === 1) {
        $('#firstPage').attr("disable", "");
        $('#previousPage').attr("disable", "");
    }
}

function updatePaginationUI2(pageNum, pageSize, totalPage, totalElements) {
    $('#paginationInfo2').attr("pageNum", pageNum);
    $('#paginationInfo2').attr("pageSize", pageSize);
    $('#paginationInfo2').attr("totalPage", totalPage);
    $('#paginationInfo2').attr("totalElements", totalElements);
    $('#pageNum2').text(pageNum);

    let startCount = (pageNum - 1) * pageSize + 1;
    let endCount = startCount + pageSize - 1;
    if (endCount > totalElements) {
        endCount = totalElements;
    }
    $('#paginationInfo2').text("Showing " + startCount + " to " + endCount + " of " + totalElements + " entries");

    $('#totalPages2').text("Total pages " + totalPage);

    if(pageNum === 1) {
        $('#firstPage2').attr("disable", "");
        $('#previousPage2').attr("disable", "");
    }
}

function updatePaginationUI3(pageNum, pageSize, totalPage, totalElements) {
    $('#paginationInfo3').attr("pageNum", pageNum);
    $('#paginationInfo3').attr("pageSize", pageSize);
    $('#paginationInfo3').attr("totalPage", totalPage);
    $('#paginationInfo3').attr("totalElements", totalElements);
    $('#pageNum3').text(pageNum);

    let startCount = (pageNum - 1) * pageSize + 1;
    let endCount = startCount + pageSize - 1;
    if (endCount > totalElements) {
        endCount = totalElements;
    }
    $('#paginationInfo3').text("Showing " + startCount + " to " + endCount + " of " + totalElements + " entries");

    $('#totalPages3').text("Total pages " + totalPage);

    if(pageNum === 1) {
        $('#firstPage3').attr("disable", "");
        $('#previousPage3').attr("disable", "");
    }
}

function updateTableContentWhenOnClickPagination(loadNewDataMethod) {
    let lvPageSize = $('#selectPageSize').val();
    $('#selectPageSize').on('click', function() {
        console.log($(this).val())
        if (lvPageSize === $(this).val()) {
            return;
        }
        lvPageSize = $(this).val();
        loadNewDataMethod($(this).val(), 1);
    });

    $('#firstPage').on('click', function() {
        if (parseInt($('#paginationInfo').attr("pageNum")) === 1) {
            return;
        }
        loadNewDataMethod(lvPageSize, 1);
    });

    $('#previousPage').on('click', function() {
        if (parseInt($('#paginationInfo').attr("pageNum")) === 1) {
            return;
        }
        loadNewDataMethod(lvPageSize, $('#paginationInfo').attr("pageNum") - 1);
    });

    $('#nextPage').on('click', function() {
        if ($('#paginationInfo').attr("pageNum") === $('#paginationInfo').attr("totalPage")) {
            return;
        }
        loadNewDataMethod(lvPageSize, parseInt($('#paginationInfo').attr("pageNum")) + 1);
    });

    $('#lastPage').on('click', function() {
        if ($('#paginationInfo').attr("pageNum") === $('#paginationInfo').attr("totalPage")) {
            return;
        }
        loadNewDataMethod(lvPageSize, $('#paginationInfo').attr("totalPage"));
    });
}

function updateTableContentWhenOnClickPagination2(loadNewDataMethod) {
    let lvPageSize = $('#selectPageSize2').val();
    $('#selectPageSize2').on('click', function() {
        console.log($(this).val())
        if (lvPageSize === $(this).val()) {
            return;
        }
        lvPageSize = $(this).val();
        loadNewDataMethod($(this).val(), 1);
    });

    $('#firstPage2').on('click', function() {
        if (parseInt($('#paginationInfo2').attr("pageNum")) === 1) {
            return;
        }
        loadNewDataMethod(lvPageSize, 1);
    });

    $('#previousPage2').on('click', function() {
        if (parseInt($('#paginationInfo2').attr("pageNum")) === 1) {
            return;
        }
        loadNewDataMethod(lvPageSize, $('#paginationInfo2').attr("pageNum") - 1);
    });

    $('#nextPage2').on('click', function() {
        if ($('#paginationInfo2').attr("pageNum") === $('#paginationInfo2').attr("totalPage")) {
            return;
        }
        loadNewDataMethod(lvPageSize, parseInt($('#paginationInfo2').attr("pageNum")) + 1);
    });

    $('#lastPage2').on('click', function() {
        if ($('#paginationInfo2').attr("pageNum") === $('#paginationInfo2').attr("totalPage")) {
            return;
        }
        loadNewDataMethod(lvPageSize, $('#paginationInfo2').attr("totalPage"));
    });
}

function updateTableContentWhenOnClickPagination3(loadNewDataMethod) {
    let lvPageSize = $('#selectPageSize3').val();
    $('#selectPageSize3').on('click', function() {
        console.log($(this).val())
        if (lvPageSize === $(this).val()) {
            return;
        }
        lvPageSize = $(this).val();
        loadNewDataMethod($(this).val(), 1);
    });

    $('#firstPage3').on('click', function() {
        if (parseInt($('#paginationInfo3').attr("pageNum")) === 1) {
            return;
        }
        loadNewDataMethod(lvPageSize, 1);
    });

    $('#previousPage3').on('click', function() {
        if (parseInt($('#paginationInfo3').attr("pageNum")) === 1) {
            return;
        }
        loadNewDataMethod(lvPageSize, $('#paginationInfo3').attr("pageNum") - 1);
    });

    $('#nextPage3').on('click', function() {
        if ($('#paginationInfo3').attr("pageNum") === $('#paginationInfo3').attr("totalPage")) {
            return;
        }
        loadNewDataMethod(lvPageSize, parseInt($('#paginationInfo3').attr("pageNum")) + 1);
    });

    $('#lastPage3').on('click', function() {
        if ($('#paginationInfo3').attr("pageNum") === $('#paginationInfo3').attr("totalPage")) {
            return;
        }
        loadNewDataMethod(lvPageSize, $('#paginationInfo3').attr("totalPage"));
    });
}

//Search tool
function setupSearchTool() {
    let brandFilter = $('#brandFilter');
    let productTypeFilter = $('#productTypeFilter');
    let colorFilter = $('#colorFilter');
    let sizeFilter = $('#sizeFilter');
    let unitFilter = $('#unitFilter');
    let discountFilter = $('#discountFilter');
    let productStatusFilter = $('#productStatusFilter');
    let branchFilter = $('#branchFilter');
    let paymentMethodFilter = $('#paymentMethodFilter');
    let salesChannelFilter = $('#salesChannelFilter');
    let orderStatusFilter = $('#orderStatusFilter');
    let paymentStatusFilter = $('#paymentStatusFilter');
    let orderTypeFilter = $('#orderTypeFilter');
    let shipMethodFilter = $('#shipMethodFilter');
    let groupCustomerFilter = $('#groupCustomerFilter');
    let dateFilter = $('#dateFilter');

    $("#btnOpenSearchAdvance").on("click", function () {
        clearSearchSelection(sizeFilter, 'Chọn kích cỡ');
        clearSearchSelection(unitFilter, 'Chọn đơn vị tính');
        clearSearchSelection(brandFilter, 'Chọn nhãn hiệu');
        clearSearchSelection(branchFilter, 'Chọn chi nhánh');
        clearSearchSelection(colorFilter, 'Chọn màu sắc');
        clearSearchSelection(discountFilter, 'Chọn khuyến mãi');
        clearSearchSelection(orderTypeFilter, 'Chọn loại đơn hàng');
        clearSearchSelection(shipMethodFilter, 'Chọn hình thức giao hàng');
        clearSearchSelection(orderStatusFilter, 'Chọn trạng thái đơn hàng');
        clearSearchSelection(productTypeFilter, 'Chọn loại sản phẩm');
        clearSearchSelection(salesChannelFilter, 'Chọn kênh bán hàng');
        clearSearchSelection(productStatusFilter, 'Chọn trạng thái sản phẩm');
        clearSearchSelection(paymentMethodFilter, 'Chọn hình thức thanh toán');
        clearSearchSelection(paymentStatusFilter, 'Chọn trạng thái thanh toán');
        clearSearchSelection(groupCustomerFilter, 'Chọn nhóm khách hàng');
        clearSearchSelection(dateFilter, 'Lọc theo thời gian');

        let keySearch = [];
        $.each($('.search-selection'), function (index, d) {
            keySearch.push($(d).attr('entity'));
        })

        $.each(keySearch, function (index, key) {
            switch (key) {
                case "BRAND":
                    downloadCategoryForSelection(brandFilter, mvHostURLCallApi + '/category/brand');
                    break;
                case "PRODUCT_TYPE":
                    downloadCategoryForSelection(productTypeFilter, mvHostURLCallApi + '/category/product-type');
                    break;
                case "COLOR":
                    downloadCategoryForSelection(colorFilter, mvHostURLCallApi + '/category/color');
                    break;
                case "SIZE":
                    downloadCategoryForSelection(sizeFilter, mvHostURLCallApi + '/category/size');
                    break;
                case "UNIT":
                    downloadCategoryForSelection(unitFilter, mvHostURLCallApi + '/category/unit');
                    break;
                case "BRANCH":
                    $.get((mvHostURLCallApi + '/system/branch/all'), function (response) {
                        if (response.status === "OK") {
                            $.each(response.data, function (index, d) {
                                branchFilter.append('<option value=' + d.id + '>' + d.branchName + '</option>');
                            });
                        }
                    }).fail(function () {
                        showErrorModal("Could not connect to the server");
                    });
                    break;
                case "PAYMENT_METHOD":
                    downloadCategoryForSelection(paymentMethodFilter, mvHostURLCallApi + '/category/payment-method');
                    break;
                case "SALES_CHANNEL":
                    downloadCategoryForSelection(salesChannelFilter, mvHostURLCallApi + '/category/sales-channel');
                    break;
                case "ORDER_STATUS":
                    downloadCategoryForSelection(orderStatusFilter, mvHostURLCallApi + '/category/order-status');
                    break;
                case "PAYMENT_STATUS":
                    downloadCategoryForSelection(paymentStatusFilter, mvHostURLCallApi + '/category/payment-status');
                    break;
                case "ORDER_TYPE":
                    downloadCategoryForSelection(orderTypeFilter, mvHostURLCallApi + '/category/order-type');
                    break;
                case "SHIP_METHOD":
                    downloadCategoryForSelection(shipMethodFilter, mvHostURLCallApi + '/category/ship-method');
                    break;
                case "GROUP_CUSTOMER":
                    downloadCategoryForSelection(groupCustomerFilter, mvHostURLCallApi + '/category/group-customer');
                    break;
                case "DISCOUNT":
                    discountFilter.append(`<option value="Y">Đang áp dụng</option>
                                           <option value="N">Không áp dụng</option>`);
                    break;
                case "PRODUCT_STATUS":
                    productStatusFilter.append(`<option value="ACTIVE">Đang kinh doanh</option>
                                                <option value="INACTIVE">Không kinh doanh</option>`);
                    break;
                case "DATE_FILTER":
                        dateFilter.append(`<option value="T0">Hôm nay</option>
                                           <option value="T-1">Hôm qua</option>
                                           <option value="T-7">7 ngày trước</option>
                                           <option value="M0">Tháng này</option>
                                           <option value="M-1">Tháng trước</option>`);
            }
        })
    })
}

function clearSearchSelection(element, defaultOption) {
    element.empty();
    if (defaultOption != null) {
        element.append(`<option value="">${defaultOption}</option>`);
    }
}

let convertDateT1 = (dateInput) => {
    let dateObject = new Date(dateInput);
    let year = dateObject.getFullYear();
    let month = dateObject.getMonth() + 1; // Tháng (đánh số từ 0)
    let day = dateObject.getDate();
    // Format lại chuỗi ngày tháng thành 'dd/MM/yyyy'
    return formattedDate = (day < 10 ? '0' : '') + day + '/' + (month < 10 ? '0' : '') + month + '/' + year;
}

function setupSelectMultiple() {
    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2()

        //Initialize Select2 Elements
        $('.select2bs4').select2({
            theme: 'bootstrap4'
        })

        //Bootstrap Duallistbox
        $('.duallistbox').bootstrapDualListbox()

        $("input[data-bootstrap-switch]").each(function () {
            $(this).bootstrapSwitch('state', $(this).prop('checked'));
        })

        //Date and time picker
        $('#reservationdatetime').datetimepicker({icons: {time: 'far fa-clock'}});
        //Timepicker
        $('#timepicker').datetimepicker({
            format: 'LT'
        })

        //Date range picker
        $('#reservation').daterangepicker()
    })
}