# myShoppingSite

A simple dynamic shopping site demo. Deployed with AWS services. DEMO website: http://www.zhouyunlu.com

It is a maven project, using springMVC and hibernate. Apply Jquery validate plugin and hibernate validator for validation. Ajax to check duplicate username. Masonry plugin for layout optimizing.


## Deploy
This application is depolyed on AWS. 
* All pictures are saved in S3, if you want to save picture to remote server or localhost, modify addProductController.java and changeProductController.java. 
* To manage database connection, modify hibernate.cfg.xml, and change connection to remote database or localhost.

In this shopping site, a user can be both seller and buyer. 
## For Buyer:
* Buyers can add product to cart and set the product quantity. After placing orders, products' stock will be updated automatically.
* Buyers can manage cart and wishlist.
* Buyers can see order history and add comments to ordered products.
* Buyers can pay through Paypal and will receive a confirmed email after orders paid.

## For Seller:
* Sellers can add new products and edit information of products.
* Sellers can see all the order details of their products.
* Sellers cannot see their own products from the shopping pages or add their products to cart.


# Version
## IDE 
Eclipse(Spring Tool Suite)
### Frameworks
* Spring 3.2.3
* Hibernate 4.2.1
* SpringMVC 3.0.7
* Bootstrap 3.3.7   
### Server
Tomcat8.0
### Database
MySQL


## note 
Database file is myShoppingSite_query.sql 
Paypal service is deployed and can test through Paypal sandbox. 
