<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<c:url var="customerListURL" value="/admin/customer-list"/>;
<%--<c:url var="customerEditURL" value="/admin/customer-edit"/>--%>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Thông tin khách hàng</title>
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
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Quản lý khách hàng</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm Kiếm</h4>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form action="${customerListURL}" modelAttribute="modelSearch" id="listForm" method="GET">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label class="label-search">Tên Khách Hàng</label>
                                                        <form:input path="fullName" cssClass="form-control" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label class="label-search">Di Động</label>
                                                        <form:input path="phoneNumber" cssClass="form-control" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label class="label-search">Email</label>
                                                        <form:input path="email" cssClass="form-control" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <div>
                                                        <security:authorize access="hasRole('MANAGER')">
                                                        <label class="name">Chọn nhân viên</label>
                                                        <form:select path = "staffId" cssClass="form-control">
                                                            <form:option value = "" label ="---Nhân viên quản lý---" />
                                                            <form:options items = "${staffs}"/>
                                                        </form:select>
                                                        </security:authorize>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <button type="button" class="btn btn-xs btn-danger"
                                                            id="btnSearch"> Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                        <div class="pull-right">
                            <a href='<c:url value='/admin/customer-edit'/>'>
                                <button class="btn btn-white btn-info btn-bold" data-toggle="tooltip"
                                        title="Thêm khách hàng" id="btnCreateBuilding">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-plus-circle" viewBox="0 0 16 16">
                                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16" />
                                        <path
                                                d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4" />
                                    </svg>
                                </button>
                            </a>
                            <button class="btn btn-white btn-warning btn-bold" data-toggle="tooltip"
                                    title="Xóa khách hàng" onclick="warningBeforeDelete()">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-x-circle" viewBox="0 0 16 16">
                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16" />
                                    <path
                                            d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708" />
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="hr hr-18 dotted hr-double"></div>
            <div class="row">
                <div class="col-xs-12">
                    <display:table name="customers" cellspacing="0" cellpadding="0" requestURI="${customerListURL}"
                                   partialList="true" sort="external" size="${model.totalItems}" defaultsort="2"
                                   defaultorder="ascending" id="tableList" pagesize="${model.maxPageItems}" export="false"
                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                   style="margin: 3em 0 1.5em;">
                        <display:column title="<fieldset class='form-group'>
                                <input type='checkbox' id='checkAll' class='check-box-element'>
                                </fieldset>" class="center select-cell" headerClass="center select-cell">
                            <fieldset>
                                <input type="checkbox" name="checkList" value="${tableList.id}"
                                       id="checkbox_${tableList.id}" class="check-box-element" />
                            </fieldset>
                        </display:column>
                        <display:column headerClass="text-left" property="fullName" title="Tên khách hàng" />
                        <display:column headerClass="text-left" property="phoneNumber" title="Di động" />
                        <display:column headerClass="text-left" property="email" title="Email" />
                        <display:column headerClass="text-left" property="demand" title="Nhu cấu" />
                        <display:column headerClass="text-left" property="createdBy" title="Người thêm" />
                        <display:column headerClass="text-left" property="createdDate" title="Ngày thêm" />
                        <display:column headerClass="text-left" property="status" title="Tình trạng" />
                        <display:column headerClass="text-left" title="Thao tác">
                            <div class="hidden-sm hidden-xs btn-group">
                                <security:authorize access="hasRole('MANAGER')">
                                <button class="btn btn-xs btn-success" data-toggle="tooltip" title="Giao khách hàng"
                                        onclick="assignmentCustomer(${tableList.id})">
                                    <i class="ace-icon fa fa-bars bigger-120"></i>
                                </button>
                                </security:authorize>
                                <a class="btn btn-xs btn-info" data-toggle="tooltip" title="Sửa thông tin"
                                    href='<c:url value="/admin/customer-edit-${tableList.id}"/>'>
                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                </a>
                                <button class="btn btn-xs btn-danger" data-toggle="tooltip" title="Xóa thông tin"
                                        onclick="warningBeforeDelete()">
                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                </button>
                            </div>
                        </display:column>
                    </display:table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="assignmentCustomerModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignmentCustomer">Giao khách hàng</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>
<script>
    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });
    function assignmentCustomer(customerId){
        assingmentModalOpen();
        loadStaff(customerId);
        $('#customerId').val(customerId);
    }
    function assingmentModalOpen(){
        $('#assignmentCustomerModal').modal();
    }
    function loadStaff(customerId) {
        $.ajax({
            url: '${customerAPI}/' + customerId +  '/staffs',
            type: 'GET',
            dataType: 'json',
            success: function (response){
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" '  + item.checked + '></td>';
                    row += '<td class="text-center">'+ item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
                return true;
            },
            error: function (response){
                console.log("fail");
                window.location.href = "<c:url value='/admin/customer-list'/>";
            }
        });
    }
    $('#btnAssignmentCustomer').click(function (e) {
        e.preventDefault();
        var data = {};
        var id = $('#customerId').val();
        data['customerId'] = id;
        var staffs = $("#staffList").find('tbody input[type = checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        $.ajax({
            url: "${customerAPI}/" + id,
            type: "POST",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log("success");
                window.location.href = "<c:url value='/admin/customer-list?message-success'/>";
                },
            error: function (response) {
                console.log("fail");
                console.log(response);
                window.location.href = "<c:url value='/admin/customer-list'/>";
            },
        });
    });
    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            event.preventDefault();
            var dataArray = $('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteCustomer(dataArray);
        });
    }

    function deleteCustomer(data) {
        $.ajax({
            url: '${customerAPI}/',
            type: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                window.location.href = "<c:url value='/admin/customer-list?message=delete_success'/>";
            },
            error: function (res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/customer-list?message=error_system'/>";
            }
        });
    }
</script>
</body>
</html>
