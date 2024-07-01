

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
                            <svg height="500px" width="500px" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 512 512" xml:space="preserve" fill="#000000">

                            <g id="SVGRepo_bgCarrier" stroke-width="0"/>

                            <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"/>

                            <g id="SVGRepo_iconCarrier"> <circle style="fill:#191533;" cx="256" cy="256" r="256"/> <path style="fill:#FEC982;" d="M260.539,363.513l52.084-31.418c12.065-7.287,18.516,6.69,11.229,10.751l-40.735,22.458 L260.539,363.513z"/> <g> <path style="fill:#ffd190;" d="M283.356,360.646l55.907-34.404c13.14-8.123,20.069,8.362,12.902,12.543l-38.346,22.458 l-30.462-0.478V360.646z"/> 
                            <path style="fill:#ffd190;" d="M148.248,372.592l-106.079,24.25c-16.007-24.37-28.073-51.606-35.121-80.754l132.48-8.72 c21.264-2.986,40.855-2.867,49.575,1.911c15.41,8.601,13.738,27.356,48.978,37.152l50.292,8.72l80.754-19.83 c14.813-2.628,17.799,13.977,8.004,17.919l-108.469,38.466C254.089,396.842,218.968,385.015,148.248,372.592z"/> </g> 
                            <path style="fill:#F0B97D;" d="M304.381,351.209c5.495,5.853-5.495,9.079-21.144,12.424c-11.229,2.389-26.281,5.256-33.568,7.048 c-14.096,0.478-50.292-8.123-75.737-17.441c13.021-0.239,67.494,16.007,76.095,14.335c8.601-1.672,49.814-12.543,52.562-14.096 C305.336,351.925,304.022,352.164,304.381,351.209z"/> 
                            <path style="fill:#2e8e7c;" d="M92.819,300.558l14.216,105.243l-51.726,9.079c-24.489-30.82-41.93-67.494-50.053-107.513 L92.819,300.558z"/> <polygon style="fill:#FFFFFF;" points="111.933,301.275 125.79,399.948 106.677,402.456 92.819,303.664 "/> 
                            <path style="fill:#8597b1;" d="M97.717,391.466c0.478,3.942-2.15,7.526-5.973,8.004s-7.406-2.27-7.884-6.212 c-0.478-3.942,2.15-7.526,5.973-8.004C93.656,384.776,97.239,387.524,97.717,391.466z"/> 
                            <path style="fill:#ffd190;" d="M175.843,305.814c26.4-4.181,43.961,11.349,57.221,29.984l24.011,5.615 c0,0,22.458-4.898,34.524-1.195c11.946,3.703,15.649,13.379,10.99,16.007c-4.659,2.628-43.961,9.557-52.562,11.229 c-8.72,1.672-57.699-8.482-75.976-14.216c-18.277-5.615-29.028-40.855,1.792-47.545V305.814z"/> 
                            <path style="fill:#2e8e7c;" d="M368.888,356.106l12.782-4.181c1.553-0.478,0.239-4.539-1.792-4.539 c-2.031,0-13.618,2.867-13.618,2.867C367.694,351.806,369.247,354.076,368.888,356.106z"/> 
                            <path style="fill:#FFFFFF;" d="M285.387,359.332l16.246-3.584c1.314-0.239,0.239-6.57-2.867-7.884 c-3.106-1.195-16.605,1.553-16.605,1.553c1.075,2.27,2.867,6.331,3.106,9.796L285.387,359.332z"/> 
                            <path style="fill:#ffd190;" d="M263.048,201.049l35.36-2.748c5.017-0.358,18.397-8.72,18.397-8.72l-12.782-21.503l-32.971-3.942 l-25.445,23.414l-9.796,20.069c-5.853,10.512-25.086,8.482-20.427-4.898l11.11-33.568c0,0,27.834-29.984,38.107-34.165 c10.273-4.181,82.546-7.765,82.546-7.765s59.132,32.254,81.112,46.231l69.764-0.956c6.57,19.113,10.99,39.182,12.902,59.968 l-87.922-2.031c0,0-55.907,10.871-74.901,8.123c-19.113-2.748-45.514-10.99-45.514-10.99s-33.329,0.597-42.766-3.703 c-13.379-4.659-12.304-21.622,3.345-22.817H263.048z"/> 
                            <path style="fill:#FFFFFF;" d="M215.862,198.66c0.597,0.239,3.823,1.553,3.823,1.553l-3.225,9.796c0,0-3.823,1.792-3.225-2.389 c0.597-4.181,2.628-9.079,2.628-9.079V198.66z"/> 
                            <path style="fill:#FFE356;" d="M273.919,200.571c-0.358-1.553-0.717-3.106-1.195-4.659c-2.27-7.048-6.092-13.379-10.99-18.636 c-8.959-9.437-21.503-15.291-35.479-15.291c-26.998,0-48.859,21.861-48.859,48.859s21.861,48.859,48.859,48.859 c16.724,0,31.537-8.362,40.258-21.264c3.345-4.778,5.734-10.154,7.168-16.007c0.358-1.434,0.597-2.867,0.836-4.301 c0.358-2.389,0.597-4.898,0.597-7.406c0-3.584-0.358-7.048-1.075-10.393l0,0L273.919,200.571z"/> 
                            <path style="fill:#f5d331;" d="M271.649,201.049c-0.358-1.434-0.717-2.986-1.195-4.42c-2.15-6.69-5.734-12.663-10.512-17.68 c-8.482-8.959-20.547-14.574-33.807-14.574c-25.684,0-46.589,20.905-46.589,46.589s20.905,46.589,46.589,46.589 c15.888,0,29.984-8.004,38.346-20.189c3.106-4.539,5.495-9.676,6.809-15.291c0.358-1.314,0.597-2.748,0.836-4.181 c0.358-2.27,0.478-4.659,0.478-7.048c0-3.464-0.358-6.69-1.075-9.915l0,0L271.649,201.049z"/> <g> 
                            <path style="fill:#FFE356;" d="M226.135,174.529c-20.069,0-36.435,16.246-36.435,36.435c0,20.069,16.366,36.435,36.435,36.435 s36.435-16.366,36.435-36.435S246.324,174.529,226.135,174.529L226.135,174.529z M226.135,179.069 c17.56,0,31.895,14.335,31.895,31.895s-14.335,31.895-31.895,31.895s-31.895-14.335-31.895-31.895S208.575,179.069,226.135,179.069 z"/> 
                            <path style="fill:#FFE356;" d="M223.268,233.303v-5.137c-3.703-0.119-7.168-1.195-9.318-2.389l1.672-6.451 c2.27,1.314,5.495,2.389,9.079,2.389c3.106,0,5.256-1.195,5.256-3.345c0-2.031-1.792-3.345-5.734-4.778 c-5.853-1.911-9.796-4.659-9.796-10.035c0-4.778,3.345-8.601,9.198-9.676v-5.137h5.376v4.778c3.703,0.119,6.092,0.956,7.884,1.792 l-1.553,6.212c-1.434-0.597-3.942-1.911-7.884-1.911c-3.584,0-4.659,1.553-4.659,3.106c0,1.792,1.911,2.986,6.57,4.659 c6.451,2.27,9.079,5.256,9.079,10.273c0,4.898-3.464,8.959-9.676,10.154v5.615h-5.376L223.268,233.303z"/> </g> 
                            <path style="fill:#ffd190;" d="M263.048,201.049c15.649-1.195,30.223-2.509,35.36-2.748c5.017-0.358,18.397-8.72,18.397-8.72 s34.762,14.335,32.493,28.79s-5.376,14.932-9.318,18.874c-17.68-3.464-37.51-9.557-37.51-9.557s-33.329,0.597-42.766-3.703 c-13.379-4.659-12.304-21.622,3.345-22.817V201.049z"/> 
                            <polygon style="fill:#FFFFFF;" points="431.843,159.955 437.697,252.655 462.186,251.102 456.332,158.402 "/> <path style="fill:#2e8e7c;" d="M452.032,148.846l7.168,112.172L512,260.181c0-1.434,0-2.867,0-4.301 c0-39.421-8.959-76.692-24.847-110.141l-35.24,2.986L452.032,148.846z"/> <path style="fill:#FFFFFF;" d="M252.894,214.428l16.963-0.478v10.273c0,0-14.693,0.597-17.441-1.672 c-2.748-2.389-1.553-7.287,0.478-8.004V214.428z"/> <path style="fill:#8597b1;" d="M483.091,245.607c0.478,3.942-2.15,7.526-5.973,8.004c-3.823,0.478-7.406-2.27-7.884-6.212 c-0.478-3.942,2.15-7.526,5.973-8.004C479.029,238.917,482.613,241.665,483.091,245.607z"/> </g>

                            </svg>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
