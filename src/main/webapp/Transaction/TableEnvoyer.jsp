
<%@page import="com.Models.EnvoyerTauxTrans"%>
<%@page import="java.util.List"%>
<%@page import="com.DAO.TransactionEnvoyer"%>
<div id="last-users" class="">
    <div class="flex items-center justify-between">
        <h1 class="font-bold text-lg  uppercase">Liste des toutes Transaction envoyé</h1>
        <form action="rechercheDate" method="get" class="flex items-center justify-center">
            <input type="date" name="date" class="text-black px-3 py-1 rounded-l-md mr-0">
            <button type="submit" class="fa-solid fa-magnifying-glass bg-gray-200 text-base text-black ml-0 px-2 py-1 rounded-r-md"></button>
        </form>
        <a href="envoyerArgent.jsp">
            <button class="bg-blue-500 bg-gray-100  text-gray-700 text-xs font-bold uppercase px-3 py-2 rounded outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150" 
                    id="showAlertBtn"   type="button">Envoyer de l'argent</button>
        </a>
    </div>
    <div class="mt-2 overflow-auto h-[700px]">
        <div class="w-full overflow-hidden rounded-lg shadow-xs">
            <div class="w-full  ">
                <table class="w-full">
                    <thead>
                        <tr class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700  dark:text-gray-400 bg-black/60">
                            <th class="px-4 py-3">id</th>
                            <th class="px-4 py-3">Numéro Envoyeur</th>
                            <th class="px-4 py-3">Numéro Récepteur</th>
                            <th class="px-4 py-3">Montant</th>
                            <th class="px-4 py-3">Raison</th>
                            <th class="px-4 py-3">avec Frais</th>
                            <th class="px-4 py-3">Date d'envoie</th>
                            <th class="px-4 py-3 text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-700 bg-gray-800">
                        <%
                            List<EnvoyerTauxTrans> listeDate = (List<EnvoyerTauxTrans>) request.getAttribute("liste");
                            List<EnvoyerTauxTrans> envoyerList = null;
                            if (listeDate != null) {
                                envoyerList = listeDate;
                            } else {
                                TransactionEnvoyer dao1 = new TransactionEnvoyer();
                                envoyerList = dao1.getAllTransactionEnvoyer();
                            }
                            for (EnvoyerTauxTrans cc : envoyerList) {
                        %>
                        <tr class="bg-gray-800 hover:bg-gray-900  text-gray-700 dark:text-gray-400">

                            <td class="px-4 py-3 text-sm"><%=cc.getTrans().getId()%></td>
                            <td class="px-4 py-3 text-sm"><%=cc.getTrans().getNumEnvoyeur()%></td>
                            <td class="px-4 py-3 text-sm"><%=cc.getTrans().getNumRecepteur()%></td>
                            <td class="px-4 py-3 text-sm"><%=cc.getTrans().getMontant()%></td>
                            <td class="px-4 py-3 text-sm"><%=cc.getTrans().getRaison()%></td>
                            <td class="px-4 py-3 text-sm">

                                <% if (cc.getEnvoyer().isIs_frais_retrait()) { %>
                                <span class="px-2 py-1 text-xs font-ligth leading-tight text-green-700 bg-green-100 rounded-full"> Oui </span>

                                <% } else {%>
                                <span class="px-2 py-1 text-xs font-ligth leading-tight text-red-700 bg-red-100 rounded-full"> Non </span>

                                <% }%>
                            </td>
                            <td class="px-4 py-3 text-sm"><%=cc.getEnvoyer().getDateEnv()%></td>
                            <td class="px-4 py-3 text-base text-center">
                                <div class="flex items-center justify-around">
                                    <form action="updateClient" method="get">
                                        <input type="hidden" name="id" value="<%= cc.getTrans().getId()%>">
                                        <button type="submit" class="fa-solid fa-pen-to-square"></button>
                                    </form>
                                    <form id="delete-form-<%= cc.getTrans().getId()%>" action="deleteTransaction" method="post">
                                        <input type="hidden" name="id" value="<%= cc.getTrans().getId()%>">
                                        <button type="button" class="fa-solid fa-trash" onclick="confirmDelete(<%= cc.getTrans().getId()%>)"></button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <%  if (envoyerList.isEmpty()) {%>
                <div>Aucun Transaction retirer</div>
                <% }%>
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


<script>
    function confirmDelete(Id) {
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
                document.getElementById('delete-form-' + Id).submit();
            }
        });
    }
</script>