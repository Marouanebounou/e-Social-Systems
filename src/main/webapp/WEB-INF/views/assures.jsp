<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>CNSS - Gestion des Assurés</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <nav class="nav nav-pills mb-4">
        <a class="nav-link" href="${pageContext.request.contextPath}/employeurs">Employeurs</a>
        <a class="nav-link active" href="${pageContext.request.contextPath}/assures">Assurés</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/declarations">Déclarations</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/droits">Droits Sociaux</a>
    </nav>

    <h2>Ajouter un Assuré</h2>
    <form action="${pageContext.request.contextPath}/assures" method="post" class="mb-5 p-3 border rounded shadow-sm">
        <div class="mb-3">
            <label class="form-label">Nom Complet</label>
            <input type="text" name="nom" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Salaire Mensuel (MAD)</label>
            <input type="number" step="0.01" name="salaireMensuel" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Employeur</label>
            <select name="employeurId" class="form-select" required>
                <c:forEach var="emp" items="${employeurs}">
                    <option value="${emp.id}">${emp.raison_social}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Enregistrer</button>
    </form>

    <h2>Liste des Assurés</h2>
    <table class="table table-striped table-hover shadow-sm">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Salaire</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="assure" items="${assures}">
            <tr>
                <td>${assure.id}</td>
                <td>${assure.name}</td>
                <td>${assure.salaireMensuel} MAD</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>