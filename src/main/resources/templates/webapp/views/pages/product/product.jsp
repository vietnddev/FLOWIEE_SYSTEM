<!DOCTYPE html>
<html lang="en" xmlns:th="https://www.thymeleaf.org">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Danh sách sản phẩm</title>
        <div th:replace="header :: stylesheets">
            <!--Nhúng các file css, icon,...-->
        </div>
        <style rel="stylesheet">
            .table td,
            th {
                vertical-align: middle;
            }
        </style>
    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
        <div class="wrapper">
            <div th:replace="header :: header"></div>

            <div th:replace="sidebar :: sidebar"></div>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper" style="padding-top: 10px; padding-bottom: 1px;">
                <!-- Main content -->
                <section class="content">
                    <div class="container-fluid">
                        <!-- Small boxes (Stat box) -->
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-header">
                                        <div class="row justify-content-between">
                                            <div class="col-4" style="display: flex; align-items: center">
                                                <h3 class="card-title"><strong>DANH SÁCH SẢN PHẨM</strong></h3>
                                            </div>
                                            <div class="col-6 text-right">
                                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                                        data-target="#import">
                                                    <i class="fa-solid fa-cloud-arrow-up"></i>
                                                    Import
                                                </button>
                                                <a th:href="@{${url_export}}" class="btn btn-info">
                                                    <i class="fa-solid fa-cloud-arrow-down"></i>
                                                    Export
                                                </a>
                                                <button type="button" class="btn btn-success" data-toggle="modal"
                                                        data-target="#insert">
                                                    <i class="fa-solid fa-circle-plus"></i>
                                                    Thêm mới
                                                </button>
                                            </div>
                                        </div>
                                        <!-- modal-content (Thêm mới sản phẩm)-->
                                    </div>
                                    <!-- /.card-header -->
                                    <div class="card-body align-items-center">
                                        <table id="example1" class="table table-bordered table-striped align-items-center">
                                            <thead class="align-self-center">
                                                <tr class="align-self-center">
                                                    <th>STT</th>
                                                    <th></th>
                                                    <th>Tên sản phẩm</th>
                                                    <th>Loại sản phẩm</th>
                                                    <th>Màu sắc</th>
                                                    <th>Số lượng</th>
                                                    <th>Đơn vị tính</th>
                                                    <th>Khuyến mãi đang áp dụng</th>
                                                    <th>Trạng thái</th>
                                                    <th>Thao tác</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr th:each="list, index : ${listProducts}">
                                                    <td th:text="${index.index + 1}"></td>
                                                    <td class="text-center">
                                                        <img th:src="@{'/' + ${list.imageActive.directoryPath} + '/' + ${list.imageActive.tenFileKhiLuu}}"
                                                             style="width: 100px; height: 100px; border-radius: 5px">
                                                    </td>
                                                    <td>
                                                        <a th:href="@{/san-pham/{id}(id=${list.productId})}"
                                                           th:text="${list.productName}"></a>
                                                    </td>
                                                    <td th:text="${list.productTypeName}"></td>
                                                    <td>
                                                        <th:block th:each="i : ${list.productVariantInfo}">
                                                            <div class="span" th:text="${i.key} + ': ' + ${i.value}"></div>
                                                        </th:block>
                                                    </td>
                                                    <td>
                                                        <div class="span" th:text="'Hiện có: ' + ${list.totalQtyStorage}"></div>
                                                        <div class="span" th:text="'Đã bán:  ' + ${list.totalQtySell}"></div>
                                                    </td>
                                                    <td th:text="${list.unitName}"></td>
                                                    <td>
                                                        <th:block th:each="voucherInfo, index : ${list.listVoucherInfoApply}">
                                                            <span th:text="${index.index + 1} + ' '"></span>
                                                            <a th:href="@{/san-pham/voucher/detail/{id}(id=${voucherInfo.id})}">
                                                                <span th:text="${voucherInfo.title}"></span>
                                                            </a>
                                                            <br>
                                                        </th:block>
                                                    </td>
                                                    <td th:text="${list.productStatus}"></td>
                                                    <td>
                                                        <button class="btn btn-outline-info btn-sm">
                                                            <a th:href="@{/san-pham/{id}(id=${list.productId})}">
                                                                <i class="fa-solid fa-eye"></i>
                                                            </a></button>
                                                        <button class="btn btn-outline-warning btn-sm" data-toggle="modal"
                                                                th:data-target="'#update-' + ${list.productId}">
                                                            <i class="fa-solid fa-pencil"></i>
                                                        </button>
                                                        <button class="btn btn-outline-danger btn-sm link-delete"
                                                                th:entityId="${list.productId}"
                                                                th:entityName="${list.productName}">
                                                            <i class="fa-solid fa-trash"></i>
                                                        </button>
                                                    </td>
                                                    <!-- Update -->
                                                    <!-- Modal update -->
                                                    <div class="modal fade" th:id="'update-' + ${list.productId}">
                                                        <div class="modal-dialog modal-lg">
                                                            <div class="modal-content">
                                                                <form th:action="@{/san-pham/update/{id}(id=${list.productId})}"
                                                                      th:object="${sanPham}" method="post">
                                                                    <div class="modal-header">
                                                                        <strong class="modal-title">Cập nhật sản
                                                                            phẩm</strong>
                                                                        <button type="button" class="close"
                                                                                data-dismiss="modal" aria-label="Close">
                                                                            <span aria-hidden="true">&times;</span>
                                                                        </button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <div class="row">
                                                                            <div class="col-12">
                                                                                <div class="form-group">
                                                                                    <label>Tên sản phẩm</label>
                                                                                    <input type="text" class="form-control"
                                                                                           placeholder="Tên sản phẩm"
                                                                                           name="tenSanPham"
                                                                                           th:value="${list.productName}"/>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label>Loại sản phẩm</label>
                                                                                    <select class="custom-select"
                                                                                            name="productType">
                                                                                        <option th:each="lstype, iterStat : ${listproductType}"
                                                                                                th:value="${lstype.id}"
                                                                                                th:text="${lstype.name}">
                                                                                        </option>
                                                                                        <option th:text="${list.productTypeName}"
                                                                                                th:value="${list.productTypeId}"
                                                                                                selected></option>
                                                                                    </select>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label>Đơn vị tính</label>
                                                                                    <select class="custom-select"
                                                                                            name="unit">
                                                                                        <option th:each="lsDvt, iterStat : ${listDonViTinh}"
                                                                                                th:value="${lsDvt.id}"
                                                                                                th:text="${lsDvt.name}">
                                                                                        </option>
                                                                                        <option th:text="${list.unitName}"
                                                                                                th:value="${list.unitId}"
                                                                                                selected></option>
                                                                                    </select>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label>Nhãn hiệu</label>
                                                                                    <select class="custom-select"
                                                                                            name="brand">
                                                                                        <option th:each="lsBrand, iterStat : ${listBrand}"
                                                                                                th:value="${lsBrand.id}"
                                                                                                th:text="${lsBrand.name}">
                                                                                        </option>
                                                                                        <option th:text="${list.brandName}"
                                                                                                th:value="${list.brandId}"
                                                                                                selected></option>
                                                                                    </select>
                                                                                </div>
                                                                                <!--<div class="form-group">
                                                                                    <label>Mô tả sản phẩm</label>
                                                                                    <textarea class="form-control" rows="5"
                                                                                              placeholder="Mô tả sản phẩm"
                                                                                              name="moTaSanPham"
                                                                                              th:text="${list.productDes}"></textarea>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label>Trạng thái</label>
                                                                                    <select class="custom-select"
                                                                                            name="status">
                                                                                        <option value="true" selected>Kinh
                                                                                            doanh
                                                                                        </option>
                                                                                        <option value="false">Ngừng kinh doanh
                                                                                        </option>
                                                                                    </select>
                                                                                </div>-->
                                                                            </div>
                                                                        </div>
                                                                        <div class="modal-footer justify-content-end"
                                                                             style="margin-bottom: -15px;">
                                                                            <button type="button" class="btn btn-default"
                                                                                    data-dismiss="modal">Hủy
                                                                            </button>
                                                                            <button type="submit" class="btn btn-primary">
                                                                                Lưu
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </form>
                                                            </div>
                                                            <!-- /.modal-content -->
                                                        </div>
                                                        <!-- /.modal-dialog -->
                                                    </div>
                                                    <!-- /.end modal update-->
                                                    <!-- End update -->
                                                </tr>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <th>STT</th>
                                                    <th></th>
                                                    <th>Tên sản phẩm</th>
                                                    <th>Loại sản phẩm</th>
                                                    <th>Màu sắc</th>
                                                    <th>Số lượng</th>
                                                    <th>Đơn vị tính</th>
                                                    <th>Khuyến mãi đang áp dụng</th>
                                                    <th>Trạng thái</th>
                                                    <th>Thao tác</th>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                    <!-- /.card-body -->

                                    <!-- modal import -->
                                    <div class="modal fade" id="import">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <form th:action="@{${url_import}}" method="POST">
                                                    <div class="modal-header">
                                                        <strong class="modal-title">Import data</strong>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="row">
                                                            <div class="col-12">
                                                                <div class="form-group">
                                                                    <label>Chọn file import</label>
                                                                    <input type="file" class="form-control" name="file">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label>Template</label>
                                                                    <a th:href="@{${url_template}}" class="form-control link">
                                                                        <i class="fa-solid fa-cloud-arrow-down"></i>
                                                                        [[${templateImportName}]]
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer justify-content-end"
                                                             style="margin-bottom: -15px;">
                                                            <button type="button" class="btn btn-default"
                                                                    data-dismiss="modal">Hủy
                                                            </button>
                                                            <button type="submit" class="btn btn-primary">Lưu</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- modal import -->

                                    <th:block>
                                        <div class="modal fade" id="insert">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <form th:action="@{/san-pham/insert}" th:object="${product}" method="post">
                                                        <div class="modal-header">
                                                            <strong class="modal-title">Thêm mới sản phẩm</strong>
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="row">
                                                                <div class="col-12">
                                                                    <div class="form-group">
                                                                        <label>Tên sản phẩm</label>
                                                                        <input type="text" class="form-control"
                                                                               placeholder="Tên sản phẩm" name="tenSanPham">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label>Loại sản phẩm</label>
                                                                        <select class="custom-select" name="productType">
                                                                            <option th:each="lstype, iterStat : ${listProductType}"
                                                                                    th:value="${lstype.id}"
                                                                                    th:text="${lstype.name}"
                                                                                    th:selected="${iterStat.index == 0}"></option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label>Đơn vị tính</label>
                                                                        <select class="custom-select"
                                                                                name="unit">
                                                                            <option th:each="lsDvt, iterStat : ${listDonViTinh}"
                                                                                    th:value="${lsDvt.id}"
                                                                                    th:text="${lsDvt.name}"
                                                                                    th:selected="${iterStat.index == 0}">
                                                                            </option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label>Nhãn hiệu</label>
                                                                        <select class="custom-select"
                                                                                name="brand">
                                                                            <option th:each="lsBrand, iterStat : ${listBrand}"
                                                                                    th:value="${lsBrand.id}"
                                                                                    th:text="${lsBrand.name}">
                                                                            </option>
                                                                        </select>
                                                                    </div>
                                                                    <!--<div class="form-group">
                                                                        <label>Mô tả sản phẩm</label>
                                                                        <textarea class="form-control" rows="5"
                                                                                  placeholder="Mô tả sản phẩm"
                                                                                  name="moTaSanPham"></textarea>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label>Trạng thái</label>
                                                                        <select class="custom-select" name="status">
                                                                            <option th:each="productStatus, iterStat : ${listProductStatus}"
                                                                                    th:value="${productStatus.key}"
                                                                                    th:text="${productStatus.value}">
                                                                            </option>
                                                                        </select>
                                                                    </div>-->
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer justify-content-end"
                                                                 style="margin-bottom: -15px;">
                                                                <button type="button" class="btn btn-default"
                                                                        data-dismiss="modal">Hủy
                                                                </button>
                                                                <button type="submit" class="btn btn-primary">Lưu</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </th:block>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <div th:replace="modal_fragments :: confirm_modal"></div>

            <div th:replace="footer :: footer">
                <!-- Nhúng các file JavaScript vào -->
            </div>

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>

            <div th:replace="header :: scripts">
                <!-- Nhúng các file JavaScript vào -->
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function() {
                $(".link-delete").on("click", function(e) {
                    e.preventDefault();
                    showDeleteConfirmModal($(this));
                });

                $('#yesButton').on("click", async function () {
                    let apiURL = hostURL + '/san-pham/delete/' + $(this).attr("entityId")
                    await callApiDelete(apiURL)
                });
            });
        </script>
    </body>
</html>