<%-- 
    Document   : register
    Created on : 21 juin 2024, 21:45:32
    Author     : macbookpro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register - FlyMoney</title>
        <%@include file="Public/indexStyle.jsp" %>
    </head>
    <body class="bg-gray-900">
        <div class="h-screen relative  overflow-hidden">
            <!-- Login Container -->

            <div class="bg-white dark:bg-gray-900">
                <div class="flex justify-center h-screen">
                    <div class="hidden bg-cover lg:block lg:w-1/2" style="background-image: url(https://media.istockphoto.com/id/1289220813/fr/photo/femme-recevant-largent-num%C3%A9rique-utilisant-le-t%C3%A9l%C3%A9phone.jpg?s=612x612&w=0&k=20&c=BZbhWn10VHT40MFCyAdDlk4HsJn0iJAAUEo_YnFNrHM=)">
                        <div class="flex items-center h-full px-20 bg-gray-900 bg-opacity-40">
                            <div>
                                <h2 class="text-4xl font-bold text-white">FlyMoney</h2>

                                <p class="max-w-xl mt-3 text-gray-200">
                                    La solution incontournable pour gérer vos paiements Mobile Money de l'envoi à la réception, en passant par les retraits et les dépôts, notre solution FlyMoney simplifie et sécurise toutes vos transactions Mobile Money. Profitez d'une gestion financière optimale avec notre plateforme intégrée et conforme aux normes fiscales mondiales. 
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="flex items-center w-full max-w-md px-6 mx-auto lg:w-1/2">
                        <div class="flex-1">
                            <div class="text-center">
                                <h2 class="text-4xl font-bold text-center text-gray-700 dark:text-white">FlyMoney</h2>

                                <p class="mt-3 text-gray-500 dark:text-gray-300">Sign in to access your account</p>
                            </div>
                            <div class="mt-8">
                                <form action="clientServlet" method="POST" class="mt-10">
                                    <input type="hidden" name="access_key" value="YOUR_ACCESS_KEY_HERE" /> 
                                    <div class="grid gap-6 sm:grid-cols-2">
                                        <div class="relative z-0 col-span-2">
                                            <input type="text"  name="noms"  class="peer block w-full appearance-none border-0 border-b border-gray-500 bg-transparent py-2.5 px-0 text-sm text-gray-300 focus:border-blue-600 focus:outline-none focus:ring-0" placeholder=" " />
                                            <label class="absolute top-3 -z-10 origin-[0] -translate-y-6 scale-75 transform text-sm text-gray-500 duration-300 peer-placeholder-shown:translate-y-0 peer-placeholder-shown:scale-100 peer-focus:left-0 peer-focus:-translate-y-6 peer-focus:scale-75 peer-focus:text-blue-600 peer-focus:dark:text-blue-500">Noms</label>
                                        </div>
                                        <div class="relative z-0 col-span-2">
                                            <input type="text" name="age"  class="peer block w-full appearance-none border-0 border-b border-gray-500 bg-transparent py-2.5 px-0 text-sm text-gray-300 focus:border-blue-600 focus:outline-none focus:ring-0" placeholder=" " />
                                            <label class="absolute top-3 -z-10 origin-[0] -translate-y-6 scale-75 transform text-sm text-gray-500 duration-300 peer-placeholder-shown:translate-y-0 peer-placeholder-shown:scale-100 peer-focus:left-0 peer-focus:-translate-y-6 peer-focus:scale-75 peer-focus:text-blue-600 peer-focus:dark:text-blue-500">Age</label>
                                        </div>
                                        <div class="relative pb-1 z-0 col-span-2">
                                            <select  name="sexe"         
                                                     class="peer block w-full appearance-none border-0 border-b border-gray-500 bg-transparent py-2.5 px-0 text-sm text-gray-300 focus:border-blue-600 focus:outline-none focus:ring-0"
                                                     placeholder=" " >
                                                <option disabled selected value="h" class="text-sm text-white">Selectionner ici</option>
                                                <option value="Homme">Homme</option>
                                                <option value="Femme">Femme</option>
                                            </select>
                                            <label class="absolute top-3 -z-10 origin-[0] -translate-y-6 scale-75 transform text-sm text-gray-500 duration-300 peer-placeholder-shown:translate-y-0 peer-placeholder-shown:scale-100 peer-focus:left-0 peer-focus:-translate-y-6 peer-focus:scale-75 peer-focus:text-blue-600 peer-focus:dark:text-blue-500">Sexe</label>
                                        </div>
                                        <div class="relative z-0 col-span-2">
                                            <input type="email" name="email" class="peer block w-full appearance-none border-0 border-b border-gray-500 bg-transparent py-2.5 px-0 text-sm text-gray-300 focus:border-blue-600 focus:outline-none focus:ring-0" placeholder=" " />
                                            <label class="absolute top-3 -z-10 origin-[0] -translate-y-6 scale-75 transform text-sm text-gray-500 duration-300 peer-placeholder-shown:translate-y-0 peer-placeholder-shown:scale-100 peer-focus:left-0 peer-focus:-translate-y-6 peer-focus:scale-75 peer-focus:text-blue-600 peer-focus:dark:text-blue-500">Email</label>
                                        </div>
                                    </div>
                                    <button type="submit" class="mt-5 rounded-md bg-black px-10 py-2 text-white">Send Message</button>
                                </form>·
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


