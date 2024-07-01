

<%@page import="com.Models.ClientCompte"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.DAO.ClientDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page - FlyMoney</title>

        <%@include file="Public/indexStyle.jsp" %>
        <script defer src="https://unpkg.com/alpinejs@3.x.x/dist/cdn.min.js"></script>
    </head>
    <body>
        <div class=" bg-gray-900 w-full min-h-screen text-slate-300 relative py-4">
            <div class="grid grid-cols-12 mx-auto xl:mx-5 gap-2 sm:gap-4 md:gap-6 lg:gap-10 xl:gap-12 max-w-8xl my-10 px-2">
                <%@include file="Components/header.jsp"%>
                <div id="content" class="bg-white/10 col-span-9 rounded-lg p-6">
                    <div class="lg:flex items-center space-x-16">
                        <div class="w-5/6 md:w-3/4 lg:w-2/3 xl:w-[500px] 2xl:w-[550px]  bg-black/60 to-white/5 mt-8 mx-auto px-16 py-8 rounded-lg">

                            <h2 class="text-center text-2xl font-bold tracking-wide text-slate-200 uppercase">Formulaire d'envoyer de l'argent</h2>

                            <form action="envoyerArgent" method="POST" class="my-8 text-sm">
                                <div class="flex flex-col my-4">
                                    <label for="numEnvoyeur" class="text-slate-400">Numéro de l'envoyeur<span class="text-red-500">*</span> </label>
                                    <select  name="numEnvoyeur"  required      
                                             class="mt-2 p-2 border border-gray-300 focus:outline-none focus:ring-0 focus:border-gray-300 rounded text-sm text-gray-900"
                                             placeholder=" " >
                                        <option disabled selected value="" class="text-sm text-white">Selectionner ici</option>
                                        <%
                                            ClientDAO dao = new ClientDAO();
                                            List<ClientCompte> clientsList = dao.getAllClientsWithAccounts();
                                            for (ClientCompte cc : clientsList) {
                                        %>
                                        <option value="<%=cc.getComptes().getNumero()%>"><%=cc.getComptes().getNumero() + " - " + cc.getClient().getNoms()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="flex flex-col my-4">
                                    <label for="numRecepteur" class="text-slate-400">Numéro du recepteur<span class="text-red-500">*</span> </label>
                                    <select  name="numRecepteur"  required      
                                             class="mt-2 p-2 border border-gray-300 focus:outline-none focus:ring-0 focus:border-gray-300 rounded text-sm text-gray-900"
                                             placeholder=" " >
                                        <option disabled selected value="h" class="text-sm text-white">Selectionner ici</option>
                                        <%
                                            for (ClientCompte cc : clientsList) {
                                        %>
                                        <option value="<%=cc.getComptes().getNumero()%>"><%=cc.getComptes().getNumero() + " - " + cc.getClient().getNoms()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="flex flex-col my-4">
                                    <label for="montant" class="text-slate-400">Solde</label>
                                    <input type="number" name="montant" id="montant" class="mt-2 p-2 border border-gray-300 focus:outline-none focus:ring-0 focus:border-gray-300 rounded text-sm text-gray-900" placeholder="Entrez le montant a envoyé">
                                </div>
                                <div class="flex flex-col my-4">
                                    <label for="raison" class="text-slate-400">Description<span class="text-red-500">*</span> </label>
                                    <textarea id="raison" name="raison" class="mt-2 p-2 border border-gray-300 focus:outline-none focus:ring-0 focus:border-gray-300 rounded text-sm text-gray-900 resize-none leading-6 transition-colors duration-200 ease-in-out" placeholder="Description de vos transaction" required></textarea>

                                </div>

                                <div class="flex items-center">
                                    <input id="isFrais" name="isFrais" type="checkbox" class="h-4 w-4 bg-indigo-500 focus:ring-indigo-400 border-gray-300 rounded">
                                    <label for="isFrais" class="ml-2 block text-base text-gray-200">Envoyer avec frais de retrait</label>
                                </div>
                                <div class="flex items-center">
                                    <a href="listeClient.jsp" class="my-4 flex items-center justify-end space-x-4 mx-2">
                                        <div class="bg-red-600 hover:bg-red-700 rounded-lg px-8 py-2 text-gray-100 hover:shadow-xl transition duration-150 uppercase">Annuler</div >
                                    </a>
                                    <div class="my-4 flex items-center justify-end space-x-4 mx-2">
                                        <button class="bg-blue-600 hover:bg-blue-700 rounded-lg px-8 py-2 text-gray-100 hover:shadow-xl transition duration-150 uppercase">Envoyer</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="flex items-center justify-center">
                            <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512" xml:space="preserve" width="500px" height="500px" fill="#40ae94" stroke="#40ae94">

                            <g id="SVGRepo_bgCarrier" stroke-width="0"/>

                            <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"/>

                            <g id="SVGRepo_iconCarrier"> <path style="fill:#FFFFFF;" d="M256,508C116.824,508,4,395.176,4,256S116.824,4,256,4s252,112.824,252,252 C507.84,395.112,395.112,507.84,256,508z"/> <g> <path style="fill:#CCCCCC;" d="M256,8c136.968,0,248,111.032,248,248S392.968,504,256,504S8,392.968,8,256 C8.152,119.096,119.096,8.152,256,8 M256,0C114.616,0,0,114.616,0,256s114.616,256,256,256s256-114.616,256-256S397.384,0,256,0z"/> <path style="fill:#CCCCCC;" d="M406.28,110.336v188c-0.016,13.352-10.84,24.176-24.192,24.192H105.72v-188 c0.016-13.352,10.84-24.176,24.192-24.192L406.28,110.336 M414.28,102.336H129.912c-17.776,0-32.192,14.416-32.192,32.192v196 h284.368c17.776,0,32.192-14.416,32.192-32.192C414.28,298.336,414.28,102.336,414.28,102.336z"/> </g> <rect x="97.72" y="139.032" width="316.568" height="45.608"/> <rect x="138.824" y="220.08" style="fill:#999999;" width="104.32" height="14.472"/> <polygon style="fill:#E21B1B;" points="168,378.608 256,464.688 344,378.608 306.232,378.608 306.232,285.896 205.768,285.896 205.768,378.608 "/> </g>

                            </svg>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
