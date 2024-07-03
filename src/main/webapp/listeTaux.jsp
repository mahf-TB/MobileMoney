
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page - FlyMoney</title>
        <%@include file="Public/indexStyle.jsp" %>     
        <script>
            function showContent(contentId) {
                // Hide both divs
                document.getElementById("content").style.display = "none";
                document.getElementById("content1").style.display = "none";
                
                // Show the selected div
                document.getElementById(contentId).style.display = "block";
                
                
            }
            window.onload = function () {
                if (document.getElementById("radioTauxRet").checked) {
                     document.getElementById("radioTauxRet").checked = true;
                     showContent("content1");
                }else{
                    document.getElementById("radioTauxEnv").checked = true;
                    showContent("content");
                }
                
                
            }
            
        </script>
    </head>
    <body>
        <div class=" bg-gray-900 w-full min-h-screen text-slate-300 relative py-4">
            <div class="h-[85vh] grid grid-cols-12 mx-auto xl:mx-5 gap-2 sm:gap-4 md:gap-6 lg:gap-10 xl:gap-12 max-w-8xl my-10 px-2">
                <%@include file="Components/header.jsp"%>

                <div class="bg-white/10 col-span-9 rounded-lg p-6">
                    <div class="col-span-9">
                        <label class="text-white">
                            <input type="radio" id="radioTauxEnv" name="toggleContent" onclick="showContent('content')" class="mr-2"> Voir taux d'envoie
                        </label>
                        <label class="text-white ml-4">
                            <input type="radio" id="radioTauxRet" name="toggleContent" onclick="showContent('content1')" class="mr-2"> Voir taux de retrait
                        </label>
                    </div>
                    <div id="content">
                        <%@include file="Taux/TableTauxEnv.jsp"%>
                    </div>
                    <div id="content1" class="hidden">
                        <%@include file="Taux/TableTauxRet.jsp"%>
                    </div>

                </div>

            </div>
        </div>

    </body>
</html>
