

<%@page import="java.util.List"%>
<%@page import="com.Models.ClientCompte"%>
<%@page import="com.DAO.ClientDAO"%>
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
            <div class="grid grid-cols-12 mx-auto xl:mx-5 gap-2 sm:gap-4 md:gap-6 lg:gap-10 xl:gap-12 max-w-8xl my-10 px-2">
                <%@include file="Components/header.jsp"%>
                <div id="content" class="bg-white/10 col-span-9 rounded-lg p-6">
                    <div id="last-users">
                        <div class="flex items-center justify-between">
                            <h1 class="font-bold text-xl  uppercase">Liste des toutes client en contact</h1>
                            <a href="ajouterClient.jsp">
                                <button class="bg-blue-500 bg-gray-100  text-gray-700 text-xs font-bold uppercase px-3 py-2 rounded outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150" 
                                        id="showAlertBtn"   type="button">Ajouter un client</button>
                            </a>
                        </div>
                        <div class="mt-2 ">
                            <div class="w-full overflow-hidden rounded-lg shadow-xs">
                                <div class="w-full overflow-x-auto">
                                    <table class="w-full">
                                        <thead>
                                            <tr class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700  dark:text-gray-400 bg-black/60">
                                                <th class="px-4 py-3">Client</th>
                                                <th class="px-4 py-3">Numéro</th>
                                                <th class="px-4 py-3">Ages</th>
                                                <th class="px-4 py-3">Sexe</th>
                                                <th class="px-4 py-3">Solde Principale</th>
                                                <th class="px-4 py-3">Status</th>
                                                <th class="px-4 py-3 text-center">Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody class="bg-white divide-y divide-gray-700 bg-gray-800">
                                            <%
                                                ClientDAO dao = new ClientDAO();
                                                List<ClientCompte> clientsList = dao.getAllClientsWithAccounts();
                                                for (ClientCompte cc : clientsList) {
                                            %>
                                            <tr class="bg-gray-800 hover:bg-gray-900  text-gray-700 dark:text-gray-400">
                                                <td class="px-4 py-3">
                                                    <div class="flex items-center text-sm">
                                                        <div class="relative hidden w-8 h-8 mr-3 rounded-full md:block">
                                                            <img class="object-cover w-full h-full rounded-full" src="https://images.unsplash.com/flagged/photo-1570612861542-284f4c12e75f?ixlib=rb-1.2.1&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=200&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjE3Nzg0fQ" alt="" loading="lazy" />
                                                            <div class="absolute inset-0 rounded-full shadow-inner" aria-hidden="true"></div>
                                                        </div>
                                                        <div>
                                                            <p class="font-semibold"><%=cc.getClient().getNoms()%></p>
                                                            <p class="text-xs text-gray-600 dark:text-gray-400"><%=cc.getClient().getEmail()%></p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="px-4 py-3 text-sm"><%=cc.getComptes().getNumero()%></td>
                                                <td class="px-4 py-3 text-sm"><%=cc.getClient().getAge()%></td>
                                                <td class="px-4 py-3 text-sm"><%=cc.getClient().getSexe()%></td>
                                                <td class="px-4 py-3 text-sm"><%=cc.getComptes().getSolde()%></td>
                                                <td class="px-4 py-3 text-sm">
                                                    <% if (cc.getComptes().isIsActive()) { %>
                                                    <span class="px-2 py-1 font-semibold leading-tight text-green-700 bg-green-100 rounded-full dark:bg-green-700 dark:text-green-100"> Approved </span>

                                                    <%
                                                    } else {
                                                    %> 
                                                    <span class="px-2 py-1 font-semibold leading-tight text-red-700 bg-red-100 rounded-full dark:text-red-100 dark:bg-red-700"> Denied </span>

                                                    <%
                                                        }
                                                    %>
                                                </td>
                                                <td class="px-4 py-3 text-base text-center">
                                                    <div class="flex items-center justify-around">
                                                        <form action="updateClient" method="get">
                                                            <input type="hidden" name="id" value="<%= cc.getClient().getId()%>">
                                                            <button type="submit" class="fa-solid fa-pen-to-square"></button>
                                                        </form>
                                                        <form id="delete-form-<%= cc.getClient().getId() %>" action="deleteClient" method="post">
                                                            <input type="hidden" name="id" value="<%= cc.getClient().getId()%>">
                                                            <button type="button" class="fa-solid fa-trash" onclick="confirmDelete(<%= cc.getClient().getId() %>)"></button>
                                                        </form>
                                                    </div>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-9 dark:text-gray-400 dark:bg-gray-800">
                                    <span class="flex items-center col-span-3"> Showing 21-30 of 100 </span>
                                    <span class="col-span-2"></span>
                                    <!-- Pagination -->
                                    <span class="flex col-span-4 mt-2 sm:mt-auto sm:justify-end">
                                        <nav aria-label="Table navigation">
                                            <ul class="inline-flex items-center">
                                                <li>
                                                    <button class="px-3 py-1 rounded-md rounded-l-lg focus:outline-none focus:shadow-outline-purple" aria-label="Previous">
                                                        <svg aria-hidden="true" class="w-4 h-4 fill-current" viewBox="0 0 20 20">
                                                        <path d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z" clip-rule="evenodd" fill-rule="evenodd"></path>
                                                        </svg>
                                                    </button>
                                                </li>
                                               
                                                <li>
                                                    <button class="px-3 py-1 rounded-md focus:outline-none focus:shadow-outline-purple">2</button>
                                                </li>
                                                <li>
                                                    <button class="px-3 py-1 text-white dark:text-gray-800 transition-colors duration-150 bg-blue-600 dark:bg-gray-100 border border-r-0 border-blue-600 dark:border-gray-100 rounded-md focus:outline-none focus:shadow-outline-purple">3</button>
                                                </li>
                                                <li>
                                                    <button class="px-3 py-1 rounded-md focus:outline-none focus:shadow-outline-purple">4</button>
                                                </li>
                                                <li>
                                                    <span class="px-3 py-1">...</span>
                                                </li>
                                                <li>
                                                    <button class="px-3 py-1 rounded-md rounded-r-lg focus:outline-none focus:shadow-outline-purple" aria-label="Next">
                                                        <svg class="w-4 h-4 fill-current" aria-hidden="true" viewBox="0 0 20 20">
                                                        <path d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z" clip-rule="evenodd" fill-rule="evenodd"></path>
                                                        </svg>
                                                    </button>
                                                </li>
                                            </ul>
                                        </nav>
                                    </span>
                                </div>
                            </div>
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
