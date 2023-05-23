<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<h1>테스트페이지</h1>
	<hr />
	
	<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">상품명</th>
      <th scope="col">문의목록</th>
      <th scope="col">상품문의</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>소고기</td>
      <td><a href="/inquiry/list?id=22">문의목록</a></td>
      <td><a href="/inquiry/add?id=22">문의하기</a> </td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>돼지고기</td>
      <td><a href="/inquiry/list?id=33">문의목록</a></td>
      <td><a href="/inquiry/add?id=33">문의하기</a> </td>
    </tr>
    <tr>
      <th scope="row">3</th>
       <td>닭고기</td>
      <td><a href="/inquiry/list?id=44">문의목록</a></td>
      <td><a href="/inquiry/add?id=44">문의하기</a> </td>
    </tr>
  </tbody>
</table>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" integrity="sha512-pumBsjNRGGqkPzKHndZMaAG+bir374sORyzM3uulLV14lN5LyykqNk8eEeUlUkB3U0M4FApyaHraT65ihJhDpQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>