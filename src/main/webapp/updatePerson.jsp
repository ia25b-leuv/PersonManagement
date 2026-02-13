<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Person hinzufügen</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link href="style.css" rel="stylesheet" text="text/css">

	</head>
	<body>
		<div class="container">
			<div class="row inhaltZentriert">
				<div class="col-12">
					<h1 class="titel">Person aktualisieren</h1>
				</div>
			</div>
			<form action="personen" method="POST">
				<div class="row inhaltZentriert">
					<div class="col-12">
						<label for="vorname">Vorname:</label><br>
						<input type="text" class="inputFeld" name="vorname" id="vorname" value="${vorname}" required><br>
					</div>
				</div>
				<div class="row inhaltZentriert	">
					<div class="col-12">
						<label for="nachname">Nachname:</label><br>
						<input type="text" class="inputFeld" name="nachname" id="nachname" value="${nachname}" required><br><br>
					</div>
				</div>
				<div class="row inhaltZentriert">
					<div class="col-12">
						<label for="geburtsdatum">Geburtsdatum</label><br>
						<input type="date" class="inputFeld" name="geburtsdatum" id="geburtsdatum" min="0001-01-01" max="9999-12-31" value="${geburtsdatum}" required><br><br>
					</div>
				</div>
				<input type="hidden" name="uuid" value="${uuid}">
				<input type="hidden" name="method" value="updatePerson">
				<div class="row inhaltZentriert">
					<div class="col-lg-2 col-md-3 col-sm-12 center">
						<input type="submit" value="Aktualisieren" class="button"><br>
					</div>
					<div class="col-lg-2 col-md-3 col-sm-12 center">
						<input type="reset" value="Zurücksetzen" class="button">
					</div>
				</div>
			</form>
			<br>
			<div class="row">
				<div class="col-12">
					<a href="personen">
						<button class="button">
							Personen auflisten
						</button>
					</a>
				</div>
			</div>
		</div>
	</body>
</html>