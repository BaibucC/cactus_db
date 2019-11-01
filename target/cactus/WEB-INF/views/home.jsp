<!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:layout="http://www.thymeleaf.org"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Electric bike administration</title>
        <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"/></script>
        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="../cactus/resources/style.css"/>
    </head>

    
    <body>

        <div class="container">
            <div><th:replace="header.jsp" /></th:replace</div>
 <a href="/login" class="btn btn-primary"><span class="fa fa-user"></span> SignIn</a>
        <a href="/logout" class="btn btn-danger">Logout <span class="fa fa-sign-out"></span> </a>
         <a href="/facebook" class="btn btn-primary">Facebook <span class="fa fa-facebook"></span> </a>       
        <a href="/google" class="btn btn-danger"> Google <span class="fa fa-google-plus"></span> </a> 
        <a href="/linkedin" class="btn btn-primary">LinkedIn <span class="fa fa-linkedin"></span> </a> 
        
 </div>
        <div>
            <div>
                <p th:text="${listBike}"></p>
                <tr th:each="listBike : ${listBike}">
        <td th:text="${listBike.bikename}">1</td>
        <td><a href="#" th:text="${listBike.bikename}">Title ...</a></td>
        <td th:text="${listBike.bikename}">Text ...</td>
    </tr>
            </div>
            <table class="tables" align="center">
                <h3>Electric bike administration list</h3>
                <th>No</th>
                <th>Employee</th>
                <th>Bike ID</th>
                <th>From date:</th>
                <th>To date:</th>
                <th>Actions</th>

                <!--<c:forEach var="user" items="${listUser}" varStatus="status">-->
                    <tr th:each="user : ${listUser}">
                        <td width="20px"><span></span>1</td>
                        <td><span th:text="${user.employee}"></span></td>
                        <td><span th:text="${user.name}"></span></td>
                        <td><span width="100px"th:text="${user.date1}"></span></td>
                        <td><span width="100px" th:text="${user.date2}"></span></td>
                        <td>
                            <!--<a class = "button" href="editUserBike?id=${user.id}">Add/edit bike</a>-->
                    <!--<form:form action="removeBike" method="post">-->
                        <!--<a href="removeBike?id=" th:text="${user.name}"></a>-->
                    <!--</form:form>-->
                    <!--<a class = "buttonDelete" href="deleteUser?id=${user.id}">Delete employee</a>-->
                    </td>

                    </tr>
                </c:forEach>	        	
            </table>
        </div>
        <hr>
        <div align="center">
            <table><tr><td class="tablerow">
                        <table class="tables">
                            <h3>All bikes</h3>
                            <th>Bike ID</th>
                            <th>Status</th>
                            <th>Available</th>
                            <th>Actions</th>
                                <c:forEach var="bike" items="${listBike}" varStatus="status">
                                <tr>
                                    <td>${bike.bikename}</td>
                                    <td width="50px" align="center">
                                        <img src="resources/${bike.status}.png" />
                                    </td>
                                    <td width="50px" align="center">
                                        <img src="resources/${bike.available}.png" />
                                    </td>
                                    <td>
                                        <a class = "button" href="editBike?id=${bike.id}">Edit</a>
                                        <a class = "buttonDelete" href="deleteBike?id=${bike.id}">Delete</a>
                                    </td>

                                </tr>
                            </c:forEach>	        	
                        </table>
                    </td><td  class="tablerow">
                        <table class="tables">
                            <h3>Available bikes</h3>
                            <th>Bike ID</th>
                            <th width="50px">Status</th>
                            <th width="50px">Available</th>

                            <c:forEach var="bike" items="${listAvailable}" varStatus="status">
                                <tr>
                                    <td>${bike.bikename}</td>
                                    <td width="50px" align="center">
                                        <img src="resources/${bike.status}.png" />
                                    </td>
                                    <td width="50px" align="center">
                                        <img src="resources/${bike.available}.png" />
                                    </td>

                                </tr>
                            </c:forEach>	        	
                        </table>
                    </td>
                </tr></table>
        </div>

        <jsp:include page="../views/footer.jsp" />
    </body>
</html>
