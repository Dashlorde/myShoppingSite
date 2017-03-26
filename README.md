# myShoppingSite

A simple dynamic shopping site demo. Website: http://www.zhouyunlu.com


It is a maven project, using springMVC and hibernate( Hibernate validator for property validation).  
In this shopping site, a user can be both seller and buyer. 
## For Buyer:
* Buyers can add product to cart and set the product quantity.
* Buyers can manage cart for deleting or editing quantity of products.
* After placing orders, products' stock will be updated automatically.
* Buyers can see order history and add comments to ordered products. All the Comments will be displayed in product information page.
* Buyers can pay through Paypal and will receive a confirmed email after order is paid.

## For Seller:
* Sellers can add new products and edit information of products.
* Sellers can see all the order details of their products.
* Sellers cannot see their own products from the shopping pages or add their products to cart.


# Version
## IDE 
Eclipse(Spring Tool Suite)
### Frameworks
* Spring3.2.3
* Hibernate4.2.1
* SpringMVC3.0.7 

### Server
Tomcat8.0
### Database
MySQL


## note 
Database file is myShoppingSite_query.sql 
Paypal service is deployed and can test through Paypal sandbox. 
