<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingEditURL" value="/admin/building-edit" />;
<c:url var="buildingAPI" value="/api/building" />;
<html>
<head>
    <title>Chỉnh sửa tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Dashboard
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row" style="font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif">
                <form:form modelAttribute="buildingEdit" action="${buildingEditURL}" id="listForm" method="GET">
                    <div class="col-xs-12">
                        <div class="form-group row">
                            <label class="col-sm-3">Tên tòa nhà</label>
                            <div class="col-sm-9">
                                    <%--<input type="text" id="name" class="form-control" name="name">--%>
                                <form:input path="name" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Quận</label>
                            <div class="col-sm-3">
                                <form:select path = "district" cssClass="form-control">
                                    <form:option value = "" label ="-----Chọn quận-----" ></form:option>
                                    <form:options items = "${district}"></form:options>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Phường</label>
                            <div class="col-sm-9">
                                    <%--<input type="text" class="form-control">--%>
                                <form:input path="ward" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Đường</label>
                            <div class="col-sm-9">
                                    <%--<input type="text" class="form-control">--%>
                                <form:input path="street" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Kết cấu</label>
                            <div class="col-sm-9">
                                    <%--<input type="text" class="form-control">--%>
                                <form:input path="structure" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Số tầng hầm</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" id="numberOfBasement" name="numberOfBasement"--%>
                                    <%--class="form-control">--%>
                                <form:input path="numberOfBasement" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Diện tích sàn</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="floorArea" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Hướng</label>
                            <div class="col-sm-9">
                                    <%--<input type="text" class="form-control">--%>
                                <form:input path="direction" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Hạng</label>
                            <div class="col-sm-3">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="level" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Diện tích thuê</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="rentArea" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Giá thuê</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="rentPrice" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Mô tả giá</label>
                            <div class="col-sm-9">
                                    <%--<input type="text" class="form-control">--%>
                                <form:input path="rentPriceDescription" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Phí dịch vụ</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="serviceFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Phí ô tô</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="carFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Phí mô tô</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="motoFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Phí ngoài giờ</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="overtimeFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Tiền điện</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="electricityFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Đặt cọc</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="deposit" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Thanh toán</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="payment" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Thời gian thuê</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="rentTime" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Thời gian trang trí</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="decorationTime" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Tên quản lý</label>
                            <div class="col-sm-9">
                                    <%--<input type="text" class="form-control">--%>
                                <form:input path="managerName" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">SĐT quản lý</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="managerPhone" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Phí môi giới</label>
                            <div class="col-sm-9">
                                    <%--<input type="number" class="form-control">--%>
                                <form:input path="brokerageFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Loại tòa nhà</label>
                            <div class="col-xs-5"
                                 style="display: flex; flex-direction: row; justify-content: space-between" id="typeCode" name="checkList">
                                <c:if test="${empty buildingEdit.id}">
                                    <form:checkboxes path="typeCode" name="form-field-checkbox" items="${typeCode}" cssStyle=""/>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3">Ghi chú</label>
                            <div class="col-sm-9">
                                    <%--<input type="text" class="form-control">--%>
                                <form:input path="note" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                            <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                            <div class="col-sm-9">
                                <c:if test="${not empty buildingEdit.image}">
                                    <c:set var="imagePath" value="/repository${buildingEdit.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                </c:if>
                                <c:if test="${empty buildingEdit.image}">
                                    <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3"></label>
                            <div class="col-sm-9">
                                <c:if test="${not empty buildingEdit.id}">
                                    <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding"> Cập nhật tòa nhà
                                    </button>
                                    <button type="button" class="btn btn-primary" id="btnCancelBuilding"> Hủy thao tác
                                    </button>
                                </c:if>
                                <c:if test="${empty buildingEdit.id}">
                                    <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding"> Thêm tòa nhà
                                    </button>
                                    <button type="button" class="btn btn-primary" id="btnCancelBuilding"> Hủy thao tác
                                    </button>
                                </c:if>

                                <img src="/img/loading.gif" style="height: 100px" id="loading_image">
                            </div>
                        </div>
                        <form:hidden path="id" id="buildingId" />
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script>
    window.onload = function loadTypeCode(){
        var data = [];
        var building = $('#buildingId').val();
        data["building"] = building;
        if(building!==""){
            $.ajax({
                url: '${buildingAPI}/' + building + '/typeCodes',
                type: 'GET',
                //dataType: 'json',
                success: function (response){
                    var row = '';
                    $.each(response.data, function (index, item) {
                        row += '<span>';
                        row += '<input type="checkbox" name = "typeCode" value=' + item.typeCode + ' '+item.checked+'>';
                        row += '<label>'+item.fullName + '</label>';
                        row += '</span>';
                    });
                    $('#typeCode').html(row);
                },
                error: function (response){
                    console.log("fail")
                    window.location.href = "<c:url value='/admin/building-list?message-error'/>";
                    console.log(response.constructor)
                }
            });
        }
    }
    //lấy dữ liệu
    var imageBase64 = '';
    var imageName = '';
    $('#btnAddOrUpdateBuilding').click(function (e) {
        e.preventDefault();
        var data = {}; //object
        var typeCode = [];
        var formData = $('#listForm').serializeArray();
        $.each(formData, function (i, item) {
            if (item.name != 'typeCode') {
                data["" + item.name] = item.value;
            } else {
                typeCode.push(item.value);
            }
            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        data['typeCode'] = typeCode;
        $('#loading_image').show();
        if(typeCode.length != 0){
            createAndUpdateBuilding(data);
        }
        else{
            window.location.href = "<c:url value='/admin/building-edit?typeCode=typeCode_required'/>";
        }
    })
    function createAndUpdateBuilding(data){
        $.ajax({
            url: "${buildingAPI}/",
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (res) {
                $('#loading_image').hide();
                console.log("success");
                window.location.href = "<c:url value='/admin/building-list?message-success'/>";
            },
            error: function (response) {
                console.log("fail");
                console.log(response)
            },
        });
    }
    $('#btnCancelBuilding').click(function (e) {
        window.location.href = "<c:url value='/admin/building-list'/>";
    });
    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name;
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>