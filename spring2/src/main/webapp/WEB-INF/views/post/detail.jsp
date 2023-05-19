<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Spring 2</title>
        <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" 
        crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <header class="my-2 p-4 text-center text-bg-light">
                <h1>상세 보기 페이지</h1>
            </header>
        </div>
        <nav>
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <c:url var="mainPage" value="/" />
                    <a class="nav-link active" aria-current="page" 
                        href="${ mainPage }">메인페이지</a>
                </li>
                <li class="nav-item">
                    <c:url var="postList" value="/post/list" />
                    <a class="nav-link active" aria-current="page" 
                        href="${ postList }">포스트 목록</a>
                </li>
            </ul>
        </nav>
        
        <main class="my-2">
            <div class="card">
                <form class="card-body"> <!-- 이동하는 form이 아니기 때문에 액션, 메서드 지정을 안해도 된다. -->
                    <div class="my-2">
                        <label class="form-label" for="id" >번호</label>
                        <input class="form-control" id="id" value="${ post.id }" readonly />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="title">제목</label>
                        <input class="form-control" id="title" value="${ post.title }" readonly />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="content">내용</label>
                        <textarea class="form-control resizable-textarea" id="content" rows="10"
                            readonly>${ post.content }</textarea>
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="author">작성자</label>
                        <input class="form-control" id="author" value="${ post.author }" readonly />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="createdTiem">작성 시간</label>
                        <fmt:formatDate value="${ post.createdTime }" pattern="yyyy-MM-dd HH:mm:ss"
                            var="created"/>
                        <input class="form-control" id="createdTiem" value="${ created }" readonly />
                    </div>
                    <div class="my-2">
                        <label class="form-label" for="modifiedTime">수정 시간</label>
                        <fmt:formatDate value="${ post.modifiedTime }" pattern="yyyy-MM-dd HH:mm:ss"
                            var="modified"/>
                        <input class="form-control" id="modifiedTime" value="${ modified }" readonly />
                    </div>
                </form>
                <div class="card-footer">
                    <div class="d-flex justify-content-end">
                        <c:url var="postModify" value="/post/modify" >
                                <c:param name="id" value="${ post.id }"></c:param>
                            </c:url>
                            <a  class="btn btn-warning btn-outline-dark"
                                href="${ postModify }">수정 하기</a>
                    </div>
                </div>
            </div>
        </main>
        
        <div>
        	<script 
        	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" 
        	integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" 
        	crossorigin="anonymous"></script>
        </div>
    </body>
</html>