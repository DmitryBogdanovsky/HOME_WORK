<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
  </head>
  <body>
    <tiles:insertAttribute name="header"/>

    <tiles:insertAttribute name="body"/>

    <tiles:insertAttribute name="footer"/>
  </body>
</html>