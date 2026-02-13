<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Person" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Personen</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link href="style.css" rel="stylesheet" text="text/css">
	</head>
	<body>
		<div class="container">
			<div class="row inhaltZentriert">
				<div class="col-12">
					<h1 class="titel">Personen</h1>
				</div>
			</div>
			<c:choose>
				<c:when test="${empty persons}">
				<div class="row inhaltZentriert">
					<div class="col-12">
						<p>Es sind keine Einträge vorhanden.</p>
					</div>
				</div>
				</c:when>
				<c:otherwise>
					<div class="row inhaltZentriert">
						<div class="col-2 center tabelle anzeigen">
							<h3>Vorname</h3>
						</div>
						<div class="col-2 center tabelle anzeigen">
							<h3>Nachname</h3>
						</div>
						<div class="col-4 center tabelle anzeigen">
							<h3>Geburtsdatum</h3>
						</div>
						<div class="col-2 center tabelle anzeigen">
							<h3>Löschen</h3>
						</div>
						<div class="col-2 center tabelle anzeigen">
							<h3>Aktualisieren</h3>
						</div>
					</div>
					<c:set value="${0}" var="i"/>
					<c:forEach var="person" items="${persons}">
							<div class="row inhaltZentriert anzeigen">
								<div class="col-2 center tabelle">
									<p><c:out value="${person.vorname}"/></p>
								</div>
								<div class="col-2 center tabelle">
								<p><c:out value="${person.nachname}"/></p>
								</div>
								<!-- 
									<form action="personen" method="POST">
										<input type="hidden" name="index" id="index" value="${i}">
										<input type="hidden" name="method" id="delete" value="delete">
										<input type="submit" value="Löschen" class="button">
									</form>
								-->
								<div class="col-4 center tabelle">
									<p><c:out value="${person.geburtsdatum}"/></p>
								</div>
								<div class="col-2 center tabelle">
									<button class="delete-button button" data-id="${person.getUUID()}">Löschen</button>
								</div>
								<div class="col-2 center tabelle">
									<form action="personen" method="POST">
										<input type="hidden" name="method" id="put" value="put">
										<input type="hidden" name="vorname" id="vorname" value="${person.vorname}">
										<input type="hidden" name="nachname" id="nachname" value="${person.nachname}">
										<input type="hidden" name="geburtsdatum" id="geburtsdatum" value="${person.geburtsdatum}">
										<input type="hidden" name="uuid" id="uuid" value="${person.getUUID()}">
										<input type="submit" value="Aktualisieren" class="button">
									</form>
								</div>
							</div>
						<c:set value="${i + 1}" var="i"/>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<div class="row">
				<div class="col-12">
					<a href="createPerson.html">
						<button class="button" id="personErstellen">
							Person erstellen
						</button>
					</a>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<a href="personen">
						<br>
						<button class="button">
							Neuladen
						</button>
					</a>
				</div>
			</div>
		</div>
		<script>
		const buttons = document.querySelectorAll(".delete-button");
		
		buttons.forEach(button => {
			button.addEventListener("click", function() {
				const index = button.dataset.id;
				
				fetch("personen?index=" + index, {
					method: "DELETE"
				})
				.then(response => {
					if(response.ok){
						location.reload();
					}
				})
				//.catch(error => console.error("Fehler: ", error));
			});
		});
		</script>
	</body>
</html>