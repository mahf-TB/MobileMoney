
<%@page import="com.Models.Tauxrecp"%>
<%@page import="com.Models.Tauxenv"%>
<%@page import="java.util.List"%>
<%@page import="com.DAO.TauxDAO"%>

<div id="last-users">
    <div class="flex items-center justify-between">
        <h1 class="font-bold text-xl  uppercase">Liste des toutes taux de retrait</h1>
        <a href="ajouterTaux.jsp">
            <button class="bg-blue-500 bg-gray-100  text-gray-700 text-xs font-bold uppercase px-3 py-2 rounded outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150" 
                    type="button">Nouveaux Taux</button>
        </a>
    </div>
    <div class="mt-2 ">
        <div class="w-full overflow-hidden rounded-lg shadow-xs">
            <div class="w-full overflow-x-auto">
                <table class="w-full">
                    <thead>
                        <tr class="text-sm font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700  dark:text-gray-400 bg-black/60">
                            <th class="px-4 py-3">ID</th>
                            <th class="px-4 py-3">Montant Min</th>
                            <th class="px-4 py-3">Montant Max</th>
                            <th class="px-4 py-3">Frais</th>
                            <th class="px-4 py-3 text-center">Actions</th>
                        </tr>
                    </thead>
                    <tbody class="bg-white divide-y divide-gray-700 bg-gray-800">
                        <%
                            TauxDAO daoRet = new TauxDAO();
                            List<Tauxrecp> tauxList1 = daoRet.getAllTauxRecp();
                            for (Tauxrecp cc : tauxList1) {
                        %>
                        <tr class="bg-gray-800 hover:bg-gray-900  text-gray-700 dark:text-gray-400">
                            <td class="px-4 py-3 text-sm"><%=cc.getId()%></td>
                            <td class="px-4 py-3 text-sm"><%=cc.getMontant_min()%></td>
                            <td class="px-4 py-3 text-sm"><%=cc.getMontant_max()%></td>
                            <td class="px-4 py-3 text-xs">
                                <%=cc.getFraisRet()%>
                            </td>
                            <td class="px-4 py-3 text-base">
                                 <div class="flex items-center justify-around">
                                    <form action="updateClientGG" method="get">
                                        <input type="hidden" name="id" value="<%= cc.getId()%>">
                                        <button type="submit" class="fa-solid fa-pen-to-square"></button>
                                    </form>
                                    <form id="delete-form-<%= cc.getId()%>" action="deleteTaux" method="post">
                                        <input type="hidden" name="id" value="<%= cc.getId()%>">
                                        <input type="hidden" name="type" value="retrait">
                                        <button type="button" class="fa-solid fa-trash" onclick="confirmDelete(<%= cc.getId()%>)"></button>
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
                                <span class="px-3 py-1">...</span>
                            </li>
                            <li>
                                <button class="px-3 py-1 rounded-md focus:outline-none focus:shadow-outline-purple">8</button>
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


