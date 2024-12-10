<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<%@ page import="com.javaweb.security.utils.SecurityUtils" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<c:url var="customerEditURL" value="/admin/customer-list"/>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Chỉnh sửa thông tin</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs','fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
<%--                <c:if test="${not empty customerEdit.id}">--%>
<%--                    <li class="active">Chỉnh sửa khách hàng</li>--%>
<%--                </c:if>--%>
<%--                <c:if test="${empty customerEdit.id}">--%>
<%--                    <li class="active">Thêm khách hàng</li>--%>
<%--                </c:if>--%>
            </ul>
        </div>
        <div class="page-content">
            <div class="page-header">
                <h1>
                    Thông tin khách hàng
                </h1>
            </div>
            <div class="row">
                <form:form modelAttribute="customerEdit" action="${customerEditURL}" id="listForm" method="GET">
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form" id="form-edit">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> Tên khách hàng </label>
                                <div class="col-sm-9 mb-3">
                                    <form:input path="fullName" id="name" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> Số điện thoại
                                </label>
                                <div class="col-sm-9">
                                    <form:input path="phoneNumber" id="customerPhone" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> Email </label>
                                <div class="col-sm-9">
                                    <form:input path="email" id="email" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"> Tên công ty
                                </label>
                                <div class="col-sm-9">
                                    <form:input path="companyName" id="companyName" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"> Nhu cầu </label>
                                <div class="col-sm-9">
                                    <form:input path="demand" id="demand" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" > Tình trạng </label>
                                <div class="col-sm-9">
                                    <form:select path = "status" cssClass="form-control">
                                        <form:option value = "" label ="-----Chọn tình trạng-----" ></form:option>
                                        <form:options items = "${status}"></form:options>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right"></label>
                                <div class="col-sm-9">
                                    <c:if test="${not empty customerEdit.id}">
<%--                                        <form:hidden path="modifiedBy" value="<%=SecurityUtils.getPrincipal().getFullName()%>"/>--%>
                                        <input type="button" class="btn btn-primary" value="Cập nhật thông tin"
                                               id="btnAddOrUpdateCustomer" />
                                        <input type="button" class="btn btn-primary" value="Hủy" id="cancelBtn" />
                                    </c:if>
                                    <c:if test="${empty customerEdit.id}">
<%--                                        <form:hidden path="createdBy" value="<%=SecurityUtils.getPrincipal().getFullName()%>"/>--%>
                                        <input type="button" class="btn btn-primary" value="Thêm khách hàng"
                                               id="btnAddOrUpdateCustomer" />
                                        <input type="button" class="btn btn-primary" value="Hủy" id="cancelBtn" />
                                    </c:if>
                                </div>
                            </div>
                            <form:hidden path="id" id="customerId" />
                        </form>
                    </div>
                </form:form>
            </div>
        </div>
        <c:forEach var="item" items="${transactionType}">
        <div class="col-xs-12">
            <div class="col-sm-12">
                <h3 class="header smaller lighter blue">${item.value}</h3>
                <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}',${customerEdit.id})">
                    <i class="orange ace-icon fa fa-location-arrow bigger-138"></i>Add
                </button>
            </div>
            <c:if test = "${item.key == 'CSKH'}">
                    <div class="col-xs-12">
                    <table id="simple-table" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Ngày tạo</th>
                            <th>Người tạo</th>
                            <th>Ngày sửa</th>
                            <th>Người sửa</th>
                            <th>Chi tiết giao dịch</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="itemCSKH" items="${CSKH}">
                        <tr>
                                <td>${itemCSKH.createdDate}</td>
                                <td>${itemCSKH.createdBy}</td>
                                <td>${itemCSKH.modifiedDate}</td>
                                <td>${itemCSKH.modifiedBy}</td>
                                <td>${itemCSKH.note}</td>
                            <td>
                                <div class="hidden-sm hidden-xs btn-group">
                                    <button class="btn btn-xs btn-info" data-toggle="tooltip" title="sửa thông tin giao dịch"
                                            onclick="UpdateTransaction('${item.key}',${itemCSKH.id})">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test = "${item.key == 'DDX'}">
                <div class="col-xs-12">
                    <table id="simple-table" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Ngày tạo</th>
                            <th>Người tạo</th>
                            <th>Ngày sửa</th>
                            <th>Người sửa</th>
                            <th>Chi tiết giao dịch</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="itemDDX" items="${DDX}">
                            <tr>
                                <td>${itemDDX.createdDate}</td>
                                <td>${itemDDX.createdBy}</td>
                                <td>${itemDDX.modifiedDate}</td>
                                <td>${itemDDX.modifiedBy}</td>
                                <td>${itemDDX.note}</td>
                                <td>
                                    <div class="hidden-sm hidden-xs btn-group">
                                        <button class="btn btn-xs btn-info" data-toggle="tooltip" title="sửa thông tin giao dịch"
                                                onclick="UpdateTransaction('${item.key}',${itemDDX.id})">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
        </c:forEach>
    </div>
</div>
<div class="modal fade" id="transactionTypeModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>
            <div class="modal-body">
                <div class="form-group has-success">
                    <label for="transactionDetail" class="col-xs-12 col-sm-3 control-label no-padding-right">Chi tiết
                        giao dịch</label>
                    <div class="col-xs-12 col-sm-9">
                            <span class=" block input-icon input-icon-right">
                                <input type="text" id="transactionDetail" class="width-100">
                            </span>
                    </div>
                </div>
                <input type="hidden" name="customerId" id="customerId" value="">
                <input type="hidden" name="code" id="code" value="">
                <input type="hidden" name="id" id="id" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction">Thêm giao dịch</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
</a>
<script>
    function transactionType(code, customerId) {
        loadTransactionDetail();
        $('#transactionTypeModal').modal();
        $('#customerId').val(customerId);
        $('#code').val(code);
    }

    function UpdateTransaction(code, id) {
        $('#id').val(id);
        $('#code').val(code);
        loadTransactionDetail(id);
        $('#transactionTypeModal').modal();
    }

    function loadTransactionDetail(transactionId) {
        $.ajax({
            url: '${customerAPI}/' + transactionId +  '/transactionDetail',
            type: 'GET',
            dataType: 'json',
            success: function (response) {
                $('#transactionDetail').val(response.transactionDetail);
                console.log("success");
            },
            error: function (response) {
                console.log("fail")
            }
        });
    }

    $('#btnAddOrUpdateTransaction').click(function (e) {
        e.preventDefault();
        var data = {};
        data['id'] = $('#id').val();
        data['customerId'] = $('#customerId').val();
        data['code'] = $('#code').val();
        data['transactionDetail'] = $('#transactionDetail').val();
        addTransaction(data);
    });
    function addTransaction (data) {
        //var id = data['customerId'];
        $.ajax({
            type: "POST",
            url: '${customerAPI}/transaction',
        data: JSON.stringify(data),
        //dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("success");
            alert("Add Transaction Successfully");
            window.location.reload();
        },
        error: function (response) {
            console.log("failed");
            window.location.href = "<c:url value='/admin/customer-edit?fail'/>";
        }
        });
    }
    //thêm xóa khách hàng
    $('#btnAddOrUpdateCustomer').click(function(e) {
        e.preventDefault();
        var data = {};
        var formData = $('#listForm').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        updateAndCreateCustomer(data);
    });
    function updateAndCreateCustomer (data) {
        $.ajax({
            url: '${customerAPI}/',
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert("Add Transaction Successfully");
                window.location.href = "<c:url value='/admin/customer-list?message=success'/>";
            },
            error: function () {
                alert("Add Transaction Unsuccessfully");
                window.location.href = "<c:url value='/admin/customer-edit?message=error_system'/>";
            }
        });
    }
    $("#cancelBtn").click(function () {
        window.location.href = "/admin/customer-list";
    });

</script>
</body>

