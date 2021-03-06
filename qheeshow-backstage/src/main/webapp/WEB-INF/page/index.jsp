<%@ page import="com.qheeshow.eway.common.util.Config" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String appPath = Config.get("app.path");
    String webPath = Config.get("web.path");
    String appDomain = Config.get("app.domain");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>梧桐e路后台管理</title>
    <link rel="stylesheet" type="text/css" href="<%=appPath%>/jquery-easyui-1.4.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=appPath%>/jquery-easyui-1.4.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=appPath%>/jquery-easyui-1.4.5/demo/demo.css">
    <script type="text/javascript" src="<%=appPath%>/jquery-easyui-1.4.5/jquery.min.js"></script>
    <script type="text/javascript" src="<%=appPath%>/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=appPath%>/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=appPath%>/js/common.js"></script>
    <script type="text/javascript" src="<%=appPath%>/js/config.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;background-image: url(<%=appPath%>/images/pic-top.png);"></div>
<div data-options="region:'west',split:true,title:'导航'" style="width:200px;padding:10px;">
    <div class="easyui-panel" style="padding:5px" title="后台管理">
        <ul id="tree" class="easyui-tree" url="./tree/tree"></ul>
    </div>
    <br/>

    <%--<div class="easyui-panel" style="padding:5px" title="系统管理">
        <ul id="sysTree" class="easyui-tree"></ul>
    </div>--%>

</div>
<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;text-align: center;">前海恒昇基金</div>
<div data-options="region:'center',title:''">
    <div id="tt" class="easyui-tabs">
        <div title="工作区">
            <div id="p" class="easyui-panel" style="width:900px;height:480px;padding:10px;margin: 10px;"
                 data-options="
				tools:[{
					iconCls:'icon-reload',
					handler:loadPage
				}]
			">
            </div>
        </div>
    </div>
</div>
<div id="mm" class="easyui-menu" style="width:120px;">
    <div onclick="edit()" data-options="iconCls:'icon-add'">编辑</div>
    <div onclick="append()" data-options="iconCls:'icon-add'">添加子节点</div>
    <div onclick="appendBrother()" data-options="iconCls:'icon-add'">添加同级节点</div>
</div>

<script type="text/javascript">
    //选中的节点
    var checkNode = null;
    //父节点
    var pNode = null;
    //当前节点
    var curNode = null;
    //加载页面
    function loadPage(path) {
        $('#tt').tabs('select', "工作区");
        $('#p').panel('refresh', path);
    }
    //添加节点
    function append() {
        pNode = checkNode;
        curNode = null;
        loadPage(appPath + 'rule/node_add_edit.html');
    }
    //添加同级节点
    function appendBrother() {
        pNode = null;
        curNode = null;
        loadPage(appPath + 'rule/node_add_edit.html');
    }
    //编辑节点
    function edit() {
        pNode = null;
        curNode = checkNode;
        loadPage(appPath + 'rule/node_add_edit.html');
    }
    $('#tree').tree({
        onClick: function (node) {
            curNode = node;
            if (node.attributes == null)
                return;
            if (node.attributes.pageType == "project1_list") {
                loadPage('<%=appPath%>/sys/project/project1_list.html');
            } else if (node.attributes.pageType == "project2_list") {
                loadPage('<%=appPath%>/sys/project/project2_list.html');
            } else if (node.attributes.pageType == "project3_list") {
                loadPage('<%=appPath%>/sys/project/project3_list.html');
            } else if (node.attributes.pageType == "project4_list") {
                loadPage('<%=appPath%>/sys/project/project4_list.html');
            } else if (node.attributes.pageType == "attacker") {
                loadPage('rule/attacker.html');
            } else if (node.attributes.pageType == "qzoneCrawlPoint") {
                loadPage('rule/qzone_crawl_point.html');
            } else if (node.attributes.pageType == "activity1") {
                loadPage('<%=appPath%>/sys/activity/activity1_list.html');
            } else if (node.attributes.pageType == "activity2") {
                loadPage('<%=appPath%>/sys/activity/activity2_list.html');
            } else if (node.attributes.pageType == "activity3") {
                loadPage('<%=appPath%>/sys/activity/activity3_list.html');
            } else if (node.attributes.pageType == "activity4") {
                loadPage('<%=appPath%>/sys/activity/activity4_list.html');
            } else if (node.attributes.pageType == "activity5") {
                loadPage('<%=appPath%>/sys/activity/activity5_list.html');
            } else if (node.attributes.pageType == "investor1_list") {
                loadPage('<%=appPath%>/sys/investor/investor1_list.html');
            } else if (node.attributes.pageType == "investor2_list") {
                loadPage('<%=appPath%>/sys/investor/investor2_list.html');
            } else if (node.attributes.pageType == "investor3_list") {
                loadPage('<%=appPath%>/sys/investor/investor3_list.html');
            } else if (node.attributes.pageType == "auth1_list") {
                loadPage('<%=appPath%>/sys/investor/auth1_list.html');
            } else if (node.attributes.pageType == "auth2_list") {
                loadPage('<%=appPath%>/sys/investor/auth2_list.html');
            } else if (node.attributes.pageType == "auth3_list") {
                loadPage('<%=appPath%>/sys/investor/auth3_list.html');
            } else if (node.attributes.pageType == "best_list") {
                loadPage('<%=appPath%>/sys/investor/best_list.html');
            } else if (node.attributes.pageType == "sign_list") {
                loadPage('<%=appPath%>/sys/investor/sign_list.html');
            } else if (node.attributes.pageType == "adviser1_list") {
                loadPage('<%=appPath%>/sys/adviser/adviser1_list.html');
            } else if (node.attributes.pageType == "adviser2_list") {
                loadPage('<%=appPath%>/sys/adviser/adviser2_list.html');
            } else if (node.attributes.pageType == "adviser3_list") {
                loadPage('<%=appPath%>/sys/adviser/adviser3_list.html');
            }
        },
        onContextMenu: function (e, node) {
            e.preventDefault();
            // 查找节点
            $('#tree').tree('select', node.target);
            checkNode = node;
            // 显示快捷菜单
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        }
    });

    $('#sysTree').tree({
        onClick: function (node) {
            if (node.id == 1)
                loadPage('<%=appPath%>/sys/classinfo/goods_list.html');
            else if (node.id == 2)
                loadPage('<%=appPath%>/sys/classinfo/vip_account_list.html');
        }
    });

    $(function () {
        $(document).ready(function () {
            $.get("<%=appPath%>/data/sys_tree_data.json", function (data) {
                $("#sysTree").tree({
                    data: data
                });
            }, "json");
        });
    });

    appPath = "<%=appPath%>";
    appDomain = "<%=appDomain%>";
    webPath = "<%=webPath%>";
</script>
</body>
</html>
