<%--
  Created by IntelliJ IDEA.
  User: 305028327
  Date: 2014/12/21
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>TreePanel</title>
    <link rel="stylesheet" href="http://cdn.sencha.io/try/extjs/4.0.7/resources/css/ext-all-gray.css"/>
    <link rel="stylesheet" href="css/check-tree.css"/>
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="ext_js/ext-all.js"></script>
</head>
<body>
<div id="tree-div"></div>
<script type="text/javascript">
    var winWidth;
    var winHeight;

    if (window.innerWidth)
        winWidth = window.innerWidth;
    else if ((document.body) && (document.body.clientWidth))
        winWidth = document.body.clientWidth;

    if (window.innerHeight)
        winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        winHeight = document.body.clientHeight;

    Ext.require([
        'Ext.tree.*',
        'Ext.data.*'
    ]);

    Ext.onReady(function () {
        var jsonArray;
        $.ajax({
            type: 'post',
            dataType: 'text',
            async: true,
            url: 'scrtree.action',
            success: function (data) {
                jsonArray = JSON.parse(data);
                csoTreeBuild(jsonArray);
            },
            error: function () {
                alert("Get Item Description failed.");
            }
        });

        function csoTreeBuild(data) {
            var store = Ext.create('Ext.data.TreeStore', {
                root: {
                    children: [{
                        text: 'All',
                        checked: false,
                        expanded: true,
                        children: data
                    }]
                },
                sorters: [{
                    property: 'leaf',
                    direction: 'ASC'
                }, {
                    property: 'text',
                    direction: 'ASC'
                }]
            });

            var tree = Ext.create('Ext.tree.Panel', {
                store: store,
                rootVisible: false,
                useArrows: true,
                frame: true,
                title: 'Item Description',
                renderTo: 'tree-div',
                width: winWidth,
                height: winHeight,
                listeners: {
                    "checkchange": function (node, checked) {
                        node.cascadeBy(function (n) {
                            n.set('checked', checked);
                        });
                    }
                }
            });
        }
    });

</script>
</body>
</html>