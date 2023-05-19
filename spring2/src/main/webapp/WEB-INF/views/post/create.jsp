<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h1>포스트 작성</h1>
            </header>
        </div>
        
        
        <nav>
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <c:url var="mainPage" value="/" />
                    <a class="nav-link active" aria-current="page" href="${ mainPage }">
                        메인페이지</a>
                </li>
                <li class="nav-item">
                    <c:url var="postListPage" value="/post/list" />
                    <a class="nav-link active" aria-current="page" href="${ postListPage }">
                        포스트 목록</a>
                </li>
            </ul>
        </nav>
        
        <main class="my-2">
            <div class="card">
                <form method="post">
                    <div class="card-body">
                        <div class="my-2">
                            <label class="form-label" for="title">제목</label>
                            <input class="form-control" type="text" id="title" name="title" 
                                autofocus required />
                        </div>
                        
                        <div class="my-2">
                            <label class="form-label" for="content">내용</label>
                            <textarea class="form-control" id="content" name="content" required></textarea>
                        </div>
                        
                        <div class="my-2">
                            <label class="form-label" for="author">작성자 아이디</label>
                            <input class="form-control" type="text" id="author" name="author" 
                                required />
                        </div>
                    </div>    
                    
                    <div class="card-footer">
                        <input class="form-control btn btn-primary my-2" type="submit" 
                        value="작성완료"/>
                    </div>
                </form>
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