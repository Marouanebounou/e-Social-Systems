<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>CNSS - Droits Sociaux</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <nav class="nav nav-pills mb-4">
        <a class="nav-link" href="${pageContext.request.contextPath}/employeurs">Employeurs</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/assures">Assurés</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/declarations">Déclarations</a>
        <a class="nav-link active" href="${pageContext.request.contextPath}/droits">Droits Sociaux</a>
    </nav>

    <h2 class="mb-4">Consultation des Droits Sociaux</h2>

    <form action="${pageContext.request.contextPath}/droits" method="get" class="mb-5 d-flex gap-2">
        <select name="assureId" class="form-select w-50" required>
            <option value="" disabled selected>-- Sélectionnez un assuré --</option>
            <c:forEach var="a" items="${assures}">
                <option value="${a.id}">${a.name}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-success">Consulter</button>
    </form>

    <%-- Affichage des résultats uniquement si un assuré a été sélectionné --%>
    <c:if test="${not empty assure}">
        <div class="card shadow-lg border-0">
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">Bilan de : ${assure.name}</h4>
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush fs-5">

                    <li class="list-group-item">
                        <strong>Mois déclarés cumulés :</strong> <span class="badge bg-secondary">${nombreDeMois}</span>
                    </li>
                    <li class="list-group-item">
                        <strong>Montant total des cotisations :</strong> ${montantTotal} MAD
                    </li>
                    <li class="list-group-item mt-3">
                        <strong>Statut d'éligibilité (Min. 6 mois) :</strong>
                        <c:choose>
                            <c:when test="${estEligible}">
                                <span class="badge bg-success fs-6">Éligible aux prestations</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger fs-6">Non Éligible</span>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </div>
    </c:if>

</div>
</body>
</html>