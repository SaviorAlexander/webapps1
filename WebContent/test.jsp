<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>


 <body>

<div style=height:64px; width:1024px;   margin: auto; background-color:#808080; 
                  color: #0000FF; align=left>
                 <div font-size: 28px; float: left >
                 <a href = index.jsp>  Main</a>
                 <a href = ListTour.jsp >  Our Tour  </a>
                 <a href = Cabinet.jsp >  Cabinet </a> 
              <a href = AdministratorPanel.jsp >  Administrator </a>
           <a href = Contact.jsp >Contact </a>
            </div> 
            
            <div class=languages style=margin-bottom:5px; align=right font-size: 14px;>
<a href=index.jsp? lang=en title=English>
<img src=en.png height=25 style=padding:0px 2px;border:1px solid black>
</a>
<a href=index.jsp? lang=ru title=Rusian>
<img src=ru.png height=25 style=padding:0px 2px;border:1px solid white>
</a>
 </div> 
 <div
  <form   class=layer2 action =     index.jspmethod = GET>
         Login: <input type = text  name = first_namerequired>
         <br />
         Password : <input type = text name = last_namerequired />
         <input type = submit value = Submit />
        </form>           
           </div> 
        </div> 

 <style>
.layer2 {
 position: absolute;
 top: 100px; 
 right: 50px; 
 line-height: 1px;
}
  </style>

<


 
    </body>

</html>