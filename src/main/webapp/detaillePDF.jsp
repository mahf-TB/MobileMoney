

<%@page import="com.Models.Transaction"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page - FlyMoney</title>
        <%@include file="Public/indexStyle.jsp" %>

    </head>
    <body>
        <div class=" bg-gray-900 w-full min-h-screen text-slate-300 relative py-4">
            <div class="h-[85vh] grid grid-cols-12 mx-auto xl:mx-5 gap-2 sm:gap-4 md:gap-6 lg:gap-10 xl:gap-12 max-w-8xl my-10 px-2">
                <%@include file="Components/header.jsp"%>
                <div id="content" class="bg-white/10 col-span-9 rounded-lg p-6">                   
                    <div id="last-users">
                        <h1 class="font-bold py-4 uppercase">Operation du <%= request.getParameter("numero") %></h1>
                        <div class="overflow-x-scroll">
                            <table class="w-full whitespace-nowrap">
                                <thead class="bg-black/60">
                                <th class="text-left py-3 px-2 rounded-l-lg">Date</th>
                                <th class="text-left py-3 px-2">Raison</th>

                                <th class="text-left py-3 px-2">Débit</th>
                                <th class="text-left py-3 px-2">Crédit</th>
                                </thead>
                                <tbody>
                                    <%
                                        List<Transaction> debitCredit = (List<Transaction>) request.getAttribute("debitCredit");
                                        
                                        String numero = request.getParameter("numero");
                                        if (debitCredit != null) {
                                            for (Transaction transaction : debitCredit) {
                                    %>
                                    <tr class="border-b border-gray-700">
                                        <td class="py-3 px-2 font-bold">
                                            <div class="inline-flex space-x-3 items-center">
                                                <span> <span><%= transaction.getDate()%></span></span>
                                            </div>
                                        </td>
                                        <td class="py-3 px-2"><%= transaction.getRaison()%></td>
                 
                                        <td class="py-3 px-2">
                                            <%= transaction.getNumRecepteur() == null || transaction.getNumEnvoyeur().equals(numero)  ? transaction.getMontant() :  "" %>
                                        </td>
                                        <td class="py-3 px-2">
                                            <%= transaction.getNumRecepteur() == null || transaction.getNumEnvoyeur().equals(numero)  ? "": transaction.getMontant() %>
                                        </td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                </tbody>

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function confirmDelete(clientId) {
                Swal.fire({
                    title: 'Êtes-vous sûr?',
                    text: "Vous ne pourrez pas revenir en arrière!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Oui, supprimez-le!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        document.getElementById('delete-form-' + clientId).submit();
                    }
                });
            }
        </script>
    </body>
</html>
