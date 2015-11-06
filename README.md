#POS Application#

POS(Point of Sale) Applications are used in every modern restaurant for digitally communicating orders between the front and the back of the house(servers and kitchen).

I've worked in restaurants for a long time, and I thought it would be a fun project to use some Hibernate and J2EE tools. I kept the UI pretty simple for simplicity's sake.

In this project, I really tried to stress a separation of layers as advised by Martin Fowler in his seminal work, Patterns of Enterprise Application Architecture. As one can see in the project structure I attempted to keep the various logic layers as separate as possible delineating View(effectively the JSP and CSS files), DTO, Service, and Database layers.

###Login Page###
![LoginPage](https://github.com/Nyzrus/PosApplication/blob/InitialBranch/PosApplication/pics/login.png)

The login JSP is connected to a servlet that passes the textbox parameters to a service class which percolates the parameters through a service layer to a database layer which checks the info against user information stored in the Hibernate database. The authentication query is returned as a boolean which is passed back through the service layer to the servlet which grants/denies access accordingly.

###Table View###
![Add User](https://github.com/Nyzrus/PosApplication/blob/InitialBranch/PosApplication/pics/tableView.png)

If the login is successful, the Table View JSP renders. The Table View is a navigation page which directs one to an Order View corresponding to a table, to a View All Orders page, or to Logout. This choice is handled by a switch statement in an accompanying TableView Servlet.

###Order View###
![Order View](https://github.com/Nyzrus/PosApplication/blob/InitialBranch/PosApplication/pics/orderView.png)
![Order View 2]
(https://github.com/Nyzrus/PosApplication/blob/InitialBranch/PosApplication/pics/orderViewwithOrder.png)

The OrderView View is a JSP page tied to an accompanying Servlet and javascript file. The Servlet handles information that will be passed to and from the database, while the javascript focuses on dynamically updating the page according to buttons selected on the page.

The Javascript file maintains a <select> menu on the left side of the page which dynamically creates <option> tags for each item added to the list. It also adds the necessary values to be added to the database to a hidden html input.

The Java operations and database queries run on the rendering and navigation away from the page. 
