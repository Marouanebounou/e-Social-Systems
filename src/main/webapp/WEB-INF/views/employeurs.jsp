<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>CNSS - Gestion des Employeurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <nav class="nav nav-pills mb-4">
        <a class="nav-link active" href="${pageContext.request.contextPath}/employeurs">Employeurs</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/assures">Assurés</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/declarations">Déclarations</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/droits">Droits Sociaux</a>
    </nav>

    <h2>Ajouter un Employeur</h2>
    <form action="${pageContext.request.contextPath}/employeurs" method="post" class="mb-5 p-3 border rounded shadow-sm">
        <div class="mb-3">
            <label class="form-label">Raison Sociale</label>
            <input type="text" name="raisonSociale" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Secteur d'Activité</label>
            <input type="text" name="secteurActivite" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Enregistrer</button>
    </form>

    <h2>Liste des Employeurs</h2>
    <table class="table table-striped table-hover shadow-sm">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Raison Sociale</th>
            <th>Secteur d'Activité</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${employeurs}">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.raison_social}</td>
                <td>${emp.secteur_activite}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>