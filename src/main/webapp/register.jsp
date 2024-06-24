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
        <div class="flex items-center justify-center h-screen relative  overflow-hidden">
            <!-- Login Container -->
            <div class="min-w-[500px] relative flex-col z-10 border bg-white px-6 py-14 shadow-md rounded-xl">
                <div class="mb-8 text-center text-blue-1">
                    <a href="/GestionMobileMoney" class="absolute top-3 left-4 text-xl cursor-pointer transition-all duration-300 hover:bg-blue-0 rounded">
                        <i class="fa-solid fa-arrow-left"></i>
                    </a>
                    <div class="flex justify-center">
                        <svg width="150px" height="150px" viewBox="0 0 256 256" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" preserveAspectRatio="xMidYMid">
                        <g>
                        <path d="M251.927436,159.692002 C238.034091,215.509402 187.200894,255.221134 127.33077,255.221134 C67.1895601,255.221134 16.7939457,213.158879 3.46995201,157.111127 C19.4913553,157.111127 34.4690339,157.184334 41.4224936,157.184334 C44.7026556,157.184334 45.3396695,157.241237 45.5294595,157.227672 C50.7614749,156.970132 57.321876,155.243289 61.686372,144.630125 L78.3177452,101.124376 L115.253702,190.054562 C118.574551,197.143511 124.118377,196.64138 127.425583,196.64138 L127.737394,196.64138 C134.18933,196.64138 136.995128,194.380483 139.448486,189.704185 L181.169129,101.791182 L195.401319,141.411649 L196.268828,143.329979 C199.277885,149.93099 201.442523,156.451138 208.889341,157.303173 C210.424427,157.4788 228.251164,157.167385 252.28324,157.167385 C252.28324,157.167385 252.28324,157.32153 251.927436,159.692002 Z" 
                              fill="#68D1BF"></path>
                        <path d="M256.003414,126.817301 C256.003414,127.481465 256.003414,129.151906 255.989849,129.151906 L255.962718,129.151906 L217.604121,129.151906 L195.225104,66.6377276 C193.23265,61.9885609 190.237072,55.0188169 181.507974,55.0188169 C176.167513,55.0188169 171.342122,59.4293987 168.604083,66.7081384 L128.035544,160.67729 L88.9851024,67.2781545 C85.16273,58.9828208 82.5196687,55.0814954 74.8749239,55.0814954 C69.6835372,55.0814954 65.8747398,56.9938193 61.7270682,66.8207976 L38.5489365,130.080118 L0.0406190218,130.039807 C0.0134882534,129.077407 0,128.115249 0,127.152849 C0,56.8322013 57.0101324,0 127.330761,0 C197.651389,0 256.003414,56.4966822 256.003414,126.817301 Z" 
                              fill="#121826"></path>
                        </g>
                        </svg>
                    </div>
                    <div>
                        <h1 class="text-3xl  text-teal-400 font-extrabold">Mobile Money</h1>
                    </div>
                </div>
                <form  action="clientServlet" method="POST">
                    <div class="flex flex-col text-sm rounded-md">
                        <!-- adresse email ou username -->
                        <div class="my-5 flex flex-col">
                            <!--  Numéro du matricule -->
                            <div class="relative z-0">
                                <input type="text" name="noms" 
                                       class="peer block w-full appearance-none border-b-2  bg-transparent  py-1.5 px-0 text-lg  border-gray-500 focus:outline-none focus:ring-0"
                                       placeholder="" />
                                <label class="absolute top-3 -z-10 origin-[0] -translate-y-6 scale-75 text-gray-500  transform text-sm duration-300 peer-placeholder-shown:translate-y-0 peer-placeholder-shown:scale-100 peer-focus:left-0 peer-focus:-translate-y-6 peer-focus:scale-75 peer-focus:text-blue-600 ">
                                    Noms<span  class="text-red-500">*</span></label>
                            </div>
                            <p class="text-red-500 text-xs hidden mb-3 font-helo">Erreur sur input</p>
                        </div>
                        <!-- adresse email ou username -->
                        <div class="my-5 flex flex-col">
                            <!--  Numéro du matricule -->
                            <div class="relative z-0">
                                <input type="text" name="age" 
                                       class="peer block w-full appearance-none border-b-2  bg-transparent  py-1.5 px-0 text-lg   border-gray-500 focus:outline-none focus:ring-0"
                                       placeholder="" />
                                <label class="absolute top-3 -z-10 origin-[0] -translate-y-6 scale-75 text-gray-500  transform text-sm duration-300 peer-placeholder-shown:translate-y-0 peer-placeholder-shown:scale-100 peer-focus:left-0 peer-focus:-translate-y-6 peer-focus:scale-75 peer-focus:text-blue-600 ">
                                    Age<span  class="text-red-500">*</span></label>
                            </div>
                            <p class="text-red-500 text-xs hidden mb-3 font-helo">Erreur sur input</p>
                        </div>
                        <!-- adresse email ou username -->
                        <div class="my-5 flex flex-col">
                            <!--  Numéro du matricule -->
                            <div class="relative z-0">
                                <input type="text" name="sexe" 
                                       class="peer block w-full appearance-none border-b-2  bg-transparent py-1.5 px-0 text-lg  border-gray-500 focus:outline-none focus:ring-0"
                                       placeholder="" />
                                <label class="absolute top-3 -z-10 origin-[0] -translate-y-6 scale-75 text-gray-500  transform text-sm duration-300 peer-placeholder-shown:translate-y-0 peer-placeholder-shown:scale-100 peer-focus:left-0 peer-focus:-translate-y-6 peer-focus:scale-75 peer-focus:text-blue-600 ">
                                    sexe<span  class="text-red-500">*</span></label>
                            </div>
                            <p class="text-red-500 text-xs hidden mb-3 font-helo">Erreur sur input</p>
                        </div>
                        <!-- input pour votre mot de passe -->
                        <div class="my-5 flex flex-col ">
                            <!--  Numéro du matricule -->
                            <div class="relative z-0 ">
                                <input type="text" name="email" 
                                       class="peer block w-full appearance-none border-b-2  bg-transparent py-1.5 px-0 text-lg  border-gray-500 focus:outline-none focus:ring-0"
                                       placeholder=" " />
                                <label class="absolute top-3 -z-10 origin-[0] -translate-y-6 text-gray-500  scale-75 transform text-sm duration-300 peer-placeholder-shown:translate-y-0 peer-placeholder-shown:scale-100 peer-focus:left-0 peer-focus:-translate-y-6 peer-focus:scale-75 peer-focus:text-blue-600 peer-focus:dark:text-blue-500">
                                    Email<span  class="text-red-500"></span></label>
                            </div>
                            <p class="text-red-500 text-xs hidden mb-3">Erreur sur input</p>
                        </div>
                        <!-- toggle voir ou hash pour votre mot de passe -->
                        <div class="flex mb-4 justify-between">
                            <label for="toggle" class="mb-3 relative rounded-full w-12 h-6 transition duration-200 ease-linear bg-gray-300"
                                   :class="[toggle ? 'bg-green-400' : 'bg-gray-200']">
                                <div
                                    class="absolute left-0 bg-gray-200 border-2 mb-2 w-6 h-6 rounded-full transition transform duration-100 ease-linear cursor-pointer"
                                    :class="[toggle ? 'translate-x-full border-green-400' : 'translate-x-0 border-gray-200']">
                                    <input type="checkbox" id="toggle" name="toggle"
                                           class="appearance-none w-full h-full active:outline-none focus:outline-none" @click="onToggle()" />
                                </div>
                            </label>
                            <a href="/reset" class="text-[#2f7c89]">Mot de passe oublié?</a>
                        </div>
                    </div>
                    <button
                        class="mt-3 w-full border p-2 bg-gradient-to-r from-gray-900 bg-gray-700 text-white rounded-[4px] hover:bg-slate-400 scale-105 duration-300"
                        type="submit">
                        SE CONNECTER
                    </button>
                    <button
                        class="mt-3 w-full border p-2 bg-gradient-to-r from-red-900 bg-red-700 text-white rounded-[4px] hover:bg-slate-400 scale-105 duration-300"
                        >
                        ANNULER
                    </button>
                </form>
            </div>
        </div>
    </body>
</html>


