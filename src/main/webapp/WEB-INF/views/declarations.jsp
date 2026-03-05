<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>CNSS - Déclarations</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <nav class="nav nav-pills mb-4">
        <a class="nav-link" href="${pageContext.request.contextPath}/employeurs">Employeurs</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/assures">Assurés</a>
        <a class="nav-link active" href="${pageContext.request.contextPath}/declarations">Déclarations</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/droits">Droits Sociaux</a>
    </nav>

    <h2>Nouvelle Déclaration Mensuelle</h2>

    <%-- Affichage des messages d'erreur ou de succès --%>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    <c:if test="${param.success == 'true'}">
        <div class="alert alert-success">Déclaration enregistrée et cotisations calculées avec succès !</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/declarations" method="post" class="mb-5 p-3 border rounded shadow-sm">
        <div class="row">
            <div class="col-md-4 mb-3">
                <label class="form-label">Employeur</label>
                <select name="employeurId" class="form-select" required>
                    <c:forEach var="emp" items="${employeurs}">
                        <option value="${emp.id}">${emp.raison_social}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Mois (1-12)</label>
                <input type="number" name="mois" class="form-control" min="1" max="12" required>
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Année</label>
                <input type="number" name="annee" class="form-control" value="2026" required>
            </div>
        </div>
        <button type="submit" class="btn btn-warning">Générer la déclaration et calculer les cotisations</button>
    </form>

    <h2>Historique des Déclarations</h2>
    <table class="table table-striped shadow-sm">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Période</th>
            <th>Date de Déclaration</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dec" items="${declarations}">
            <tr>
                <td>${dec.id}</td>
                <td>${dec.mois} / ${dec.annee}</td>
                <td>${dec.dateDeclaration}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>